<%@page import="com.google.gson.Gson"%>
<%@page import="dda.silabo.reportes.comunes.ReporteComun"%>
<%@page import="dda.silabo.reportes.iu.EntidadIU"%>
<%
    String opcion = request.getParameter("opcion");
    if (opcion != null) {
        if (opcion.equals("getEntidadPDF")) {
            session.setAttribute("jsonEntidadPDF", request.getParameter("jsonEntidadPDF"));
            response.sendRedirect("EntidadEstadisticaModelo.jsp?opcion=" + opcion);
        } else if (opcion.equals("getEntidadCampoDocentePDF")) {
            session.setAttribute("jsonCampoDocentePDF", request.getParameter("jsonEntidadPDF"));
            response.sendRedirect("EntidadEstadisticaModelo.jsp?opcion=" + opcion);
        } else if (opcion.equals("getReporteEntidadUsuarioPDF")) {
            String jsonReporte = request.getParameter("jsonReporte");
            response.sendRedirect("EntidadEstadisticaModelo.jsp?opcion=" + opcion + "&jsonReporte=" + jsonReporte);
        } else if (opcion.equals("getReporteEstadoSilabosPDF")) {
            response.sendRedirect("EntidadEstadisticaModelo.jsp?opcion=" + opcion);
        } else if (opcion.equals("show")) {
            String accion = request.getParameter("accion");
            response.sendRedirect("EntidadEstadisticaVista.jsp?accion=" + accion);
        } else if (opcion.equals("verificarEstadoSilabo")) {
            String idSilabo = request.getParameter("idSilabo");
            response.sendRedirect("EntidadEstadisticaModelo.jsp?opcion=" + opcion + "&idSilabo=" + idSilabo);
        }
    }
%>