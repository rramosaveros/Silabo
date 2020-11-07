
<%@page import="dda.silabo.roles.iu.RolIU"%>
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
        if (opcion.equals("getOpciones")) {
            String josnRol = "{'idRol':0}";
            String jsonopciones = port.opcionesCargar(josnRol);
            OpcionesIU opcionesiu = G.fromJson(jsonopciones, OpcionesIU.class);
            session.setAttribute("Opciones", opcionesiu);
            response.sendRedirect("OpcionesControlador.jsp?opcion=show&accion=view");
        }
        if (opcion.equals("saveOpciones")) {

            String jsonOpciones = (String) session.getAttribute("jsonOpciones");
            String result = port.opcionesGuardar(jsonOpciones);
            OpcionesIU opcionesiu = G.fromJson(result, OpcionesIU.class);
            session.setAttribute("Opciones", opcionesiu);
            response.sendRedirect("OpcionesControlador.jsp?opcion=show&accion=view");
        }

        if (opcion.equals("deleteOpcion")) {
            String jsonOpcion = request.getParameter("jsonOpcion");
            String result = port.opcionEliminar(jsonOpcion);
            OpcionesIU opcionesiu = G.fromJson(result, OpcionesIU.class);
            session.setAttribute("Opciones", opcionesiu);
            response.sendRedirect("OpcionesControlador.jsp?opcion=show&accion=view");
        }
        if (opcion.equals("habilitarOpcion")) {
            String jsonOpcion = request.getParameter("jsonOpcion");
            String result = port.opcionHabilitar(jsonOpcion);
            OpcionesIU opcionesiu = G.fromJson(result, OpcionesIU.class);
            session.setAttribute("Opciones", opcionesiu);
            response.sendRedirect("OpcionesControlador.jsp?opcion=show&accion=view");
        }
        if (opcion.equals("rolOpciones")) {
            String json = request.getParameter("jsonRol");
            String jsonRol = port.rolOpcionesCargar(json);
            RolIU rolIU = G.fromJson(jsonRol, RolIU.class);
            session.setAttribute("RolIU", rolIU);

            response.sendRedirect("OpcionesControlador.jsp?opcion=show&accion=modalRolOpciones");
        }
    }
%>   