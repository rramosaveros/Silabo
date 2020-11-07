<%@page import="java.util.Date"%>
<%@page import="java.util.TimeZone"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import='dda.silabo.opciones.comunes.Opcion'%>
<%@page import='dda.silabo.roles.iu.RolIU'%>
<%@page import='dda.silabo.autentificarse.iu.LoginIU'%>
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
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.5/js/bootstrap.min.js" crossorigin="anonymous"></script>
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

        <header class='dda-titulo'> 
            <!--CONTENIDO HEADER-->
            <nav class='nav navbar navbar-dark bg-inverse navbar-top navbar-fixed-top' role='navigation'>
                <!-- sello ESPOCH -->
                <a class='navbar-brand navbar-dark' href='http://www.espoch.edu.ec' target='blank'>
                    <div class='d-inline-block sello_espoch_image'></div>
                </a>
                <!-- logo APP -->
                <div class='navbar-brand navbar-dark'>
                    <img src='images/sello_app.png' class='d-inline-block' alt='SDA'>
                    <img src='images/sello_app_texto.png' class='d-inline-block' alt='DESARROLLO ACADÉMICO'>
                </div>
                <!-- Role del usuario-->
                <div id='txtRole' class='role'>


                    ....
                </div>

                <ul id='menu' class='nav navbar-nav float-xs-right'>
                    <!-- Alertas -->
                    <li class='nav-item bg-inverse invisible'>
                        <a id='lnkAlertas' href='#' class='nav-link link_menu'>
                            <i class='fa fa-bell alertaIcono'></i>
                            <div class='alertaNumero'>4</div>
                        </a>
                    </li>
                    <!-- opciones VERTICALES -->
                    <li class='nav-item dropdown bg-inverse'>
                        <a id='menuVertical' class='nav-link fa fa-chevron-down float-xs-right' data-toggle='dropdown' aria-haspopup='true' aria-expanded='true'>
                            <img src='images/dda_user.png' class='dda-user float-xs-left' alt='usuario'/>
                            <span class='name float-xs-left mr-1' id='nombreDocente'>Cargando...</span>&nbsp;
                        </a>
                        <ul class='dropdown-menu dropdown-menu-right bg-inverse' id='contenidoRoles' aria-labelledby='menuVerticalItems'>
                            <!--Contenido Roles-->
                        </ul>
                    </li>
                </ul>
            </nav>
        </header>

        <main>
            <!--CONTENIDO PRINCIPAL-->
            <div id='mainContainer' class='container'>
                <!-- Encabezado -->
                <div id='contenidoEncabezadoAsignatura' class='row'>
                    <!-- BOTON menu -->
                    <div id='contenidoIcono' class='col-xs-1 p-0' >
                        <a href='aplicaciones.html'>
                            <div class='d-inline-block icono_menu'></div>
                        </a>
                    </div>
                    <div class='col-xs-9 contenidoTitulo' >
                        <p id='titulo' class='text-small-caps'>
                            Cargando...
                        </p>
                    </div>
                    <div id='contenidoEncabezadoDetalle' class='col-xs-3 p-0'>
                        <p id='subtitulo' class='text-small-caps'>
                            Cargando...
                        </p>
                    </div>
                </div>    

                <div id='brrNavegacion' class='row'>

                    <br>
                </div>

                <div id='contenido' class='row'>
                    <div id='menuLateral' class='col-xs-3'>
                        <div id='menuTipo'>

                        </div>
                        <div id='menuLateralScrollbar'>

                            <!-- Menú Lateral-->
                            <div id='contenidoMenuLateral'>
                                <!--Contenido Menu Sistema--> 
                            </div>
                        </div>
                    </div>
                    <div id='contenidoPrincipal' class='col-xs-8'>
                        <!-- Contenido Principal -->
                        <div class='row dda-row'>
                            <div id='contenidoTitulo' class='col-xs-8'>

                            </div>
                            <div id='contenidoInfo' class='col-xs-4 text-white'>
                                <!--&nbsp;&nbsp;|&nbsp;&nbsp;<a id='lnkAyuda' data-toggle='popover' class='nav-link text-white fa fa-question' tabindex='0' role='button' data-trigger='focus' data-placement='left'></a>-->
                            </div>
                        </div>
                        <div class='row'>
                            <div id='contenidoDinamicoScrollbar' class='col-xs-12'>
                                <div class="mt-1" id="contenidoDinamico"> 
                                    <textarea cols="40" id="todolist" rows="10" class="form-control todolist">
                                    </textarea>
                                    <button type="button" onclick="agregar();" class="btn btn-info"><i class="fa fa-list"></i></button>
                                    <button type="button" onclick="identar();" class ="btn btn-info"><i class="fa fa-indent"></i></button>
                                    <button type="button" onclick="desidentar();" class ="btn btn-info"><i class="fa fa-outdent"></i></button>


                                </div>
                            </div>
                        </div>
                    </div>
                    <div class='row'>
                        <div id='contenidoPie' class='col-xs-12'>
                            <i class='fa fa-spinner fa-pulse fa-fw fa-1x' style='color: #00417F;' aria-hidden='true'></i>&nbsp;&nbsp;|&nbsp;&nbsp;

                        </div>
                    </div>
                </div>
            </div>
        </div>   

    </main>

    <link href='css/dda.select.css' rel='stylesheet' type='text/css'/>
    <link href='css/dda.css' rel='stylesheet' type='text/css'/>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.13.2/js/bootstrap-select.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.13.2/js/i18n/defaults-es_ES.min.js"></script>
    <script type='text/javascript' src='ejemplos.js'></script>
    <footer class='dda-pie float-xs-bottom'>

    </footer>

    <div id='dda-online' class='dda-internet'></div>
</body>
<!-- DDA JS -->
<div id='referencias'>
    <!-- DDA CSS -->



</div>
<div id="calendarModal" class="modal fade">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">×</span> <span class="sr-only">close</span></button>
                <h4 id="modalTitle" class="modal-title"></h4>
            </div>
            <div id="modalBody" class="modal-body"> </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            </div>
        </div>
    </div>
</div>
<div id='contenidosModal' style='width: 100%;'>
</div>
<div id='contenidosModal2' style='width: 100%;'>

</div>
</html> 