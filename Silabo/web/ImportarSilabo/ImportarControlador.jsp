<%
    String opcion = request.getParameter("opcion");
    if (opcion != null) {
        if (opcion.equals("getAsignaturasPeriodos")) {
            response.sendRedirect("ImportarModelo.jsp?opcion=getAsignaturasPeriodos");
        } else if (opcion.equals("show")) {
            String accion = request.getParameter("accion");
            response.sendRedirect("ImportarVista.jsp?accion=" + accion);
        } else if (opcion.equals("saveImportacion")) {

            String unidadAnterior = request.getParameter("unidadAnterior");
            String tipo = request.getParameter("tipo");
            String unidad = request.getParameter("unidad");
            String idTipo = request.getParameter("idTipo");
            String idSilaboActual = request.getParameter("idSilaboActual");
            response.sendRedirect("ImportarModelo.jsp?opcion=saveImportacion&tipo=" + tipo + "&unidad=" + unidad + "&idTipo=" + idTipo + "&idSilaboActual=" + idSilaboActual + "&unidadAnterior=" + unidadAnterior);
        } else {
            String jsonSilabo = request.getParameter("jsonSilabo");
            response.sendRedirect("ImportarModelo.jsp?opcion=" + opcion + "&jsonSilabo=" + jsonSilabo);
        }

    } else {
        response.sendRedirect("../../index.jsp");

    }
%>