package com.company.gui.Article;

import com.codename1.components.MultiButton;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.layouts.BoxLayout;
import com.company.gui.Menu;
import com.mycompany.dao.ArticleDao;
import com.mycompany.entities.Article;
import com.mycompany.entities.Item;
import com.mycompany.services.ArticleService;
import java.util.ArrayList;

import java.util.List;

public class ArticleListForm extends Form {

    public ArticleListForm(Form previous, ArticleService articleService) {
        //add button in toolbar 
        getToolbar().addMaterialCommandToLeftBar("Back", FontImage.MATERIAL_ARROW_BACK, ev -> {
            previous.showBack();
        });

        List<Article> articles = articleService.getAllArticles();
        for (Article article : articles) {
            MultiButton mb = new MultiButton(article.getTitre());
            mb.setTextLine2("details");
            mb.addActionListener(e -> new ArticleDetailsForm(article).show());
            add(mb);
        }
    }

 

    
}
