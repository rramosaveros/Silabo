/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


function gestionarUnidades() {
    esperaProceso("");
    $.ajax({
        url: "Silabo/2EstructuraDesarrollo/InformacionUnidad/InformacionUnidadControlador.jsp",
        type: "POST",
        dataType: "text",
        data: {opcion: "gestionarUnidades"},
        success: function (datos) {
            swal.close();
            var data = JSON.parse(datos);
            $("#contenidosModal").html(data.contenidoModal);
//            $("#modalCargarContenido").modal("show");
            $("#modalgestionar").modal('show');
        },
        error: function (jqXHR, textStatus, errorThrown) {
            _fncBtnGuardarOK();
            _fncHabilitarContenido();
        }
    });

}
function cerrarModalContenido() {
    $("#modalCargarContenido").modal("hide");
}

function agregarNuevaUnidad() {
    var unidades = 0;
    $('.record_table').find('tr').find('.numero').each(function () {
        unidades++;
    });
    unidades++;
    var fila = "<tr class='informacion' id='" + unidades + "'>"
            + "<td style='width: 80%;' class='titulo'>"
            + "             <div class='input-group'>"
            + "            <input type='text' id='0' class='form-control'  value='' data-value='' onkeypress='return soloLetras(event);' onkeyup='actualizarValor(this);' placeholder='Información unidad'>"
            + "            </div>"
            + "</td>"
            + "<td class='numero' style='width: 20%;'>"
            + " <div class='input-group'>"
            + "<select class='form-control' disabled='disabled' onchange='cambianumeroUnidad(this);'>";
    for (var i = 1; i <= unidades; i++) {
        var selected = "";
        if (i === unidades) {
            selected = "selected";
        }
        fila += "<option value='" + i + "' " + selected + ">" + i + "</option>";
    }
    fila += "</select>"
            + " <span class='input-group-addon eliminar' title='Eliminar Unidad' onclick='EliminarUnidad(this);'><i class='fa fa-trash'></i></span>"
            + " </div>"
            + " </td>"
            + "</tr>";
    refreshSelected(unidades);
    $('.record_table').append(fila);
}
function refreshSelected(unidades) {
    var i = 1;
    $('.record_table').find('.informacion').each(function () {
        ($(this).attr('id', i));
        i++;
    });
    $('.record_table').find('.informacion').each(function () {
        var id = parseInt($(this).attr('id'));
        var opciones = "";
        for (var i = 1; i <= unidades; i++) {
            var selected = "";
            if (i === id) {
                selected = "selected";
            }
            opciones += "<option value='" + i + "' " + selected + ">" + i + "</option>";
        }
        $(this).find('.numero').find('.input-group').find('.form-control').html(opciones);
    });
}
function EliminarUnidad(objeto) {
    $(objeto).html("<i class='fa fa-spinner fa-pulse fa-fw fa-1x'  aria-hidden='true'></i>");
    var id = parseInt($(objeto).parent().parent().parent().find('.titulo').find('.input-group').find('.form-control').attr('id'));
    if (id !== 0) {
        $(objeto).parent().parent().parent().remove();
        var unidades = 0;
        $('.record_table').find('tr').find('.numero').each(function () {
            unidades++;
        });
        refreshSelected(unidades);
        var unidad = {};
        unidad.silabos = {'idSilabo': parseInt($("#idSilabo").val()), 'idTipo': parseInt($("#idTipo").val()), 'idUnidad': parseInt($("#idUnidad").val()), 'tipo': 'Unidades', 'rol': $("#RolUsuario").val()};
        unidad.unidades = [];
        var u = {};
        u.desc = $(objeto).parent().parent().parent().find('.titulo').find('.input-group').find('.form-control').val();
        u.numUnidad = $(objeto).parent().parent().parent().attr('id');
        u.id = $(objeto).parent().parent().parent().find('.titulo').find('.input-group').find('.form-control').attr('id');
        u.accion = "eliminar";
        unidad.unidades.push(u);
        var json = JSON.stringify(unidad);
        $.ajax({
            url: "Silabo/2EstructuraDesarrollo/InformacionUnidad/InformacionUnidadControlador.jsp",
            type: "POST",
            dataType: "text",
            data: {opcion: "saveEstructura", jsonEstructura: json},
            success: function (datos) {
                guardarUnidades();
            },
            error: function (jqXHR, textStatus, errorThrown) {
                loadEntidadTrabajo('getDocenteInicio', $("#slcFacultad").val(), $("#slcCarrera").val(), 'todos', $("#RolUsuario").val());
            }
        });
    } else {
        $(objeto).parent().parent().parent().remove();
    }
}

function cambianumeroUnidad(objeto) {
    var posicion = parseInt($(objeto).parent().parent().parent().attr('id'));
    var unidad = parseInt($(objeto).find("option:selected").text());
    if (unidad < posicion) {
        refreshdelante(posicion, unidad);
    } else {
        refreshdetras(posicion, unidad);
    }
}
function refreshdelante(posicion, unidad) {
    var trAux = $('.record_table').find("#" + posicion).html();

    for (var i = posicion; i > 1; i--) {
        $('.record_table').find("#" + (i)).html($('.record_table').find("#" + (i - 1)).html());
    }
    $('.record_table').find("#" + (unidad)).html(trAux);
    refreshvalueInput();
}
function refreshdetras(posicion, unidad) {
    var unidades = 0;
    $('.record_table').find('tr').find('.numero').each(function () {
        unidades++;
    });
    var trAux = $('.record_table').find("#" + posicion).html();
    for (var i = posicion; i < unidades; i++) {
        $('.record_table').find("#" + (i)).html($('.record_table').find("#" + (i + 1)).html());
    }
    $('.record_table').find("#" + (unidad)).html(trAux);
    refreshvalueInput();
}

function actualizarValor(objeto) {
    $(objeto).attr("data-value", $(objeto).val());
}

function refreshvalueInput() {
    $('.record_table').find('tr').find('.titulo').each(function () {
        $(this).find('.input-group').find('.form-control').val($(this).find('.input-group').find('.form-control').attr("data-value"));
    });
    var unidades = 0;
    $('.record_table').find('tr').find('.numero').each(function () {
        unidades++;
    });

    $('.record_table').find('tr').each(function () {

        var id = parseInt($(this).attr('id'));
        var opciones = "";
        for (var i = 1; i <= unidades; i++) {


            var selected = "";
            if (i === id) {
                selected = "selected";
            }
            opciones += "<option " + selected + ">" + i + "</option>";
        }
        $(this).find('.numero').find('.input-group').find('.form-control').html(opciones);
    });
}
function guardarUnidades() {
    var unidad = {};
    unidad.silabos = {'idSilabo': parseInt($("#idSilabo").val()), 'idTipo': parseInt($("#idTipo").val()), 'idUnidad': parseInt($("#idUnidad").val()), 'tipo': 'Unidades', 'rol': $("#RolUsuario").val()};
    unidad.unidades = [];
    $('.record_table').find('.informacion').each(function () {
        var u = {};
        u.desc = $(this).find('.titulo').find('.input-group').find('.form-control').val();
        u.numUnidad = $(this).attr('id');
        u.id = $(this).find('.titulo').find('.input-group').find('.form-control').attr('id');
        u.accion = $(this).attr('data-accion');
        if (u.desc !== "") {
            unidad.unidades.push(u);
        }
    });
    var json = JSON.stringify(unidad);
    $.ajax({
        url: "Silabo/2EstructuraDesarrollo/InformacionUnidad/InformacionUnidadControlador.jsp",
        type: "POST",
        dataType: "text",
        data: {opcion: "saveEstructura", jsonEstructura: json},
        success: function (datos) {
            $("#modalCargarContenido").modal("hide");

            $("#modalgestionar").modal('hide');
            loadEntidadTrabajo('getDocenteInicio', $("#slcFacultad").val(), $("#slcCarrera").val(), 'todos', $("#RolUsuario").val());

        },
        error: function (jqXHR, textStatus, errorThrown) {
            $("#modalCargarContenido").modal("hide");

            $("#modalgestionar").modal('hide');
            loadEntidadTrabajo('getDocenteInicio', $("#slcFacultad").val(), $("#slcCarrera").val(), 'todos', $("#RolUsuario").val());
        }
    });
}

function reestablecerUnidades() {
    var unidad = {};
    unidad.silabos = {'idSilabo': parseInt($("#idSilabo").val()), 'idTipo': parseInt($("#idTipo").val()), 'idUnidad': parseInt($("#idUnidad").val()), 'tipo': 'Unidades', 'rol': $("#RolUsuario").val()};
    unidad.unidades = [];
    var json = JSON.stringify(unidad);
    $.ajax({
        url: "Silabo/2EstructuraDesarrollo/InformacionUnidad/InformacionUnidadControlador.jsp",
        type: "POST",
        dataType: "text",
        data: {opcion: "reestablecer", json: json},
        success: function (datos) {
            $("#modalgestionar").modal('hide');
            loadEntidadTrabajo('getDocenteInicio', $("#slcFacultad").val(), $("#slcCarrera").val(), 'todos', $("#RolUsuario").val());
        },
        error: function (jqXHR, textStatus, errorThrown) {
            $("#modalgestionar").modal('hide');
            loadEntidadTrabajo('getDocenteInicio', $("#slcFacultad").val(), $("#slcCarrera").val(), 'todos', $("#RolUsuario").val());
        }
    });
}