/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.mariannie.infom3;

import fr.insa.naho.model.GestionBD;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Border;
import javafx.scene.layout.GridPane;

/**
 *
 * @author alexa
 */
public class VueInscription extends GridPane {

    private MainPane main;
    private TextField tfnom;
    private TextField tfprenom;
    private TextField tfmail;
    private TextField tfcodepostal;
    private PasswordField pfpass;
    
   
    private Label nom;
    private Label prenom;
    private Label pass;
    private Label mail;
    private Label codepostal;
    
    private Button login;
    private Button create;
    
    private Label bienvenue;
  

    public VueInscription(MainPane main) {
        this.main = main;
        
        this.setAlignment(Pos.CENTER);
        this.setVgap(10);
        this.setHgap(10);
        
        this.nom = new Label("Nom:");
        this.tfnom = new TextField("");
        this.add(this.nom, 0, 1);
        this.add(this.tfnom, 1, 1);

        this.prenom = new Label("Prénom:");
        this.tfprenom = new TextField();
        this.add(this.prenom, 0, 2);
        this.add(this.tfprenom,1, 2);
        
        this.mail = new Label("Email:");
        this.tfmail = new TextField();
        this.add(this.mail, 0, 3);
        this.add(this.tfmail,1, 3);
        
        this.codepostal = new Label("Code Postal:");
        this.tfcodepostal = new TextField();
        this.add(this.codepostal, 0, 4);
        this.add(this.tfcodepostal,1, 4);
        

        this.pass = new Label("Pass:");
        this.pfpass = new PasswordField();
        this.add(this.pass, 0, 5);
        this.add(this.pfpass, 1, 5);

        this.bienvenue = new Label(" Dear Customer,welcome!");
        this.add(this.bienvenue, 0, 0);
       

        this.create = new Button("Create my account");
        create.setOnAction((t) -> {
            
            if ( this.tfnom.getText()==""||this.tfprenom.getText()==""){
    Utils.showErrorInAlert("","Veuillez remplir tous les champs");   
    return;
                
                }
            try{
                boolean test = true;
                /*test=*/ GestionBD.demandeUtilisateur(this.main.getCon(), this.tfnom.getText(), this.tfprenom.getText(),this.tfmail.getText(), 
                    this.pfpass.getText(), this.tfcodepostal.getText());
                    //createUtilisateur(this.main.getCon(),this.tfnom.getText(),this.tfprenom.getText(),this.tfmail.getText(),
                    //this.tfcodepostal.getText(),this.pfpass.getText());
            this.main.setCurUserMail(this.tfmail.getText());
            this.main.setCenter(new VueGlobale(this.main));
        }catch (SQLException ex) {
                Utils.showErrorInAlert("Pb bdd", "erreur création compte", ex.getLocalizedMessage());
            }
            
        });
        
        this.add(this.create, 1, 6);
        
        this.login = new Button ("Login");
        this.add(this.login,2, 6);
        this.login.setOnAction((t) -> {
            
            this.main.setCenter(new VueLogin(this.main));
               
                
            });
    }
/* try {
                GestionBD.login(this.main.getCon(),this.tfMail.getText(),
                        this.pass.getText());
            } catch (SQLException ex) {
                Utils.showErrorInAlert("Pb bdd", "erreur login", ex.getLocalizedMessage());
            }
        });*/
    
}
