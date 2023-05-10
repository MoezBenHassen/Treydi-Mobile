/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.gui;

import com.company.gui.Article.AddArticleForm;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.codename1.ui.util.UIBuilder;
import com.company.gui.Article.ArticleListForm;
import com.mycompany.dao.ArticleDao;
import com.mycompany.services.ArticleService;

/**
 *
 * @author FGH
 */
public class Menu extends Form{
private Resources theme;
    public Menu() {
        setTitle("Menu Principale");
        setLayout(BoxLayout.y());
        
        getToolbar().addCommandToSideMenu("Profile", 
                null,
                ev -> { 
                    new ItemsList(this).show();
                } 
        );
        getToolbar().addCommandToSideMenu("Items", null, ev -> { new ItemsList(this).show(); } );
        getToolbar().addCommandToSideMenu("Echanges", null, ev -> { new ItemsList(this).show(); } );
        getToolbar().addCommandToSideMenu("Coupons", null, ev -> { new ItemsList(this).show(); } );
        getToolbar().addCommandToSideMenu("Articles", null, ev -> { new ItemsList(this).show(); } );
        getToolbar().addCommandToSideMenu("Reclamations", null, ev -> { new ItemsList(this).show(); } );
        
        ArticleDao articleDao = new ArticleDao();
        ArticleService articleService = new ArticleService();
        
        getToolbar().addCommandToSideMenu("Articles", null, ev -> { new AddArticleForm(this, articleService).show();  });
        getToolbar().addCommandToSideMenu("ArticlesL", null, ev -> { new ArticleListForm(this).show();});

        
    }
    
}
