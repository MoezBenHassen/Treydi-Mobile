package com.company.gui.Article;

import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import com.company.gui.Menu;
import com.mycompany.dao.ArticleDao;
import com.mycompany.entities.Article;
import com.mycompany.services.ArticleService;

public class AddArticleForm extends Form {
    private ArticleService articleService = new ArticleService();
  //  private ArticleDao articleDao = new ArticleDao();
    private TextField titreField = new TextField();
    private TextField descriptionField = new TextField();
    private TextField contenuField = new TextField();
    private Button saveButton = new Button("Save");

    public AddArticleForm(Form previous,  ArticleService articleService) {
        super("Add Article", BoxLayout.y());
        getToolbar().addMaterialCommandToLeftBar("Back", FontImage.MATERIAL_ARROW_BACK, ev -> {
            previous.showBack();
        });
        Container container = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        container.add(new Label("Titre"));
        container.add(titreField);
        container.add(new Label("Description"));
        container.add(descriptionField);
        container.add(new Label("Contenu"));
        container.add(contenuField);
        container.add(saveButton);

        saveButton.addActionListener(evt -> {
            Article article = new Article();
            article.setTitre(titreField.getText());
            article.setDescription(descriptionField.getText());
            article.setContenu(contenuField.getText());
            articleService.addArticle(article);
//            clearFields();
        });

        addComponent(container);
        
    }


    
    private void clearFields() {
        titreField.clear();
        descriptionField.clear();
        contenuField.clear();
    }
    
    
}
