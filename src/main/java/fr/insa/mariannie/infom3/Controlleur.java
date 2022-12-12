/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.mariannie.infom3;

import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;

/**
 *
 * @author alexa
 */
public class Controlleur {
    
    private int state;
    private MainPane vue;
    private PasswordField passwordfield;
    private Label label;
    

   // Controlleur(MainPane aThis) {
    //    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    //}
     
    Controlleur (MainPane vue){
        this.vue=vue;
        //this.state=state;
        this.passwordfield = new PasswordField();
        this.label= new Label();
    }
       
        
    
    public void changeState(int state){ //fonction pour changer l'etat en fonction des events
        System.out.println("changedstate"+state);
        this.state=state;
        switch(state){
            case 1:
        }
    }
     public void State (){
         
         if (this.state==10){
             this.vue.PanneauPrincipal();
         
     }

    
}
     
   public void Pass(ActionEvent event) {
      
               String password = passwordfield.getText();
               label.setText(password);
   }
    
}

