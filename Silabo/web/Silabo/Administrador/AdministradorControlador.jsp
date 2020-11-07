
<%@page import="dda.silabo.autentificarse.iu.LoginIU"%>
<%
    LoginIU logueo = (LoginIU) session.getAttribute("logueo");
    if (logueo == null) {
        response.sendRedirect("../../login/loginControlador.jsp");
    } else {
        String tipo = request.getParameter("tipo");
        String jsonSilabo = request.getParameter("jsonSilabo");
        if (tipo != null && jsonSilabo != null) {
            session.setAttribute("jsonSilabo", jsonSilabo);
            session.setAttribute("tipo", tipo);
            response.sendRedirect("AdministradorModelo.jsp");
        }
        if (tipo.equals("getMenuAdministrador")) {
            session.setAttribute("tipo", tipo);
            response.sendRedirect("AdministradorModelo.jsp");
        }
        if (tipo.equals("show")) {
            response.sendRedirect("AdministradorVista.jsp");
        }
    }

%>
