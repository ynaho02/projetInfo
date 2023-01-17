/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.mariannie.infom3;

import com.sun.javafx.scene.control.MenuBarButton;
import fr.insa.naho.modelinterface.GestionBDinterface;
import java.io.InputStream;
import java.sql.Timestamp;
import java.util.Optional;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.application.Application;
import javafx.event.EventType;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

/**
 *
 * @author alexa
 */
public class VueEncheres extends GridPane {
    
    private MainPane main;
    
      private Timestamp quand;
      private Label montant;
      private TextField tfmontant;
      private int offre;
      
      private Label titre;
      private TextField tftitre;
      private Label email;
      private TextField tfemail;
      
      
      
      
      private  Button encherir;
   
    public VueEncheres (MainPane main){
         this.setStyle("-fx-color:white;-fx-font-size:14px;-fx-font-weight:bold");
        
        
         Image fondecran =getImage("ressources/fond4.jpg", 25, 25);
        this.setBackground(new Background(new BackgroundImage(fondecran, 
                BackgroundRepeat.SPACE, 
                BackgroundRepeat.SPACE,
                BackgroundPosition.CENTER,
                BackgroundSize.DEFAULT)));
        
        
        this.main=main;
        
        
        this.titre=new Label("Titre:");
        this.tftitre= new TextField();
        this.add(this.titre, 0,1 );
         this.add(this.tftitre, 1,1 );
         
        this.montant=new Label("Montant:");
        this.tfmontant= new TextField();
        this.add(this.montant, 0,2 );
        this.add(this.tfmontant, 1, 2);  
        
         this.email=new Label("Email:");
        this.tfemail= new TextField();
        this.add(this.email, 0,3 );
         this.add(this.tfemail, 1, 3);  
         
         this.encherir=  new Button ("Enchérir");
         this.add(this.encherir, 4,5);
        
        
         this.setAlignment(Pos.CENTER_RIGHT);;
        this.setHgap(10);
        this.setVgap(8);
        
      
        
        
        encherir.setOnAction((t) -> {
            
              try {
               Timestamp moment = new Timestamp(System.currentTimeMillis());
        this.quand=moment;
         Scanner scannermontant = new Scanner(this.tfmontant.getText());
             this.offre= scannermontant.nextInt();
           
                 Alert confirmer = new Alert(Alert.AlertType.CONFIRMATION);
            
            //confirmer.setTitle("Attention");
            
            confirmer.setHeaderText("Vous allez enchérir sur cet objet. Etes vous sur?");
            ButtonType oui = new ButtonType("Oui");
            ButtonType non = new ButtonType("Non");
            confirmer.getButtonTypes().clear();
           confirmer.getButtonTypes().addAll(oui, non);
            Optional<ButtonType> select = confirmer.showAndWait();
            if (select.get() == oui) {
               
            GestionBDinterface.demandeEnchere(this.main.getCon(),this.tftitre.getText(),this.offre,this.tfemail.getText());
                confirmer.setHeaderText("Votre enchère a bien été effectuée." +"Souhaitez-vous enchérir sur un autre objet? ");
                ButtonType oui1 = new ButtonType("Oui");
                ButtonType non1 = new ButtonType("Non");
            confirmer.getButtonTypes().clear();
           confirmer.getButtonTypes().addAll(oui, non);
            Optional<ButtonType> select1 = confirmer.showAndWait();
            if (select1.get() == oui) {
            this.tftitre.clear();
            this.tfmontant.clear();
            
                
            } else if (select1.get() == non) {
                this.main.setCenter(new VueGlobale(this.main));
            }
            
               
            } else if (select.get() == non) {
                this.main.setCenter(new VueGlobale(this.main));
            }
            
           
                 } catch (Exception ex) {
                Logger.getLogger(VueDéposerObjet.class.getName()).log(Level.SEVERE, null, ex);
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
            
             
           
            
                
        
         
       
        
        //demandeEnchere(this.quand,this.offre,objet,mail)
        
    
