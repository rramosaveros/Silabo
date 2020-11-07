/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */




function  gLogros(objeto) {
    $('[data-toggle="tooltip"]').tooltip('hide');
    var logros = {};
    logros.accion = 'guardar';
    logros.logros = [];
    $('.unidad').each(
            function () {
                if ($(this).find('.input-group').find('.Logros').val().length > 0) {
                    var Logro = {};
                    Logro.descripcion = $(this).find('.input-group').find('.Logros').val();
                    Logro.idLogro = $(this).find('.input-group').find('.Logros').attr('id');
                    if (Logro.descripcion !== "") {
                        logros.logros.push(Logro);
                    }
                }
            }
    );
    ejecutarAccionLogros(logros);
}

function agregarLogro(objeto) {
    $(".nuevo").focus();
    var fila = " <tr>"
            //                        + "            <td><input type='checkbox' id='" + actividad.getId_actividades_aprendizaje() + "' class='Actividades' " + estadoContenido + " value='" + actividad.getDescripcion() + "' " + actividad.getChv_check() + " >" + actividad.getDescripcion() + "</td>"
            + "      <td>"
            + "           <div class='unidad'> "
            + "                <div class='input-group'>"
            + "                     <input type='text' class='form-control Logros nuevo' id='0' onkeypress='return soloLetras(event);' onkeyup='verificarCambiosInput(this);' placeholder='Descripci&oacute;n del Logro de Aprendizaje' value=''/>"
            + "                     <span onclick='eliminarLogro(this);' class='input-group-addon' data-toggle='tooltip' data-placement='bottom' title='Eliminar Logro de Aprendizaje'>"
            + "                     <i class='fa fa-minus-circle'> </i>"
            + "                     </span>";
            + "                </div>"
            + "           </div>"
            + "       </td>"
            + " </tr>";
    $(objeto).parent().parent().parent().find('.Logros').find('.record_table').find('tbody').append(fila);
}

//function agregarLogro() {
//    $(".nuevo").focus();
//    var result = "";
//    result = "<div class='unidad'>"
//            + "<div class='input-group'>"
//            + "<input type='text' class='form-control Logros' id='0' placeholder='Descripci&oacute;n del Logro de Aprendizaje' value=''/>"
//            + "<span onclick='eliminarLogro(this);' class='input-group-addon' data-toggle='tooltip' data-placement='bottom' title='Eliminar Logro de Aprendizaje' id='0'>"
//            + "<i class='fa fa-minus-circle'> </i>"
//            + "</span>"
//            + "</div>"
//            + "</div>";
//    $("#ContenidoLogro").prepend(result);
//}

function eliminarLogro(objeto) {
    var id = $(objeto).parent().find('.Logros').attr('id');
    if (id === '0') {
        $(objeto).parent().parent().parent().remove();
    } else {
        var id = $(objeto).attr('id');
        if (id === "0") {
            $(objeto).parent().parent().remove();
        } else {
            var logros = {};
            logros.accion = 'eliminar';
            logros.logros = [];
            var logro = {};
            logro.idLogro = $(objeto).attr('id');
            logros.logros.push(logro);
            ejecutarAccionLogros(logros);
        }
    }
}

//function eliminarLogro(objeto) {
//    var id = $(objeto).attr('id');
//    if (id === "0") {
//        $(objeto).parent().parent().remove();
//    } else {
//        var logros = {};
//        logros.accion = 'eliminar';
//        logros.logros = [];
//        var logro = {};
//        logro.idLogro = $(objeto).attr('id');
//        logros.logros.push(logro);
//        ejecutarAccionLogros(logros);
//
//    }
//}

function ejecutarAccionLogros(logros) {
    fncBtnGuardar();
    logros.silabos = {'idSilabo': parseInt($("#idSilabo").val()), 'idTipo': parseInt($("#idTipo").val()), 'idUnidad': parseInt($("#idUnidad").val()), 'tipo': 'Logros', 'rol': $("#RolUsuario").val()};
    if ($("#RolUsuario").val() !== 'Doc' || $("#idObservacion").val() !== undefined) {
        logros.observaciones = [];
        var observacion = {};
        var observacionText = $("#txtObservaciones").val();
        observacionText = escape(observacionText);
        observacion.observacion = observacionText;
        observacion.id_observacion = $("#idObservacion").val();
        logros.observaciones.push(observacion);
    }
    var datos = JSON.stringify(logros);
    $.ajax({
        url: "Silabo/2EstructuraDesarrollo/LogrosAprendizaje/LogrosControlador.jsp",
        type: "POST",
        dataType: "text",
        data: {jsonLogro: datos, opcion: "saveLogro"},
        success: function (datos) {
            var data = JSON.parse(datos);
            $("#contenidoDinamico").html(data.contenidoDinamico);
            $("#contenidoTitulo").html(data.contenidoTitulo);
            $("#contenidoMenuLateral").html(data.contenidoMenuLateral);
            $("#treeview").shieldTreeView();
            agregarObservacionesContenido(JSON.stringify(logros.silabos));
            fncInitLnkAyuda(data.lnkAyuda);
            _fncBtnGuardarOK();
        },
        error: function (jqXHR, textStatus, errorThrown) {
            _fncHabilitarContenido();
        }
    });

}