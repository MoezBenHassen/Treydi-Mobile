/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.company.gui;

import com.codename1.io.FileSystemStorage;
import com.codename1.io.Log;
import com.codename1.ui.Display;
import com.codename1.util.Base64;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.mycompany.entities.Categorie_Items;
import com.mycompany.entities.Item;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import com.itextpdf.text.Image;

/**
 *
 * @author MDJMi
 */
public class ItemsServices {

    public static void generatePdfFromItems(ArrayList<Item> items, ArrayList<Categorie_Items> catitems) {
        Document document = new Document();

        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            PdfWriter.getInstance(document, baos);
            document.open();
            PdfPTable table = new PdfPTable(6); // 2 columns: item name and price
            table.addCell("Image");
            table.addCell("Libelle");
            table.addCell("Categorie");
            table.addCell("Description");
            table.addCell("Likes");
            table.addCell("Dislikes");
            for (Item item : items) {
                table.addCell(item.getLibelle());

                table.addCell(item.getLibelle());
                StringBuilder sb = new StringBuilder();
                for (Categorie_Items x : catitems) {
                    if (item.getId_categorie() == x.getId_categorie()) {
                        if (sb.length() > 0) {
                            sb.append(", ");
                        }
                        sb.append(x.getNom_categorie());
                    }
                }
                String result = sb.toString();
                table.addCell(result);
                table.addCell(String.valueOf(item.getDescription()));
                table.addCell(String.valueOf(item.getLikes()));
                table.addCell(String.valueOf(item.getDislikes()));
            }
            document.add(table);
            document.close();
            byte[] pdfBytes = baos.toByteArray();
            String fileName = "myPdfFile.pdf";
            FileSystemStorage fs = FileSystemStorage.getInstance();
            String filePath = fs.getAppHomePath() + fileName;
            fs.openOutputStream(filePath).write(pdfBytes);
            Display.getInstance().execute(filePath);

        } catch (DocumentException | IOException e) {
            Log.e(e);
        }
    }

}
