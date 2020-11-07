<%@page import="dda.silabo.estructura.unidad.info.iu.InformacionUnidadIU"%>
<%@page import="dda.silabo.escenarios.iu.EscenariosIU"%>
<%@page import="dda.silabo.criteriosevaluaciones.iu.CriteriosEvaluacionesIU"%>
<%@page import="dda.silabo.bibliografias.iu.BibliografiasIU"%>
<%@page import="dda.silabo.subirarchivo.iu.ArchivoIU"%>
<%@page import="dda.silabo.reportes.iu.EntidadIU"%>
<%@page import="dda.silabo.menulateral.MenuLateralIU"%>
<%@page import="dda.silabo.autentificarse.iu.LoginIU"%>
<%@page import="dda.silabo.areadocentes.comunes.DocentesInformacion"%>
<%@page import="com.google.gson.Gson"%>
<%@page import="dda.silabo.silabo.comunes.Silabo"%>
<%@page import="dda.silabo.importacion.iu.ImportarSilaboIU"%>
<%
    LoginIU logueo = (LoginIU) session.getAttribute("logueo");
    String tipo = "";
    if (logueo != null) {
        tipo = "html";
    } else {
        tipo = "login/loginControlador.jsp";
    }

    String accion = request.getParameter("accion");
    String menuLateral = "";
    ArchivoIU archivoIU = new ArchivoIU();
    if (accion.equals("view")) {
        String respuestaImportacion = (String) session.getAttribute("RespuestaG");
        if (respuestaImportacion == null) {
            ImportarSilaboIU importariu = (ImportarSilaboIU) session.getAttribute("Importacion");
            EntidadIU uAcademica = (EntidadIU) session.getAttribute("Entidad" + logueo.getRolActivo() + logueo.getCedula());
            String result = "{"
                    + "\"periodos\":\"" + importariu.toHTMLPeriodos(0) + "\","
                    + "\"carreras\":\"" + importariu.toHTMLCarreras(0, uAcademica) + "\","
                    + "\"contenidoMenuLateral\":\"" + menuLateral + "\","
                    + "\"modalImportar\":\"" + archivoIU.modalImportartoHTML() + "\","
                    + "\"tipo\":\"" + tipo + "\","
                    + "\"informacion\":\"Datos\","
                    + "\"AsignaturasAnteriores\":\"" + importariu.toHTMLAnteriores(0) + "\""
                    + "}";
            response.setContentType("text/plain");
            response.getWriter().write(result);
        } else {
            String result = "{"
                    + "\"informacion\":\"Vacio\","
                    + "\"modalImportar\":\"" + archivoIU.modalImportartoHTML() + "\","
                    + "\"tipo\":\"" + tipo + "\""
                    + "}";
            response.setContentType("text/plain");
            response.getWriter().write(result);
        }
    } else if (accion.equals("errorContenido")) {
        ImportarSilaboIU importariu = new ImportarSilaboIU();
        String result = "{"
                + "\"contenidos\":\"" + importariu.toHTMLContenido() + "\","
                + "\"tituloContenido\":\"" + "Contenido no Disponible" + "\""
                + "}";
        response.setContentType("text/plain");
        response.getWriter().write(result);
    } else if (accion.equals("contenidoBibliografias")) {
        BibliografiasIU bibliografias = (BibliografiasIU) session.getAttribute("Bibliografias");
        String result = "{"
                + "\"contenidos\":\"" + bibliografias.toHTMLImportacion() + "\","
                + "\"tituloContenido\":\"" + "Contenido de Bibliografías" + "\""
                + "}";
        response.setContentType("text/plain");
        response.getWriter().write(result);
    } else if (accion.equals("contenidoCriterios")) {
        CriteriosEvaluacionesIU criteriosEvaluacionesIU = (CriteriosEvaluacionesIU) session.getAttribute("Evaluaciones");
        String result = "{"
                + "\"contenidos\":\"" + criteriosEvaluacionesIU.toHTMLImportacion() + "\","
                + "\"tituloContenido\":\"" + "Contenido de Criterios de Evaluacón" + "\""
                + "}";
        response.setContentType("text/plain");
        response.getWriter().write(result);
    } else if (accion.equals("contenidoEscenarios")) {
        EscenariosIU escenariosIU = new EscenariosIU();
        EscenariosIU escenariosReal = (EscenariosIU) session.getAttribute("EscenariosReal");
        EscenariosIU escenariosVirtual = (EscenariosIU) session.getAttribute("EscenariosVirtual");
        EscenariosIU escenariosAulico = (EscenariosIU) session.getAttribute("EscenariosAulico");
        String result = "{"
                + "\"contenidos\":\"" + escenariosIU.toHTMLImportacion(escenariosReal.getEscenarios(), escenariosAulico.getEscenarios(), escenariosVirtual.getEscenarios()) + "\","
                + "\"tituloContenido\":\"" + "Contenido para Escenarios de Aprendizaje" + "\""
                + "}";
        response.setContentType("text/plain");
        response.getWriter().write(result);
    } else if (accion.equals("contenidoUnidades")) {
        InformacionUnidadIU informacion = (InformacionUnidadIU) session.getAttribute("InformacionUnidad");
        String result = "{"
                + "\"contenidos\":\"" + informacion.toHTMLImportacion() + "\","
                + "\"tituloContenido\":\"" + "Contenido para Unidades" + "\""
                + "}";
        response.setContentType("text/plain");
        response.getWriter().write(result);
    } else {
        String respuesta = (String) session.getAttribute("Respuesta");
        String result = "{"
                + "\"respuesta\":\"" + respuesta + "\""
                + "}";
        response.setContentType("text/plain");
        response.getWriter().write(result);
    }
%>
