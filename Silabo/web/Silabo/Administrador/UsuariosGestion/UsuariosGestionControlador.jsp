<%@page import="dda.silabo.autentificarse.iu.LoginIU"%>
<%
    LoginIU logueo = (LoginIU) session.getAttribute("logueo");
    if (logueo == null) {
        response.sendRedirect("../../../login/loginControlador.jsp");
    } else {
        String opcion = request.getParameter("opcion");
        String jsonSilabo = (String) session.getAttribute("jsonSilabo");

        if (opcion != null) {
            if (opcion.equals("getUsuarios")) {
                response.sendRedirect("UsuariosGestionModelo.jsp?opcion=getUsuarios");
            }
            if (opcion.equals("show")) {
                String vista = request.getParameter("vista");
                response.sendRedirect("UsuariosGestionVista.jsp?vista=" + vista);
            }
            if (opcion.equals("saveUsuarios")) {
                String jsonUsuarios = request.getParameter("jsonUsuarios");
                if (jsonUsuarios != null) {
                    session.setAttribute("jsonUsuarios", jsonUsuarios);
                    response.sendRedirect("UsuariosGestionModelo.jsp?opcion=saveUsuarios");
                }
            }
            if (opcion.equals("estadoUsuarios")) {
                String jsonUsuarios = request.getParameter("jsonUsuarios");
                if (jsonUsuarios != null) {
                    session.setAttribute("jsonUsuarios", jsonUsuarios);
                    response.sendRedirect("UsuariosGestionModelo.jsp?opcion=estadoUsuarios");
                }
            }
            if (opcion.equals("verificarUsuario")) {
                String jsonUsuario = request.getParameter("jsonUsuario");
                if (jsonUsuario != null) {
                    response.sendRedirect("UsuariosGestionModelo.jsp?jsonUsuario=" + jsonUsuario + "&opcion=verificarUsuario");
                }
            }
            if (opcion.equals("deleteUsuario")) {

                String jsonUsuario = request.getParameter("jsonUsuario");
                if (jsonUsuario != null) {
                    response.sendRedirect("UsuariosGestionModelo.jsp?jsonUsuario=" + jsonUsuario + "&opcion=deleteUsuario");
                }
            }
            if (opcion.equals("editarInformacion")) {

                String jsonUsuario = request.getParameter("jsonUsuario");
                if (jsonUsuario != null) {
                    response.sendRedirect("UsuariosGestionModelo.jsp?jsonUsuario=" + jsonUsuario + "&opcion=editarInformacion");
                }
            }
            if (opcion.equals("habilitarUsuario")) {
                String jsonUsuarios = request.getParameter("jsonUsuarios");
                if (jsonUsuarios != null) {
                    response.sendRedirect("OpcionesModelo.jsp?jsonOpcion=" + jsonUsuarios + "&opcion=habilitarUsuario");
                }
            }
            if (opcion.equals("rolesUsuario")) {
                String jsonUsuarios = request.getParameter("jsonUsuario");
                if (jsonUsuarios != null) {
                    response.sendRedirect("UsuariosGestionModelo.jsp?jsonUsuario=" + jsonUsuarios + "&opcion=rolesUsuario");
                }
            }
            if (opcion.equals("saverolesUsuario")) {
                String jsonUsuarios = request.getParameter("jsonUsuario");
                if (jsonUsuarios != null) {
                    response.sendRedirect("UsuariosGestionModelo.jsp?jsonUsuario=" + jsonUsuarios + "&opcion=saverolesUsuario");
                }
            }
            if (opcion.equals("agregarNuevoUsuario")) {
                String jsonUsuarios = request.getParameter("jsonUsuario");
                if (jsonUsuarios != null) {
                    response.sendRedirect("UsuariosGestionModelo.jsp?jsonUsuario=" + jsonUsuarios + "&opcion=agregarNuevoUsuario");
                }
            }
            if (opcion.equals("entidadesRol")) {
                String idEntidad = request.getParameter("idEntidad");
                if (idEntidad != null) {
                    response.sendRedirect("UsuariosGestionModelo.jsp?opcion=entidadesRol" + "&idEntidad=" + idEntidad);
                }
            }
            if (opcion.equals("entidadesrol2")) {
                String codEntidad = request.getParameter("codEntidad");
                String nivel = request.getParameter("nivel");

                String idEntidad = request.getParameter("idEntidad");
                if (codEntidad != null) {
                    response.sendRedirect("UsuariosGestionModelo.jsp?codEntidad=" + codEntidad + "&opcion=entidadesrol2" + "&idEntidad=" + idEntidad + "&nivel=" + nivel);
                }
            }
            if (opcion.equals("nivelInferior")) {
                String codEntidad = request.getParameter("codEntidad");
                String nivel = request.getParameter("nivel");
                String cedula = request.getParameter("cedula");
                String idRol = request.getParameter("idRol");

                String idEntidad = request.getParameter("idEntidad");
                if (codEntidad != null) {
                    response.sendRedirect("UsuariosGestionModelo.jsp?codEntidad=" + codEntidad + "&opcion=nivelInferior" + "&idEntidad=" + idEntidad + "&nivel=" + nivel + "&cedula=" + cedula + "&idRol=" + idRol);
                }
            }

        }
    }
%>
