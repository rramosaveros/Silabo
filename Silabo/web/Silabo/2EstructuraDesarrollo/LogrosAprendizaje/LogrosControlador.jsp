
<%@page import="dda.silabo.autentificarse.iu.LoginIU"%>
<%
    LoginIU logueo = (LoginIU) session.getAttribute("logueo");
    if (logueo == null) {
        response.sendRedirect("../../../login/loginControlador.jsp");
    } else {
        String jsonSilabo = (String) session.getAttribute("jsonSilabo");
        String opcion = request.getParameter("opcion");
        if (opcion != null) {
            if (opcion.equals("getLogros") && jsonSilabo != null) {
                response.sendRedirect("LogrosModelo.jsp?opcion=getLogros");
            }
            if (opcion.equals("show")) {
                String accion = request.getParameter("accion");
                response.sendRedirect("LogrosVista.jsp?accion=" + accion);
            }
            if (opcion.equals("saveLogro")) {
                String jsonLogro = request.getParameter("jsonLogro");
                session.setAttribute("jsonLogro", jsonLogro);
                response.sendRedirect("LogrosModelo.jsp?opcion=saveLogro");
            }

        }
    }
%>
