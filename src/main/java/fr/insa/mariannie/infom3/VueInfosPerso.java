/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.mariannie.infom3;

import fr.insa.naho.model.GestionBD;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.GridPane;

/**
 *
 * @author alexa
 */
public class VueInfosPerso extends GridPane {
    
   private MainPane main;
    
    private ToggleButton mesenchères;
    private ToggleButton mesobjets;
    private RadioButton retour;
    
    
    public VueInfosPerso (MainPane main){
        
        this.main=main;
        
        this.mesenchères= new ToggleButton("Mes enchères");
        this.mesobjets= new ToggleButton ("Mes objets");
        this.retour= new RadioButton("Retour");
        
        this.add(this.mesobjets,2,0);
        this.add(this.mesenchères, 3, 0);
        this.add(this.retour,10,0);
        this.setHgap(10);
        this.setVgap(2);
        
        mesobjets.setOnAction((t) -> {
            try{
            GestionBD.mesObjets(this.main.getCon(),this.main.getCurUserMail());
            //this.main.setCenter(new VueGlobale(this.main));
                   }catch (SQLException ex) {
                Utils.showErrorInAlert("PB", ex.getLocalizedMessage());
            
            
        }   catch (Exception ex) {
                Logger.getLogger(VueInfosPerso.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        
    });
    
        
        mesenchères.setOnAction((t) -> {
            
           // try{
               // String emailuser = this.main.getCurUserMail();
           //  GestionBD.mesEncheres(this.main.getCon(), this.main.getCurUserMail());
            //this.main.setCenter(new VueGlobale(this.main));   Si je le commente pas, alors va direct sur vue globae = PB! et montre pas enchères/objets
       // }catch (SQLException ex) {
               // Utils.showErrorInAlert("PB", ex.getLocalizedMessage());
                this.main.setCenter(new VueGlobale(this.main)); // Si je le met ici il se passe rien
        //}
            
                        
        });
        
        retour.setOnAction((t) -> {
            
            this.main.setCenter(new VueGlobale(this.main));
            
        });
  
}
}