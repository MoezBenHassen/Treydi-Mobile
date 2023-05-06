/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.gui;

import com.codename1.components.ImageViewer;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.ComboBox;
import static com.codename1.ui.Component.CENTER;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.Slider;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.mycompany.entities.Item;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author FGH
 */
public class ItemsList extends Form {

    public ItemsList(Form previous) {

        ArrayList<Item> items = new ArrayList<>();
        Item item1 = new Item("Playstation3", "desc", Item.type.Physique, Item.state.Neuf, "https://upload.wikimedia.org/wikipedia/commons/thumb/a/ac/Sony-PlayStation-3-4001B-wController-L.jpg/250px-Sony-PlayStation-3-4001B-wController-L.jpg", 0, 0, 0, 0, 0);
        items.add(item1);

        getToolbar().addMaterialCommandToLeftBar("Back", FontImage.MATERIAL_ARROW_BACK, ev -> {
            previous.showBack();
        });

        getToolbar().addCommandToRightBar("Add", null, (ActionListener) (ActionEvent evt) -> {
            Form f2 = new Form("Ajout", BoxLayout.y());
            TextField tfLibelle = new TextField();
            ComboBox<String> cbCategorie = new ComboBox<>("Random Values", "Value 1", "Value 2", "Value 3", "Value 4");
            TextArea taDescription = new TextArea();
            ButtonGroup bgType = new ButtonGroup();
            RadioButton rb1 = new RadioButton("Physique Neuf");
            RadioButton rb2 = new RadioButton("Physique Occasion");
            RadioButton rb3 = new RadioButton("Virtuelle");
            RadioButton rb4 = new RadioButton("Service");
            bgType.add(rb1);
            bgType.add(rb2);
            bgType.add(rb3);
            bgType.add(rb4);

            Button btn = new Button("Ajouter");
            f2.add(new Label("Libelle :"));
            f2.add(tfLibelle);
            f2.add(new Label("Categorie :"));
            f2.add(cbCategorie);
            f2.add(new Label("Description :"));
            f2.add(taDescription);
            f2.add(new Label("Type et Etat :"));
            f2.add(rb1);
            f2.add(rb2);
            f2.add(rb3);
            f2.add(rb4);
            f2.add(new Label("----------"));
            f2.add(btn);
          
            f2.show();

            btn.addActionListener((ActionListener) (ActionEvent evt1) -> {
                Item i = new Item(tfLibelle.getText(), taDescription.getText(), Item.type.Physique, Item.state.Neuf, "C:/Home/Pictures/Covers/Game Covers/3D Dot Game Heroes Cover.jpg/", 0, 0, 0, 0, 0);
                items.add(i);
                removeAll();
                for (Item ix : items) {
                    addItem(ix);
                }
                showBack();
            });
        });
        for (Item i : items) {
            addItem(i);
        }

        show();
    }

    public void addItem(Item item) {
        ImageViewer img = null;
        Container C1 = new Container(new BoxLayout(BoxLayout.X_AXIS));
        String imageUrl = item.getImageurl(); // replace with your image URL

// create an Image instance from the URL
        Image image = URLImage.createToStorage(
                EncodedImage.createFromImage(Image.createImage(Display.getInstance().getDisplayWidth(), Display.getInstance().getDisplayHeight(), 0x000000), true),
                "cacheKey",
                imageUrl,
                URLImage.RESIZE_SCALE_TO_FILL);
        img = new ImageViewer(image);
        img.setPreferredSize(new Dimension(300, 300));

        Container C2 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        Label l = new Label(item.getLibelle());
        Label tel = new Label(item.getDescription());

        l.addPointerPressedListener((ActionListener) (ActionEvent evt) -> {
            Dialog.show("Contact", "Nom : " + l.getText() + " \n Tel : " + tel.getText(), "Ok", null);
        });

        C2.add(l);
        C2.add(tel);
        C1.add(img);
        C1.add(C2);
        C1.setLeadComponent(l);
        add(C1);
        refreshTheme();

    }
}
