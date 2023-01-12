package fr.insa.mariannie.infom3;


import fr.insa.naho.modelinterface.Objet;
import java.sql.SQLException;
import java.util.ArrayList;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author alexa
 */
public class VueAnnonces extends ScrollPane  {
    private MainPane main;

    
    private GridPane gridAnnonce;
    
    public VueAnnonces(MainPane main, ArrayList<Objet> objets) throws SQLException {
        this.main = main;
        this.gridAnnonce= new GridPane();
        this.gridAnnonce.setVgap(30);
        this.gridAnnonce.setAlignment(Pos.TOP_CENTER);
        
        afficherAnnonce(objets);
        
        
        
        this.setContent(gridAnnonce);
    }

    private void afficherAnnonce(ArrayList<Objet> objets) throws SQLException {
        int t =objets.size();
       for(int i=0; i<t;i++){
           this.gridAnnonce.add(new Annonce(objets.get(i),this.main), 0, i);
       }
        
    }

   
    
}
