<%@page import="dda.silabo.autentificarse.iu.LoginIU"%>
<%@page import="dda.silabo.escenarios.iu.EscenariosGestionIU"%>
<%@page import="com.google.gson.Gson"%>
<%
    LoginIU logueo = (LoginIU) session.getAttribute("logueo");
    String tipo = "";
    if (logueo != null) {
        tipo = "html";
    } else {
        tipo = "login/loginControlador.jsp";
    }
    EscenariosGestionIU escenarios = (EscenariosGestionIU) session.getAttribute("Escenarios");
    String result = "{"
            + "\"contenidoTitulo\":\"" + escenarios.getTitulo() + "\","
            + "\"contenidoDinamico\":\"" + escenarios.toHTML() + "\","
            + "\"tipo\":\"" + tipo + "\","
            + "\"lnkAyuda\":\"" + escenarios.getAyuda() + "\""
            + "}";

    response.setContentType("text/plain");
    response.getWriter().write(result);
%>
