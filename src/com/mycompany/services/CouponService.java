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
import com.codename1.ui.Form;
import com.codename1.ui.events.ActionListener;
import com.mycompany.entities.Coupon;
import com.mycompany.entities.Trader;
import com.mycompany.entities.Utilisateur;
import com.mycompany.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.List;
import java.util.Random;
 
public class CouponService {
    public static CouponService instance=null;
    
    public ConnectionRequest req;
    
    public static CouponService getInstance(){
        if (instance==null) 
            instance= new CouponService();
           return instance;
        
           
    }
    
    public CouponService() {
        req= new ConnectionRequest();
        
}
    
    public boolean ajoutCoupon (Coupon coupon) {
     String baseUrl = "http://192.168.1.8:8000/";
     String addCouponRoute = "addcoupon";
     String url = baseUrl + addCouponRoute + "?titre_coupon=" + coupon.getTitre_coupon() + "&description_coupon=" + coupon.getDescription_coupon() + "&code=" + coupon.getCode() + "&date_expiration=" + coupon.getDate_expiration() + "&etat_coupon=" + coupon.getEtat_coupon()+ "&id_user_id=" + coupon.getId_user()+ "&id_categorie_id="+ coupon.getId_categorie();


        req.setUrl(url);
        req.addResponseListener((e) -> {
            String str= new String(req.getResponseData());
            System.out.println("data==" +str);
            
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return true;
        }
                
     public String affecterCouponCasual () {
     Random random = new Random();
    int randomNumber = random.nextInt(1000) + 1;
    String code = "CasCouponMai" + randomNumber;
    Coupon coupon = new Coupon("Coupon Casual Mai", "50% sur la livraison", "VALID", code,"2023-06-01",1,1 );
    
     
     String baseUrl1 = "http://localhost:8000/";
     String addCouponRoute1 = "addcoupon";
     String url1 = baseUrl1 + addCouponRoute1 + "?titre_coupon=" + coupon.getTitre_coupon() + "&description_coupon=" + coupon.getDescription_coupon() + "&code=" + coupon.getCode() + "&date_expiration=" + coupon.getDate_expiration() + "&etat_coupon=" + coupon.getEtat_coupon()+ "&id_user_id=" + coupon.getId_user()+ "&id_categorie_id="+ coupon.getId_categorie();


        req.setUrl(url1);
        req.addResponseListener((e) -> {
            String str= new String(req.getResponseData());
            System.out.println("data==" +str);
            
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return code;
        }
     
      public String affecterCouponSpecial () {
     Random random = new Random();
    int randomNumber = random.nextInt(1000) + 1;
    String code = "SpecCouponMai" + randomNumber;
    Coupon coupon = new Coupon("Coupon Special Mai", "100% sur la livraison", "VALID", code,"2023-06-01",1,1 );
    
     
     String baseUrl1 = "http://localhost:8000/";
     String addCouponRoute1 = "addcoupon";
     String url1 = baseUrl1 + addCouponRoute1 + "?titre_coupon=" + coupon.getTitre_coupon() + "&description_coupon=" + coupon.getDescription_coupon() + "&code=" + coupon.getCode() + "&date_expiration=" + coupon.getDate_expiration() + "&etat_coupon=" + coupon.getEtat_coupon()+ "&id_user_id=" + coupon.getId_user()+ "&id_categorie_id="+ coupon.getId_categorie();


        req.setUrl(url1);
        req.addResponseListener((e) -> {
            String str= new String(req.getResponseData());
            System.out.println("data==" +str);
            
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return code;
        }
      
    public String affecterCouponExclusif () {
    Random random = new Random();
    int randomNumber = random.nextInt(1000) + 1;
    String code = "ExcluCouponMai" + randomNumber;
    Coupon coupon = new Coupon("Coupon Exclusif Mai", "Carte de recharge gratuite", "VALID", code,"2023-06-01",1,1 );
    
     
     String baseUrl1 = "http://localhost:8000/";
     String addCouponRoute1 = "addcoupon";
     String url1 = baseUrl1 + addCouponRoute1 + "?titre_coupon=" + coupon.getTitre_coupon() + "&description_coupon=" + coupon.getDescription_coupon() + "&code=" + coupon.getCode() + "&date_expiration=" + coupon.getDate_expiration() + "&etat_coupon=" + coupon.getEtat_coupon()+ "&id_user_id=" + coupon.getId_user()+ "&id_categorie_id="+ coupon.getId_categorie();


        req.setUrl(url1);
        req.addResponseListener((e) -> {
            String str= new String(req.getResponseData());
            System.out.println("data==" +str);
            
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return code;
        }
    
    
     public ArrayList<Coupon>MyCoupons() {
        ArrayList<Coupon> result = new ArrayList<>();
        
        String url = Statics.BASE_URL+"/displaycouponuser";
        req.setUrl(url);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                JSONParser jsonp ;
                jsonp = new JSONParser();
                
                try {
                    Map<String,Object>mapCoupons = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
                    System.out.println(mapCoupons);
                    
                    List<Map<String,Object>> listOfMaps =  (List<Map<String,Object>>) mapCoupons.get("coupons"); 
                    System.out.println(listOfMaps);
                     for(Map<String, Object> obj : listOfMaps) { 
                        
                        Coupon c = new Coupon();

                        String objet = (String) obj.get("titre_coupon");
                        String description = (String) obj.get("description_coupon");
                        String code = (String) obj.get("code");
                        String etat = (String) obj.get("etat_coupon");
                        
                 
                        c.setTitre_coupon(objet);
                        c.setDescription_coupon(description);
                        c.setCode(code);
                        c.setEtat_coupon(etat);
                        result.add(c);
                    
                     } 
                }catch(IOException ex) {
                   
                    ex.printStackTrace();
                }
            
            }
        });
        
      NetworkManager.getInstance().addToQueueAndWait(req);//execution ta3 request sinon yet3ada chy dima nal9awha
        
        return result;
        
        
    }
     
     
      public ArrayList<Trader>Scoreboard() {
        ArrayList<Trader> result = new ArrayList<>();
        
        String url = Statics.BASE_URL+"/scores";
        req.setUrl(url);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                JSONParser jsonp ;
                jsonp = new JSONParser();
                
                try {
                    Map<String,Object>mapusers = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
                    System.out.println(mapusers);
                    
                    List<Map<String,Object>> listOfMaps =  (List<Map<String,Object>>) mapusers.get("users"); 
                    System.out.println(listOfMaps);
                     for(Map<String, Object> obj : listOfMaps) { 
                        
                        Trader u = new Trader();

                        String nom = (String) obj.get("nom");
                        String prenom = (String) obj.get("prenom");
                        Object scoreObj = obj.get("score");
                        float score = (scoreObj != null) ? Float.parseFloat(scoreObj.toString()) : 0.0f;
                                              
                        u.setNom(nom);
                        u.setPrenom(prenom);
                        u.setScore((int)score);
                      
                        result.add(u);
                    
                     } 
                }catch(IOException ex) {
                   
                    ex.printStackTrace();
                }
            
            }
        });
        
      NetworkManager.getInstance().addToQueueAndWait(req);//execution ta3 request sinon yet3ada chy dima nal9awha
        
        return result;
        
        
    }
    
    }

        