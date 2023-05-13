package com.mycompany.services;


import com.codename1.components.MultiButton;
import com.codename1.io.ConnectionRequest;
import com.mycompany.dao.ArticleDao;
import com.mycompany.entities.Article;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Dialog;
import com.codename1.ui.events.ActionListener;
import com.codename1.util.Callback;
import com.company.gui.Article.ArticleDetailsForm;
import com.mycompany.utils.Statics;
import java.io.ByteArrayInputStream;


import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import java.util.List;


public class ArticleService {
    public static ArticleService instance = null ;
    private ArticleDao articleDao = new ArticleDao();
        
    private static final String API_URL = "https://your-symfony-api-url.com/getAllArticles";
    
    //initilisation connection request 
    private ConnectionRequest req;
    private ArrayList<Article> articles;
    public static ArticleService getInstance() {
        if(instance == null )
            instance = new ArticleService();
        return instance ;
    }
    public ArticleService(){
        req=new ConnectionRequest();
    }
    public ArrayList<Article> getArticles() {
        String url = Statics.BASE_URL+"/getAllArticles";
        req.setUrl(url);
        
        articles = new ArrayList<>();
        
        req.addResponseListener((ActionListener<NetworkEvent>) evt -> {
        
                // Parse the JSON response
                JSONParser jsonParser = new JSONParser();
                Map<String, Object> response = null;
            try {
                response = jsonParser.parseJSON(new InputStreamReader(new ByteArrayInputStream(req.getResponseData())));
            } catch (IOException ex) {
                
            }
                
                // Extract the articles from the response
                List<Map<String, Object>> articlesData = (ArrayList<Map<String, Object>>) response.get("articles");
                for (Map<String, Object> articleData : articlesData) {
                    String title = (String) articleData.get("title");
                    String description = (String) articleData.get("description");
                    String contenu = (String) articleData.get("contenu");
                    String image = (String) articleData.get("image");
                    
                    // Create a new Article object and add it to the list
                    Article article = new Article(title, description, contenu, image);
                    articles.add(article);
                }
          
        });
        req.setHttpMethod("GET");
        NetworkManager.getInstance().addToQueue(req);
        
        return articles;
    }

    
    
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
