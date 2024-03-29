/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.mariannie.infom3;


import fr.insa.naho.modelinterface.GestionBDinterface;
import java.io.InputStream;
import java.sql.SQLException;
import static javafx.geometry.HPos.LEFT;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;



/**
 *
 * @author alexa
 */
public class VueGlobale extends BorderPane{
    
    private MainPane main;
    
    private Button disconnect;
    
            
    public VueGlobale (MainPane main) throws SQLException, GestionBDinterface.CategorieGenExisteDejaException, GestionBDinterface.CategorieExisteDejaException{
        
        this.main= main;
         Image fondecran =getImage("ressources/fond4.jpg", 25, 25);
        this.setBackground(new Background(new BackgroundImage(fondecran, 
                BackgroundRepeat.SPACE, 
                BackgroundRepeat.SPACE,
                BackgroundPosition.CENTER,
                BackgroundSize.DEFAULT)));
        
      
        // this.setStyle("-fx-background-color:#C3ECD8");
         
         /*this.disconnect= new Button("Déconnection");
        this.setRight(disconnect);
        
           disconnect.setOnAction((t) -> {
             
            this.main.setCenter(new VueInscription(this.main));  
         });*/

          VueEncheres vue = new VueEncheres(this.main);
         this.main.setTop( new VueEntete(this.main));
         this.main.setRight(new VuePanneauDroit(this.main));
         //this.main.setBottom(vue);
         //this.main.setAlignment(vue, Pos.BOTTOM_RIGHT);
        
       
        
      
       
          
          
         
        
    
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
