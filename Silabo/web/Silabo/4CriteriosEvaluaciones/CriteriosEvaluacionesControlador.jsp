
<%@page import="dda.silabo.autentificarse.iu.LoginIU"%>
<%@page import="dda.silabo.criteriosevaluaciones.iu.CriteriosEvaluacionesIU"%>
<%
    LoginIU logueo = (LoginIU) session.getAttribute("logueo");
    if (logueo == null) {
        response.sendRedirect("../../login/loginControlador.jsp");
    } else {
        String jsonSilabo = (String) session.getAttribute("jsonSilabo");
        String opcion = request.getParameter("opcion");
        if (opcion != null) {
            if (opcion.equals("getEvaluaciones") && jsonSilabo != null) {
                response.sendRedirect("CriteriosEvaluacionesModelo.jsp?opcion=getEvaluaciones");
            }
            if (opcion.equals("show")) {
                String accion = request.getParameter("accion");
                response.sendRedirect("CriteriosEvaluacionesVista.jsp?accion="+accion);
            }
            if (opcion.equals("saveEvaluaciones")) {
                String jsonEvaluaciones = request.getParameter("jsonEvaluaciones");
                session.setAttribute("jsonEvaluaciones", jsonEvaluaciones);
                response.sendRedirect("CriteriosEvaluacionesModelo.jsp?opcion=saveEvaluaciones");
            }
        }
    }
%>
