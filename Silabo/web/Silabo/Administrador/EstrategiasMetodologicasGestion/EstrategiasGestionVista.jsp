
<%@page import="dda.silabo.estructura.unidad.estrategias.iu.EstrategiasMetodologicasIU"%>
<%@page import="dda.silabo.autentificarse.iu.LoginIU"%>
<%@page import="dda.silabo.estructura.unidad.estrategias.iu.EstrategiasGestionIU"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    LoginIU logueo = (LoginIU) session.getAttribute("logueo");
    String tipo = "";
    if (logueo != null) {
        tipo = "html";
    } else {
        tipo = "login/loginControlador.jsp";
    }
    EstrategiasMetodologicasIU estrategias = (EstrategiasMetodologicasIU) session.getAttribute("Estrategias");
    String result = "{"
            + "\"contenidoTitulo\":\"" + estrategias.getTitulo() + "\","
            + "\"contenidoDinamico\":\"" + estrategias.toHTML() + "\","
            + "\"tipo\":\"" + tipo + "\","
            + "\"lnkAyuda\":\"" + estrategias.getAyuda() + "\""
            + "}";

    response.setContentType("text/plain");
    response.getWriter().write(result);
%>
