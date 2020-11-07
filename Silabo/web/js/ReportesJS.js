/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



/* global google, swal, URL, downloadLink */
function generarDetallePdfEntidad(codigo, nombre, tipo, accion, opcional, avance) {

    avance = (parseInt(avance) + 1);
    var entidad = {'codigo': codigo, nombre: nombre, 'tipo': tipo, fncClick: opcional, avance: avance};
    var jsonEntidad = JSON.stringify(entidad);
    if (accion === 'pdf') {
        esperaProceso('Generando Reporte', 'El proceso puede tardar unos minutos');
        var opcion = 'getEntidadPDF';
        var filename = "REPORTE_" + codigo.toUpperCase() + "_" + nombre.toUpperCase();
        if (tipo === 'campoformacion' || tipo === 'docente') {
            opcion = 'getEntidadCampoDocentePDF';
        }
        $.ajax({
            url: "Estadistica/EntidadEstadisticaControlador.jsp",
            type: "GET",
            data: {opcion: opcion, jsonEntidadPDF: jsonEntidad},
            success: function (datos) {
                swal.close();
                var sampleArr = base64ToArrayBuffer(datos);
                saveByteArray(filename, sampleArr);
            },
            error: function (e) {
                swal.close();
                errorReporte("REPORTE DE " + codigo.toUpperCase() + "_" + nombre.toUpperCase() + "NO DISPONIBLE");
            }
        });
    } else if (tipo !== 'docente') {
        $("#modalCargarContenido").modal("show");
        $("#contenidoDinamico").html("");
        $("#contenidoPie").html(" <i class='fa fa-spinner fa-pulse fa-fw fa-2x' style='color: #00417F;' aria-hidden='true'></i>");
        $("#titulo").html("Cargando...");
        $("#contenidoTitulo").html("Cargando...");
        $.ajax({
            url: "Inicio/indexControlador.jsp",
            type: "GET",
            data: {codCarrera: 'todos', tipo: "panel", jsonEntidad: jsonEntidad},
            success: function (datos) {
                var area = JSON.parse(datos);
                if (area.tipo === 'html') {
                    mostrarInformacionPanel(area, jsonEntidad);
                } else {
                    window.location = area.tipo;
                }
                $("#modalCargarContenido").modal("hide");
            },
            error: function (error) {
                $("#modalCargarContenido").modal("hide");
                $("#contenidoPie").html("<i class='fa fa-exclamation-triangle' style='color:gold!important' aria-hidden='true'></i>&nbsp;&nbsp;|&nbsp;&nbsp;Reportes no Disponibles por el momento. Intentelo mas tarde....");
            }
        });
    } else {
        swal("No existe mas Niveles\n");
    }

}
function  generarDetallePdfEntidadAnterior(codigo, nombre, tipo, accion, opcional, avance) {
    avance = (parseInt(avance) - 1);
    generarDetallePdfEntidad(codigo, nombre, tipo, accion, opcional, avance);
}
function panelAnterior() {
    alert("Panel Superior");
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
        closeOnConfirm: false,
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
function reporteCriteriosEntidadUsuario(id) {
    esperaProceso('Generando Reporte', 'El proceso puede tardar unos minutos');
    var entidad = {'codigo': $("#tipoEntidad").val(), 'nombre': $("#nombreEntidad").val(), 'tipo': id};
    var jsonReporte = JSON.stringify(entidad);
    $.ajax({
        url: "Estadistica/EntidadEstadisticaControlador.jsp",
        type: "GET",
        data: {opcion: "getReporteEntidadUsuarioPDF", jsonReporte: jsonReporte},
        success: function (data) {
            swal.close();
            id = id.toUpperCase();
            var sampleArr = base64ToArrayBuffer(data);
            saveByteArray(id, sampleArr);
        },
        error: function (e) {
            swal.close();
            errorReporte("REPORTE DE " + id + "NO DISPONIBLE");
        }
    });
}
function base64ToArrayBuffer(base64) {
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

function saveByteArray(reportName, byte) {
    var blob = new Blob([byte], {type: "application/pdf"});
    saveAs(blob, reportName + ".pdf");
}
;
function saveByteArray2(reportName, byte) {
//    var blob = new Blob([byte], {type: 'application/pdf'});
// It is necessary to create a new blob object with mime-type explicitly set
// otherwise only Chrome works like it should
    var newBlob = new Blob([byte], {type: "application/pdf"});

    // IE doesn't allow using a blob object directly as link href
    // instead it is necessary to use msSaveOrOpenBlob
//    if (window.navigator && window.navigator.msSaveOrOpenBlob) {
//        window.navigator.msSaveOrOpenBlob(newBlob);
//        return;
//    }
//
//// For other browsers: 
//// Create a link pointing to the ObjectURL containing the blob.
//    else {
//    const data = window.URL.createObjectURL(newBlob);
    var url = createObjectURL(newBlob);
//    $("#contenidoDinamico").html("<embed src='" + url + "' type='application/pdf' width='800' height='600'></embed>");
    sessionStorage.setItem('url', url);
    window.open("SilaboGenerar/ArchivosPDF.jsp", "_blank");
//        window.open(data, '_blank');
//        var link = document.createElement('a');
//        link.href = data;
//        link.download = "file.pdf";
//        link.click();
//    }
}
function createObjectURL(file) {
    if (window.webkitURL) {
        return window.webkitURL.createObjectURL(file);
    } else if (window.URL && window.URL.createObjectURL) {
        return window.URL.createObjectURL(file);
    } else {
        return null;
    }
}
function cargarEstadoSilabos() {
    esperaProceso('Generando Reporte', 'El proceso puede tardar unos minutos');
    $.ajax({
        url: "Estadistica/EntidadEstadisticaControlador.jsp",
        type: "GET",
        data: {opcion: "getReporteEstadoSilabosPDF"},
        success: function (data) {
            swal.close();
            var id = "ESTADO_SILABOS";
            var sampleArr = base64ToArrayBuffer(data);
            saveByteArray(id, sampleArr);
        },
        error: function (e) {
            swal.close();
            errorReporte("REPORTE DE " + id + "NO DISPONIBLE");
        }
    });
}