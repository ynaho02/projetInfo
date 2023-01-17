/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.mariannie.infom3;

import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.GridPane;

/**
 *
 * @author alexa
 */
public class VueDeconnexion extends GridPane{
    
    private Button disconnect;
    
    public VueDeconnexion (){
    
    this.disconnect= new Button("Déconnection");
        this.add(disconnect,0,1);
        
           disconnect.setOnAction((t) -> {
             
            //setCenter(new VueInscription());  
         });
    
}
}
