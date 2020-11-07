<%@page import="dda.silabo.reportes.iu.CarrerasIU"%>
<%@page import="dda.silabo.roles.iu.RolIU"%>
<%@page import="dda.silabo.reportes.iu.ReporteGraficoIU"%>
<%@page import="dda.silabo.datosdocentes.iu.RolUsuarioIU"%>
<%@page import="dda.silabo.autentificarse.iu.LoginIU"%>
<%@page import="dda.silabo.silabo.comunes.Silabo"%>
<%@page import="dda.silabo.areadocentes.comunes.DocentesInformacion"%>
<%@page import="dda.silabo.reportes.iu.EntidadIU"%>
<%@page import="com.google.gson.Gson"%>
<%@page import="java.util.List"%>

<%
    LoginIU logueo = (LoginIU) session.getAttribute("logueo");
    if (logueo != null) {
        String tipo = request.getParameter("tipo");
        dda.silabo.sw.SilaboSW_Service service = new dda.silabo.sw.SilaboSW_Service();
        dda.silabo.sw.SilaboSW port = service.getSilaboSWPort();
        Gson g = new Gson();
        if (tipo.equals("panel")) {
            String jsonEntidad = request.getParameter("jsonEntidad");
            ReporteGraficoIU reportePanelIU = null;//(ReporteGraficoIU) session.getAttribute("Entidad" + logueo.getRolActivo() + logueo.getCedula() + jsonEntidad);
//            if (reportePanelIU == null) {
//                if (!logueo.getRoles().isEmpty()) {
                    String jsonReporteUsuarioRol = port.reporteUnidadesAcademicasUsuario(jsonEntidad, g.toJson(logueo));
                    reportePanelIU = g.fromJson(jsonReporteUsuarioRol, ReporteGraficoIU.class);
                    session.setAttribute("Entidad" + logueo.getRolActivo() + logueo.getCedula() + jsonEntidad, reportePanelIU);
//                }
//            }
            String jsonRol = port.rolOpcionesCargar("{'idRol':" + logueo.getIdRolActivo() + "}");
            RolIU rolIU = g.fromJson(jsonRol, RolIU.class);
            session.setAttribute("RolIU", rolIU);
            response.sendRedirect("indexControlador.jsp?tipo=show&accion=" + tipo + "&jsonEntidad=" + jsonEntidad);
        }
    }
%>    