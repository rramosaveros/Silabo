/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function gEscenarios(objeto) {
    $('[data-toggle="tooltip"]').tooltip('hide');
    fncBtnGuardar();
    var escenarios = {};
    var tipo = $("#tipoE").val();
    escenarios.silabos = {'tipoSeccion': 'seccion', 'idSilabo': parseInt($("#idSilabo").val()), 'idTipo': parseInt($("#idTipo").val()), 'idUnidad': parseInt($("#idUnidad").val()), 'tipo': tipo, 'rol': $("#RolUsuario").val()};
    escenarios.escenarios = [];

    var seleccionados = "";
    $('.Escenarios:checked').each(
            function () {
                var escenario = {};
                escenario.idEsc = ($(this).attr('id'));
                escenario.tipoE = tipo;
                escenarios.escenarios.push(escenario);
                seleccionados += $(this).val();
            }
    );
    if ($("#RolUsuario").val() !== 'Doc' || $("#idObservacion").val() !== undefined) {
        escenarios.observaciones = [];
        var observacion = {};
        var observacionText = $("#txtObservaciones").val();
        observacionText = escape(observacionText);
        observacion.observacion = observacionText;
        observacion.id_observacion = $("#idObservacion").val();
        escenarios.observaciones.push(observacion);
    }

    var datos = JSON.stringify(escenarios);
    var seleccionados2 = sessionStorage.getItem('seleccionados');
    if (seleccionados === seleccionados2 && $("#RolUsuario").val() === 'Docente') {
        _fncBtnGuardarOK();
    } else {
        $.ajax({
            url: "Silabo/3EscenarioAprendizaje/EscenariosControlador.jsp",
            type: "POST",
            dataType: "text",
            data: {jsonEscenarios: datos, opcion: "saveEscenarios"},
            success: function (datos) {
                var data = JSON.parse(datos);
                $("#contenidoDinamico").html(data.contenidoDinamico);
                $("#contenidoTitulo").html(data.contenidoTitulo);
                fncInitLnkAyuda(data.lnkAyuda);
                $("#contenidoMenuLateral").html(data.contenidoMenuLateral);
                $("#treeview").shieldTreeView();
                agregarObservacionesContenido(JSON.stringify(escenarios.silabos));
                _fncBtnGuardarOK();
            },
            error: function (jqXHR, textStatus, errorThrown) {
                _fncBtnGuardarKO();
            }
        });
    }
}

