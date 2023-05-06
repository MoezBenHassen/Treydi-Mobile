/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.gui;

import com.codename1.components.ImageViewer;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkManager;
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
import com.codename1.util.Base64;
import com.mycompany.entities.Item;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author FGH
 */
public class ItemsList extends Form {

    ArrayList<Item> items = new ArrayList<>();

    public ItemsList(Form previous) {

        ConnectionRequest request = new ConnectionRequest() {
            @Override
            protected void readResponse(InputStream input) throws IOException {

                JSONParser parser = new JSONParser();
                System.out.println(parser);
                Map<String, Object> response = parser.parseJSON(new InputStreamReader(input));
                List<Map<String, Object>> itemss = (List<Map<String, Object>>) response.get("items");
                for (Map<String, Object> item : itemss) {

                    Item itemx = new Item((String) item.get("libelle"), (String) item.get("description"), Item.type.valueOf((String) item.get("type")), Item.state.valueOf((String) item.get("etat")), (String) item.get("imageurl"), (Integer) item.get("imageurl"), (Integer) item.get("id_categorie"), (Integer) item.get("id_echange"), (Integer) item.get("likes"), (Integer) item.get("dislikes"));
                    items.add(itemx);

                }
                for (Item i : items) {
                    addItem(i);
                }
            }
        };
        request.setUrl("http://127.0.0.1:8000/item/mobile/list");
        request.setHttpMethod("GET");
        NetworkManager.getInstance().addToQueue(request);

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
            f2.add(btn);

            f2.show();

            btn.addActionListener((ActionListener) (ActionEvent evt1) -> {
                String strType = "Physique";
                String strEtat = "Null";
                if (rb1.isSelected()) {
                    strType = "Physique";
                    strEtat = "Neuf";
                } else if (rb2.isSelected()) {
                    strType = "Physique";
                    strEtat = "Occasion";
                } else if (rb3.isSelected()) {
                    strType = "Virtuelle";
                    strEtat = "Null";
                } else if (rb4.isSelected()) {
                    strType = "Service";
                    strEtat = "Null";
                } else {
                    strType = "Physique";
                    strEtat = "Null";
                }
                Item.type stType = Item.type.valueOf(strType);
                Item.state stEtat = Item.state.valueOf(strEtat);

                Item i = new Item(tfLibelle.getText(), taDescription.getText(), stType, stEtat, "C:/Home/Pictures/Covers/Game Covers/3D Dot Game Heroes Cover.jpg/", 0, 0, 0, 0, 0);
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

        EncodedImage encodedImage;
        if (imageUrl.startsWith("data:image")) {
            // base64 encoded image
            String imageData = imageUrl.substring(imageUrl.indexOf(',') + 1);
            byte[] decodedBytes = Base64.decode(imageData.getBytes());
            encodedImage = EncodedImage.create(decodedBytes);
 

        img = new ImageViewer(encodedImage);
        img.setPreferredSize(new Dimension(300, 300));

        Container C2 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        Label lLibelle = new Label(item.getLibelle());
        Label lCategorie = new Label(item.getDescription());
        Label lTypeEtat = new Label();
        if (item.getType() == Item.type.Physique) {
            lTypeEtat = new Label(item.getType().toString() + " " + item.getEtat().toString());
            lLibelle.addPointerPressedListener((ActionListener) (ActionEvent evt) -> {
                Dialog.show(item.getLibelle(), "Categorie : " + lLibelle.getText() + " \n Type : " + item.getType().toString() + " \n Etat : " + item.getEtat().toString() + " \n Description : " + item.getDescription(), "Ok", null);
            });
        } else {
            lLibelle.addPointerPressedListener((ActionListener) (ActionEvent evt) -> {
                Dialog.show(item.getLibelle(), "Categorie : " + lLibelle.getText() + " \n Type : " + item.getType().toString() + " \n Description : " + item.getDescription(), "Ok", null);
            });
            lTypeEtat = new Label(item.getType().toString());
        }

        C2.add(lLibelle);
        C2.add(lCategorie);
        C2.add(lTypeEtat);
        C1.add(img);
        C1.add(C2);
        C1.setLeadComponent(lLibelle);
        add(C1);
        refreshTheme();

    }
}
