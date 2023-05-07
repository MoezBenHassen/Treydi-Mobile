package com.company.gui.Article;

import com.codename1.components.ImageViewer;
import com.codename1.components.MultiButton;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.Log;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Slider;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.Layout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.Style;
import com.company.gui.Menu;
import com.mycompany.dao.ArticleDao;
import com.mycompany.entities.Article;
import com.mycompany.entities.Item;
import com.mycompany.services.ArticleService;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import java.util.List;
import java.util.Map;
import javafx.scene.image.ImageView;

public class ArticleListForm extends Form {
    List<Article> articles = new ArrayList<>();
    public ArticleListForm(Form previous) {
        //add button in toolbar 
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, ev -> {
            previous.showBack();
        });
        
       Image imgs = null;
       ImageViewer imgV;
       EncodedImage placeholder1 = EncodedImage.createFromImage(FontImage.createMaterial(FontImage.MATERIAL_IMAGE, "MultiButton", 8), true);
       ConnectionRequest connectionRequest;
        connectionRequest = new ConnectionRequest() {
            @Override
            protected void readResponse(InputStream input) throws IOException {
                // Parse the JSON response
                JSONParser jsonParser = new JSONParser();
                Map<String, Object> response;
                response = jsonParser.parseJSON(new InputStreamReader(input));
                //Log.p("DEBUG3"+response) ;
                // Extract the articles from the response
                ArrayList<Map<String, Object>> articlesData = (ArrayList<Map<String, Object>>) response.get("articles");
                Log.p("articlesDATA : "+articlesData);
                for (Map<String, Object> articleData : articlesData) {
                    //String id = (String) articleData.get("id");
                    String title = (String) articleData.get("title");
                    String description = (String) articleData.get("description");
                    String contenu = (String) articleData.get("contenu");
                    String image = (String) articleData.get("image");
                    Log.p("CONTENT : " +contenu);
                    Log.p("TITLE STRING: " + title);
                    Log.p("DESCRIPTION STRING: " + description);
                    Log.p("IMAGE: " + image);
                    
                    // Create a new Article object and add it to the list
                    Article article = new Article(title,description, contenu,image);
                    Log.p("ONE ARTICLE : "+article.toString());
                    articles.add(article);
                    

                }
                
              // Create the UI components
                for (Article article : articles) {
                    MultiButton mb = new MultiButton(article.getTitre());
                    mb.setText(article.getTitre());

                    Log.p("TITRE"+article.getTitre());
                    Log.p("GET IMAGE : "+article.getImage());
                    mb.setTextLine2("details");
                    mb.addActionListener(e -> new ArticleDetailsForm(article, ArticleListForm.this).show());
                    Image img=null;
                    img = URLImage.createToStorage(placeholder1,
                        article.getImage(),
                        article.getImage(),
                        URLImage.RESIZE_SCALE);
                    int deviceWidth = Display.getInstance().getDisplayWidth();

                    img = img.scaledWidth(deviceWidth);
                    int height = (int)(563.0 / 1133.0 *deviceWidth);
                    img = img.scaledHeight(height);
                    img = img.scaled(deviceWidth, height);
                    ImageViewer imgV = new ImageViewer(img);

                    Container cnt = new Container(new BoxLayout(BoxLayout.Y_AXIS));
                    cnt.add(imgV);
                    cnt.add(mb);
                    cnt.setWidth(deviceWidth);
                    cnt.addPointerPressedListener(e -> new ArticleDetailsForm(article, ArticleListForm.this).show());
                    imgV.addPointerPressedListener(e -> new ArticleDetailsForm(article, ArticleListForm.this).show());
                    add(cnt);
                    refreshTheme();
                    // Execute code after image has loaded
                }

                // Refresh the UI theme
                refreshTheme();



            }

            @Override
            protected void handleErrorResponseCode(int code, String message) {
                // Handle errors here
            }
        };

        // Set the URL of your Symfony API
        connectionRequest.setUrl("http://127.0.0.1:8000/getAllArticles");

        // Send the request asynchronously
        connectionRequest.setHttpMethod("GET");
        NetworkManager.getInstance().addToQueue(connectionRequest);

    }
}