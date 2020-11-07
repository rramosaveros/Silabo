
<%@page import="dda.silabo.autentificarse.iu.LoginIU"%>
<%@page import="dda.silabo.estructura.unidad.estrategias.iu.EstrategiasGestionIU"%>
<%
//    LoginIU logueo = (LoginIU) session.getAttribute("logueo");
//    if (logueo == null) {
//        response.sendRedirect("../../../login/loginControlador.jsp");
//    } else {
//    String jsonSilabo = (String) session.getAttribute("jsonSilabo");
    String opcion = request.getParameter("opcion");
    if (opcion != null) {
        if (opcion.equals("getEstrategias")) {
            response.sendRedirect("EstrategiasGestionModelo.jsp?opcion=getEstrategias");
        }
        if (opcion.equals("show")) {
            response.sendRedirect("EstrategiasGestionVista.jsp");
        }
        if (opcion.equals("saveEstrategias")) {
            String jsonGEstrategias = request.getParameter("jsonGEstrategias");
            session.setAttribute("jsonGEstrategias", jsonGEstrategias);
            response.sendRedirect("EstrategiasGestionModelo.jsp?opcion=saveEstrategias");
        }
        if (opcion.equals("deleteEstrategia")) {
            String jsonEstrategias = request.getParameter("jsonEstrategias");
            if (jsonEstrategias != null) {
                response.sendRedirect("EstrategiasGestionModelo.jsp?jsonEstrategias=" + jsonEstrategias + "&opcion=deleteEstrategia");
            } else {
                response.sendRedirect("../../../indexSilaboControlador.jsp?tipo=mnuAdministrador");
            }
        }
        if (opcion.equals("habilitarEstrategia")) {
            String jsonEstrategias = request.getParameter("jsonEstrategias");
            if (jsonEstrategias != null) {
                response.sendRedirect("EstrategiasGestionModelo.jsp?jsonEstrategias=" + jsonEstrategias + "&opcion=habilitarEstrategia");
            } else {
                response.sendRedirect("../../../indexSilaboControlador.jsp?tipo=mnuAdministrador");
            }
        }
//        }
    }

%>
