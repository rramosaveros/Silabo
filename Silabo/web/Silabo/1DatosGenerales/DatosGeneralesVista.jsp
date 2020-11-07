<%@page import="dda.silabo.autentificarse.iu.LoginIU"%>
<%@page import="dda.silabo.datosgenerales.iu.DatosGeneralesIU"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    String result = "";
    LoginIU logueo = (LoginIU) session.getAttribute("logueo");
    String tipo = "";
    if (logueo != null) {
        tipo = "html";
    } else {
        tipo = "login/loginControlador.jsp";
    }
    String codCarrera = (String) session.getAttribute("codCarrera");
    String codMateria = (String) session.getAttribute("codMateria");
    DatosGeneralesIU datosgenerales = (DatosGeneralesIU) session.getAttribute("DatosGenerales_" + logueo.getCedula() + codCarrera + codMateria);
    result = "{"
            + "\"contenidoTitulo\":\"" + datosgenerales.getTitulo() + "\","
            + "\"contenidoDinamico\":\"" + datosgenerales.toHTML() + "\","
            + "\"tipo\":\"" + tipo + "\","
            + "\"lnkAyuda\":\"" + datosgenerales.getAyuda() + "\""
            + "}";

    response.setContentType("text/plain");
    response.getWriter().write(result);
%>
