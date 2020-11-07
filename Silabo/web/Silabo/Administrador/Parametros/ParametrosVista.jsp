<%@page import="dda.silabo.parametros.iu.ParametrosIU"%>
<%@page import="dda.silabo.roles.iu.RolIU"%>
<%@page import="dda.silabo.opciones.iu.OpcionesIU"%>
<%@page import="dda.silabo.autentificarse.iu.LoginIU"%>
<%
    LoginIU logueo = (LoginIU) session.getAttribute("logueo");
    String tipo = "";
    if (logueo != null) {
        tipo = "html";
    } else {
        tipo = "login/loginControlador.jsp";
    }
        ParametrosIU parametros = (ParametrosIU) session.getAttribute("Parametros");
        String result = "{"
                + "\"contenidoTitulo\":\""  + parametros.getTitulo() +  "\","
                + "\"contenidoDinamico\":\"" + parametros.toHTML() + "\","
                + "\"tipo\":\"" + tipo + "\","
                + "\"lnkAyuda\":\"" + parametros.getAyuda() + "\""
                + "}";

        response.setContentType("text/plain");
        response.getWriter().write(result);
   
%>
