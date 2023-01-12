/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.mariannie.infom3;

import fr.insa.naho.modelinterface.GestionBDinterface;
import fr.insa.naho.modelinterface.Objet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.GridPane;

/**
 *
 * @author alexa
 */
public class VueDéposerObjet extends GridPane{
    
    private MainPane main;
    
   
    private TextField tftitre;
    private TextField tfdescription;
    private TextField tffin;
    private TextField tfprix;
    private TextField tfcatgen;
    private TextField tfcat;
     private TextField tfannee;
      private TextField tfmois;
     private TextField tfdate;
   
    private Label Titre;
    private Label Description;
    private Label DateFin;
    private Label Prix;
    private Label CatGen;
    private Label Cat;
    
    private Label annee;
    private Label mois;
     private Label date; 
     private int Heure =00;
     private int Minute =00;
     private int Sec =00;
     private int valueannee;
     private int valuemois;
     private int valuedate;
     private int valueprix;
    private String fin;
    
    private ToggleButton depoObjet;
     
    public VueDéposerObjet ( MainPane main){
        
        this.main = main;
        
        this.setAlignment(Pos.CENTER);
        this.setVgap(10);
        this.setHgap(10);
        
        
        
        this.Titre = new Label("Titre:");
        this.tftitre = new TextField("");
        this.add(this.Titre, 0, 1);
        this.add(this.tftitre, 1, 1);

        this.Description = new Label("Description:");
        this.tfdescription = new TextField();
        this.add(this.Description, 0, 2);
        this.add(this.tfdescription,1, 2);
        
        this.annee = new Label("Année");
        this.tfannee = new TextField();
        this.add(this.annee, 0, 3);
        this.add(this.tfannee,1, 3);
        
          
        this.mois = new Label("Mois");
        this.tfmois = new TextField();
        this.add(this.mois, 0, 4);
        this.add(this.tfmois,1, 4);
        
          
        this.date = new Label("Date");
        this.tfdate = new TextField();
        this.add(this.date, 0, 5);
        this.add(this.tfdate,1, 5);
        
        this.Prix = new Label("Prix:");
        this.tfprix = new TextField();
        this.add(this.Prix, 0, 6);
        this.add(this.tfprix,1, 6);
        

        this.CatGen = new Label("Catégorie Générale:");
        this.tfcatgen = new TextField();
        this.add(this.CatGen, 0, 7);
        this.add(this.tfcatgen, 1, 7);
        
        this.Cat = new Label("Catégorie :");
        this.tfcat = new TextField();
        this.add(this.Cat, 0, 8);
        this.add(this.tfcat, 1, 8);
        
        this.depoObjet= new ToggleButton("Déposer mon objet");
         this.add(this.depoObjet, 4, 11);
       
        

        
          depoObjet.setOnAction((t) -> {
              
        try {
            
               Scanner scannerannee = new Scanner(this.tfannee.getText());
             this.valueannee= scannerannee.nextInt();
             Scanner scannerprix = new Scanner(this.tfprix.getText());
             this.valueprix= scannerprix.nextInt();
             Scanner scannermois = new Scanner(this.tfmois.getText());
             this.valuemois= scannermois.nextInt();
             Scanner scannerdate = new Scanner(this.tfdate.getText());
             this.valuedate= scannerdate.nextInt();
             
             this.fin = this.valueannee+"-"+this.valuemois+"-"+this.valuedate+" "+this.Heure+ ":" + this.Minute+":"+this.Sec;
                    System.out.println("bbbbbb");
            
                 GestionBDinterface.demandeObjet(this.main.getCon(), this.tftitre.getText(),this.tfdescription.getText(), 
                 valueprix,valueannee, valuemois,valuedate, this.fin,
                 this.tfcatgen.getText(),this.tfcat.getText(),this.main.getCurUserMail() );
                 
                 Alert confirmer = new Alert(Alert.AlertType.CONFIRMATION);
            
            //confirmer.setTitle("Attention");
            confirmer.setHeaderText("Votre objet est mis aux enchères ");
            confirmer.setHeaderText("Votre objet est maintenant mis aux enchères. "
                    +   "    Voulez vous mettre un nouvel objet aux enchères?");
            ButtonType oui = new ButtonType("Oui");
            ButtonType non = new ButtonType("Non");
            confirmer.getButtonTypes().clear();
           confirmer.getButtonTypes().addAll(oui, non);
            Optional<ButtonType> select = confirmer.showAndWait();
            if (select.get() == non) {
               this.main.setCenter(new VueGlobale(this.main));
                
            } else if (select.get() == oui) {
                tftitre.clear();
                tfdescription.clear();
                tfannee.clear();
                tfdate.clear();
                tfcat.clear();
                tfcatgen.clear();
                tfmois.clear();
                tffin.clear();
                tfprix.clear();

            }
           
                
                        
                 
                     
                 } catch (Exception ex) {
                Logger.getLogger(VueDéposerObjet.class.getName()).log(Level.SEVERE, null, ex);
            }
                        
                  
                  
                System.out.println("aaaaa");
           //GestionBD.demandeUtilisateur(this.main.getCon(), this.tfnom.getText(), this.tfprenom.getText(),this.tfmail.getText(), 
                    //this.pfpass.getText(), this.tfcodepostal.getText());  
                    
                    //createObjet(this.main.getCon(), this.tftitre.getText()this.tfdescription.getText(),
                 

       
            //} catch (Exception ex) {
            //Logger.getLogger(VueDéposerObjet.class.getName()).log(Level.SEVERE, null, ex);
          //  throw new Error (ex);
        //}
          });
    
        
    }

   


    }
    
    

