<%@page import="dda.silabo.autentificarse.iu.LoginIU"%>
<%@page import="dda.silabo.datosdocentes.iu.DatosDocentesIU"%>
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
    DatosDocentesIU datosdocentes = (DatosDocentesIU) session.getAttribute("DatosDocentesSilabo");
    DatosDocentesIU docentesIU = (DatosDocentesIU) session.getAttribute("DatosDocente");
    String result = "";
    result = "{"
            + "\"contenidoTitulo\":\"" + datosdocentes.getTitulo() + "\","
            + "\"contenidoDinamico\":\"" + datosdocentes.toHTML(logueo.getCedula(), logueo.getRolActivo(), docentesIU) + "\","
            + "\"tipo\":\"" + tipo + "\","
            + "\"lnkAyuda\":\"" + datosdocentes.getAyuda() + "\""
            + "}";
    response.setContentType("text/plain");
    response.setCharacterEncoding("UTF-8");
    response.getWriter().write(result);
%>



