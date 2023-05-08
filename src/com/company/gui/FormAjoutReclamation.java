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
        
        // Set the title of the form
        setTitle("Ajouter une réclamation");

        // Use a vertical box layout to stack the components
        setLayout(BoxLayout.y());

        // Create text fields for the title and description of the reclamation
        TextField titreReclamationField = new TextField("", "Titre : ");
        TextArea descriptionReclamationField = new TextArea();
        descriptionReclamationField.setHint("Description : ");
        // Set the rows and columns of the textarea
        descriptionReclamationField.setRows(4);
        descriptionReclamationField.setColumns(20);
        // Add a line break if the text exceeds 35 characters
        descriptionReclamationField.addDataChangedListener((int oldLen, int newLen) -> {
            if (newLen > 0 && newLen % 30 == 0) {
                String text = descriptionReclamationField.getText();
                descriptionReclamationField.setText(text.substring(0, text.length() - 1) + "\n" + text.substring(text.length() - 1));
            }
        });

        // Create a button to submit the reclamation
        Button submitButton = new Button("Valider");

        // Add a border to the button
        submitButton.getStyle().setBorder(Border.createLineBorder(2));

        // Add an action listener to the button to handle the submission
         submitButton.addActionListener(evt -> {
            // Get the values of the text fields
            String titre_reclamation = titreReclamationField.getText();
            String description_reclamation = descriptionReclamationField.getText();
            try {

                if (titre_reclamation.equals("") || description_reclamation.equals("")) {
                    Dialog.show("Veuillez vérifier les données", "", "Annuler", "OK");
                    
                    
                    
                } else {
                    
                    
                    
                    
                 //   InfiniteProgress ip = new InfiniteProgress();

                 //   final Dialog iDialog = ip.showInfiniteBlocking();

                    Reclamation r = new Reclamation(titre_reclamation, description_reclamation);
                    
                    System.out.println("data  reclamation == " + r);

                   
                      ServiceReclamation.getInstance().ajoutReclamation( titreReclamationField.getText() ,descriptionReclamationField.getText());
                    

                   // iDialog.dispose(); 

                    refreshTheme();

                }

            } catch (Exception ex) {
                ex.printStackTrace();
            }

        });

        // Add the components to the form
        addAll(titreReclamationField, descriptionReclamationField, submitButton);

    }
}
