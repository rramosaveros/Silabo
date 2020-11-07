<%@page import="dda.silabo.reportes.iu.CarrerasIU"%>
<%@page import="dda.silabo.reportes.iu.EntidadIU"%>
<%@page import="dda.silabo.roles.iu.RolIU"%>
<%@page import="dda.silabo.menulateral.MenuLateralAdministradorIU"%>
<%@page import="dda.silabo.menulateral.MenuLateralIU"%>
<%@page import="dda.silabo.roles.iu.RolesIU"%>
<%@page import="dda.silabo.autentificarse.iu.LoginIU"%>
<%
    LoginIU logueo = (LoginIU) session.getAttribute("logueo");
    String tipo = "";
    if (logueo != null) {
        tipo = "html";
    } else {
        tipo = "login/loginControlador.jsp";
    }
    String view = request.getParameter("view");
    String menu = "";
    if (view.equals("roles")) {
        RolesIU roles = (RolesIU) session.getAttribute("Roles");
        String result = "{"
                + "\"contenidoTitulo\":\"" + "" + roles.getTitulo() + "\","
                + "\"contenidoDinamico\":\"" + roles.toHTML() + "\","
                + "\"tipo\":\"" + tipo + "\","
                + "\"contenidoMenuLateral\":\"" + menu + "\""
                //+ "\"lnkAyuda\":\"" + opciones.getAyuda() + "\""
                + "}";

        response.setContentType("text/plain");
        response.getWriter().write(result);
    }
    if (view.equals("modalUsuariosRol")) {
        EntidadIU rolIU = (EntidadIU) session.getAttribute("EntidadIU");
        String result = "{"
                + "\"modalRolesUsuario\":\"" + rolIU.modalRolUsuarios() + "\""
                + "}";

        response.setContentType("text/plain");
        response.getWriter().write(result);
    }
    if (view.equals("saverolUsuarios")) {
        String result = (String) session.getAttribute("ResultRol");
        String resultado = "{"
                + "\"saveRolesUsuario\":\"" + result + "\""
                + "}";
        response.setContentType("text/plain");
        response.getWriter().write(resultado);
    }
    if (view.equals("saveRolOpciones")) {
        String result = (String) session.getAttribute("ResultRol");
        String resultado = "{"
                + "\"saveRolOpciones\":\"" + result + "\""
                + "}";
        response.setContentType("text/plain");
        response.getWriter().write(resultado);
    }
    if (view.equals("contenidoDocentes")) {
        CarrerasIU carrerasIU = (CarrerasIU) session.getAttribute("CarrerasIU");
        String result = "{"
                + "\"contenidoDocentes\":\"" + carrerasIU.contenidoDocentes() + "\""
                + "}";

        response.setContentType("text/plain");
        response.getWriter().write(result);
    }
    if (view.equals("nivelInferior")) {
        EntidadIU entidadIU = (EntidadIU) session.getAttribute("EntidadIU");
        String result = "{"
                + "\"contenidoNivel\":\"" + entidadIU.contenidoNivel() + "\""
                + "}";

        response.setContentType("text/plain");
        response.getWriter().write(result);
    }

%>

