<%@page import="dda.silabo.menulateral.comunes.SeccionSilabo"%>
<%@page import="ec.edu.espoch.academico.Periodo"%>
<%@page import="dda.silabo.roles.iu.RolIU"%>
<%@page import="dda.silabo.reportes.iu.CarrerasIU"%>
<%@page import="dda.silabo.reportes.iu.EntidadIU"%>
<%@page import="java.util.logging.Logger"%>
<%@page import="java.util.logging.Level"%>
<%@page import="dda.silabo.datosgenerales.iu.DatosGeneralesIU"%>
<%@page import="dda.silabo.autentificarse.iu.LoginIU"%>
<%@page import="dda.silabo.menulateral.MenuLateralIU"%>
<%@page import="dda.silabo.silabo.comunes.Silabo"%>
<%@page import="dda.silabo.datosdocentes.iu.DatosDocentesIU"%>
<%@page import="com.google.gson.Gson"%>
<%
    LoginIU logueo = (LoginIU) session.getAttribute("logueo");
    if (logueo == null) {
        response.sendRedirect("../../login/loginControlador.jsp");
    } else {
        try {
            dda.silabo.sw.SilaboSW_Service service = new dda.silabo.sw.SilaboSW_Service();
            dda.silabo.sw.SilaboSW port = service.getSilaboSWPort();
            String opcion = request.getParameter("opcion");
            String codCarrera = (String) session.getAttribute("codCarrera");
            String codMateria = (String) session.getAttribute("codMateria");
            String codFacultad = (String) session.getAttribute("codFacultad");
            String jsonDatosDocente = "";
            Gson G = new Gson();
            if (opcion.equals("getDatosDocente")) {
                jsonDatosDocente = (String) session.getAttribute("jsonAsignaturaInfo");
                String jsonDatosDocentes = port.datosDocentesCargar(jsonDatosDocente);
                DatosDocentesIU datosdocentes = G.fromJson(jsonDatosDocentes, DatosDocentesIU.class);
                session.setAttribute("DatosDocentesSilabo", datosdocentes);
                response.sendRedirect("DatosDocentesControlador.jsp?opcion=show");
            }
            if (opcion.equals("getDocenteInicio")) {
                EntidadIU entidadIU = (EntidadIU) session.getAttribute("Entidad" + logueo.getRolActivo() + logueo.getCedula());
                if (entidadIU == null) {
                    String uAcademica = port.unidadesAcademicasUsuarioCargar(G.toJson(logueo));
                    entidadIU = G.fromJson(uAcademica, EntidadIU.class);
                    session.setAttribute("Entidad" + logueo.getRolActivo() + logueo.getCedula(), entidadIU);
                }
                if (codCarrera.equals("todos")) {
                    codCarrera = entidadIU.getPrimerObjetoCarrera(codFacultad, codCarrera);
                } else {
                    codCarrera = "{'codCarrera':'" + codCarrera + "'}";
                }
                CarrerasIU carreraIU2 = G.fromJson(codCarrera, CarrerasIU.class);
                String codCarreraaux = carreraIU2.getCodCarrera();
                if (codMateria.equals("todos")) {
                    CarrerasIU carrerasIU = (CarrerasIU) session.getAttribute("CarreraIU" + logueo.getRolActivo() + logueo.getCedula() + codCarreraaux);
                    if (carrerasIU == null) {
                        String jsonAsignaturasCarrera = port.asignaturasCarreraUsuario(codCarrera, G.toJson(logueo));
                        carrerasIU = G.fromJson(jsonAsignaturasCarrera, CarrerasIU.class);
                        session.setAttribute("CarreraIU" + logueo.getRolActivo() + logueo.getCedula() + codCarreraaux, carrerasIU);
                    }
                    codMateria = carrerasIU.getPrimeraMateria();

                }
                codCarrera = carreraIU2.getCodCarrera();
                Periodo periodo = port.periodoActual();
                Silabo silabo = new Silabo();
                silabo.setRol(logueo.getRolActivo());
                silabo.setPeriodo(periodo.getCodigo());
                silabo.setCodCarrera(codCarrera);
                silabo.setCedula(logueo.getCedula());
                silabo.setCodMateria(codMateria);
                DatosGeneralesIU datosgenerales = (DatosGeneralesIU) session.getAttribute("DatosGenerales_" + logueo.getCedula() + codCarrera + codMateria);
                if (datosgenerales == null) {
                    String jsonDatosGenerales = port.datosGeneralesCargar(G.toJson(silabo));
                    datosgenerales = G.fromJson(jsonDatosGenerales, DatosGeneralesIU.class);
                    session.setAttribute("DatosGenerales_" + logueo.getCedula() + codCarrera + codMateria, datosgenerales);
                }
                session.setAttribute("jsonAsignaturaInfo", G.toJson(silabo));
                session.setAttribute("codCarrera", codCarrera);
                session.setAttribute("codMateria", codMateria);
                session.setAttribute("codPeriodo", periodo.getCodigo());
                Integer idSilabo = port.silaboIdCargar(G.toJson(silabo));
                if (idSilabo != 0) {
                    session.setAttribute("idSilabo", idSilabo);
                    String jsonMenuSilabo = port.menuSilaboCargar(G.toJson(silabo));
                    MenuLateralIU menulateral = G.fromJson(jsonMenuSilabo, MenuLateralIU.class);
                    session.setAttribute("MenuLateral", menulateral);
                    String jsonDocenteInfo = port.datosDocentesInicioCargar(G.toJson(silabo));
                    DatosDocentesIU datosDocentesIU = G.fromJson(jsonDocenteInfo, DatosDocentesIU.class);
                    String jsonRol = port.rolOpcionesCargar("{'idRol':" + logueo.getIdRolActivo() + "}");
                    RolIU rolIU = G.fromJson(jsonRol, RolIU.class);
                    session.setAttribute("RolIU", rolIU);
                    session.setAttribute("DatosDocente", datosDocentesIU);
                } else {
                    MenuLateralIU menulateral = new MenuLateralIU();
                    SeccionSilabo seccionSilabo = new SeccionSilabo();
                    seccionSilabo.setTipoSeccion("Secciones No definidas");
                    menulateral.getSecciones().add(seccionSilabo);
                    session.setAttribute("MenuLateral", menulateral);
                }
                response.sendRedirect("DatosDocentesControlador.jsp?opcion=showInicio");
            }

            if (opcion.equals("updateDocente")) {
                String jsonDocente = (String) session.getAttribute("jsonDocente");
                String jsonSilabo = (String) session.getAttribute("jsonAsignaturaInfo");
                String jsonDatosDocentes = port.datosDocenteActualizar(jsonDocente, jsonSilabo);
                DatosDocentesIU datosdocentes = G.fromJson(jsonDatosDocentes, DatosDocentesIU.class);
                session.setAttribute("DatosDocentesSilabo", datosdocentes);
                response.sendRedirect("DatosDocentesControlador.jsp?opcion=show");
            }
        } catch (Exception e) {
            response.sendError(300, e.toString());
        }
    }
%>   