/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.company.gui;

import com.codename1.ui.Button;
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
import java.io.IOException;
import com.mycompany.services.QRCODE;


/**
 *
 * @author Kal
 */
public class CouponsList extends Form{
    
    
  public CouponsList(Form previous) {
     setTitle("Choisir un coupon");
        setLayout(new BoxLayout(BoxLayout.Y_AXIS));
        getToolbar().addMaterialCommandToRightBar("", FontImage.MATERIAL_ADD, e -> {
            // handle click event
        });

    

    Button CM = new Button("Coupon Mensuel");
    Button CS = new Button("Coupon Special");
    Button CE = new Button("Coupon Exclusif");
   
    
      Container card = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        card.getStyle().setBgColor(0xFFFFFF);
        card.getStyle().setBgTransparency(255);
        card.getStyle().setMargin(5, 5, 0, 0);
        card.getStyle().setPadding(10, 10, 10, 10);
        card.getStyle().setBorder(
         Border.createLineBorder(2, 0xCCCCCC)
        );
        
        card.add(CM);
        card.add(CS);
        card.add(CE);

        add(card);
  

    
    CouponService cs= new CouponService();

    CM.addActionListener((ActionListener) (ActionEvent evt1) -> {
    String code= cs.affecterCouponCasual();
    if(code!=null){
        Dialog.show("Succès", "Le coupon a été créé avec succès", "OK", null);
        showQRCode(code); 
    } else {
        Dialog.show("Erreur", "Une erreur s'est produite lors de la création du coupon", "OK", null);
    }
});

    CS.addActionListener((ActionListener) (ActionEvent evt1) -> {
    String code= cs.affecterCouponSpecial();    
    if(code!=null) {
        Dialog.show("Succès", "Le coupon a été créé avec succès", "OK", null);
    } else {
        Dialog.show("Erreur", "Une erreur s'est produite lors de la création du coupon", "OK", null);
    }
});

CE.addActionListener((ActionListener) (ActionEvent evt1) -> {
    String code= cs.affecterCouponExclusif();
    if(code!=null){
        Dialog.show("Succès", "Le coupon a été créé avec succès", "OK", null);
    } else {
        Dialog.show("Erreur", "Une erreur s'est produite lors de la création du coupon", "OK", null);
    }
});



}
  
  private void showQRCode(String qrCodeData) {
    QRCODE qc= new QRCODE();
    Form qrCodeForm = new Form("QR Code");
    qrCodeForm.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
    
    Label qrCodeLabel = new Label();
  
        // Generate QR code image and set it to the label
        Image qrCodeImage = qc.getQrCode(qrCodeData);
        qrCodeLabel.setIcon(qrCodeImage);
   
  
    qrCodeForm.add(qrCodeLabel);
    qrCodeForm.show();
}

}

