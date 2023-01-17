/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.mariannie.infom3;

import fr.insa.naho.modelinterface.GestionBDinterface;
import fr.insa.naho.modelinterface.Objet;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.GridPane;

/**
 *
 * @author alexa
 */
public class Annonce extends GridPane {

    private MainPane main;
    private TextField tfEnchere;
    private Button bEnchere;

    public Annonce(Objet obj, MainPane main) throws SQLException {
        
         this.setStyle("-fx-color:white;-fx-font-size:14px;-fx-font-weight:bold");
         
         Image fondecran =getImage("ressources/fond4.jpg", 100, 100);
        this.setBackground(new Background(new BackgroundImage(fondecran, 
                BackgroundRepeat.SPACE, 
                BackgroundRepeat.SPACE,
                BackgroundPosition.CENTER,
                BackgroundSize.DEFAULT)));
            this.main=main;
        
         
        this.main = main;

        int idCategorie = (obj.getCategorie());
        int idUtilisateur = (obj.getProposepar());
        String nomCategorie = recupereNomCategorie(idCategorie);
        String nomUtilisateur = recupereNomUtilisateur(idUtilisateur);

        this.add(new Label(obj.getTitre()), 2, 1);
        this.add(new Label("Titre:"), 1, 1);

        this.add(new Label("Description:"), 1, 2);
        this.add(new Label(obj.getDescription()), 2, 2);

        this.add(new Label("Catégorie:"), 1, 3);
        this.add(new Label(nomCategorie), 2, 3);

        this.add(new Label("Date de début d'enchère:"), 1, 4);
        this.add(new Label(String.valueOf(obj.getDebut())), 2, 4);

        this.add(new Label("Date de fin d'enchère:"), 1, 5);
        this.add(new Label(String.valueOf(obj.getFin())), 2, 5);

        this.add(new Label("Mis aux enchères par:"), 1, 6);
        this.add(new Label(nomUtilisateur), 2, 6);

        this.add(new Label("Prix:"), 1, 7);
        this.add(new Label(String.valueOf(obj.getPrixbase())), 2, 7);

        this.add(new Label("Montant actuel de l'enchère:"), 1, 8);
        this.add(new Label(String.valueOf(obj.getMax())), 2, 8);

        this.add(new Label("Vous avez enchéri de:"), 1, 9);
        this.add(new Label(String.valueOf(obj.getMontant())), 2, 9);

        this.tfEnchere = new TextField();
        this.bEnchere = new Button("Encherir");
        this.add(this.tfEnchere, 1, 10);
        this.add(this.bEnchere, 2, 10);
        
         byte[] imageBytes = obj.getImages();
           if (imageBytes !=null){
               
               Image image = new Image(new ByteArrayInputStream(imageBytes));
               ImageView imageview = new ImageView(image);
               imageview.setFitWidth(300); //fixer a 300 pixels
               imageview.setFitHeight(150);//fixer a 150 pixels
               imageview.setPreserveRatio(true); //conserver le ratio de l'image
               this.add(imageview,3,3,1,6);
           }

        this.bEnchere.setOnAction((t) -> {
            String mail = this.main.getCurUserMail();
            String titre = obj.getTitre();
            Scanner scannermontant = new Scanner(this.tfEnchere.getText());
             
            int montant = scannermontant.nextInt();
//Integer.parseInt(this.tfEnchere.getText());
            Connection con = this.main.getCon();
            try {
                GestionBDinterface.demandeEnchere(con, titre, montant, mail);
                Utils.showErrorInAlert("Enchere OK", "Vous avez bien enchéri sur cet objet", "");
            } catch (Exception ex) {
                Utils.showErrorInAlert("Erreur", "Montant trop bas", "");
            }
        });

        this.setHgap(10);

    }

    public String recupereNomCategorie(Integer id) throws SQLException {
        Connection con = this.main.getCon();
        try ( PreparedStatement st = con.prepareStatement("select * from categorie where id = ?")) {
            st.setInt(1, id);
            ResultSet res = st.executeQuery();
            while (res.next()) {
                return res.getString("nom"); //nom de la colone qui contient le nom de la categorie 
            }

        }
        return null;
    }

    public String recupereNomUtilisateur(Integer id) throws SQLException {
        Connection con = this.main.getCon();
        try ( PreparedStatement st = con.prepareStatement("select * from utilisateur where id = ?")) {
            st.setInt(1, id);
            ResultSet res = st.executeQuery();
            while (res.next()) {
                return res.getString("email"); //nom de la colone qui contient le nom de la categorie 
            }

        }
        return null;
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
