<%@page import="dda.silabo.autentificarse.iu.LoginIU"%>
<%
    LoginIU logueo = (LoginIU) session.getAttribute("logueo");
    if (logueo == null) {
        response.sendRedirect("../../../login/loginControlador.jsp");
    } else {
        String opcion = request.getParameter("opcion");

        if (opcion != null) {
            if (opcion.equals("getActividades")) {
                String tipo = request.getParameter("tipo");
                session.setAttribute("tipoA", tipo);
                response.sendRedirect("ActividadesGestionModelo.jsp?opcion=getActividades");
            }
            if (opcion.equals("show")) {
                response.sendRedirect("ActividadesGestionVista.jsp");
            }
            if (opcion.equals("saveActividades")) {
                String jsonGActividades = request.getParameter("jsonGActividades");
                session.setAttribute("jsonGActividades", jsonGActividades);
                if (jsonGActividades != null) {
                    response.sendRedirect("ActividadesGestionModelo.jsp?opcion=saveActividades");
                } else {
                    response.sendRedirect("../../../indexSilaboControlador.jsp?tipo=mnuAdministrador");
                }
            }
            if (opcion.equals("deleteActividad")) {
                String jsonActividad = request.getParameter("jsonActividad");
                if (jsonActividad != null) {
                    response.sendRedirect("ActividadesGestionModelo.jsp?jsonActividad=" + jsonActividad + "&opcion=deleteActividad");
                } else {
                    response.sendRedirect("../../../indexSilaboControlador.jsp?tipo=mnuAdministrador");
                }
            }
            if (opcion.equals("habilitarActividad")) {
                String jsonActividad = request.getParameter("jsonActividad");
                if (jsonActividad != null) {
                    response.sendRedirect("ActividadesGestionModelo.jsp?jsonActividad=" + jsonActividad + "&opcion=habilitarActividad");
                } else {
                    response.sendRedirect("../../../indexSilaboControlador.jsp?tipo=mnuAdministrador");
                }
            }
        }
    }
%>
