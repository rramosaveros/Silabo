
<%@page import="dda.silabo.reportes.iu.CarrerasIU"%>
<%@page import="dda.silabo.reportes.iu.EntidadIU"%>
<%@page import="dda.silabo.roles.iu.RolIU"%>
<%@page import="dda.silabo.menulateral.MenuLateralAdministradorIU"%>
<%@page import="dda.silabo.roles.iu.RolesIU"%>
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
        if (opcion.equals("getRoles")) {
            String jsonroles = port.rolesCargar(G.toJson(logueo));
            RolesIU rolesiu = G.fromJson(jsonroles, RolesIU.class);
            session.setAttribute("Roles", rolesiu);
            response.sendRedirect("RolesGestionControlador.jsp?opcion=show&view=roles");
        }
        if (opcion.equals("saveRoles")) {
            String jsonRoles = request.getParameter("jsonRoles");
            String result = port.rolesGuardar(jsonRoles);
            RolesIU rolesiu = G.fromJson(result, RolesIU.class);
            MenuLateralAdministradorIU menu = G.fromJson(result, MenuLateralAdministradorIU.class);
            session.setAttribute("Menu", menu);
            session.setAttribute("Roles", rolesiu);
            response.sendRedirect("RolesGestionControlador.jsp?opcion=show&view=save");
        }

        if (opcion.equals("deleteRol")) {
            String jsonRol = request.getParameter("jsonRol");
            String result = port.rolEliminar(jsonRol, G.toJson(logueo));
            RolesIU rolesiu = G.fromJson(result, RolesIU.class);
            session.setAttribute("Roles", rolesiu);
            response.sendRedirect("RolesGestionControlador.jsp?opcion=show&view=roles");
        }
        if (opcion.equals("habilitarRol")) {
            String jsonRol = request.getParameter("jsonRol");
            String result = port.rolHabilitar(jsonRol, G.toJson(logueo));
            RolesIU rolesiu = G.fromJson(result, RolesIU.class);
            session.setAttribute("Roles", rolesiu);
            response.sendRedirect("RolesGestionControlador.jsp?opcion=show&view=roles");
        }

        if (opcion.equals("usuariosRol")) {
            String jsonRol = request.getParameter("jsonRol");
            String jsonUsuariosRol = port.rolEntidadesCargar(jsonRol, G.toJson(logueo));
            EntidadIU entidadIU = G.fromJson(jsonUsuariosRol, EntidadIU.class);
            session.setAttribute("EntidadIU", entidadIU);
            response.sendRedirect("RolesGestionControlador.jsp?opcion=show&view=modalUsuariosRol");
        }

        if (opcion.equals("saverolUsuarios")) {
            String json = request.getParameter("json");
            String result = port.rolUsuariosGuardar(json);
            session.setAttribute("ResultRol", result);
            response.sendRedirect("RolesGestionControlador.jsp?opcion=show&view=saverolUsuarios");
        }
        if (opcion.equals("saveRolOpciones")) {
            String jsonRolOpciones = request.getParameter("jsonRolOpciones");
            String result = port.rolOpcionesGuardar(jsonRolOpciones, G.toJson(logueo));
            session.setAttribute("ResultRol", result);
            response.sendRedirect("RolesGestionControlador.jsp?opcion=show&view=saveRolOpciones");
        }
        if (opcion.equals("rolesDocentes")) {
            String codEntidad = request.getParameter("codEntidad");
            Integer idEntidad = Integer.parseInt(request.getParameter("idEntidad"));
            Integer idRol = Integer.parseInt(request.getParameter("idRol"));
            String result = port.rolUsuariosCargar(codEntidad, idEntidad, idRol);
            CarrerasIU carrerasIU = G.fromJson(result, CarrerasIU.class);
            session.setAttribute("CarrerasIU", carrerasIU);
            response.sendRedirect("RolesGestionControlador.jsp?opcion=show&view=contenidoDocentes");
        }
        if (opcion.equals("nivelInferior")) {
            String codEntidad = request.getParameter("codEntidad");
            String nivel = request.getParameter("nivel");
            Integer idEntidad = Integer.parseInt(request.getParameter("idEntidad"));
            String result = port.rolUsuariosNivelInferiorCargar(codEntidad, idEntidad, G.toJson(logueo), nivel, null, null);
            EntidadIU entidadIU = G.fromJson(result, EntidadIU.class);
            session.setAttribute("EntidadIU", entidadIU);
            response.sendRedirect("RolesGestionControlador.jsp?opcion=show&view=nivelInferior");

        }
    }
%>   