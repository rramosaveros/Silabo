/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//$(document).ready(function () {
//
//    $('select#a').on('change', function () {
//        var valor = 0;
//        $("select#a").each(
//                function () {
//                    valor += parseInt($(this).val());
//                }
//        );
//        if (valor > 8) {
//            valor = valor - $(this).val();
//            $(this).val(0);
//            alert("Máxima nota: 8");
//        }
//        $("#parcial1").val(valor);
//        $("#btnGuardar").attr("disabled", false);
//
//    });
//
//    $('select#a1').on('change', function () {
//        var valor2 = 0;
//        $("select#a1").each(
//                function () {
//                    valor2 += parseInt($(this).val());
//                }
//        );
//        if (valor2 > 10) {
//            valor2 = valor2 - $(this).val();
//            $(this).val(0);
//            alert("Máxima nota: 10");
//        }
//        $("#parcial2").val(valor2);
//        $("#btnGuardar").attr("disabled", false);
//    });
//    $('select#a2').on('change', function () {
//        var valor3 = 0;
//        $("select#a2").each(
//                function () {
//                    valor3 += parseInt($(this).val());
//                }
//        );
//        if (valor3 > 10) {
//            valor3 = valor3 - $(this).val();
//            $(this).val(0);
//            alert("Máxima nota: 10");
//        }
//        $("#parcial3").val(valor3);
//        $("#btnGuardar").attr("disabled", false);
//    });
//
//    $(".gEvaluaciones").click(function () {
//        $('[data-toggle="tooltip"]').tooltip('hide');
//        $('[data-toggle="tooltip"]').tooltip('dispose');
//        fncBtnGuardar();
//        var evaluaciones = {};
//        evaluaciones.evaluaciones = [];
//        evaluaciones.silabos = {'idSilabo': parseInt($("#idSilabo").val()), 'idTipo': parseInt($("#idTipo").val()), 'idUnidad': parseInt($("#idUnidad").val()), 'tipo': "Criterios", 'rol': $("#RolUsuario").val()};
//
//        var actividades = new Array();
//        $("td.actividades").each(
//                function () {
//                    actividades.push($(this).attr('id'));
//                });
//
//        var aportes = new Array();
//        $("td.aportes").each(
//                function () {
//                    aportes.push($(this).attr('id'));
//                });
//
//        var primerparcial = new Array();
//        $("select#a").each(
//                function () {
//                    primerparcial.push($(this).val());
//                }
//        );
//
//        var segundoparcial = new Array();
//        $("select#a1").each(
//                function () {
//                    segundoparcial.push($(this).val());
//                }
//        );
//        var tercerparcial = new Array();
//        $("select#a2").each(
//                function () {
//                    tercerparcial.push($(this).val());
//                }
//        );
//
//        for (var i in actividades) {
//
//            if (primerparcial[i] !== '0')
//            {
//                var evaluacion1 = {};
//                evaluacion1.actividad = actividades[i];
//                evaluacion1.aporte = aportes[0];
//                evaluacion1.nota = primerparcial[i];
//                evaluaciones.evaluaciones.push(evaluacion1);
//            }
//
//            if (segundoparcial[i] !== '0')
//            {
//                var evaluacion2 = {};
//                evaluacion2.actividad = actividades[i];
//                evaluacion2.aporte = aportes[1];
//                evaluacion2.nota = segundoparcial[i];
//                evaluaciones.evaluaciones.push(evaluacion2);
//            }
//            if (tercerparcial[i] !== '0')
//            {
//                var evaluacion3 = {};
//                evaluacion3.actividad = actividades[i];
//                evaluacion3.aporte = aportes[2];
//                evaluacion3.nota = tercerparcial[i];
//                evaluaciones.evaluaciones.push(evaluacion3);
//            }
//        }
//        var evaluacion4 = {};
//        evaluacion4.actividad = 1;
//        evaluacion4.aporte = aportes[3];
//        evaluacion4.nota = $("#NP").val();
//        evaluaciones.evaluaciones.push(evaluacion4);
//        var evaluacion5 = {};
//        evaluacion5.actividad = 1;
//        evaluacion5.aporte = aportes[4];
//        evaluacion5.nota = $("#NS").val();
//        evaluaciones.evaluaciones.push(evaluacion5);
//        if ($("#RolUsuario").val() !== 'Doc' || $("#idObservacion").val() !== undefined) {
//            evaluaciones.observaciones = [];
//            var observacion = {};
//            var observacionText = $("#txtObservaciones").val();
//            observacionText = escape(observacionText);
//            observacion.observacion = observacionText;
//            observacion.id_observacion = $("#idObservacion").val();
//            evaluaciones.observaciones.push(observacion);
//        }
//
//        var datos = JSON.stringify(evaluaciones);
//        $.ajax({
//            url: "Silabo/4CriteriosEvaluaciones/CriteriosEvaluacionesControlador.jsp",
//            type: "POST",
//            dataType: "text",
//            data: {jsonEvaluaciones: datos, opcion: "saveEvaluaciones"},
//            success: function (datos) {
//                var data = JSON.parse(datos);
//                $("#contenidoDinamico").html(data.contenidoDinamico);
//                $("#contenidoDinamico").append(data.contenidoObservacion);
//                $("#contenidoTitulo").html(data.contenidoTitulo);
//                $("#contenidoMenuLateral").html(data.contenidoMenuLateral);
//                $("#treeview").shieldTreeView();
//                var textoOb = $("#txtObservaciones").val();
//                textoOb = unescape(textoOb);
//                $("#txtObservaciones").val(textoOb);
//                fncInitLnkAyuda(data.lnkAyuda);
//                _fncBtnGuardarOK();
//            },
//            error: function (jqXHR, textStatus, errorThrown) {
//                _fncBtnGuardarKO();
//            }
//
//        });
//    });
//});

function cambiosColumna(objeto) {
    var valor = 0;
    $("select#a").each(
            function () {
                valor += parseInt($(this).val());
            }
    );
    if (valor > 8) {
        swal("Máxima nota: 8\n");
        valor = valor - $(objeto).val();
        $(objeto).val(0);
    }
    $("#parcial1").val(valor);
    $("#btnGuardar").attr("disabled", false);
}
function cambiosColumna1(objeto) {
    var valor2 = 0;
    $("select#a1").each(
            function () {
                valor2 += parseInt($(this).val());
            }
    );
    if (valor2 > 10) {
        swal("Máxima nota: 10\n");
        valor2 = valor2 - $(objeto).val();
        $(objeto).val(0);

    }
    $("#parcial2").val(valor2);
    $("#btnGuardar").attr("disabled", false);
}
function cambiosColumna2(objeto) {
    var valor3 = 0;
    $("select#a2").each(
            function () {
                valor3 += parseInt($(this).val());
            }
    );
    if (valor3 > 10) {
        swal("Máxima nota: 10\n");
        valor3 = valor3 - $(objeto).val();
        $(objeto).val(0);
    }
    $("#parcial3").val(valor3);
    $("#btnGuardar").attr("disabled", false);
}

function gEvaluaciones(objeto) {
    $('[data-toggle="tooltip"]').tooltip('hide');
    fncBtnGuardar();
    var evaluaciones = {};
    evaluaciones.evaluaciones = [];
    evaluaciones.silabos = {'tipoSeccion': 'seccion', 'idSilabo': parseInt($("#idSilabo").val()), 'codCarrera': 'null', 'idTipo': parseInt($("#idTipo").val()), 'idUnidad': parseInt($("#idUnidad").val()), 'tipo': "Criterios", 'rol': $("#RolUsuario").val()};

    var actividades = new Array();
    $("td.actividades").each(
            function () {
                actividades.push($(this).attr('id'));
            });

    var aportes = new Array();
    $("td.aportes").each(
            function () {
                aportes.push($(this).attr('id'));
            });

    var primerparcial = new Array();
    $("select#a").each(
            function () {
                primerparcial.push($(this).val());
            }
    );

    var segundoparcial = new Array();
    $("select#a1").each(
            function () {
                segundoparcial.push($(this).val());
            }
    );
    var tercerparcial = new Array();
    $("select#a2").each(
            function () {
                tercerparcial.push($(this).val());
            }
    );

    for (var i in actividades) {

        if (primerparcial[i] !== '0')
        {
            var evaluacion1 = {};
            evaluacion1.actividad = actividades[i];
            evaluacion1.aporte = aportes[0];
            evaluacion1.nota = primerparcial[i];
            evaluaciones.evaluaciones.push(evaluacion1);
        }

        if (segundoparcial[i] !== '0')
        {
            var evaluacion2 = {};
            evaluacion2.actividad = actividades[i];
            evaluacion2.aporte = aportes[1];
            evaluacion2.nota = segundoparcial[i];
            evaluaciones.evaluaciones.push(evaluacion2);
        }
        if (tercerparcial[i] !== '0')
        {
            var evaluacion3 = {};
            evaluacion3.actividad = actividades[i];
            evaluacion3.aporte = aportes[2];
            evaluacion3.nota = tercerparcial[i];
            evaluaciones.evaluaciones.push(evaluacion3);
        }
    }
    var evaluacion4 = {};
    evaluacion4.actividad = $("#Ex").attr('data-id');
    evaluacion4.aporte = aportes[3];
    evaluacion4.nota = $("#NP").val();
    evaluaciones.evaluaciones.push(evaluacion4);
    var evaluacion5 = {};
    evaluacion5.actividad = $("#Ex").attr('data-id');
    evaluacion5.aporte = aportes[4];
    evaluacion5.nota = $("#NS").val();
    evaluaciones.evaluaciones.push(evaluacion5);
    if ($("#RolUsuario").val() !== 'Doc' || $("#idObservacion").val() !== undefined) {
        evaluaciones.observaciones = [];
        var observacion = {};
        var observacionText = $("#txtObservaciones").val();
        observacionText = escape(observacionText);
        observacion.observacion = observacionText;
        observacion.id_observacion = $("#idObservacion").val();
        evaluaciones.observaciones.push(observacion);
    }

    var datos = JSON.stringify(evaluaciones);
    $.ajax({
        url: "Silabo/4CriteriosEvaluaciones/CriteriosEvaluacionesControlador.jsp",
        type: "POST",
        dataType: "text",
        data: {jsonEvaluaciones: datos, opcion: "saveEvaluaciones"},
        success: function (datos) {
            var data = JSON.parse(datos);
            $("#contenidoDinamico").html(data.contenidoDinamico);
            $("#contenidoTitulo").html(data.contenidoTitulo);
            $("#contenidoMenuLateral").html(data.contenidoMenuLateral);
            $("#treeview").shieldTreeView();
            agregarObservacionesContenido(JSON.stringify(evaluaciones.silabos));
            fncInitLnkAyuda(data.lnkAyuda);
            _fncBtnGuardarOK();
        },
        error: function (jqXHR, textStatus, errorThrown) {
            _fncBtnGuardarKO();
        }

    });
}