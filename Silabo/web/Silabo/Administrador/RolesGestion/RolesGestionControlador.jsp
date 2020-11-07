<%@page import="dda.silabo.autentificarse.iu.LoginIU"%>
<%
    LoginIU logueo = (LoginIU) session.getAttribute("logueo");
    if (logueo == null) {
        response.sendRedirect("../../../login/loginControlador.jsp");
    } else {
        String opcion = request.getParameter("opcion");
        String jsonSilabo = (String) session.getAttribute("jsonSilabo");

        if (opcion != null) {
            if (opcion.equals("getRoles") && jsonSilabo != null) {
                response.sendRedirect("RolesGestionModelo.jsp?opcion=getRoles");
            }
            if (opcion.equals("show")) {
                String view = request.getParameter("view");
                response.sendRedirect("RolesGestionVista.jsp?view=" + view);
            }
            if (opcion.equals("saveRoles")) {
                String jsonRoles = request.getParameter("jsonRoles");
                if (jsonRoles != null) {
                    response.sendRedirect("RolesGestionModelo.jsp?jsonRoles=" + jsonRoles + "&opcion=saveRoles");
                }
            }
            if (opcion.equals("deleteRol")) {

                String jsonRol = request.getParameter("jsonRol");
                if (jsonRol != null) {
                    response.sendRedirect("RolesGestionModelo.jsp?jsonRol=" + jsonRol + "&opcion=deleteRol");
                }
            }
            if (opcion.equals("habilitarRol")) {
                String jsonRol = request.getParameter("jsonRol");
                if (jsonRol != null) {
                    response.sendRedirect("RolesGestionModelo.jsp?jsonRol=" + jsonRol + "&opcion=habilitarRol");
                }
            }
            if (opcion.equals("usuariosRol")) {
                String jsonRol = request.getParameter("jsonRol");
                if (jsonRol != null) {
                    response.sendRedirect("RolesGestionModelo.jsp?jsonRol=" + jsonRol + "&opcion=usuariosRol");
                }
            }
            if (opcion.equals("saverolUsuarios")) {
                String json = request.getParameter("json");
                if (json != null) {
                    response.sendRedirect("RolesGestionModelo.jsp?json=" + json + "&opcion=saverolUsuarios");
                }
            }
            if (opcion.equals("saveRolOpciones")) {
                String jsonRolOpciones = request.getParameter("jsonRolOpciones");
                if (jsonRolOpciones != null) {
                    response.sendRedirect("RolesGestionModelo.jsp?jsonRolOpciones=" + jsonRolOpciones + "&opcion=saveRolOpciones");
                }
            }
            if (opcion.equals("rolesDocentes")) {
                String codEntidad = request.getParameter("codEntidad");
                String idEntidad = request.getParameter("idEntidad");
                String idRol = request.getParameter("idRol");
                if (codEntidad != null) {
                    response.sendRedirect("RolesGestionModelo.jsp?codEntidad=" + codEntidad + "&opcion=rolesDocentes" + "&idEntidad=" + idEntidad + "&idRol=" + idRol);
                }
            }
            if (opcion.equals("nivelInferior")) {
                String codEntidad = request.getParameter("codEntidad");
                String nivel = request.getParameter("nivel");

                String idEntidad = request.getParameter("idEntidad");
                if (codEntidad != null) {
                    response.sendRedirect("RolesGestionModelo.jsp?codEntidad=" + codEntidad + "&opcion=nivelInferior" + "&idEntidad=" + idEntidad + "&nivel=" + nivel);
                }
            }

        }
    }
%>

