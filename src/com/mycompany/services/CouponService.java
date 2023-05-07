/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.services;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkManager;
import com.mycompany.utils.Statics;
import com.mycompany.entities.Coupon;
 
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
    
    public void ajoutCoupon (Coupon coupon) {
        String url= Statics.BASE_URL+"http://127.0.0.1:8000/addcoupon?titre_coupon"+coupon.getTitre_coupon()+"&description_coupon"+coupon.getDescription_coupon()+"&code"+coupon.getCode()+"&date_expiration"+coupon.getDate_expiration()+"&etat_coupon"+coupon.getEtat_coupon();
        req.setUrl(url);
        req.addResponseListener((e) -> {
            String str= new String(req.getResponseData());
            System.out.println("data==" +str);
            
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        }
                
    }
