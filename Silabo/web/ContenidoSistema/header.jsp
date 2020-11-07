<nav class="nav navbar navbar-dark bg-inverse navbar-top navbar-fixed-top" role="navigation">
    <!-- sello ESPOCH -->
    <a class="navbar-brand navbar-dark" href="http://www.espoch.edu.ec" target="blank">
        <div class="d-inline-block sello_espoch_image"></div>
    </a>
    <!-- logo APP -->
    <div class="navbar-brand navbar-dark">
        <img src="images/sello_app.png" class="d-inline-block" alt="SDA">
        <img src="images/sello_app_texto.png" class="d-inline-block" alt="DESARROLLO ACADÉMICO">
    </div>
    <!-- Role del usuario-->
    <div id="txtRole" class="role">
        ....
    </div>

    <ul id="menu" class="nav navbar-nav float-xs-right">
        <!-- Alertas -->
        <li class="nav-item bg-inverse invisible">
            <a id="lnkAlertas" href="#" class="nav-link link_menu">
                <i class="fa fa-bell alertaIcono"></i>
                <div class="alertaNumero">4</div>
            </a>
        </li>
        <!-- opciones VERTICALES -->
        <li class="nav-item dropdown bg-inverse">
            <a id="menuVertical" class="nav-link fa fa-chevron-down float-xs-right" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
                <img src="images/dda_user.png" class="dda-user float-xs-left" alt="usuario"/>
                <span class="name float-xs-left mr-1" id="nombreDocente">Cargando...</span>&nbsp;
            </a>
            <ul class="dropdown-menu dropdown-menu-right bg-inverse" id="contenidoRoles" aria-labelledby="menuVerticalItems">
                <!--Contenido Roles-->
            </ul>
        </li>
    </ul>
</nav>