/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.mariannie.infom3;

import fr.insa.naho.model.GestionBD;
import java.sql.SQLException;
import static javafx.geometry.HPos.LEFT;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
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
    
     private ToggleButton InfosPerso;
    private Label label;
    private Categories cat;
    private ToggleButton search;
    private TextField tfsearch;
    private ToggleButton depoAnnonce;
    private ToggleButton disconnect;
    
            
    public VueGlobale (MainPane main){
        
        this.main= main;
        
       this.depoAnnonce=new ToggleButton ("Mise en vente");
        
        
        this.tfsearch = new TextField();
      
         
        this.search=new ToggleButton("recherche");
         
         
        

         
         this.main.setTop( new VueEntete(this.main));
         this.main.setRight(new VuePanneauDroit(this.main));
         
        
         
        
      /* InfosPerso.setOnAction((t) -> {
           
           this.main.setCenter(new VueInfosPerso(this.main));
                /*try {
                GestionBD.
             
            } catch (SQLException ex) {
                Utils.showErrorInAlert("Pb ", ex.getLocalizedMessage());
            }*/
       // });*
       
       depoAnnonce.setOnAction((t) -> {
           
           this.main.setCenter(new VueMiseEnVentes(this.main));
               
        });
       
          
              //this.add(this.cat,50,50);
           
           
           //this.add(new Categories);
           
           //this.setBottom(new Categories());
          //try{
             //  GestionBD.mesObjets(con, emailuser);
           //}
                
               
                
                
            //});
        
        
        
       /* login.setOnAction((t) -> {
            try{
            GestionBD.createUtilisateur(this.main.getCon(),this.tfnom.getText(),this.tfprenom.getText(),this.tfmail.getText(),
                    this.tfcodepostal.getText(),this.pfpass.getText());
        }catch (SQLException ex) {
                Utils.showErrorInAlert("Pb bdd", "erreur création compte", ex.getLocalizedMessage());
            } catch (GestionBD.EmailExisteDejaException ex) {
                Logger.getLogger(VueInscription.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        });
        */   
        
    
}

   

    
}
