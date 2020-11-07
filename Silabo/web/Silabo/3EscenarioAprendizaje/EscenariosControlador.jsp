<%@page import="dda.silabo.autentificarse.iu.LoginIU"%>
<%
    LoginIU logueo = (LoginIU) session.getAttribute("logueo");
    if (logueo == null) {
        response.sendRedirect("../../login/loginControlador.jsp");
    } else {
        String jsonSilabo = (String) session.getAttribute("jsonSilabo");
        String opcion = request.getParameter("opcion");
        if (opcion != null) {
            if (opcion.equals("getEscenarios") && jsonSilabo != null) {
                response.sendRedirect("EscenariosModelo.jsp?opcion=getEscenarios");
            }
            if (opcion.equals("show")) {
                String accion = request.getParameter("accion");
                response.sendRedirect("EscenariosVista.jsp?accion=" + accion);
            }
            if (opcion.equals("saveEscenarios")) {
                String jsonEscenarios = request.getParameter("jsonEscenarios");
                session.setAttribute("jsonEscenarios", jsonEscenarios);
                response.sendRedirect("EscenariosModelo.jsp?opcion=saveEscenarios");
            }

        }
    }
%>
