/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.mariannie.infom3;

import fr.insa.naho.modelinterface.GestionBDinterface;
import fr.insa.naho.modelinterface.Objet;
import java.io.InputStream;
import java.util.ArrayList;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.GridPane;

/**
 *
 * @author alexa
 */
public class VueDescription extends GridPane {
    
   
        
    
    
     private MainPane main;
    
     private TextField tfdesc ;
     
     private Button valider;
       
    
     public VueDescription(MainPane main){
         
           this.setStyle("-fx-color:white;-fx-font-size:14px;-fx-font-weight:bold");
         
          Image fondecran =getImage("ressources/fond4.jpg", 25, 25);
        this.setBackground(new Background(new BackgroundImage(fondecran, 
                BackgroundRepeat.SPACE, 
                BackgroundRepeat.SPACE,
                BackgroundPosition.CENTER,
                BackgroundSize.DEFAULT)));
        
    
         this.main = main;
        
        
        this.tfdesc = new TextField();
        this.add(this.tfdesc, 3,3 );
       
        
        this.valider = new Button("Valider");
        this.add(this.valider, 5,3 );
        
        this.setAlignment(Pos.TOP_CENTER);
        
         valider.setOnAction((t) -> {
             
             try { 
                ArrayList<Objet> objets;
                objets = GestionBDinterface.trouveObjetMot(this.main.getCon(), this.tfdesc.getText());
                
                this.main.setCenter(new VueAnnonces(this.main, objets));
            } catch (Exception ex) {
                Utils.showErrorInAlert("PB", ex.getLocalizedMessage());
            }
                
     
        
        
      
       
                 
});
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
        
