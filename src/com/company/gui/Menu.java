/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.gui;

import com.codename1.ui.Form;
import com.codename1.ui.layouts.BoxLayout;

/**
 *
 * @author FGH
 */
public class Menu extends Form{

    public Menu() {
        setTitle("Menu Principale");
        setLayout(BoxLayout.y());
        
         getToolbar().addCommandToSideMenu("Profile", null, ev -> { new ItemsList(this).show(); } );
        getToolbar().addCommandToSideMenu("Items", null, ev -> { new ItemsList(this).show(); } );
        getToolbar().addCommandToSideMenu("Echanges", null, ev -> { new ItemsList(this).show(); } );
        getToolbar().addCommandToSideMenu("Coupons", null, ev -> { new CouponsList(this).show(); } );
         getToolbar().addCommandToSideMenu("Articles", null, ev -> { new ItemsList(this).show(); } );
         getToolbar().addCommandToSideMenu("Reclamations", null, ev -> { new ItemsList(this).show(); } );
    }
    
}
