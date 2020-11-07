<%@page import="dda.silabo.autentificarse.iu.LoginIU"%>
<%
    LoginIU logueo = (LoginIU) session.getAttribute("logueo");
    if (logueo == null) {
        response.sendRedirect("../../../login/loginControlador.jsp");
    } else {
        String opcion = request.getParameter("opcion");
        if (opcion != null) {
            if (opcion.equals("getParametros")) {
                String jsonSilabo = request.getParameter("jsonSilabo");
                response.sendRedirect("ParametrosModelo.jsp?opcion=getParametros&jsonSilabo=" + jsonSilabo);
            }
            if (opcion.equals("show")) {
                response.sendRedirect("ParametrosVista.jsp");
            }
            if (opcion.equals("saveParametros")) {
                String jsonSilabo = request.getParameter("jsonSilabo");
                String jsonParametros = request.getParameter("jsonParametros");
                session.setAttribute("jsonParametros", jsonParametros);
                if (jsonParametros != null) {
                    response.sendRedirect("ParametrosModelo.jsp?opcion=saveParametros&jsonSilabo=" + jsonSilabo);
                }
            }
        }
    }
%>

