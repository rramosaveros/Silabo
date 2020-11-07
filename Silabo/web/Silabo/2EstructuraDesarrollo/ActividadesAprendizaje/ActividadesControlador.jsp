<%@page import="dda.silabo.autentificarse.iu.LoginIU"%>
<%
//    LoginIU logueo = (LoginIU) session.getAttribute("logueo");
//    if (logueo == null) {
//        response.sendRedirect("../../../login/loginControlador.jsp");
//    } else {
    String jsonSilabo = (String) session.getAttribute("jsonSilabo");
    String opcion = request.getParameter("opcion");
    if (opcion != null) {
        if (opcion.equals("getActividades") && jsonSilabo != null) {
            String tipo = request.getParameter("tipo");
            session.setAttribute("tipoDA", tipo);
            response.sendRedirect("ActividadesModelo.jsp?opcion=getActividades");
        } else if (opcion.equals("show")) {
            String accion = request.getParameter("accion");
            response.sendRedirect("ActividadesVista.jsp?accion=" + accion);
        } else if (opcion.equals("saveActividades")) {
            String jsonActividades = request.getParameter("jsonActividades");
            session.setAttribute("jsonActividades", jsonActividades);
            response.sendRedirect("ActividadesModelo.jsp?opcion=saveActividades");
        } else if (opcion.equals("deleteActividadUsuario")) {
            String jsonActividades = request.getParameter("jsonActividades");
            response.sendRedirect("ActividadesModelo.jsp?jsonActividades=" + jsonActividades + "&opcion=deleteActividadUsuario");
        }
//        }
    }
%>