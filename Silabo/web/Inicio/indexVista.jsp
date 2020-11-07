<%@page import="dda.silabo.reportes.iu.CarrerasIU"%>
<%@page import="dda.silabo.roles.iu.RolIU"%>
<%@page import="dda.silabo.menulateral.ContenidoInicioIU"%>
<%@page import="dda.silabo.reportes.iu.ReporteGraficoIU"%>
<%@page import="com.google.gson.Gson"%>
<%@page import="dda.silabo.reportes.iu.EntidadIU"%>
<%@page import="dda.silabo.datosdocentes.iu.RolUsuarioIU"%>
<%@page import="dda.silabo.autentificarse.iu.LoginIU"%>
<%@page import="dda.silabo.datosgenerales.iu.DatosGeneralesIU"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    LoginIU logueo = (LoginIU) session.getAttribute("logueo");
    String tipo = "";
    if (logueo != null) {
        tipo = "html";
    } else {
        tipo = "login/loginControlador.jsp";
    }
    String accion = (request.getParameter("accion"));
    Gson G = new Gson();
    String jsonEntidad = request.getParameter("jsonEntidad");
    RolUsuarioIU rolIu = new RolUsuarioIU();
    if (accion.equals("panel")) {
        EntidadIU entidadIU = new EntidadIU();
        RolIU rolIU = (RolIU) session.getAttribute("RolIU");
        ContenidoInicioIU contenidoInicioIU = new ContenidoInicioIU();
        String resultado = "{"
                + "\"menuPanel\":\"" + contenidoInicioIU.menuPanel(logueo.getRoles()) + "\","
                + "\"nombreDocente\":\"" + entidadIU.nombreDocenteToHTML(logueo) + "\","
                + "\"docenteInfo\":\"" + entidadIU.docenteInfoToHTML() + "\","
                + "\"contenidoInfo\":\"" + rolIU.contenidoInfoHTML("reportes", null, null, null) + "\","
                + "\"contenidoRoles\":\"" + rolIu.toHTMLPanel(logueo.getRoles()) + "\","
                + "\"menuTipo\":\"" + rolIu.toHTMLMenuTipo(logueo.getRoles(), logueo.getRolActivo(), rolIU.getOpciones()) + "\","
                + "\"tipo\":\"" + tipo + "\","
                + "\"nombresdocentes\":\"" + "" + "\","
                + "\"rolActivo\":\"" + rolIu.toHTMLRolActivo(logueo.getRoles(), logueo.getRolActivo()) + "\""
                + "}";
        response.setContentType("text/plain");
        response.getWriter().write(resultado);
    } else if (accion.equals("getEntidad")) {
        ReporteGraficoIU reportePanelIU = (ReporteGraficoIU) session.getAttribute("Entidad" + logueo.getRolActivo() + logueo.getCedula() + jsonEntidad);
//        if (reportePanelIU.getElementos().isEmpty()) {
        if (reportePanelIU == null) {
            reportePanelIU = new ReporteGraficoIU(); 
            reportePanelIU.obtenerValoresDefectoGrafico();
        }
        String Entidad = G.toJson(reportePanelIU);
        response.setContentType("text/plain");
        response.getWriter().write(Entidad);
    }

%>
