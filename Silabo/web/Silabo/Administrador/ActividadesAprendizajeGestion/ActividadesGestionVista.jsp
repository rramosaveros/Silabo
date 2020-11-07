<%@page import="dda.silabo.autentificarse.iu.LoginIU"%>
<%@page import="dda.silabo.estructura.unidad.actividades.iu.ActividadesGestionIU"%>
<%@page import="com.google.gson.Gson"%>
<%
    LoginIU logueo = (LoginIU) session.getAttribute("logueo");
    String tipo = "";
    if (logueo != null) {
        tipo = "html";
    } else {
        tipo = "login/loginControlador.jsp";
    }
    ActividadesGestionIU actividades = (ActividadesGestionIU) session.getAttribute("Actividades");
    String tipoA = (String) session.getAttribute("tipoA");
    if (tipoA==null){
    tipoA="Aula";
    }

    String result = "{"
            + "\"contenidoTitulo\":\"" + actividades.getTitulo() + "\","
            + "\"contenidoDinamico\":\"" + actividades.toHTML(tipoA) + "\","
            + "\"tipo\":\"" + tipo + "\","
            + "\"lnkAyuda\":\"" + actividades.getAyuda() + "\""
            + "}";
    response.setContentType("text/plain");
    response.getWriter().write(result);
%>
