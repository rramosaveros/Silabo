<%@page import="dda.silabo.reportes.pdf.iu.EstadosPDFIU"%>
<%@page import="dda.silabo.reportes.pdf.iu.ReportePDFIU"%>
<%@page import="dda.silabo.autentificarse.iu.LoginIU"%>
<%@page import="com.google.gson.Gson"%>
<%
    dda.silabo.sw.SilaboSW_Service service = new dda.silabo.sw.SilaboSW_Service();
    dda.silabo.sw.SilaboSW port = service.getSilaboSWPort();
    String opcion = request.getParameter("opcion");
    LoginIU logueo = (LoginIU) session.getAttribute("logueo");
    Gson G = new Gson();
    if (opcion.equals("getEntidadPDF")) {
        String jsonEntidad = (String) session.getAttribute("jsonEntidadPDF");
        String jsonEntidadUsuario = port.reporteUnidadesAcademicasUsuario(jsonEntidad, G.toJson(logueo));
        ReportePDFIU reportePDFIU = G.fromJson(jsonEntidadUsuario, ReportePDFIU.class);
        session.setAttribute("REPORTEIUPDF", reportePDFIU);
        response.sendRedirect("EntidadEstadisticaControlador.jsp?opcion=show&accion=getEntidadPDF");

    } else if (opcion.equals("getEntidadCampoDocentePDF")) {
        String jsonCampoDocentePDF = (String) session.getAttribute("jsonCampoDocentePDF");
        String jsonEntidadCampoDocente = port.reporteCampoFormacionDocente(jsonCampoDocentePDF, G.toJson(logueo));
        EstadosPDFIU estadosIU = G.fromJson(jsonEntidadCampoDocente, EstadosPDFIU.class);
        session.setAttribute("ESTADOSPDFIU", estadosIU);
        response.sendRedirect("EntidadEstadisticaControlador.jsp?opcion=show&accion=getEntidadCampoDocentePDF");

    } else if (opcion.equals("getReporteEntidadUsuarioPDF")) {
        String jsonReporte = request.getParameter("jsonReporte");
        String jsonReporteEntidadUsuario = port.reporteCriteriosEntidadUsuario(jsonReporte, G.toJson(logueo));
        ReportePDFIU reportePDFIU = G.fromJson(jsonReporteEntidadUsuario, ReportePDFIU.class);
        session.setAttribute("REPORTEIUPDF", reportePDFIU);
        response.sendRedirect("EntidadEstadisticaControlador.jsp?opcion=show&accion=getReporteEntidadUsuarioPDF");
    } else if (opcion.equals("getReporteEstadoSilabosPDF")) {
        String jsonReporteEntidadUsuario = port.reporteEstadoSilabos(G.toJson(logueo));
        ReportePDFIU reportePDFIU = G.fromJson(jsonReporteEntidadUsuario, ReportePDFIU.class);
        session.setAttribute("REPORTEIUPDF", reportePDFIU);
        response.sendRedirect("EntidadEstadisticaControlador.jsp?opcion=show&accion=getReporteEstadoSilabosPDF");
    } else if (opcion.equals("verificarEstadoSilabo")) {
        Integer idSilabo = Integer.parseInt(request.getParameter("idSilabo"));
        String icon = port.silaboEstadoVerificar(idSilabo, logueo.getRolActivo());
        session.setAttribute("icon", icon);
        response.sendRedirect("EntidadEstadisticaControlador.jsp?opcion=show&accion=verificarEstadoSilabo");
    }

%>  