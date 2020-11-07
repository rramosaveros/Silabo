/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var cargar = "<div style='text-align: center; margin-top: 20%;'><img align='center' src='images/gifCarga.gif' style='height: 4%; width: 4%;'/></div>";
$(document).ready(function () {
    $(function () {
        if ($("#RolUsuario").val() !== 'Est' && $("#RolUsuario").val() !== 'Adm') {
//            loadEntidadTrabajo('getDocenteInicio', 'todos', 'todos', 'todos', $("#RolUsuario").val());
            graficarPanelInicio('todos', $("#RolUsuario").val());
        } else if ($("#RolUsuario").val() === 'Adm') {
            loadEntidadAdministrador();
        } else {
            cargarEstructuraCurricular('todos', 'todos');
        }
    });
});

function graficarPanelInicio(codEntidad, rolUsuario) {
    $("#modalCargarContenido").modal("show");
    $("#contenidoPie").html(" <i class='fa fa-spinner fa-pulse fa-2x' style='color: #00417F;' aria-hidden='true'></i>");
    $("#contenidoMenuLateral").html("<div style='margin-top: 50%; text-align: center;'><i class='fa fa-spinner fa-pulse fa-2x'></i><span class='sr-only'>Loading...</span></div>");
    var result = "<form class='form-inline col-xs-11' id='contenidoSelect'>"
            + "<br>"
            + " </form>";
    $("#brrNavegacion").html(result);
    var entidad = {'codigo': codEntidad, 'tipo': 'todos'};
    var jsonEntidad = JSON.stringify(entidad);
    $.ajax({
        url: "Inicio/indexControlador.jsp",
        type: "GET",
        data: {codCarrera: 'todos', tipo: "panel", jsonEntidad: jsonEntidad, accionActiva: 'Reporte'},
        success: function (datos) {
            var area = JSON.parse(datos);
            if (area.tipo === 'html') {
                if (rolUsuario !== 'Est') {
                    mostrarInformacionPanel(area, jsonEntidad);
                } else {
                    cargarEstructuraCurricular(area);
                }
            } else {
                window.location = area.tipo;
            }
            $("#modalCargarContenido").modal("hide");
        },
        error: function (error) {
            $("#modalCargarContenido").modal("hide");
            $("#contenidoPie").html("<i class='fa fa-exclamation-triangle' style='color:gold!important' aria-hidden='true'></i>&nbsp;&nbsp;|&nbsp;&nbsp;Reportes no Disponibles por el momento. Intentelo mas tarde....");
            $("#contenidoMenuLateral").html("");
        }
    });
}

function mostrarInformacionPanel(area, jsonEntidad) {
    $("#contenidoTitulo").empty();
    $("#contenidoMenuLateral").html(area.menuPanel);
    $("#treeview").shieldTreeView();
    $("#nombreDocente").html(area.nombreDocente);
    $("#contenidoRoles").html(area.contenidoRoles);
    $("#menuTipo").html(area.menuTipo);
    $("#txtRole").html(area.rolActivo);
    $("#subtitulo").html(area.nombresdocentes);
    $("#contenidoInfo").html(area.contenidoInfo);
    fncInitLnkAyuda();
    $("#contenidoPie").html("");
    $.ajax({
        url: "Inicio/indexControlador.jsp",
        type: "GET",
        data: {codCarrera: 'todos', accion: "getEntidad", tipo: 'show', 'jsonEntidad': jsonEntidad},
        success: function (entidad) {
            // $("#contenidoDinamico").html(contenido);
            var objEntidad = JSON.parse(entidad);
            $("#modalCargarContenido").modal("hide");
            fncLnkReportes(objEntidad);
        }
    });
}

function clicContenidoA(objeto) {
    $("#contenidoPie").html("");
    $("#contenidoDinamico").html("");
    fncInitLnkAyuda('');
    $("#contenidoPie").html(" <i class='fa fa-spinner fa-pulse fa-fw fa-2x' style='color: #00417F;' aria-hidden='true'></i>");
    $("#contenidoTitulo").empty();
    var tipo = $(objeto).attr('id');
    var silabos = {};
    silabos.idSilabo = 0;
    silabos.idUnidad = 0;
    silabos.tipo = tipo;
    silabos.rol = $("#RolUsuario").val();
    var a = JSON.stringify(silabos);
    $.ajax({
        url: "Silabo/Administrador/AdministradorControlador.jsp",
        type: "POST",
        data: {tipo: tipo, jsonSilabo: a},
        success: function (contenido) {
            var data = JSON.parse(contenido);
            if (data.tipo === 'html') {
                $("#contenidoDinamico").html(data.contenidoDinamico);
                $("#contenidoTitulo").html(data.contenidoTitulo);
                $("#contenidoPie").html("");
            } else {
                window.location = data.tipo;
            }
        },

        error: function (error) {
            $("#contenidoPie").html("<i class='fa fa-exclamation-triangle' style='color:gold!important' aria-hidden='true'></i>&nbsp;&nbsp;|&nbsp;&nbsp;Se ha presentado un error en el Servidor....");
        }
    });
}

function  clicUsuarios(objeto) {
    $("#contenidoDinamico").html("");
    var id = $(objeto).attr('id');
    $("#contenidoPie").html(" <i class='fa fa-spinner fa-pulse fa-fw fa-2x' style='color: #00417F;' aria-hidden='true'></i>");
    $("#contenidoTitulo").empty();
    $.ajax({
        url: "Silabo/Administrador/UsuariosGestion/UsuariosGestionControlador.jsp",
        type: 'GET',
        data: {opcion: 'getUsuarios'},

        success: function (result) {
            var data = JSON.parse(result);
            if (data.tipo === 'html') {
                $("#contenidoDinamico").html(data.contenidoDinamico);
                $("#titulo").html(data.titulo);
                $("#contenidoPie").html("");
            } else {
                window.location = data.tipo;
            }
        },
        error: function (jqXHR, textStatus, errorThrown) {
            $("#contenidoPie").html("<i class='fa fa-exclamation-triangle' style='color:gold!important' aria-hidden='true'></i>&nbsp;&nbsp;|&nbsp;&nbsp;Se ha presentado un error en el Servidor....");
        }
    });
}


function BusquedaUsuario() {
    var searchTerm = $(".search").val();
    var searchSplit = searchTerm.replace(/ /g, "'):containsi('");
    if (validarSiNumero(searchSplit)) {
        searchSplit = searchSplit.toUpperCase();
        var cont = 0;
        $(".unidad").each(function () {
            var input = $(this).find(".input-group").find('.usuarioInfo').val();
            if (input.indexOf(searchSplit) !== -1) {
                $(this).show();
                cont++;
            } else {
                $(this).hide();
            }

        });

        if (cont === 1) {
            $('.counter').text(cont + ' usuario');
        } else {
            $('.counter').text(cont + ' usuarios');
        }
    } else {
        var cont2 = 0;
        $(".unidad").each(function () {
            var input = $(this).find(".input-group").find('.usuariocedula').html();
            if (input.indexOf(searchSplit) !== -1) {
                $(this).show();
                cont2++;
            } else {
                $(this).hide();
            }

        });

        if (cont2 === 1) {
            $('.counter').text(cont2 + ' usuario');
        } else {
            $('.counter').text(cont2 + ' usuarios');
        }
    }
}
function validarSiNumero(numero) {
    if (!/^([0-9])*$/.test(numero)) {
        return true;
    } else {
        return  false;
    }
}
function soloNumeros(e) {
    var key = window.event ? e.which : e.keyCode;
    if (key < 48 || key > 57) {
        e.preventDefault();
    }
}

function validarNumeros(e) {
    var tecla = e.key;
    if (tecla === "-") {
        return true;
    }
    var key = e.keyCode || e.which;
    e = parseInt(e.key);
    var especiales = [8, 37, 39, 46];

    var tecla_especial = false;
    for (var i in especiales) {
        if (key === especiales[i]) {
            tecla_especial = true;
            break;
        }
    }
    try {
        if (!/^([0-9])*$/.test(e) && !tecla_especial) {
            return false;
        } else {
            return  true;
        }
    } catch (exception) {
        return true;
    }



}
function validarNumerosVerificar(e) {
    if (e.keyCode !== 13 && $("#Cedula").val().length < 10) {
        $("#informacionUsuario").html("");
        var key = e.keyCode || e.which;
        e = parseInt(e.key);
        var especiales = [8, 37, 39, 46];

        var tecla_especial = false;
        for (var i in especiales) {
            if (key === especiales[i]) {
                tecla_especial = true;
                break;
            }
        }
        try {
            if (!/^([0-9])*$/.test(e) && !tecla_especial) {
                return false;
            } else {
                return  true;
            }
        } catch (exception) {
            return true;
        }

    } else {

        if (verificarCedula($("#Cedula").val())) {
            $("#informacionUsuario").html('   <div style="text-align: center;"> <b>Verificando </b><i class="fa fa-spinner fa-pulse"></i> </div>');
            verificarUsuario();
        } else {
            $("#informacionUsuario").html('<div style="text-align: center;"> <b>Cédula incorrecta </b><i class="fa fa-exclamation-triangle" style="color: gold;"></i></div>');
            $("#Cedula").focus();

        }
    }

}
function validaLetras(e) {
    var key = e.keyCode || e.which;
    var tecla = String.fromCharCode(key).toLowerCase();
    var letras = " áéíóúabcdefghijklmnñopqrstuvwxyz";
    var especiales = "37-39-46-13";

    var tecla_especial = false;
    for (var i in especiales) {
        if (key === especiales[i]) {
            tecla_especial = true;
            break;
        }
    }
    alert(tecla_especial);
    if (letras.indexOf(tecla) === -1 && !tecla_especial) {
        return false;
    }

}
