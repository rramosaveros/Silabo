<%@page import="dda.silabo.autentificarse.iu.LoginIU"%>
<%@page import="dda.silabo.estructura.unidad.actividades.iu.ActividadesGestionIU"%>
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
        if (opcion.equals("getActividades")) {
            String jsonSilabo = (String) session.getAttribute("jsonSilabo");
            String jsonActividades = port.actividadesAprendizajeCargar(jsonSilabo);
            ActividadesGestionIU actividades = G.fromJson(jsonActividades, ActividadesGestionIU.class);
            session.setAttribute("Actividades", actividades);
            response.sendRedirect("ActividadesGestionControlador.jsp?opcion=show");
        }
        if (opcion.equals("saveActividades")) {
            String jsonGActividades = (String) session.getAttribute("jsonGActividades");
            String jsonActiividades = port.actividadesGuardarAdm(jsonGActividades);
            ActividadesGestionIU actividades = G.fromJson(jsonActiividades, ActividadesGestionIU.class);
            session.setAttribute("Actividades", actividades);
            response.sendRedirect("ActividadesGestionControlador.jsp?opcion=show");
        }

        if (opcion.equals("deleteActividad")) {
            String jsonActividad = request.getParameter("jsonActividad");
            String jsonActividades = port.actividadesEliminarAdm(jsonActividad);
            ActividadesGestionIU actividades = G.fromJson(jsonActividades, ActividadesGestionIU.class);
            session.setAttribute("Actividades", actividades);
            response.sendRedirect("ActividadesGestionControlador.jsp?opcion=show");
        }
        if (opcion.equals("habilitarActividad")) {
            String jsonActividad = request.getParameter("jsonActividad");
            String jsonActividades = port.actividadHabilitarAdm(jsonActividad);
            ActividadesGestionIU actividades = G.fromJson(jsonActividades, ActividadesGestionIU.class);
            session.setAttribute("Actividades", actividades);
            response.sendRedirect("ActividadesGestionControlador.jsp?opcion=show");
        }
    }
%> 

