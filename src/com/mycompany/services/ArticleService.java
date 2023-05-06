package com.mycompany.services;

import com.mycompany.dao.ArticleDao;
import com.mycompany.entities.Article;

import java.util.List;

public class ArticleService {

    private ArticleDao articleDao = new ArticleDao();

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
