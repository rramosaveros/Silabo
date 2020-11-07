<%@page import="dda.silabo.autentificarse.iu.LoginIU"%>
<%
    LoginIU logueo = (LoginIU) session.getAttribute("logueo");
    if (logueo == null) {
        response.sendRedirect("../login/loginControlador.jsp");
    } else {
        String opcion = request.getParameter("opcion");
        if (opcion != null) {
            if (opcion.equals("generarSilabo")) {
                String jsonSilabo = request.getParameter("jsonSilabo");
                response.sendRedirect("SilaboGenerarModelo.jsp?opcion=generarSilabo&jsonSilabo=" + jsonSilabo);
            } else if (opcion.equals("subir")) {
                response.sendRedirect("SilaboGenerarModelo.jsp?opcion=" + opcion);
            } else if (opcion.equals("show")) {
                response.sendRedirect("SilaboGenerarVista.jsp");
            }

        }
    }
%>
