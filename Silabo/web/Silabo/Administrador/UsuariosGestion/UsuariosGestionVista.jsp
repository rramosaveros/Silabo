<%@page import="dda.silabo.reportes.iu.EntidadIU"%>
<%@page import="dda.silabo.usuarios.iu.UsuarioIU"%>
<%@page import="dda.silabo.usuarios.iu.UsuariosIU"%>
<%@page import="dda.silabo.autentificarse.iu.LoginIU"%>
<%
    LoginIU logueo = (LoginIU) session.getAttribute("logueo");
    String tipo = "";
    if (logueo != null) {
        tipo = "html";
    } else {
        tipo = "login/loginControlador.jsp";
    }
    String result = "";
    String vista = request.getParameter("vista");
    if (vista.equals("get")) {
        UsuariosIU usuarios = (UsuariosIU) session.getAttribute("Usuarios");
        result = "{"
                + "\"contenidoTitulo\":\"" + usuarios.getTitulo() + "\","
                + "\"contenidoDinamico\":\"" + usuarios.toHTML() + "\","
                + "\"tipo\":\"" + tipo + "\""
                //+ "\"lnkAyuda\":\"" + usuarios.getAyuda() + "\""
                + "}";

    } else if (vista.equals("verificar")) {
        UsuarioIU usuario = (UsuarioIU) session.getAttribute("Usuario");
        String resultado = usuario.HTMLInfoUsuario();
        result = "{"
                + "\"ContenidoUsuario\":\"" + resultado + "\""
                + "}";
    } else if (vista.equals("save")) {
        result = (String) session.getAttribute("ResultadoSave");
        result = "{"
                + "\"ResultadoSave\":\"" + result + "\""
                + "}";
    } else if (vista.equals("modalEditar")) {
        UsuarioIU usuario = (UsuarioIU) session.getAttribute("UsuarioV");
        result = "{"
                + "\"modalEditar\":\"" + usuario.modalHTML() + "\""
                + "}";
    } else if (vista.equals("modalUsuarios")) {
        UsuariosIU usuarios = new UsuariosIU();
        result = "{"
                + "\"modalUsuarios\":\"" + usuarios.modalUsuarios() + "\""
                + "}";
    } else if (vista.equals("modalRolesUsuario")) {
        UsuarioIU usuario = (UsuarioIU) session.getAttribute("UsuarioR");
        result = "{"
                + "\"modalRolesUsuario\":\"" + usuario.modalRolesUsuario() + "\""
                + "}";
    } else if (vista.equals("saveRolesUsuario")) {
        result = (String) session.getAttribute("ResultUsuario");
        result = "{"
                + "\"saveRolesUsuario\":\"" + result + "\""
                + "}";
    } else if (vista.equals("contenidoEntidades")) {
        EntidadIU entidadIU = (EntidadIU) session.getAttribute("EntidadIU");
        result = "{"
                + "\"contenidoEntidades\":\"" + entidadIU.contenidoEntidades() + "\""
                + "}";

    } else if (vista.equals("entidadesrol2")) {
        EntidadIU entidadIU = (EntidadIU) session.getAttribute("EntidadIU");
        result = "{"
                + "\"contenidoNivel\":\"" + entidadIU.contenidoNivelEntidades() + "\""
                + "}";

    } else if (vista.equals("nivelInferior")) {
        EntidadIU entidadIU = (EntidadIU) session.getAttribute("EntidadIU");
        result = "{"
                + "\"contenidoEntidades\":\"" + entidadIU.contenidoEntidadesCarreras() + "\""
                + "}";

    }
    response.setContentType("text/plain");
    response.setCharacterEncoding("UTF-8");
    response.getWriter().write(result);
%>

