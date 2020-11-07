<%@page import="dda.silabo.autentificarse.iu.LoginIU"%>
<%@page import="dda.silabo.menulateral.MenuLateralIU"%>
<%@page import="dda.silabo.observaciones.comunes.Observacion"%>
<%@page import="com.google.gson.Gson"%>
<%@page import="dda.silabo.observaciones.iu.ObservacionesIU"%>
<%@page import="dda.silabo.estructura.unidad.actividades.iu.ActividadesIU"%>
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
    String accion = request.getParameter("accion");
    String menuLateral = "";
    ActividadesIU actividades = (ActividadesIU) session.getAttribute("Actividades");
    if (accion.equals("save")) {
        MenuLateralIU menu = (MenuLateralIU) session.getAttribute("MenuLateral");
        menuLateral = menu.toHTMLDocente(actividades.getSilabos().getIdUnidad(), logueo.getRolActivo());
    }
    String tipoDA = (String) session.getAttribute("tipoDA");
    if (tipoDA == null) {
        tipoDA = "Aula";
    }
    String result = "{"
            + "\"contenidoTitulo\":\"" + actividades.getTitulo() + "\","
            + "\"contenidoDinamico\":\"" + actividades.toHTML(tipoDA) + "\","
            + "\"tipo\":\"" + tipo + "\","
            + "\"contenidoMenuLateral\":\"" + menuLateral + "\","
            + "\"lnkAyuda\":\"" + actividades.getAyuda() + "\""
            + "}";
    response.setContentType("text/plain");
    response.setCharacterEncoding("UTF-8");
    response.getWriter().write(result);
    

//toHTML(tipoDA)
%>