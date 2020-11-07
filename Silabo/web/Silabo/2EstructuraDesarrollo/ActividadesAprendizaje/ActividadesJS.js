/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function gActividades(objeto) {
    $('[data-toggle="tooltip"]').tooltip('hide');
    fncBtnGuardar();
    var actividades = {};
    var tipo = $("#tipoA").val();
    actividades.actividades = [];
    actividades.silabos = {'idSilabo': parseInt($("#idSilabo").val()), 'idTipo': parseInt($("#idTipo").val()), 'idUnidad': parseInt($("#idUnidad").val()), 'tipo': tipo, 'rol': $("#RolUsuario").val()};
//       parseInt($("#idSilabo").val())
    $('.Actividades:checked').each(
            function () {
                var actividad = {};
                actividad.id_actividades_aprendizaje = parseInt($(this).attr('id'));
                actividades.tipo_actividad = tipo;
                actividades.actividades.push(actividad);
            }
    );
    $('.nuevasActividades').each(
            function () {
                var actividad = {};
                actividad.id_actividades_aprendizaje = parseInt($(this).attr('id'));
                actividad.descripcion = $(this).val();
                actividad.idpadre = $(this).attr('data-padre');
                actividad.chv_check = "Doc";
                actividades.tipo_actividad = tipo;
                if (actividad.descripcion !== "") {
                    actividades.actividades.push(actividad);
                }
            }
    );
    if ($("#RolUsuario").val() !== 'Doc' || $("#idObservacion").val() !== undefined) {
        actividades.observaciones = [];
        var observacion = {};
        var observacionText = $("#txtObservaciones").val();
        observacionText = escape(observacionText);
        observacion.observacion = observacionText;
        observacion.id_observacion = $("#idObservacion").val();
        actividades.observaciones.push(observacion);
    }

    var datos = JSON.stringify(actividades);
    var jsonSilabo = JSON.stringify(actividades.silabos);
    $.ajax({
        url: "Silabo/2EstructuraDesarrollo/ActividadesAprendizaje/ActividadesControlador.jsp",
        type: "POST",
        dataType: "text",
        data: {jsonActividades: datos, opcion: "saveActividades"},
        success: function (datos) {
            var data = JSON.parse(datos);
            if (data.tipo === 'html') {
                $("#contenidoDinamico").html(data.contenidoDinamico);
                $("#contenidoTitulo").html(data.contenidoTitulo);
                agregarObservacionesContenido(jsonSilabo);
                $("#contenidoMenuLateral").html(data.contenidoMenuLateral);
                $("#treeview").shieldTreeView();
                fncInitLnkAyuda(data.lnkAyuda);
                _fncBtnGuardarOK();

            } else {
                window.location = data.tipo;
            }
        },
        error: function (jqXHR, textStatus, errorThrown) {
            _fncBtnGuardarKO();
        }
    });

}

function addActividadUusario(objeto) {
    var fila = "        <tr>"
            //                        + "            <td><input type='checkbox' id='" + actividad.getId_actividades_aprendizaje() + "' class='Actividades' " + estadoContenido + " value='" + actividad.getDescripcion() + "' " + actividad.getChv_check() + " >" + actividad.getDescripcion() + "</td>"
            + "<td>"
            + "<div class='input-group'>"
            + "                                         <input type='text' class='form-control nuevasActividades' onkeypress='return soloLetras(event);' data-padre='" + $(objeto).attr('data-id') + "' id='0' placeholder='Nueva Actividad' aria-label='actvidad' aria-describedby='basic-addon1'>"
            + "                                        <span onclick='removerActvidad(this);' class='input-group-addon'>"
            + "<i class='fa fa-minus-circle'></i>"
            + "                                        </span>"
            + "                                    </div>"
            + "</td>"
            + "        </tr>";
    $(objeto).parent().parent().parent().find('.actividad').find('.record_table').find('tbody').append(fila);
}

//actvidades de aprendizaje 
function removerActvidad(objeto) {
    var id = $(objeto).parent().find('.nuevasActividades').attr('id');
    if (id === '0') {
        $(objeto).parent().parent().parent().remove();
    } else {
        var actividades = {};
        var tipo = $("#tipoA").val();
        actividades.actividades = [];
        actividades.silabos = {'idSilabo': parseInt($("#idSilabo").val()), 'idTipo': parseInt($("#idTipo").val()), 'idUnidad': parseInt($("#idUnidad").val()), 'tipo': tipo, 'rol': $("#RolUsuario").val()};
        var actividad = {};
        actividad.id_actividades_aprendizaje = parseInt(id);
        actividades.actividades.push(actividad);
        var jsonActvidad = JSON.stringify(actividades);
        $.ajax({
            url: "Silabo/2EstructuraDesarrollo/ActividadesAprendizaje/ActividadesControlador.jsp",
            type: "POST",
            dataType: "text",
            data: {jsonActividades: jsonActvidad, opcion: 'deleteActividadUsuario'},
            success: function (datos) {
                var data = JSON.parse(datos);
                $("#contenidoDinamico").html(data.contenidoDinamico);
                $("#contenidoTitulo").html(data.contenidoTitulo);
                $("#contenidoMenuLateral").html(data.contenidoMenuLateral);
                $("#treeview").shieldTreeView();
                agregarObservacionesContenido(JSON.stringify(actividades.silabos));
                fncInitLnkAyuda(data.lnkAyuda);
                _fncBtnGuardarOK();
            },
            error: function (jqXHR, textStatus, errorThrown) {
                _fncBtnGuardarKO();
            }
        });
    }
}