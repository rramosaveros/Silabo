<%
    String jsonSilabo = (String) session.getAttribute("jsonSilabo");
    String opcion = request.getParameter("opcion");
    if (opcion != null) {
        if (opcion.equals("getRecursos") && jsonSilabo != null) {
            response.sendRedirect("RecursosModelo.jsp?opcion=getRecursos");
        }
        if (opcion.equals("show")) {
            String accion = request.getParameter("accion");
            response.sendRedirect("RecursosVista.jsp?accion=" + accion);
        }
        if (opcion.equals("saveRecursos")) {
            String jsonR = request.getParameter("jsonR");
            response.sendRedirect("RecursosModelo.jsp?opcion=saveRecursos&jsonR=" + jsonR);
        }
    } else {
        response.sendRedirect("../../index.jsp");
    }
%>
