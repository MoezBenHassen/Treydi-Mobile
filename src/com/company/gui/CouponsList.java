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
import com.mycompany.entities.Coupon;
import java.util.ArrayList;
import com.mycompany.services.CouponService;

/**
 *
 * @author Kal
 */
public class CouponsList extends Form{
    
    
  public CouponsList(Form previous) {
    ArrayList<Coupon> coupons = new ArrayList<>();
    getToolbar().addMaterialCommandToLeftBar("Back", FontImage.MATERIAL_ARROW_BACK, ev -> {
        previous.showBack();
    });

    Form f = new Form("Transformation", BoxLayout.y());

    // Create container and label for title
    Container titleContainer = new Container(new FlowLayout(CENTER));
      Label title = new Label("Transformer votre score");
    title.getStyle().setFgColor(0x000000);
    titleContainer.add(title);
    f.add(titleContainer);

    // Create container for buttons
    Container buttonsContainer = new Container(new GridLayout(1, 3));
    Button CM = new Button("Coupon Mensuel");
    Button CS = new Button("Coupon Special");
    Button CE = new Button("Coupon Exclusif");
    buttonsContainer.addAll(CM, CS, CE);
    f.add(buttonsContainer);

    // Center the buttons container
    buttonsContainer.getAllStyles().setMarginTop(CENTER);
    
    CouponService cs= new CouponService();

    CM.addActionListener((ActionListener) (ActionEvent evt1) -> {
        Coupon c = new Coupon("Coupon Mensuel Mai", "50% sur la livraison", "VALID", "CasCouponMai","2023-06-01",1,1 );
        cs.ajoutCoupon(c);
    });
    CS.addActionListener((ActionListener) (ActionEvent evt1) -> {
        Coupon c = new Coupon("Coupon Special Mai", "100% sur la livraison", "VALID", "SpecCouponMai","2023-06-01",2,1 );
        cs.ajoutCoupon(c);
    });
    CE.addActionListener((ActionListener) (ActionEvent evt1) -> {
        Coupon c = new Coupon("Coupon Exclusif Mai", "Carte de recharge gratuite", "VALID", "ExcluCouponMai","2023-06-01",3,1 );
        cs.ajoutCoupon(c);
    });

    f.show();
}

}

