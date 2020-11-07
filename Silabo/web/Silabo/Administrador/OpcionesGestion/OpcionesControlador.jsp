<%@page import="dda.silabo.autentificarse.iu.LoginIU"%>
<%
    LoginIU logueo = (LoginIU) session.getAttribute("logueo");
    if (logueo == null) {
        response.sendRedirect("../../../login/loginControlador.jsp");
    } else {
        String opcion = request.getParameter("opcion");
        String jsonSilabo = (String) session.getAttribute("jsonSilabo");

        if (opcion != null) {
            if (opcion.equals("getOpciones") && jsonSilabo != null) {
                response.sendRedirect("OpcionesModelo.jsp?opcion=getOpciones");
            }
            if (opcion.equals("show")) {
                String accion = request.getParameter("accion");
                response.sendRedirect("OpcionesVista.jsp?accion=" + accion);
            }
            if (opcion.equals("saveOpciones")) {
                String jsonOpciones = request.getParameter("jsonOpciones");
                session.setAttribute("jsonOpciones", jsonOpciones);
                if (jsonOpciones != null) {
                    response.sendRedirect("OpcionesModelo.jsp?opcion=saveOpciones");
                }
            }
            if (opcion.equals("deleteOpcion")) {

                String jsonOpcion = request.getParameter("jsonOpcion");
                if (jsonOpcion != null) {
                    response.sendRedirect("OpcionesModelo.jsp?jsonOpcion=" + jsonOpcion + "&opcion=deleteOpcion");
                }
            }
            if (opcion.equals("habilitarOpcion")) {
                String jsonOpcion = request.getParameter("jsonOpcion");
                if (jsonOpcion != null) {
                    response.sendRedirect("OpcionesModelo.jsp?jsonOpcion=" + jsonOpcion + "&opcion=habilitarOpcion");
                }
            }
            if (opcion.equals("rolOpciones")) {
                String jsonRol = request.getParameter("jsonRol");
                response.sendRedirect("OpcionesModelo.jsp?opcion=rolOpciones&jsonRol=" + jsonRol);
            }
        }
    }
%>

