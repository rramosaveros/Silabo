<%@page import="java.util.logging.Logger"%>
<%@page import="java.util.logging.Level"%>
<%@page import="dda.silabo.autentificarse.iu.LoginIU"%>
<%@page import="dda.silabo.menulateral.MenuLateralIU"%>
<%@page import="dda.silabo.escenarios.iu.EscenariosIU"%>
<%@page import="com.google.gson.Gson"%>
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
            if (opcion.equals("getEscenarios")) {
                String jsonSilabo = (String) session.getAttribute("jsonSilabo");
                String jsonEscenarios = port.escenariosCargar(jsonSilabo);
                EscenariosIU escenarios = G.fromJson(jsonEscenarios, EscenariosIU.class);
                session.setAttribute("Escenarios", escenarios);
                response.sendRedirect("EscenariosControlador.jsp?opcion=show&accion=view");
            }
            if (opcion.equals("saveEscenarios")) {
                String jsonMenuSilabo = "";
                jsonMenuSilabo = (String) session.getAttribute("jsonAsignaturaInfo");
                String jsonEscenarios = (String) session.getAttribute("jsonEscenarios");
                String resultado = port.escenariosGuardar(jsonEscenarios);
                if (resultado.equals("ok")) {
                    EscenariosIU escenarios = G.fromJson(jsonEscenarios, EscenariosIU.class);
                    resultado = port.escenariosCargar(G.toJson(escenarios.getSilabos()));
                } else {
                    response.sendError(300, "Error al guardar");
                }
                EscenariosIU escenarios = G.fromJson(resultado, EscenariosIU.class);
                session.setAttribute("Escenarios", escenarios);
                String jsonMenuLateral = port.menuSilaboCargar(jsonMenuSilabo);
                MenuLateralIU menulateral = G.fromJson(jsonMenuLateral, MenuLateralIU.class);
                session.setAttribute("MenuLateral", menulateral);
                response.sendRedirect("EscenariosControlador.jsp?opcion=show&accion=save");
            }
        } catch (Exception e) {
            Logger.getLogger("EscenariosModelo.jsp").log(Level.SEVERE, "Silabo/EscenariosAprendizaje/EscenariosModelo.jsp", e.getClass().getName() + "*****" + e.getMessage());
            System.err.println("ERROR: " + e.getClass().getName() + "***" + e.getMessage());
        }
    }
%>
