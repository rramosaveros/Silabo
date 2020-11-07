<%
    try {
        String op = request.getParameter("op");

        if (op.equals("subirArchivo")) {

            response.sendRedirect("ArchivoVista.jsp?op="+op);
        }

    } catch (Exception ex) {
        // TODO handle custom exceptions here
    }
%>