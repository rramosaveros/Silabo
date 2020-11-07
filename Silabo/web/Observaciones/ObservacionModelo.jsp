<%@page import="java.util.logging.Logger"%>
<%@page import="java.util.logging.Level"%>
<%@page import="java.util.logging.Level"%>
<%@page import="dda.silabo.observaciones.iu.ObservacionesIU"%>
<%@page import="com.google.gson.Gson"%>
<%@page import="dda.silabo.autentificarse.iu.LoginIU"%>
<%
    LoginIU logueo = (LoginIU) session.getAttribute("logueo");
    if (logueo == null) {
        response.sendRedirect("../../login/loginControlador.jsp");
    } else {
        dda.silabo.sw.SilaboSW_Service service = new dda.silabo.sw.SilaboSW_Service();
        dda.silabo.sw.SilaboSW port = service.getSilaboSWPort();
        String opcion = request.getParameter("opcion");
        Gson G = new Gson();
        try {
            if (opcion.equals("getObservaciones")) {
                String jsonSilabo = (String) session.getAttribute("jsonSilabo");
                String jsonEscenarios = port.observacionesSeccionesCargar(jsonSilabo);
                ObservacionesIU observacionesIU = G.fromJson(jsonEscenarios, ObservacionesIU.class);
                session.setAttribute("ObservacionesIU", observacionesIU);
                response.sendRedirect("ObservacionControlador.jsp?opcion=show&accion=view");
            }
        } catch (Exception e) {
            Logger.getLogger("EscenariosModelo.jsp").log(Level.SEVERE, "Silabo/EscenariosAprendizaje/EscenariosModelo.jsp", e.getClass().getName() + "*****" + e.getMessage());
            System.err.println("ERROR: " + e.getClass().getName() + "***" + e.getMessage());
        }
    }
%>
