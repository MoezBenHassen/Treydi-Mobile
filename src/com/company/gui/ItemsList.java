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
import com.codename1.ui.Container;
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
import com.company.gui.ItemsServices;
import static com.mycompany.utils.Statics.BASE_URL;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import com.codename1.util.Base64;
import java.util.List;
import java.util.Map;
import org.json.JSONObject;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import com.codename1.io.FileSystemStorage;
import com.codename1.io.Log;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.codename1.ui.Image;
import com.codename1.util.StringUtil;

/**
 *
 * @author FGH
 */
public class ItemsList extends Form {

    //////////////////////////////////////////////////////////////////////////////
    //Initial Items
    int user = 5;
    ArrayList<Item> items = new ArrayList<>();
    ArrayList<Categorie_Items> catitems = new ArrayList<>();

    public ItemsList(Form previous) {
        setTitle(
                "Items");
        ConnectionRequest request = new ConnectionRequest() {
            @Override
            protected void readResponse(InputStream input) throws IOException {
                items = new ArrayList<>();
                JSONParser parser = new JSONParser();
                System.out.println(parser);
                Map<String, Object> response = parser.parseJSON(new InputStreamReader(input));
                List<Map<String, Object>> itemss = (List<Map<String, Object>>) response.get("items");
                for (Map<String, Object> item : itemss) {

                    Item itemx = new Item(((Double) item.get("id")).intValue(), (String) item.get("libelle"), (String) item.get("description"), Item.type.valueOf((String) item.get("type")), Item.state.valueOf((String) item.get("etat")), (String) item.get("imageurl"), ((Double) item.get("id_user")).intValue(), ((Double) item.get("id_categorie")).intValue(), 0, ((Double) item.get("likes")).intValue(), ((Double) item.get("dislikes")).intValue(), ((Double) item.get("views")).intValue());
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
        NetworkManager.getInstance().addToQueueAndWait(request2);
        NetworkManager.getInstance().addToQueue(request);

        getToolbar().addMaterialCommandToLeftBar("Back", FontImage.MATERIAL_ARROW_BACK, ev -> {
            previous.showBack();
        });
        Button pdf = new Button("Generer PDF Catalog");

        TextField tsearch = new TextField();
        Button search = new Button("Chercher");

        add(search);
        add(pdf);
        add(tsearch);
        add(new Label("─────────────────────────────────────────"));

        search.addActionListener(e -> {

            String keyword = tsearch.getText();

            if (keyword.isEmpty()) {
                removeAll();
                add(search);
                add(pdf);
                add(tsearch);
                add(new Label("─────────────────────────────────────────"));

                for (Item ix : items) {
                    addItem(ix);
                }

            } else {
                ArrayList<Item> filteredItems = new ArrayList<>();
                for (Item item : items) {

                    if (item.getLibelle().contains(keyword)) {
                        filteredItems.add(item);

                    }
                }

                removeAll();
               add(search);
        add(pdf);
        add(tsearch);
                add(new Label("─────────────────────────────────────────"));

                for (Item ix : filteredItems) {
                    addItem(ix);
                }

            }
        });

        pdf.addActionListener(e -> {
            ItemsServices.generatePdfFromItems(items, catitems);
        });

        //////////////////////////////////////////////////////////////////////////////
        //Ajouter Items
        getToolbar()
                .addCommandToRightBar("Add", null, (ActionListener) (ActionEvent evt) -> {

                    Form f2 = new Form("Ajout", BoxLayout.y());

                    TextField tfLibelle = new TextField();
                    ComboBox<String> cbCategorie = new ComboBox<>();
                    for (Categorie_Items i : catitems) {
                        cbCategorie.addItem(i.getNom_categorie());
                    }
                    TextArea taDescription = new TextArea();
                    taDescription.setRows(5);

                    ButtonGroup bgType = new ButtonGroup();
                    RadioButton rb1 = new RadioButton("Physique Neuf");
                    RadioButton rb2 = new RadioButton("Physique Occasion");
                    RadioButton rb3 = new RadioButton("Virtuelle");
                    RadioButton rb4 = new RadioButton("Service");
                    rb1.getStyle().setFgColor(0xffffff);
                    rb2.getStyle().setFgColor(0xffffff);
                    rb3.getStyle().setFgColor(0xffffff);
                    rb4.getStyle().setFgColor(0xffffff);
                    bgType.add(rb1);
                    bgType.add(rb2);
                    bgType.add(rb3);
                    bgType.add(rb4);
                    rb1.setSelected(true);
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

                    ivv.setPreferredSize(new Dimension(400, 400));

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

                    f2.getToolbar().addMaterialCommandToLeftBar("Back", FontImage.MATERIAL_ARROW_BACK, ev -> {
                        // Navigate back to the previous form
                        showBack();
                    });

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

                        if (tfLibelle.getText().isEmpty() || taDescription.getText().isEmpty() || encodedImagee.toString().isEmpty()) {
                            Dialog.show("Error", "Remplissez tout le formulaire s'il vous plait. ", "ok", null);

                        } else {

                            Item i = new Item(tfLibelle.getText(), taDescription.getText(), stType, stEtat, encodedImagee.toString(), user, Integer.parseInt(result), 1, 0, 0, 0);

                            JSONObject itemJson = new JSONObject();
                            itemJson.put("libelle", i.getLibelle());
                            itemJson.put("description", i.getDescription());
                            itemJson.put("id_categorie", Integer.parseInt(result));
                            itemJson.put("type", i.getType());
                            itemJson.put("etat", i.getEtat());
                            itemJson.put("imageurl", i.getImageurl());
                            itemJson.put("id_user", user);

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
                        }
                    });
                }
                );
        for (Item i : items) {
            addItem(i);
        }

        show();
    }
//////////////////////////////////////////////////////////////////////////////
//Affichage Items

    public void addItem(Item item) {

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

        Image xx = encodedImage.scaled(290, 290);
        Button b = new Button(xx);

        //////////////////////////////////////////////////////////////////////////////
        //Details Items
        b.addPointerPressedListener((ActionListener) (ActionEvent evt) -> {

            JSONObject itemJsonx = new JSONObject();
            itemJsonx.put("id", item.getId_item());
            String endpointUrlx = BASE_URL + "/item/mobile/views/" + item.getId_item() + "_" + user;
            ConnectionRequest request10 = new ConnectionRequest();
            request10.setUrl(endpointUrlx);
            request10.setPost(true);
            request10.setContentType("application/json");
            request10.setRequestBody(itemJsonx.toString());
            request10.addResponseListener((e) -> {
                if (request10.getResponseCode() == 200) {

                } else {
                    // Failed to send the item to Symfony
                }
            });
            NetworkManager.getInstance().addToQueueAndWait(request10);

            Label lLibelled = new Label(item.getLibelle());
            String imageUrld = item.getImageurl();
            ImageViewer imgd = null;

            EncodedImage encodedImaged = null;
            if (imageUrl.startsWith("data:image")) {

                String imageData = imageUrl.substring(imageUrl.indexOf(',') + 1);
                byte[] decodedBytes = Base64.decode(imageData.getBytes());
                encodedImaged = EncodedImage.create(decodedBytes);
            } else {
                encodedImaged = URLImage.createToStorage(
                        EncodedImage.createFromImage(Image.createImage(1, 1, 0x000000), true),
                        imageUrld,
                        imageUrld,
                        URLImage.RESIZE_SCALE_TO_FILL);
            }

            imgd = new ImageViewer(encodedImaged);

            Label lDescd = new Label(item.getDescription());

            Label lTypeEtatd = new Label();
            if (item.getType() == Item.type.Physique) {
                lTypeEtatd = new Label(item.getType().toString() + " " + item.getEtat().toString());
            } else {

                lTypeEtatd = new Label(item.getType().toString());
            }

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

            Label lCategoried = new Label(result);

            Form fd = new Form("Details", BoxLayout.y());

            //////////////////////////////////////////////////////////////////////////////
            //Comment Items
            ConnectionRequest request7 = new ConnectionRequest() {
                @Override
                protected void readResponse(InputStream input) throws IOException {

                    JSONParser parser = new JSONParser();
                    System.out.println(parser);
                    Map<String, Object> response = parser.parseJSON(new InputStreamReader(input));
                    List<Map<String, Object>> itemss = (List<Map<String, Object>>) response.get("itemcoms");
                    for (Map<String, Object> item : itemss) {
                        System.out.println((String) item.get("comment"));
                        Container COM = new Container(new BoxLayout(BoxLayout.Y_AXIS));
                        Label usercomment = new Label((String) item.get("user") + " : " + (String) item.get("comment"));

                        COM.add(usercomment);

                        fd.add(COM);
                        fd.refreshTheme();
                    }

                }
            };

            fd.add(new Label("Libelle :"));
            fd.add(lLibelled);

            fd.add(imgd);
            fd.add(new Label("─────────────────────────────────────────"));
            fd.add(new Label("Categorie :"));
            fd.add(lCategoried);
            fd.add(new Label("─────────────────────────────────────────"));
            fd.add(new Label("Description :"));
            fd.add(lDescd);
            fd.add(new Label("─────────────────────────────────────────"));
            fd.add(new Label("Type et Etat :"));
            fd.add(lTypeEtatd);
            fd.add(new Label("─────────────────────────────────────────"));
            fd.add(new Label("Commentaire :"));
            TextArea comta = new TextArea();
            comta.setRows(5);
            Button comadd = new Button("Commenter");

            comadd.addPointerPressedListener((ActionListener) (ActionEvent evt3) -> {

                if (comta.getText().isEmpty()) {
                    Dialog.show("Error", "Ecrire un commentaire pour l'envoyer", "ok", null);

                } else {

                    JSONObject itemJson = new JSONObject();
                    itemJson.put("itemid", item.getId_item());
                    itemJson.put("userid", user);
                    itemJson.put("comment", comta.getText());

                    String endpointUrl = BASE_URL + "/item/mobile/commentadd";
                    ConnectionRequest request8 = new ConnectionRequest();
                    request8.setUrl(endpointUrl);
                    request8.setPost(true);
                    request8.setContentType("application/json");
                    request8.setRequestBody(itemJson.toString());
                    request8.addResponseListener((e) -> {
                        if (request8.getResponseCode() == 200) {
                            showBack();
                            fd.show();
                        } else {
                            // Failed to send the item to Symfony
                        }
                    });
                    NetworkManager.getInstance().addToQueueAndWait(request8);
                }
            });

            fd.add(comta);
            fd.add(comadd);

            request7.setUrl(BASE_URL + "/item/mobile/commentlist/" + item.getId_item());
            request7.setHttpMethod("GET");
            NetworkManager.getInstance().addToQueue(request7);

            fd.getToolbar().addMaterialCommandToLeftBar("Back", FontImage.MATERIAL_ARROW_BACK, ev -> {
                // Navigate back to the previous form
                showBack();
            });

            fd.show();

        }
        );

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

        if (item.getType()
                == Item.type.Physique) {
            lTypeEtat = new Label(item.getType().toString() + " " + item.getEtat().toString());

        } else {

            lTypeEtat = new Label(item.getType().toString());
        }

        Container C3 = new Container(new BoxLayout(BoxLayout.X_AXIS));

        //////////////////////////////////////////////////////////////////////////////
        //Supprimer Items
        Button btns = new Button("Supprimer ");

        btns.addActionListener(
                (ActionListener) (ActionEvent evt1) -> {
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

                }
        );

        //////////////////////////////////////////////////////////////////////////////
        //Modifier Items
        Button btnm = new Button("Modifier ");

        btnm.addActionListener(
                (ActionListener) (ActionEvent evt1) -> {

                    Form f2 = new Form("Modification", BoxLayout.y());
                    TextField tfLibelle = new TextField(item.getLibelle());
                    ComboBox<String> cbCategorie = new ComboBox<>();
                    for (Categorie_Items i : catitems) {

                        cbCategorie.addItem(i.getNom_categorie());
                    }
                    TextArea taDescription = new TextArea(item.getDescription());
                    taDescription.setRows(5);
                    ButtonGroup bgType = new ButtonGroup();
                    RadioButton rb1 = new RadioButton("Physique Neuf");
                    RadioButton rb2 = new RadioButton("Physique Occasion");
                    RadioButton rb3 = new RadioButton("Virtuelle");
                    RadioButton rb4 = new RadioButton("Service");
                    bgType.add(rb1);
                    bgType.add(rb2);
                    bgType.add(rb3);
                    bgType.add(rb4);
                    rb1.setSelected(true);
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

                    Button btn = new Button("Modifier");
                    f2.add(new Label("Libelle :"));
                    f2.add(tfLibelle);

                    f2.add(new Label("Categorie :"));
                    f2.add(cbCategorie);
                    f2.add(new Label("Description :"));
                    f2.add(taDescription);
                    f2.add(new Label("Type et Etat :"));

                    rb1.getStyle().setFgColor(0xffffff);
                    rb2.getStyle().setFgColor(0xffffff);
                    rb3.getStyle().setFgColor(0xffffff);
                    rb4.getStyle().setFgColor(0xffffff);
                    f2.add(rb1);
                    f2.add(rb2);
                    f2.add(rb3);
                    f2.add(rb4);
                    f2.add(imgb);
                    f2.add(ivv);
                    f2.add(btn);
                    f2.getToolbar().addMaterialCommandToLeftBar("Back", FontImage.MATERIAL_ARROW_BACK, ev -> {
                        // Navigate back to the previous form
                        showBack();
                    });
                    f2.show();

                    btn.addActionListener((ActionListener) (ActionEvent evt2) -> {
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

                        StringBuilder sbb = new StringBuilder();
                        for (Categorie_Items x : catitems) {
                            if (cbCategorie.getSelectedItem() == x.getNom_categorie()) {
                                if (sbb.length() > 0) {
                                    sbb.append(", ");
                                }
                                sbb.append(x.getId_categorie());
                            }
                        }
                        String resultt = sbb.toString();

                        if (tfLibelle.getText().isEmpty() || taDescription.getText().isEmpty() || encodedImagee.toString().isEmpty()) {
                            Dialog.show("Error", "Remplissez tout le formulaire s'il vous plait. ", "ok", null);

                        } else {

                            Item i = new Item(item.getId_item(), tfLibelle.getText(), taDescription.getText(), stType, stEtat, encodedImagee.toString(), user, Integer.parseInt(resultt), 1, 0, 0, 0);

                            JSONObject itemJson = new JSONObject();
                            itemJson.put("id", item.getId_item());
                            itemJson.put("libelle", i.getLibelle());
                            itemJson.put("description", i.getDescription());
                            itemJson.put("id_categorie", Integer.parseInt(resultt));
                            itemJson.put("type", i.getType());
                            itemJson.put("etat", i.getEtat());
                            itemJson.put("imageurl", i.getImageurl());
                            itemJson.put("id_user", user);

                            String endpointUrl = BASE_URL + "/item/mobile/modify";
                            ConnectionRequest request5 = new ConnectionRequest();
                            request5.setUrl(endpointUrl);
                            request5.setPost(true);
                            request5.setContentType("application/json");
                            request5.setRequestBody(itemJson.toString());
                            request5.addResponseListener((e) -> {
                                if (request5.getResponseCode() == 200) {

                                } else {
                                    // Failed to send the item to Symfony
                                }
                            });
                            NetworkManager.getInstance().addToQueueAndWait(request5);

                            ConnectionRequest request = new ConnectionRequest() {
                                @Override
                                protected void readResponse(InputStream input) throws IOException {
                                    items = new ArrayList<>();
                                    JSONParser parser = new JSONParser();
                                    System.out.println(parser);
                                    Map<String, Object> response = parser.parseJSON(new InputStreamReader(input));
                                    List<Map<String, Object>> itemss = (List<Map<String, Object>>) response.get("items");
                                    for (Map<String, Object> item : itemss) {

                                        Item itemx = new Item(((Double) item.get("id")).intValue(), (String) item.get("libelle"), (String) item.get("description"), Item.type.valueOf((String) item.get("type")), Item.state.valueOf((String) item.get("etat")), (String) item.get("imageurl"), ((Double) item.get("id_user")).intValue(), ((Double) item.get("id_categorie")).intValue(), 0, ((Double) item.get("likes")).intValue(), ((Double) item.get("dislikes")).intValue(), ((Double) item.get("views")).intValue());
                                        items.add(itemx);

                                    }

                                    removeAll();
                                    showBack();
                                    for (Item i : items) {
                                        addItem(i);
                                    }
                                    show();

                                }

                            };
                            request.setUrl(BASE_URL + "/item/mobile/list");
                            request.setHttpMethod("GET");
                            NetworkManager.getInstance().addToQueue(request);

                        }
                    }
                    );

                }
        );

        //////////////////////////////////////////////////////////////////////////////
        //Like Items
        Button like = new Button("👍 " + item.getLikes() + "  .");

        like.addActionListener(
                (ActionListener) (ActionEvent evt1) -> {
                    JSONObject itemJson = new JSONObject();
                    itemJson.put("id", item.getId_item());

                    String endpointUrl = BASE_URL + "/item/mobile/like/" + item.getId_item() + "_" + user;
                    ConnectionRequest request6 = new ConnectionRequest();
                    request6.setUrl(endpointUrl);
                    request6.setPost(true);
                    request6.setContentType("application/json");
                    request6.setRequestBody(itemJson.toString());
                    request6.addResponseListener((e) -> {
                        if (request6.getResponseCode() == 200) {

                        } else {
                            // Failed to send the item to Symfony
                        }
                    });
                    NetworkManager.getInstance().addToQueueAndWait(request6);

                    ConnectionRequest request = new ConnectionRequest() {
                @Override
                protected void readResponse(InputStream input) throws IOException {
                    items = new ArrayList<>();
                    JSONParser parser = new JSONParser();
                    System.out.println(parser);
                    Map<String, Object> response = parser.parseJSON(new InputStreamReader(input));
                    List<Map<String, Object>> itemss = (List<Map<String, Object>>) response.get("items");
                    for (Map<String, Object> item : itemss) {

                        Item itemx = new Item(((Double) item.get("id")).intValue(), (String) item.get("libelle"), (String) item.get("description"), Item.type.valueOf((String) item.get("type")), Item.state.valueOf((String) item.get("etat")), (String) item.get("imageurl"), ((Double) item.get("id_user")).intValue(), ((Double) item.get("id_categorie")).intValue(), 0, ((Double) item.get("likes")).intValue(), ((Double) item.get("views")).intValue(), ((Double) item.get("dislikes")).intValue());
                        items.add(itemx);

                    }

                    removeAll();
                    showBack();
                    for (Item i : items) {
                        addItem(i);
                    }
                    show();

                }

            };
                    request.setUrl(BASE_URL + "/item/mobile/list");
                    request.setHttpMethod("GET");
                    NetworkManager.getInstance().addToQueue(request);

                }
        );

        //////////////////////////////////////////////////////////////////////////////
        //Dislike Items
        Button dislike = new Button("👎 " + item.getDislikes() + " .");

        dislike.addActionListener(
                (ActionListener) (ActionEvent evt1) -> {
                    JSONObject itemJson = new JSONObject();
                    itemJson.put("id", item.getId_item());

                    String endpointUrl = BASE_URL + "/item/mobile/dislike/" + item.getId_item() + "_" + user;
                    ConnectionRequest request6 = new ConnectionRequest();
                    request6.setUrl(endpointUrl);
                    request6.setPost(true);
                    request6.setContentType("application/json");
                    request6.setRequestBody(itemJson.toString());
                    request6.addResponseListener((e) -> {
                        if (request6.getResponseCode() == 200) {

                        } else {
                            // Failed to send the item to Symfony
                        }
                    });
                    NetworkManager.getInstance().addToQueueAndWait(request6);

                    ConnectionRequest request = new ConnectionRequest() {
                @Override
                protected void readResponse(InputStream input) throws IOException {
                    items = new ArrayList<>();
                    JSONParser parser = new JSONParser();
                    System.out.println(parser);
                    Map<String, Object> response = parser.parseJSON(new InputStreamReader(input));
                    List<Map<String, Object>> itemss = (List<Map<String, Object>>) response.get("items");
                    for (Map<String, Object> item : itemss) {

                        Item itemx = new Item(((Double) item.get("id")).intValue(), (String) item.get("libelle"), (String) item.get("description"), Item.type.valueOf((String) item.get("type")), Item.state.valueOf((String) item.get("etat")), (String) item.get("imageurl"), ((Double) item.get("id_user")).intValue(), ((Double) item.get("id_categorie")).intValue(), 0, ((Double) item.get("likes")).intValue(), ((Double) item.get("dislikes")).intValue(), ((Double) item.get("views")).intValue());
                        items.add(itemx);

                    }

                    removeAll();
                    showBack();
                    for (Item i : items) {
                        addItem(i);
                    }
                    show();

                }

            };
                    request.setUrl(BASE_URL + "/item/mobile/list");
                    request.setHttpMethod("GET");
                    NetworkManager.getInstance().addToQueue(request);

                }
        );

        Label lViews = new Label("👁 : " + Integer.toString(item.getViews()));

        C3.add(like);

        C3.add(dislike);

        if (user
                == item.getId_user()) {
            C3.add(btns);
            C3.add(btnm);
        }

        C2.add(lLibelle);

        C2.add(lCategorie);

        C2.add(lTypeEtat);

        C2.add(lViews);

        C1.add(b);

        C1.add(C2);

        add(C1);

        add(C3);

        add(
                new Label("─────────────────────────────────────────"));
        refreshTheme();

    }

}
