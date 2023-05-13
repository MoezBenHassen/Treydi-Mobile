/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.company.gui;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.FontImage;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.RoundBorder;


public class Profile extends Form {

    public Profile(Form previous) {
        
        super("Edit Profile", BoxLayout.y());
         getToolbar().addMaterialCommandToLeftBar("Back", FontImage.MATERIAL_ARROW_BACK, ev -> {
          previous.showBack();
         });
    // Retrieve user data from SessionManager
        int id = SessionManager.getId();
        String email = SessionManager.getEmail();
        String nom = SessionManager.getNom();
        String prenom = SessionManager.getPrenom();
        String adresse = SessionManager.getAdresse();
        int score = SessionManager.getScore();

        // Create UI components to display user data
        Label emailLabel = new Label("Email: " + email);
        Label nomLabel = new Label("Nom: " + nom);
        Label prenomLabel = new Label("Prénom: " + prenom);
        Label adresseLabel = new Label("Adresse: " + adresse);
        Label scoreLabel = new Label("Score: " + score);

        // Style UI components
        Container profileContainer = new Container(new BorderLayout());
        profileContainer.getStyle().setBgColor(0xffffff);
        profileContainer.getStyle().setBgTransparency(255);
        profileContainer.getStyle().setMargin(10, 10, 0, 0);
        profileContainer.getStyle().setPadding(10, 10, 10, 10);
        profileContainer.add(BorderLayout.CENTER, new Container(new BoxLayout(BoxLayout.Y_AXIS))
                .add(emailLabel)
                .add(nomLabel)
                .add(prenomLabel)
                .add(adresseLabel)
                .add(scoreLabel));

        Button editButton = new Button("Edit");
        editButton.getStyle().setBgColor(0x4CAF50);
        editButton.getStyle().setFgColor(0xffffff);
        editButton.getStyle().setPadding(5, 5, 5, 5);
        editButton.addActionListener(evt -> {
            new EditProfile(previous).show();
        });

        // Create a container to hold the profile and edit button
        Container container = new Container(new BorderLayout());
        container.getStyle().setPadding(10, 10, 10, 10);
        container.getStyle().setMargin(0, 0, 10, 10);
        container.getStyle().setBgColor(0xffffff);
        container.getStyle().setBgTransparency(255);
        container.add(BorderLayout.CENTER, profileContainer);
        container.add(BorderLayout.SOUTH, editButton);

        // Add the container to the form
        add(container);
    }
}
