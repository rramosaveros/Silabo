<%@page import="dda.silabo.criteriosevaluaciones.comunes.CriteriosEvaluaciones"%>
<%@page import="dda.silabo.autentificarse.iu.LoginIU"%>
<%@page import="dda.silabo.menulateral.MenuLateralIU"%>
<%@page import="dda.silabo.observaciones.comunes.Observacion"%>
<%@page import="dda.silabo.observaciones.iu.ObservacionesIU"%>
<%@page import="dda.silabo.criteriosevaluaciones.iu.CriteriosEvaluacionesIU"%>
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
    CriteriosEvaluacionesIU evaluaciones = (CriteriosEvaluacionesIU) session.getAttribute("Evaluaciones");
    String menuLateral = "";
    String accion = request.getParameter("accion");
    if (accion.equals("save")) {
        MenuLateralIU menu = (MenuLateralIU) session.getAttribute("MenuLateral");
        menuLateral = menu.toHTMLDocente(0, logueo.getRolActivo());
    }
    String result = "{"
            + "\"contenidoTitulo\":\"" + evaluaciones.getTitulo() + "\","
            + "\"contenidoDinamico\":\"" + evaluaciones.toHTML() + "\","
            + "\"tipo\":\"" + tipo + "\","
            + "\"contenidoMenuLateral\":\"" + menuLateral + "\","
            + "\"lnkAyuda\":\"" + evaluaciones.getAyuda() + "\""
            + "}";

    response.setContentType("text/plain");
    response.setCharacterEncoding("UTF-8");
    response.getWriter().write(result);
%>