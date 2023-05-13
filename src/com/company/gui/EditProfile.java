/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.company.gui;

import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.services.ServiceUtilisateur;

public class EditProfile extends Form {

    public EditProfile(Form previous) {
        
        super("Edit Profile", BoxLayout.y());
         getToolbar().addMaterialCommandToLeftBar("Back", FontImage.MATERIAL_ARROW_BACK, ev -> {
          previous.showBack();
         });

        // Add text fields for user input
        TextField nomField = new TextField("", "Nom", 20, TextField.ANY);
        TextField prenomField = new TextField("", "Prenom", 20, TextField.ANY);
        TextField adresseField = new TextField("", "Adresse", 30, TextField.ANY);
        TextField passwordField = new TextField("", "Password", 20, TextField.PASSWORD);

        Button saveButton = new Button("Save");
        saveButton.addActionListener(e -> {
            String nom = nomField.getText();
            String prenom = prenomField.getText();
            String adresse = adresseField.getText();
            String password = passwordField.getText();
            int id = SessionManager.getId();

            boolean result = ServiceUtilisateur.getInstance().modifierUser(id, nom, prenom, adresse, password);
            if (result) {
                Dialog.show("Success", "Profile updated successfully.", "OK", null);
            } else {
                Dialog.show("Error", "Profile update failed.", "OK", null);
            }
        });

        // Add the input fields and button to the form
  
        add(nomField);
        add(prenomField);
        add(adresseField);
        add(passwordField);
        add(saveButton);
    }
}

