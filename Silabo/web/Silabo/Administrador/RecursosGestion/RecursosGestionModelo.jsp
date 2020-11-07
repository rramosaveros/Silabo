
<%@page import="dda.silabo.autentificarse.iu.LoginIU"%>
<%@page import="dda.silabo.estructura.unidad.recursos.iu.RecursosGestionIU"%>
<%@page import="com.google.gson.Gson"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    LoginIU logueo = (LoginIU) session.getAttribute("logueo");
    if (logueo == null) {
        response.sendRedirect("../../../login/loginControlador.jsp");
    } else {
        dda.silabo.sw.SilaboSW_Service service = new dda.silabo.sw.SilaboSW_Service();
        dda.silabo.sw.SilaboSW port = service.getSilaboSWPort();
        String opcion = request.getParameter("opcion");

        if (opcion.equals("getRecursos")) {
            String jsonSilabo = (String) session.getAttribute("jsonSilabo");
            String jsonRecursos = port.recursosCargar(jsonSilabo);
            Gson G = new Gson();
            RecursosGestionIU recursos = G.fromJson(jsonRecursos, RecursosGestionIU.class);
            session.setAttribute("Recursos", recursos);
            response.sendRedirect("RecursosGestionControlador.jsp?opcion=show");
        }
        if (opcion.equals("saveRecursos")) {
            String jsonGRecursos = (String) session.getAttribute("jsonGRecursos");
            String jsonRecursos = port.recursosGuardarAdm(jsonGRecursos);
            Gson G = new Gson();
            RecursosGestionIU recursos = G.fromJson(jsonRecursos, RecursosGestionIU.class);
            session.setAttribute("Recursos", recursos);
            response.sendRedirect("RecursosGestionControlador.jsp?opcion=show");
        }
        if (opcion.equals("deleteRecurso")) {
            String jsonRecurso = request.getParameter("jsonRecurso");
            String jsonRecursos = port.recursosEliminarAdm(jsonRecurso);
            Gson G = new Gson();
            RecursosGestionIU recursos = G.fromJson(jsonRecursos, RecursosGestionIU.class);
            session.setAttribute("Recursos", recursos);
            response.sendRedirect("RecursosGestionControlador.jsp?opcion=show");
        }
        if (opcion.equals("habilitarRecurso")) {
            String jsonRecurso = request.getParameter("jsonRecurso");
            String jsonRecursos = port.recursoHabilitarAdm(jsonRecurso);
            Gson G = new Gson();
            RecursosGestionIU recursos = G.fromJson(jsonRecursos, RecursosGestionIU.class);
            session.setAttribute("Recursos", recursos);
            response.sendRedirect("RecursosGestionControlador.jsp?opcion=show");
        }
    }

%>
