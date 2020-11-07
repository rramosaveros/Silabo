<%@page import="dda.silabo.autentificarse.iu.LoginIU"%>
<%
    LoginIU logueo = (LoginIU) session.getAttribute("logueo");
    if (logueo == null) {
        response.sendRedirect("../../../login/loginControlador.jsp");
    } else {
        String opcion = request.getParameter("opcion");
        String jsonSilabo = (String) session.getAttribute("jsonSilabo");
        String jsonAsignaturaInfo = (String) session.getAttribute("jsonAsignaturaInfo");
        if (opcion != null) {
            if (opcion.equals("getInformacion")) {
                response.sendRedirect("InformacionUnidadModelo.jsp?opcion=getInformacion");
            } else if (opcion.equals("show")) {
                String accion = request.getParameter("accion");
                response.sendRedirect("InformacionUnidadVista.jsp?accion=" + accion);
            } else if (opcion.equals("saveEstructura")) {
                String jsonEstructura = request.getParameter("jsonEstructura");
                session.setAttribute("jsonEstructura", jsonEstructura);
                response.sendRedirect("InformacionUnidadModelo.jsp?opcion=saveEstructura");
            } else if (opcion.equals("deleteTema")) {
                String jsonEstructura = request.getParameter("jsonEstructura");
                response.sendRedirect("InformacionUnidadModelo.jsp?opcion=deleteTema&jsonEstructura=" + jsonEstructura);
            } else if (opcion.equals("deleteSubtema")) {
                String jsonEstructura = request.getParameter("jsonEstructura");
                response.sendRedirect("InformacionUnidadModelo.jsp?opcion=deleteSubtema&jsonEstructura=" + jsonEstructura);
            } else if (opcion.equals("gestionarUnidades")) {
                response.sendRedirect("InformacionUnidadModelo.jsp?opcion=gestionarUnidades");
            } else if (opcion.equals("reestablecer")) {
                String json = request.getParameter("json");
                response.sendRedirect("InformacionUnidadModelo.jsp?opcion=reestablecer&json=" + json);
            }
        }
    }
%>