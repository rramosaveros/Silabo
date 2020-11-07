<%-- 
    Document   : formularioServicios
    Created on : 11/11/2016, 17:45:49
    Author     : Anthonny
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>SELECCIONE UN SERVICIO WEB</h1>

        <form name="Submit" action="index.jsp">
            <h3>SERVICIOS WEB INFOCARRERA</h3>
            <input type="submit" name="opc" value="WS1">
            <h3>SERVICIOS WEB INFOGENERAL</h3>
            <input type="submit" name="opc" value="WS2">
            <h3>SERVICIOS WEB SEGURIDAD</h3>
            <input type="submit" name="opc" value="WS3">
        </form>
        <br>
        <br>
        <h2>DATOS RECIBIDOS DEL SERVICIO WEB</h2>
        <%
            String datos=request.getParameter("datos");
        %>
        <input type="text" name="datos" value=<%=datos%>>

    </body>
</html>
