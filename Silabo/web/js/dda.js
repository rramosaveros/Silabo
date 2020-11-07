/* global $scope, RiskHomePageService, $http, URL */

$(document).ready(function () {

    fncLoadContenidosSistema();
    fncVerificarInternet();
    fncVerificaEstadoSilabo();
    fncInitLnkAyuda();


    /* Programar el evento click del lnkReportes */
    $("#lnkReportes").click(fncLnkReportes);

    /* Programar el evento click del lnkDocumentos */
    $("#lnkDocumentos").click(fncLnkDocumentos);

    /* Programar el evento click del lnkConfigurar */
    $("#lnkConfigurar").click(fncLnkConfigurar);

    /* Programar el evento click del btnGuardar */
    $("#btnGuardar").click(fncBtnGuardar);

    /* Habilitar los tooltips*/
//    $('[data-toggle="tooltip"]').tooltip();

    /* Habilitar los popover*/
//    $('[data-toggle="popover"]').popover();

    /* Menu lateral*/
//    $("#treeview").shieldTreeView();

    $('.treeview').each(function () {
        var tree = $(this);
        tree.treeview();
    });

    /* Contenidos */
    $('.tree-toggle').click(fncToogle);

    $(fncToogleInit());

});
function fncLoadContenidosSistema() {
//    $("head").load("ContenidoSistema/head.jsp");
    $("#contenidosModal").load("ContenidoSistema/modal.jsp");
    $(".dda-titulo").load("ContenidoSistema/header.jsp");
    $("main").load("ContenidoSistema/main.jsp");
    $(".dda-pie").load("ContenidoSistema/footer.jsp");
    $("#contenidosModalInicio").load("ContenidoSistema/modal.jsp");
    $("#referencias").load("ContenidoSistema/referencias.jsp");
}
function  popoverAyuda(objeto) {
    $(objeto).popover();
}
/*****************************/

function fncLnkReportes(reporte) {
    fncVerificarInternet();
    $("#lnkReportes").addClass("dda-link-selected");
    $("#lnkDocumentos").removeClass("dda-link-selected");
    $("#lnkConfigurar").removeClass("dda-link-selected");
    pcDibujar(reporte);

}

function fncLnkDocumentos() {
    fncVerificarInternet();
    $("#lnkReportes").removeClass("dda-link-selected");
    $("#lnkDocumentos").addClass("dda-link-selected");
    $("#lnkConfigurar").removeClass("dda-link-selected");
}

function fncLnkConfigurar() {
    fncVerificarInternet();
    $("#lnkReportes").removeClass("dda-link-selected");
    $("#lnkDocumentos").removeClass("dda-link-selected");
    $("#lnkConfigurar").addClass("dda-link-selected");
}

function sleep(miliseconds) {
    var currentTime = new Date().getTime();
    while (currentTime + miliseconds >= new Date().getTime()) {
    }
}

function fncBtnGuardar() {
    var btnGuardar = $("#btnGuardar");
    if (btnGuardar !== undefined) {
        btnGuardar.html('Guardar | <i class="fa fa-spinner fa-pulse" ></i>');
        /* Deshabilitar*/
        _fncDeshabilitarContenido();

    }
}

function _fncDeshabilitarContenido() {
    var contenido = $('#contenido');
    if (contenido !== undefined) {
        contenido.find("input,select,textarea,button").prop('disabled', true);
        contenido.fadeTo('slow', .6);
    }
}

function _fncDeshabilitarTotal() {
    $('#mainContainer').children().off('click');

}


function _fncHabilitarContenido() {
    var contenido = $('#contenido');
    if (contenido !== undefined) {
        contenido.find("input,select,textarea,button").prop('disabled', false);

        contenido.fadeTo('slow', 1);
    }
}

function _fncBtnGuardarOK() {
    var btnGuardar = $("#btnGuardar");
    if (btnGuardar !== undefined) {
        _fncHabilitarContenido();
        btnGuardar.tooltip('hide');
        btnGuardar.html('Guardar | <i class="fa fa-check" ></i>');
        btnGuardar.attr('title', 'Cambios Guardados.');
        btnGuardar.tooltip();
        btnGuardar.tooltip('show');
    }
}

function _fncBtnGuardarKO() {
    var btnGuardar = $("#btnGuardar");
    if (btnGuardar !== undefined) {
        btnGuardar.tooltip('hide');
        btnGuardar.html('Guardar | <i class="fa fa-exclamation" ></i>');
        btnGuardar.attr('title', 'Cambios NO guardados.');
        btnGuardar.tooltip();
        btnGuardar.tooltip('show');
    }
}

function fncInitLnkAyuda(txtAyuda) {


    txtAyuda = (txtAyuda === "") ? 'Establecer el texto de AYUDA que permita orientar cómo llenar el contenido.' : txtAyuda;
    var lnkAyuda = $("#lnkAyuda");
    if (lnkAyuda !== undefined) {
        lnkAyuda.attr('title', '');
        lnkAyuda.attr('data-content', txtAyuda);
        lnkAyuda.popover({
            html: true
        });
    }

}

function fncToogle() {
    $(this).parent().children('ul.tree').toggle(200);
}

function fncToogleInit() {
    $('.tree-toggle').parent().children('ul.tree').toggle(200);
}

function fncVerificarInternet() {
    setTimeout('fncVerificarInternet()', 2000);
    if (navigator.onLine) {
        $('.dda-internet').removeClass('dda-offline');
    } else {
        $('#dda-online').addClass('dda-offline');
    }
}

function fncVerificaEstadoSilabo() {
    setTimeout('fncVerificaEstadoSilabo()', 5000);
    if ($("#idSilabo").val() !== 'null' && $(".filter-option-inner-inner").html() !== undefined) {
        $.ajax({
            url: "Estadistica/EntidadEstadisticaControlador.jsp",
            type: "GET",
            dataType: "text",
            data: {opcion: 'verificarEstadoSilabo', idSilabo: $("#idSilabo").val()},
            success: function (datos) {
                var data = JSON.parse(datos);
                refrescar(data.icon);
            },
            error: function (jqXHR, textStatus, errorThrown) {
                _fncBtnGuardarKO();
            }
        });
    }

}


/*****************************************************/
