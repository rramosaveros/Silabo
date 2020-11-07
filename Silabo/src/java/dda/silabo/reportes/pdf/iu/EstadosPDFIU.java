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
import dda.silabo.clases.unidos.FacultadUnidos;
import dda.silabo.reportes.comunes.Estados;
import dda.silabo.reportes.comunes.ReporteComun;
//import dda.silabo.reportes.comunes.ReporteComun;
import ec.edu.espoch.academico.Periodo;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.util.Base64;
import javax.swing.ImageIcon;
import org.knowm.xchart.BitmapEncoder;
import org.knowm.xchart.PieChartBuilder;
import org.knowm.xchart.style.PieStyler;

public class EstadosPDFIU extends ReporteComun {

    public String generarCampoDocenteDetallePDF() {
        String resultado = "";
        try {
/////////////////////////////////////////////////////////////////////
            Document doc = new Document();
            ByteArrayOutputStream archivo = new ByteArrayOutputStream();
            PdfWriter writer = PdfWriter.getInstance(doc, archivo);

            doc.open();
            doc.setPageSize(PageSize.A4.rotate());
            doc.newPage();

///////////////////////////////TABLA HEADER//////////////////////////            
            PdfPTable header = new PdfPTable(3);
            header.setHorizontalAlignment(Element.ALIGN_CENTER);
            header.setWidthPercentage(100f);

            PdfPCell logoespoch = new PdfPCell();
            byte[] logoEspoch = null;
            String fotoEspoch = logoEntidad("ESPOCH", "Instituto");
            if ((fotoEspoch != null)) {
                logoEspoch = Base64.getDecoder().decode(fotoEspoch);
                Image imgespoch = Image.getInstance(logoEspoch);
//                Image imgespoch = Image.getInstance("C:\\Pictures\\PDF\\logo.png");
                imgespoch.scaleToFit(60, 60);
                logoespoch.setVerticalAlignment(Element.ALIGN_CENTER);
                logoespoch.setHorizontalAlignment(Element.ALIGN_CENTER);
                logoespoch.addElement(imgespoch);
            } else {
                logoespoch.addElement(new Paragraph("Logo no Disponible"));
            }

            logoespoch.setBorder(0);
            Periodo periodoActual = periodoActual();
            String periodoDefinido = periodoActual.getDescripcion();
            if (periodoDefinido == null) {
                periodoDefinido = "NO DEFINIDO";
            }
            Paragraph entidad = new Paragraph("ESCUELA SUPERIOR POLITÉCNICA DE CHIMBORAZO\n", obtenerfuentePDF("institucionFuente"));
            Paragraph tipoReporte = new Paragraph(this.getAyuda(), obtenerfuentePDF("tituloreporteFuente"));
            Paragraph periodo = new Paragraph("PERÍODO ACADÉMICO: " + periodoDefinido, obtenerfuentePDF("periodoFuente"));
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

            PdfPCell facultad = new PdfPCell();
            byte[] logofacultad = null;
            String fotoFacultad = logoEntidad(this.getElementos().get(0).getCodigo(), "Carrera");
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
            header.addCell(logoespoch);
            header.addCell(titulo);
            header.addCell(facultad);
            float[] header1 = {15f, 70f, 15f};
            header.setWidths(header1);
            doc.add(header);
            doc.add(new Paragraph("\n"));

///////////////////////////TITULO ENTIDAD////////////////
            PdfPTable tablaentidad = new PdfPTable(1);
            tablaentidad.setWidthPercentage(100f);
            PdfPCell cellentidad = new PdfPCell(new Paragraph(this.getTitulo(), obtenerfuentePDF("reportentidadFuente")));
            cellentidad.setVerticalAlignment(Element.ALIGN_CENTER);
            cellentidad.setFixedHeight(24f);
            cellentidad.setBackgroundColor(new BaseColor(30, 70, 130));
            cellentidad.setBorder(Rectangle.NO_BORDER);
            tablaentidad.addCell(cellentidad);
            doc.add(tablaentidad);
            doc.add(new Paragraph("\n"));

///////////////////////////TABLA DE ASIGNATURA Y DOCENTE////////////////
            PdfPTable tabla = new PdfPTable(2);
            tabla.getDefaultCell().setBorder(0);
            tabla.setWidthPercentage(100f);
            float[] tablat = {65f, 35f};
            tabla.setWidths(tablat);

            PdfPTable tablareporte = new PdfPTable(2);

            BaseColor colorborde = WebColors.getRGBColor("#8EAADB");
            BaseColor colorcelda = WebColors.getRGBColor("#1E4682");
            Integer contAprobado = 0, contRevision = 0, contCorregir = 0, contInicio = 0;

            for (int j = 0; j < this.getDatos().size(); j++) {
                Estados estados = this.getDatos().get(j);
                PdfPCell espaciotb2 = new PdfPCell(new Paragraph(""));
                espaciotb2.setFixedHeight(10f);
                espaciotb2.setBorder(0);
                espaciotb2.setColspan(2);
                tablareporte.addCell(espaciotb2);
                if (estados.getDescripcion().equals("Inicio")) {
                    contInicio = estados.getInicio();
                } else if (estados.getDescripcion().equals("Revision")) {
                    contRevision = estados.getInicio();
                } else if (estados.getDescripcion().equals("Corregir")) {
                    contCorregir = estados.getInicio();
                } else if (estados.getDescripcion().equals("Aprobado")) {
                    contAprobado = estados.getInicio();
                }
                PdfPCell espaciotb = new PdfPCell(new Paragraph(estados.getDescripcion() + ": " + estados.getInicio() + " ASIGNATURAS", obtenerfuentePDF("tituloestadosFuente")));
                espaciotb.setBorder(0);
                espaciotb.setColspan(2);
                tablareporte.addCell(espaciotb);
                tablareporte.addCell(espaciotb2);
                if (estados.getAsignatura().size() > 0) {
                    PdfPCell celdasignatura = new PdfPCell(new Paragraph("ASIGNATURAS", obtenerfuentePDF("titulostablas")));
                    celdasignatura.setFixedHeight(24f);
                    celdasignatura.setVerticalAlignment(Element.ALIGN_CENTER);
                    celdasignatura.setHorizontalAlignment(Element.ALIGN_CENTER);
                    celdasignatura.setBackgroundColor(colorcelda);
                    celdasignatura.setBorderColor(colorborde);
                    tablareporte.addCell(celdasignatura);

                    Paragraph celdadocente = new Paragraph("DOCENTES", obtenerfuentePDF("titulostablas"));
                    PdfPCell docente = new PdfPCell(celdadocente);
                    docente.setVerticalAlignment(Element.ALIGN_CENTER);
                    docente.setHorizontalAlignment(Element.ALIGN_CENTER);
                    docente.setBackgroundColor(colorcelda);
                    docente.setBorderColor(colorborde);

                    tablareporte.addCell(docente);
                    for (Integer fila = 0; fila < estados.getAsignatura().size(); fila++) {
                        AsignaturaUnidos asignaturaComun = estados.getAsignatura().get(fila);
//                    AsignaturaUnidos asignaturaComun = estados.getAsignatura().get(fila);
                        String docentes = "";
                        docentes = asignaturaComun.getDocentes().stream().map((datoDocente) -> datoDocente.getNombres() + " " + datoDocente.getApellidos() + "\n").reduce(docentes, String::concat);
                        PdfPCell celdaAsignatura = new PdfPCell(new Paragraph(asignaturaComun.getNombreMateria(), obtenerfuentePDF("contenidotablaFuente")));
                        PdfPCell celdaDocentes = new PdfPCell(new Paragraph(docentes, obtenerfuentePDF("contenidotablaFuente")));

                        BaseColor mycolor = WebColors.getRGBColor("#8CB9D8");
                        if (fila % 2 == 1) {
                            celdaAsignatura.setBackgroundColor(new BaseColor(217, 227, 234));
                            celdaAsignatura.setBorderColor(mycolor);
                            celdaDocentes.setBackgroundColor(new BaseColor(217, 227, 234));
                            celdaDocentes.setBorderColor(mycolor);
                        }
                        celdaAsignatura.setBorderColor(mycolor);
                        celdaDocentes.setBorderColor(mycolor);
                        tablareporte.addCell(celdaAsignatura);
                        tablareporte.addCell(celdaDocentes);
                    }
                }
            }

////////////////////////////////////GRAFICO////////////////////////////////////////            
            org.knowm.xchart.PieChart chart = new PieChartBuilder().width(350).height(350).build();

            PdfPTable tablaGraf = new PdfPTable(1);
//            tablaGraf.setSpacingBefore(20f);
//            tablaGraf.setWidthPercentage(30f);
            tablaGraf.setWidthPercentage(100f);

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
            chart.addSeries("Revision", contRevision);
            chart.addSeries("Corregir", contCorregir);
            chart.addSeries("Aprobados", contAprobado);

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

////////////////////////////////TABLA LEYENDA/////////////////////////////////////
            PdfPTable tablaleyenda = new PdfPTable(4);
            tablaleyenda.setTotalWidth(257);
            tablaleyenda.setLockedWidth(true);
            tablaleyenda.setHorizontalAlignment(Element.ALIGN_LEFT);
            tablaleyenda.getDefaultCell().setBorder(0);

            PdfPTable tablalyinicio = new PdfPTable(2);
            PdfPTable tablalyrevision = new PdfPTable(2);
            PdfPTable tablalycorregir = new PdfPTable(2);
            PdfPTable tablalyaprobado = new PdfPTable(2);

            float[] tamanio = {0.1f, 0.4f};
            tablalyinicio.setWidths(tamanio);
            tablalyrevision.setWidths(tamanio);
            tablalycorregir.setWidths(tamanio);
            tablalyaprobado.setWidths(tamanio);

            PdfPCell lyinicio = new PdfPCell(new Paragraph(" Inicio", obtenerfuentePDF("leyendaFuente")));
            PdfPCell lyrevision = new PdfPCell(new Paragraph(" Revision", obtenerfuentePDF("leyendaFuente")));
            PdfPCell lycorregir = new PdfPCell(new Paragraph(" Corregir", obtenerfuentePDF("leyendaFuente")));
            PdfPCell lyaprobado = new PdfPCell(new Paragraph(" Aprobado", obtenerfuentePDF("leyendaFuente")));

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
////////////////////////////////////TABLA DETALLE Y GRAFICO////////////////////////////////////////////
            tablaGraf.setHorizontalAlignment(Element.ALIGN_RIGHT);
            tablaGraf.getDefaultCell().setBorderColor(new BaseColor(204, 204, 204));
            tablaGraf.addCell(tituloGrafico);
            tablaGraf.addCell(finalIm);
            tablaGraf.addCell(tablaleyenda);

            PdfPCell celda_grafico = new PdfPCell();
            celda_grafico.setBorder(0);
            celda_grafico.setHorizontalAlignment(Element.ALIGN_CENTER);
            celda_grafico.addElement(tablaGraf);
            tabla.addCell(tablareporte);
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
//

    private static Periodo periodoActual() {
        dda.silabo.sw.SilaboSW_Service service = new dda.silabo.sw.SilaboSW_Service();
        dda.silabo.sw.SilaboSW port = service.getSilaboSWPort();
        return port.periodoActual();
    }

    public Font obtenerfuentePDF(String tipo) {
        Font result = new Font();
        if (tipo.equals("institucionFuente")) {
            Font institucionFuente = new Font();
            institucionFuente.setFamily("Segoe UI");
            institucionFuente.setSize(17);
            institucionFuente.setStyle(Font.BOLD);
            result = institucionFuente;
        }
        if (tipo.equals("tituloreporteFuente")) {
            Font tituloreporteFuente = new Font();
            tituloreporteFuente.setFamily("Segoe UI");
            tituloreporteFuente.setSize(15);
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
        if (tipo.equals("tituloestadosFuente")) {
            Font tituloestadosFuente = new Font();
            tituloestadosFuente.setFamily("Segoe UI");
            tituloestadosFuente.setSize(12);
            tituloestadosFuente.setColor(BaseColor.BLACK);
            tituloestadosFuente.setStyle(Font.BOLD);
            result = tituloestadosFuente;
        }
        if (tipo.equals("titulostablas")) {
            Font titulostablas = new Font();
            titulostablas.setFamily("Segoe UI");
            titulostablas.setSize(12);
            titulostablas.setStyle(Font.BOLD);
            titulostablas.setColor(BaseColor.WHITE);
            result = titulostablas;
        }
        if (tipo.equals("contenidotablaFuente")) {
            Font contenidotablaFuente = new Font();
            contenidotablaFuente.setFamily("Segoe UI");
            contenidotablaFuente.setSize(12);
            contenidotablaFuente.setColor(BaseColor.BLACK);
            result = contenidotablaFuente;
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

    private static String logoEntidad(java.lang.String arg0, java.lang.String arg1) {
        dda.silabo.sw.SilaboSW_Service service = new dda.silabo.sw.SilaboSW_Service();
        dda.silabo.sw.SilaboSW port = service.getSilaboSWPort();
        return port.logoEntidad(arg0, arg1);
    }

}
