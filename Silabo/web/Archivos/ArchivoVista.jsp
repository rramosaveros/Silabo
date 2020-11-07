
<%@page import="java.io.PrintWriter"%>
<%
    Integer resultIAE = -1;

    String op = request.getParameter("op");

    if (op.equals("subirArchivo")) {
        resultIAE = (Integer) session.getAttribute("resultIAE");
    }

    String result = "{"
            + "\"resultIAE\":\"" + resultIAE + "\""
            + "}";

    response.setContentType("text/plain");
    response.getWriter().write(result);
%>