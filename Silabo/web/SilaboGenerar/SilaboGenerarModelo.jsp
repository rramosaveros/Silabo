<%@page import="dda.silabo.datosdocentes.comunes.DatoDocente"%>
<%@page import="dda.silabo.datosdocentes.iu.DatosDocentesIU"%>
<%@page import="dda.silabo.pdf.SilaboVigente"%>
<%@page import="dda.silabo.archivos.comunes.ArchivoComunes"%>
<%@page import="dda.silabo.pdf.SilaboNoVigente"%>
<%@page import="java.util.logging.Logger"%>
<%@page import="java.util.logging.Level"%>
<%@page import="dda.silabo.autentificarse.iu.LoginIU"%>
<%@page import="com.google.gson.Gson"%>

<%
    LoginIU logueo = (LoginIU) session.getAttribute("logueo");
    if (logueo == null) {
        response.sendRedirect("../login/loginControlador.jsp");
    } else {
        try {
            dda.silabo.sw.SilaboSW_Service service = new dda.silabo.sw.SilaboSW_Service();
            dda.silabo.sw.SilaboSW port = service.getSilaboSWPort();
            String opcion = request.getParameter("opcion");
            Gson G = new Gson();
            if (opcion.equals("generarSilabo")) {
                String jsonDocenteInfo = port.datosDocentesInicioCargar((String) session.getAttribute("jsonAsignaturaInfo"));
                DatosDocentesIU datosDocentesIU = G.fromJson(jsonDocenteInfo, DatosDocentesIU.class); //docentes 

                String jsonSilabo = request.getParameter("jsonSilabo");
                String jsonDatosDocentes = port.datosDocentesCargar(jsonSilabo);
                DatosDocentesIU datosdocentes = G.fromJson(jsonDatosDocentes, DatosDocentesIU.class); //docentes con titulos 
                String pdf = "";

                Integer idSilabo = port.estructuraCurricularSilaboID(jsonSilabo);
                String result = port.silaboGenerarPDF(idSilabo, jsonSilabo);
                byte[] silabo = null;

                ArchivoComunes archivoComunes = G.fromJson(result, ArchivoComunes.class);
                if (archivoComunes.getArchivo() != null) {
                    silabo = archivoComunes.getArchivo();
                }
                if (archivoComunes.getVigencia().equals("vigente")) {
                    SilaboVigente silaboVigente = new SilaboVigente();
                    pdf = silaboVigente.generarPDF(jsonSilabo, silabo, idSilabo, datosdocentes, datosDocentesIU);
                } else {
                    SilaboNoVigente silaboPDFLN = new SilaboNoVigente();
                    pdf = silaboPDFLN.generarPDF(jsonSilabo, silabo, idSilabo, datosdocentes, datosDocentesIU);
                }
//                silabo = archivoComunes.getArchivo();
//                } else {
//                    String docentes = datosDocentesIU.IngenierosNoAutenticados(datosdocentes.getDatosdocentes());
//                    String resultado = "{"
//                            + "\"docentes\":\"" + docentes + "\""
//                            + "}";
//                    pdf = resultado;
//                }
                response.setContentType("text/plain");
                response.getWriter().write(pdf);
            } else if (opcion.equals("subir")) {
                String jsonAsignaturaInfo = (String) session.getAttribute("jsonAsignaturaInfo");
                String estado = port.silaboEstadoCargar(jsonAsignaturaInfo);
                session.setAttribute("estado", estado);
                response.sendRedirect("SilaboGenerarControlador.jsp?opcion=show");
            }
        } catch (Exception e) {
            Logger.getLogger("SilaboGenerarModelo.jsp").log(Level.SEVERE, "SilaboGenerar/SilaboGenerarModelo.jsp", e.getClass().getName() + "*****" + e.getMessage());
            System.err.println("ERROR: " + e.getClass().getName() + "***" + e.getMessage());
        }
    }
%>
