<%@page import="dda.silabo.estructura.unidad.estrategias.comunes.Estrategia"%>
<%@page import="dda.silabo.estructura.unidad.estrategias.iu.EstrategiasMetodologicasIU"%>
<%@page import="dda.silabo.autentificarse.iu.LoginIU"%>
<%@page import="dda.silabo.menulateral.MenuLateralIU"%>
<%@page import="dda.silabo.observaciones.comunes.Observacion"%>
<%@page import="dda.silabo.observaciones.iu.ObservacionesIU"%>
<%@page import="dda.silabo.estructura.unidad.estrategias.iu.EstrategiasIU"%>
<%
    LoginIU logueo = (LoginIU) session.getAttribute("logueo");
    String tipo = "";
    if (logueo != null) {
        tipo = "html";
    } else {
        tipo = "login/loginControlador.jsp";
    }
    String accion = request.getParameter("accion");
    if (!accion.equals("dibujarLista")) {
        String menuLateral = "";
        EstrategiasMetodologicasIU estrategias = (EstrategiasMetodologicasIU) session.getAttribute("Estrategias");
        if (accion.equals("save")) {
            MenuLateralIU menu = (MenuLateralIU) session.getAttribute("MenuLateral");
            menuLateral = menu.toHTMLDocente(estrategias.getSilabos().getIdUnidad(), logueo.getRolActivo());
        }
        String result = "{"
                + "\"contenidoTitulo\":\"" + estrategias.getTitulo() + "\","
                + "\"contenidoDinamico\":\"" + estrategias.toHTMLDOCENTE() + "\","
                + "\"tipo\":\"" + tipo + "\","
                + "\"contenidoMenuLateral\":\"" + menuLateral + "\","
                + "\"lnkAyuda\":\"" + estrategias.getAyuda() + "\""
                + "}";
        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(result);
    } else {
        String id = (String) session.getAttribute("id");
        EstrategiasMetodologicasIU estrategias = (EstrategiasMetodologicasIU) session.getAttribute("Estrategias");
        EstrategiasIU estrategiasIU = new EstrategiasIU();

        String result = "{"
                + "\"contenidoM\":\"" + estrategiasIU.toHTML(estrategias, Integer.parseInt(id)) + "\""
                + "}";
        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(result);
    }

%>

