package com.mycompany.services;


import com.codename1.components.MultiButton;
import com.codename1.io.ConnectionRequest;
import com.mycompany.dao.ArticleDao;
import com.mycompany.entities.Article;
import com.codename1.io.JSONParser;
import com.codename1.ui.Dialog;
import com.codename1.util.Callback;
import com.company.gui.Article.ArticleDetailsForm;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import java.util.List;

public class ArticleService {

    private ArticleDao articleDao = new ArticleDao();
        
    private static final String API_URL = "https://your-symfony-api-url.com/getAllArticles";
    
       
    public void addArticle(Article article) {
        articleDao.addArticle(article);
    }

    public Article getArticle(int id) {
        return articleDao.getArticle(id);
    }

    public void updateArticle(Article updatedArticle) {
        articleDao.updateArticle(updatedArticle);
    }

    public void deleteArticle(int id) {
        articleDao.deleteArticle(id);
    }

    public List<Article> getAllArticles() {
        return articleDao.getAllArticles();
    }
}
