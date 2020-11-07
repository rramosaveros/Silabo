/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function cambioRolesTrabajo(Objeto) {
    $('#RolUsuario').val($(Objeto).attr('id'));
    $("#txtRole").html("Cargando...");
    loadEntidadTrabajo('cambioRolUsuario', 'todos', 'todos', 'todos', $(Objeto).attr('id'));
}


function cambioRolesPanel(Objeto) {
    $("#modalCargarContenido").modal("show");
    $("#txtRole").html("Cargando...");
    $("#contenidoDinamico").html("");
    $("#contenidoPie").html(" <i class='fa fa-spinner fa-pulse fa-fw fa-2x' style='color: #00417F;' aria-hidden='true'></i>");
    $("#contenidoMenuLateral").html("<div style='margin-top: 40%; text-align: center;'><i class='fa fa-spinner fa-pulse fa-2x fa-fw'></i><span class='sr-only'>Loading...</span></div>");
    var result = "<form class='form-inline col-xs-11' id='contenidoSelect'>"
            + "<br>"
            + " </form>";
    $("#brrNavegacion").html(result);
    $("#titulo").html("Cargando...");
    $("#contenidoTitulo").html("Cargando...");
    var entidad = {'codigo': 'todos', 'tipo': 'todos'};
    var jsonEntidadPanel = JSON.stringify(entidad);
    $.ajax({
        url: "Inicio/indexControlador.jsp",
        type: "GET",
        data: {rolActivo: $(Objeto).attr('id'), codCarrera: "todos", tipo: "cambioPanel", jsonEntidadPanel: jsonEntidadPanel},
        success: function (datos) {
            var area = JSON.parse(datos);
            if (area.tipo === 'html') {
                mostrarInformacionPanel(area, jsonEntidadPanel);
            } else {
                window.location = area.tipo;
            }
            $("#contenidoPie").html("");
        },
        error: function (error) {
            $("#contenidoDinamico").html("<div style='text-align: center; line-height: 250px;'><b>Se ha presentado un error en el Servidor....</b></div>");
        }
    });
}

function loadEntidadAdministrador() {
    _fncDeshabilitarTotal();
    $("#contenidoPie").html(" <i class='fa fa-spinner fa-pulse fa-fw fa-2x' style='color: #00417F;' aria-hidden='true'></i>");
    fncLnkConfigurar();
    $("#brrNavegacion").html("<div id='contenidoSelect'><br></div>");
    $("#contenidoMenuLateral").html("<div style='margin-top: 40%; text-align: center;'><i class='fa fa-spinner fa-pulse fa-3x fa-fw'></i><span class='sr-only'>Loading...</span></div>");
    $("#subtitulo").html("");
    $("#titulo").html("Cargando...");
    $("#contenidoDinamico").html("");
    $.ajax({
        url: "Silabo/Administrador/AdministradorControlador.jsp",
        type: "GET",
        data: {tipo: 'getMenuAdministrador'},
        success: function (datos) {
            $("#contenidoTitulo").html("Administrar Silabo");
            var area = JSON.parse(datos);
            if (area.tipo === 'html') {
                $("#nombreDocente").html(area.nombreDocente);
                $("#contenidoRoles").html(area.contenidoRoles);
                $("#txtRole").html(area.rolActivo);
                $("#titulo").html(area.contenidoTitulo);
                $("#contenidoMenuLateral").html(area.contenidoMenuLateral);
                $("#treeview").shieldTreeView();
                $("#contenidoPie").html("");
                var span = "<span id='lnkConfigurar' onclick='loadEntidadAdministrador();' class='dda-link'>";
                span += "      <i class='fa fa-cog'></i>";
                span += "</span>";
                $("#menuTipo").html(span);
                $("#contenidoInfo").html("");
                fncLnkConfigurar();
            } else {
                window.location = area.tipo;
            }
        },
        error: function (error) {
            $("#contenidoPie").html("&nbsp;&nbsp;|&nbsp;&nbsp;Contenido de Administrador no Disponible. Intentelo mas tarde....");
        }
    });
}