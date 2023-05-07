/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.gui;

import com.codename1.components.ImageViewer;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.FileSystemStorage;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.ImageIO;
import com.mycompany.entities.Categorie_Items;
import com.mycompany.entities.Item;
import static com.mycompany.utils.Statics.BASE_URL;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import com.codename1.util.Base64;
import java.util.List;
import java.util.Map;
import org.json.JSONObject;

/**
 *
 * @author FGH
 */
public class ItemsList extends Form {

    ArrayList<Item> items = new ArrayList<>();
    ArrayList<Categorie_Items> catitems = new ArrayList<>();

    public ItemsList(Form previous) {

        ConnectionRequest request = new ConnectionRequest() {
            @Override
            protected void readResponse(InputStream input) throws IOException {
                items = new ArrayList<>();
                JSONParser parser = new JSONParser();
                System.out.println(parser);
                Map<String, Object> response = parser.parseJSON(new InputStreamReader(input));
                List<Map<String, Object>> itemss = (List<Map<String, Object>>) response.get("items");
                for (Map<String, Object> item : itemss) {

                    Item itemx = new Item(((Double) item.get("id")).intValue(), (String) item.get("libelle"), (String) item.get("description"), Item.type.valueOf((String) item.get("type")), Item.state.valueOf((String) item.get("etat")), (String) item.get("imageurl"), ((Double) item.get("id_user")).intValue(), ((Double) item.get("id_categorie")).intValue(), 0, ((Double) item.get("likes")).intValue(), ((Double) item.get("dislikes")).intValue());
                    items.add(itemx);

                }

                for (Item i : items) {
                    addItem(i);
                }

            }
        };

        ConnectionRequest request2 = new ConnectionRequest() {
            @Override
            protected void readResponse(InputStream input) throws IOException {

                JSONParser parser = new JSONParser();
                System.out.println(parser);
                Map<String, Object> response = parser.parseJSON(new InputStreamReader(input));
                List<Map<String, Object>> itemss = (List<Map<String, Object>>) response.get("itemcats");
                for (Map<String, Object> item : itemss) {

                    Categorie_Items itemx = new Categorie_Items(((Double) item.get("id")).intValue(), (String) item.get("nom_categorie"));
                    catitems.add(itemx);

                }

            }
        };
        request.setUrl(BASE_URL + "/item/mobile/list");
        request.setHttpMethod("GET");
        request2.setUrl(BASE_URL + "/item/mobile/listc");
        request2.setHttpMethod("GET");
        NetworkManager.getInstance().addToQueue(request2);
        NetworkManager.getInstance().addToQueue(request);

        getToolbar().addMaterialCommandToLeftBar("Back", FontImage.MATERIAL_ARROW_BACK, ev -> {
            previous.showBack();
        });

        getToolbar().addCommandToRightBar("Add", null, (ActionListener) (ActionEvent evt) -> {
            Form f2 = new Form("Ajout", BoxLayout.y());
            TextField tfLibelle = new TextField();
            ComboBox<String> cbCategorie = new ComboBox<>();
            for (Categorie_Items i : catitems) {
                cbCategorie.addItem(i.getNom_categorie());
            }
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

            // Create a Command to open the device's file picker
            ImageViewer ivv = new ImageViewer();
            StringBuilder encodedImagee = new StringBuilder();

            Button imgb = new Button("Selectionner Image");
            imgb.addActionListener(e -> {
                Display.getInstance().openGallery(new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        String filePath = (String) evt.getSource();
                        Image img = null;
                        try {

                            img = Image.createImage(filePath);
                            ivv.setImage(img);
                            ByteArrayOutputStream out = new ByteArrayOutputStream();
                            ImageIO.getImageIO().save(img, out, ImageIO.FORMAT_PNG, 1.0f);
                            String encodedImage = "data:image/jpeg;base64, " + Base64.encode(out.toByteArray());
                            encodedImagee.append(encodedImage);
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    }
                }, Display.GALLERY_IMAGE);
            });

            ivv.setPreferredSize(new Dimension(300, 300));

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
            f2.add(imgb);
            f2.add(ivv);
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

                Item i = new Item(tfLibelle.getText(), taDescription.getText(), stType, stEtat, encodedImagee.toString(), 0, 1, 1, 0, 0);

                StringBuilder sb = new StringBuilder();
                for (Categorie_Items x : catitems) {
                    if (cbCategorie.getSelectedItem() == x.getNom_categorie()) {
                        if (sb.length() > 0) {
                            sb.append(", ");
                        }
                        sb.append(x.getId_categorie());
                    }
                }
                String result = sb.toString();

                JSONObject itemJson = new JSONObject();
                itemJson.put("libelle", i.getLibelle());
                itemJson.put("description", i.getDescription());
                itemJson.put("id_categorie", Integer.parseInt(result));
                itemJson.put("type", i.getType());
                itemJson.put("etat", i.getEtat());
                itemJson.put("imageurl", i.getImageurl());
                itemJson.put("id_user", 5);

                String endpointUrl = BASE_URL + "/item/mobile/add";
                ConnectionRequest request3 = new ConnectionRequest();
                request3.setUrl(endpointUrl);
                request3.setPost(true);
                request3.setContentType("application/json");
                request3.setRequestBody(itemJson.toString());
                request3.addResponseListener((e) -> {
                    if (request3.getResponseCode() == 200) {
                        String responseJson = new String(request3.getResponseData());
                        JSONObject responseObject = new JSONObject(responseJson);
                        int itemId = responseObject.getInt("id");
                        i.setId_item(itemId);
                    } else {
                        // Failed to send the item to Symfony
                    }
                });
                NetworkManager.getInstance().addToQueueAndWait(request3);
                
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
        String imageUrl = item.getImageurl();

        EncodedImage encodedImage = null;
        if (imageUrl.startsWith("data:image")) {

            String imageData = imageUrl.substring(imageUrl.indexOf(',') + 1);
            byte[] decodedBytes = Base64.decode(imageData.getBytes());
            encodedImage = EncodedImage.create(decodedBytes);
        } else {
            encodedImage = URLImage.createToStorage(
                    EncodedImage.createFromImage(Image.createImage(300, 300, 0x000000), true),
                    imageUrl,
                    imageUrl,
                    URLImage.RESIZE_SCALE_TO_FILL);
        }

        img = new ImageViewer(encodedImage);
        img.setPreferredSize(new Dimension(300, 300));

        Container C2 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        Label lLibelle = new Label(item.getLibelle());

        StringBuilder sb = new StringBuilder();
        for (Categorie_Items x : catitems) {
            if (item.getId_categorie() == x.getId_categorie()) {
                if (sb.length() > 0) {
                    sb.append(", ");
                }
                sb.append(x.getNom_categorie());
            }
        }
        String result = sb.toString();

        Label lCategorie = new Label(result);
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

        Container C3 = new Container(new BoxLayout(BoxLayout.X_AXIS));

        Button btns = new Button("Supprimer ");

        btns.addActionListener((ActionListener) (ActionEvent evt1) -> {
            JSONObject itemJson = new JSONObject();
            itemJson.put("id", item.getId_item());
            String endpointUrl = BASE_URL + "/item/mobile/remove/" + item.getId_item();
            ConnectionRequest request4 = new ConnectionRequest();
            request4.setUrl(endpointUrl);
            request4.setPost(true);
            request4.setContentType("application/json");
            request4.setRequestBody(itemJson.toString());
            request4.addResponseListener((e) -> {
                if (request4.getResponseCode() == 200) {
                    // Successfully sent the item to Symfony
                } else {
                    // Failed to send the item to Symfony
                }
            });

            NetworkManager.getInstance().addToQueueAndWait(request4);
            
               
                items.remove(item);
                removeAll();
                for (Item ix : items) {
                    addItem(ix);
                }
                showBack();
                
                 show();

        });

        Button btnm = new Button("Modifier ");

        Button like = new Button("👍 " + item.getLikes() + " ");

        Button dislike = new Button("👎 " + item.getDislikes() + " ");

        C3.add(btns);
        C3.add(btnm);
        C3.add(like);
        C3.add(dislike);

        C2.add(lLibelle);
        C2.add(lCategorie);
        C2.add(lTypeEtat);
        C1.add(img);
        C1.add(C2);

        C1.setLeadComponent(img);
        add(C1);
        add(C3);

        add(new Label("─────────────────────────────────────────"));
        refreshTheme();

    }
}
