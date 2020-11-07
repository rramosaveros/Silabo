<%@page import="dda.silabo.autentificarse.iu.LoginIU"%>
<%
    LoginIU logueo = (LoginIU) session.getAttribute("logueo");
    if (logueo == null) {
        response.sendRedirect("../../login/loginControlador.jsp");
    } else {
        String opcion = request.getParameter("opcion");
        if (opcion != null) {
            if (opcion.equals("getObservaciones")) {
                session.setAttribute("jsonSilabo", request.getParameter("jsonSilabo"));
                response.sendRedirect("ObservacionModelo.jsp?opcion=getObservaciones");
            } else if (opcion.equals("show")) {
                String accion = request.getParameter("accion");
                response.sendRedirect("ObservacionVista.jsp?accion=" + accion);
            }

        }
    }
%>
