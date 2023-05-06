package com.mycompany.entities;

import com.mycompany.Utils.Enums.Roles;
import javafx.scene.image.Image;

public class Utilisateur {
    private int id_user;
    private String password,nom,prenom,email,adresse,avatar_url;
    public  Roles role;
    private int archived;
    public Utilisateur(String password, String nom, String prenom, String email, String adresse, String avatar_url, Roles role) {

        this.password = password;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.adresse = adresse;
        this.avatar_url = avatar_url;
        this.role = role;
    }

    public  Utilisateur(){

    }
    public Utilisateur(String password, String nom, String prenom, String email, String adresse, String avatar_url,Roles role,int archived) {
        this.password = password;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.adresse = adresse;
        this.avatar_url = avatar_url;
        this.role= role;
        this.archived = archived;

    }

    public Utilisateur( String password, String nom, String prenom, String email, String adresse, String avatar_url, Roles role,int id_user, int archived) {
        this.password = password;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.adresse = adresse;
        this.avatar_url = avatar_url;
        this.role = role;
        this.id_user = id_user;
        this.archived = archived;
    }
    public Utilisateur( String password, String nom, String prenom, String email, String adresse, Roles role) {
        this.password = password;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.adresse = adresse;
        this.role = role;
    }
    public Utilisateur( String password, String nom, String prenom, String email, String adresse, int id_user) {
        this.password = password;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.adresse = adresse;
        this.id_user = id_user;
    }
    public Utilisateur(int id_user) {
        this.id_user = id_user;
    }

    public Utilisateur(String password, String nom, String prenom, String avatarUrl, int id_user, int archived) {
        this.password = password;
        this.nom = nom;
        this.prenom = prenom;
        this.id_user = id_user;
        this.archived = archived;
    }

    public Utilisateur(String email, String password, String role) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }


    public int getArchived() {
        return archived;
    }

    public void setArchived(int archived) {
        this.archived = archived;
    }
    public void setId_user(int id_user) {
        this.id_user = id_user;
    }
    public int getId_user() {
        return id_user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;

    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }


    public String getAvatar_url() {
        return avatar_url;
    }

    public void setAvatar_url(String avatar_url) {
        this.avatar_url = avatar_url;
    }

    public Roles getRole() {
        return role;
    }

    public  void setRole(Roles role) {
        this.role = role;
    }


    @Override
    public String toString() {
        return "\nutilisateur{" +
                "id_user=" + id_user +
                ", password='" + password + '\'' +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", email='" + email + '\'' +
                ", adresse='" + adresse + '\'' +
                ", avatar_url='" + avatar_url + '\'' +
                ", role=" + role +
                '}';
    }
}