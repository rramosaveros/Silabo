/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function cargarParametros() {
    $("#contenidoPie").html("");
    $("#contenidoDinamico").html("");
    $("#contenidoPie").html(" <i class='fa fa-spinner fa-pulse fa-fw fa-2x' style='color: #00417F;' aria-hidden='true'></i>");
    var Silabo = {};

    Silabo.idSilabo = 0;
    Silabo.codMateria = "";
    Silabo.codCarrera = "";
    Silabo.periodo = "";

    var jsonSilabo = JSON.stringify(Silabo);
    $.ajax({
        url: "Silabo/Administrador/Parametros/ParametrosControlador.jsp",
        type: "POST",
        dataType: "text",
        data: {jsonSilabo: jsonSilabo, opcion: 'getParametros'},
        success: function (result) {
            var data = JSON.parse(result);
            $("#contenidoDinamico").html(data.contenidoDinamico);
            $("#contenidoTitulo").html(data.contenidoTitulo);
            $("#contenidoPie").html("");
        },
        error: function (jqXHR, textStatus, errorThrown) {
            _fncBtnGuardarKO();
        }
    });
}

function guardarParametros() {
    fncBtnGuardar();
    var Silabo = {};
    Silabo.idSilabo = 0;
    Silabo.codMateria = "";
    Silabo.codCarrera = "";
    Silabo.periodo = "";
    var jsonSilabo = JSON.stringify(Silabo);
    var Parametros = {};
    Parametros.parametros = [];
    $(".parametros").each(function () {
        var Parametro = {};
        Parametro.id = $(this).find('.pdescripcion').find('.input-group').find('.descripcion').attr('id');
        Parametro.descripcion = $(this).find('.pdescripcion').find('.input-group').find('.descripcion').val();
        Parametro.valor = $(this).find('.pvalor').find('.input-group').find('.valor').val();
        Parametros.parametros.push(Parametro);
    });
    var jsonParametros = JSON.stringify(Parametros);
    $.ajax({
        url: "Silabo/Administrador/Parametros/ParametrosControlador.jsp",
        type: "POST",
        dataType: "text",
        data: {jsonSilabo: jsonSilabo, jsonParametros: jsonParametros, opcion: 'saveParametros'},
        success: function (result) {
            var data = JSON.parse(result);
            $("#contenidoDinamico").html(data.contenidoDinamico);
            $("#contenidoTitulo").html(data.contenidoTitulo);
            _fncBtnGuardarOK();
        },
        error: function (jqXHR, textStatus, errorThrown) {
            _fncBtnGuardarKO();
        }
    });
}
