<%@page import="dda.silabo.menulateral.ContenidoInicioIU"%>
<%@page import="dda.silabo.reportes.iu.EntidadIU"%>
<%@page import="dda.silabo.datosdocentes.iu.RolUsuarioIU"%>
<%@page import="dda.silabo.roles.iu.RolIU"%>
<%@page import="dda.silabo.menulateral.MenuLateralAdministradorIU"%>
<%@page import="dda.silabo.autentificarse.iu.LoginIU"%>
<%@page import="dda.silabo.menulateral.MenuLateralIU"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    LoginIU logueo = (LoginIU) session.getAttribute("logueo");
    String tipo = "";
    if (logueo != null) {
        tipo = "html";
    } else {
        tipo = "login/loginControlador.jsp";
    }
    EntidadIU entidadIU = (EntidadIU) session.getAttribute("EntidadAdministrador");
    String menu = "";
    MenuLateralAdministradorIU menuLateral = new MenuLateralAdministradorIU();
    RolIU rolIU = (RolIU) session.getAttribute("RolIU");
    menu = menuLateral.toHTMLAdministrador(rolIU.getOpciones());
    RolUsuarioIU rolIu = new RolUsuarioIU();
    String menuTipo = rolIu.toHTMLMenuTipo(logueo.getRoles(), logueo.getRolActivo(), rolIU.getOpciones());

    String result = "{"
            + "\"tipo\":\"" + tipo + "\","
            + "\"contenidoTitulo\":\"" + entidadIU.getNombre() + "\","
//            + "\"nombreDocente\":\"" + logueo.getNombres() + "\","
            + "\"nombreDocente\":\"" + logueo.getNombres().split(" ")[0].trim() +" "+ logueo.getApellidos().split(" ")[0].trim() + "\","
            + "\"contenidoRoles\":\"" + rolIu.toHTMLTrabajo(logueo.getRoles()) + "\","
            + "\"rolActivo\":\"" + rolIu.toHTMLRolActivo(logueo.getRoles(), logueo.getRolActivo()) + "\","
            + "\"menuTipo\":\"" + menuTipo + "\","
            + "\"contenidoMenuLateral\":\"" + menu + "\""
            + "}";
    response.setContentType("text/plain");
    response.getWriter().write(result);
%>
