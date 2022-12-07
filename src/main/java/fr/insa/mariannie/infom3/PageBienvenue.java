/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.mariannie.infom3;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;



/**
 *
 * @author alexa
 */
public class PageBienvenue extends BorderPane{
    
     private ToggleButton InfosPerso;
    private Label label; 
            
    public PageBienvenue (){
        
        this.InfosPerso= new ToggleButton ();
        
        this.label=new Label("Bienvenue sur notre site de ventes aux enchères");
        this.setCenter(label);
        
         VBox vbox= new VBox (this.InfosPerso); 
        this.setRight(vbox);
        
       PasswordField passwordField = new PasswordField();
       Button button = new Button("Show Password");
       Label label = new Label("?");
        
        
        
         
        
    
}

    PageBienvenue(MainPane aThis) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
