/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.mariannie.infom3;

import java.util.Optional;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

/**
 *
 * @author alexa
 */
public class VueLogin extends GridPane {
    private MainPane main;
    private TextField text;
    private Label nom;
    private Label prenom;
    private Label mdp;
    private Button login;
    private PasswordField pass;
    private TextField textfield;
    private Label bienvenue;

    public VueLogin(MainPane aThis) {
        this.main= aThis;
        this.nom=new Label ("Nom:");
        this.text =new TextField("");
        this.add(this.nom, 0, 0);
        this.add(this.text, 1, 0);
       
        this.prenom = new Label ("Prénom:");
        this.textfield= new TextField ();
        this.add(this.prenom, 0,2);
        this.add(this.textfield, 1,1);
        
        this.mdp= new Label("Pass:");
        this.pass= new PasswordField();
        this.add(this.mdp, 0, 2);
        this.add(this.textfield, 1,2);
        
        this.bienvenue= new Label (" Dear Customer,welcome!");
        this.add(this.bienvenue, 3,4);
        
        
        this.login = new Button ("Login");
        login.setOnAction((t)-> {
            //Login();
        });
        this.add(this.login, 5,5);
    }
}
    //}
  
   //public void Login(){
       
     // this.textfield = new TextField();
     // this.pass= new PasswordField();
      //try{
       //    Optional<Utilisateur> user
       //             = GestionBdD.login(this.main.getBdd(), nom, pass);
          
     // }  catch (SQLException ex) {
        //    JavaFXUtils.showErrorInAlert("Pb bdd", "Erreur",
          //          ex.getMessage());
       
   //}
    /*ublic void doLogin() {
        String nom = this.tfNom.getText();
        String pass = this.pfPass.getText();
        try {
            Optional<Utilisateur> user
                    = GestionBdD.login(this.main.getBdd(), nom, pass);
            if (user.isEmpty()) {
                JavaFXUtils.showErrorInAlert("Erreur", "utilisateur invalide", "");
            } else {
                this.main.getSessionInfo().setCurUser(user);
                this.main.setEntete(new Label("connection OK"));
                this.main.setMainContent(new Label("vous êtes " + user));
            }

        } catch (SQLException ex) {
            JavaFXUtils.showErrorInAlert("Pb bdd", "Erreur",
                    ex.getMessage());
        }
    }
    
    /*this.main = main;
        this.getChildren().add(new Label("nom :"));
        this.tfNom = new TextField();
        this.getChildren().add(this.tfNom);
        this.getChildren().add(new Label("pass :"));
        this.pfPass = new PasswordField();
        this.getChildren().add(this.pfPass);
        Button bLogin = new Button("Login");
        bLogin.setOnAction((t) -> {
            doLogin();
        });
        this.getChildren().add(bLogin);*/
//}

      