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
    String accion = request.getParameter("accion");
    if (accion.equals("view")) {
        OpcionesIU opciones = (OpcionesIU) session.getAttribute("Opciones");
        String result = "{"
                + "\"contenidoTitulo\":\""  + opciones.getTitulo()  + "\","
                + "\"contenidoDinamico\":\"" + opciones.toHTML() + "\","
                + "\"tipo\":\"" + tipo + "\","
                + "\"lnkAyuda\":\"" + opciones.getAyuda() + "\""
                + "}";

        response.setContentType("text/plain");
        response.getWriter().write(result);
    }
    if (accion.equals("modalRolOpciones")) {
        RolIU roliu = (RolIU) session.getAttribute("RolIU");
         String result = "{"
            + "\"contenidoOpciones\":\"" + roliu.toHTMLRolOpciones() + "\","
            + "\"tipo\":\"" + tipo + "\""
            + "}";

        response.setContentType("text/plain");
        response.getWriter().write(result);
    }
%>
