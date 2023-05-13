/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.company.gui;

import com.codename1.components.MultiButton;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.TextArea;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Border;
import com.mycompany.entities.Reponse;
import com.mycompany.services.ServiceReponse;
import java.util.ArrayList;
import com.codename1.ui.Component;
import com.codename1.ui.Display;

/**
 *
 * @author MSI
 */
public class ReponseAffichageForm extends Form {

    public ReponseAffichageForm(int tt, Form previous) {
        getToolbar().addMaterialCommandToLeftBar("Back", FontImage.MATERIAL_ARROW_BACK, ev -> {
            previous.showBack();
        });
        ArrayList<Reponse> listr = new ServiceReponse().affichageReponse(tt);

        for (Reponse rep : listr) {
            MultiButton m = new MultiButton(rep.getTitre_reponse());
            m.setUIID("Label");
            int idreponse = rep.getId_reponse();
            Image happyEmoji = FontImage.createMaterial(FontImage.MATERIAL_SENTIMENT_VERY_SATISFIED, "Label", 8);
            Button m1 = new Button();
            m1.setIcon(happyEmoji);

            m1.addActionListener(e -> {
                new ServiceReponse().Avissatisfait(idreponse);
                Display.getInstance().callSerially(() -> {
                    refreshTheme();
                });
            });

            Image angryEmoji = FontImage.createMaterial(FontImage.MATERIAL_SENTIMENT_VERY_DISSATISFIED, "Label", 8);
            Button m2 = new Button();
            m2.setIcon(angryEmoji);

            m2.addActionListener(e -> {
                new ServiceReponse().AvisNonatisfait(idreponse);
                Display.getInstance().callSerially(() -> {
                    refreshTheme();
                });
            });

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
            card1.add(m1);
            card1.add(m2);
            addAll(card1);

        }

    }
}
