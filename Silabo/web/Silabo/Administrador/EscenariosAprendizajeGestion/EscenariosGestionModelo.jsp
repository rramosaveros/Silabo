<%@page import="dda.silabo.autentificarse.iu.LoginIU"%>
<%@page import="dda.silabo.escenarios.iu.EscenariosGestionIU"%>
<%@page import="com.google.gson.Gson"%>
<%
    LoginIU logueo = (LoginIU) session.getAttribute("logueo");
    if (logueo == null) {
        response.sendRedirect("../../../login/loginControlador.jsp");
    } else {
        dda.silabo.sw.SilaboSW_Service service = new dda.silabo.sw.SilaboSW_Service();
        dda.silabo.sw.SilaboSW port = service.getSilaboSWPort();
        String opcion = request.getParameter("opcion");
        Gson G = new Gson();
        if (opcion.equals("getEscenarios")) {
            String jsonSilabo = (String) session.getAttribute("jsonSilabo");
            String jsonEscenarios = port.escenariosCargar(jsonSilabo);
            EscenariosGestionIU escenarios = G.fromJson(jsonEscenarios, EscenariosGestionIU.class);
            session.setAttribute("Escenarios", escenarios);
            response.sendRedirect("EscenariosGestionControlador.jsp?opcion=show");
        }
        if (opcion.equals("saveEscenarios")) {
            String jsonGEscenarios = (String) session.getAttribute("jsonGEscenarios");
            String jsonEscenarios = port.escenariosGuardarAdm(jsonGEscenarios);
            EscenariosGestionIU escenarios = G.fromJson(jsonEscenarios, EscenariosGestionIU.class);
            session.setAttribute("Escenarios", escenarios);
            response.sendRedirect("EscenariosGestionControlador.jsp?opcion=show");
        }

        if (opcion.equals("deleteEscenario")) {
            String jsonEscenario = request.getParameter("jsonEscenario");
            String jsonEscenarios = port.escenariosEliminarAdm(jsonEscenario);
            EscenariosGestionIU escenarios = G.fromJson(jsonEscenarios, EscenariosGestionIU.class);
            session.setAttribute("Escenarios", escenarios);
            response.sendRedirect("EscenariosGestionControlador.jsp?opcion=show");
        }
        if (opcion.equals("habilitarEscenario")) {
            String jsonEscenario = request.getParameter("jsonEscenario");
            String jsonEscenarios = port.escenarioHabilitarAdm(jsonEscenario);
            EscenariosGestionIU escenarios = G.fromJson(jsonEscenarios, EscenariosGestionIU.class);
            session.setAttribute("Escenarios", escenarios);
            response.sendRedirect("EscenariosGestionControlador.jsp?opcion=show");
        }
    }
%>