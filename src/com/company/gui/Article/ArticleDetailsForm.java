package com.company.gui.Article;

import com.codename1.charts.util.ColorUtil;
import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.Style;
import com.mycompany.entities.Article;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Element;
import com.mycompany.services.PDFArticle;
import java.io.IOException;


public class ArticleDetailsForm extends Form {

    public ArticleDetailsForm(Article article, Form previous) {
         getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, ev -> {
            previous.showBack();
        });
        
        setTitle("Article");
        setLayout(new BoxLayout(BoxLayout.Y_AXIS));
        
        
        //Container container = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        Container container = new Container(new BorderLayout());
        Container container2 = new Container(new BorderLayout());
        Container titleContainer = new Container(new FlowLayout(CENTER,CENTER));
        titleContainer.add(article.getTitre());
        SpanLabel title = new SpanLabel(article.getTitre());
        Style titleStyle = title.getAllStyles();
        titleStyle.setAlignment(CENTER);
        titleStyle.setFgColor(ColorUtil.BLACK);
        titleStyle.setUnderline(false);
        titleStyle.setAlignment(Component.CENTER);
        title.setUIID("Title"); // Set the UIID to apply the style
        
        SpanLabel description = new SpanLabel(article.getDescription());
        SpanLabel contenu = new SpanLabel(article.getContenu());
        EncodedImage placeholder1 = EncodedImage.createFromImage(FontImage.createMaterial(FontImage.MATERIAL_IMAGE, "MultiButton", 8), true);
        Image img=null;
        img = URLImage.createToStorage(placeholder1,
                 article.getImage(),
                 article.getImage(),
                 URLImage.RESIZE_SCALE);
        int deviceWidth = Display.getInstance().getDisplayWidth();

        img = img.scaledWidth(deviceWidth);
        //int height = (int)(img.getHeight() * ((float)deviceWidth / img.getWidth()));
        int height = (int)(563.0 / 1133.0 *deviceWidth);
        img = img.scaledHeight(height);
        img = img.scaled(deviceWidth, height);
        ImageViewer imgV = new ImageViewer(img);

        Container cnt = new Container (new BorderLayout());

//        container.add(imgV);
          
        Image downloadIcon = null;
        try {
            downloadIcon = Image.createImage("telecharger.ng"); // Replace with your image path
        } catch (IOException ex) {
            
        }
        Button pdf_btn = new Button("Download");
        pdf_btn.setIcon(downloadIcon);
        container2.addComponent(BorderLayout.SOUTH, pdf_btn);

        //PDF ARTICLE
        PDFArticle pf= new PDFArticle();
        pdf_btn.addActionListener((ActionListener) (ActionEvent evt1) -> {
            pf.PDFArticle(article);

        });
        
        Container northContainer = new Container(new BoxLayout(BoxLayout.Y_AXIS));
      
        northContainer.add(imgV);
        northContainer.addComponent(container2);
        northContainer.add(title);
        northContainer.add(description);
        northContainer.add(contenu);
        
        container.addComponent(BorderLayout.NORTH, northContainer);
        addComponent(container);
/*
        Button editButton = new Button("Edit");
        Button deleteButton = new Button("Delete");

      

        addComponent(editButton);
        addComponent(deleteButton);
*/
    }
}