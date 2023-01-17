/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.mariannie.infom3;

import fr.insa.naho.modelinterface.GestionBDinterface;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.Optional;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;

/**
 *
 * @author alexa
 */
public class VueASavoir extends BorderPane{
    private MainPane main;
    
    public VueASavoir (MainPane main) throws SQLException, GestionBDinterface.CategorieGenExisteDejaException, GestionBDinterface.CategorieExisteDejaException{
        
        this.setStyle("-fx-color:white;-fx-font-size:14px;-fx-font-weight:bold");
        
        Image fondecran4 =getImage("ressources/fond4.jpg", 100, 100);
        this.setBackground(new Background(new BackgroundImage(fondecran4, 
                BackgroundRepeat.SPACE, 
                BackgroundRepeat.SPACE,
                BackgroundPosition.CENTER,
                BackgroundSize.DEFAULT)));
        this.main=main;
         Alert confirmer = new Alert(Alert.AlertType.INFORMATION);
            
            //confirmer.setTitle("Attention");
            confirmer.setHeaderText("");
            confirmer.setHeaderText("Pensez-à-écrire chaque 1ère lettre de chaque nom en majuscule lorsque: "
                    + "-vous créez un objet" + "-vous enchérissez"+
                            "-vous recherchez un objet"+ " EX: Lit en hauteur ou Chaise à Pied");
            ButtonType oui = new ButtonType("Ok");
            
            confirmer.getButtonTypes().clear();
           confirmer.getButtonTypes().addAll(oui);
            Optional<ButtonType> select = confirmer.showAndWait();
            this.main.setCenter(new VueGlobale(this.main));
            
            //this.setCenter(main);
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
