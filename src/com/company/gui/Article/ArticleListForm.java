
package com.company.gui.Article;

import com.codename1.components.ImageViewer;
import com.codename1.components.MultiButton;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.Log;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Slider;
import com.codename1.ui.TextField;
import com.codename1.ui.URLImage;
import com.codename1.ui.geom.Dimension;
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
import com.mycompany.utils.Statics;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import java.util.List;
import java.util.Map;
//import java.util.stream.Collectors;
//import java.util.stream.Collectors;



public class ArticleListForm extends Form {
    List<Article> articles = new ArrayList<>();
    List<MultiButton> articleButtons = new ArrayList<>();
    EncodedImage placeholder1 = EncodedImage.createFromImage(FontImage.createMaterial(FontImage.MATERIAL_IMAGE, "MultiButton", 8), true);
    public ArticleListForm(Form previous) {
        //add button in toolbar 
       getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, ev -> { previous.showBack(); } );
       setTitle("Our Blog");
       Image imgs = null;
       ImageViewer imgV;
     /*
       TextField tsearch = new TextField();
       Button search = new Button("Chercher");
      
       add(tsearch);
       add(search);
       
       */
       
       ConnectionRequest connectionRequest;
        connectionRequest = new ConnectionRequest() {
            @Override
            protected void readResponse(InputStream input) throws IOException {
               
                JSONParser jsonParser = new JSONParser();
                Map<String, Object> response;
                response = jsonParser.parseJSON(new InputStreamReader(input));
                //Log.p("DEBUG3"+response) ;
                // EXTRACT THE ARTCILES FROM THE REPONSE
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
              TextField searchField = new TextField();
        Button searchButton = new Button("Search");
        searchButton.addActionListener(e -> {
            String query = searchField.getText().toLowerCase();
           List<Article> filteredArticles = new ArrayList<>();
            for (Article article : articles) {
                if (article.getTitre().toLowerCase().contains(query)) {
                    filteredArticles.add(article);
                }
            }
            createArticleButtons(filteredArticles);

        });

        // Add the search components to the form
        add(FlowLayout.encloseCenter(searchField, searchButton));

        // Create the article buttons and add them to the form
        createArticleButtons(articles);
      
                // CREATE THE UI COMPONENTS
                for (Article article : articles) {
                    MultiButton mb = new MultiButton(article.getTitre());
                    mb.setText(article.getTitre());

                    Log.p("TITRE: "+article.getTitre());
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
                    Slider starRank = new Slider();
                    Form hi = new Form(new BoxLayout(BoxLayout.Y_AXIS));
                         
                    //hi.add();
                  
                    cnt.add(imgV);
                   
                    Slider starRankSlider = createStarRankSlider();
                    cnt.add(FlowLayout.encloseCenter(starRankSlider));
                    
                    
                    int rating = starRankSlider.getProgress();
                    Log.p("RATING: "+rating);
                    
                    cnt.add(mb);
                    
                   
                    cnt.setWidth(deviceWidth);
                    cnt.addPointerPressedListener(e -> new ArticleDetailsForm(article, ArticleListForm.this).show());
                    imgV.addPointerPressedListener(e -> new ArticleDetailsForm(article, ArticleListForm.this).show());
                    cnt.getStyle().setMargin(20, 20, 0, 0);
                    

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
   
            private void createArticleButtons(List<Article> articleList) {
               // Remove any existing article buttons from the form
               for (MultiButton mb : articleButtons) {
                   removeComponent(mb);
               }
               articleButtons.clear();

               // Create new article buttons for the given list of articles
               for (Article article : articleList) {
                   MultiButton mb = new MultiButton(article.getTitre());
                   mb.setText(article.getTitre());
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
                   Slider starRankSlider = createStarRankSlider();
                   
                   cnt.add(imgV);
                   cnt.add(FlowLayout.encloseCenter(starRankSlider));
                   cnt.add(mb);
                   
                   cnt.setWidth(deviceWidth);
                   cnt.addPointerPressedListener(e -> new ArticleDetailsForm(article, ArticleListForm.this).show());
                   imgV.addPointerPressedListener(e -> new ArticleDetailsForm(article, ArticleListForm.this).show());
                   cnt.getStyle().setMargin(20, 20, 0, 0);
                   add(cnt);
                   articleButtons.add(mb);
               }

               // Refresh the UI theme
               refreshTheme();
           }
     
                  public void showForm() {
                 
                }


            private void initStarRankStyle(Style s, Image star) {
                s.setBackgroundType(Style.BACKGROUND_IMAGE_TILE_BOTH);
                s.setBorder(Border.createEmpty());
                s.setBgImage(star);
                s.setBgTransparency(0);
            }
            
            private Slider createStarRankSlider() {
                Slider starRank = new Slider();
                starRank.setEditable(true);
                starRank.setMinValue(0);
                starRank.setMaxValue(5);
                 Font fnt = Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_PLAIN, Font.SIZE_SMALL);

                Style s = new Style(0xffff33, 0, fnt, (byte)0);
               
                Image fullStar = FontImage.createMaterial(FontImage.MATERIAL_STAR, s).toImage();
                
                starRank.addActionListener(e -> {
                        int rating = starRank.getProgress();
                        initStarRankStyle(starRank.getSliderFullSelectedStyle(), fullStar);
                        Log.p("RATING: " + rating);
                    });
                 
               
                s.setOpacity(100);
                s.setFgColor(0);
                Image emptyStar = FontImage.createMaterial(FontImage.MATERIAL_STAR, s).toImage();
                initStarRankStyle(starRank.getSliderEmptySelectedStyle(), emptyStar);
                initStarRankStyle(starRank.getSliderEmptyUnselectedStyle(), emptyStar);
                initStarRankStyle(starRank.getSliderFullSelectedStyle(), fullStar);
                initStarRankStyle(starRank.getSliderFullUnselectedStyle(), fullStar);
                starRank.setPreferredSize(new Dimension(fullStar.getWidth() * 5, fullStar.getHeight()));
                
                return starRank; 
            }
            
        };

        // Set the URL of your Symfony API
        connectionRequest.setUrl(Statics.BASE_URL+"/getAllArticles");

        // Send the request asynchronously
        connectionRequest.setHttpMethod("GET");
        NetworkManager.getInstance().addToQueue(connectionRequest);

    }
    
}