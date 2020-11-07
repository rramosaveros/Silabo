<%@page import="com.google.gson.Gson"%>
<%@page import="dda.silabo.reportes.comunes.ReporteComun"%>
<%@page import="dda.silabo.reportes.iu.EntidadIU"%>
<%
    String opcion = request.getParameter("opcion");
    Gson G = new Gson();
    if (opcion != null) {
        if (opcion.equals("mallaCarrera")) {
            String codCarrera = request.getParameter("codCarrera");
            String codFacultad = request.getParameter("codFacultad");
            session.setAttribute("codCarrera", codCarrera);
            session.setAttribute("codFacultad", codFacultad);
            response.sendRedirect("EstudianteModelo.jsp?opcion=" + opcion);
        }
        if (opcion.equals("show")) {
            String vista = request.getParameter("vista");
            response.sendRedirect("EstudianteVista.jsp?vista=" + vista);
        }
    }
%>