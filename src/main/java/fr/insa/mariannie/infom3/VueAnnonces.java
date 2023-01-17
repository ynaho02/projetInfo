package fr.insa.mariannie.infom3;


import fr.insa.naho.modelinterface.Objet;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.GridPane;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author alexa
 */
public class VueAnnonces extends ScrollPane  {
    private MainPane main;

    private ImageView imageview;
    private GridPane gridAnnonce;
    
    public VueAnnonces(MainPane main, ArrayList<Objet> objets) throws SQLException {
         this.setStyle("-fx-color:white;-fx-font-size:14px;-fx-font-weight:bold");
        Image fondecran =getImage("ressources/fond4.jpg", 100, 100);
        this.setBackground(new Background(new BackgroundImage(fondecran, 
                BackgroundRepeat.SPACE, 
                BackgroundRepeat.SPACE,
                BackgroundPosition.CENTER,
                BackgroundSize.DEFAULT)));
        
            this.main=main;
        this.main = main;
        this.gridAnnonce= new GridPane();
        this.gridAnnonce.setVgap(30);
        this.gridAnnonce.setAlignment(Pos.TOP_CENTER);
        
        afficherAnnonce(objets);
        
        
        
        this.setContent(gridAnnonce);
    }

    private void afficherAnnonce(ArrayList<Objet> objets) throws SQLException {
        int t =objets.size();
       for(int i=0; i<t;i++){
           byte[] imageBytes = objets.get(i).getImages();
             if (imageBytes !=null){
               
               Image image = new Image(new ByteArrayInputStream(imageBytes));
               ImageView imageview = new ImageView(image);
               imageview.setFitWidth(300); //fixer a 300 pixels
               imageview.setFitHeight(150);//fixer a 150 pixels
               imageview.setPreserveRatio(true); //conserver le ratio de l'image
               
               
           }
                      this.gridAnnonce.add(new Annonce(objets.get(i),this.main), 0, i);
           }
           
          
               
           
       }
    
    private Image getImage(String resourcePath,int w,int h) {
        InputStream input 
                = this.getClass().getResourceAsStream(resourcePath);
        Image image = new Image(input);
        
        image.widthProperty();
        image.heightProperty();
        return image;
  }
        
    }

  
   
    

