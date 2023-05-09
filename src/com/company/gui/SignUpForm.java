package com.company.gui;


import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;

import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.services.ServiceUtilisateur;
import java.util.Vector;

public class SignUpForm extends Form {
    private Button signUpButton;
    
    public SignUpForm(Resources res) {
        super("Sign Up", new BoxLayout(BoxLayout.Y_AXIS));
        
                
        TextField nom = new TextField("", "Nom", 20, TextField.ANY);
        TextField prenom = new TextField("", "Prenom", 20, TextField.ANY);
        TextField adresse = new TextField("", "Adresse", 20, TextField.ANY);
        TextField email = new TextField("", "E-Mail", 20, TextField.EMAILADDR);
        TextField password = new TextField("", "Password", 20, TextField.PASSWORD);
        TextField confirmPassword = new TextField("", "Confirm Password", 20, TextField.PASSWORD);
        
           //Role 
        //Vector 3ibara ala array 7atit fiha roles ta3na ba3d nzidouhom lel comboBox
        Vector<String> vectorRole;
        vectorRole = new Vector();
        
        vectorRole.add("Trader");
        vectorRole.add("Livreur");
        
        ComboBox<String>roles = new ComboBox<>(vectorRole);
        
        signUpButton = new Button("Sign Up");
        
        
        add(new Label("Create a new account"));
        add(nom);
        add(prenom);
        add(email);
        add(adresse);
        add(password);
        add(confirmPassword);
        add(roles);
        add(signUpButton);

        // Add action listener to the sign up button
        signUpButton.addActionListener((evt) -> {
    // Get the text field values
        String nomValue = nom.getText();
        String prenomValue = prenom.getText();
        String adresseValue = adresse.getText();
        String emailValue = email.getText();
        String passwordValue = password.getText();
        String confirmPasswordValue = confirmPassword.getText();
        String roleValue = roles.getSelectedItem();
    
    // Call the signup method with the text field objects
        ServiceUtilisateur.getInstance().signup(nom, prenom, adresse, password, email, confirmPassword, roles, res);
    
        Dialog.show("Success", "Account is saved", "OK", null);
    new SignInForm(res).show();
});
    }
}
