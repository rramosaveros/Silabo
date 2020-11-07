/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dda.silabo.reportes.pdf.iu;

import com.google.gson.Gson;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.html.WebColors;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import dda.silabo.clases.unidos.AsignaturaUnidos;
import dda.silabo.clases.unidos.CarreraUnidos;
import dda.silabo.clases.unidos.DocenteUnidos;
import dda.silabo.clases.unidos.EscuelaUnidos;
import dda.silabo.clases.unidos.FacultadUnidos;
import dda.silabo.datosdocentes.comunes.DatoDocente;
import dda.silabo.reportes.comunes.CriterioSilabo;
import dda.silabo.reportes.comunes.Elemento;
import dda.silabo.reportes.comunes.Estados;
import dda.silabo.reportes.comunes.ReporteComun;
import ec.edu.espoch.academico.Periodo;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.util.Base64;
import javax.print.Doc;
import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.swing.ImageIcon;
import org.knowm.xchart.BitmapEncoder;
import org.knowm.xchart.PieChartBuilder;
import org.knowm.xchart.style.PieStyler;
import org.w3c.dom.Node;

/**
 *
 * @author Jorge Zaruma
 */
public class ReportePDFIU extends ReporteComun {

    public String generarEntidadDetallePDF() {
        String resultado = "";
        try {
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
            Document doc = new Document();
            ByteArrayOutputStream archivo = new ByteArrayOutputStream();
            PdfWriter writer = PdfWriter.getInstance(doc, archivo);

            doc.open();
            doc.setPageSize(PageSize.A4.rotate());
            doc.newPage();

/////////////////////HEADER///////////////////// 
            PdfPTable header = new PdfPTable(3);
            header.setWidthPercentage(100f);
            PdfPCell espoch = new PdfPCell();
            byte[] logoEspoch = null;
            String fotoEspoch = logoEntidad("ESPOCH", "Instituto");
            if ((fotoEspoch != null)) {
                logoEspoch = Base64.getDecoder().decode(fotoEspoch);
                Image imgespoch = Image.getInstance(logoEspoch);
//                Image imgespoch = Image.getInstance("C:\\Pictures\\PDF\\logo.png");
                imgespoch.scaleToFit(60, 60);
                espoch.setVerticalAlignment(Element.ALIGN_CENTER);
                espoch.setHorizontalAlignment(Element.ALIGN_CENTER);
                espoch.addElement(imgespoch);
            } else {
                espoch.addElement(new Paragraph("Logo no Disponible"));
            }

            espoch.setBorder(0);
            PdfPTable titulos = new PdfPTable(1);
            Periodo periodoActual = periodoActual();
            String periododefinido = periodoActual.getDescripcion();
            if (periododefinido == null) {
                periododefinido = "NO DEFINIDO";
            }
            Paragraph entidad = new Paragraph("ESCUELA SUPERIOR POLITÉCNICA DE CHIMBORAZO\n", obtenerfuentePDF("institucionFuente"));
            Paragraph tipoReporte = new Paragraph(this.getAyuda(), obtenerfuentePDF("tituloreporteFuente"));
            Paragraph periodo = new Paragraph("PERIODO ACADÉMICO:  " + periododefinido, obtenerfuentePDF("periodoFuente"));
            PdfPCell celentidad = new PdfPCell(entidad);
            PdfPCell cellreporte = new PdfPCell(tipoReporte);
            PdfPCell cellperiodo = new PdfPCell(periodo);

            celentidad.setBorder(0);
            celentidad.setVerticalAlignment(Element.ALIGN_CENTER);
            celentidad.setHorizontalAlignment(Element.ALIGN_CENTER);
            cellreporte.setBorder(0);
            cellreporte.setVerticalAlignment(Element.ALIGN_CENTER);
            cellreporte.setHorizontalAlignment(Element.ALIGN_CENTER);
            cellperiodo.setBorder(0);
            cellperiodo.setVerticalAlignment(Element.ALIGN_CENTER);
            cellperiodo.setHorizontalAlignment(Element.ALIGN_CENTER);

            titulos.addCell(celentidad);
            titulos.addCell(cellreporte);
            titulos.addCell(cellperiodo);

            PdfPCell titulo = new PdfPCell(titulos);
            titulo.setBorder(0);
            titulo.setVerticalAlignment(Element.ALIGN_CENTER);
            titulo.setHorizontalAlignment(Element.ALIGN_CENTER);

            PdfPCell facultad = new PdfPCell();
            byte[] logofacultad = null;
            String fotoFacultad = logoEntidad("EIS", "Carrera");
            if ((fotoFacultad != null)) {
                logofacultad = Base64.getDecoder().decode(fotoFacultad);
                Image imgfacultad = Image.getInstance(logofacultad);
//                Image imgfacultad = Image.getInstance("C:\\Pictures\\PDF\\fie.png");
                imgfacultad.scaleToFit(80, 80);
                facultad.setVerticalAlignment(Element.ALIGN_CENTER);
                facultad.setHorizontalAlignment(Element.ALIGN_CENTER);
                facultad.addElement(imgfacultad);
            } else {
                facultad.addElement(new Paragraph("Logo no Disponible"));
            }

            facultad.setBorder(0);
            header.addCell(espoch);
            header.addCell(titulo);
            header.addCell(facultad);
            float[] header1 = {15f, 70f, 15f};
            header.setWidths(header1);
            doc.add(header);
            doc.add(new Paragraph("\n"));

/////////////////////TITULO DE LA ENTIDAD/////////////////////  
            PdfPTable tablaentidad = new PdfPTable(1);
            tablaentidad.setWidthPercentage(100f);
            PdfPCell cellentidad = new PdfPCell(new Paragraph(this.getTitulo(), obtenerfuentePDF("reportentidadFuente")));
            cellentidad.setFixedHeight(24f);
            cellentidad.setBackgroundColor(new BaseColor(30, 70, 130));
            cellentidad.setBorder(Rectangle.NO_BORDER);
            tablaentidad.addCell(cellentidad);
            tablaentidad.setHorizontalAlignment(Element.ALIGN_LEFT);
            doc.add(tablaentidad);
            doc.add(new Paragraph("\n"));
            doc.add(new Paragraph("\n"));

/////////////////////TABLA (DETALLE Y GRAFICO)/////////////////////
            PdfPTable tabla = new PdfPTable(3);
            tabla.getDefaultCell().setBorder(0);
            tabla.setWidthPercentage(100f);

//////////Tabla detalle
            PdfPTable tablaDetalle = new PdfPTable(1);
            PdfPTable tablareporte = new PdfPTable(6);
            tablaDetalle.getDefaultCell().setBorder(0);
            tablaDetalle.setTotalWidth(485);
            tablaDetalle.setLockedWidth(true);

            BaseColor colorborde = WebColors.getRGBColor("#8EAADB");
            BaseColor colorcelda = WebColors.getRGBColor("#1E4682");

            PdfPCell celdaentidad = new PdfPCell(new Paragraph(this.getSubtitulo(), obtenerfuentePDF("titulostablas")));
            celdaentidad.setFixedHeight(27f);
            celdaentidad.setVerticalAlignment(Element.ALIGN_CENTER);
            celdaentidad.setHorizontalAlignment(Element.ALIGN_CENTER);
            celdaentidad.setColspan(2);
            celdaentidad.setBackgroundColor(colorcelda);
            celdaentidad.setBorderColor(colorborde);

            Paragraph celdainicio = new Paragraph("INICIO", obtenerfuentePDF("titulostablas"));
            PdfPCell taminicio = new PdfPCell(celdainicio);
            taminicio.setVerticalAlignment(Element.ALIGN_CENTER);
            taminicio.setHorizontalAlignment(Element.ALIGN_CENTER);
            taminicio.setBackgroundColor(colorcelda);
            taminicio.setBorderColor(colorborde);

            Paragraph celdarevision = new Paragraph("REVISIÓN", obtenerfuentePDF("titulostablas"));
            PdfPCell tamrevision = new PdfPCell(celdarevision);
            tamrevision.setVerticalAlignment(Element.ALIGN_CENTER);
            tamrevision.setHorizontalAlignment(Element.ALIGN_CENTER);
            tamrevision.setBackgroundColor(colorcelda);
            tamrevision.setBorderColor(colorborde);

            Paragraph celdacorregir = new Paragraph("CORREGIR", obtenerfuentePDF("titulostablas"));
            PdfPCell tamcorregir = new PdfPCell(celdacorregir);
            tamcorregir.setVerticalAlignment(Element.ALIGN_CENTER);
            tamcorregir.setHorizontalAlignment(Element.ALIGN_CENTER);
            tamcorregir.setBackgroundColor(colorcelda);
            tamcorregir.setBorderColor(colorborde);

            Paragraph celdaaprobado = new Paragraph("APROBADO", obtenerfuentePDF("titulostablas"));
            PdfPCell tamaprobado = new PdfPCell(celdaaprobado);
            tamaprobado.setVerticalAlignment(Element.ALIGN_CENTER);
            tamaprobado.setHorizontalAlignment(Element.ALIGN_CENTER);
            tamaprobado.setBackgroundColor(colorcelda);
            tamaprobado.setBorderColor(colorborde);

            tablareporte.addCell(celdaentidad);
            tablareporte.addCell(taminicio);
            tablareporte.addCell(tamrevision);
            tablareporte.addCell(tamcorregir);
            tablareporte.addCell(tamaprobado);

            Integer contAprobado = 0, contRevision = 0, contCorregir = 0, contInicio = 0;

            for (int i = 0; i < this.getElementos().size(); i++) {

                Elemento escuela = this.getElementos().get(i);
                contAprobado += escuela.getDatos().getAprobado();
                contRevision += escuela.getDatos().getRevision();
                contCorregir += escuela.getDatos().getCorregir();
                contInicio += escuela.getDatos().getInicio();
                PdfPCell nombrescuelas = new PdfPCell(new Paragraph(escuela.getNombre(), obtenerfuentePDF("contenidotablaFuente")));
                PdfPCell inicio = new PdfPCell(new Paragraph(escuela.getDatos().getInicio().toString(), obtenerfuentePDF("contenidotablaFuente")));
                PdfPCell revision = new PdfPCell(new Paragraph(escuela.getDatos().getRevision().toString(), obtenerfuentePDF("contenidotablaFuente")));
                PdfPCell corregir = new PdfPCell(new Paragraph(escuela.getDatos().getCorregir().toString(), obtenerfuentePDF("contenidotablaFuente")));
                PdfPCell aprobado = new PdfPCell(new Paragraph(escuela.getDatos().getAprobado().toString(), obtenerfuentePDF("contenidotablaFuente")));
                inicio.setVerticalAlignment(Element.ALIGN_CENTER);
                inicio.setHorizontalAlignment(Element.ALIGN_CENTER);
                revision.setVerticalAlignment(Element.ALIGN_CENTER);
                revision.setHorizontalAlignment(Element.ALIGN_CENTER);
                corregir.setVerticalAlignment(Element.ALIGN_CENTER);
                corregir.setHorizontalAlignment(Element.ALIGN_CENTER);
                aprobado.setVerticalAlignment(Element.ALIGN_CENTER);
                aprobado.setHorizontalAlignment(Element.ALIGN_CENTER);
                nombrescuelas.setColspan(2);
                if (i % 2 == 1) {
                    nombrescuelas.setBackgroundColor(new BaseColor(217, 227, 234));
                    inicio.setBackgroundColor(new BaseColor(217, 227, 234));
                    revision.setBackgroundColor(new BaseColor(217, 227, 234));
                    corregir.setBackgroundColor(new BaseColor(217, 227, 234));
                    aprobado.setBackgroundColor(new BaseColor(217, 227, 234));
                }
                BaseColor mycolor = WebColors.getRGBColor("#8CB9D8");
                nombrescuelas.setBorderColor(mycolor);
                inicio.setBorderColor(mycolor);
                revision.setBorderColor(mycolor);
                corregir.setBorderColor(mycolor);
                aprobado.setBorderColor(mycolor);
                inicio.setVerticalAlignment(Element.ALIGN_CENTER);
                inicio.setHorizontalAlignment(Element.ALIGN_CENTER);
                revision.setVerticalAlignment(Element.ALIGN_CENTER);
                revision.setHorizontalAlignment(Element.ALIGN_CENTER);
                corregir.setVerticalAlignment(Element.ALIGN_CENTER);
                corregir.setHorizontalAlignment(Element.ALIGN_CENTER);
                aprobado.setVerticalAlignment(Element.ALIGN_CENTER);
                aprobado.setHorizontalAlignment(Element.ALIGN_CENTER);
                tablareporte.addCell(nombrescuelas);
                tablareporte.addCell(inicio);
                tablareporte.addCell(revision);
                tablareporte.addCell(corregir);
                tablareporte.addCell(aprobado);
            }
            PdfPCell totales = new PdfPCell(new Paragraph("TOTAL", obtenerfuentePDF("totaltablaFuente")));
            totales.setColspan(2);
            PdfPCell inicio = new PdfPCell(new Paragraph(contInicio.toString(), obtenerfuentePDF("contenidotablaFuente")));
            PdfPCell revision = new PdfPCell(new Paragraph(contRevision.toString(), obtenerfuentePDF("contenidotablaFuente")));
            PdfPCell corregir = new PdfPCell(new Paragraph(contCorregir.toString(), obtenerfuentePDF("contenidotablaFuente")));
            PdfPCell aprobado = new PdfPCell(new Paragraph(contAprobado.toString(), obtenerfuentePDF("contenidotablaFuente")));

            BaseColor mycolor = WebColors.getRGBColor("#8CB9D8");
            totales.setBorderColor(mycolor);
            inicio.setBorderColor(mycolor);
            inicio.setVerticalAlignment(Element.ALIGN_CENTER);
            inicio.setHorizontalAlignment(Element.ALIGN_CENTER);
            revision.setBorderColor(mycolor);
            revision.setVerticalAlignment(Element.ALIGN_CENTER);
            revision.setHorizontalAlignment(Element.ALIGN_CENTER);
            corregir.setBorderColor(mycolor);
            corregir.setVerticalAlignment(Element.ALIGN_CENTER);
            corregir.setHorizontalAlignment(Element.ALIGN_CENTER);
            aprobado.setBorderColor(mycolor);
            aprobado.setVerticalAlignment(Element.ALIGN_CENTER);
            aprobado.setHorizontalAlignment(Element.ALIGN_CENTER);
            tablareporte.addCell(totales);
            tablareporte.addCell(inicio);
            tablareporte.addCell(revision);
            tablareporte.addCell(corregir);
            tablareporte.addCell(aprobado);

//////////GRAFICO           
//            org.knowm.xchart.PieChart chart = new PieChartBuilder().width(350).height(350).title(this.getNombre()).build();
            org.knowm.xchart.PieChart chart = new PieChartBuilder().width(350).height(350).build();
            PdfPTable tablaGraf = new PdfPTable(1);
            tablaGraf.setWidthPercentage(30f);
            Color[] colors = new Color[]{new Color(137, 174, 197), new Color(62, 119, 156), new Color(0, 69, 107), new Color(248, 153, 35)};
            chart.getStyler().setPlotBorderVisible(false);
            chart.getStyler().setLegendVisible(false);
            chart.getStyler().setAnnotationType(PieStyler.AnnotationType.Percentage);
            chart.getStyler().setAnnotationDistance(1.1);
            chart.getStyler().setStartAngleInDegrees(20);
            chart.getStyler().setSeriesColors(colors);
            chart.getStyler().setChartTitleBoxBackgroundColor(Color.BLACK);
            chart.getStyler().getChartTitleFont();
            chart.getStyler().setChartBackgroundColor(Color.WHITE);
            chart.getStyler().setPlotContentSize(0.7);
            chart.getStyler().setPlotBorderColor(Color.GRAY);
            // Series
            chart.addSeries("Inicio", contInicio);
            chart.addSeries("Revisión", contRevision);
            chart.addSeries("Corregir", contCorregir);
            chart.addSeries("Aprobados", contAprobado);

//////////Guardar el diagrama pastel como img
            // Save it
            BitmapEncoder.saveBitmap(chart, "./Sample_Chart", BitmapEncoder.BitmapFormat.PNG);
            BitmapEncoder.saveBitmapWithDPI(chart, "./Sample_Chart_300_DPI", BitmapEncoder.BitmapFormat.PNG, 50);

            java.awt.Image image;
            BufferedImage deck = BitmapEncoder.getBufferedImage(chart);
            // or save it in high-res
            image = deck;
            ImageIcon format = new ImageIcon(image);
            java.awt.Image awtImage = format.getImage();
            com.itextpdf.text.Image finalIm = com.itextpdf.text.Image.getInstance(awtImage, null);

//////////Tabla Leyenda          
            PdfPTable tablaleyenda = new PdfPTable(4);
            tablaleyenda.setTotalWidth(257);
            tablaleyenda.setLockedWidth(true);
            tablaleyenda.setHorizontalAlignment(Element.ALIGN_LEFT);
            tablaleyenda.getDefaultCell().setBorder(0);

            PdfPTable tablalyinicio = new PdfPTable(2);
            PdfPTable tablalyrevision = new PdfPTable(2);
            PdfPTable tablalycorregir = new PdfPTable(2);
            PdfPTable tablalyaprobado = new PdfPTable(2);

            float[] tamañoly = {0.1f, 0.4f};
            tablalyinicio.setWidths(tamañoly);
            tablalyrevision.setWidths(tamañoly);
            tablalycorregir.setWidths(tamañoly);
            tablalyaprobado.setWidths(tamañoly);

            PdfPCell lyinicio = new PdfPCell(new Paragraph("Inicio", obtenerfuentePDF("leyendaFuente")));
            PdfPCell lyrevision = new PdfPCell(new Paragraph("Revisión", obtenerfuentePDF("leyendaFuente")));
            PdfPCell lycorregir = new PdfPCell(new Paragraph("Corregir", obtenerfuentePDF("leyendaFuente")));
            PdfPCell lyaprobado = new PdfPCell(new Paragraph("Aprobado", obtenerfuentePDF("leyendaFuente")));

            PdfPCell colori = new PdfPCell();
            PdfPCell colorr = new PdfPCell();
            PdfPCell colorc = new PdfPCell();
            PdfPCell colora = new PdfPCell();

            lyinicio.setBorder(Rectangle.NO_BORDER);
            lyrevision.setBorder(Rectangle.NO_BORDER);
            lycorregir.setBorder(Rectangle.NO_BORDER);
            lyaprobado.setBorder(Rectangle.NO_BORDER);
            colori.setBorder(Rectangle.NO_BORDER);
            colorr.setBorder(Rectangle.NO_BORDER);
            colorc.setBorder(Rectangle.NO_BORDER);
            colora.setBorder(Rectangle.NO_BORDER);

            colori.setBackgroundColor(new BaseColor(137, 174, 197));
            colorr.setBackgroundColor(new BaseColor(62, 119, 156));
            colorc.setBackgroundColor(new BaseColor(0, 69, 107));
            colora.setBackgroundColor(new BaseColor(248, 153, 35));

            tablalyinicio.addCell(colori);
            tablalyinicio.addCell(lyinicio);
            tablalyrevision.addCell(colorr);
            tablalyrevision.addCell(lyrevision);
            tablalycorregir.addCell(colorc);
            tablalycorregir.addCell(lycorregir);
            tablalyaprobado.addCell(colora);
            tablalyaprobado.addCell(lyaprobado);

            tablaleyenda.addCell(tablalyinicio);
            tablaleyenda.addCell(tablalyrevision);
            tablaleyenda.addCell(tablalycorregir);
            tablaleyenda.addCell(tablalyaprobado);

            PdfPCell tituloGrafico = new PdfPCell(new Paragraph(this.getTitulo(), obtenerfuentePDF("periodoFuente")));
            tituloGrafico.setBorderColor(new BaseColor(204, 204, 204));
            tituloGrafico.setHorizontalAlignment(Element.ALIGN_CENTER);
            tituloGrafico.setVerticalAlignment(Element.ALIGN_CENTER);
/////////////////////TABLA GENERALES ///////////////////// 
            tablaDetalle.setHorizontalAlignment(Element.ALIGN_LEFT);
            tablaDetalle.addCell(tablareporte);

            tablaGraf.setHorizontalAlignment(Element.ALIGN_RIGHT);
            tablaGraf.getDefaultCell().setBorderColor(new BaseColor(204, 204, 204));
            tablaGraf.addCell(tituloGrafico);
            tablaGraf.addCell(finalIm);
            tablaGraf.addCell(tablaleyenda);

            PdfPCell celda_detalle = new PdfPCell(tablaDetalle);//tabla del detalle a la cela de la tabla general
            celda_detalle.setColspan(2);
            celda_detalle.setBorder(Rectangle.NO_BORDER);
            celda_detalle.setVerticalAlignment(Element.ALIGN_RIGHT);
            celda_detalle.setHorizontalAlignment(Element.ALIGN_RIGHT);

            PdfPCell celda_grafico = new PdfPCell(tablaGraf);//tabla del grafico a la cela de la tabla general
            celda_grafico.setBorder(Rectangle.NO_BORDER);
            celda_grafico.setVerticalAlignment(Element.ALIGN_LEFT);
            celda_grafico.setHorizontalAlignment(Element.ALIGN_LEFT);

            tabla.addCell(celda_detalle);
            tabla.addCell(celda_grafico);
            doc.add(tabla);
            doc.close();
            byte[] pdfBytes = archivo.toByteArray();
            String base64Encoded = Base64.getEncoder().encodeToString(pdfBytes);
            resultado = base64Encoded;
        } catch (Exception e) {
        }
        return resultado;
    }

    public String generarCriteriosSilaboPDF() {
        String resultado = "";
        try {
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
            Document doc = new Document();
            ByteArrayOutputStream archivo = new ByteArrayOutputStream();
            PdfWriter writer = PdfWriter.getInstance(doc, archivo);

            doc.open();
            doc.newPage();
////////////////////////////////////////////////////////
            PdfPTable header = new PdfPTable(3);
            header.setHorizontalAlignment(Element.ALIGN_CENTER);
            header.setWidthPercentage(100f);

            PdfPCell espoch = new PdfPCell();
            byte[] logoEspoch = null;
            String fotoEspoch = logoEntidad("ESPOCH", "Instituto");
            if ((fotoEspoch != null)) {
                logoEspoch = Base64.getDecoder().decode(fotoEspoch);
                Image imgespoch = Image.getInstance(logoEspoch);
//                Image imgespoch = Image.getInstance("C:\\Pictures\\PDF\\logo.png");
                imgespoch.scaleToFit(60, 60);
                espoch.setVerticalAlignment(Element.ALIGN_CENTER);
                espoch.setHorizontalAlignment(Element.ALIGN_CENTER);
                espoch.addElement(imgespoch);
            } else {
                espoch.addElement(new Paragraph("Logo no Disponible"));
            }

            espoch.setBorder(0);
            Periodo periodoActual = periodoActual();
            String periodoDefinido = periodoActual.getDescripcion();
            if (periodoDefinido == null) {
                periodoDefinido = "NO DEFINIDO";
            }
            PdfPCell facultad = new PdfPCell();
            byte[] logofacultad = null;
            FacultadUnidos f = this.getFacultades().stream().findFirst().orElse(null);
            String codFacultad = "FIE";
            if (f != null) {
                codFacultad = f.getCodFacultad();
            }
            String fotoFacultad = logoEntidad(codFacultad, "Facultad");
            if ((fotoFacultad != null)) {
                logofacultad = Base64.getDecoder().decode(fotoFacultad);
                Image imgfacultad = Image.getInstance(logofacultad);
//                Image imgfacultad = Image.getInstance("C:\\Pictures\\PDF\\fie.png");
                imgfacultad.scaleToFit(80, 80);
                facultad.setVerticalAlignment(Element.ALIGN_CENTER);
                facultad.setHorizontalAlignment(Element.ALIGN_CENTER);
                facultad.addElement(imgfacultad);
            } else {
                facultad.addElement(new Paragraph("Logo no Disponible"));
            }

            facultad.setBorder(0);
            Paragraph entidad = new Paragraph("ESCUELA SUPERIOR POLITÉCNICA DE CHIMBORAZO\n", obtenerfuentePDF("institucionFuenteB"));
            Paragraph tipoReporte = new Paragraph(this.getAyuda() + "\n", obtenerfuentePDF("tituloreporteFuenteB"));
            Paragraph periodo = new Paragraph("PERÍODO ACADÉMICO:  " + periodoDefinido, obtenerfuentePDF("periodoFuente"));
            PdfPTable titulos = new PdfPTable(1);
            PdfPCell celentidad = new PdfPCell(entidad);
            PdfPCell cellreporte = new PdfPCell(tipoReporte);
            PdfPCell cellperiodo = new PdfPCell(periodo);

            celentidad.setBorder(0);
            celentidad.setVerticalAlignment(Element.ALIGN_CENTER);
            celentidad.setHorizontalAlignment(Element.ALIGN_CENTER);
            cellreporte.setBorder(0);
            cellreporte.setVerticalAlignment(Element.ALIGN_CENTER);
            cellreporte.setHorizontalAlignment(Element.ALIGN_CENTER);
            cellperiodo.setBorder(0);
            cellperiodo.setVerticalAlignment(Element.ALIGN_CENTER);
            cellperiodo.setHorizontalAlignment(Element.ALIGN_CENTER);

            titulos.addCell(celentidad);
            titulos.addCell(cellreporte);
            titulos.addCell(cellperiodo);

            PdfPCell titulo = new PdfPCell(titulos);
            titulo.setBorder(0);
            titulo.setVerticalAlignment(Element.ALIGN_CENTER);
            titulo.setHorizontalAlignment(Element.ALIGN_CENTER);

            header.addCell(espoch);
            header.addCell(titulo);
            header.addCell(facultad);
            float[] header1 = {13f, 74f, 13f};
            header.setWidths(header1);
            doc.add(header);
            doc.add(new Paragraph("\n"));

///////////////////////////////TITULO SECCION////////////////////
            PdfPTable tablaentidad = new PdfPTable(1);
            tablaentidad.setWidthPercentage(100f);
            PdfPCell cellentidad = new PdfPCell(new Paragraph(this.getSubtitulo(), obtenerfuentePDF("reportseccionFuenteB")));
            cellentidad.setFixedHeight(24f);
            cellentidad.setBackgroundColor(new BaseColor(30, 70, 130));
            cellentidad.setBorder(Rectangle.NO_BORDER);
            tablaentidad.addCell(cellentidad);
            tablaentidad.setHorizontalAlignment(Element.ALIGN_LEFT);
            doc.add(tablaentidad);

//////////////////////////////TABLA BIBLIOGRAFIA////////////////////            
            Paragraph descrip = new Paragraph("", obtenerfuentePDF("contenidotablaFuenteB"));
            doc.add(descrip);
            float[] tamanioCeldas = {5f, 80f, 15f};
            PdfPTable tablareporte = new PdfPTable(3);
            tablareporte.setWidths(tamanioCeldas);
            tablareporte.setWidthPercentage(100f);

            doc.add(new Paragraph("\n"));

            BaseColor colorborde = WebColors.getRGBColor("#8EAADB");
            BaseColor colorcelda = WebColors.getRGBColor("#1E4682");
            if (this.getFacultades().size() > 0) {
                for (int i = 0; i < this.getFacultades().size(); i++) {
                    FacultadUnidos facultadComun = this.getFacultades().get(i);
                    PdfPCell espaciotb2 = new PdfPCell(new Paragraph(""));
                    espaciotb2.setBorder(0);
                    espaciotb2.setColspan(3);
                    if (i != 0) {
                        espaciotb2.setFixedHeight(10f);
                    }
                    tablareporte.addCell(espaciotb2);
                    PdfPCell espaciotbFacultad = new PdfPCell(new Paragraph(facultadComun.getNombre(), obtenerfuentePDF("tituloentidadFuenteB")));
                    espaciotbFacultad.setVerticalAlignment(Element.ALIGN_CENTER);
                    espaciotbFacultad.setFixedHeight(22f);
                    espaciotbFacultad.setBorder(0);
                    espaciotbFacultad.setColspan(3);
                    tablareporte.addCell(espaciotbFacultad);
                    for (int j = 0; j < facultadComun.getEscuelas().size(); j++) {
                        EscuelaUnidos escuelaComun = facultadComun.getEscuelas().get(j);
                        espaciotb2 = new PdfPCell(new Paragraph(""));
                        if (j != 0) {
                            espaciotb2.setFixedHeight(10f);
                        }
                        espaciotb2.setBorder(0);
                        espaciotb2.setColspan(3);
                        tablareporte.addCell(espaciotb2);
                        PdfPCell espaciotbEscuela = new PdfPCell(new Paragraph(escuelaComun.getNombre(), obtenerfuentePDF("tituloentidadFuenteB")));
                        espaciotbEscuela.setFixedHeight(22f);
                        espaciotbEscuela.setVerticalAlignment(Element.ALIGN_CENTER);
                        espaciotbEscuela.setIndent(25);
                        espaciotbEscuela.setBorder(0);
                        espaciotbEscuela.setColspan(3);
                        tablareporte.addCell(espaciotbEscuela);
                        for (int ka = 0; ka < escuelaComun.getCarreras().size(); ka++) {
                            CarreraUnidos carreraComun = escuelaComun.getCarreras().get(ka);
                            espaciotb2 = new PdfPCell(new Paragraph(""));
                            espaciotb2.setBorder(0);
                            espaciotb2.setColspan(3);
                            if (ka != 0) {
                                espaciotb2.setFixedHeight(10f);
                            }
                            tablareporte.addCell(espaciotb2);

                            PdfPCell espaciotbCarrera = new PdfPCell(new Paragraph(carreraComun.getNombre(), obtenerfuentePDF("tituloentidadFuenteB")));
                            espaciotbCarrera.setFixedHeight(22f);
                            espaciotbCarrera.setVerticalAlignment(Element.ALIGN_CENTER);
                            espaciotbCarrera.setIndent(50);
                            espaciotbCarrera.setBorder(0);
                            espaciotbCarrera.setColspan(3);
                            tablareporte.addCell(espaciotbCarrera);

                            PdfPCell celdaContador = new PdfPCell(new Paragraph("N°", obtenerfuentePDF("titulostablasB")));
                            celdaContador.setFixedHeight(24f);
                            celdaContador.setBackgroundColor(colorcelda);
                            celdaContador.setBorderColor(colorborde);
//                        tablareporte.addCell(celdaContador);

                            PdfPCell celdaDescripcion = new PdfPCell(new Paragraph("DESCRIPCIÓN", obtenerfuentePDF("titulostablasB")));
                            celdaDescripcion.setVerticalAlignment(Element.ALIGN_CENTER);
                            celdaDescripcion.setHorizontalAlignment(Element.ALIGN_CENTER);
                            celdaDescripcion.setBackgroundColor(colorcelda);
                            celdaDescripcion.setBorderColor(colorborde);
//                        tablareporte.addCell(celdaDescripcion);

                            PdfPCell celdaCantidad = new PdfPCell(new Paragraph("CANTIDAD", obtenerfuentePDF("titulostablasB")));
                            celdaCantidad.setVerticalAlignment(Element.ALIGN_CENTER);
                            celdaCantidad.setHorizontalAlignment(Element.ALIGN_CENTER);
                            celdaCantidad.setBackgroundColor(colorcelda);
                            celdaCantidad.setBorderColor(colorborde);
//                        tablareporte.addCell(celdaCantidad); 

                            PdfPCell celdaBibliografia = new PdfPCell();
                            celdaBibliografia.setIndent(80);
                            celdaBibliografia.setColspan(3);
                            celdaBibliografia.setBorder(0);
                            PdfPTable tablaBibliografia = new PdfPTable(3);
                            tablaBibliografia.setSpacingBefore(10f);
                            float[] tamanioCeldastbB = {5f, 80f, 15f};
                            tablaBibliografia.setWidths(tamanioCeldastbB);
                            tablaBibliografia.setWidthPercentage(90);
                            tablaBibliografia.setHorizontalAlignment(Element.ALIGN_RIGHT);
                            tablaBibliografia.addCell(celdaContador);
                            tablaBibliografia.addCell(celdaDescripcion);
                            tablaBibliografia.addCell(celdaCantidad);
                            ScriptEngineManager manager = new ScriptEngineManager();
                            ScriptEngine engine = manager.getEngineByName("javascript");
                            String script1 = (String) "function unescapeTexto(texto) {return unescape(texto);}";
                            engine.eval(script1);

                            Invocable invocar = (Invocable) engine;
                            BaseColor mycolor = WebColors.getRGBColor("#8CB9D8");
                            for (Integer fila = 0; fila < carreraComun.getCriteriosSilabo().size(); fila++) {
                                CriterioSilabo criterioSilabo = carreraComun.getCriteriosSilabo().get(fila);
                                Integer idOpcional = fila + 1;
                                Object object = invocar.invokeFunction("unescapeTexto", criterioSilabo.getDescripcion());
                                PdfPCell contador = new PdfPCell(new Paragraph(idOpcional.toString(), obtenerfuentePDF("contenidotablaFuenteB")));
                                PdfPCell descripcion = new PdfPCell(new Paragraph((String) object, obtenerfuentePDF("contenidotablaFuenteB")));
                                PdfPCell cantidad = new PdfPCell(new Paragraph(criterioSilabo.getCantidad().toString(), obtenerfuentePDF("contenidotablaFuenteB")));
                                cantidad.setVerticalAlignment(Element.ALIGN_CENTER);
                                cantidad.setHorizontalAlignment(Element.ALIGN_CENTER);

                                if (fila % 2 == 1) {
                                    descripcion.setBackgroundColor(new BaseColor(217, 227, 234));
                                    descripcion.setBorderColor(mycolor);
                                    cantidad.setBackgroundColor(new BaseColor(217, 227, 234));
                                    cantidad.setBorderColor(mycolor);
                                    contador.setBackgroundColor(new BaseColor(217, 227, 234));
                                    contador.setBorderColor(mycolor);
                                }
                                descripcion.setBorderColor(mycolor);
                                cantidad.setBorderColor(mycolor);
                                contador.setBorderColor(mycolor);
                                tablaBibliografia.addCell(contador);
                                tablaBibliografia.addCell(descripcion);
                                tablaBibliografia.addCell(cantidad);

//                            tablareporte.addCell(contador);
//                            tablareporte.addCell(descripcion);
//                            tablareporte.addCell(cantidad);
                            }
                            celdaBibliografia.addElement(tablaBibliografia);
                            tablareporte.addCell(celdaBibliografia);
                            tablareporte.setSpacingAfter(10f);
                        }

                    }
                }
            } else {
                doc.add(new Paragraph("Informacion no disponible", obtenerfuentePDF("institucionFuenteB")));
            }
            doc.add(tablareporte);
            doc.add(new Paragraph("\n"));

            doc.close();
            byte[] pdfBytes = archivo.toByteArray();
            String base64Encoded = Base64.getEncoder().encodeToString(pdfBytes);
            resultado = base64Encoded;

        } catch (Exception e) {
        }
        return resultado;
    }
//

    private static Periodo periodoActual() {
        dda.silabo.sw.SilaboSW_Service service = new dda.silabo.sw.SilaboSW_Service();
        dda.silabo.sw.SilaboSW port = service.getSilaboSWPort();
        return port.periodoActual();
    }

    public Font obtenerfuentePDF(String tipo) {
        Font result = new Font();
        ////////////////////////////////////////////////////HEADER////////////////////////////////////   
        if (tipo.equals("institucionFuente")) {
            Font institucionFuente = new Font();
            institucionFuente.setFamily("Segoe UI");
            institucionFuente.setSize(17);
            institucionFuente.setStyle(Font.BOLD);
            result = institucionFuente;
        }
        if (tipo.equals("institucionFuenteB")) {
            Font institucionFuente = new Font();
            institucionFuente.setFamily("Segoe UI");
            institucionFuente.setSize(14);
            institucionFuente.setStyle(Font.BOLD);
            result = institucionFuente;
        }
        if (tipo.equals("tituloreporteFuente")) {
            Font tituloreporteFuente = new Font();
            tituloreporteFuente.setFamily("Segoe UI");
            tituloreporteFuente.setSize(15);
            result = tituloreporteFuente;
        }
        if (tipo.equals("tituloreporteFuenteB")) {
            Font tituloreporteFuente = new Font();
            tituloreporteFuente.setFamily("Segoe UI");
            tituloreporteFuente.setSize(13);
            result = tituloreporteFuente;
        }
        if (tipo.equals("periodoFuente")) {
            Font periodoFuente = new Font();
            periodoFuente.setFamily("Segoe UI");
            periodoFuente.setSize(12);
            result = periodoFuente;
        }
        if (tipo.equals("reportentidadFuente")) {
            Font reportentidadFuente = new Font();
            reportentidadFuente.setFamily("Segoe UI");
            reportentidadFuente.setSize(12);
            reportentidadFuente.setColor(BaseColor.WHITE);
            result = reportentidadFuente;
        }
        if (tipo.equals("reportseccionFuenteB")) {
            Font reportseccionFuente = new Font();
            reportseccionFuente.setFamily("Segoe UI");
            reportseccionFuente.setSize(13);
            reportseccionFuente.setColor(BaseColor.WHITE);
            result = reportseccionFuente;
        }
        if (tipo.equals("titulostablas")) {
            Font titulostablas = new Font();
            titulostablas.setFamily("Segoe UI");
            titulostablas.setSize(12);
            titulostablas.setStyle(Font.BOLD);
            titulostablas.setColor(BaseColor.WHITE);
            result = titulostablas;
        }
        if (tipo.equals("titulostablasB")) {
            Font titulostablas = new Font();
            titulostablas.setFamily("Segoe UI");
            titulostablas.setSize(11);
            titulostablas.setStyle(Font.BOLD);
            titulostablas.setColor(BaseColor.WHITE);
            result = titulostablas;
        }
        if (tipo.equals("tituloentidadFuenteB")) {
            Font tituloentidadFuente = new Font();
            tituloentidadFuente.setFamily("Segoe UI");
            tituloentidadFuente.setSize(12);
            tituloentidadFuente.setColor(BaseColor.BLACK);
            tituloentidadFuente.setStyle(Font.BOLD);
            result = tituloentidadFuente;
        }
        if (tipo.equals("contenidotablaFuente")) {
            Font contenidotablaFuente = new Font();
            contenidotablaFuente.setFamily("Segoe UI");
            contenidotablaFuente.setSize(12);
            contenidotablaFuente.setColor(BaseColor.BLACK);
            result = contenidotablaFuente;
        }
        if (tipo.equals("contenidotablaFuenteB")) {
            Font contenidotablaFuente = new Font();
            contenidotablaFuente.setFamily("Segoe UI");
            contenidotablaFuente.setSize(11);
            contenidotablaFuente.setColor(BaseColor.BLACK);
            result = contenidotablaFuente;
        }
        if (tipo.equals("totaltablaFuente")) {
            Font totaltablaFuente = new Font();
            totaltablaFuente.setFamily("Segoe UI");
            totaltablaFuente.setSize(12);
            totaltablaFuente.setStyle(Font.BOLD);
            result = totaltablaFuente;
        }
        if (tipo.equals("leyendaFuente")) {
            Font leyendaFuente = new Font();
            leyendaFuente.setFamily("Segoe UI");
            leyendaFuente.setSize(8);
            leyendaFuente.setColor(BaseColor.DARK_GRAY);
            result = leyendaFuente;
        }
        return result;
    }

    public String getReporteEstadoSilabosPDF() {
        String resultado = "";
        try {
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
            Document doc = new Document();
            ByteArrayOutputStream archivo = new ByteArrayOutputStream();
            PdfWriter writer = PdfWriter.getInstance(doc, archivo);

            doc.open();
            doc.newPage();
////////////////////////////////////////////////////////
            PdfPTable header = new PdfPTable(3);
            header.setHorizontalAlignment(Element.ALIGN_CENTER);
            header.setWidthPercentage(100f);

            PdfPCell espoch = new PdfPCell();
            byte[] logoEspoch = null;
            String fotoEspoch = logoEntidad("ESPOCH", "Instituto");
            if ((fotoEspoch != null)) {
                logoEspoch = Base64.getDecoder().decode(fotoEspoch);
                Image imgespoch = Image.getInstance(logoEspoch);
//                Image imgespoch = Image.getInstance("C:\\Pictures\\PDF\\logo.png");
                imgespoch.scaleToFit(60, 60);
                espoch.setVerticalAlignment(Element.ALIGN_CENTER);
                espoch.setHorizontalAlignment(Element.ALIGN_CENTER);
                espoch.addElement(imgespoch);
            } else {
                espoch.addElement(new Paragraph("Logo no Disponible"));
            }

            espoch.setBorder(0);
            Periodo periodoActual = periodoActual();
            String periodoDefinido = periodoActual.getDescripcion();
            if (periodoDefinido == null) {
                periodoDefinido = "NO DEFINIDO";
            }
            PdfPCell facultad = new PdfPCell();
            byte[] logofacultad = null;

            Image imgfacultad = Image.getInstance("http://oldwww.espoch.edu.ec/Fotos/noticias/logo_finaldda_2d299.jpg");
            if (this.getFacultades().size() == 1) {
                FacultadUnidos f = this.getFacultades().stream().findFirst().orElse(null);
                String codFacultad = "FIE";
                if (f != null) {
                    codFacultad = f.getCodFacultad();
                }
                String fotoFacultad = logoEntidad(codFacultad, "Facultad");
                logofacultad = Base64.getDecoder().decode(fotoFacultad);
                imgfacultad = Image.getInstance(logofacultad);
                imgfacultad.scaleToFit(80, 80);
                facultad.setVerticalAlignment(Element.ALIGN_CENTER);
                facultad.setHorizontalAlignment(Element.ALIGN_CENTER);
                facultad.addElement(imgfacultad);
            } else {
                facultad.addElement(new Paragraph("Logo no Disponible"));
            }

            facultad.setBorder(0);
            Paragraph entidad = new Paragraph("ESCUELA SUPERIOR POLITÉCNICA DE CHIMBORAZO\n", obtenerfuentePDF("institucionFuenteB"));
            Paragraph tipoReporte = new Paragraph("REPORTE PARA " + this.getAyuda().toUpperCase() + "\n", obtenerfuentePDF("tituloreporteFuenteB"));
            Paragraph periodo = new Paragraph("PERÍODO ACADÉMICO:  " + periodoDefinido, obtenerfuentePDF("periodoFuente"));
            PdfPTable titulos = new PdfPTable(1);
            PdfPCell celentidad = new PdfPCell(entidad);
            PdfPCell cellreporte = new PdfPCell(tipoReporte);
            PdfPCell cellperiodo = new PdfPCell(periodo);

            celentidad.setBorder(0);
            celentidad.setVerticalAlignment(Element.ALIGN_CENTER);
            celentidad.setHorizontalAlignment(Element.ALIGN_CENTER);
            cellreporte.setBorder(0);
            cellreporte.setVerticalAlignment(Element.ALIGN_CENTER);
            cellreporte.setHorizontalAlignment(Element.ALIGN_CENTER);
            cellperiodo.setBorder(0);
            cellperiodo.setVerticalAlignment(Element.ALIGN_CENTER);
            cellperiodo.setHorizontalAlignment(Element.ALIGN_CENTER);

            titulos.addCell(celentidad);
            titulos.addCell(cellreporte);
            titulos.addCell(cellperiodo);

            PdfPCell titulo = new PdfPCell(titulos);
            titulo.setBorder(0);
            titulo.setVerticalAlignment(Element.ALIGN_CENTER);
            titulo.setHorizontalAlignment(Element.ALIGN_CENTER);

            header.addCell(espoch);
            header.addCell(titulo);
            header.addCell(facultad);
            float[] header1 = {13f, 74f, 13f};
            header.setWidths(header1);
            doc.add(header);
            doc.add(new Paragraph("\n"));

///////////////////////////////TITULO SECCION////////////////////
            PdfPTable tablaentidad = new PdfPTable(1);
            tablaentidad.setWidthPercentage(100f);
            PdfPCell cellentidad = new PdfPCell(new Paragraph("ESTADOS DE SÍLABOS", obtenerfuentePDF("reportseccionFuenteB")));
            cellentidad.setFixedHeight(24f);
            cellentidad.setBackgroundColor(new BaseColor(30, 70, 130));
            cellentidad.setBorder(Rectangle.NO_BORDER);
            tablaentidad.addCell(cellentidad);
            tablaentidad.setHorizontalAlignment(Element.ALIGN_LEFT);
            doc.add(tablaentidad);

//////////////////////////////TABLA BIBLIOGRAFIA////////////////////            
            doc.add(new Paragraph("", obtenerfuentePDF("contenidotablaFuenteB")));
            float[] tamanioCeldas = {5f, 50f, 45f};
            PdfPTable tablareporte = new PdfPTable(3);
            tablareporte.setWidths(tamanioCeldas);
            tablareporte.setWidthPercentage(100f);
            BaseColor colorborde = WebColors.getRGBColor("#8EAADB");
            BaseColor colorcelda = WebColors.getRGBColor("#1E4682");
            if (this.getFacultades().size() > 0) {
                int i = 0;
                for (FacultadUnidos facultadComun : this.getFacultades()) {
                    PdfPTable tablareporteFacultad = new PdfPTable(1);
                    tablareporteFacultad.setWidthPercentage(100f);
                    PdfPCell espaciotb2 = new PdfPCell(new Paragraph(""));
                    espaciotb2.setBorder(0);
                    espaciotb2.setColspan(3);
                    if (i != 0) {
                        espaciotb2.setFixedHeight(10f);
                    }
                    tablareporte.addCell(espaciotb2);
                    PdfPCell espaciotbFacultad = new PdfPCell(new Paragraph(facultadComun.getNombre(), obtenerfuentePDF("tituloentidadFuenteB")));
                    espaciotbFacultad.setVerticalAlignment(Element.ALIGN_CENTER);
                    espaciotbFacultad.setFixedHeight(22f);
                    espaciotbFacultad.setBorder(0);
                    tablareporteFacultad.addCell(espaciotbFacultad);
                    doc.add(tablareporteFacultad);
                    int j = 0;
                    for (EscuelaUnidos escuelaComun : facultadComun.getEscuelas()) {
                        PdfPTable tablareporteEscuela = new PdfPTable(1);
                        tablareporteEscuela.setWidthPercentage(100f);
                        espaciotb2 = new PdfPCell(new Paragraph(""));
                        if (j != 0) {
                            espaciotb2.setFixedHeight(10f);
                        }
                        espaciotb2.setBorder(0);
                        espaciotb2.setColspan(3);
                        tablareporteEscuela.addCell(espaciotb2);
                        PdfPCell espaciotbEscuela = new PdfPCell(new Paragraph(escuelaComun.getNombre(), obtenerfuentePDF("tituloentidadFuenteB")));
                        espaciotbEscuela.setFixedHeight(22f);
                        espaciotbEscuela.setVerticalAlignment(Element.ALIGN_CENTER);
                        espaciotbEscuela.setIndent(25);
                        espaciotbEscuela.setBorder(0);
                        tablareporteEscuela.addCell(espaciotbEscuela);
                        doc.add(tablareporteEscuela);
                        int ka = 0;
                        for (CarreraUnidos carreraComun : escuelaComun.getCarreras()) {
                            PdfPTable tablareporteCarrera = new PdfPTable(1);
                            tablareporteCarrera.setWidthPercentage(100f);
                            espaciotb2 = new PdfPCell(new Paragraph(""));
                            espaciotb2.setBorder(0);
                            espaciotb2.setColspan(3);
                            if (ka != 0) {
                                espaciotb2.setFixedHeight(10f);
                            }
                            tablareporteCarrera.addCell(espaciotb2);

                            PdfPCell espaciotbCarrera = new PdfPCell(new Paragraph(carreraComun.getNombre(), obtenerfuentePDF("tituloentidadFuenteB")));
                            espaciotbCarrera.setFixedHeight(22f);
                            espaciotbCarrera.setVerticalAlignment(Element.ALIGN_CENTER);
                            espaciotbCarrera.setIndent(50);
                            espaciotbCarrera.setBorder(0);
                            tablareporteCarrera.addCell(espaciotbCarrera);
                            doc.add(tablareporteCarrera);

                            for (Estados estados : carreraComun.getEstadosSilabo()) {
                                if (!estados.getAsignatura().isEmpty()) {
                                    doc.add(new Paragraph("\n"));
                                    Integer cantidad2 = estados.getAsignatura().size();
                                    tablaentidad = new PdfPTable(1);
                                    tablaentidad.setWidthPercentage(100f);
                                    cellentidad = new PdfPCell(new Paragraph(new Paragraph("ESTADO " + estados.getDescripcion() + ": " + cantidad2.toString(), obtenerfuentePDF("tituloentidadFuenteB"))));
                                    cellentidad.setFixedHeight(24f);
                                    cellentidad.setBorder(Rectangle.NO_BORDER);
                                    cellentidad.setIndent(50);
                                    tablaentidad.addCell(cellentidad);
                                    tablaentidad.setHorizontalAlignment(Element.ALIGN_LEFT);
                                    doc.add(tablaentidad);
                                    PdfPTable tablareporteEstado = new PdfPTable(3);
                                    tablareporteEstado.setWidths(tamanioCeldas);
                                    tablaentidad.setWidthPercentage(100f);
                                    PdfPCell celdaContador = new PdfPCell(new Paragraph("N°", obtenerfuentePDF("titulostablasB")));
                                    celdaContador.setBackgroundColor(colorcelda);
                                    celdaContador.setFixedHeight(22f);
                                    celdaContador.setBorderColor(colorborde);
                                    tablareporteEstado.addCell(celdaContador);

                                    PdfPCell celdaDescripcion = new PdfPCell(new Paragraph("ASIGNATURA", obtenerfuentePDF("titulostablasB")));
                                    celdaDescripcion.setVerticalAlignment(Element.ALIGN_CENTER);
                                    celdaDescripcion.setHorizontalAlignment(Element.ALIGN_CENTER);
                                    celdaDescripcion.setBackgroundColor(colorcelda);
                                    celdaDescripcion.setBorderColor(colorborde);
//                                    tablareporteEstado.addCell(celdaDescripcion);

                                    PdfPCell celdaCantidad = new PdfPCell(new Paragraph("DOCENTES", obtenerfuentePDF("titulostablasB")));
                                    celdaCantidad.setVerticalAlignment(Element.ALIGN_CENTER);
                                    celdaCantidad.setHorizontalAlignment(Element.ALIGN_CENTER);
                                    celdaCantidad.setBackgroundColor(colorcelda);
                                    celdaCantidad.setBorderColor(colorborde);
//                                    tablareporteEstado.addCell(celdaCantidad);

                                    doc.add(tablareporteEstado);
                                    PdfPTable tablaBibliografia = new PdfPTable(3);
                                    tablaBibliografia.setSpacingBefore(10f);
                                    float[] tamanioCeldastbB = {5f, 50f, 45f};
                                    tablaBibliografia.setWidths(tamanioCeldastbB);
                                    tablaBibliografia.setWidthPercentage(90f);
                                    tablaBibliografia.setHorizontalAlignment(Element.ALIGN_RIGHT);
                                    BaseColor mycolor = WebColors.getRGBColor("#8CB9D8");
                                    tablaBibliografia.addCell(celdaContador);
                                    tablaBibliografia.addCell(celdaDescripcion);
                                    tablaBibliografia.addCell(celdaCantidad);
                                    for (Integer fila = 0; fila < estados.getAsignatura().size(); fila++) {
                                        AsignaturaUnidos as = estados.getAsignatura().get(fila);
                                        Integer idOpcional = fila + 1;
                                        PdfPCell contador = new PdfPCell(new Paragraph(idOpcional.toString(), obtenerfuentePDF("contenidotablaFuenteB")));
                                        PdfPCell descripcion = new PdfPCell(new Paragraph((as.getNombreMateria()), obtenerfuentePDF("contenidotablaFuenteB")));
                                        String nombresDocentes = "";
                                        for (DocenteUnidos dt : as.getDocentes()) {
                                            nombresDocentes += dt.getNombres() + " " + dt.getApellidos() + "\n";
                                        }
                                        PdfPCell cantidad = new PdfPCell(new Paragraph(nombresDocentes, obtenerfuentePDF("contenidotablaFuenteB")));
                                        cantidad.setVerticalAlignment(Element.ALIGN_CENTER);
                                        cantidad.setHorizontalAlignment(Element.ALIGN_CENTER);

                                        if (fila % 2 == 1) {
                                            descripcion.setBackgroundColor(new BaseColor(217, 227, 234));
                                            descripcion.setBorderColor(mycolor);
                                            cantidad.setBackgroundColor(new BaseColor(217, 227, 234));
                                            cantidad.setBorderColor(mycolor);
                                            contador.setBackgroundColor(new BaseColor(217, 227, 234));
                                            contador.setBorderColor(mycolor);
                                        }
                                        descripcion.setBorderColor(mycolor);
                                        cantidad.setBorderColor(mycolor);
                                        contador.setBorderColor(mycolor);
                                        tablaBibliografia.addCell(contador);
                                        tablaBibliografia.addCell(descripcion);
                                        tablaBibliografia.addCell(cantidad);

                                    }
                                    doc.add(tablaBibliografia);
                                }
                            }
                            doc.add(new Paragraph("\n"));
                        }
                    }
                }
            } else {
                doc.add(new Paragraph("Informacion no disponible", obtenerfuentePDF("institucionFuenteB")));
            }
            doc.add(tablareporte);
            doc.add(new Paragraph("\n"));

            doc.close();
            byte[] pdfBytes = archivo.toByteArray();
            String base64Encoded = Base64.getEncoder().encodeToString(pdfBytes);
            resultado = base64Encoded;

        } catch (Exception e) {
        }
        return resultado;
    }

    private static String logoEntidad(java.lang.String arg0, java.lang.String arg1) {
        dda.silabo.sw.SilaboSW_Service service = new dda.silabo.sw.SilaboSW_Service();
        dda.silabo.sw.SilaboSW port = service.getSilaboSWPort();
        return port.logoEntidad(arg0, arg1);
    }

    private static String logoEntidad_1(java.lang.String arg0, java.lang.String arg1) {
        dda.silabo.sw.SilaboSW_Service service = new dda.silabo.sw.SilaboSW_Service();
        dda.silabo.sw.SilaboSW port = service.getSilaboSWPort();
        return port.logoEntidad(arg0, arg1);
    }

}
