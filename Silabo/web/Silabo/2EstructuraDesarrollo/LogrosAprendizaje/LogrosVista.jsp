<%@page import="dda.silabo.autentificarse.iu.LoginIU"%>
<%@page import="dda.silabo.menulateral.MenuLateralIU"%>
<%@page import="java.util.List"%>
<%@page import="dda.silabo.observaciones.comunes.Observacion"%>
<%@page import="dda.silabo.observaciones.iu.ObservacionesIU"%>
<%@page import="dda.silabo.estructura.unidad.logros.iu.LogrosIU"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    LoginIU logueo = (LoginIU) session.getAttribute("logueo");
    String tipo = "";
    if (logueo != null) {
        tipo = "html";
    } else {
        tipo = "login/loginControlador.jsp";
    }
    String accion = request.getParameter("accion");
    String menuLateral = "";
    LogrosIU logros = (LogrosIU) session.getAttribute("Logros");
    if (accion.equals("save")) {
        MenuLateralIU menu = (MenuLateralIU) session.getAttribute("MenuLateral");
        menuLateral = menu.toHTMLDocente(logros.getSilabos().getIdUnidad(), logueo.getRolActivo());
    }
    String result = "{"
            + "\"contenidoTitulo\":\"" + logros.getTitulo() + "\","
            + "\"contenidoDinamico\":\"" + logros.toHTML() + "\","
            + "\"tipo\":\"" + tipo + "\","
            + "\"seleccionados\":\"" + logros.seleccionadostoHTML() + "\","
            + "\"contenidoMenuLateral\":\"" + menuLateral + "\","
            + "\"lnkAyuda\":\"" + logros.getAyuda() + "\""
            + "}";

    response.setContentType("text/plain");
    response.setCharacterEncoding("UTF-8");
    response.getWriter().write(result);
%>