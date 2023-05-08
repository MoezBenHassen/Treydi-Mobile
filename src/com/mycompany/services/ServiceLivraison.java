/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.services;

import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkManager;
import com.mycompany.utils.Statics;

/**
 *
 * @author Marayed
 */
public class ServiceLivraison {

    public static ServiceLivraison instance = null;

    public static boolean resultOk = true;

    //initilisation connection request 
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
    req.addArgument("id", String.valueOf(id));

    req.addResponseListener((e) -> {
        String str = new String(req.getResponseData());
        System.out.println("data == " + str);
    });

    NetworkManager.getInstance().addToQueueAndWait(req);
}

}
