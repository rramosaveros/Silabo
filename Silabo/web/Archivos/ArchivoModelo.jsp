<%@page import="dda.silabo.archivos.comunes.ArchivoPublicacion"%>
<%@page import="javax.swing.JOptionPane"%>
<%@page import="org.apache.commons.fileupload.FileUploadException"%>
<%@page import="org.apache.commons.io.IOUtils"%>
<%@page import="org.apache.commons.fileupload.FileItem"%>
<%@page import="java.util.Iterator"%>
<%@page import="org.apache.commons.fileupload.disk.DiskFileItemFactory"%>
<%@page import="org.apache.commons.fileupload.servlet.ServletFileUpload"%>
<%@page import="com.google.gson.Gson"%>

<%
    try {
        String op = request.getParameter("op");

        if (op.equals("registrarArchivos")) {
            byte[] bytessolicitud = null;
            if (ServletFileUpload.isMultipartContent(request)) {
                ServletFileUpload SFileUpload = new ServletFileUpload(new DiskFileItemFactory());
                Iterator iter = null;

                try {
                    iter = SFileUpload.parseRequest(request).iterator();
                    while (iter.hasNext()) {
                        FileItem FItem = (FileItem) iter.next();
                        if (!FItem.isFormField()) {
                            if (FItem.getFieldName().equals("archivosolicitud")) {
                                bytessolicitud = IOUtils.toByteArray(FItem.getInputStream());
                            }
                        }
                    }
                } catch (FileUploadException e) {
                    out.println(e.toString());
                }
            }

            Gson gson = new Gson();
            String ObjApJSON = request.getParameter("ObjApJSON");
            String jsonSilabo = request.getParameter("jsonSilabo");
            ArchivoPublicacion archivoPublicacion = gson.fromJson(ObjApJSON, ArchivoPublicacion.class);
            archivoPublicacion.setBtyArchivo(bytessolicitud);
            dda.silabo.sw.SilaboSW_Service service = new dda.silabo.sw.SilaboSW_Service();
            dda.silabo.sw.SilaboSW port = service.getSilaboSWPort();
            Integer resultIAE = port.subirArchivos(gson.toJson(archivoPublicacion), jsonSilabo);

            session.setAttribute("resultIAE", resultIAE);
            response.sendRedirect("ArchivoControlador.jsp?op=subirArchivo");
        }

    } catch (Exception ex) {
        // TODO handle custom exceptions here
    }
%>      