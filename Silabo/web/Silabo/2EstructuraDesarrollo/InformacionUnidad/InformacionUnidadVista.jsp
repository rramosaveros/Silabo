<%@page import="dda.silabo.menulateral.MenuLateralIU"%>
<%@page import="dda.silabo.observaciones.iu.ObservacionesIU"%>
<%@page import="dda.silabo.autentificarse.iu.LoginIU"%>
<%@page import="dda.silabo.estructura.unidad.info.iu.InformacionUnidadIU"%>
<%
    LoginIU logueo = (LoginIU) session.getAttribute("logueo");
    String tipo = "";
    if (logueo != null) {
        tipo = "html";
    } else {
        tipo = "login/loginControlador.jsp";
    }
    String accion = request.getParameter("accion");
    if (!accion.equals("gestionarUnidades")) {
        String menuLateral = "";
        InformacionUnidadIU informacion = (InformacionUnidadIU) session.getAttribute("InformacionUnidad");
        if (accion.equals("save")) {
            MenuLateralIU menu = (MenuLateralIU) session.getAttribute("MenuLateral");
            menuLateral = menu.toHTMLDocente(informacion.getSilabos().getIdUnidad(), logueo.getRolActivo());
        }
        String result = "{"
                + "\"contenidoTitulo\":\"" + "Unidad " + informacion.getSilabos().getIdUnidad() + "\","
                + "\"contenidoDinamico\":\"" + informacion.toHTML() + "\","
                + "\"tipo\":\"" + tipo + "\","
                + "\"contenidoMenuLateral\":\"" + menuLateral + "\","
                + "\"lnkAyuda\":\"" + informacion.getAyuda() + "\""
                + "}";
        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(result);
    } else {
        InformacionUnidadIU informacion = (InformacionUnidadIU) session.getAttribute("InformacionUnidadIU");
        String result = "{"
                + "\"contenidoModal\":\"" + informacion.toHTMLModal() + "\""
                + "}";
        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(result);
    }
%>