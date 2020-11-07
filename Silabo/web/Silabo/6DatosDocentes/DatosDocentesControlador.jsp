<%@page import="dda.silabo.autentificarse.iu.LoginIU"%>
<%
    LoginIU logueo = (LoginIU) session.getAttribute("logueo");
    if (logueo == null) {
        response.sendRedirect("../../login/loginControlador.jsp");
    } else {
        try {
            String opcion = request.getParameter("opcion");
            if (opcion != null) {
                if (opcion.equals("getDatosDocente")) {
                    response.sendRedirect("DatosDocentesModelo.jsp?opcion=" + opcion);
                } else if (opcion.equals("show")) {
                    response.sendRedirect("DatosDocentesVista.jsp");
                } else if (opcion.equals("showInicio")) {
                    response.sendRedirect("DatosDocentesInicioVista.jsp");
                } else if (opcion.equals("getDocenteInicio")) {
                    session.setAttribute("codFacultad", request.getParameter("codFacultad"));
                    session.setAttribute("codCarrera", request.getParameter("codCarrera"));
                    session.setAttribute("codMateria", request.getParameter("codMateria"));
                    String rolActivo = request.getParameter("rolActivo");
                    if (!logueo.getRolActivo().equals(rolActivo)) {
                        logueo.agregarRolActivo(rolActivo);
                    }
                    response.sendRedirect("DatosDocentesModelo.jsp?opcion=" + opcion);
                } else if (opcion.equals("cambioRolUsuario")) {
                    String rolActivo = request.getParameter("rolActivo");
                    logueo.agregarRolActivo(rolActivo);
                    session.setAttribute("codFacultad", request.getParameter("codFacultad"));
                    session.setAttribute("codCarrera", request.getParameter("codCarrera"));
                    session.setAttribute("codMateria", request.getParameter("codMateria"));
                    response.sendRedirect("DatosDocentesModelo.jsp?opcion=getDocenteInicio");
                } else if (opcion.equals("updateDocente")) {
                    String jsonDocente = request.getParameter("jsonDocente");
                    session.setAttribute("jsonDocente", jsonDocente);
                    response.sendRedirect("DatosDocentesModelo.jsp?opcion=updateDocente");
                }
            }
        } catch (Exception e) {
        }
    }
%>
