/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.mariannie.infom3;


import fr.insa.naho.modelinterface.GestionBDinterface;
import java.io.InputStream;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Border;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

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
        //this.setStyle("-fx-color:white;-fx-font-size:18px;-fx-font-weight:bold");
        
        Image fondecran =getImage("ressources/Inscription.jpg", 25, 25);
        this.setBackground(new Background(new BackgroundImage(fondecran, 
                BackgroundRepeat.SPACE, 
                BackgroundRepeat.SPACE,
                BackgroundPosition.CENTER,
                BackgroundSize.DEFAULT)));
       
        this.main = main;
        
        this.setAlignment(Pos.CENTER);
        this.setVgap(10);
        this.setHgap(10);
        
        this.nom = new Label("Nom:");
        nom.setTextFill(Color.WHITE);
        nom.setStyle("-fx-font-size: 20;-fx-font-weight:bold");
        this.tfnom = new TextField("");
        this.add(this.nom, 0, 1);
        this.add(this.tfnom, 1, 1);

        this.prenom = new Label("Prénom:");
        this.tfprenom = new TextField();
        prenom.setTextFill(Color.WHITE);
        prenom.setStyle("-fx-font-size: 20;-fx-font-weight:bold");
        this.add(this.prenom, 0, 2);
        this.add(this.tfprenom,1, 2);
        
        this.mail = new Label("Email:");
        this.tfmail = new TextField();
        mail.setTextFill(Color.WHITE);
        mail.setStyle("-fx-font-size: 20;-fx-font-weight:bold");
        this.add(this.mail, 0, 3);
        this.add(this.tfmail,1, 3);
        
        this.codepostal = new Label("Code Postal:");
        this.tfcodepostal = new TextField();
        codepostal.setTextFill(Color.WHITE);
        codepostal.setStyle("-fx-font-size: 20;-fx-font-weight:bold");
        this.add(this.codepostal, 0, 4);
        this.add(this.tfcodepostal,1, 4);
        

        this.pass = new Label("Pass:");
        this.pfpass = new PasswordField();
        pass.setTextFill(Color.WHITE);
        pass.setStyle("-fx-font-size: 20;-fx-font-weight:bold");
        this.add(this.pass, 0, 5);
        this.add(this.pfpass, 1, 5);

        this.bienvenue = new Label(" Cher client, bienvenue!");
        this.add(this.bienvenue, 1, 0);
        bienvenue.setTextFill(Color.WHITE);
        bienvenue.setStyle("-fx-font-size: 30;-fx-font-weight:bold");
       

        this.create = new Button("Create my account");
        create.setOnAction((t) -> {
            
            if ( this.tfnom.getText()==""||this.tfprenom.getText()==""){
    Utils.showErrorInAlert("","Veuillez remplir tous les champs");   
    return;
                
                }
            try{
                
                 GestionBDinterface.demandeUtilisateur(this.main.getCon(), this.tfnom.getText(), this.tfprenom.getText(),this.tfmail.getText(), 
                    this.pfpass.getText(), this.tfcodepostal.getText());
                    //createUtilisateur(this.main.getCon(),this.tfnom.getText(),this.tfprenom.getText(),this.tfmail.getText(),
                    //this.tfcodepostal.getText(),this.pfpass.getText());
                    System.out.println("ca marche");
            this.main.setCurUserMail(this.tfmail.getText());
            this.main.setCenter(new VueGlobale(this.main));
        }catch (Exception ex) {
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
  
    private Image getImage(String resourcePath,int w,int h) {
        InputStream input //
                = this.getClass().getResourceAsStream(resourcePath);
        Image image = new Image(input);
        
        image.widthProperty();
        image.heightProperty();
        return image;
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
}
