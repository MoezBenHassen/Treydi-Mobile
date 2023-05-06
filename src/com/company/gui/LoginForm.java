/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.company.gui;

import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.mycompany.entities.Utilisateur;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;

public class LoginForm extends Form {

    private TextField emailField;
    private TextField passwordField;
    private Button loginButton;

    public LoginForm() {
        super("Login");

        emailField = new TextField("", "Email", 20, TextField.EMAILADDR);
        passwordField = new TextField("", "Password", 20, TextField.PASSWORD);
        loginButton = new Button("Login");

        Container contentPane = getContentPane();
        contentPane.setLayout(new BoxLayout(BoxLayout.Y_AXIS));

        contentPane.addComponent(emailField);
        contentPane.addComponent(passwordField);
        contentPane.addComponent(loginButton);

        loginButton.addActionListener(evt -> {
            // validate user credentials
            String email = emailField.getText();
            String password = passwordField.getText();

            if (email.isEmpty() || password.isEmpty()) {
                Dialog.show("Error", "Please enter your email and password", "OK", null);
            } else {
                // check if user exists and credentials are valid
                Utilisateur user = getUserByEmail(email);
                if (user != null && user.getPassword().equals(password)) {
                    // redirect to home page
                    ItemsList homePage = new ItemsList(this);
                    homePage.show();
                } else {
                    Dialog.show("Error", "Invalid email or password", "OK", null);
                }
            }
        });
    }

    private Utilisateur getUserByEmail(String email) {
        Utilisateur user = null;
        // search for user in database or server using email
        // if found, set user to the retrieved user object
        return user;
    }
}
