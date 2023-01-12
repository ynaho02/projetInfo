/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.mariannie.infom3;

import javafx.scene.control.ToggleButton;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

/**
 *
 * @author alexa
 */
public class VuePanneauDroit extends GridPane {
    
    
   private MainPane main;
    
    private  ToggleButton InfosPerso;
    
    public  ToggleButton disconnect;
         
        VuePanneauDroit (MainPane main){
            this.main=main;
        
        this.InfosPerso= new ToggleButton ("Infos Utilisateur");
        this.add(InfosPerso, 0, 0);
         
        
        this.disconnect= new ToggleButton("Déconnection");
        this.add(disconnect,0,1);
        
        //VBox vbox = new VBox(this.InfosPerso,this.disconnect);
        
        
        
        //this.getChildren().addAll(InfosPerso,disconnect);
        
        
         
       InfosPerso.setOnAction((t) -> {
           
           this.main.setCenter(new VueInfosPerso(this.main));
              
        });
        
    }
           
    
}
