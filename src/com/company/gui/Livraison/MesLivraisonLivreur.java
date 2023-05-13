/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.company.gui.Livraison;

import com.codename1.components.MultiButton;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.plaf.Border;
import com.mycompany.entities.Echange;
import com.mycompany.entities.Item;
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

        ServiceLivraison sl = new ServiceLivraison();

        ArrayList<Livraison> list = ServiceLivraison.getInstance().listMesLivraisonLivreur();

        for (Livraison livraison : list) {
            MultiButton mb = new MultiButton(livraison.getEchange().getTitre_echange());
            Echange echanges = livraison.getEchange();
            mb.setUIID("Label");

            Container card = new Container(new BoxLayout(BoxLayout.Y_AXIS));
            card.getStyle().setBgColor(0xFFFFFF);
            card.getStyle().setBgTransparency(255);
            card.getStyle().setMargin(5, 5, 0, 0);
            card.getStyle().setPadding(10, 10, 10, 10);
            card.getStyle().setBorder(Border.createLineBorder(2, 0xCCCCCC));

            mb.addActionListener(evt -> {
                Form detailsForm = new Form(new GridLayout(1, 2));
                detailsForm.setTitle(echanges.getTitre_echange()); // Set the form title to the exchange title

                detailsForm.getToolbar().addMaterialCommandToLeftBar("Back", FontImage.MATERIAL_ARROW_BACK, ev -> {
                    previous.showBack();
                });

                ArrayList<Item> allItems = (ArrayList<Item>) echanges.getItems();
                Label user1_nom = new Label("This is my label");

                Container labelContainer_user1nom = new Container(new BorderLayout());

                labelContainer_user1nom.add(BorderLayout.CENTER, user1_nom);
                Container items1 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
                items1.add(labelContainer_user1nom);
                items1.getStyle().setBgColor(0xFFFFFF);
                items1.getStyle().setMargin(5, 5, 0, 0);
                items1.getStyle().setPadding(10, 10, 10, 10);
                items1.getStyle().setBorder(Border.createLineBorder(2, 0xCCCCCC));
                for (Item item : allItems) {
                    if (item.getId_user() == echanges.getId_user1()) {
                        MultiButton aa = new MultiButton((String) item.getLibelle());
                        aa.setUIID("Label");
                        aa.getAllStyles().setPaddingLeft(2);
                        aa.getAllStyles().setPaddingRight(2);
                        aa.getAllStyles().setPaddingTop(5);
                        aa.getAllStyles().setPaddingBottom(5);
                        aa.getAllStyles().setMarginLeft(2);
                        aa.getAllStyles().setMarginRight(2);
                        aa.getAllStyles().setMarginTop(2);
                        aa.getAllStyles().setMarginBottom(2);
                        aa.getAllStyles().setBorder(Border.createLineBorder(1, 0xCCCCCC));
                        aa.setWidth(100);
                        items1.add(aa);
                    }
                }

                Container items2 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
                items2.getStyle().setBgColor(0xFFFFFF);
                items2.getStyle().setBgTransparency(255);
                items2.getStyle().setMargin(5, 5, 0, 0);
                items2.getStyle().setPadding(10, 10, 10, 10);
                items2.getStyle().setBorder(Border.createLineBorder(2, 0xCCCCCC));
                for (Item item : allItems) {
                    if (item.getId_user() == echanges.getId_user2()) {
                        MultiButton aa = new MultiButton((String) item.getLibelle());
                        aa.setUIID("Label");
                        aa.getAllStyles().setPaddingLeft(2);
                        aa.getAllStyles().setPaddingRight(2);
                        aa.getAllStyles().setPaddingTop(5);
                        aa.getAllStyles().setPaddingBottom(5);
                        aa.getAllStyles().setMarginLeft(2);
                        aa.getAllStyles().setMarginRight(2);
                        aa.getAllStyles().setMarginTop(2);
                        aa.getAllStyles().setMarginBottom(2);
                        aa.getAllStyles().setBorder(Border.createLineBorder(1, 0xCCCCCC));
                        aa.setWidth(100);
                        items2.add(aa);
                    }
                }

                // Create the delete button
                Button deleteButton = new Button("Delete");
                deleteButton.addActionListener(e -> {
                    if (Dialog.show("Confirmation", "Voulez-vous supprimer cette livraison ?", "Oui", "Non")) {
                        sl.supprimerLivraison(livraison.getId_livraison(), echanges.getId_echange());
                        showBack();
                    }
                });

                Button terminerButton = new Button("Terminer");
                terminerButton.addActionListener(e -> {
                    sl.terminerLivraison(livraison.getId_livraison());
                });

                // Add the components to the form
                detailsForm.add(items1);
                detailsForm.add(items2);
                detailsForm.add(deleteButton);

                if ("En_Cours".equals(livraison.getEtat())) {
                    detailsForm.add(terminerButton);
                }

                detailsForm.show();
            });

            card.add(mb);

            // AFFICHAGE
            add(card);
        }
    }
}
