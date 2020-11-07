function  gUnidades(objeto) {
    $('[data-toggle="tooltip"]').tooltip('hide');
    fncBtnGuardar();
    var estructura = {};
    estructura.silabos = {'idSilabo': parseInt($("#idSilabo").val()), 'idTipo': parseInt($("#idTipo").val()), 'idUnidad': parseInt($("#idUnidad").val()), 'tipo': 'informacionUnidad', 'rol': $("#RolUsuario").val()};
    estructura.unidades = [];
    var unidad = {};
    unidad.desc = $('.tituloUnidad').val();
    unidad.id = parseInt($('.tituloUnidad').attr('id'));
    unidad.numUnidad = parseInt($('.tituloUnidad').attr('name'));
    unidad.temas = [];
    $('.tema').each(
            function () {
                var tema = {};
                tema.desc = $(this).find('.descTema').val();
                tema.id = parseInt($(this).find('.idTema').attr('id'));
                tema.sistema = $(this).find('.sistema').val();
                tema.subtemas = [];
                $(this).find('.subtema').each(
                        function () {
                            if ($(this).find('.descSubtema').val().length > 0) {
                                var subtema = {};
                                subtema.desc = $(this).find('.descSubtema').val();
                                subtema.id = parseInt($(this).find('.idSubtema').attr('id'));
                                subtema.sistema = $(this).find('.sistema').val();
                                tema.subtemas.push(subtema);
                            }
                        }
                );
                if ($(this).find('.descTema').val().length > 0 || tema.subtemas.length > 0) {
                    unidad.temas.push(tema);
                }
            }
    );
    if ($("#RolUsuario").val() !== 'Doc' || $("#idObservacion").val() !== undefined) {
        estructura.observaciones = [];
        var observacion = {};
        var observacionText = $("#txtObservaciones").val();
        observacionText = escape(observacionText);
        observacion.observacion = observacionText;
        observacion.id_observacion = $("#idObservacion").val();
        estructura.observaciones.push(observacion);
    }

    estructura.unidades.push(unidad);
    var datos = JSON.stringify(estructura);
    $.ajax({
        url: "Silabo/2EstructuraDesarrollo/InformacionUnidad/InformacionUnidadControlador.jsp",
        type: "POST",
        dataType: "text",
        data: {jsonEstructura: datos, opcion: "saveEstructura"},
        success: function (datos) {
            var data = JSON.parse(datos);
            $("#contenidoDinamico").html(data.contenidoDinamico);
            $("#contenidoTitulo").html(data.contenidoTitulo);
            $("#contenidoMenuLateral").html(data.contenidoMenuLateral);
            $("#treeview").shieldTreeView();
            agregarObservacionesContenido(JSON.stringify(estructura.silabos));
            fncInitLnkAyuda(data.lnkAyuda);
            _fncBtnGuardarOK();
        },
        error: function (jqXHR, textStatus, errorThrown) {
            _fncHabilitarContenido();
//            $("#contenidoPie").html("<i class='fa fa-exclamation-triangle' style='color:gold!important' aria-hidden='true'></i>&nbsp;&nbsp;|&nbsp;&nbsp;Error el guardar unidades....");
            agregarContenidoDinamicoPrincipal(JSON.stringify(estructura.silabos), 'informacionUnidad');
        }
    });
}
function deleteTema(span) {
    $(span).html("<i class='fa fa-spinner fa-pulse fa-fw fa-1x'  aria-hidden='true'></i>");
    var inputGroup = $(span).parents('.tema').attr('id');
    var idTema = parseInt($(span).parents('.tema').find('.idTema').attr('id'));
    if (idTema === 0) {
        $("#" + inputGroup + "").remove();
    } else {
        var estructura = {};
        estructura.silabos = {'idSilabo': parseInt($("#idSilabo").val()), 'idTipo': parseInt($("#idTipo").val()), 'idUnidad': parseInt($("#idUnidad").val()), 'tipo': 'informacionUnidad', 'rol': $("#RolUsuario").val()};
        estructura.unidades = [];

        var unidad = {};
        unidad.desc = $('.tituloUnidad').val();
        unidad.id = parseInt($('.tituloUnidad').attr('id'));
        unidad.numUnidad = parseInt($('.tituloUnidad').attr('name'));
        unidad.temas = [];
        var tema = {};
        tema.id = idTema;
        unidad.temas.push(tema);
        estructura.unidades.push(unidad);
        var datos = JSON.stringify(estructura);
        $.ajax({
            url: "Silabo/2EstructuraDesarrollo/InformacionUnidad/InformacionUnidadControlador.jsp",
            type: "POST",
            dataType: "text",
            data: {jsonEstructura: datos, opcion: "deleteTema"},
            success: function (datos) {
                var data = JSON.parse(datos);
                $("#contenidoDinamico").html(data.contenidoDinamico);
                $("#contenidoTitulo").html(data.contenidoTitulo);
                $("#contenidoMenuLateral").html(data.contenidoMenuLateral);
                $("#treeview").shieldTreeView();
                agregarObservacionesContenido(JSON.stringify(estructura.silabos));
                fncInitLnkAyuda(data.lnkAyuda);
                _fncBtnGuardarOK();
                agregarContenidoDinamicoPrincipal(JSON.stringify(estructura.silabos), 'informacionUnidad');
            },
            error: function (jqXHR, textStatus, errorThrown) {
                _fncHabilitarContenido();
                $("#contenidoPie").html("<i class='fa fa-exclamation-triangle' style='color:gold!important' aria-hidden='true'></i>&nbsp;&nbsp;|&nbsp;&nbsp;Error el eliminar tema....");
            }
        });
    }

}

function deleteSubtema(span) {
    $(span).html("<i class='fa fa-spinner fa-pulse fa-fw fa-1x'  aria-hidden='true'></i>");
    var inputGroup = $(span).parents('.subtema').attr('id');
    var idSubtema = parseInt($(span).parents('.tema').find('.idSubtema').attr('id'));
    if (idSubtema === 0) {
        $("#" + inputGroup + "").remove();
    } else {
        var estructura = {};
        estructura.silabos = {'idSilabo': parseInt($("#idSilabo").val()), 'idTipo': parseInt($("#idTipo").val()), 'idUnidad': parseInt($("#idUnidad").val()), 'tipo': 'informacionUnidad', 'rol': $("#RolUsuario").val()};
        estructura.unidades = [];

        var unidad = {};
        unidad.temas = [];
        var tema = {};
        tema.subtemas = [];
        var subtema = {};
        subtema.id = idSubtema;
        tema.subtemas.push(subtema);
        unidad.temas.push(tema);
        estructura.unidades.push(unidad);
        var datos = JSON.stringify(estructura);
        $.ajax({
            url: "Silabo/2EstructuraDesarrollo/InformacionUnidad/InformacionUnidadControlador.jsp",
            type: "POST",
            dataType: "text",
            data: {jsonEstructura: datos, opcion: "deleteSubtema"},
            success: function (datos) {
                var data = JSON.parse(datos);
                $("#contenidoDinamico").html(data.contenidoDinamico);
                $("#contenidoTitulo").html(data.contenidoTitulo);
                $("#contenidoMenuLateral").html(data.contenidoMenuLateral);
                $("#treeview").shieldTreeView();
                agregarObservacionesContenido(JSON.stringify(estructura.silabos));
                fncInitLnkAyuda(data.lnkAyuda);
                _fncBtnGuardarOK();
            },
            error: function (jqXHR, textStatus, errorThrown) {
                _fncHabilitarContenido();
                $("#contenidoPie").html("<i class='fa fa-exclamation-triangle' style='color:gold!important' aria-hidden='true'></i>&nbsp;&nbsp;|&nbsp;&nbsp;Error el eliminar subtema....");
            }
        });
    }

}

function agregarTema(span) {
    var contTemas = parseInt($("#contTemas").val());
    contTemas++;
    var numTemas = contTemas;
    var idTemas = 't' + contTemas.toString();
    var cont = 1;
    $('#contenidoUnidad .tema').each(
            function () {
                cont++;
            }
    );
    var result = "";
    result += "<div class='tema' id='" + idTemas + "'>"
            + "<input type='hidden' class='sistema' value='S'>"
            + "<div class='input-group'>"
            + "<span class='input-group-addon'>"
            + "Tema " + cont + " : </span>"
            + "<input type='text' class='form-control descTema' onkeypress='return soloLetras(event);' onkeyup='verificarCambiosInput(this);' placeholder='Título del tema' value=''>";
    result += "<span class='input-group-addon idTema' onclick='agregarSubtema(this)' data-toggle='tooltip' data-placement='bottom' title='Agregar Subtema' id='0'>"
            + "<i class='fa fa-plus-circle'></i>"
            + "</span>";
    result += "<span class='input-group-addon' onclick='deleteTema(this)' data-toggle='tooltip' data-placement='bottom' title='Eliminar Tema' id=''>"
            + "<i class='fa fa-minus-circle'> </i>"
            + "</span>"
            + "  </div>"
            + "  </div>";
    $("#contenidoUnidad").append(result);
    $("#contTemas").val(numTemas);

}

function agregarSubtema(span) {
    var inputGroup = $(span).parents('.tema').attr('id');
    var contSubtemas = parseInt($("#contSubtemas").val());
    contSubtemas++;
    var cont = 1;
    var idSubtemas = 'st' + contSubtemas.toString();
    $('#' + inputGroup + ' .subtema').each(
            function () {
                cont++;
            }
    );
    var result = "";
    result += "<div class='subtema' id='" + idSubtemas + "'>"
            + "<input type='hidden' class='sistema' value='S'>"
            + "<div class='input-group'>"
            + "<span class='input-group-addon'>"
            + "Subtema " + cont + ": </span>"
            + "<input type='text' class='form-control descSubtema' onkeypress='return soloLetras(event);' onkeyup='verificarCambiosInput(this);' placeholder='Título del subtema' value=''>";
    result += "<span class='input-group-addon idSubtema' onclick='deleteSubtema(this)'  data-toggle='tooltip' data-placement='bottom' title='Eliminar Subtema' id='0'>"
            + "<i class='fa fa-minus-circle'> </i>"
            + "</span>"
            + " </div>"
            + " </div>";
    $("#" + inputGroup + "").append(result);
    $("#contSubtemas").val(contSubtemas);

}