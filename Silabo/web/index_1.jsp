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
        <meta charset=UTF-8'>
        <meta name='viewport' content='width=device-width, initial-scale=1, shrink-to-fit=no'>
        <meta http-equiv='x-ua-compatible' content='ie=edge'>
        <!-- jQuery first, then Tether, then Bootstrap JS. -->
        <!-- Bootstrap CSS -->
        <link href='https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.5/css/bootstrap.min.css' crossorigin='anonymous' rel='stylesheet' />
        <script src='https://use.fontawesome.com/7c719b02b1.js'></script>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.13.2/css/bootstrap-select.min.css">
        <!-- ShieldUI CSS para el MENU LATERAL (treeview)-->
        <link href='//www.shieldui.com/shared/components/latest/css/light-bootstrap/all.min.css' type='text/css' rel='stylesheet' />
        <link href="https://cdnjs.cloudflare.com/ajax/libs/mdbootstrap/4.6.0/css/mdb.min.css" rel="stylesheet">



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
                            <div id='contenidoDinamicoScrollbar' class='col-xs-12 rotateIn rotateOutDownLeft'>
                                <div class="mt-1" id="contenidoDinamico"> 
                                    <i class="fa fa-redo-alt"></i>
                                    <select class="selectpicker" >
                                        <option data-tokens="ff" data-subtext="Rep California">Tom Foolery</option>
                                        <option data-subtext="Sen California">Bill Gordon</option>
                                        <option data-subtext="Sen Massacusetts">Elizabeth Warren</option>
                                        <option data-subtext="Rep Alabama">Mario Flores</option>
                                        <option data-subtext="Rep Alaska">Don Young</option>
                                        <option data-subtext="Rep California" disabled="disabled">Marvin Martinez</option>
                                    </select>
                                     <div class='form-group row'><div class='col-xs-12'><b>Campo amplio de grado académico</b></br><select class='form-control' onchange='updEspecificos(this);' id='selectAmplios'><option selected='true' disabled='disabled'>SELECCIONE UNA OPCIÓN</option><option data-especificos='[{\"codigo\":\"19\",\"descripcion\":\"Tecnologías de la información y la comunicación (TIC)\",\"estado\":\"habilitado\",\"codigoAmplio\":\"13\",\"seleccionado\":\"no\",\"tipoRegistro\":\"\\u0000\",\"listaDetallados\":[]}]' value='13'>Tecnologías de la información y la comunicación (TIC)</option><option data-especificos='[{\"codigo\":\"11\",\"descripcion\":\"Educación\",\"estado\":\"habilitado\",\"codigoAmplio\":\"9\",\"seleccionado\":\"no\",\"tipoRegistro\":\"\\u0000\",\"listaDetallados\":[]}]' value='9'>Educación</option><option data-especificos='[{\"codigo\":\"29\",\"descripcion\":\"Ciencias sociales y del comportamiento\",\"estado\":\"habilitado\",\"codigoAmplio\":\"12\",\"seleccionado\":\"no\",\"tipoRegistro\":\"\\u0000\",\"listaDetallados\":[]},{\"codigo\":\"37\",\"descripcion\":\"Periodismo e información\\n\",\"estado\":\"habilitado\",\"codigoAmplio\":\"12\",\"seleccionado\":\"no\",\"tipoRegistro\":\"\\u0000\",\"listaDetallados\":[]}]' value='12'>Ciencias sociales, periodismo, información y derecho
</option><option data-especificos='[{\"codigo\":\"26\",\"descripcion\":\"PE POO2\",\"estado\":\"habilitado\",\"codigoAmplio\":\"14\",\"seleccionado\":\"no\",\"tipoRegistro\":\"\\u0000\",\"listaDetallados\":[]},{\"codigo\":\"20\",\"descripcion\":\"Educación comercial y administración\\n\",\"estado\":\"habilitado\",\"codigoAmplio\":\"14\",\"seleccionado\":\"no\",\"tipoRegistro\":\"\\u0000\",\"listaDetallados\":[]}]' value='14'>Administración</option><option data-especificos='[{\"codigo\":\"21\",\"descripcion\":\"Ciencias biológicas y afines\",\"estado\":\"habilitado\",\"codigoAmplio\":\"15\",\"seleccionado\":\"no\",\"tipoRegistro\":\"\\u0000\",\"listaDetallados\":[]}]' value='15'>Ciencias naturales, matemáticas y estadística </option></select><br><b>Campo específico de grado académico</b></br><select class='form-control' id='selectEspecificos'><option selected='true' disabled='disabled'>SELECCIONE UNA OPCIÓN</option><option value='19'>Tecnologías de la información y la comunicación (TIC)</option></select><br></div></div>
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
    <link href='css/dda.css' rel='stylesheet' type='text/css'/>
    <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/mdbootstrap/4.6.0/js/mdb.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.13.2/js/bootstrap-select.min.js"></script>

    <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.13.2/js/i18n/defaults-es_ES.min.js"></script>
    <script type='text/javascript' src='ejemplos.js'></script>
    <link href='css/dda.select.css' rel='stylesheet' type='text/css'/>
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