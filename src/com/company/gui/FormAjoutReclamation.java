package com.company.gui;

import com.codename1.components.InfiniteProgress;
import com.codename1.ui.Button;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.entities.Reclamation;
import com.mycompany.services.ServiceReclamation;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Form for adding a new reclamation.
 */
public class FormAjoutReclamation extends Form {

    public FormAjoutReclamation(Form previous) {
        getToolbar().addMaterialCommandToLeftBar("Back", FontImage.MATERIAL_ARROW_BACK, ev -> {
            previous.showBack();
        });
        
       
        setTitle("Ajouter une réclamation");

        setLayout(BoxLayout.y());
        
        TextField titreReclamationField = new TextField("", "Titre : ");
        TextArea descriptionReclamationField = new TextArea();
        descriptionReclamationField.setHint("Description : ");
        descriptionReclamationField.setRows(4);
        descriptionReclamationField.setColumns(20);
        descriptionReclamationField.addDataChangedListener((int oldLen, int newLen) -> {
            if (newLen > 0 && newLen % 30 == 0) {
                String text = descriptionReclamationField.getText();
                descriptionReclamationField.setText(text.substring(0, text.length() - 1) + "\n" + text.substring(text.length() - 1));
            }
        });

      
        Button submitButton = new Button("Valider");

        
        submitButton.getStyle().setBorder(Border.createLineBorder(2));

       
         submitButton.addActionListener(evt -> {
            
            int id_user = SessionManager.getId();
            String titre_reclamation = titreReclamationField.getText();
            String description_reclamation = descriptionReclamationField.getText();
            try {

                if (titre_reclamation.equals("") || description_reclamation.equals("")) {
                    Dialog.show("Veuillez vérifier les données", "", "Annuler", "OK");
                    
                    
                    
                } else {
                    
                    
                    
                    
                 

                    Reclamation r = new Reclamation(titre_reclamation, description_reclamation);
                    
                    System.out.println("data  reclamation == " + r);

                    System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"+id_user);
                      ServiceReclamation.getInstance().ajoutReclamation( titreReclamationField.getText() ,descriptionReclamationField.getText());
                    

        

                    refreshTheme();

                }

            } catch (Exception ex) {
                ex.printStackTrace();
            }

        });

     
        addAll(titreReclamationField, descriptionReclamationField, submitButton);

    }
}
