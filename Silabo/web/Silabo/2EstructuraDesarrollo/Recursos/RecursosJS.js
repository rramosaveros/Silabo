/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function gRecursos(objeto) {
    $('[data-toggle="tooltip"]').tooltip('hide');
    fncBtnGuardar();
    var recursos = {};
    recursos.silabos = {'idSilabo': parseInt($("#idSilabo").val()), 'idTipo': parseInt($("#idTipo").val()), 'idUnidad': parseInt($("#idUnidad").val()), 'tipo': 'Recursos', 'rol': $("#RolUsuario").val()};
    recursos.recursos = [];

    var seleccionados = "";
    $('.Recursos:checked').each(
            function () {
                var recurso = {};
                recurso.idRecurso = ($(this).attr('id'));
                recursos.recursos.push(recurso);
                seleccionados += $(this).val();
            }
    );
    if ($("#RolUsuario").val() !== 'Doc' || $("#idObservacion").val() !== undefined) {
        recursos.observaciones = [];
        var observacion = {};
        var observacionText = $("#txtObservaciones").val();
        observacionText = escape(observacionText);
        observacion.observacion = observacionText;
        observacion.id_observacion = $("#idObservacion").val();
        recursos.observaciones.push(observacion);
    }

    var datos = JSON.stringify(recursos);
    var seleccionados2 = sessionStorage.getItem('seleccionados');
    if (seleccionados === seleccionados2 && $("#RolUsuario").val() === 'Docente') {
        _fncBtnGuardarOK();
    } else {
        $.ajax({
            url: "Silabo/2EstructuraDesarrollo/Recursos/RecursosControlador.jsp",
            type: "POST",
            dataType: "text",
            data: {jsonR: datos, opcion: "saveRecursos"},
            success: function (datos) {
                var data = JSON.parse(datos);
                $("#contenidoDinamico").html(data.contenidoDinamico);
                $("#contenidoTitulo").html(data.contenidoTitulo);
                $("#contenidoMenuLateral").html(data.contenidoMenuLateral);
                $("#treeview").shieldTreeView();
                agregarObservacionesContenido(JSON.stringify(recursos.silabos));
                fncInitLnkAyuda(data.lnkAyuda);
                _fncBtnGuardarOK();
            },
            error: function (jqXHR, textStatus, errorThrown) {
                _fncBtnGuardarKO();
            }
        });
    }
}
