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
import com.company.gui.SessionManager;
import com.mycompany.entities.Echange;
import com.mycompany.entities.Item;
import com.mycompany.entities.Livraison;
import com.mycompany.entities.Utilisateur;
import com.mycompany.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Marayed
 */
public class ServiceLivraison {

    public static ServiceLivraison instance = null;

    public static boolean resultOk = true;

    // initialization of connection request
    private ConnectionRequest req;

    public static ServiceLivraison getInstance() {
        if (instance == null) {
            instance = new ServiceLivraison();
        }
        return instance;
    }

    public ServiceLivraison() {
        req = new ConnectionRequest();
    }

    public void accepterLivraison(int id) {
        String url = Statics.BASE_URL + "/livraison/addm";
        ConnectionRequest req = new ConnectionRequest();
        req.setUrl(url);
        req.setPost(true);
        req.addArgument("id_echange", String.valueOf(id));
        req.addArgument("id_user", String.valueOf(SessionManager.getId()));
        req.addResponseListener((e) -> {
            String str = new String(req.getResponseData());
            System.out.println("data == " + str);
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
    }
    
    public boolean supprimerLivraison(int id, int id_echange) {

        String url = Statics.BASE_URL + "/livraison/mobile/delete";
        req.setUrl(url);
        req.setPost(true);
        req.addArgument("id", String.valueOf(id));
        req.addArgument("id_echange", String.valueOf(id_echange));
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
    
        public boolean terminerLivraison(int id) {

        String url = Statics.BASE_URL + "/livraison/mobile/terminer";
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
    
    

    public ArrayList<Livraison> listMesLivraisonLivreur() {
        ArrayList<Livraison> result = new ArrayList<>();
        String url = Statics.BASE_URL + "/livraison/mobile/livreur/meslivraisons";
        req.setUrl(url);
        req.addArgument("id", String.valueOf(SessionManager.getId()));
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                JSONParser jsonp;
                jsonp = new JSONParser();
                try {
                    Map<String, Object> mapMesLivraisons = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
                    List<Map<String, Object>> listOfMaps = (List<Map<String, Object>>) mapMesLivraisons.get("meslivraison");
                    for (Map<String, Object> obj : listOfMaps) {
                        Livraison livraison = new Livraison();

                        Map<String, Object> echangeMap = (Map<String, Object>) obj.get("echange");
                        Map<String, Object> user1Map = (Map<String, Object>) obj.get("user1");
                        Map<String, Object> user2Map = (Map<String, Object>) obj.get("user2");
                        //user1
                        Utilisateur user1 = new Utilisateur();
                        user1.setId_user((int) Float.parseFloat(user1Map.get("id").toString()));
                        user1.setPrenom((String) user1Map.get("prenom"));
                        user1.setAdresse((String) user1Map.get("adresse"));
                        
                        //user2
                        Utilisateur user2 = new Utilisateur();
                        user2.setId_user((int) Float.parseFloat(user2Map.get("id").toString()));
                        user2.setPrenom((String) user2Map.get("prenom"));
                        user2.setAdresse((String) user2Map.get("adresse"));
                        
                        //echange
                        Echange echange = new Echange();
                        
                        echange.setId_echange((int) Float.parseFloat(echangeMap.get("id").toString()));
                        echange.setTitre_echange((String) echangeMap.get("titre_echange"));
                        echange.setId_user1((int) Float.parseFloat(echangeMap.get("id_user1").toString()));
                        echange.setId_user2((int) Float.parseFloat(echangeMap.get("id_user2").toString()));
                        
                        //items
                        List<Map<String, Object>> itemMaps = (List<Map<String, Object>>) obj.get("echange_items");
                        List<Item> items = new ArrayList<>();
                        for (Map<String, Object> itemMap : itemMaps) {
                            Item item = new Item();
                            float id = Float.parseFloat(itemMap.get("id").toString());
                            item.setId_item((int) id);
                            float id_echange = Float.parseFloat(itemMap.get("id_echange").toString());
                            item.setId_echange((int) id_echange);
                            float id_user = Float.parseFloat(itemMap.get("id_user").toString());
                            item.setId_user((int) id_user);
                            item.setLibelle((String) itemMap.get("libelle"));
                            // set other item properties as needed
                            items.add(item);
                        }
                        
                        echange.setUser1(user1);
                        echange.setUser2(user2);

                        float id_livraison = Float.parseFloat(obj.get("id").toString());
                        livraison.setId_livraison((int) id_livraison);

                        float id_livreur = Float.parseFloat(obj.get("id_livreur").toString());
                        livraison.setId_livreur((int) id_livreur);

                        float id_echange = Float.parseFloat(obj.get("id_echange").toString());
                        livraison.setId_echange((int) id_echange);

                        String date_creation_livraison = obj.get("date_creation_livraison").toString();
                        livraison.setDate_creation_livraison(date_creation_livraison);

                        String date_terminer_livraison = obj.get("date_terminer_livraison").toString();
                        livraison.setDate_terminer_livraison(date_terminer_livraison);

                        String etat_lvraison = obj.get("etat_livraison").toString();
                        livraison.setEtat(etat_lvraison);

                        String adresse_livraison1 = (String) obj.get("adresse_livraison1");
                        livraison.setAdresse_livraison1((String) adresse_livraison1);

                        String adresse_livraison2 = (String) obj.get("adresse_livraison2");
                        livraison.setAdresse_livraison2((String) adresse_livraison2);
                        
                        echange.setItems(items);
                        
                        livraison.setEchange(echange);

                        result.add(livraison);

                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        System.out.println(result);
        return result;
    }
}
