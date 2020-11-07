/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dda.silabo.pdf;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.ExceptionConverter;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfContentByte;
import static com.itextpdf.text.pdf.PdfDictionary.FONT;
import com.itextpdf.text.pdf.PdfDocument;
import com.itextpdf.text.pdf.PdfName;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfTemplate;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javafx.scene.text.Font.font;

/**
 *
 * @author Evelyn
 */
public class FooterPaginaItext extends PdfPageEventHelper {

    protected PdfPTable footer;

    public FooterPaginaItext(PdfPTable footer) {
        this.footer = footer;
    }

    public void onEndPage(PdfWriter writer, Document document) {
        //ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_CENTER, new Phrase("Dirección: Panamericana Sur km 1 ½.	"), 200, 20, 0);
        footer.writeSelectedRows(0, -1, 100, 50, writer.getDirectContent());
        
            }
    
//            Logger.getLogger(FooterPaginaItext.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (IOException ex) {
//            Logger.getLogger(FooterPaginaItext.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (DocumentException ex) {
//            Logger.getLogger(FooterPaginaItext.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
}
