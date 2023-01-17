/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.mariannie.infom3;


import fr.insa.naho.modelinterface.GestionBDinterface;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
public class VuePanneauDroit extends GridPane {
    
    
   private MainPane main;
    
    private  ToggleButton InfosPerso;
    
    public  ToggleButton disconnect;
    private ToggleButton asavoir;
         
    //private Button encherir;
        VuePanneauDroit (MainPane main){
            
            this.setStyle("-fx-color:white;-fx-font-size:14px;-fx-font-weight:bold");
        
        Image fondecran =getImage("ressources/fond4.jpg", 100, 100);
        this.setBackground(new Background(new BackgroundImage(fondecran, 
                BackgroundRepeat.SPACE, 
                BackgroundRepeat.SPACE,
                BackgroundPosition.CENTER,
                BackgroundSize.DEFAULT)));
            this.main=main;
                    
        
       this.setVgap(8);
        
        this.InfosPerso= new ToggleButton ();
        ImageView ivDisconnect1 =getIcon("ressources/personne.png", 25, 25);
        this.InfosPerso.setGraphic(ivDisconnect1);
        this.InfosPerso.setPrefWidth(120);
        this.add(InfosPerso, 0, 0);
        
        this.asavoir = new ToggleButton("A Savoir");
        ImageView ivDisconnect2 =getIcon("ressources/information.png", 25, 25);
        this.asavoir.setGraphic(ivDisconnect2);
         this.add(this.asavoir,0,1);
         
         this.asavoir.setPrefWidth(120);
        
        /*this.encherir = new Button("enchérir");
        this.add(this.encherir, 0,2 );
        
        
              
      
        this.setVgap(100);
        
         encherir.setOnAction((t) -> {
             
             this.main.setCenter(new VueEncheres(this.main));
             
         });*/
         
         asavoir.setOnAction((t) -> {
             
                try {
                    this.main.setCenter(new VueASavoir(main));
                } catch (SQLException ex) {
                    Logger.getLogger(VuePanneauDroit.class.getName()).log(Level.SEVERE, null, ex);
                } catch (GestionBDinterface.CategorieGenExisteDejaException ex) {
                    Logger.getLogger(VuePanneauDroit.class.getName()).log(Level.SEVERE, null, ex);
                } catch (GestionBDinterface.CategorieExisteDejaException ex) {
                    Logger.getLogger(VuePanneauDroit.class.getName()).log(Level.SEVERE, null, ex);
                }
         });
         
        
        this.disconnect= new ToggleButton();
        ImageView ivDisconnect =getIcon("ressources/logout.png", 25, 25);
        this.disconnect.setGraphic(ivDisconnect);
        this.disconnect.setPrefWidth(120);
        this.add(disconnect,0,2);
        
           disconnect.setOnAction((t) -> {
             
            this.main.setCenter(new VueInscription(this.main));  
            this.main.setTop(null);  
            this.main.setRight(null);  
            this.main.setLeft(null);  
         });
         
       // this.setStyle("-fx-background-color:#E7C285"); 
        
        //VBox vbox = new VBox(this.InfosPerso,this.disconnect);
        
        
        
        //this.getChildren().addAll(InfosPerso,disconnect);
        
        
         
       InfosPerso.setOnAction((t) -> {
           this.setStyle("-fx-color:white;-fx-font-size:14px;-fx-font-weight:bold");
        
        Image fondecran4 =getImage("ressources/fond4.jpg", 100, 100);
        this.setBackground(new Background(new BackgroundImage(fondecran4, 
                BackgroundRepeat.SPACE, 
                BackgroundRepeat.SPACE,
                BackgroundPosition.CENTER,
                BackgroundSize.DEFAULT)));
            this.main=main;
           
           this.main.setCenter(new VueInfosPerso(this.main));
              
        });
        
    }
        
        private ImageView getIcon(String resourcePath,int w,int h) {
        InputStream input //
                = this.getClass().getResourceAsStream(resourcePath);
        Image image = new Image(input);
        ImageView IV = new ImageView(image);
        IV.setFitHeight(h);
        IV.setFitWidth(w);
        return IV;
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
