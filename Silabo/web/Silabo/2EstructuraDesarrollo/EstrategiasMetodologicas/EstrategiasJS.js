/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function gEstrategias(objeto) {
    $('[data-toggle="tooltip"]').tooltip('hide');
    fncBtnGuardar();
    var estrategias = {};
    estrategias.silabos = {'periodo': $('#idEstrategia').val(), 'idSilabo': parseInt($("#idSilabo").val()), 'idTipo': parseInt($("#idTipo").val()), 'idUnidad': parseInt($("#idUnidad").val()), 'tipo': 'Estrategias', 'rol': $("#RolUsuario").val()};

    estrategias.estrategias = [];

    var seleccionados = "";
    $('.Estrategias:checked').each(
            function () {
                var estrategia = {};
                estrategia.id_estrategia = ($(this).attr('id'));
                estrategias.estrategias.push(estrategia);

                seleccionados += $(this).val();
            }
    );
    if ($("#RolUsuario").val() !== 'Doc' || $("#idObservacion").val() !== undefined) {
        estrategias.observaciones = [];
        var observacion = {};
        var observacionText = $("#txtObservaciones").val();
        observacionText = escape(observacionText);
        observacion.observacion = observacionText;
        observacion.id_observacion = $("#idObservacion").val();
        estrategias.observaciones.push(observacion);
    }

    var datos = JSON.stringify(estrategias);
    $.ajax({
        url: "Silabo/2EstructuraDesarrollo/EstrategiasMetodologicas/EstrategiasControlador.jsp",
        type: "POST",
        dataType: "text",
        data: {jsonEstrategias: datos, opcion: 'saveEstrategias'},
        success: function (datos) {
            var data = JSON.parse(datos);
            $("#contenidoDinamico").html(data.contenidoDinamico);
            $("#contenidoTitulo").html(data.contenidoTitulo);
            $("#contenidoMenuLateral").html(data.contenidoMenuLateral);
            $("#treeview").shieldTreeView();
            agregarObservacionesContenido(JSON.stringify(estrategias.silabos));
            fncInitLnkAyuda(data.lnkAyuda);
            _fncBtnGuardarOK();
        },
        error: function (jqXHR, textStatus, errorThrown) {
            _fncBtnGuardarKO();
        }
    });
}


