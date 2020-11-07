

import com.itextpdf.text.Document;
import com.itextpdf.text.html.simpleparser.HTMLWorker;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.StringReader;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Jorge Zaruma
 */
public class NewMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        try {
            String k = "<ul>" +
"    <li>Lorem</li>" +
"    <li>Ipsum</li>" +
"    <li>Dolor</li>" +
"    <li>Sit</li>" +
"    <li>Amet</li>" +
"</ul>";
            OutputStream file = new FileOutputStream(new File("Test.pdf"));
            Document document = new Document();
            PdfWriter.getInstance(document, file);
            document.open();
            HTMLWorker htmlWorker = new HTMLWorker(document);
            htmlWorker.parse(new StringReader(k));
            document.close();
            file.close();
            Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + "Test.pdf");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
