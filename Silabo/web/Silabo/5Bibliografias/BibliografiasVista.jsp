<%@page import="dda.silabo.menulateral.MenuLateralIU"%>
<%@page import="dda.silabo.autentificarse.iu.LoginIU"%>
<%@page import="dda.silabo.observaciones.iu.ObservacionesIU"%>
<%@page import="dda.silabo.observaciones.comunes.Observacion"%>
<%@page import="dda.silabo.bibliografias.iu.BibliografiasIU"%>
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
    BibliografiasIU bibliografias = (BibliografiasIU) session.getAttribute("Bibliografias");
 
    if (accion.equals("save") || accion.equals("delete")) {
        MenuLateralIU menu = (MenuLateralIU) session.getAttribute("MenuLateral");
        menuLateral = menu.toHTMLDocente(0, logueo.getRolActivo());
    }
    String contenido = "";
    contenido = bibliografias.toHTML();
    String result = "{"
            + "\"contenidoTitulo\":\"" + bibliografias.getTitulo() + "\","
            + "\"contenidoDinamico\":\"" + contenido + "\","
            + "\"tipo\":\"" + tipo + "\","
            + "\"contenidoMenuLateral\":\"" + menuLateral + "\","
            + "\"lnkAyuda\":\"" + bibliografias.getAyuda() + "\""
            + "}";

    response.setContentType("text/plain");
         response.setCharacterEncoding("UTF-8");
    response.getWriter().write(result);
%>


