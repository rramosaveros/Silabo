<%@page import="dda.silabo.autentificarse.iu.LoginIU"%>
<%
    LoginIU logueo = (LoginIU) session.getAttribute("logueo");
    if (logueo == null) {
        response.sendRedirect("../../../login/loginControlador.jsp");
    } else {
        String opcion = request.getParameter("opcion");
        String jsonSilabo = (String) session.getAttribute("jsonSilabo");

        if (opcion != null) {
            if (opcion.equals("getEscenarios") && jsonSilabo != null) {
                response.sendRedirect("EscenariosGestionModelo.jsp?opcion=getEscenarios");
            }
            if (opcion.equals("show")) {
                response.sendRedirect("EscenariosGestionVista.jsp");
            }
            if (opcion.equals("saveEscenarios")) {
                String jsonGEscenarios = request.getParameter("jsonGEscenarios");
                session.setAttribute("jsonGEscenarios", jsonGEscenarios);
                if (jsonGEscenarios != null) {
                    response.sendRedirect("EscenariosGestionModelo.jsp?opcion=saveEscenarios");
                } else {
                    response.sendRedirect("../../../indexSilaboControlador.jsp?tipo=mnuAdministrador");
                }
            }
            if (opcion.equals("deleteEscenario")) {

                String jsonEscenario = request.getParameter("jsonEscenario");
                if (jsonEscenario != null) {
                    response.sendRedirect("EscenariosGestionModelo.jsp?jsonEscenario=" + jsonEscenario + "&opcion=deleteEscenario");
                } else {
                    response.sendRedirect("../../../indexSilaboControlador.jsp?tipo=mnuAdministrador");
                }
            }
            if (opcion.equals("habilitarEscenario")) {

                String jsonEscenario = request.getParameter("jsonEscenario");
                if (jsonEscenario != null) {
                    response.sendRedirect("EscenariosGestionModelo.jsp?jsonEscenario=" + jsonEscenario + "&opcion=habilitarEscenario");
                } else {
                    response.sendRedirect("../../../indexSilaboControlador.jsp?tipo=mnuAdministrador");
                }
            }
        }
    }
%>
