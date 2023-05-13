/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.entities;

/**
 *
 * @author Marayed
 */
import java.util.Date;

public class Livraison {

    int id_livraison, id_livreur, id_echange;
    String adresse_livraison1, adresse_livraison2;
    private String etat;
    String titre_echange;
    String date_creation_livraison, date_terminer_livraison;
    Echange echange;

    public Echange getEchange() {
        return echange;
    }

    public void setEchange(Echange echange) {
        this.echange = echange;
    }

    
    public Livraison() {}

    public Livraison(int id_livraison) {
        this.id_livraison = id_livraison;
    }

    public Livraison(int id_livreur, int id_echange, String adresse_livraison1, String etat) {
        this.id_livreur = id_livreur;
        this.id_echange = id_echange;
        this.adresse_livraison1 = adresse_livraison1;
        this.etat = etat;
    }

    public Livraison(int id_livraison, int id_livreur, int id_echange, String adresse_livraison1, String etat) {
        this.id_livraison = id_livraison;
        this.id_livreur = id_livreur;
        this.id_echange = id_echange;
        this.adresse_livraison1 = adresse_livraison1;
        this.etat = etat;
    }

    public Livraison(int id_livraison, int id_livreur, int id_echange, String adresse_livraison1, String adresse_livraison2, String etat) {
        this.id_livraison = id_livraison;
        this.id_livreur = id_livreur;
        this.id_echange = id_echange;
        this.adresse_livraison1 = adresse_livraison1;
        this.adresse_livraison2 = adresse_livraison2;
        this.etat = etat;
    }

    public Livraison(int id_livreur, int id_echange, String adresse_livraison1, String adresse_livraison2, String etat) {
        this.id_livraison = id_livraison;
        this.id_livreur = id_livreur;
        this.id_echange = id_echange;
        this.adresse_livraison1 = adresse_livraison1;
        this.adresse_livraison2 = adresse_livraison2;
        this.etat = etat;
    }
    public Livraison(int id_livraison, int id_livreur, int id_echange, String adresse_livraison1, String adresse_livraison2, String etat, String date_creation_livraison, String date_terminer_livraison) {
        this.id_livraison = id_livraison;
        this.id_livreur = id_livreur;
        this.id_echange = id_echange;
        this.adresse_livraison1 = adresse_livraison1;
        this.adresse_livraison2 = adresse_livraison2;
        this.etat = etat;
        this.date_creation_livraison = date_creation_livraison;
        this.date_terminer_livraison = date_terminer_livraison;
    }

    public Livraison(int id_livraison, int id_livreur, int id_echange, String adresse_livraison1, String adresse_livraison2, String etat, String titre_echange, String date_creation_livraison, String date_terminer_livraison) {
        this.id_livraison = id_livraison;
        this.id_livreur = id_livreur;
        this.id_echange = id_echange;
        this.adresse_livraison1 = adresse_livraison1;
        this.adresse_livraison2 = adresse_livraison2;
        this.etat = etat;
        this.titre_echange = titre_echange;
        this.date_creation_livraison = date_creation_livraison;
        this.date_terminer_livraison = date_terminer_livraison;
    }



    public String getTitre_echange() {
        return titre_echange;
    }

    public void setTitre_echange(String titre_echange) {
        this.titre_echange = titre_echange;
    }


    
    public String getAdresse_livraison2() {
        return adresse_livraison2;
    }

    public void setAdresse_livraison2(String adresse_livraison2) {
        this.adresse_livraison2 = adresse_livraison2;
    }

    public String getDate_terminer_livraison() {
        return date_terminer_livraison;
    }

    public void setDate_terminer_livraison(String date_terminer_livraison) {
        this.date_terminer_livraison = date_terminer_livraison;
    }

    public int getId_livraison() {
        return id_livraison;
    }

    public void setId_livraison(int id_livraison) {
        this.id_livraison = id_livraison;

    }

    public int getId_livreur() {
        return id_livreur;
    }

    public void setId_livreur(int id_livreur) {
        this.id_livreur = id_livreur;
    }

    public int getId_echange() {
        return id_echange;
    }

    public void setId_echange(int id_echange) {
        this.id_echange = id_echange;
    }

    public String getAdresse_livraison1() {
        return adresse_livraison1;
    }

    public void setAdresse_livraison1(String adresse_livraison1) {
        this.adresse_livraison1 = adresse_livraison1;
    }

    public String getDate_creation_livraison() {
        return date_creation_livraison;
    }

    public void setDate_creation_livraison(String date_creation_livraison) {
        this.date_creation_livraison = date_creation_livraison;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    @Override
    public String toString() {
        return "Livraison{" +
                "id_livraison=" + id_livraison +
                ", id_livreur=" + id_livreur +
                ", id_echange=" + id_echange +
                ", adresse_livraison1='" + adresse_livraison1 + '\'' +
                ", adresse_livraison2='" + adresse_livraison2 + '\'' +
                ", etat=" + etat +
                ", date_creation_livraison=" + date_creation_livraison +
                ", date_terminer_livraison=" + date_terminer_livraison +
                '}';
    }
}
