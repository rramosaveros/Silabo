<%@page import="dda.silabo.autentificarse.iu.LoginIU"%>
<%@page import="dda.silabo.menulateral.MenuLateralIU"%>
<%@page import="dda.silabo.observaciones.comunes.Observacion"%>
<%@page import="java.util.List"%>
<%@page import="dda.silabo.observaciones.iu.ObservacionesIU"%>
<%@page import="dda.silabo.estructura.unidad.objetivos.iu.ObjetivosIU"%>
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
    ObjetivosIU objetivos = (ObjetivosIU) session.getAttribute("Objetivos");
    if (accion.equals("save")) {
        MenuLateralIU menu = (MenuLateralIU) session.getAttribute("MenuLateral");
        menuLateral = menu.toHTMLDocente(objetivos.getSilabos().getIdUnidad(), logueo.getRolActivo());
    }
    String result = "{"
            + "\"contenidoTitulo\":\"" + objetivos.getTitulo() + "\","
            + "\"contenidoDinamico\":\"" + objetivos.toHTML() + "\","
            + "\"tipo\":\"" + tipo + "\","
            + "\"contenidoMenuLateral\":\"" + menuLateral + "\","
            + "\"lnkAyuda\":\"" + objetivos.getAyuda() + "\""
            + "}";

    response.setContentType("text/plain");
    response.setCharacterEncoding("UTF-8");
    response.getWriter().write(result);
%>