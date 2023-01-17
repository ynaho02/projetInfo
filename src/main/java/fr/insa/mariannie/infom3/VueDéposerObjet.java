/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.mariannie.infom3;

import fr.insa.naho.modelinterface.GestionBDinterface;
import fr.insa.naho.modelinterface.Objet;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
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
public class VueDéposerObjet extends GridPane {

    private MainPane main;

    private TextField tftitre;
    private TextField tfdescription;
    private TextField tffin;
    private TextField tfprix;
    private TextField tfcatgen;
    private TextField tfcat;
    private TextField tfannee;
    private TextField tfmois;
    private TextField tfdate;

    private Label Titre;
    private Label Description;
    private Label DateFin;
    private Label Prix;
    private Label CatGen;
    private Label Cat;

    private Label annee;
    private Label mois;
    private Label date;
    private int Heure = 00;
    private int Minute = 00;
    private int Sec = 00;
    private int valueannee;
    private int valuemois;
    private int valuedate;
    private int valueprix;
    private String fin;
    private ComboBox cbcategorie;

    private ToggleButton depoObjet;

    private Button image;
    private byte[] images;

    public VueDéposerObjet(MainPane main) {
        
         this.setStyle("-fx-color:white;-fx-font-size:14px;-fx-font-weight:bold");
        
        
         Image fondecran =getImage("ressources/fond4.jpg", 25, 25);
        this.setBackground(new Background(new BackgroundImage(fondecran, 
                BackgroundRepeat.SPACE, 
                BackgroundRepeat.SPACE,
                BackgroundPosition.CENTER,
                BackgroundSize.DEFAULT)));

        this.main = main;

        this.setAlignment(Pos.CENTER);
        this.setVgap(10);
        this.setHgap(10);

        this.images = null;

        this.cbcategorie = new ComboBox();
        String c0 = "Catégories";

        String c16 = "Arts";
        String c4 = "Bijoux";
        String c1 = "Immobilier";
        String c14 = "Electroménager";
        String c2 = "Véhicules";
        String c3 = "Vêtements";

        String c5 = "Musique";
        String c6 = "Livres";
        String c7 = "Films";
        String c8 = "Jeux & Jouets";
        String c9 = "Vins & Gastronomie";
        String c10 = "Jeux Videos";
        String c11 = "Téléphonies";
        String c12 = "Image & Son";
        String c13 = "Informatique";

        String c15 = "Décoration";

        String c17 = "Matériel Agricole";
        String c18 = "BTP";
        ObservableList<String> listCategorie = FXCollections.observableArrayList(c16, c4, c14, c7, c8, c9, c10, c11, c12, c13, c15, c17, c18, c1, c2, c3, c5, c6);

        this.cbcategorie.setItems(listCategorie);

        this.Titre = new Label("Titre:");
        this.tftitre = new TextField("");
        this.add(this.Titre, 0, 1);
        this.add(this.tftitre, 1, 1);

        this.Description = new Label("Description:");
        this.tfdescription = new TextField();
        this.add(this.Description, 0, 2);
        this.add(this.tfdescription, 1, 2);

        this.annee = new Label("Date de fin d'enchère: Année");
        this.tfannee = new TextField();
        this.add(this.annee, 0, 3);
        this.add(this.tfannee, 1, 3);

        this.mois = new Label("Mois");
        this.tfmois = new TextField();
        this.add(this.mois, 0, 4);
        this.add(this.tfmois, 1, 4);

        this.date = new Label("Date ");
        this.tfdate = new TextField();
        this.add(this.date, 0, 5);
        this.add(this.tfdate, 1, 5);

        this.Prix = new Label("Prix:");
        this.tfprix = new TextField();
        this.add(this.Prix, 0, 6);
        this.add(this.tfprix, 1, 6);

        /*this.CatGen = new Label("Catégorie Générale:");
        this.tfcatgen = new TextField();
        this.add(this.CatGen, 0, 7);
        this.add(this.tfcatgen, 1, 7);*/
        this.Cat = new Label("Catégorie :");
        this.tfcat = new TextField();
        this.add(this.Cat, 0, 7);
        this.add(this.cbcategorie, 1, 7);

        this.image = new Button("Associer image");
       // this.add(this.image, 0, 8);

//          image.setOnAction((t) -> {
//               
//            try {
//                this.images=GestionBDinterface.insererImage(this.main.getCon());
//                
//            } catch (SQLException ex) {
//                Logger.getLogger(VueDéposerObjet.class.getName()).log(Level.SEVERE, null, ex);
//            } catch (IOException ex) {
//                Logger.getLogger(VueDéposerObjet.class.getName()).log(Level.SEVERE, null, ex);
//            }
//              
//              
//          });
        this.depoObjet = new ToggleButton("Déposer mon objet");
        this.add(this.depoObjet, 4, 10);

        depoObjet.setOnAction((t) -> {

            try {

                Scanner scannerannee = new Scanner(this.tfannee.getText());
                this.valueannee = scannerannee.nextInt();
                Scanner scannerprix = new Scanner(this.tfprix.getText());
                this.valueprix = scannerprix.nextInt();
                Scanner scannermois = new Scanner(this.tfmois.getText());
                this.valuemois = scannermois.nextInt();
                Scanner scannerdate = new Scanner(this.tfdate.getText());
                this.valuedate = scannerdate.nextInt();

                this.fin = this.valueannee + "-" + this.valuemois + "-" + this.valuedate + " " + this.Heure + ":" + this.Minute + ":" + this.Sec;
                System.out.println(this.cbcategorie.getItems().toString());
                this.images = GestionBDinterface.insererImage(this.main.getCon());
                GestionBDinterface.demandeObjet(this.main.getCon(), this.tftitre.getText(), this.tfdescription.getText(),
                        valueprix, valueannee, valuemois, valuedate, this.fin,
                        "Puit", this.cbcategorie.getValue().toString(), this.main.getCurUserMail(), this.images);

//                 GestionBDinterface.demandeObjet(this.main.getCon(), this.tftitre.getText(),this.tfdescription.getText(), 
//                 valueprix,valueannee, valuemois,valuedate, this.fin,
//                 "Accessoires",this.tfcat.getText(),this.main.getCurUserMail() );
                Alert confirmer = new Alert(Alert.AlertType.CONFIRMATION);

                //confirmer.setTitle("Attention");
                confirmer.setHeaderText("Votre objet est mis aux enchères ");
                confirmer.setHeaderText("Votre objet est maintenant mis aux enchères. "
                        + "    Voulez vous mettre un nouvel objet aux enchères?");
                ButtonType oui = new ButtonType("Oui");
                ButtonType non = new ButtonType("Non");
                confirmer.getButtonTypes().clear();
                confirmer.getButtonTypes().addAll(oui, non);
                Optional<ButtonType> select = confirmer.showAndWait();
                if (select.get() == non) {
                    this.main.setCenter(new VueGlobale(this.main));

                } else if (select.get() == oui) {
                    tftitre.clear();
                    tfdescription.clear();
                    tfannee.clear();
                    tfdate.clear();
                    tfcat.clear();

                    tfmois.clear();

                    tfprix.clear();

                }

            } catch (Exception ex) {
                Logger.getLogger(VueDéposerObjet.class.getName()).log(Level.SEVERE, null, ex);
            }

            System.out.println("aaaaa");
            //GestionBD.demandeUtilisateur(this.main.getCon(), this.tfnom.getText(), this.tfprenom.getText(),this.tfmail.getText(), 
            //this.pfpass.getText(), this.tfcodepostal.getText());  

            //createObjet(this.main.getCon(), this.tftitre.getText()this.tfdescription.getText(),
            //} catch (Exception ex) {
            //Logger.getLogger(VueDéposerObjet.class.getName()).log(Level.SEVERE, null, ex);
            //  throw new Error (ex);
            //}
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
