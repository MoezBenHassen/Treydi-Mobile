/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.entities;

import com.mycompany.utils.Roles;

/**
 *
 * @author MSI
 */
public class Trader extends Utilisateur{
    private int score;
    private String  date_naissance;



    public Trader(String password, String nom, String prenom, String email, String adresse, String avatar_url, Roles role, int score, String date_naissance) {
        super(password, nom, prenom, email, adresse, avatar_url, role);
        this.score = score;
        this.date_naissance = date_naissance;
    }

    public Trader(String password, String nom, String prenom, String email, String adresse, String avatar_url, Roles role, int score,int archived, String date_naissance) {
        super(password, nom, prenom, email, adresse, avatar_url, role,archived);
        this.score = score;
        this.date_naissance = date_naissance;
    }
    public Trader(String password, String nom, String prenom, String email, String adresse, String avatar_url, Roles role, int score, String date_naissance,int id_user,int archived) {
        super(password, nom, prenom, email, adresse, avatar_url, role,id_user,archived);
        this.score = score;
        this.date_naissance = date_naissance;
    }

    public Trader(String password, String nom, String prenom, String email, String adresse, String avatar_url, Roles role, int score, String date_naissance,int id_user) {
        super(password, nom, prenom, email, adresse, avatar_url, role,id_user);
        this.score = score;
        this.date_naissance = date_naissance;
    }
    public Trader(String password, String nom, String prenom, String email, String adresse, String avatar_url, Roles role, int id_user,int archived) {
        super(password, nom, prenom, email, adresse, avatar_url, role, id_user, archived);
    }
    public Trader() {

    }

    public Trader(int id_user) {
        super(id_user);
    }


    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getDate_naissance() {
        return date_naissance;
    }

    public void setDate_naissance(String date_naissance) {
        this.date_naissance = date_naissance;
    }

    @Override
    public String toString() {
        return "\n"+super.toString() + ", " +
                "score=" + score +
                ", date_naissance=" + date_naissance +
                '}';
    }
    
}
