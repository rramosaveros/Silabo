/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).ready(function () {
    $("#addRecurso").click(function () {
        $("#contenidoPie").html("");
        var divRecurso = $("<div class=\"unidad\">");
        var srecurso = $("<div class=\"input-group\">");
        var txt = $("<input type=\"text\" id=\"0\" class=\"form-control Recursos\" onkeypress=\"return soloLetras(event);\" placeholder=\"Descripci&oacute;n del recurso\"/>");
        var quitarRecurso2 = $("<span class=\"input-group-addon\" data-toggle=\"tooltip\" data-placement=\"bottom\" title=\"Eliminar Recurso\"> <i class=\"fa fa-minus-circle\"></i></span>");

        srecurso.append(txt);
        srecurso.append(quitarRecurso2);
        divRecurso.append(srecurso);

        $("#ContenidoGestion").prepend(divRecurso);
        quitarRecurso2.click(function () {
            $(this).parent().remove();
        });
    });


    $(".gRecursos").click(function () {
        var Recursos = {};
        Recursos.recursos = [];
        Recursos.silabos = {'idSilabo': 0, 'idUnidad': 0, 'tipo': 'Recursos', 'rol': $("#RolUsuario").val()};
        $('.Recursos').each(
                function () {
                    var Recurso = {};
                    Recurso.strDescripcion = $(this).val();
                    Recurso.idRecurso = $(this).attr('id');
                    if (Recurso.strDescripcion !== "") {
                        Recursos.recursos.push(Recurso);
                    }
                }
        );

        var datos = JSON.stringify(Recursos);
        $.ajax({
            url: "Silabo/Administrador/RecursosGestion/RecursosGestionControlador.jsp",
            type: "POST",
            dataType: "text",
            data: {jsonGRecursos: datos, opcion: 'saveRecursos'},
            success: function (result) {
                var data = JSON.parse(result);
                $("#contenidoDinamico").html(data.contenidoDinamico);
                $("#contenidoTitulo").html(data.contenidoTitulo);
                _fncBtnGuardarOK();
            },
            error: function (jqXHR, textStatus, errorThrown) {
                $("#contenidoPie").html("<i class='fa fa-exclamation-triangle' style='color:gold!important' aria-hidden='true'></i>&nbsp;&nbsp;|&nbsp;&nbsp;Problemas al guardar Contenido");
            }
        });

    });

    $(".deleteRecurso").click(function () {
        $("#contenidoPie").html(" <i class='fa fa-spinner fa-pulse fa-fw fa-2x' style='color: #00417F;' aria-hidden='true'></i>");
        var Recursos = {};
        Recursos.recursos = [];
        Recursos.silabos = {'idSilabo': 0, 'idUnidad': 0, 'tipo': 'Recursos', 'rol': $("#RolUsuario").val()};
        var Recurso = {};
        Recurso.idRecurso = parseInt($(this).attr('id'));
        Recursos.recursos.push(Recurso);
        var r = JSON.stringify(Recursos);
        $.ajax({
            url: "Silabo/Administrador/RecursosGestion/RecursosGestionControlador.jsp",
            type: "POST",
            dataType: "text",
            data: {jsonRecurso: r, opcion: 'deleteRecurso'},
            success: function (result) {
                var data = JSON.parse(result);
                $("#contenidoDinamico").html(data.contenidoDinamico);
                $("#contenidoTitulo").html(data.contenidoTitulo);
                $("#contenidoPie").html("");
            },
            error: function (jqXHR, textStatus, errorThrown) {
                $("#contenidoPie").html("<i class='fa fa-exclamation-triangle' style='color:gold!important' aria-hidden='true'></i>&nbsp;&nbsp;|&nbsp;&nbsp;Problemas al eliminar Recurso");
            }
        });
    });
    $(".habilitarRecurso").click(function () {
        $("#contenidoPie").html(" <i class='fa fa-spinner fa-pulse fa-fw fa-2x' style='color: #00417F;' aria-hidden='true'></i>");
        var Recursos = {};
        Recursos.recursos = [];
        Recursos.silabos = {'idSilabo': 0, 'idUnidad': 0, 'tipo': 'Recursos', 'rol': $("#RolUsuario").val()};
        var Recurso = {};
        Recurso.idRecurso = parseInt($(this).attr('id'));
        Recursos.recursos.push(Recurso);
        var r = JSON.stringify(Recursos);
        $.ajax({
            url: "Silabo/Administrador/RecursosGestion/RecursosGestionControlador.jsp",
            type: "POST",
            dataType: "text",
            data: {jsonRecurso: r, opcion: 'habilitarRecurso'},
            success: function (result) {
                var data = JSON.parse(result);
                $("#contenidoDinamico").html(data.contenidoDinamico);
                $("#contenidoTitulo").html(data.contenidoTitulo);
                $("#contenidoPie").html("");

            },
            error: function (jqXHR, textStatus, errorThrown) {
                $("#contenidoPie").html("<i class='fa fa-exclamation-triangle' style='color:gold!important' aria-hidden='true'></i>&nbsp;&nbsp;|&nbsp;&nbsp;Problemas al habilitar Recurso");
            }
        });
    });
});
