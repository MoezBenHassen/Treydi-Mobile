/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.company.gui;

import com.codename1.components.MultiButton;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.TextArea;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Border;
import com.mycompany.entities.Reponse;
import com.mycompany.services.ServiceReponse;
import java.util.ArrayList;

/**
 *
 * @author MSI
 */
public class ReponseAffichageForm extends Form {

    public ReponseAffichageForm(int tt) {

        ArrayList<Reponse> listr = new ServiceReponse().affichageReponse(tt);
        
                for (Reponse rep : listr) {
            MultiButton m = new MultiButton(rep.getTitre_reponse());
            m.setUIID("Label");

            TextArea t = new TextArea(rep.getDescription_reponse());
            t.setUIID("SlightlySmallerFontLabel");
            t.setEditable(false);
            Container card1 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
            card1.getStyle().setBgColor(0xFFFFFF);
            card1.getStyle().setBgTransparency(255);
            card1.getStyle().setMargin(5, 5, 0, 0);
            card1.getStyle().setPadding(10, 10, 10, 10);
            card1.getStyle().setBorder(
                    Border.createLineBorder(2, 0xCCCCCC)
            );

            card1.add(m);
            card1.add(t);
                addAll(card1);

        }
    }
}
