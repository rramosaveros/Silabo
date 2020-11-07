
<%@page import="dda.silabo.autentificarse.iu.LoginIU"%>
<%
    LoginIU logueo = (LoginIU) session.getAttribute("logueo");
    if (logueo == null) {
        response.sendRedirect("../login/loginControlador.jsp");
    } else {
        String tipo = request.getParameter("tipo");
        String codCarrera = (request.getParameter("codCarrera"));
        if (tipo.equals("panel")) {
            String jsonEntidad = request.getParameter("jsonEntidad");
            response.sendRedirect("indexModelo.jsp?tipo=" + tipo + "&jsonEntidad=" + jsonEntidad);
        }
        if (tipo.equals("cambioPanel")) {
            String jsonEntidad = request.getParameter("jsonEntidadPanel");
            String rolActivo = request.getParameter("rolActivo");
            logueo.agregarRolActivo(rolActivo);
            response.sendRedirect("indexModelo.jsp?tipo=panel&jsonEntidad=" + jsonEntidad);
        }
        if (tipo.equals("show")) {
            String jsonEntidad = request.getParameter("jsonEntidad");
            String accion = (request.getParameter("accion"));
            response.sendRedirect("indexVista.jsp?codCarrera=" + codCarrera + "&accion=" + accion + "&jsonEntidad=" + jsonEntidad);
        }
    }

%>

