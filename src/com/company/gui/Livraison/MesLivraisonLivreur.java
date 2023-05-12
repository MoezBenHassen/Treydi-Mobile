/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.company.gui.Livraison;

import com.codename1.components.MultiButton;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Border;
import com.mycompany.entities.Livraison;
import com.mycompany.services.ServiceLivraison;
import java.util.ArrayList;

/**
 *
 * @author Marayed
 */
public class MesLivraisonLivreur extends Form {

    public MesLivraisonLivreur(Form previous) {
        setTitle("Mes Livraisons");
        setLayout(new BoxLayout(BoxLayout.Y_AXIS));
        getToolbar().addMaterialCommandToRightBar("", FontImage.MATERIAL_ADD, e -> {
            // handle click event
        });

       getToolbar().addMaterialCommandToLeftBar("Back", FontImage.MATERIAL_ARROW_BACK, ev -> {
            previous.showBack();
        });
        ArrayList<Livraison> list = ServiceLivraison.getInstance().listMesLivraisonLivreur();

        for (Livraison livraison : list) {
            MultiButton mb = new MultiButton(livraison.getEchange().getTitre_echange());
            mb.setUIID("Label");

            Container card = new Container(new BoxLayout(BoxLayout.Y_AXIS));
            card.getStyle().setBgColor(0xFFFFFF);
            card.getStyle().setBgTransparency(255);
            card.getStyle().setMargin(5, 5, 0, 0);
            card.getStyle().setPadding(10, 10, 10, 10);
            card.getStyle().setBorder(Border.createLineBorder(2, 0xCCCCCC));

            card.add(mb);

            // AFFICHAGE

            add(card);
        }
    }
}
