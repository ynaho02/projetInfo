/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.mariannie.infom3;

import java.lang.ModuleLayer.Controller;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 *
 * @author alexa
 */
public class MainPane extends BorderPane {
    
    
    
    private ToggleButton DeposerAnnonce;
    private ToggleButton Categories;
    private ToggleButton InfosPerso;
    private ScrollPane scrollpane;
    private Controlleur controlleur;
    
   private PageBienvenue welcome;
   
    public MainPane() {
        
        this.controlleur=new Controlleur(this);
        
        this.DeposerAnnonce = new ToggleButton("depose annonce");
        this.Categories = new ToggleButton("categories");
        this.scrollpane = new ScrollPane();
        
        this.welcome = new PageBienvenue(this);
        this.controlleur.changeState(0);
            System.out.println("Bienvenue");
        
        
         scrollpane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
         scrollpane.setHbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
         VBox scroll= new VBox(this.scrollpane);
         this.setRight(scrollpane);
         
         this.InfosPerso.setOnAction((t) -> {
             this.controlleur.changeState(10);
         });
         
         
         
       
        HBox hbox = new HBox(this.DeposerAnnonce,this.Categories);
        this.setTop(hbox);
         this.InfosPerso = new ToggleButton("infos perso");
        VBox vbox= new VBox (this.InfosPerso); 
        this.setRight(vbox);
         this.setBottom(new Categories());
         
         
        
        
    }
  
}
    
    

