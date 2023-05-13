/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.services;

import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.mycompany.entities.Coupon;
import java.util.Date;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Font;
import com.codename1.io.FileSystemStorage;
import com.codename1.io.Log;
import com.codename1.ui.Display;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Element;
import com.mycompany.entities.Article;
import java.text.SimpleDateFormat;
/**
 *
 * @author SBS
 */
public class PDFArticle {
   public static void PDFArticle(Article article) {
        Document document = new Document();

        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            PdfWriter.getInstance(document, baos);
            document.open();
            
            // Add a title to the table
            Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18, BaseColor.BLACK);
            Paragraph title = new Paragraph(article.getTitre(), titleFont);
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(title);
            
            // Add some padding around the table
            PdfPTable table = new PdfPTable(1);
            table.setSpacingBefore(20);
            table.setSpacingAfter(20);
             
            // Add alternating row colors
            BaseColor[] rowColors = {BaseColor.WHITE, BaseColor.LIGHT_GRAY};
            int currentRowColorIndex = 0;
            
            // Add header row
            PdfPCell cell,titre,auteur,description,contenu;
            Font headerFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12, BaseColor.BLACK);
       /*     for (String header : new String[]{"CHOSE"}) {
                cell = new PdfPCell(new Phrase(header, headerFont));
                cell.setBackgroundColor(BaseColor.GRAY);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                table.addCell(cell);
            }
         */   
            float[] columnWidths = {2}; // adjust the widths as per your needs
            table.setWidths(columnWidths);
            // Add data rows
            Font dataFont = FontFactory.getFont(FontFactory.HELVETICA, 10, BaseColor.BLACK);
            
                currentRowColorIndex = (currentRowColorIndex + 1) % 2;
             
                    
                    description = new PdfPCell(new Phrase(article.getDescription(), dataFont));
                    description.setBackgroundColor(rowColors[0]);
                    description.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
                    description.setVerticalAlignment(Element.ALIGN_JUSTIFIED);
                    description.setBorderWidth(0);
                    table.addCell(description);
                    
                    
                    contenu = new PdfPCell(new Phrase(article.getContenu(), dataFont));
                    contenu.setBackgroundColor(rowColors[0]);
                    contenu.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
                    contenu.setVerticalAlignment(Element.ALIGN_JUSTIFIED);
                    contenu.setBorderWidth(0);
                    table.addCell(contenu);
                    
  
            
            
            // Add footer with some text
            Font footerFont = FontFactory.getFont(FontFactory.HELVETICA, 10, BaseColor.BLACK);
            Paragraph footer = new Paragraph("visit Treydi.tn for more articles " + new SimpleDateFormat("dd/MM/yyyy").format(new Date()), footerFont);
            footer.setAlignment(Element.ALIGN_CENTER);
            document.add(footer);
            
            // Add table to document
            document.add(table);
            
            document.close();
            byte[] pdfBytes = baos.toByteArray();
            String fileName = "article.pdf";
            FileSystemStorage fs = FileSystemStorage.getInstance();
            String filePath = fs.getAppHomePath() + fileName;
            fs.openOutputStream(filePath).write(pdfBytes);
            Display.getInstance().execute(filePath);

        } catch (DocumentException | IOException e) {
            Log.e(e);
        }
    }
}
