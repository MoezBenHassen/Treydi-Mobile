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
import com.mycompany.entities.Echange;
import com.mycompany.entities.Item;
import com.mycompany.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Marayed
 */
public class ServiceEchange {

    public static ServiceEchange instance = null;
    public static boolean resultOk = true;

    private ConnectionRequest req;

    public static ServiceEchange getInstance() {
        if (instance == null) {
            instance = new ServiceEchange();
        }
        return instance;
    }

    public ServiceEchange() {
        req = new ConnectionRequest();

    }

    public ArrayList<Echange> affichageEcahnges() {
        ArrayList<Echange> result = new ArrayList<>();

        String url = Statics.BASE_URL + "/echange/mobile/list";
        req.setUrl(url);

        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                JSONParser jsonp;
                jsonp = new JSONParser();

                try {
                    Map<String, Object> mapEchange = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
                    System.out.println(mapEchange);

                    List<Map<String, Object>> listOfMaps = (List<Map<String, Object>>) mapEchange.get("echanges");
                    for (Map<String, Object> obj : listOfMaps) {
                        Echange echange = new Echange();
                        
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

                        //dima id fi codename one float 5outhouha
                        String titre = (String) obj.get("titre_echange");
                        echange.setTitre_echange(titre);
                        float id_echange = Float.parseFloat(obj.get("id").toString());
                        echange.setId_echange((int) id_echange);
                        float id_user1 = Float.parseFloat(obj.get("user1").toString());
                        echange.setId_user1((int) id_user1);
                        float id_user2 = Float.parseFloat(obj.get("user2").toString());
                        echange.setId_user2((int) id_user2);
                        echange.setItems(items);

                        //insert data into ArrayList result
                        result.add(echange);
                        //result.add(items);

                    }

                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });

        NetworkManager.getInstance().addToQueueAndWait(req);//execution ta3 request sinon yet3ada chy dima nal9awha

        return result;

    }
    
     public ArrayList<Echange> affichageEcahngesLivreur() {
        ArrayList<Echange> result = new ArrayList<>();

        String url = Statics.BASE_URL + "/echange/mobile/listLivreur";
        req.setUrl(url);

        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                JSONParser jsonp;
                jsonp = new JSONParser();

                try {
                    Map<String, Object> mapEchange = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
                    System.out.println(mapEchange);

                    List<Map<String, Object>> listOfMaps = (List<Map<String, Object>>) mapEchange.get("echanges");
                    for (Map<String, Object> obj : listOfMaps) {
                        Echange echange = new Echange();
                        
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

                        //dima id fi codename one float 5outhouha
                        String titre = (String) obj.get("titre_echange");
                        echange.setTitre_echange(titre);
                        float id_echange = Float.parseFloat(obj.get("id").toString());
                        echange.setId_echange((int) id_echange);
                        float id_user1 = Float.parseFloat(obj.get("user1").toString());
                        echange.setId_user1((int) id_user1);
                        float id_user2 = Float.parseFloat(obj.get("user2").toString());
                        echange.setId_user2((int) id_user2);
                        echange.setItems(items);

                        //insert data into ArrayList result
                        result.add(echange);
                        //result.add(items);

                    }

                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });

        NetworkManager.getInstance().addToQueueAndWait(req);//execution ta3 request sinon yet3ada chy dima nal9awha

        return result;

    }

}
