/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.services;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkManager;
import com.mycompany.entities.Coupon;
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
     String baseUrl = "http://localhost:8000/";
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
    }
