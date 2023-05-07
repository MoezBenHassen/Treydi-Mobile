package com.mycompany.dao;

import com.mycompany.entities.Article;

import java.util.ArrayList;
import java.util.List;

public class ArticleDao {

    // We will store our articles in an ArrayList
    private List<Article> articles = new ArrayList<>();
    
    // Create operation
    public void addArticle(Article article) {
        articles.add(article);
    }
    
    // Read operation
    public Article getArticle(int id) {
        for (Article article : articles) {
            if (article.getId() == id) {
                return article;
            }
        }
        return null;
    }

    // Update operation
    public void updateArticle(Article updatedArticle) {
        for (Article article : articles) {
            if (article.getId() == updatedArticle.getId()) {
                article.setTitre(updatedArticle.getTitre());
                article.setDescription(updatedArticle.getDescription());
                article.setContenu(updatedArticle.getContenu());
                article.setDate_publication(updatedArticle.getDate_publication());
                article.setId_categorie(updatedArticle.getId_categorie());
                article.setArchived(updatedArticle.getArchived());
                article.setId_user(updatedArticle.getId_user());
                article.setAuteur(updatedArticle.getAuteur());
                article.setAvgRating(updatedArticle.getAvgRating());
            }
        }
    }

    // Delete operation
public void deleteArticle(int id) {
    for (int i = 0; i < articles.size(); i++) {
        if (articles.get(i).getId() == id) {
            articles.remove(i);
            break;
        }
    }
}

    // Get all articles
    public List<Article> getAllArticles() {
        return articles;
    }
}
