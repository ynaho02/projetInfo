/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.mariannie.infom3;


import fr.insa.naho.modelinterface.GestionBDinterface;
import fr.insa.naho.modelinterface.Objet;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.geometry.Pos;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

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
        
         this.setStyle("-fx-color:white;-fx-font-size:14px;-fx-font-weight:bold");
        
        Image fondecran =getImage("ressources/fond4.jpg", 100, 100);
        this.setBackground(new Background(new BackgroundImage(fondecran, 
                BackgroundRepeat.SPACE, 
                BackgroundRepeat.SPACE,
                BackgroundPosition.CENTER,
                BackgroundSize.DEFAULT)));
            this.main=main;
        
        this.main=main;
        this.setVgap(8);
        
        this.mesenchères= new ToggleButton("Mes enchères");
        mesenchères.setStyle("-fx-color:white;-fx-font-weight:bold");
        this.mesenchères.setPrefWidth(120);
        this.mesobjets= new ToggleButton ("Mes objets");
        mesobjets.setStyle("-fx-color:white;-fx-font-weight:bold");
        this.mesobjets.setPrefWidth(120);
        this.retour= new RadioButton("Retour");
        retour.setStyle("-fx-color:white;-fx-font-weight:bold");
        this.retour.setPrefWidth(120);
        
        Region region= new Region();
        Region region1= new Region();
        
        
        VBox vb = new VBox(mesenchères,region,mesobjets,region1,retour);
        Image fondecran5 =getImage("ressources/fond4.jpg", 100, 100);
        vb.setBackground(new Background(new BackgroundImage(fondecran5, 
                BackgroundRepeat.SPACE, 
                BackgroundRepeat.SPACE,
                BackgroundPosition.CENTER,
                BackgroundSize.DEFAULT)));
        
        region.setPrefSize(10, 10);
        region1.setPrefSize(10, 10);
        
        this.main.setRight(vb);
       /* this.add(this.mesobjets,2,0);
        this.add(this.mesenchères, 3, 0);
        this.add(this.retour,10,0);
        this.setHgap(10);
        this.setVgap(2);*/
        
        
       
        mesobjets.setOnAction((t) -> {
            
            
         
            try{
                ArrayList<Objet> objets   ;
                objets=GestionBDinterface.mesObjets(this.main.getCon(),this.main.getCurUserMail());
                
                 
                this.main.setCenter(new VueAnnonces(this.main, objets));
                
            } catch (Exception ex){
                
            }
            
           
        
        
        
    
    });
    
        
        mesenchères.setOnAction((t) -> {
            
            try{
                 ArrayList<Objet> objets   ;
            
    
                objets= GestionBDinterface.mesObjetsVoulus(this.main.getCon(),this.main.getCurUserMail());
                
                this.main.setCenter(new VueAnnonces(this.main, objets));
                        
            }catch (Exception ex){
                
            }
            
            
                        
            
                        
        });
        
        retour.setOnAction((t) -> {
            
            try {
                this.main.setCenter(new VueGlobale(this.main));
            } catch (SQLException ex) {
                Logger.getLogger(VueInfosPerso.class.getName()).log(Level.SEVERE, null, ex);
            } catch (GestionBDinterface.CategorieGenExisteDejaException ex) {
                Logger.getLogger(VueInfosPerso.class.getName()).log(Level.SEVERE, null, ex);
            } catch (GestionBDinterface.CategorieExisteDejaException ex) {
                Logger.getLogger(VueInfosPerso.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        });
  
}
    
    private Image getImage(String resourcePath,int w,int h) {
        InputStream input //
                = this.getClass().getResourceAsStream(resourcePath);
        Image image = new Image(input);
        
        image.widthProperty();
        image.heightProperty();
        return image;
}
}