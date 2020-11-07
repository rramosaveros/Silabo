/*
 * This class is part of the book "iText in Action - 2nd Edition"
 * written by Bruno Lowagie (ISBN: 9781935182610)
 * For more info, go to: http://itextpdf.com/examples/
 * This example only works with the AGPL version of iText.
 */

import com.itextpdf.text.BaseColor;
import java.io.FileOutputStream;
import java.io.IOException;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import ec.edu.espoch.academico.Periodo;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.Base64;
import javax.swing.ImageIcon;
import org.knowm.xchart.BitmapEncoder;
import org.knowm.xchart.PieChartBuilder;
import org.knowm.xchart.style.PieStyler;

public class MyFirstTable {

    /**
     * The resulting PDF file.
     */
    public static final String RESULT
            = "first_table.pdf";

    /**
     * Main method.
     *
     * @param args no arguments needed
     * @throws DocumentException
     * @throws IOException
     */
    public static void main(String[] args)
            throws IOException, DocumentException {
        new MyFirstTable().createPdf(RESULT);
        Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + RESULT);
    }

    /**
     * Creates a PDF with information about the movies
     *
     * @param filename the name of the PDF file that will be created.
     * @throws DocumentException
     * @throws IOException
     */
    public void createPdf(String filename)
            throws IOException, DocumentException {
        // step 1
        Document document = new Document();
        // step 2
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(filename));
        // step 3
        document.setPageSize(PageSize.A4.rotate());
        document.open();
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
        Paragraph entidad = new Paragraph("ESCUELA SUPERIOR POLITÉCNICA DE CHIMBORAZO\n");
        Paragraph tipoReporte = new Paragraph("sasasasa");
        Paragraph periodo = new Paragraph("PERÍODO ACADÉMICO: " + periodoDefinido);
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
        header.addCell(logoespoch);
        header.addCell(titulo);
        header.addCell(facultad);
        float[] header1 = {15f, 70f, 15f};
        header.setWidths(header1);
        document.add(header);

///////////////////////////TITULO ENTIDAD////////////////
        PdfPTable tablaentidad = new PdfPTable(1);
        tablaentidad.setWidthPercentage(100f);
        PdfPCell cellentidad = new PdfPCell(new Paragraph("dsfsdfdfdfdf")
        );
        cellentidad.setVerticalAlignment(Element.ALIGN_CENTER);
        cellentidad.setFixedHeight(24f);
        cellentidad.setBackgroundColor(new BaseColor(30, 70, 130));
        cellentidad.setBorder(Rectangle.NO_BORDER);
        tablaentidad.addCell(cellentidad);
        document.add(tablaentidad);
        // step 4
//        document.add(createFirstTable());
//        document.add(createFirstTable2());
        PdfPTable p = new PdfPTable(3);
        
        p.setWidthPercentage(100f);
        float[] header12 = {40f, 30, 30f};
        p.setWidths(header12);
        p.setSpacingBefore(100f);
        p.getDefaultCell().setBorder(0);
        PdfPCell pc = new PdfPCell();
        PdfPCell pc2 = new PdfPCell();
        pc.setBorder(0);
        // pc2.setFixedHeight(40f);
        pc.setHorizontalAlignment(Element.ALIGN_CENTER);
        pc.addElement(createFirstTable2());
        p.addCell(createFirstTable());
        p.addCell(new PdfPCell());
        p.addCell(pc2);
//        document.add(createPdf2(filename, 10));
//        document.add(createPdf2(filename, 400));
        // step 5
        document.add(p);
        document.close();
    }

    /**
     * Creates our first table
     *
     * @return our first table
     */
    public static PdfPTable createFirstTable() throws DocumentException {
        // a table with three columns
        PdfPTable table = new PdfPTable(2);
        table.setHorizontalAlignment(Element.ALIGN_LEFT);
        // the cell object
        PdfPCell cell;

        // we add the four remaining cells with addCell()
        table.addCell("row 1; cell 1");
        table.addCell("row 1; cell 2");
        table.addCell("row 2; cell 1");
        table.addCell("row 2; cell 2");
        table.addCell("row 1; cell 1");
        table.addCell("row 1; cell 2");
        table.addCell("row 2; cell 1");
        table.addCell("row 2; cell 2");
        table.addCell("row 1; cell 1");
        table.addCell("row 1; cell 2");
        table.addCell("row 2; cell 1");
        table.addCell("row 2; cell 2");
        table.addCell("row 2; cell 1");
        table.addCell("row 2; cell 2");
        table.addCell("row 1; cell 1");
        table.addCell("row 1; cell 2");
        table.addCell("row 2; cell 1");
        table.addCell("row 2; cell 2");
        table.addCell("row 1; cell 1");
        table.addCell("row 1; cell 2");
        table.addCell("row 2; cell 1");
        table.addCell("row 2; cell 2");
        table.addCell("row 1; cell 1");
        table.addCell("row 1; cell 2");
        table.addCell("row 2; cell 1");
        table.addCell("row 2; cell 2");
        table.addCell("row 2; cell 1");
        table.addCell("row 2; cell 2");
        table.addCell("row 2; cell 1");
        table.addCell("row 2; cell 2");
        table.addCell("row 1; cell 1");
        table.addCell("row 1; cell 2");
        table.addCell("row 2; cell 1");
        table.addCell("row 2; cell 2");
        table.addCell("row 1; cell 1");
        table.addCell("row 1; cell 2");
        table.addCell("row 2; cell 1");
        table.addCell("row 2; cell 2");
        table.addCell("row 1; cell 1");
        table.addCell("row 1; cell 2");
        table.addCell("row 2; cell 1");
        table.addCell("row 2; cell 2");
        table.addCell("row 2; cell 1");
        table.addCell("row 2; cell 2");
        table.addCell("row 2; cell 1");
        table.addCell("row 2; cell 2");
        table.addCell("row 1; cell 1");
     
        table.addCell("row 2; cell 2");
        return table;
    }
    
    public static PdfPTable createFirstTable2() throws DocumentException, IOException {
        org.knowm.xchart.PieChart chart = new PieChartBuilder().width(350).height(350).build();
        
        PdfPTable tablaGraf = new PdfPTable(1);
        tablaGraf.setWidthPercentage(100f);
//        tablaGraf.setSpacingBefore(20f);
//        tablaGraf.setWidthPercentage(30f);
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
        chart.addSeries("Inicio", 5);
        chart.addSeries("Revision", 3);
        chart.addSeries("Corregir", 2);
        chart.addSeries("Aprobados", 1);

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
        tablaleyenda.setTotalWidth(240);
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
        
        PdfPCell lyinicio = new PdfPCell(new Paragraph(" Inicio"));
        PdfPCell lyrevision = new PdfPCell(new Paragraph(" Revision"));
        PdfPCell lycorregir = new PdfPCell(new Paragraph(" Corregir"));
        PdfPCell lyaprobado = new PdfPCell(new Paragraph(" Aprobado"));
        
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
        
        PdfPCell tituloGrafico = new PdfPCell(new Paragraph("SASASASASASAS"));
        tituloGrafico.setBorderColor(new BaseColor(204, 204, 204));
        tituloGrafico.setHorizontalAlignment(Element.ALIGN_CENTER);
        tituloGrafico.setVerticalAlignment(Element.ALIGN_CENTER);
////////////////////////////////////TABLA DETALLE Y GRAFICO////////////////////////////////////////////
        tablaGraf.setHorizontalAlignment(Element.ALIGN_RIGHT);
        tablaGraf.getDefaultCell().setBorderColor(new BaseColor(204, 204, 204));
        tablaGraf.addCell(tituloGrafico);
        tablaGraf.addCell(finalIm);
        tablaGraf.addCell(tablaleyenda);
        
        return tablaGraf;
    }
    
    public static Paragraph createPdf2(String dest, int iden) throws IOException, DocumentException {
        
        PdfPTable table = new PdfPTable(8);
        table.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.setTotalWidth(150);
        table.setLockedWidth(true);
        for (int aw = 0; aw < 16; aw++) {
            table.addCell("hi");
        }
        Paragraph p = new Paragraph();
        p.setIndentationLeft(iden);
        p.add(table);
        return p;
    }
    
    private static String logoEntidad(java.lang.String arg0, java.lang.String arg1) {
        dda.silabo.sw.SilaboSW_Service service = new dda.silabo.sw.SilaboSW_Service();
        dda.silabo.sw.SilaboSW port = service.getSilaboSWPort();
        return port.logoEntidad(arg0, arg1);
    }
    
    private static Periodo periodoActual() {
        dda.silabo.sw.SilaboSW_Service service = new dda.silabo.sw.SilaboSW_Service();
        dda.silabo.sw.SilaboSW port = service.getSilaboSWPort();
        return port.periodoActual();
    }
}
