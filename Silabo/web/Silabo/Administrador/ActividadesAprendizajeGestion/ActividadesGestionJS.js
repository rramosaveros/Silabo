/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//*****************************************************
//ACTIVIDADES
//***************************************************** 
function gActividadesGestion(objeto) {
    var tipo_actividad = $("#tipo").val();
    var jsonActividades = obtenerjsonActividades(tipo_actividad);
    $.ajax({
        url: "Silabo/Administrador/ActividadesAprendizajeGestion/ActividadesGestionControlador.jsp",
        type: "POST",
        dataType: "text",
        data: {jsonGActividades: jsonActividades, opcion: 'saveActividades'},
        success: function (result) {
            var data = JSON.parse(result);
            $("#contenidoDinamico").html(data.contenidoDinamico);
            $("#contenidoTitulo").html(data.contenidoTitulo);
            _fncBtnGuardarOK();
            $("#contenidoPie").html("");
        },
        error: function (jqXHR, textStatus, errorThrown) {
            $("#contenidoPie").html("<i class='fa fa-exclamation-triangle' style='color:gold!important' aria-hidden='true'></i>&nbsp;&nbsp;|&nbsp;&nbsp;Problemas al guardar Contenido");
        }
    });
}
function obtenerjsonActividades(tipo_actividad) {
    var actividades = {};
    actividades.actividades = [];
    actividades.silabos = {'idSilabo': 0, 'idUnidad': 0, 'tipo': tipo_actividad, 'rol': $("#RolUsuario").val()};
    if (tipo_actividad !== 'Aula') {
        $('.Actividades').each(
                function () {
                    var actividad = {};
                    actividad.id_actividades_aprendizaje = $(this).attr('id');
                    actividad.descripcion = $(this).val();
                    actividad.tipo_actividad = tipo_actividad;
                    if (actividad.descripcion !== "") {
                        actividades.actividades.push(actividad);
                    }
                }
        );
    } else {
        $('.Actividades').each(
                function () {
                    var actividad = {};
                    actividad.id_actividades_aprendizaje = $(this).attr('id');
                    actividad.descripcion = $(this).val();
                    actividad.tipo_actividad = tipo_actividad;
                    actividad.nivel2 = [];
                    $(this).parent().parent().find('.nivel2').find('.input-group').find('.Actividades2').each(
                            function () {
                                var actividad2 = {};
                                actividad2.id_actividades_aprendizaje = $(this).attr('id');
                                actividad2.descripcion = $(this).val();
                                actividad2.tipo_actividad = tipo_actividad;
                                if (actividad2.descripcion !== "") {
                                    actividad.nivel2.push(actividad2);
                                }

                            }
                    );
                    if (actividad.descripcion !== "") {
                        actividades.actividades.push(actividad);
                    }
                }
        );
    }
    var datos = JSON.stringify(actividades);
    return datos;
}
function habilitarActividad(objeto) {

    $("#contenidoPie").html(" <i class='fa fa-spinner fa-pulse fa-fw fa-2x' style='color: #00417F;' aria-hidden='true'></i>");
    var actividades = {};
    actividades.actividades = [];
    actividades.silabos = {'idSilabo': 0, 'idUnidad': 0, 'tipo': $("#tipo").val(), 'rol': $("#RolUsuario").val()};
    var actividad = {};
    actividad.id_actividades_aprendizaje = parseInt($(objeto).attr('id'));
    actividad.tipo_actividad = $("#tipo").val();
    actividades.actividades.push(actividad);

    var datos = JSON.stringify(actividades);
    $.ajax({
        url: "Silabo/Administrador/ActividadesAprendizajeGestion/ActividadesGestionControlador.jsp",
        type: "POST",
        dataType: "text",
        data: {jsonActividad: datos, opcion: 'habilitarActividad'},
        success: function (result) {
            var data = JSON.parse(result);
            $("#contenidoDinamico").html(data.contenidoDinamico);
            $("#contenidoTitulo").html(data.contenidoTitulo);
            $("#contenidoPie").html("");
        },
        error: function (jqXHR, textStatus, errorThrown) {
            $("#contenidoPie").html("<i class='fa fa-exclamation-triangle' style='color:gold!important' aria-hidden='true'></i>&nbsp;&nbsp;|&nbsp;&nbsp;Problemas al habilitar Actividad de Aprendizaje");
        }
    });
}
function deleteActividad(objeto) {
    if ($(objeto).attr('id') !== '0') {
        $("#contenidoPie").html(" <i class='fa fa-spinner fa-pulse fa-fw fa-2x' style='color: #00417F;' aria-hidden='true'></i>");
        var actividades = {};
        actividades.actividades = [];
        actividades.silabos = {'idSilabo': 0, 'idUnidad': 0, 'tipo': $("#tipo").val(), 'rol': $("#RolUsuario").val()};
        var actividad = {};
        actividad.id_actividades_aprendizaje = parseInt($(objeto).attr('id'));
        actividad.tipo_actividad = $("#tipo").val();
        actividades.actividades.push(actividad);

        var datos = JSON.stringify(actividades);
        $.ajax({
            url: "Silabo/Administrador/ActividadesAprendizajeGestion/ActividadesGestionControlador.jsp",
            type: "POST",
            dataType: "text",
            data: {jsonActividad: datos, opcion: 'deleteActividad'},
            success: function (result) {
                var data = JSON.parse(result);
                $("#contenidoDinamico").html(data.contenidoDinamico);
                $("#contenidoTitulo").html(data.contenidoTitulo);
                $("#contenidoPie").html("");

            },
            error: function (jqXHR, textStatus, errorThrown) {
                $("#contenidoPie").html("<i class='fa fa-exclamation-triangle' style='color:gold!important' aria-hidden='true'></i>&nbsp;&nbsp;|&nbsp;&nbsp;Problemas al eliminar Actividad de Aprendizaje");
            }
        });
    } else {
        if ($(objeto).parent().parent().attr('class') === 'nivel2') {
            $(objeto).parent().remove();
        } else {
            $(objeto).parent().parent().remove();
        }

    }
}
function addActividadnivel2(objeto) {
    $("#contenidoPie").html("");
    var divActividades = "<div class='input-group'  style='width: 95%; float: right;'>"
            + "<input type='text' id='0' class='form-control Actividades2' placeholder='Descripci&oacute;n de la actividad'/>"
            + "<span class='input-group-addon' onclick='deleteActividad(this);' data-toggle='tooltip' id='0' data-placement='bottom' title='Eliminar Actividad'> <i class='fa fa-minus-circle'></i></span>"
            + "</div>";
    $(objeto).parent().parent().find('.nivel2').prepend(divActividades);
}

function addActividades(objeto) {
    $("#contenidoPie").html("");
    var agregar = "";
    var span = "";
    if ($("#tipo").val() === 'Aula') {
        agregar = "<div class='nivel2'>"
                + "</div>";
        span = "<span class='input-group-addon' onclick='addActividadnivel2(this);' data-toggle='tooltip' id='0' data-placement='bottom' title='Agregar Actividad'> <i class='fa fa-plus-circle'></i></span>";
    }

    var divActividades = "<div class='unidad'>"
            + "<div class='input-group'>"
            + "<input type='text' id='0' class='form-control Actividades' onkeypress='return soloLetras(event)' placeholder='Descripci&oacute;n de la actividad'/>"
            + span
            + "<span class='input-group-addon' onclick='deleteActividad(this);' data-toggle='tooltip' id='0' data-placement='bottom' title='Eliminar Actividad'> <i class='fa fa-minus-circle'></i></span>"
            + "</div>"
            + agregar
            + "</div>";


    $("#ContenidoGestion").prepend(divActividades);

}