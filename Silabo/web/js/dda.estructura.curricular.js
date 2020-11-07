function cargarEstructuraCurricular(codCarrera, codFacultad) {

    $("#contenidoTitulo").html("Cargando...");
    $("#contenidoPie").html(" <i class='fa fa-spinner fa-pulse fa-fw fa-2x' style='color: #00417F;' aria-hidden='true'></i>");
    $("#contenidoDinamico").html("");
    $.ajax({
        url: "Estudiante/EstudianteControlador.jsp",
        type: "GET",
        data: {opcion: 'mallaCarrera', codCarrera: codCarrera, codFacultad: codFacultad},
        success: function (datos) {
            var data = JSON.parse(datos);
            var result = "<form class='form-inline col-xs-11' id='contenidoSelect'>"
                    + " <select id='slcAsignatura2' class='selectpicker dda-select' >"
                    + " <option data-icon='fa-fw' data-subtext=''>Cargando...</option>"
                    + " </select>"
                    + " </form>";
            $("#brrNavegacion").html(result);
            $("#contenidoSelect").html(data.facultades);
            $("#contenidoSelect").append(data.carrerasSelect);
            $("#menuTipo").html(data.menuTipo);
            $("#txtRole").html(data.rolActivo);
            $("#contenidoTitulo").html(data.contenidoTitulo);
            $("#nombreDocente").html(data.nombreUsuario);
            $("#contenidoRoles").html(data.contenidoRoles);
            $('#slcFacultad').selectpicker({
                showTick: false,
                showIcon: true,
                //  iconBase: 'fa',
                // tickIcon: 'fa-check',
                size: '7',
                width: '33%'
                        //   template: "caret: '<span class=\"fa-chevron-down\"></span>'}",
                        //      liveSearch: true
//                    liveSearchNormalize: true
            });
            $('#slcCarrera').selectpicker({
                showTick: false,
                showIcon: true,
                //  iconBase: 'fa',
                // tickIcon: 'fa-check',
                size: '7',
                width: '34%'
                        //   template: "caret: '<span class=\"fa-chevron-down\"></span>'}",
                        //      liveSearch: true
//                    liveSearchNormalize: true
            });
            $("#titulo").html($('#slcCarrera option:selected').text());
            $.ajax({
                url: "Estudiante/EstudianteControlador.jsp",
                type: "GET",
                data: {opcion: 'show', vista: 'entidad'},
                success: function (jsoncarrera) {
                    try {
                        var carrera = JSON.parse(jsoncarrera);
                        ecDibujar(carrera);
                        $("#contenidoPie").html("");
                    } catch (ex) {
                        $("#contenidoPie").html("<i class='fa fa-exclamation-triangle' style='color:gold!important' aria-hidden='true'></i>&nbsp;&nbsp;|&nbsp;&nbsp;Informaci&oacute;n no disponible");
                    }
                },
                error: function (error) {
                    $("#contenidoPie").html("<i class='fa fa-exclamation-triangle' style='color:gold!important' aria-hidden='true'></i>&nbsp;&nbsp;|&nbsp;&nbsp;Se ha presentado un error en el Servidor....");
                }
            });
        },
        error: function (error) {
            $("#contenidoPie").html("<i class='fa fa-exclamation-triangle' style='color:gold!important' aria-hidden='true'></i>&nbsp;&nbsp;|&nbsp;&nbsp;Se ha presentado un error en el Servidor....");
        }
    });

}
var camposFormacion;
var camposFormacionClass;
function ecDibujar(carrera) {
    try {
        if (carrera !== undefined) {
            // loading...
            $("#titulo").html(carrera.desc);
            $("#subtitulo").html("<b>" + $("#slcFacultad").val() + " - " + $("#slcCarrera").val() + "</b>");
            fncInitLnkAyuda("Estructura Curricular de la carrera ordenada por niveles y campos de formación");

            camposFormacion = carrera.camposFormacion;
            _ecCrearCamposFromacionClass();
            _ecDibujarCamposFormacion();
        }
    } catch (ex) {
        console.error(ex.message);
    }
}

var niveles = [];
var nivelesCamposFormacion = {};

function _ecCrearCamposFromacionClass() {
    try {
        camposFormacionClass = {};
        $.each(camposFormacion, function (j, campoFormacion) {
            camposFormacionClass[campoFormacion.codCampoF] = (j % 2 == 0) ? 'dda-ec-cf-par' : 'dda-ec-cf-impar';
        });
    } catch (ex) {
        console.error(ex.message);
    }

}
;

function _ecDibujarCamposFormacion() {
    try {
        // NO loading....
        _ecCrearNivelesXCamposFormacion();
        _ecCrearTabla();
    } catch (ex) {
        console.error(ex.message);
    }
}

function _ecCrearNivelesXCamposFormacion() {
    try {
        $.each(camposFormacion, function (i, campoFormacion) {
            var idCampoFormacion = campoFormacion.codCampoF;

            var asignaturas = campoFormacion.asignaturas;
            $.each(asignaturas, function (j, asignatura) {
                var codNivel = asignatura.codNivel;
                var idNivelCampoFormacion = codNivel + idCampoFormacion;
                niveles[codNivel] = codNivel;
                var nivelCampoFormacion = nivelesCamposFormacion[idNivelCampoFormacion];
                if (nivelCampoFormacion === undefined) {
                    nivelCampoFormacion = $("<td>").attr("id", idNivelCampoFormacion);
                    nivelCampoFormacion.addClass("dda-ec-elemento " + camposFormacionClass[idCampoFormacion] + " text-small-caps");
                    nivelesCamposFormacion[idNivelCampoFormacion] = nivelCampoFormacion;
                }
                var divAsignatura = $("<div onclick=" + asignatura.fncClick + ">").addClass("dda-ec-asignatura");
                divAsignatura.html(asignatura.nombreMateria);
                divAsignatura.attr("id", asignatura.codMateria);
                divAsignatura.appendTo(nivelCampoFormacion);
            });
        });
    } catch (ex) {
        console.log(ex.message);
    }
}

function _ecCrearTabla() {
    try {
        var contenidoDinamico = $("#contenidoDinamico");
        var tbEC = $("<table>").appendTo(contenidoDinamico);
        _ecCrearTablaEncabezado(tbEC);
        _ecCrearTablaCuerpo(tbEC);
    } catch (e) {
        console.log(ex.message);
    }
}

function  _ecCrearTablaEncabezado(tbEC) {
    try {
        var trEncabezado = $("<tr>").addClass("dda-ec-fila").appendTo(tbEC);
        $("<th>").appendTo(trEncabezado);

        $.each(camposFormacion, function (j, campoFormacion) {
            campoFormacionClass = (j % 2 == 0) ? 'dda-ec-cf-par' : 'dda-ec-cf-impar';

            $("<th>").addClass("text-small-caps " + campoFormacionClass).attr("id", campoFormacion.codCampoF).html(campoFormacion.descCampoF).appendTo(trEncabezado);
        });
    } catch (ex) {
        console.log(ex.message);
    }
}

function  _ecCrearTablaCuerpo(tbEC) {
    try {
        for (i = 0; i < niveles.length; i++) {
            if (niveles[i] !== undefined) {
                var trNivel = $("<tr>").addClass("dda-ec-fila");
                $("<th>").html(i).appendTo(trNivel);
                $.each(camposFormacion, function (j, campoFormacion) {
                    tdNivelCampoFormacion = _ecGetNiveCampoFormacion(i, campoFormacion.codCampoF);
                    tdNivelCampoFormacion.appendTo(trNivel);
                });
                trNivel.appendTo(tbEC);
            }
        }
    } catch (ex) {
        console.log(ex.message);
    }
}

function _ecGetNiveCampoFormacion(idNivel, idCampoFormacion) {
    var tdNivelCampoFormacion = null;
    try {
        var idNivelCampoFormacion = idNivel + idCampoFormacion;
        var tdNivelCampoFormacion = nivelesCamposFormacion[idNivelCampoFormacion];
        if (tdNivelCampoFormacion === undefined) {
            tdNivelCampoFormacion = $("<td>").attr("id", idNivelCampoFormacion);
        }
        tdNivelCampoFormacion.addClass("dda-ec-elemento " + camposFormacionClass[idCampoFormacion] + " text-small-caps");
    } catch (ex) {
        console.log(ex.message);
    }
    return tdNivelCampoFormacion;
}
