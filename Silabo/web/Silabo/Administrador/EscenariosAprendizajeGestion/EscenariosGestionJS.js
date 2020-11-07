/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).ready(function () {
    $("#addEscenario").click(function () {
        $("#contenidoPie").html("");
        var divEscenario = $("<div class=\"unidad\">");
        var sescenario = $("<div class=\"input-group\">");
        var txt = $("<input type=\"text\" id=\"0\" class=\"form-control Escenarios\" onkeypress=\"return soloLetras(event);\" placeholder=\"Descripci&oacute;n del Escenario\"/>");
        var quitarEscenario2 = $("<span class=\"input-group-addon\" data-toggle=\"tooltip\" data-placement=\"bottom\" title=\"Eliminar Escenario\"> <i class=\"fa fa-minus-circle\"></i></span>");

        sescenario.append(txt);
        sescenario.append(quitarEscenario2);
        divEscenario.append(sescenario);

        $("#ContenidoGestion").prepend(divEscenario);
        quitarEscenario2.click(function () {
            $(this).parent().remove();
        });
    });

    $(".gEscenarios").click(function () {
        var tipoE = $("#tipo").val();
        var escenarios = {};
        escenarios.escenarios = [];
        escenarios.silabos = {'idSilabo': 0, 'idUnidad': 0, 'tipo': tipoE, 'rol': $("#RolUsuario").val()};
        $('.Escenarios').each(
                function () {
                    var escenario = {};
                    escenario.descripcion = $(this).val();
                    escenario.idEsc = $(this).attr('id');
                    escenario.tipoE = tipoE;
                    if (escenario.descripcion !== "") {
                        escenarios.escenarios.push(escenario);
                    }
                }
        );

        var datos = JSON.stringify(escenarios);
        $.ajax({
            url: "Silabo/Administrador/EscenariosAprendizajeGestion/EscenariosGestionControlador.jsp",
            type: "POST",
            dataType: "text",
            data: {jsonGEscenarios: datos, opcion: 'saveEscenarios'},
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
    });

    $(".deleteEscenario").click(function () {
        $("#contenidoPie").html(" <i class='fa fa-spinner fa-pulse fa-fw fa-2x' style='color: #00417F;' aria-hidden='true'></i>");
        var escenarios = {};
        escenarios.escenarios = [];
        escenarios.silabos = {'idSilabo': 0, 'idUnidad': 0, 'tipo': $("#tipo").val(), 'rol': $("#RolUsuario").val()};

        var escenario = {};
        escenario.idEsc = parseInt($(this).attr('id'));
        escenario.tipoE = $("#tipo").val();
        escenarios.escenarios.push(escenario);
        var esc = JSON.stringify(escenarios);
        $.ajax({
            url: "Silabo/Administrador/EscenariosAprendizajeGestion/EscenariosGestionControlador.jsp",
            type: "POST",
            dataType: "text",
            data: {jsonEscenario: esc, opcion: 'deleteEscenario'},
            success: function (result) {
                var data = JSON.parse(result);
                $("#contenidoDinamico").html(data.contenidoDinamico);
                $("#contenidoTitulo").html(data.contenidoTitulo);
                $("#contenidoPie").html("");
            },
            error: function (jqXHR, textStatus, errorThrown) {
                $("#contenidoPie").html("<i class='fa fa-exclamation-triangle' style='color:gold!important' aria-hidden='true'></i>&nbsp;&nbsp;|&nbsp;&nbsp;Problemas al eliminar Escenario");
            }
        });
    });
    $(".habilitarEscenario").click(function () {
        $("#contenidoPie").html(" <i class='fa fa-spinner fa-pulse fa-fw fa-2x' style='color: #00417F;' aria-hidden='true'></i>");
        var escenarios = {};
        escenarios.escenarios = [];
        escenarios.silabos = {'idSilabo': 0, 'idUnidad': 0, 'tipo': $("#tipo").val(), 'rol': $("#RolUsuario").val()};

        var escenario = {};
        escenario.idEsc = parseInt($(this).attr('id'));
        escenario.tipoE = $("#tipo").val();
        escenarios.escenarios.push(escenario);
        var esc = JSON.stringify(escenarios);
        $.ajax({
            url: "Silabo/Administrador/EscenariosAprendizajeGestion/EscenariosGestionControlador.jsp",
            type: "POST",
            dataType: "text",
            data: {jsonEscenario: esc, opcion: 'habilitarEscenario'},
            success: function (result) {
                var data = JSON.parse(result);
                $("#contenidoDinamico").html(data.contenidoDinamico);
                $("#contenidoTitulo").html(data.contenidoTitulo);
                $("#contenidoPie").html("");
            },
            error: function (jqXHR, textStatus, errorThrown) {
                $("#contenidoPie").html("<i class='fa fa-exclamation-triangle' style='color:gold!important' aria-hidden='true'></i>&nbsp;&nbsp;|&nbsp;&nbsp;Problemas al habilitar Escenario");
            }
        });
    });
});