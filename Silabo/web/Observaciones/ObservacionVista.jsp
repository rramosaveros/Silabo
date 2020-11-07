<%@page import="dda.silabo.autentificarse.iu.LoginIU"%>
<%@page import="dda.silabo.menulateral.MenuLateralIU"%>
<%@page import="com.google.gson.Gson"%>
<%@page import="dda.silabo.observaciones.comunes.Observacion"%>
<%@page import="dda.silabo.observaciones.iu.ObservacionesIU"%>
<%@page import="dda.silabo.escenarios.iu.EscenariosIU"%>
<%@page import="java.util.List"%>
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
    ObservacionesIU obsHTML = (ObservacionesIU) session.getAttribute("ObservacionesIU");
//    if (accion.equals("save")) {
//        MenuLateralIU menu = (MenuLateralIU) session.getAttribute("MenuLateral");
//        menuLateral = menu.toHTMLDocente(-1, logueo.getRolActivo());
//    }
    String result = "{"
            + "\"contenidoObservacion\":\"" + obsHTML.toHTML(obsHTML.getAccionBoton(), logueo.getRolActivo(), "Guardar Cambios", obsHTML.getIdTipo()) + "\""
            + "}";
    response.setContentType("text/plain");
    response.setCharacterEncoding("UTF-8");
    response.getWriter().write(result);

%>
