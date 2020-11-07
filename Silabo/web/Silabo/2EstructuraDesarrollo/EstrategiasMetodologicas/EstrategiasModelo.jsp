<%@page import="dda.silabo.estructura.unidad.estrategias.iu.EstrategiasMetodologicasIU"%>
<%@page import="java.util.logging.Logger"%>
<%@page import="java.util.logging.Level"%>
<%@page import="java.util.logging.Level"%>
<%@page import="dda.silabo.autentificarse.iu.LoginIU"%>
<%@page import="dda.silabo.menulateral.MenuLateralIU"%>
<%@page import="com.google.gson.Gson"%>
<%@page import="dda.silabo.estructura.unidad.estrategias.iu.EstrategiasIU"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    LoginIU logueo = (LoginIU) session.getAttribute("logueo");
    if (logueo == null) {
        response.sendRedirect("../../../login/loginControlador.jsp");
    } else {
        dda.silabo.sw.SilaboSW_Service service = new dda.silabo.sw.SilaboSW_Service();
        dda.silabo.sw.SilaboSW port = service.getSilaboSWPort();
        String opcion = request.getParameter("opcion");
        Gson G = new Gson();
        try {
            if (opcion.equals("getEstrategias")) {
                String jsonSilabo = (String) session.getAttribute("jsonSilabo");
                String jsonEstrategias = port.estrategiasCargar(jsonSilabo);
                EstrategiasMetodologicasIU estrategias = G.fromJson(jsonEstrategias, EstrategiasMetodologicasIU.class);
                session.setAttribute("Estrategias", estrategias);
                response.sendRedirect("EstrategiasControlador.jsp?opcion=show&accion=view");
            } else if (opcion.equals("saveEstrategias")) {
                String jsonMenuSilabo = "";
                jsonMenuSilabo = (String) session.getAttribute("jsonAsignaturaInfo");
                String jsonEstrategias = request.getParameter("jsonEstrategias");
                String resultado = port.estrategiasGuardar(jsonEstrategias).toString();
//                if (resultado.equals("ok")) {
//                    EstrategiasIU estrategias = G.fromJson(jsonEstrategias, EstrategiasIU.class);
//                    resultado = port.estrategiasCargar(G.toJson(estrategias.getSilabos()));
//                } else {
//                    response.sendError(300, "Error al guardar");
//                }
                EstrategiasMetodologicasIU estrategias = G.fromJson(resultado, EstrategiasMetodologicasIU.class);
                session.setAttribute("Estrategias", estrategias);
                String jsonMenuLateral = port.menuSilaboCargar(jsonMenuSilabo);
                MenuLateralIU menulateral = G.fromJson(jsonMenuLateral, MenuLateralIU.class);
                session.setAttribute("MenuLateral", menulateral);
                response.sendRedirect("EstrategiasControlador.jsp?opcion=show&accion=save");
            }
        } catch (Exception e) {
            Logger.getLogger("EstrategiasModelo.jsp").log(Level.SEVERE, "Silabo/2EstructuraDesarrollo/EstrategiasMetodologicas/EstrategiasModelo.jsp", e.getClass().getName() + "*****" + e.getMessage());
            System.err.println("ERROR: " + e.getClass().getName() + "***" + e.getMessage());
        }
    }
%>
