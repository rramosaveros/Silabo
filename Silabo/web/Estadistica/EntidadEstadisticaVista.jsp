<%@page import="dda.silabo.reportes.pdf.iu.EstadosPDFIU"%>
<%@page import="dda.silabo.reportes.pdf.iu.ReportePDFIU"%>
<%@page import="dda.silabo.autentificarse.iu.LoginIU"%>
<%@page import="dda.silabo.reportes.iu.EntidadIU"%>
<%@page import="com.google.gson.Gson"%>
<%
    LoginIU logueo = (LoginIU) session.getAttribute("logueo");
    String tipo = "";
    if (logueo != null) {
        tipo = "html";
    } else {
        tipo = "login/loginControlador.jsp";
    }

    String accion = request.getParameter("accion");
    if (accion.equals("getEntidadPDF")) {
        ReportePDFIU reportePDFIU = (ReportePDFIU) session.getAttribute("REPORTEIUPDF");
        String pdf = reportePDFIU.generarEntidadDetallePDF();
        response.setContentType("text/plain");
        response.getWriter().write(pdf);
    } else if (accion.equals("getEntidadCampoDocentePDF")) {
        EstadosPDFIU estadosIU = (EstadosPDFIU) session.getAttribute("ESTADOSPDFIU");
        String pdf = estadosIU.generarCampoDocenteDetallePDF();
        response.setContentType("text/plain");
        response.getWriter().write(pdf);
    } else if (accion.equals("getReporteEntidadUsuarioPDF")) {
        ReportePDFIU reportePDFIU = (ReportePDFIU) session.getAttribute("REPORTEIUPDF");
        String pdf = reportePDFIU.generarCriteriosSilaboPDF();
        response.setContentType("text/plain");
        response.getWriter().write(pdf);
    } else if (accion.equals("getReporteEstadoSilabosPDF")) {
        ReportePDFIU reportePDFIU = (ReportePDFIU) session.getAttribute("REPORTEIUPDF");
        String pdf = reportePDFIU.getReporteEstadoSilabosPDF();
        response.setContentType("text/plain");
        response.getWriter().write(pdf);
    } else if (accion.equals("verificarEstadoSilabo")) {
        String icon = (String) (session.getAttribute("icon"));
        String result = "{"
                + "\"icon\":\"" + icon + "\""
                + "}";

        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(result);
    }
%>
