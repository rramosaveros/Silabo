<%@page import="dda.silabo.estructura.unidad.info.iu.InformacionUnidadIU"%>
<%@page import="dda.silabo.criteriosevaluaciones.iu.CriteriosEvaluacionesIU"%>
<%@page import="dda.silabo.bibliografias.iu.BibliografiasIU"%>
<%@page import="dda.silabo.escenarios.iu.EscenariosIU"%>
<%@page import="dda.silabo.silabo.comunes.Silabo"%>
<%@page import="dda.silabo.reportes.iu.EntidadIU"%>
<%@page import="dda.silabo.autentificarse.iu.LoginIU"%>
<%@page import="dda.silabo.menulateral.MenuLateralIU"%>
<%@page import="dda.silabo.areadocentes.comunes.DocentesInformacion"%>
<%@page import="dda.silabo.importacion.iu.ImportarSilaboIU"%>
<%@page import="com.google.gson.Gson"%>
<%
    LoginIU logueo = (LoginIU) session.getAttribute("logueo");
    if (logueo == null) {
        response.sendRedirect("../../login/loginControlador.jsp");
    } else {
        dda.silabo.sw.SilaboSW_Service service = new dda.silabo.sw.SilaboSW_Service();
        dda.silabo.sw.SilaboSW port = service.getSilaboSWPort();
        String opcion = request.getParameter("opcion");
        String jsonAsignaturaInfo = (String) session.getAttribute("jsonAsignaturaInfo");
        String jsonSilabo = request.getParameter("jsonSilabo");
        Integer idSilabo = port.silaboImportacionIdSilabo(jsonSilabo);
        Gson G = new Gson();
        if (opcion.equals("getAsignaturasPeriodos")) {
            EntidadIU uAcademica = (EntidadIU) session.getAttribute("Entidad" + logueo.getRolActivo() + logueo.getCedula());
            if (uAcademica == null) {
                String uAcademica2 = port.unidadesAcademicasUsuarioCargar(G.toJson(logueo));
                uAcademica = G.fromJson(uAcademica2, EntidadIU.class);
                session.setAttribute("Entidad" + logueo.getRolActivo() + logueo.getCedula(), uAcademica);
            }

            ImportarSilaboIU importacioniu = null;// (ImportarSilaboIU) session.getAttribute("Importacion");
            if (importacioniu == null) {
                String jsonImportacion = port.asignaturasPeriodos(jsonAsignaturaInfo);
                if (!jsonImportacion.equals("vacio")) {
                    importacioniu = G.fromJson(jsonImportacion, ImportarSilaboIU.class);
                    session.setAttribute("Importacion", importacioniu);
                } else {
                    session.setAttribute("RespuestaG", "Vacio");
                }
            }
            response.sendRedirect("ImportarControlador.jsp?opcion=show&accion=view");
        } else if (opcion.equals("saveImportacion")) {
            String tipo = request.getParameter("tipo");
            Integer unidadAnterior = Integer.parseInt(request.getParameter("unidadAnterior"));
            Integer unidad = Integer.parseInt(request.getParameter("unidad"));
            Integer idTipo = Integer.parseInt(request.getParameter("idTipo"));
            Integer idSilaboActual = Integer.parseInt(request.getParameter("idSilaboActual"));
            Integer idSilaboAnterior = (Integer) session.getAttribute("idSilaboAnterior");
            String resp = port.silaboImportacionGenerar(tipo, unidad, idTipo, idSilaboActual, idSilaboAnterior, unidadAnterior);
            session.setAttribute("Respuesta", resp);
            response.sendRedirect("ImportarControlador.jsp?opcion=show&accion=save");
        } else if (opcion.equals("Escenarios")) {
            if (idSilabo != 0) {
                session.setAttribute("idSilaboAnterior", idSilabo);
                Silabo silabo = G.fromJson(jsonSilabo, Silabo.class);
                silabo.setIdSilabo(idSilabo);
                silabo.setTipo("Real");
                String jsonEscenarios = port.escenariosCargar(G.toJson(silabo));
                EscenariosIU escenariosReal = G.fromJson(jsonEscenarios, EscenariosIU.class);
                session.setAttribute("EscenariosReal", escenariosReal);
                silabo.setTipo("Virtual");
                jsonEscenarios = port.escenariosCargar(G.toJson(silabo));
                EscenariosIU escenariosVirtual = G.fromJson(jsonEscenarios, EscenariosIU.class);
                session.setAttribute("EscenariosVirtual", escenariosVirtual);
                silabo.setTipo("Aulico");
                jsonEscenarios = port.escenariosCargar(G.toJson(silabo));
                EscenariosIU escenariosAulico = G.fromJson(jsonEscenarios, EscenariosIU.class);
                session.setAttribute("EscenariosAulico", escenariosAulico);
                response.sendRedirect("ImportarControlador.jsp?opcion=show&accion=contenidoEscenarios");
            } else {
                response.sendRedirect("ImportarControlador.jsp?opcion=show&accion=errorContenido");
            }

        } else if (opcion.equals("Criterios")) {
            if (idSilabo != 0) {
                session.setAttribute("idSilaboAnterior", idSilabo);
                Silabo silabo = G.fromJson(jsonSilabo, Silabo.class);
                silabo.setIdSilabo(idSilabo);
                String jsonEvaluaciones = port.evaluacionesCargar(G.toJson(silabo));
                CriteriosEvaluacionesIU evaluaciones = G.fromJson(jsonEvaluaciones, CriteriosEvaluacionesIU.class);
                session.setAttribute("Evaluaciones", evaluaciones);
                response.sendRedirect("ImportarControlador.jsp?opcion=show&accion=contenidoCriterios");
            } else {
                response.sendRedirect("ImportarControlador.jsp?opcion=show&accion=errorContenido");
            }

        } else if (opcion.equals("Bibliografias")) {
            if (idSilabo != 0) {
                session.setAttribute("idSilaboAnterior", idSilabo);
                Silabo silabo = G.fromJson(jsonSilabo, Silabo.class);
                silabo.setIdSilabo(idSilabo);
                String jsonBibliografias = port.bibliografiasCargar(G.toJson(silabo));
                BibliografiasIU bibliografias = G.fromJson(jsonBibliografias, BibliografiasIU.class);
                session.setAttribute("Bibliografias", bibliografias);
                response.sendRedirect("ImportarControlador.jsp?opcion=show&accion=contenidoBibliografias");
            } else {
                response.sendRedirect("ImportarControlador.jsp?opcion=show&accion=errorContenido");
            }

        } else if (opcion.equals("informacionUnidad")) {
            if (idSilabo != 0) {
                session.setAttribute("idSilaboAnterior", idSilabo);
                Silabo silabo = G.fromJson(jsonSilabo, Silabo.class);
                silabo.setIdSilabo(idSilabo);
                String jsonInformacionUnidad = port.unidadInformacionSilabo(silabo.getCodCarrera(), silabo.getCodMateria(), silabo.getPeriodo());
                InformacionUnidadIU informacion = G.fromJson(jsonInformacionUnidad, InformacionUnidadIU.class);
                session.setAttribute("InformacionUnidad", informacion);
                response.sendRedirect("ImportarControlador.jsp?opcion=show&accion=contenidoUnidades");
            } else {
                response.sendRedirect("ImportarControlador.jsp?opcion=show&accion=errorContenido");
            }

        }

    }
%>
