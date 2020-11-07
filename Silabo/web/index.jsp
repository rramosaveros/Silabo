<%@page import="dda.silabo.opciones.comunes.Opcion"%>
<%@page import="dda.silabo.roles.iu.RolIU"%>
<%@page import="dda.silabo.autentificarse.iu.LoginIU"%>
<!DOCTYPE html>
<%@taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
    <head>
        <title>DDA | Dirección de Desarrollo Académico</title>
        <meta charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta http-equiv="x-ua-compatible" content="ie=edge">
        <!-- jQuery first, then Tether, then Bootstrap JS. -->

        <!-- Bootstrap CSS -->
        <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.5/css/bootstrap.min.css" crossorigin="anonymous" rel="stylesheet" />
        <script src="https://use.fontawesome.com/7c719b02b1.js"></script>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.13.2/css/bootstrap-select.min.css">
        <link rel="shortcut icon" href="favicon.ico?v=2">
        <!-- Bootstrap JS -->
        <script src="https://cdnjs.cloudflare.com/ajax/libs/tether/1.3.7/js/tether.min.js" crossorigin="anonymous"></script>
        <!-- ShieldUI CSS para el MENU LATERAL (treeview)-->
        <link href="//www.shieldui.com/shared/components/latest/css/light-bootstrap/all.min.css" type="text/css" rel="stylesheet" />
        <script src="//www.shieldui.com/shared/components/latest/js/shieldui-all.min.js" type="text/javascript"></script>
        <!--SCRIPT PARA GRAFICOS-->
        <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
        <!--SCRIPT PARA DESCARGAR PDFs-->
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jszip/3.1.4/jszip.min.js"  type="text/javascript"></script>
        <script type="text/javascript" src="https://cdn.jsdelivr.net/g/filesaver.js" ></script>
        <!--SCRIPT PARA ALERTAS -->
        <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/6.10.1/sweetalert2.all.min.js"></script>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/6.10.1/sweetalert2.css">
    </head>
    <body>
        <%
            LoginIU logueo = (LoginIU) session.getAttribute("logueo");
            RolIU rolIU = new RolIU();
            if (logueo == null) {
                response.sendRedirect("login/loginControlador.jsp");
            } else {
                rolIU = (RolIU) session.getAttribute("RolIU");
        %>
        <input type="hidden" id="idSilabo" value="<%=session.getAttribute("idSilabo")%>">
        <input type="hidden" id="RolUsuario" value="<%=logueo.getRolActivo()%>">
        <% }
        %>
        <header class="dda-titulo"> 
            <!--CONTENIDO HEADER-->
        </header>

        <main>
            <!--CONTENIDO PRINCIPAL-->
        </main>

        <footer class="dda-pie float-xs-bottom">

        </footer>

        <div id="dda-online" class="dda-internet"></div>
    </body>
    <!-- DDA JS -->
    <script src="https://cdn.jsdelivr.net/jquery/3.2.1/jquery.min.js" crossorigin="anonymous"></script> 
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.13.2/js/bootstrap-select.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.13.2/js/i18n/defaults-es_ES.min.js"></script>
    <script src="js/dda.js" type="text/javascript"></script>
    <script type="text/javascript" src="js/bootstrap-filestyle.min.js"></script>
    <div id="referencias"></div>
    <div id="contenidosModalInicio" style="width: 100%;">
    </div>
    <div id="contenidosModal" style="width: 100%;">
    </div>
    <div id="contenidosModal2" style="width: 100%;">

    </div>
</html> 