/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.company.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextArea;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.entities.Reclamation;
import com.mycompany.services.ServiceReclamation;

/**
 *
 * @author MSI
 */
public class UpdateReclamationForm extends Form {

    private TextArea descriptionArea;
    private Button saveButton;
    
    public UpdateReclamationForm(int id,String t,String d,Form previous) {
        setTitle("Modifier Réclamation");
        setLayout(new BoxLayout(BoxLayout.Y_AXIS));
       getToolbar().addMaterialCommandToLeftBar("Back", FontImage.MATERIAL_ARROW_BACK, ev -> {
            previous.showBack();
        });
        TextArea titre = new TextArea(t);
        
        add(titre);

        
        
        TextArea des = new TextArea(d);
        
        add(des);
        
        saveButton = new Button("Enregistrer");
        saveButton.addActionListener(e -> {
          String a = titre.getText();
         String b = des.getText();
        
                    System.out.println(a);
                   System.out.println(id);

            new ServiceReclamation().getInstance().modifierReclamation( id,a,b);
           
           
        });
        add(saveButton);
        
    }

    
}

