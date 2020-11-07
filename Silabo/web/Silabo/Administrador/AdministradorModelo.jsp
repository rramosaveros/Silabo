<%@page import="dda.silabo.roles.iu.RolIU"%>
<%@page import="dda.silabo.reportes.iu.EntidadIU"%>
<%@page import="com.google.gson.Gson"%>
<%@page import="dda.silabo.autentificarse.iu.LoginIU"%>
<%
    dda.silabo.sw.SilaboSW_Service service = new dda.silabo.sw.SilaboSW_Service();
    dda.silabo.sw.SilaboSW port = service.getSilaboSWPort();
    LoginIU logueo = (LoginIU) session.getAttribute("logueo");
    Gson G = new Gson();
    if (logueo == null) {
        response.sendRedirect("../../login/loginControlador.jsp");
    } else {
        String tipo = (String) session.getAttribute("tipo");
        if (tipo.equals("Recursos")) {
            response.sendRedirect("RecursosGestion/RecursosGestionControlador.jsp?opcion=getRecursos");
        }
        if (tipo.equals("Estrategias")) {
            response.sendRedirect("EstrategiasMetodologicasGestion/EstrategiasGestionControlador.jsp?opcion=getEstrategias");

        }
        if (tipo.equals("Autonomas") || (tipo.equals("Aula"))) {
            response.sendRedirect("ActividadesAprendizajeGestion/ActividadesGestionControlador.jsp?opcion=getActividades&tipo=" + tipo);
        }

        if (tipo.equals("Real") || tipo.equals("Virtual") || tipo.equals("Aulico")) {
            response.sendRedirect("EscenariosAprendizajeGestion/EscenariosGestionControlador.jsp?opcion=getEscenarios");
        }
        if (tipo.equals("opciones")) {
            response.sendRedirect("OpcionesGestion/OpcionesControlador.jsp?opcion=getOpciones");
        }
        if (tipo.equals("roles")) {
            response.sendRedirect("RolesGestion/RolesGestionControlador.jsp?opcion=getRoles");
        }
        if (tipo.equals("usuarios")) {
            response.sendRedirect("UsuariosGestion/UsuariosGestionControlador.jsp?opcion=getUsuarios");
        }
        if (tipo.equals("asociarRoles")) {
            response.sendRedirect("RolesUsuariosOpcionesGestion/RolesUsuariosOpcionesControlador.jsp?opcion=getUsuariosOpciones");
        }
        if (tipo.equals("getMenuAdministrador")) {
            String jsonEntidad = port.entidadTrabajoRol(G.toJson(logueo));
            EntidadIU entidadIU = G.fromJson(jsonEntidad, EntidadIU.class);
            session.setAttribute("EntidadAdministrador", entidadIU);
            String jsonRol = port.rolOpcionesCargar("{'idRol':" + logueo.getIdRolActivo() + "}");
            RolIU rolIU = G.fromJson(jsonRol, RolIU.class);
            session.setAttribute("RolIU", rolIU);
            response.sendRedirect("AdministradorControlador.jsp?tipo=show");

        }
    }

%>

