/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.mariannie.infom3;

import fr.insa.naho.model.GestionBD;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 *
 * @author alexa
 */
public class MainPane extends BorderPane {
    
    
    private Connection con;
    private String curUserMail;
    private String curName;
    
    
    private ToggleButton DeposerAnnonce;
    private ToggleButton Categories;
    private ToggleButton InfosPerso;
    private ScrollPane scrollpane;
    private Controlleur controlleur;
    private Label label;
    private PasswordField passwordfield;
   private VueGlobale welcome;
   
    public MainPane() {
        
        try {
            this.controlleur=new Controlleur(this);
            
            this.con = GestionBD.defautConnect();
            
            this.DeposerAnnonce = new ToggleButton("depose annonce");
            this.Categories = new ToggleButton("categories");
            this.scrollpane = new ScrollPane();
            this.InfosPerso= new ToggleButton();
           
            
            
            //this.InfosPerso= new ToggleButton ("Infos Perso");
            //this.setRight(InfosPerso);
            
           // this.label=new Label("Bienvenue sur notre site de ventes aux enchères");
           // this.setCenter(label);
            
                     
           //VBox vbox= new VBox (this.InfosPerso);
            //this.setRight(vbox);
            
             this.setCenter(new VueInscription(this));
            
           // this.InfosPerso.setOnAction((t) -> {
               // this.setCenter(new VueGlobale(this));
                
                // this.controlleur.Pass(t);
                
                
           // });
            
            
            //this.welcome = new PageBienvenue();
            //this.controlleur.changeState(0);
            // System.out.println("Bienvenue");
            
            
            scrollpane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
            scrollpane.setHbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
            // VBox scroll= new VBox(this.scrollpane);
            //this.setRight(scrollpane);
            
//         this.InfosPerso.setOnAction((t) -> {
//             this.controlleur.changeState(10);
//             this.PanneauPrincipal();
//         });
        } catch (ClassNotFoundException  | SQLException ex) {
            this.setCenter(new Label("pb bdd : "+ex.getLocalizedMessage()));
        }
         
         
         
    }  
    
    public void PanneauPrincipal (){
         
        HBox hbox = new HBox(this.DeposerAnnonce,this.Categories);
        this.setTop(hbox);
         this.InfosPerso = new ToggleButton("infos perso");
        VBox vbox= new VBox (this.InfosPerso); 
        this.setRight(vbox);
         this.setBottom(new Categories());
         
         
        
        
    }

    /**
     * @return the con
     */
    public Connection getCon() {
        return con;
    }

    /**
     * @return the curUserMail
     */
    public String getCurUserMail() {
        return curUserMail;
    }

    /**
     * @param curUserMail the curUserMail to set
     */
    public void setCurUserMail(String curUserMail) {
        this.curUserMail = curUserMail;
    }

    /**
     * @return the curName
     */
    public String getCurName() {
        return curName;
    }

    /**
     * @param curName the curName to set
     */
    public void setCurName(String curName) {
        this.curName = curName;
    }
  
}
        
    
    

