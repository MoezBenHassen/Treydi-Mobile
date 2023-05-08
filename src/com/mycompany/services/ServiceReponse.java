/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.mycompany.entities.Reponse;
import com.mycompany.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author MSI
 */
public class ServiceReponse {
    
       public static ServiceReponse instance = null ;
    
    public static boolean resultOk = true;

    //initilisation connection request 
    private ConnectionRequest req;
    
    
    public static ServiceReponse getInstance() {
        if(instance == null )
            instance = new ServiceReponse();
        return instance ;
    }
    
    
    
    public ServiceReponse() {
        req = new ConnectionRequest();
        
    }
    
      public ArrayList<Reponse>affichageReponse(int idr) {
        ArrayList<Reponse> result = new ArrayList<>();
        
        String url = Statics.BASE_URL+"/reponse/listm";
        req.setUrl(url);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                JSONParser jsonp ;
                jsonp = new JSONParser();
                
                try {
                    Map<String,Object>mapReponse = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
                    System.out.println(mapReponse);
                        System.out.println(mapReponse);System.out.println(mapReponse);
                    List<Map<String,Object>> listOfMapss =  (List<Map<String,Object>>) mapReponse.get("repon"); 
                    System.out.println(listOfMapss);
                    for(Map<String, Object> obj : listOfMapss) {
                        Reponse re = new Reponse();
                        String objet = (String) obj.get("titre_reponse");
                        
                        String description = (String) obj.get("description_reponse");
                        
                        float id = Float.parseFloat(obj.get("id").toString());
                        float idreclamation = Float.parseFloat(obj.get("id").toString());
                        re.setTitre_reponse(objet);
                        re.setDescription_reponse(description);
                        re.setId_reponse((int)id);
                        re.setId_reclamation((int)idreclamation);

                        if(idr  ==    idreclamation){
                        result.add(re);
                        }
                            
                    }
                    
                }catch(IOException ex) {
                   
                    ex.printStackTrace();
                }
            
            }
        });
        
      NetworkManager.getInstance().addToQueueAndWait(req);
        
        return result;
        
        
    }
    
    
    
    
    
    
    
}
