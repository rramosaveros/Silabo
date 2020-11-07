<%@page import="dda.silabo.estructura.unidad.estrategias.iu.EstrategiasMetodologicasIU"%>
<%@page import="dda.silabo.estructura.unidad.estrategias.comunes.EstrategiasMetodologicas"%>
<%@page import="dda.silabo.autentificarse.iu.LoginIU"%>
<%@page import="dda.silabo.estructura.unidad.estrategias.iu.EstrategiasGestionIU"%>
<%@page import="com.google.gson.Gson"%>
<%
//    LoginIU logueo = (LoginIU) session.getAttribute("logueo");
//    if (logueo == null) {
//        response.sendRedirect("../../../login/loginControlador.jsp");
//    } else {
    dda.silabo.sw.SilaboSW_Service service = new dda.silabo.sw.SilaboSW_Service();
    dda.silabo.sw.SilaboSW port = service.getSilaboSWPort();
    String opcion = request.getParameter("opcion");
    if (opcion.equals("getEstrategias")) {
        String jsonSilabo = (String) session.getAttribute("jsonSilabo");
        String jsonEstrategias = port.estrategiasCargar(jsonSilabo);
        Gson G = new Gson();
        EstrategiasMetodologicasIU estrategias = G.fromJson(jsonEstrategias, EstrategiasMetodologicasIU.class);
        session.setAttribute("Estrategias", estrategias);
        response.sendRedirect("EstrategiasGestionControlador.jsp?opcion=show");
    }
    if (opcion.equals("saveEstrategias")) {
        String jsonGEstrategias = (String) session.getAttribute("jsonGEstrategias");
        String jsonEstrategias = port.estrategiasGuardarAdm(jsonGEstrategias);
        Gson G = new Gson();
        EstrategiasMetodologicasIU estrategias = G.fromJson(jsonEstrategias, EstrategiasMetodologicasIU.class);
        session.setAttribute("Estrategias", estrategias);
        response.sendRedirect("EstrategiasGestionControlador.jsp?opcion=show");
    }

    if (opcion.equals("deleteEstrategia")) {
        String jsonEstrategia = request.getParameter("jsonEstrategias");
        String jsonEstrategias = port.estrategiasEliminarAdm(jsonEstrategia);
        Gson G = new Gson();
        EstrategiasGestionIU estrategias = G.fromJson(jsonEstrategias, EstrategiasGestionIU.class);
        session.setAttribute("Estrategias", estrategias);
        response.sendRedirect("EstrategiasGestionControlador.jsp?opcion=show");
    }
    if (opcion.equals("habilitarEstrategia")) {
        String jsonEstrategia = request.getParameter("jsonEstrategias");
        String jsonEstrategias = port.estrategiaHabilitarAdm(jsonEstrategia);
        Gson G = new Gson();
        EstrategiasGestionIU estrategias = G.fromJson(jsonEstrategias, EstrategiasGestionIU.class);
        session.setAttribute("Estrategias", estrategias);
        response.sendRedirect("EstrategiasGestionControlador.jsp?opcion=show");
//        }
    }
%>