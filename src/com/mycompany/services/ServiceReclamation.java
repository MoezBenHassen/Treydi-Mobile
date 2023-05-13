/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.services;




import com.mycompany.entities.Reclamation;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.company.gui.SessionManager;
import com.mycompany.utils.Statics;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class ServiceReclamation {
     public static ServiceReclamation instance = null ;
    
    public static boolean resultOk = true;

    //initilisation connection request 
    private ConnectionRequest req;
    
    
    public static ServiceReclamation getInstance() {
        if(instance == null )
            instance = new ServiceReclamation();
        return instance ;
    }
    
    
    
    public ServiceReclamation() {
        req = new ConnectionRequest();
        
    }
    
    public void ajoutReclamation(String a, String b) {
        String url = Statics.BASE_URL + "/reclamation/addUserm??id_user="+SessionManager.getId()+"&titre_reclamation=" + a + "&description_reclamation=" + b ;
        System.out.println(SessionManager.getId());
        req.setUrl(url);
        String i = String.valueOf(SessionManager.getId());
        System.out.println(i);
        req.addArgument("id_user",i);
        req.addResponseListener((e) -> {
            String str = new String(req.getResponseData());
            System.out.println("data == " + str);
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
    }



     public ArrayList<Reclamation>affichageReclamations() {
        ArrayList<Reclamation> result = new ArrayList<>();
        
        String url = Statics.BASE_URL+"/reclamation/listm?id_user="+SessionManager.getId();
        req.setUrl(url);
        // req.addArgument("id_user", String.valueOf(SessionManager.getId()));
         System.out.println("dddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddd"+SessionManager.getId());
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                JSONParser jsonp ;
                jsonp = new JSONParser();
                
                try {
                    Map<String,Object>mapReclamations = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
                    System.out.println(mapReclamations);
                        
                    List<Map<String,Object>> listOfMaps =  (List<Map<String,Object>>) mapReclamations.get("recs"); 
                    System.out.println(listOfMaps);
                    for(Map<String, Object> obj : listOfMaps) {
                        Reclamation re = new Reclamation();
                        
                       
                    
                        
                        String objet = (String) obj.get("titre_reclamation");
                        
                        String description = (String) obj.get("description_reclamation");
                        
                        float id = Float.parseFloat(obj.get("id").toString());
                        
                        re.setTitre_reclamation(objet);
                        re.setDescription(description);
                        re.setId_reclamation((int)id);
                        result.add(re);
                       
                    
                    }
                    
                }catch(IOException ex) {
                   
                    ex.printStackTrace();
                }
            
            }
        });
        
      NetworkManager.getInstance().addToQueueAndWait(req);
        
        return result;
        
        
    }
public boolean modifierReclamation(int id, String titre, String desc) {
    String url = Statics.BASE_URL + "/reclamation/updateUserm";
    req.setUrl(url);
    req.setPost(true);
    req.addArgument("id", String.valueOf(id));
    req.addArgument("titre_reclamation", titre);
    req.addArgument("description_reclamation", desc);
    
    req.addResponseListener(new ActionListener<NetworkEvent>() {
        @Override
        public void actionPerformed(NetworkEvent evt) {
            resultOk = req.getResponseCode() == 200;  
            req.removeResponseListener(this);
        }
    });
        
    NetworkManager.getInstance().addToQueueAndWait(req);
    return resultOk; 
}

public boolean supprimerReclamation(int id){

    String url = Statics.BASE_URL + "/reclamation/deletem";
    req.setUrl(url);
    req.setPost(true);
    req.addArgument("id", String.valueOf(id));
    req.addResponseListener(new ActionListener<NetworkEvent>() {
        @Override
        public void actionPerformed(NetworkEvent evt) {
            resultOk = req.getResponseCode() == 200;  
            req.removeResponseListener(this);
        }
    });
        
    NetworkManager.getInstance().addToQueueAndWait(req);
    return resultOk; 


}



}

   

