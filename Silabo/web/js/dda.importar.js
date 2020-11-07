/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
function subirSilabo() {
    esperaProceso("");
    $.ajax({
        url: "SilaboGenerar/SilaboGenerarControlador.jsp",
        type: "GET",
        data: {opcion: 'subir'},
        success: function (datos) {
            swal.close();
            var data = JSON.parse(datos);
            $("#contenidosModal2").html(data.modalSubirArchivo);
            $('#archivosolicitud').filestyle({
                'text': ' Archivo',
                'htmlIcon': "<i class='fa fa-folder-o' aria-hidden='true'></i>",
                'btnClass': 'btn-primary',
                'size': 'nr',
                'input': true,
                'badge': true,
                'badgeName': 'badge-light',
                'buttonBefore': true,
                'disabled': false,
                'placeholder': 'Seleccione Archivo PDF',
                'onChange': function () {}
            });
            $("#modaArchivos").modal("show");
        },
        error: function (error) {
        }
    });
}

function  importarSilabo(tipo, unidad, titulo, idTipo) {
    sessionStorage.setItem("tipo", tipo);
    sessionStorage.setItem("unidad", unidad);
    sessionStorage.setItem("idTipo", idTipo);
    $("#respuesta").html("<i class='fa fa-spinner fa-pulse fa-3x fa-fw'></i>");
    $.ajax({
        url: "ImportarSilabo/ImportarControlador.jsp?opcion=getAsignaturasPeriodos",
        type: "GET",
        data: {tipo: 'importar'},
        success: function (importar) {
            var data = JSON.parse(importar);
            if (data.informacion === 'Datos') {
                $("#contenidosModal").html(data.modalImportar);
                $("#slcImportarPeriodos").html("");
                $("#slcImportarPeriodos").html(data.periodos);
                $('#modalPeriodos').selectpicker({
                    showTick: false,
                    showIcon: true,
                    //  iconBase: 'fa',
                    // tickIcon: 'fa-check',
                    size: '7',
                    width: '49%'
                            //   template: "caret: '<span class=\"fa-chevron-down\"></span>'}",
//                    liveSearch: true
//                    liveSearchNormalize: true
                });
                $("#slcImportar").html("");
                $("#slcImportar").html(data.carreras);
                $("#slcImportar").append(data.AsignaturasAnteriores);
                $("#modalCarreras").selectpicker({
                    showTick: false,
                    showIcon: true,
                    //  iconBase: 'fa',
                    // tickIcon: 'fa-check',
                    size: '7',
                    width: '49%'
                            //   template: "caret: '<span class=\"fa-chevron-down\"></span>'}",
//                    liveSearch: true,
//                    liveSearchNormalize: true
                });
                $("#modalAsignaturas").selectpicker({
                    showTick: false,
                    showIcon: true,
                    //  iconBase: 'fa',
                    // tickIcon: 'fa-check',
                    size: '7',
                    width: '50%',
                    //   template: "caret: '<span class=\"fa-chevron-down\"></span>'}",
                    liveSearch: true,
                    liveSearchNormalize: true
                });
                // $("#anterior").html("<i class='fa fa-question'aria-hidden='true'></i>");
                $("#myModalLabel").html(titulo);
                var asignatura = $("#titulo").html();
                $("#actual").html("<b>" + asignatura + "</b>");
                sessionStorage.setItem('peticion', 'asignado');
                $("#respuesta").html("");
                clicContenidImportar(tipo, unidad, idTipo);
                $("#Importarmodal").modal("show");

            } else {
                $("#contenidosModal").html(data.modalImportar);
                $("#Importarmodal").modal("show");
                $("#contenidomodal").html("No existen S&iacute;labos para la Importaci&oacute;n");
            }
            var divError = $("#error");
            if (divError !== undefined) {
                $(".modal-footer").find('.btn-primary').hide();
            } else {
                $(".modal-footer").find('.btn-primary').show();
            }
        },
        error: function (e) {
            $("#respuesta").html("<p>Se ha presentado un error en el Servidor....</p>");
        }

    });
}

function clicContenidImportar(tipo, unidad, idTipo) {
    var silabos = {};
    silabos.idSilabo = parseInt($("#idSilabo").val());
    silabos.idUnidad = parseInt(unidad);
    silabos.tipo = tipo;
    silabos.idTipo = idTipo;
    silabos.rol = 'Doc';
    silabos.codCarrera = $('#modalCarreras').val();
    silabos.codMateria = $('#modalAsignaturas').val();
    silabos.periodo = $('#modalPeriodos').val();
    var silabo = JSON.stringify(silabos);
    $("#contenidoTitulo").empty();
    $.ajax({
        url: "ImportarSilabo/ImportarControlador.jsp",
        type: "GET",
        data: {opcion: tipo, jsonSilabo: silabo},
        success: function (datos) {
            var data = JSON.parse(datos);
            $(".card-body").html(data.contenidos);
            $(".card-title").html(data.tituloContenido);
            var divError = $("#error").html();
            if (divError !== undefined) {
                $(".modal-footer").find('.btn-primary').hide();
                $(".modal-footer").find('.btn-primary').attr('id', 0);
            } else {
                $(".modal-footer").find('.btn-primary').attr('id', 1);
                $(".modal-footer").find('.btn-primary').show();
            }

        },
        error: function (error) {
            $("#contenidoPie").html("<i class='fa fa-exclamation-triangle' style='color:gold!important' aria-hidden='true'></i>&nbsp;&nbsp;|&nbsp;&nbsp;Se ha presentado un error en el Servidor....");
        }
    });
}

function guardarImportacion() {
    var tipo = sessionStorage.getItem("tipo");
    var unidad = sessionStorage.getItem("unidad");
    var idTipo = sessionStorage.getItem("idTipo");
    var unidadAnterior = sessionStorage.getItem('unidadAnterior');
    if (unidad === "undefined" || unidadAnterior === "undefined") {
        unidad = 0;
        unidadAnterior = 0;
    }

    $.ajax({
        url: "ImportarSilabo/ImportarControlador.jsp",
        type: "GET",
        data: {tipo: tipo, unidad: unidad, unidadAnterior: unidadAnterior, idTipo: idTipo, idSilaboActual: $("#idSilabo").val(), opcion: 'saveImportacion'},
        success: function (datos) {
            var data = JSON.parse(datos);
            if (data.respuesta === 'ok') {
                swal("Sección Importada Correctamente\n");
                $("#modalCargarContenido").modal("hide");
                $("#Importarmodal").modal("hide");
                var objeto = sessionStorage.getItem('objetoP');
                $(objeto).attr('id').click();
            } else {
                errorReporte("Error en la Importacion");
            }

        },
        error: function (error) {
            $("#contenidoPie").html("<i class='fa fa-exclamation-triangle' style='color:gold!important' aria-hidden='true'></i>&nbsp;&nbsp;|&nbsp;&nbsp;Se ha presentado un error en el Servidor....");
        }
    });
}
function  seleccionarUnidad(objeto) {
    $(objeto).find('.radio').find('.radiob').prop("checked", true);
    $("#importar").removeClass('btn-secondary');
    $("#importar").addClass('btn-primary');
    $("#importar").attr('onclick', 'guardarImportacion();');
    sessionStorage.setItem('unidadAnterior', $(objeto).find('.radio').find('.radiob').attr('id'));

}

