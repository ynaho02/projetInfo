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
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import static javafx.scene.input.KeyCode.W;
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
public class VueLogin extends GridPane {

    private MainPane main;
    private TextField tfmail;
    private Label lMail;
   
    private Label mdp;
    private Button login;
    private PasswordField pass;
    
    private Label bienvenue;
    private String mail;
    private String password;
    
    private Button retour;
    
    
    public VueLogin(MainPane main) {
        this.setStyle("-fx-color:red;-fx-font-size:14px;-fx-font-weight:bold");
        
        Image fondecran =getImage("ressources/login3.jpg", 100, 100);
        this.setBackground(new Background(new BackgroundImage(fondecran, 
                BackgroundRepeat.SPACE, 
                BackgroundRepeat.SPACE,
                BackgroundPosition.CENTER,
                BackgroundSize.DEFAULT)));
        this.main = main;
        this.lMail = new Label("Mail:");
        this.tfmail = new TextField();
        this.add(this.lMail, 0, 0);
        this.add(this.tfmail, 1, 0);

        this.mdp = new Label("Pass:");
        this.pass = new PasswordField();
        this.add(this.mdp, 0, 2);
        this.add(this.pass, 1, 2);

        this.bienvenue = new Label(" Cher Client, quel plaisir de vous revoir!");
        this.add(this.bienvenue, 1, 5);
        bienvenue.setStyle("-fx-font-size: 20");

        this.login = new Button("Login");
        this.login.setStyle("-fx-background-radius:14px");
        
        this.retour = new Button ("Retour");
        this.retour.setStyle("-fx-background-radius:14px");
        this.add(this.retour,2,6);
      
        login.setOnAction((t) -> {
            
           
            try {
                   
               boolean oui= GestionBDinterface.login(this.main.getCon(),this.tfmail.getText(),
                        this.pass.getText());
                this.main.setCurUserMail(this.tfmail.getText());
                if(oui==true){
                this.main.setCenter(new VueGlobale(this.main));   
                    System.out.println("ca marche!");
                } else {
                    System.out.println("pb connection");
                }
                   
                    
                
                
            
            
        }   catch (Exception ex) {
                Logger.getLogger(VueLogin.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
       
        retour.setOnAction((t) -> {
            
             this.main.setCenter(new VueInscription(this.main));
        });
        
         /* try{
                boolean test = true;
                /*test=*//* GestionBDinterface.demandeUtilisateur(this.main.getCon(), this.tfnom.getText(), this.tfprenom.getText(),this.tfmail.getText(), 
                    this.pfpass.getText(), this.tfcodepostal.getText());
                    //createUtilisateur(this.main.getCon(),this.tfnom.getText(),this.tfprenom.getText(),this.tfmail.getText(),
                    //this.tfcodepostal.getText(),this.pfpass.getText());
            this.main.setCurUserMail(this.tfmail.getText());
            this.main.setCenter(new VueGlobale(this.main));
        }catch (Exception ex) {
                Utils.showErrorInAlert("Pb bdd", "erreur création compte", ex.getLocalizedMessage());
            }*/
            
        
        this.add(this.login, 1, 6);
        
        this.setAlignment(Pos.CENTER);
        this.setVgap(10);
        this.setHgap(10);
          

    
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
