package com.company.gui;

import com.codename1.components.MultiButton;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.TextArea;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.plaf.Border;
import com.mycompany.entities.Reclamation;
import com.mycompany.services.ServiceReclamation;
import java.util.ArrayList;
import com.company.gui.UpdateReclamationForm;
import com.mycompany.entities.Reponse;
import com.mycompany.services.ServiceReponse;



 public class ListReclamation extends Form {

    public ListReclamation() {
        setTitle("Liste des Reclamations");
        setLayout(new BoxLayout(BoxLayout.Y_AXIS));
        getToolbar().addMaterialCommandToRightBar("", FontImage.MATERIAL_ADD, e -> {
            // handle click event
        });
       ArrayList<Reclamation> list = new ServiceReclamation().getInstance().affichageReclamations();

        for(Reclamation rec : list ) {
       
             int id = rec.getId_reclamation();
        Button repondreButton = new Button("Reponse");
    
        MultiButton mb = new MultiButton(rec.getTitre_reclamation());
        mb.setUIID("Label");

        TextArea ta = new TextArea(rec.getDescription());
        ta.setUIID("SlightlySmallerFontLabel");
        ta.setEditable(false);
        ta.setRows(ta.getLines());
           EncodedImage deleteIcon = EncodedImage.createFromImage(FontImage.createMaterial(FontImage.MATERIAL_DELETE, "Delete", 4), false);
           EncodedImage updateIcon = EncodedImage.createFromImage(FontImage.createMaterial(FontImage.MATERIAL_UPDATE, "Update", 4), false);

       
            Button deleteButton = new Button(deleteIcon);

            Button updateButton = new Button(updateIcon);
            
            deleteButton.addActionListener(e -> {
                new ServiceReclamation().supprimerReclamation(id);
                 Display.getInstance().callSerially(() -> {
                  refreshTheme();
            });

            });
            updateButton.addActionListener(e -> {

                new UpdateReclamationForm(id, rec.getTitre_reclamation(), rec.getDescription()).show();
            });
            
            
            
            repondreButton.addActionListener(e -> {
                new  ReponseAffichageForm(rec.getId_reclamation()).show();
                
                
            }); 


        Container card = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        card.getStyle().setBgColor(0xFFFFFF);
        card.getStyle().setBgTransparency(255);
        card.getStyle().setMargin(5, 5, 0, 0);
        card.getStyle().setPadding(10, 10, 10, 10);
        card.getStyle().setBorder(
                Border.createLineBorder(2, 0xCCCCCC)
        );
      

        card.add(mb);
        card.add(ta);
        card.add(deleteButton);
        card.addComponent(repondreButton);
        card.add(updateButton);
        
        add(card);
    }
    }
}



