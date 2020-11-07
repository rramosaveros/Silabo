<%@page import="dda.silabo.subirarchivo.iu.ArchivoIU"%>
<%@page import="dda.silabo.autentificarse.iu.LoginIU"%>
<%
    LoginIU logueo = (LoginIU) session.getAttribute("logueo");
    if (logueo == null) {
        response.sendRedirect("../login/loginControlador.jsp");
    } else {
        String estado = (String) session.getAttribute("estado");
        ArchivoIU archivoIU = new ArchivoIU();
        String resultado = "{"
                + "\"modalSubirArchivo\":\"" + archivoIU.modalArchivotoHTML(estado) + "\""
                + "}";
        response.setContentType("text/plain");
        response.getWriter().write(resultado);
    }

%>