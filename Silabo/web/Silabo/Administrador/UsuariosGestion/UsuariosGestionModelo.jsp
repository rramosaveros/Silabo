<%@page import="dda.silabo.roles.comunes.Rol"%>
<%@page import="dda.silabo.reportes.iu.EntidadIU"%>
<%@page import="dda.silabo.usuarios.iu.UsuariosIU"%>
<%@page import="dda.silabo.usuarios.iu.UsuarioIU"%>
<%@page import="dda.silabo.opciones.iu.OpcionesIU"%>
<%@page import="dda.silabo.autentificarse.iu.LoginIU"%>
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
        if (opcion.equals("getUsuarios")) {
            String jsonUsuarios = port.usuariosCargar(G.toJson(logueo));
            UsuariosIU usuariosiu = G.fromJson(jsonUsuarios, UsuariosIU.class);
            session.setAttribute("Usuarios", usuariosiu);
            response.sendRedirect("UsuariosGestionControlador.jsp?opcion=show&vista=get");
        }
        if (opcion.equals("verificarUsuario")) {
            String jsonUsuario = request.getParameter("jsonUsuario");
            String result = port.usuarioVerificar(jsonUsuario);
            UsuarioIU usuario = G.fromJson(result, UsuarioIU.class);
            session.setAttribute("Usuario", usuario);
            response.sendRedirect("UsuariosGestionControlador.jsp?opcion=show&vista=verificar");
        }
        if (opcion.equals("saveUsuarios")) {
            String jsonUsuarios = (String) session.getAttribute("jsonUsuarios");
            String result = port.usuariosGuardar(jsonUsuarios, G.toJson(logueo));
            UsuariosIU usuariosiu = G.fromJson(result, UsuariosIU.class);
            session.setAttribute("Usuarios", usuariosiu);
            response.sendRedirect("UsuariosGestionControlador.jsp?opcion=show&vista=get");
        }
        if (opcion.equals("estadoUsuarios")) {
            String jsonUsuarios = (String) session.getAttribute("jsonUsuarios");
            String result = port.usuarioEstadoActualizar(jsonUsuarios, G.toJson(logueo));
            UsuariosIU usuariosiu = G.fromJson(result, UsuariosIU.class);
            session.setAttribute("Usuarios", usuariosiu);
            response.sendRedirect("UsuariosGestionControlador.jsp?opcion=show&vista=get");
        }
        if (opcion.equals("editarInformacion")) {
            String jsonUsuario = request.getParameter("jsonUsuario");
            String result = port.usuariosEditar(jsonUsuario);
            UsuarioIU usuario = G.fromJson(result, UsuarioIU.class);
            session.setAttribute("UsuarioV", usuario);
            response.sendRedirect("UsuariosGestionControlador.jsp?opcion=show&vista=modalEditar");
        }
        if (opcion.equals("rolesUsuario")) {
            String jsonUsuario = request.getParameter("jsonUsuario");
            String result = port.usuarioRolesCargar(jsonUsuario, G.toJson(logueo));
            UsuarioIU usuario = G.fromJson(result, UsuarioIU.class);
            session.setAttribute("UsuarioR", usuario);
            response.sendRedirect("UsuariosGestionControlador.jsp?opcion=show&vista=modalRolesUsuario");
        }
        if (opcion.equals("saverolesUsuario")) {
            String jsonUsuario = request.getParameter("jsonUsuario");
            String result = port.usuarioRolesGuardar(jsonUsuario);
            session.setAttribute("ResultUsuario", result);
            response.sendRedirect("UsuariosGestionControlador.jsp?opcion=show&vista=saveRolesUsuario");
        }

        if (opcion.equals("agregarNuevoUsuario")) {
            String jsonUsuario = request.getParameter("jsonUsuario");
            String result = port.usuarioNuevoAgregar(jsonUsuario, G.toJson(logueo));
            UsuariosIU usuario = G.fromJson(result, UsuariosIU.class);
            session.setAttribute("Usuarios", usuario);
            response.sendRedirect("UsuariosGestionControlador.jsp?opcion=show&vista=get");
        }
        if (opcion.equals("deleteUsuario")) {
            String jsonUsuario = request.getParameter("jsonUsuario");
            String jsonUsuarios = port.usuarioEliminar(jsonUsuario, G.toJson(logueo));
            UsuariosIU usuariosiu = G.fromJson(jsonUsuarios, UsuariosIU.class);
            session.setAttribute("Usuarios", usuariosiu);
            response.sendRedirect("UsuariosGestionControlador.jsp?opcion=show&vista=get");
        }
        if (opcion.equals("entidadesRol")) {
            Integer idEntidad = Integer.parseInt(request.getParameter("idEntidad"));
            Rol rol = new Rol();
            rol.setIdTipoEntidad(idEntidad);
            String jsonUsuariosRol = port.rolEntidadesCargar(G.toJson(rol), G.toJson(logueo));
            EntidadIU entidadIU = G.fromJson(jsonUsuariosRol, EntidadIU.class);
            session.setAttribute("EntidadIU", entidadIU);
            response.sendRedirect("UsuariosGestionControlador.jsp?opcion=show&vista=contenidoEntidades");
        }
        if (opcion.equals("entidadesrol2")) {
            Integer idEntidad = Integer.parseInt(request.getParameter("idEntidad"));
            Rol rol = new Rol();
            rol.setIdTipoEntidad(idEntidad);
            String jsonUsuariosRol = port.rolEntidadesCargar(G.toJson(rol), G.toJson(logueo));
//            String result = port.rolUsuariosNivelInferiorCargar(codEntidad, idEntidad, G.toJson(logueo), nivel);
            EntidadIU entidadIU = G.fromJson(jsonUsuariosRol, EntidadIU.class);
            session.setAttribute("EntidadIU", entidadIU);
            response.sendRedirect("UsuariosGestionControlador.jsp?opcion=show&vista=entidadesrol2");

        }
        if (opcion.equals("nivelInferior")) {
            String codEntidad = request.getParameter("codEntidad");
            String nivel = request.getParameter("nivel");
            String cedula = request.getParameter("cedula");
            Integer idRol = Integer.parseInt(request.getParameter("idRol"));
            Integer idEntidad = Integer.parseInt(request.getParameter("idEntidad"));
            String result = port.rolUsuariosNivelInferiorCargar(codEntidad, idEntidad, G.toJson(logueo), nivel, idRol, cedula);
            EntidadIU entidadIU = G.fromJson(result, EntidadIU.class);
            session.setAttribute("EntidadIU", entidadIU);
            response.sendRedirect("UsuariosGestionControlador.jsp?opcion=show&vista=nivelInferior");

        }
    }
%>   
