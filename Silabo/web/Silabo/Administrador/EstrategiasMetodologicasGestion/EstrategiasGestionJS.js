/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).ready(function () {
    $("#addEstrategias").click(function () {
        $("#contenidoPie").html("");
        var divEstrategia = $("<div class=\"unidad\">");
        var sestrategia = $("<div class=\"input-group\">");
        var txt = $("<input type=\"text\" id=\"0\" class=\"form-control Estrategias\" placeholder=\"Descripci&oacute;n de la Estrategia\"/>");
        var quitarEstrategia = $("<span class=\"input-group-addon\" data-toggle=\"tooltip\" data-placement=\"bottom\" title=\"Eliminar Estrategia\"> <i class=\"fa fa-minus-circle\"></i></span>");
        sestrategia.append(txt);
        sestrategia.append(quitarEstrategia);
        divEstrategia.append(sestrategia);

        $("#ContenidoGestion").prepend(divEstrategia);
        quitarEstrategia.click(function () {
            $(this).parent().remove();
        });
    });


    $(".gEstrategias").click(function () {
        fncBtnGuardar();
        var Estrategias = {};
        Estrategias.estrategias = [];
        Estrategias.silabos = {'idSilabo': 0, 'idUnidad': 0, 'tipo': 'Estrategias', 'rol': $("#RolUsuario").val()};
        $('.Estrategias').each(
                function () {
                    var Estrategia = {};
                    Estrategia.descripcion = $(this).val();
                    Estrategia.id_estrategia = $(this).attr('id');
                    if (Estrategia.descripcion !== "") {
                        Estrategias.estrategias.push(Estrategia);
                    }
                }
        );

        var datos = JSON.stringify(Estrategias);
        $.ajax({
            url: "Silabo/Administrador/EstrategiasMetodologicasGestion/EstrategiasGestionControlador.jsp",
            type: "POST",
            dataType: "text",
            data: {jsonGEstrategias: datos, opcion: 'saveEstrategias'},
            success: function (result) {
                var data = JSON.parse(result);
                $("#contenidoDinamico").html(data.contenidoDinamico);
                $("#contenidoTitulo").html(data.contenidoTitulo);
                _fncHabilitarContenido();
                _fncBtnGuardarOK();
            },
            error: function (jqXHR, textStatus, errorThrown) {
                _fncHabilitarContenido();
                $("#contenidoPie").html("<i class='fa fa-exclamation-triangle' style='color:gold!important' aria-hidden='true'></i>&nbsp;&nbsp;|&nbsp;&nbsp;Problemas al guardar Contenido");
            }
        });
    });

    $(".deleteEstrategia").click(function () {
        $("#contenidoPie").html(" <i class='fa fa-spinner fa-pulse fa-fw fa-2x' style='color: #00417F;' aria-hidden='true'></i>");
        var Estrategias = {};
        Estrategias.estrategias = [];
        Estrategias.silabos = {'idSilabo': 0, 'idUnidad': 0, 'tipo': 'Estrategias', 'rol': $("#RolUsuario").val()};
        var estrategia = {};
        estrategia.id_estrategia = parseInt($(this).attr('id'));
        Estrategias.estrategias.push(estrategia);
        var est = JSON.stringify(Estrategias);
        $.ajax({
            url: "Silabo/Administrador/EstrategiasMetodologicasGestion/EstrategiasGestionControlador.jsp",
            type: "POST",
            dataType: "text",
            data: {jsonEstrategias: est, opcion: 'deleteEstrategia'},
            success: function (result) {
                var data = JSON.parse(result);
                $("#contenidoDinamico").html(data.contenidoDinamico);
                $("#contenidoTitulo").html(data.contenidoTitulo);
                $("#contenidoPie").html("");
            },
            error: function (jqXHR, textStatus, errorThrown) {
                $("#contenidoPie").html("<i class='fa fa-exclamation-triangle' style='color:gold!important' aria-hidden='true'></i>&nbsp;&nbsp;|&nbsp;&nbsp;Problemas al eliminar Estrategia Metodol&oacute;gica");
            }
        });
    });
    $(".habilitarEstrategia").click(function () {
        $("#contenidoPie").html(" <i class='fa fa-spinner fa-pulse fa-fw fa-2x' style='color: #00417F;' aria-hidden='true'></i>");
        var Estrategias = {};
        Estrategias.estrategias = [];
        Estrategias.silabos = {'idSilabo': 0, 'idUnidad': 0, 'tipo': 'Estrategias', 'rol': $("#RolUsuario").val()};
        var estrategia = {};
        estrategia.id_estrategia = parseInt($(this).attr('id'));
        Estrategias.estrategias.push(estrategia);
        var est = JSON.stringify(Estrategias);
        $.ajax({
            url: "Silabo/Administrador/EstrategiasMetodologicasGestion/EstrategiasGestionControlador.jsp",
            type: "POST",
            dataType: "text",
            data: {jsonEstrategias: est, opcion: 'habilitarEstrategia'},
            success: function (result) {
                var data = JSON.parse(result);
                $("#contenidoDinamico").html(data.contenidoDinamico);
                $("#contenidoTitulo").html(data.contenidoTitulo);
                $("#contenidoPie").html("");
            },
            error: function (jqXHR, textStatus, errorThrown) {
                $("#contenidoPie").html("<i class='fa fa-exclamation-triangle' style='color:gold!important' aria-hidden='true'></i>&nbsp;&nbsp;|&nbsp;&nbsp;Problemas al habilitar Estrategia Metodol&oacute;gica");
            }
        });
    });
});


