<%@page import="dda.silabo.autentificarse.iu.LoginIU"%>
<%@page import="dda.silabo.datosgenerales.iu.DatosGeneralesIU"%>
<%
    LoginIU logueo = (LoginIU) session.getAttribute("logueo");
    if (logueo == null) {
        response.sendRedirect("../../login/loginControlador.jsp");
    } else {
        String jsonAsignaturaInfo = (String) session.getAttribute("jsonAsignaturaInfo");
        String opcion = request.getParameter("opcion");
        if (opcion != null) {
            if (opcion.equals("getDatosGenerales") && jsonAsignaturaInfo != null) {
                response.sendRedirect("DatosGeneralesModelo.jsp?opcion=getDatosGenerales");
            }
            if (opcion.equals("show")) {
                response.sendRedirect("DatosGeneralesVista.jsp");
            }
        }
    }

%>