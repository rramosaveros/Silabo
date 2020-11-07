<%@page import="dda.silabo.roles.iu.RolIU"%>
<%@page import="dda.silabo.reportes.iu.CarrerasIU"%>
<%@page import="dda.silabo.reportes.iu.EntidadIU"%>
<%@page import="dda.silabo.datosdocentes.iu.RolUsuarioIU"%>
<%@page import="dda.silabo.autentificarse.iu.LoginIU"%>
<%@page import="com.google.gson.Gson"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    String vista = request.getParameter("vista");
    Gson G = new Gson();
    LoginIU logueo = (LoginIU) session.getAttribute("logueo");
    if (vista.equals("todos")) {
        String codFacultad = (String) session.getAttribute("codFacultad");
        String codCarrera = (String) session.getAttribute("codCarrera");
        EntidadIU uAcademica = (EntidadIU) session.getAttribute("EntidadesInstitucion");
        RolUsuarioIU rolIu = new RolUsuarioIU();
        String contenidoRolesEst = "", menuTipo = "", rolActivo = "";
        if (logueo.getRolActivo() != "Est") {
            RolIU rolIU = (RolIU) session.getAttribute("RolIU");
            contenidoRolesEst = rolIu.toHTMLPanel(logueo.getRoles());
            menuTipo = rolIu.toHTMLMenuTipo(logueo.getRoles(), logueo.getRolActivo(), rolIU.getOpciones());
            rolActivo = rolIu.toHTMLRolActivo(logueo.getRoles(), logueo.getRolActivo());
        } else {
            menuTipo = rolIu.toHTMLMenuTipoEstudiante();
            contenidoRolesEst = "<li class='dropdown-divider'></li>"
                    + "<li>"
                    + "  <a class='nav-link' onclick='cerrarSession();'>Salir <i class='fa fa-sign-out' aria-hidden='true'></i></a>"
                    + "</li>";
            rolActivo = "Estudiante";
        }

        String result = "{"
                + "\"facultades\":\"" + uAcademica.toHTMLfacultades(codFacultad) + "\","
                + "\"carrerasSelect\":\"" + uAcademica.toHTMLcarrerasF(codFacultad, codCarrera) + "\","
                + "\"menuTipo\":\"" + menuTipo + "\","
                + "\"rolActivo\":\"" + rolActivo + "\","
                + "\"nombreUsuario\":\"" + logueo.getNombres() + "\","
                + "\"contenidoRoles\":\"" + contenidoRolesEst + "\","
                + "\"contenidoTitulo\":\"" + "Estructura Curricular" + "\""
                + "}";
        response.setContentType("text/plain");
        response.getWriter().write(result);
    } else {
        CarrerasIU carrerasIU = (CarrerasIU) session.getAttribute("CarreraIU");
        String resultado = "";
        if (carrerasIU != null) {
            resultado = G.toJson(carrerasIU);
        }
        response.setContentType("text/plain");
        response.getWriter().write(resultado);
    }
%>