/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.entities;

import java.util.Date;

/**
 *
 * @author SBS
 */
public class Article {
    private int id;
    private String titre, description, contenu;
    private Date date_publication;
    private int id_categorie;
    private int archived;
    private int id_user;
    private String auteur;
    private float AvgRating;
    private String image;

    public Article(String titre, String description, String contenu, String image) {
        this.titre = titre;
        this.description = description;
        this.contenu = contenu;
        this.image = image;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
    
    public Article(String titre, String description, String contenu) {
        this.titre = titre;
        this.description = description;
        this.contenu = contenu;
    }

    public Article() {
    }
    
    public Article(String title, String description) {
       this.titre = title;
       this.description = description;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public Date getDate_publication() {
        return date_publication;
    }

    public void setDate_publication(Date date_publication) {
        this.date_publication = date_publication;
    }

    public int getId_categorie() {
        return id_categorie;
    }

    public void setId_categorie(int id_categorie) {
        this.id_categorie = id_categorie;
    }

    public int getArchived() {
        return archived;
    }

    public void setArchived(int archived) {
        this.archived = archived;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public String getAuteur() {
        return auteur;
    }

    public void setAuteur(String auteur) {
        this.auteur = auteur;
    }

    public float getAvgRating() {
        return AvgRating;
    }

    public void setAvgRating(float AvgRating) {
        this.AvgRating = AvgRating;
    }

    @Override
    public String toString() {
        return "Article{" + "titre=" + titre + '}';
    }
 
    
}
