
<%@page import="dda.silabo.autentificarse.iu.LoginIU"%>
<%
    LoginIU logueo = (LoginIU) session.getAttribute("logueo");
    if (logueo == null) {
        response.sendRedirect("../../login/loginControlador.jsp");
    } else {
        String jsonSilabo = (String) session.getAttribute("jsonSilabo");
        String opcion = request.getParameter("opcion");
        if (opcion != null) {
            if (opcion.equals("getBibliografias") && jsonSilabo != null) {
                response.sendRedirect("BibliografiasModelo.jsp?opcion=" + opcion);
            }
            if (opcion.equals("show")) {
                String accion = request.getParameter("accion");
                response.sendRedirect("BibliografiasVista.jsp?accion=" + accion);
            }
            if (opcion.equals("saveBibliografias")) {
                String jsonBibliografias = request.getParameter("jsonBibliografias");
                session.setAttribute("jsonBibliografias", jsonBibliografias);
                response.sendRedirect("BibliografiasModelo.jsp?opcion=" + opcion);
            }
            if (opcion.equals("deleteBibliografia")) {
                String jsonDBibliografias = request.getParameter("jsonDBibliografias");
                response.sendRedirect("BibliografiasModelo.jsp?opcion=" + opcion + "&jsonDBibliografias=" + jsonDBibliografias);
            }
        }
    }
%>
