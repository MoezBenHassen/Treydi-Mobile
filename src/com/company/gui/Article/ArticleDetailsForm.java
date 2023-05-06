package com.company.gui.Article;

import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.entities.Article;

public class ArticleDetailsForm extends Form {

    public ArticleDetailsForm(Article article) {
        setTitle(article.getTitre());
        setLayout(new BoxLayout(BoxLayout.Y_AXIS));

        Container container = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        container.add(new Label(article.getDescription()));
        container.add(new Label(article.getContenu()));

        addComponent(container);

        Button editButton = new Button("Edit");
        Button deleteButton = new Button("Delete");

      

        addComponent(editButton);
        addComponent(deleteButton);
    }
}
