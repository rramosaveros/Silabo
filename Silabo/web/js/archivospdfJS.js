
function subirArchivos() {
    var archivosolicitud = $('#archivosolicitud').prop('files');
    var ObjApJSON = extraerCamposSolicitud("silabo", 'Archivo Solicitud');
//    var formulario = document.getElementById('frmRegistrarArchivoSolicitud');
    var form = new FormData();
    form.append('archivosolicitud', archivosolicitud[0]);
    fncBtnGuardar();
    var silabo = {};
    silabo.idSilabo = $("#idSilabo").val();
    $.ajax({
        type: 'POST',
        url: "Archivos/ArchivoModelo.jsp?op=registrarArchivos&ObjApJSON=" + JSON.stringify(ObjApJSON) + "&jsonSilabo=" + JSON.stringify(silabo),
        data: form,
        cache: false,
        processData: false,
        contentType: false,
        success: function (result) {
            var objetoResult = JSON.parse(result);
            if (objetoResult.resultIAE === "1") {
                _fncBtnGuardarOK();
                _fncHabilitarContenido();
                $("#mensajeSubir").html("<b>Archivo Subido</b>");
            }
        },
        error: function () {//SI OCURRE UN ERROR 
            _fncHabilitarContenido();
            _fncBtnGuardarKO();
            $("#mensajeSubir").html("<b>Archivo no Subido</b>");
        }
    });
}

function extraerCamposSolicitud(nombre, tipo) {
    var objeto = new Object();
    objeto.strTipoArchivo = tipo;
    objeto.strNombreArchivo = nombre;
    return objeto;
}
