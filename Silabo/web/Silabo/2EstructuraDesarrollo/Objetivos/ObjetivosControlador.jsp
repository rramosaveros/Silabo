
<%@page import="dda.silabo.autentificarse.iu.LoginIU"%>
<%
    LoginIU logueo = (LoginIU) session.getAttribute("logueo");
    if (logueo == null) {
        response.sendRedirect("../../../login/loginControlador.jsp");
    } else {
        String jsonSilabo = (String) session.getAttribute("jsonSilabo");
        String opcion = request.getParameter("opcion");
        if (opcion != null) {
            if (opcion.equals("getObjetivos") && jsonSilabo != null) {
                response.sendRedirect("ObjetivosModelo.jsp?opcion=getObjetivos");
            }
            if (opcion.equals("show")) {
                String accion = request.getParameter("accion");
                response.sendRedirect("ObjetivosVista.jsp?accion=" + accion);
            }
            if (opcion.equals("saveObjetivo")) {
                String jsonObjetivo = request.getParameter("jsonObjetivo");
                session.setAttribute("jsonObjetivo", jsonObjetivo);
                response.sendRedirect("ObjetivosModelo.jsp?opcion=saveObjetivo");
            }

        }
    }
%>
