/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/* global google */


function loadEntidadTrabajo(opcion, codFacultad, codCarrera, codMateria, rolActivo) {
    $("#modalCargarContenido").modal("show");
    $("#contenidoTitulo").html("Cargando...");
    fncLnkDocumentos();
    $("#contenidoDinamico").html("");
    $("#contenidoTitulo").html("Cargando...");
    $("#contenidoPie").html(" <i class='fa fa-spinner fa-pulse fa-2x fa-fw' style='color: #00417F;' aria-hidden='true'></i>");
    $("#contenidoMenuLateral").html("<div style='margin-top: 50%; text-align: center;'><i class='fa fa-spinner fa-pulse fa-2x fa-fw'></i><span class='sr-only'>Loading...</span></div>");
    $("#titulo").html("Cargando...");
    $("#subtitulo").html("Cargando...");
    $.ajax({
        url: "Silabo/6DatosDocentes/DatosDocentesControlador.jsp",
        type: "GET",
        dataType: "text",
        data: {opcion: opcion, codFacultad: codFacultad, codCarrera: codCarrera, codMateria: codMateria, rolActivo: rolActivo},
        success: function (Materia) {
            var silabo = JSON.parse(Materia);
            $("#contenidoRoles").html(silabo.contenidoRoles);
            $("#contenidoSelect").html(silabo.facultades);
            $("#contenidoSelect").append(silabo.carreras);
            $("#contenidoSelect").append(silabo.materias);
            $("#titulo").html(silabo.nombreMateria);
            $("#subtitulo").html(silabo.nombresDocente);
            $("#contenidoTitulo").html(silabo.contenidoTitulo);
            $('#slcFacultad').selectpicker({
                showTick: false,
                showIcon: true,
                size: '7',
                width: '32%'
            });
            $('#slcCarrera').selectpicker({
                showTick: false,
                showIcon: true,
                size: '7',
                width: '32%'
            });
            var s = false;
            if (($('#slcMateria').find('option').length > 6)) {
                s = true;
            }
            $('#slcMateria').selectpicker({
                showTick: false,
                showIcon: true,
                size: '7',
                width: '32%',
                liveSearch: s,
                liveSearchNormalize: s,
                liveSearchStyle: 'contains',
                noneResultsText: 'No existe coincidencia {0}',
                title: 'Seleccione materias',
            });
            $("#contenidoMenuLateral").html(silabo.contenidoMenuLateral);
            $("#treeview").shieldTreeView();
            $("#contenidoDinamico").html(silabo.contenidoDinamico);
            $("#idSilabo").val(parseInt(silabo.idSilabo));
            $("#txtRole").html(silabo.rolActivo);
            $("#menuTipo").html(silabo.menuTipo);
            $("#nombreDocente").html(silabo.nombreDocente);
            $("#contenidoInfo").html(silabo.contenidoInfo);
            $("#contenidoPie").html("");
            fncLnkDocumentos();
            $("#lnkAyuda").attr('data-content', "<div style='text-align:justify'>" + silabo.ayuda + "</div>");
            $("#modalCargarContenido").modal("hide");
            $('.collapse').collapse();
            // fncInitLnkAyuda(silabo.lnkAyuda);
            accionMostrarEntidadesTrabajo();
            if (rolActivo === 'Adm') {
                loadEntidadAdministrador();
            }

        },
        error: function (error) {
            $("#contenidoMenuLateral").html("");
//            $("#modalCargarContenido").modal("hide");
            $("#contenidoPie").html("<i class='fa fa-exclamation-triangle' style='color:gold!important' aria-hidden='true'></i>&nbsp;&nbsp;|&nbsp;&nbsp;Informaci&oacute;n no disponible");
        }
    });
}
function accionMostrarEntidadesTrabajo() {
    if ($("#slcMateria").val() === "0") {
        $("#contenidoInfo").find(".fa-upload").attr("onclick", null);
        $("#contenidoInfo").find(".fa-cloud-upload").attr("onclick", null);
        $("#contenidoInfo").find(".fa-cloud-download").attr("onclick", null);
        $("#contenidoInfo").find(".fa-upload").attr("title", "Silabo no Definido para Importar");
        $("#contenidoInfo").find(".fa-cloud-upload").attr("title", "Silabo no Definido para Subir PDF");
        $("#contenidoInfo").find(".fa-cloud-download").attr("title", "Silabo no Definido para Descargar PDF");
        $("#subtitulo").html("Ing. No definido");
    }

}
function loadEntidadPanel(codEntidad) {
    $("#contenidoTitulo").html("Cargando...");
    $("#titulo").html("Cargando...");
    $("#subtitulo").html("Cargando...");
    $("#contenidoDinamico").html("");
    graficarPanelInicio(codEntidad);
}

function  esperaProceso(title, text) {
    swal({
        title: title,
//        type: "info",
        text: text,
        timer: 90000,
        animation: true,
        closeOnClickOutside:false, //cambie
        allowOutsideClick: false, //cambie
        closeOnConfirm: false, //cambie
        onOpen: function () {
            swal.showLoading();
        }
    }).then(
            function () {},
            // handling the promise rejection
                    function (dismiss) {
                        if (dismiss === 'timer') {
                            console.log('I was closed by the timer');
//                            errorReporte("Tiempo de Espera Excedido");
                        }
                    }
            );
        }
function  errorReporte(msjError) {
    swal({
        title: "ERROR!",
        text: msjError,
        type: "error",
        confirmButtonText: "Close",
        confirmButtonColor: "#00417F"
    });
}

////////////////////////////////////////////////////////////// PAGINAS DOCENTES
function clicContenido(objeto, unidad) {
    var tipo = $(objeto).attr('id');

//    if ($(objeto).parent().parent().parent().hasClass('sui-treeview-item-active') && sessionStorage.getItem('tipoP' + unidad + $("#RolUsuario").val()) === tipo) {
//    } else {
    if ($("#RolUsuario").val() !== "Doc") {
        $(objeto).find('i').removeClass('fa-question');
        $(objeto).find('i').addClass('fa-check');
    }
    sessionStorage.setItem('tipoP' + unidad + $("#RolUsuario").val(), tipo);
    $('[data-toggle="tooltip"]').tooltip('hide');
    sessionStorage.setItem('objetoP', objeto);
    sessionStorage.setItem('unidadP', unidad);
    $("#modalCargarContenido").modal("show");
    $("#contenidoDinamico").html("");
    $("#contenidoPie").html(" <i class='fa fa-spinner fa-spin fa-fw fa-2x' style='color: #00417F;' aria-hidden='true'></i>");

    var idTipo = $(objeto).attr('data-idTipo');
    var tipoSeccion = $(objeto).attr('data-tipo');
    cargarInformacionImportar($(objeto).attr('data-id'), unidad, $(objeto).attr('data-titulo'), idTipo);

    var silabos = {};
    silabos.idSilabo = parseInt($("#idSilabo").val());
    silabos.idUnidad = parseInt(unidad);
    silabos.tipo = tipo;
    silabos.idTipo = idTipo;
    silabos.tipoSeccion = tipoSeccion;
    silabos.rol = $("#RolUsuario").val();
    var silabo = JSON.stringify(silabos);
    $("#contenidoTitulo").empty();
    agregarContenidoDinamicoPrincipal(silabo, tipo);
//    }
}
function agregarContenidoDinamicoPrincipal(silabo, tipo) {
    $.ajax({
        url: "indexSilaboControlador.jsp",
        type: "GET",
        data: {tipo: tipo, jsonSilabo: silabo},
        success: function (datos) {
            var data = JSON.parse(datos);
            if (data.tipo === 'html') {
                $("#contenidoDinamico").html(data.contenidoDinamico);
                $("#contenidoTitulo").html(data.contenidoTitulo);
                $("#contenidoPie").html("");
                if (tipo !== 'Docente' && tipo !== 'DatosGenerales') {
                    agregarObservacionesContenido(silabo);
                }
                habilitarSelectPicker();


            } else {
                window.location = data.tipo;
            }
            $("#lnkAyuda").attr('data-content', "<div style='text-align:justify'>" + data.lnkAyuda + "</div>");
            $("#modalCargarContenido").modal("hide");
            $('[data-toggle="tooltip"]').tooltip();
        },
        error: function (error) {
            $("#contenidoPie").html("<i class='fa fa-exclamation-triangle' style='color:gold!important' aria-hidden='true'></i>&nbsp;&nbsp;|&nbsp;&nbsp;Se ha presentado un error en el Servidor....");
            $("#modalCargarContenido").modal("hide");
        }
    });
}
function agregarObservacionesContenido(jsonSilabo) {
    $.ajax({
        url: "Observaciones/ObservacionControlador.jsp",
        type: "GET",
        data: {opcion: 'getObservaciones', jsonSilabo: jsonSilabo},
        success: function (datos) {
            var data = JSON.parse(datos);
            $("#contenidoObservaciones").html(data.contenidoObservacion);
            var textoOb = $("#txtObservaciones").val();
            textoOb = unescape(textoOb);
            $("#txtObservaciones").val(textoOb);
            $('[data-toggle="tooltip"]').tooltip();
            unescapeHistorico();
        },
        error: function (error) {
            $("#contenidoPie").html("<i class='fa fa-exclamation-triangle' style='color:gold!important' aria-hidden='true'></i>&nbsp;&nbsp;|&nbsp;&nbsp;Se ha presentado un error en el Servidor....");
            $("#modalCargarContenido").modal("hide");
        }
    });
}
function unescapeHistorico() {
    $(".ml-1").each(function () {
        $(this).html(unescape($(this).html()));
    });
}
function  cargarInformacionImportar(tipo, unidad, titulo, idTipo) {
//    if (tipo === 'Docente' || tipo === 'DatosGenerales') {
//        $("#contenidoInfo").find('.cambios').removeClass('fa-upload');
//    } else {
//        $("#contenidoInfo").find('.cambios').removeClass('fa-upload');
//        $("#contenidoInfo").find('.cambios').addClass('fa-upload');
//
//        $("#contenidoInfo").find('.cambios').attr('onclick', 'importarSilabo("' + tipo + '",' + unidad + ',"' + titulo + '","' + idTipo + '");');
//        $("#contenidoInfo").find('.cambios').attr('data-original-title', 'Importar ' + titulo);
//    }
}
function habilitarSelectPicker() {
    $('.form-group').each(
            function () {
                $(this).find('.col-xs-8').find('.selectpicker').selectpicker({
                    showTick: true,
                    showIcon: true,
                    iconBase: 'fa',
                    tickIcon: 'fa-check',
                    size: '7',
                    width: '100%',
                    template: "caret: '<span class=\"fa-chevron-down\"></span>'}"
                });
            }
    );
}
function  cambioAsignaturas() {
    $(".card-body").html("Contenido...");
    $(".card-title").html("Información..");
    clicContenidImportar(sessionStorage.getItem('tipo'), sessionStorage.getItem('unidad'), sessionStorage.getItem('idTipo'));
}

function  cambioFacultad() {
    var rolUsuario = $('#RolUsuario').val();
    var verificar = $("#bibliografias").html();
    if (rolUsuario !== 'Est' && verificar === undefined) {
        loadEntidadTrabajo('getDocenteInicio', $("#slcFacultad").val(), 'todos', 'todos', rolUsuario);
    } else {
        cargarEstructuraCurricular('todos', $("#slcFacultad").val());
    }
}
function  cambioCarrera() {
    var verificar = $("#bibliografias").html();
    var rolUsuario = $('#RolUsuario').val();
    if (rolUsuario !== 'Est' && verificar === undefined) {
        loadEntidadTrabajo('getDocenteInicio', $("#slcFacultad").val(), $("#slcCarrera").val(), 'todos', rolUsuario);
    } else {
        cargarEstructuraCurricular($("#slcCarrera").val(), $("#slcFacultad").val());
    }
}

function  cambioMateria() {
    loadEntidadTrabajo('getDocenteInicio', $("#slcFacultad").val(), $("#slcCarrera").val(), $("#slcMateria").val(), $('#RolUsuario').val());
}
function cerrarSession() {
    sessionStorage.setItem("logueo", null);
    window.location = "login/loginControlador.jsp?opcion=salir&salir=2";
}
/* global google, swal */
function  descargarSilabo(codCarrera, codMateria, codPeriodo) {
    esperaProceso('Generando Reporte', 'El proceso puede tardar unos minutos');
    var silabo = {};
    silabo.codMateria = codMateria;
    silabo.codCarrera = codCarrera;
    silabo.periodo = codPeriodo;

    var jsonSilabo = JSON.stringify(silabo);
    var filename = "SILABO";
    $.ajax({
        url: "SilaboGenerar/SilaboGenerarControlador.jsp?jsonSilabo=" + jsonSilabo,
        type: "GET",
        data: {opcion: 'generarSilabo'},
        success: function (datos) {

            if (datos !== "") {
                swal.close();
                var sampleArr = base64ToArrayBuffer2(datos);
                if (sampleArr !== "vacio") {
                    saveByteArray(filename, sampleArr);
                } else {
                    errorReporte("SILABO NO DISPONIBLE");
                }
            }

        },
        error: function (e) {
            swal.close();
            errorReporte("SILABO NO DISPONIBLE");
        }

    });
}
function base64ToArrayBuffer2(base64) {
    var bytes = null;
    try {
        var binaryString = window.atob(base64);
        var binaryLen = binaryString.length;
        if (binaryLen !== 0) {
            bytes = new Uint8Array(binaryLen);
            for (var i = 0; i < binaryLen; i++) {
                var ascii = binaryString.charCodeAt(i);
                bytes[i] = ascii;
            }
        } else {
            bytes = "vacio";
        }
    } catch (ex) {
        bytes = "";
    }
    return bytes;
}


function GuardarDocentes(id) {
    fncBtnGuardar();
    var docente = {};
    docente.id = id;
    docente.telefono = $("#Telefono").val();
    docente.tercerNivel = [];
    docente.cuartoNivel = [];
    if ($("#tercerNivel2").val() === undefined) {
        $('#tercerNivel option:selected').each(function () {
            var tercer = {};
            tercer.id = $(this).val();
            docente.tercerNivel.push(tercer);
        });
        $('#cuartoNivel option:selected').each(function () {
            var cuarto = {};
            cuarto.id = $(this).val();
            docente.cuartoNivel.push(cuarto);
        });
    } else {
        docente.idTercerNivel = $("#tercerNivel2").attr('data-id');
        docente.idCuartoNivel = $("#cuartoNivel2").attr('data-id');
        docente.strTercerNivel = $("#tercerNivel2").val();
        docente.strCuartoNivel = $("#cuartoNivel2").val();

    }
    var jsonDocente = JSON.stringify(docente);
    $.ajax({
        url: "Silabo/6DatosDocentes/DatosDocentesControlador.jsp",
        type: "POST",
        data: {opcion: 'updateDocente', jsonDocente: jsonDocente},
        success: function (datos) {
            var data = JSON.parse(datos);
            if (data.tipo === 'html') {
                $("#contenidoDinamico").html(data.contenidoDinamico);
                _fncBtnGuardarOK();
                habilitarSelectPicker();
                $("#btnGuardar").html('Guardar | <i class="fa fa-save" ></i>');
            }
        },
        error: function (e) {
            swal.close();
            errorReporte("DOCENTES NO GUARDADOS");
        }

    });
}
function  upperCaseTexto(id) {
    var texto = $("#" + id).val();
    texto = texto.toUpperCase();
    $("#" + id).val(texto);
}

///CONTROLES DE SILABO

function  verificardatosSelccionados(tipo) {
    var seleccionados = 0;
    $('.' + tipo + ':checked').each(
            function () {
                seleccionados++;
            }
    );
    if (seleccionados > 0) {
        $("#btnGuardar").attr("disabled", false);
    } else {
        $("#btnGuardar").attr("disabled", true);
    }

}
function verificarCambiosInput(objeto) {
    $("#btnGuardar").attr("disabled", false);
}
function agregarMetodologiaTipo(objeto) {
    $("#contenidoM").html("");
    $('.nav .items').each(function () {
        $(this).find('a').removeClass('active');
    });
    $(objeto).find('a').addClass('active');
    $.ajax({
        url: "Silabo/2EstructuraDesarrollo/EstrategiasMetodologicas/EstrategiasControlador.jsp",
        type: "POST",
        dataType: "text",
        data: {id: $(objeto).attr('id'), opcion: 'dibujarLista'},
        success: function (datos) {
            var data = JSON.parse(datos);
            $("#contenidoM").html(data.contenidoM);
        },
        error: function (jqXHR, textStatus, errorThrown) {
            _fncBtnGuardarKO();
        }
    });
}

function mostrarAyuda(objeto) {
    var lnkAyuda = $(objeto).find('a');
    if (lnkAyuda !== undefined) {
        lnkAyuda.attr('data-content', "<div style='text-align:justify'>" + unescape(lnkAyuda.attr('data-content') + "</div>"));
        lnkAyuda.attr('title', '');
        lnkAyuda.popover({
            html: true,
            trigger: "hover"
        });
    }

}
function mostrarAyudaGeneral(objeto) {
    var lnkAyuda = $(objeto);
    lnkAyuda.attr('data-content', "<div style='text-align:justify; color:#000000;'>" + unescape(lnkAyuda.attr('data-content') + "</div>"));
    lnkAyuda.attr('title', '');
    lnkAyuda.popover({
        html: true,
        trigger: "hover"
    });
}


function validaLetrasGlobal(e) {

    var key = e.keyCode || e.which;
    var tecla = String.fromCharCode(key).toLowerCase();
    var letras = " áéíóúabcdefghijklmnñopqrstuvwxyz,;";
    if (letras.indexOf(tecla) === -1) {
        return false;
    }

}
function mostrarInformacionPlaceholder(objeto) {
    if ($(objeto).val() !== "") {
        $(objeto).parent().parent().find("#placeholder").html("<b> Ejemplo: " + $(objeto).attr('placeholder') + "</b>");
    } else {
        $(objeto).parent().parent().find("#placeholder").html("");
    }
}
function validaNumerosGlobal(e) {

    var key = e.keyCode || e.which;
    var tecla = String.fromCharCode(key).toLowerCase();
    var letras = "0123456789";
    if (letras.indexOf(tecla) === -1) {
        return false;
    }

}


function seleccionarTodosItems(objeto) {
    $(".record_table tr").each(function () {
        var elm = $(this).find('td').find('.input-group').find('.input-group-addon').find('input');
        if (elm.attr("checked") === undefined) {
            elm.attr("checked", true);
        }
    });

    var c = $(objeto).find('input');
    if (c.attr("checked") === undefined) {
        c.attr("checked", true);
    }
    $(objeto).attr('onclick', 'deseleccionarTodosItems(this);');
    $(objeto).html('<div class=\'selectstrategies\'>Deseleccionar todos <input type="checkbox" checked="checked"></div>');
}

function deseleccionarTodosItems(objeto) {

    $(".record_table tr").each(function () {
        var elm = $(this).find('td').find('.input-group').find('.input-group-addon').find('input');
        elm.attr("checked", false);
    });
    var c = $(objeto).find('input');
    c.attr("checked", false);
    $(objeto).attr('onclick', 'seleccionarTodosItems(this);');
    $(objeto).html('<div class=\'selectstrategies\'>Seleccionar todos <input type="checkbox"></div>');
}

function seleccionarItem(objeto) {
    var elm = $(objeto).find('td').find('.input-group').find('.input-group-addon').find('input');
    if (elm.attr("checked") === undefined) {
        elm.attr("checked", true);
    } else {
        elm.attr("checked", false);
    }
}

function seleccionarTodosItemsEstrategias(objeto) {
    var clase = $(objeto).attr('id');
    $(objeto).parent().find("." + clase).each(function () {
        var elm = $(this).find('td').find('.input-group').find('.input-group-addon').find('input');
        if (elm.attr("checked") === undefined) {
            elm.attr("checked", true);
        }
    });

    var c = $(objeto).find('input');
    if (c.attr("checked") === undefined) {
        c.attr("checked", true);
    }
    $(objeto).attr('onclick', 'deseleccionarTodosItemsEstrategias(this);');
    $(objeto).html('<div class=\'selectstrategies\'>Deseleccionar todos <input type="checkbox" checked="checked"></div>');
}

function deseleccionarTodosItemsEstrategias(objeto) {
    var clase = $(objeto).attr('id');
    $(objeto).parent().find("." + clase).each(function () {
        var elm = $(this).find('td').find('.input-group').find('.input-group-addon').find('input');
        elm.attr("checked", false);
    });
    var c = $(objeto).find('input');
    c.attr("checked", false);
    $(objeto).attr('onclick', 'seleccionarTodosItemsEstrategias(this);');
    $(objeto).html('<div class=\'selectstrategies\'>Seleccionar todos <input type="checkbox"></div>');
}

function refrescar(icon) {
    $(".filter-option-inner-inner").find('i').removeClass('fa fa-question');
    $(".filter-option-inner-inner").find('i').removeClass('fa fa-check');
    $(".filter-option-inner-inner").find('i').removeClass('fa fa-exclamation-triangle');
    $(".filter-option-inner-inner").find('i').removeClass('tag-warning');
    $(".filter-option-inner-inner").find('i').removeClass('fa fa-fw');
    $(".filter-option-inner-inner").find('i').addClass('fa ' + icon);
    $('.dropdown-menu').find('.active').find('a').find('.glyphicon').removeClass('fa fa-question');
    $('.dropdown-menu').find('.active').find('a').find('.glyphicon').removeClass('fa fa-check');
    $('.dropdown-menu').find('.active').find('a').find('.glyphicon').removeClass('fa fa-exclamation-triangle');
    $('.dropdown-menu').find('.active').find('a').find('.glyphicon').removeClass('tag-warning');
    $('.dropdown-menu').find('.active').find('a').find('.glyphicon').removeClass('fa fa-fw');
    $('.dropdown-menu').find('.active').find('a').find('.glyphicon').addClass('fa ' + icon);
}