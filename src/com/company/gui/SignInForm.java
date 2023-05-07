package com.company.gui;

import com.codename1.ui.Button;

import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.TextField;
import com.codename1.ui.Dialog;
import com.codename1.ui.util.Resources;
import com.mycompany.services.ServiceUtilisateur;

public class SignInForm extends Form {
    
    private TextField emailField;
    private TextField passwordField;
    private Button signInButton;
    private Resources theme;
    
    public SignInForm(Resources theme) {
        
        this.theme = theme;
        
        setTitle("Sign In");
        setLayout(new BorderLayout());
        Container signInContainer = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        
        emailField = new TextField("", "Email", 20, TextField.ANY);
        passwordField = new TextField("", "Password", 20, TextField.PASSWORD);
        signInButton = new Button("Sign In");
        Button signUpButton = new Button("Sign Up");
        signUpButton.addActionListener((evt) -> {
        new SignUpForm(theme).show();
        })  ; 
        
        signInContainer.add(emailField);
        signInContainer.add(passwordField);
        signInContainer.add(signInButton);
        signInContainer.add(signUpButton);
        
        signInButton.addActionListener((evt) -> {
            if (emailField.getText().isEmpty() || passwordField.getText().isEmpty()) {
                Dialog.show("Error", "Please fill all the fields", "OK", null);
            } else {
                ServiceUtilisateur.getInstance().signin(emailField, passwordField, theme);
            }
        });
        
        addComponent(BorderLayout.CENTER, signInContainer);
    }
}
