/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.company.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.plaf.Border;
import com.mycompany.entities.Coupon;
import java.util.ArrayList;
import com.mycompany.services.CouponService;
import com.codename1.ui.Dialog;
import com.codename1.ui.Image;
import com.codename1.ui.plaf.Style;
import java.io.IOException;
import com.mycompany.services.QRCODE;
import com.codename1.ui.Form;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.entities.Utilisateur;
import java.util.ArrayList;
import com.codename1.ui.Container;
import com.mycompany.entities.Trader;


/**
 *
 * @author Kal
 */
public class CouponsList extends Form {

    public CouponsList(Form previous) {
           getToolbar().addMaterialCommandToLeftBar("Back", FontImage.MATERIAL_ARROW_BACK, ev -> {
        previous.showBack();
    });
    setTitle("Choisir un coupon");
    setLayout(new BoxLayout(BoxLayout.Y_AXIS));
    
    Label scoreLabel = new Label("Votre score est: " + SessionManager.getScore());


    Button CM = new Button("Coupon Mensuel");
    Button CS = new Button("Coupon Special");
    Button CE = new Button("Coupon Exclusif");

    Container card = new Container(new BoxLayout(BoxLayout.Y_AXIS));
    card.getStyle().setBgColor(0xFFFFFF);
    card.getStyle().setBgTransparency(255);
    card.getStyle().setMargin(5, 5, 0, 0);
    card.getStyle().setPadding(10, 10, 10, 10);
    card.getStyle().setBorder(Border.createLineBorder(2, 0xCCCCCC));
    
    card.add(scoreLabel);
    card.add(CM);
    card.add(CS);
    card.add(CE);

    add(card);

    Container centerContainer = new Container(new BoxLayout(BoxLayout.Y_AXIS));
    centerContainer.getStyle().setAlignment(Component.CENTER);

    // Create a new container for the scoreboard and mycoupons buttons
    Container buttonContainer = new Container(new BoxLayout(BoxLayout.Y_AXIS));

    Button scoreboardButton = new Button("Scoreboard");
    // Add some margin between the buttons
    scoreboardButton.getStyle().setMargin(400, 0, 0, 0);
    buttonContainer.add(scoreboardButton);

    Button mycoupons = new Button("Mes Coupons");
    // Add some margin between the buttons
    mycoupons.getStyle().setMargin(30, 0, 0, 0);
    buttonContainer.add(mycoupons);

    Button PDF = new Button("Génerer un PDF de mes coupons:");
    // Add the button container to the center container
    centerContainer.add(buttonContainer);

    // Set the center container to take up all available space
    centerContainer.setScrollableY(true);

    add(centerContainer);

        mycoupons.addActionListener((ActionListener) (ActionEvent evt1) -> {
             
            Form newForm = new Form();
             newForm.getToolbar().addMaterialCommandToLeftBar("Back", FontImage.MATERIAL_ARROW_BACK, ev -> {
        // Navigate back to the previous form
        showBack();
    });

            CouponService cs = new CouponService();
            ArrayList<Coupon> coupons = cs.MyCoupons();
            Container cardsContainer = new Container();

            // Add each coupon as a separate card
            for (Coupon coupon : coupons) {
                Container card1 = new Container(new BorderLayout());
                card1.getStyle().setMarginUnit(Style.UNIT_TYPE_DIPS);
                card1.getStyle().setMarginTop(10);

                Label title = new Label(coupon.getTitre_coupon());
                title.getStyle().setFgColor(0x007aff);
                card1.add(BorderLayout.NORTH, title);

                Label description = new Label(coupon.getDescription_coupon());
                card1.add(BorderLayout.CENTER, description);

                Label date_expiration = new Label(coupon.getDate_expiration());
                date_expiration.getStyle().setFgColor(0x007aff);
                card1.add(BorderLayout.WEST, date_expiration);

                Label code = new Label(coupon.getCode());
                code.getStyle().setFgColor(0x007aff);
                card1.add(BorderLayout.EAST, code);

                cardsContainer.add(card1);
            }

            newForm.getContentPane().add(cardsContainer);
            newForm.show();
        });

     scoreboardButton.addActionListener((ActionListener) (ActionEvent evt1) -> {
    Form newForm = new Form();
     newForm.getToolbar().addMaterialCommandToLeftBar("Back", FontImage.MATERIAL_ARROW_BACK, ev -> {
        // Navigate back to the previous form
        showBack();
    });

    CouponService cs = new CouponService();
    ArrayList<Trader> users = cs.Scoreboard();
    Container cardsContainer = new Container(new BoxLayout(BoxLayout.Y_AXIS)); // Use a BoxLayout with Y_AXIS

    // Add each coupon as a separate card
    for (Trader user : users) {
        Container card1 = new Container(new BorderLayout());
        card1.getStyle().setMarginUnit(Style.UNIT_TYPE_DIPS);
        card1.getStyle().setMarginTop(10);

        Label nom = new Label(user.getNom());
        nom.getStyle().setFgColor(0x007aff);
        card1.add(BorderLayout.WEST, nom); // Add the nom label to the left

        Label prenom = new Label(user.getPrenom());
        card1.add(BorderLayout.CENTER, prenom);

        Label score = new Label(String.valueOf(user.getScore()));
        score.getStyle().setFgColor(0x007aff);
        card1.add(BorderLayout.EAST, score);

        cardsContainer.add(card1);
    }

    newForm.getContentPane().add(cardsContainer);
    newForm.show();
});

      

    
    CouponService cs= new CouponService();

    CM.addActionListener((ActionListener) (ActionEvent evt1) -> {
    String code= cs.affecterCouponCasual();
    if(code!=null){
        Dialog.show("Succès", "Le coupon a été créé avec succès", "OK", null);
        showQRCode(code); 
    } else {
        Dialog.show("Erreur", "Une erreur s'est produite lors de la création du coupon! Votre score est insuffisant.", "OK", null);
    }
    
});

    CS.addActionListener((ActionListener) (ActionEvent evt1) -> {
    String code= cs.affecterCouponSpecial();    
    if(code!=null) {
        Dialog.show("Succès", "Le coupon a été créé avec succès", "OK", null);
        showQRCode(code); 
    } else {
        Dialog.show("Erreur", "Une erreur s'est produite lors de la création du coupon! Votre score est insuffisant.", "OK", null);
    }
});

CE.addActionListener((ActionListener) (ActionEvent evt1) -> {
    String code= cs.affecterCouponExclusif();
    if(code!=null){
        Dialog.show("Succès", "Le coupon a été créé avec succès", "OK", null);
        showQRCode(code); 
    } else {
        Dialog.show("Erreur", "Une erreur s'est produite lors de la création du coupon! Votre score est insuffisant.", "OK", null);
    }
});



}
  
  private void showQRCode(String qrCodeData) {
    QRCODE qc= new QRCODE();
    Form qrCodeForm = new Form("QR Code");
     qrCodeForm.getToolbar().addMaterialCommandToLeftBar("Back", FontImage.MATERIAL_ARROW_BACK, ev -> {
        // Navigate back to the previous form
        showBack();
    });

    qrCodeForm.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
    
    
    Label qrCodeDataLabel = new Label("Votre coupon est: " + qrCodeData);
    qrCodeForm.add(qrCodeDataLabel);
      Style style = qrCodeDataLabel.getUnselectedStyle();
    style.setFgColor(0x8B008B); // Set the color to purple
    qrCodeDataLabel.setUnselectedStyle(style);
    Label qrCodeLabel = new Label();
  
        // Generate QR code image and set it to the label
        Image qrCodeImage = qc.getQrCode(qrCodeData);
        qrCodeLabel.setIcon(qrCodeImage);
   
  
    qrCodeForm.add(qrCodeLabel);
    qrCodeForm.show();
}

   
  
  
  
  
  private void showcoupons() {
    
}

}

