package com.company.gui;

import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.entities.Utilisateur;

/**
 * A form for creating a new user account.
 */
public class SignUpForm extends Form {

    private TextField emailField;
    private TextField passwordField;
    private ComboBox<String> roleComboBox;
    private Button signupButton;

    public SignUpForm() {
        super("Sign up");

        // Initialize components
        emailField = new TextField("", "Email", 20, TextField.EMAILADDR);
        passwordField = new TextField("", "Password", 20, TextField.PASSWORD);
        roleComboBox = new ComboBox<>(new String[]{"Trader", "Livreur"});
        signupButton = new Button("Sign up");

        // Add components to content pane
        Container contentPane = getContentPane();
        contentPane.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
        contentPane.addComponent(emailField);
        contentPane.addComponent(passwordField);
        contentPane.addComponent(roleComboBox);
        contentPane.addComponent(signupButton);

        // Add event listener for sign-up button
        signupButton.addActionListener(evt -> {
            // Validate input fields
            String email = emailField.getText();
            String password = passwordField.getText();
            String role = roleComboBox.getSelectedItem();
            if (email.isEmpty() || password.isEmpty() || role == null) {
                Dialog.show("Error", "Please fill in all fields", "OK", null);
            } else {
                // Create new user account
                Utilisateur newUser = new Utilisateur(email, password, role);
                // Save user account to database or server
                // TODO: Add code here to save user account

                // Redirect to login page
                LoginForm loginForm = new LoginForm();
                loginForm.show();
            }
        });
    }
}
