/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


function gObjetivos(objeto) {
    $('[data-toggle="tooltip"]').tooltip('hide');
    var objetivos = {};
    objetivos.accion = 'guardar';
    var seleccionados = "";
    objetivos.objetivos = [];
    $('.unidad').each(
            function () {
                if ($(this).find('.input-group').find('.Objetivos').val().length > 0) {
                    var Objetivo = {};
                    var ObjetivoText= $(this).find('.input-group').find('.Objetivos').val();
                    ObjetivoText = escape(ObjetivoText);
                    Objetivo.descripcion = ObjetivoText;
                    Objetivo.idObjetivo = $(this).find('.input-group').find('.Objetivos').attr('id');
                    seleccionados += $(this).find('.input-group').find('.Objetivos').val();
                    if (Objetivo.descripcion !== "") {
                        objetivos.objetivos.push(Objetivo);
                    }
                }
            }
    );
    ejecutarAccionObjetivos(objetivos);
}

function agregarObjetivo(objeto) {
    $(".nuevo").focus();
    var fila = " <tr>"
            //                        + "            <td><input type='checkbox' id='" + actividad.getId_actividades_aprendizaje() + "' class='Actividades' " + estadoContenido + " value='" + actividad.getDescripcion() + "' " + actividad.getChv_check() + " >" + actividad.getDescripcion() + "</td>"
            + "      <td>"
            + "           <div class='unidad'> "
            + "                <div class='input-group'>"
            + "                     <textarea class='form-control Objetivos nuevo' id='0' onkeypress='return soloLetras(event);' onkeyup='verificarCambiosInput(this);' placeholder='Descripci&oacute;n del Objetivo'></textarea>"
            + "                     <span onclick='eliminarObjetivo(this);' class='input-group-addon' data-toggle='tooltip' data-placement='bottom' title='Eliminar Objetivo'>"
            + "                     <i class='fa fa-minus-circle'> </i>"
            + "                     </span>";
            + "                </div>"
            + "           </div>"
            + "       </td>"
            + " </tr>";
    $(objeto).parent().parent().parent().find('.Objetivos').find('.record_table').find('tbody').append(fila);
}

//function agregarObjetivo() {
//    $(".nuevo").focus();
//    var result = "";
//
//    result = "<div class='unidad'>"
//            + "<div class='input-group'>"
//            + "<input type='text' class='form-control Objetivos' id='0' placeholder='Descripci&oacute;n del Objetivo' value=''/>"
//            + "<span onclick='eliminarObjetivo(this);' class='input-group-addon' data-toggle='tooltip' data-placement='bottom' title='Eliminar Objetivo' id='0'>"
//            + "<i class='fa fa-minus-circle'> </i>"
//            + "</span>"
//            + "</div>"
//            + "</div>";
//    $("#ContenidoObjetivo").prepend(result);
//}


function eliminarObjetivo(objeto) {
    var id = $(objeto).parent().find('.Objetivos').attr('id');
    if (id === '0') {
        $(objeto).parent().parent().parent().remove();
    } else {
        var id = $(objeto).attr('id');
        if (id === "0") {
            $(objeto).parent().parent().remove();

        } else {
            var objetivos = {};
            objetivos.accion = 'eliminar';
            objetivos.objetivos = [];
            var objetivo = {};
            objetivo.idObjetivo = $(objeto).attr('id');
            objetivos.objetivos.push(objetivo);
            ejecutarAccionObjetivos(objetivos);
        }
    }
}

//function eliminarObjetivo(objeto) {
//    var id = $(objeto).attr('id');
//    if (id === "0") {
//        $(objeto).parent().parent().remove();
//
//    } else {
//        var objetivos = {};
//        objetivos.accion = 'eliminar';
//        objetivos.objetivos = [];
//        var objetivo = {};
//        objetivo.idObjetivo = $(objeto).attr('id');
//        objetivos.objetivos.push(objetivo);
//        ejecutarAccionObjetivos(objetivos);
//    }
//}


function ejecutarAccionObjetivos(objetivos) {
    fncBtnGuardar();
    objetivos.silabos = {'idSilabo': parseInt($("#idSilabo").val()), 'idTipo': parseInt($("#idTipo").val()), 'idUnidad': parseInt($("#idUnidad").val()), 'tipo': 'Objetivos', 'rol': $("#RolUsuario").val()};

    if ($("#RolUsuario").val() !== 'Doc' || $("#idObservacion").val() !== undefined) {
        objetivos.observaciones = [];
        var observacion = {};
        var observacionText = $("#txtObservaciones").val();
        observacionText = escape(observacionText);
        observacion.observacion = observacionText;
        observacion.id_observacion = $("#idObservacion").val();
        objetivos.observaciones.push(observacion);
    }
    var datos = JSON.stringify(objetivos);
    $.ajax({
        url: "Silabo/2EstructuraDesarrollo/Objetivos/ObjetivosControlador.jsp",
        type: "POST",
        dataType: "text",
        data: {jsonObjetivo: datos, opcion: "saveObjetivo"},
        success: function (datos) {
            var data = JSON.parse(datos);
            $("#contenidoDinamico").html(data.contenidoDinamico);
            $("#contenidoTitulo").html(data.contenidoTitulo);
            $("#contenidoMenuLateral").html(data.contenidoMenuLateral);
            $("#treeview").shieldTreeView();
            agregarObservacionesContenido(JSON.stringify(objetivos.silabos));
            fncInitLnkAyuda(data.lnkAyuda);
            _fncBtnGuardarOK();
        },

        error: function (jqXHR, textStatus, errorThrown) {
            _fncHabilitarContenido();
            _fncBtnGuardarKO();
        }
    });
}