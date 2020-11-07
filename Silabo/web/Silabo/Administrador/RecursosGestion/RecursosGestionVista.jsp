<%@page import="dda.silabo.autentificarse.iu.LoginIU"%>
<%@page import="com.google.gson.Gson"%>
<%@page import="dda.silabo.estructura.unidad.recursos.iu.RecursosGestionIU"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    LoginIU logueo = (LoginIU) session.getAttribute("logueo");
    String tipo = "";
    if (logueo != null) {
        tipo = "html";
    } else {
        tipo = "login/loginControlador.jsp";
    }
    RecursosGestionIU recursos = (RecursosGestionIU) session.getAttribute("Recursos");
    String result = "{"
            + "\"contenidoTitulo\":\"" + recursos.getTitulo() + "\","
            + "\"contenidoDinamico\":\"" + recursos.toHTML() + "\","
            + "\"tipo\":\"" + tipo + "\","
            + "\"lnkAyuda\":\"" + recursos.getAyuda() + "\""
            + "}";

    response.setContentType("text/plain");
    response.getWriter().write(result);
%>
