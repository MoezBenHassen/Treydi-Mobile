/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.gui;

import com.company.gui.Livraison.EchangeListLivreur;
import com.company.gui.Livraison.EchangeList;
import com.company.gui.Article.AddArticleForm;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.codename1.ui.util.UIBuilder;
import com.company.gui.Article.ArticleListForm;
import com.company.gui.Livraison.MesLivraisonLivreur;
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
        getToolbar().addCommandToSideMenu("Echanges", null, ev -> { new EchangeList().show(); } );
        getToolbar().addCommandToSideMenu("Echanges Livreur", null, ev -> { new EchangeListLivreur(this).show(); } );
        getToolbar().addCommandToSideMenu("Mes Livraisons", null, ev -> { new MesLivraisonLivreur(this).show(); } );
        getToolbar().addCommandToSideMenu("Coupons", null, ev -> { new CouponsList(this).show(); } );
         getToolbar().addCommandToSideMenu("List Reclamations", null, ev -> { new ListReclamation(this).show(); } );
         getToolbar().addCommandToSideMenu("ajouter une Reclamation", null, ev -> { new FormAjoutReclamation(this).show(); } );

    
        ArticleDao articleDao = new ArticleDao();
        ArticleService articleService = new ArticleService();
        
       // getToolbar().addCommandToSideMenu("Articles", null, ev -> { new AddArticleForm(this, articleService).show();  });
        getToolbar().addCommandToSideMenu("ArticlesL", null, ev -> { new ArticleListForm(this).show();});


    }
    
}
