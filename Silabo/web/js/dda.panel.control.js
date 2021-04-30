/* global google */

var elementos;

function pcDibujar(reporte) {
    if (reporte !== null) {
        elementos = reporte.elementos;
        // loading...
        $("#titulo").html(reporte.titulo);
        $("#subtitulo").html("");
        var contenido;
        if (reporte.fncClick === undefined) {
            contenido = reporte.subtitulo;
        } else {
            if (reporte.subtitulo !== undefined) {
                contenido = "<span onclick=" + reporte.fncClick + "><i class='fa fa-level-up' aria-hidden='true'></i></span>  " + reporte.subtitulo + "";
            } else {
                contenido = "<span onclick=" + reporte.fncClick + "><i class='fa fa-level-up' aria-hidden='true'></i></span> Retroceder";
                $("#contenidoPie").html("<i class='fa fa-exclamation-triangle' style='color:gold!important' aria-hidden='true'></i>&nbsp;&nbsp;|&nbsp;&nbsp;Reporte no disponible por el momento. Intentelo mas tarde....");
            }

        }
        $("#contenidoTitulo").html(contenido);
        // fncInitLnkAyuda(reporte.ayuda);

        google.charts.load('current', {'packages': ['corechart']});
        google.charts.setOnLoadCallback(_pcDibujarElementos);
    } else {
        errorReporte("Reporte no disponible");
    }
}

function _pcDibujarElementos() {
    // NO loading....
    var contenidoDinamico = $("#contenidoDinamico").html("");
    var dda_titulo_grafico = $("<header>").addClass("text-small-caps").appendTo(contenidoDinamico);
    dda_titulo_grafico.attr("id", "tituloestado"); 
    dda_titulo_grafico.text("ESTADO DE LOS SÍLABOS"); 
    var dda_pc = $("<article>").addClass("dda-pc").appendTo(contenidoDinamico);
    var dda_pc_fila = "";

    $.each(elementos, function (i, elemento) {
        dda_pc_fila = (i % 3 === 0) ? $("<section>").addClass("dda-pc-fila").appendTo(dda_pc) : dda_pc_fila;

        var dda_pc_elemento = $("<article>").addClass("dda-pc-elemento").appendTo(dda_pc_fila);
        var dda_pc_elemento_titulo = $("<header>").addClass("dda-pc-elemento-titulo").appendTo(dda_pc_elemento);
        dda_pc_elemento_titulo.text(elemento.nombre);
        var dda_pc_elemento_contenido = $("<section id='grafico' onclick=" + elemento.fncClick + ">").addClass("dda-pc-elemento-contenido").appendTo(dda_pc_elemento);
        dda_pc_elemento_contenido.attr("id", elemento.codigo);
        // crear el gráfico
        var datos = elemento.datos;
        _pcDibujarElemento(dda_pc_elemento_contenido[0], datos);
        //
        var dda_pc_elemento_pie = $("<footer>").addClass("dda-pc-elemento-pie").appendTo(dda_pc_elemento);
        var dda_pc_elemento_pie_mas = $("<div id='pdf' tittle='Generar Detalle' onclick=" + elemento.fncClick + ">").addClass("dda-link dda-pc-elemento-pie-mas").appendTo(dda_pc_elemento_pie);
//        dda_pc_elemento_pie_mas.click(elemento.fncClick);
//        dda_pc_elemento_pie_mas.on("click", function () {
//            elemento.fncClick;
//        });
        $("<i>").addClass("dda-pc-elemento-pie-mas-icono fa fa-file-pdf-o fa-2x").appendTo(dda_pc_elemento_pie_mas);

    });
}

function _pcDibujarElemento(dda_pc_elemento_contenido, datos) {
    var dataTable = _pcDataTableCreate(datos);
    var data = google.visualization.arrayToDataTable(dataTable);
    var options = _pcOptionsCreate();
    var chart = new google.visualization.PieChart(dda_pc_elemento_contenido);
    chart.draw(data, options);
}

function _pcDataTableCreate(datos) {
    var dataTable = [];
    try {
        var conceptos = Object.keys(datos);
        var valores = Object.values(datos);
        var dataTableElement = ["Concepto", "Valor"];
        dataTable.push(dataTableElement);
        $.each(conceptos, function (i, concepto) {
            var dataTableElement = [concepto, valores[i]];
            dataTable.push(dataTableElement);
        });
    } catch (ex) {
        console.log(ex.message);
    }
    return dataTable;
}

function _pcOptionsCreate() {
    var options = {};
    try {
        options = {
            title: "",
            titleTextStyle: {bold: true, fontSize: 12},
            legend: {position: 'bottom', textStyle: {color: 'rgb(27, 38, 49)', fontSize: 9}},
            pieSliceText: 'none',
            labels: 'none',
            tooltip: {textStyle: {color: 'rgb(27, 38, 49)'}, showColorCode: true},
            pieStartAngle: 0,
            colors: ['rgb(137,174,197)', 'rgb(248,153,35)', 'rgb(62,119,156)', 'rgb(0,69,107)'],
            pieSliceBorderColor: 'rgb(204,204,204)',
            //is3D: true
            pieHole: 0.55,
            chartArea: {width: '100%', height: '100%', bottom: 15}
        };
    } catch (ex) {
        console.log(ex.message);
    }
    return options;
}
