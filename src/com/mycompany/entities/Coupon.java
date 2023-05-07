/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.entities;

/**
 *
 * @author Kal
 */
public class Coupon {
    private int id ;
    private String titre_coupon ;
    private String description_coupon ;
    private String etat_coupon ;
    private String code ;
    private boolean archived;
    private String date_expiration ;
    private int id_user ;
    private int id_categorie ;

    public Coupon(String titre_coupon, String description_coupon, String etat_coupon, String code, String date_expiration, int id_categorie, int id_user) {
      
        this.titre_coupon = titre_coupon;
        this.description_coupon = description_coupon;
        this.etat_coupon = etat_coupon;
        this.code = code;
        this.date_expiration = date_expiration;
        this.id_categorie = id_categorie;
        this.id_user = id_user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitre_coupon() {
        return titre_coupon;
    }

    public void setTitre_coupon(String titre_coupon) {
        this.titre_coupon = titre_coupon;
    }

    @Override
    public String toString() {
        return "Coupon{" + "id=" + id + ", titre_coupon=" + titre_coupon + ", description_coupon=" + description_coupon + ", etat_coupon=" + etat_coupon + ", code=" + code + ", archived=" + archived + ", date_expiration=" + date_expiration + ", id_user=" + id_user + ", id_categorie=" + id_categorie + '}';
    }

    public String getDescription_coupon() {
        return description_coupon;
    }

    public void setDescription_coupon(String description_coupon) {
        this.description_coupon = description_coupon;
    }

    public String getEtat_coupon() {
        return etat_coupon;
    }

    public void setEtat_coupon(String etat_coupon) {
        this.etat_coupon = etat_coupon;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public boolean isArchived() {
        return archived;
    }

    public void setArchived(boolean archived) {
        this.archived = archived;
    }

    public String getDate_expiration() {
        return date_expiration;
    }

    public void setDate_expiration(String date_expiration) {
        this.date_expiration = date_expiration;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public int getId_categorie() {
        return id_categorie;
    }

    public void setId_categorie(int id_categorie) {
        this.id_categorie = id_categorie;
    }
    
    
}
