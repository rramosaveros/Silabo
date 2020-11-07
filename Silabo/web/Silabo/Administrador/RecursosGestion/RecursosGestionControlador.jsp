<%@page import="dda.silabo.autentificarse.iu.LoginIU"%>
<%
    LoginIU logueo = (LoginIU) session.getAttribute("logueo");
    if (logueo == null) {
        response.sendRedirect("../../../login/loginControlador.jsp");
    } else {
        String jsonSilabo = (String) session.getAttribute("jsonSilabo");
        String opcion = request.getParameter("opcion");
        if (opcion != null) {
            if (opcion.equals("getRecursos") && jsonSilabo != null) {
                //          String jsonRecursos = request.getParameter("jsonRecursos");
                //          if (jsonSilabo != null && jsonRecursos == null) 
                //{
                response.sendRedirect("RecursosGestionModelo.jsp?opcion=getRecursos");
                //              response.sendRedirect("RecursosGestionVista.jsp?jsonRecursos=" + jsonRecursos);

            }
            if (opcion.equals("show")) {
                response.sendRedirect("RecursosGestionVista.jsp");
            }
            if (opcion.equals("saveRecursos")) {
                String jsonGRecursos = request.getParameter("jsonGRecursos");
                if (jsonGRecursos != null) {
                    session.setAttribute("jsonGRecursos", jsonGRecursos);
                    response.sendRedirect("RecursosGestionModelo.jsp?opcion=saveRecursos");
                }
            }
            if (opcion.equals("deleteRecurso")) {
                String jsonRecurso = request.getParameter("jsonRecurso");
                if (jsonRecurso != null) {
                    response.sendRedirect("RecursosGestionModelo.jsp?jsonRecurso=" + jsonRecurso + "&opcion=deleteRecurso");
                }
            }
            if (opcion.equals("habilitarRecurso")) {
                String jsonRecurso = request.getParameter("jsonRecurso");
                if (jsonRecurso != null) {
                    response.sendRedirect("RecursosGestionModelo.jsp?jsonRecurso=" + jsonRecurso + "&opcion=habilitarRecurso");
                }
            }
        }
    }
%>}
