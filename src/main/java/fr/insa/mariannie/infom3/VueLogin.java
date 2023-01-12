/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.mariannie.infom3;


import fr.insa.naho.modelinterface.GestionBDinterface;
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

    public VueLogin(MainPane aThis) {
        this.main = aThis;
        this.lMail = new Label("Mail:");
        this.tfmail = new TextField();
        this.add(this.lMail, 0, 0);
        this.add(this.tfmail, 1, 0);

        this.mdp = new Label("Pass:");
        this.pass = new PasswordField();
        this.add(this.mdp, 0, 2);
        this.add(this.pass, 1, 2);

        this.bienvenue = new Label(" Dear Customer,welcome back!");
        this.add(this.bienvenue, 1, 5);

        this.login = new Button("Login");
      
        login.setOnAction((t) -> {
            try {
                GestionBDinterface.login(this.main.getCon(),this.tfmail.getText(),
                        this.pass.getText());
                this.main.setCurUserMail(this.tfmail.getText());
                this.main.setCenter(new VueGlobale(this.main));
            } catch (Exception ex) {
                Utils.showErrorInAlert("Pb bdd", "erreur login", ex.getLocalizedMessage());
            }
        });
        this.add(this.login, 1, 6);
        
        this.setAlignment(Pos.CENTER);
        this.setVgap(10);
        this.setHgap(10);
    }

    
}
