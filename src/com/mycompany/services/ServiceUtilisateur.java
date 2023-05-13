/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;

import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.ComboBox;
import com.mycompany.utils.Statics;
import com.codename1.ui.Dialog;
import com.codename1.ui.TextField;

import com.codename1.ui.events.ActionListener;
import com.codename1.ui.util.Resources;
import com.company.gui.Menu;
import com.company.gui.SessionManager;
import com.mycompany.entities.Utilisateur;

import java.util.Map;
import java.util.Vector;

/**
 *
 * @author Lenovo
 */
public class ServiceUtilisateur {
    
    
  //singleton 
    public static ServiceUtilisateur instance = null ;
    
    public static boolean resultOK = true;
    String json;
    

    //initilisation connection request 
    private ConnectionRequest req;
    
    
    public static ServiceUtilisateur getInstance() {
        if(instance == null )
            instance = new ServiceUtilisateur();
        return instance ;
    }
    
    
    
    public ServiceUtilisateur() {
        req = new ConnectionRequest();
        
    }
    
    //Signup
    public void signup(TextField nom,TextField prenom,TextField adresse,TextField password,TextField email,TextField confirmPassword, ComboBox<String> roles , Resources res ) {
        
     
        
        String url = Statics.BASE_URL+"/register/mob?nom="+nom.getText().toString()+"&prenom="+prenom.getText().toString()+
                "&adresse="+adresse.getText().toString()+"&email="+email.getText().toString()+"&password="+password.getText().toString()+
                "&roles="+roles.getSelectedItem().toString();

        
        req.setUrl(url);
       
        //Control saisi
        if(password.getText().equals(" ") && email.getText().equals(" ")) {
            
            Dialog.show("Erreur","Veuillez remplir les champs","OK",null);
            
        }
        
        //hethi wa9t tsir execution ta3 url 
        req.addResponseListener((e)-> {
         
            //njib data ly7atithom fi form 
            byte[]data = (byte[]) e.getMetaData();//lazm awl 7aja n7athrhom ke meta data ya3ni na5o id ta3 kol textField 
            String responseData = new String(data);//ba3dika na5o content 
            
            System.out.println("data ===>"+responseData);
        }
        );
        
        
        //ba3d execution ta3 requete ely heya url nestanaw response ta3 server.
        NetworkManager.getInstance().addToQueueAndWait(req);
        
            
        
    }
    
    
    //SignIn
    
    public void signin(TextField email,TextField password, Resources rs ) {
        
        
        String url = Statics.BASE_URL+"/login/mob?email="+email.getText().toString()+"&password="+password.getText().toString();
        req = new ConnectionRequest(url, false); //false ya3ni url mazlt matba3thtich lel server
        req.setUrl(url);
        
        req.addResponseListener((e) ->{
            
            JSONParser j = new JSONParser();
            
            String json = new String(req.getResponseData()) + "";
            
            
            try {
            
            if(json.equals("failed")) {
                Dialog.show("Echec d'authentification","Email ou mot de passe éronné","OK",null);
            }
            else {
                System.out.println("data =="+json);
                
                Map<String,Object> user = j.parseJSON(new CharArrayReader(json.toCharArray()));
                
                
             
                //Session 
                float id = Float.parseFloat(user.get("id").toString());

               
                SessionManager.setId((int)id);
                
                SessionManager.setPassword(user.get("password").toString());
                SessionManager.setEmail(user.get("email").toString());
                if (user.get("nom") == null) {
                SessionManager.setNom("");
                } else {
                SessionManager.setNom(user.get("nom").toString());
                }
                
                 // check if prenom value is null
                if (user.get("prenom") == null) {
                SessionManager.setPrenom("");
                } else {
                SessionManager.setPrenom(user.get("prenom").toString());
                }

    // check if adresse value is null
                if (user.get("adresse") == null) {
               SessionManager.setAdresse("");
                } else {
               SessionManager.setAdresse(user.get("adresse").toString());
                }
                
                if (user.get("score") == null) {
                 SessionManager.setScore(0);
                } else {
                float score = Float.parseFloat(user.get("score").toString());
                SessionManager.setScore((int)score);
                }

     
                
                if(user.size() >0 ) // l9a user
                   // new ListReclamationForm(rs).show();//yemchi lel list reclamation
                    new Menu().show();
                    
                    }
            
            }catch(Exception ex) {
                ex.printStackTrace();
            }
            
            
            
        });
        
        
        
        NetworkManager.getInstance().addToQueueAndWait(req);
        System.out.println(SessionManager.getEmail());
        System.out.println(SessionManager.getId());
        System.out.println(SessionManager.getPassword());
        System.out.println(SessionManager.getNom());
        System.out.println(SessionManager.getPrenom());
        System.out.println(SessionManager.getScore());
        System.out.println(SessionManager.getAdresse());
        

    }
    

 public String getPasswordByEmail(String email, Resources rs ) {
        
        
        String url = Statics.BASE_URL+"/login/getpasswordbyemail?email="+email;
        req = new ConnectionRequest(url, false); //false ya3ni url mazlt matba3thtich lel server
        req.setUrl(url);
        
        req.addResponseListener((e) ->{
            
            JSONParser j = new JSONParser();
            
             json = new String(req.getResponseData()) + "";
            
            
            try {
            
          
                System.out.println("data =="+json);
                
                Map<String,Object> password = j.parseJSON(new CharArrayReader(json.toCharArray()));
                
                
            
            
            }catch(Exception ex) {
                ex.printStackTrace();
            }
            
            
            
        });
    
         //ba3d execution ta3 requete ely heya url nestanaw response ta3 server.
        NetworkManager.getInstance().addToQueueAndWait(req);
    return json;
    }

    public boolean modifierUser( int id ,String nom, String prenom, String adresse, String password) {
    String url = Statics.BASE_URL + "/updateUserm";
    req.setUrl(url);
    req.setPost(true);
    
    id = SessionManager.getId();
    
    req.addArgument("id", String.valueOf(id));
    req.addArgument("nom", nom);
    req.addArgument("prenom", prenom);
    req.addArgument("adresse", adresse);
    req.addArgument("password", password);
    
    req.addResponseListener(new ActionListener<NetworkEvent>() {
        @Override
        public void actionPerformed(NetworkEvent evt) {
            resultOK = req.getResponseCode() == 200;  
            req.removeResponseListener(this);
        }
    });
        
    NetworkManager.getInstance().addToQueueAndWait(req);
    return resultOK; 
}



    



}
