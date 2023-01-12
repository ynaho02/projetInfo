/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.mariannie.infom3;

import fr.insa.naho.model.GestionBD;
import fr.insa.naho.modelinterface.GestionBDinterface;
import fr.insa.naho.modelinterface.Objet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.TreeView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

/**
 *
 * @author alexa
 */
public class VueEntete extends HBox {

    private MainPane main;

    private ToggleButton InfosPerso;
    private Label label;
    private Categories cat;
    private ToggleButton search;
    private TextField tfsearch;
    private ToggleButton depoAnnonce;
    private ToggleButton disconnect;
    private ToggleButton afficheAllAnnonce;

    private MenuButton vente;
    private MenuItem categorie;
    private MenuItem categorieGenerale;
    private MenuItem objet;
    private SplitMenuButton a;

    private MenuButton find;
    private MenuItem fcategorie;
    private MenuItem fcategorieGenerale;
    private MenuItem fkeyword;
    private MenuItem fcodePostal;
    
    private ComboBox cbcategorie;

    public VueEntete(MainPane main) {

        this.main = main;
        this.depoAnnonce = new ToggleButton("Mise en vente");

        //hb1 = new HBox (this.next, label, region, this.Ajouter  );
        //hb1.setAlignment(Pos.CENTER_LEFT);
        this.tfsearch = new TextField();

        this.search = new ToggleButton("recherche");
        
         this.afficheAllAnnonce = new ToggleButton("Toutes les Annonces");
       
        
        this.fcategorie=new MenuItem("Catégorie");
        this.fcategorieGenerale=new MenuItem("Catégorie Générale");
        this.fkeyword=new MenuItem( "Mots Clés");
        this.fcodePostal=new MenuItem("Code Postal");
        
         this.find = new SplitMenuButton(fcategorie, fcategorieGenerale, fcodePostal,fkeyword);
        find.setText("Rechercher par");
        
       
        
        
         

        search.setOnAction((t) -> {
            try {
                ArrayList<Objet> objets;
                objets = GestionBDinterface.trouveObjetTitre(this.main.getCon(), this.tfsearch.getText());
                
                this.main.setCenter(new VueAnnonces(this.main, objets));

            } catch (SQLException ex) {
                Utils.showErrorInAlert("PB", ex.getLocalizedMessage());
            }
        });
        
        fcategorie.setOnAction((t) -> {
            try {
                ArrayList<Objet> objets;
                objets = GestionBDinterface.trouveObjetCat(this.main.getCon(), this.tfsearch.getText());
                
                this.main.setCenter(new VueAnnonces(this.main, objets));

            } catch (SQLException ex) {
                Utils.showErrorInAlert("PB", ex.getLocalizedMessage());
            }
        });
        
        fcategorieGenerale.setOnAction((t) -> {
            try {
                ArrayList<Objet> objets;
                objets = GestionBDinterface.trouveObjetCatGen(this.main.getCon(), this.tfsearch.getText());
                
                this.main.setCenter(new VueAnnonces(this.main, objets));

            } catch (SQLException ex) {
                Utils.showErrorInAlert("PB", ex.getLocalizedMessage());
            }
        });
        
        fkeyword.setOnAction((t) -> {
            try {
                ArrayList<Objet> objets;
                objets = GestionBDinterface.trouveObjetMot(this.main.getCon(), this.tfsearch.getText());
                
                this.main.setCenter(new VueAnnonces(this.main, objets));

            } catch (SQLException ex) {
                Utils.showErrorInAlert("PB", ex.getLocalizedMessage());
            }
        });
        
         fcodePostal.setOnAction((t) -> {
            try {
                ArrayList<Objet> objets;
                objets = GestionBDinterface.trouveObjetCodePostal(this.main.getCon(), this.tfsearch.getText());
                
                this.main.setCenter(new VueAnnonces(this.main, objets));

            } catch (SQLException ex) {
                Utils.showErrorInAlert("PB", ex.getLocalizedMessage());
            }
        });
        
        
        
        afficheAllAnnonce.setOnAction((t) -> {
            ArrayList<Objet> objets =null ;
            try {
                objets = GestionBDinterface.afficheAllObjets(this.main.getCon());
            } catch (SQLException ex) {
                Logger.getLogger(VueEntete.class.getName()).log(Level.SEVERE, null, ex);
            }
                
            try {
                this.main.setCenter(new VueAnnonces(this.main, objets));
            } catch (SQLException ex) {
                Logger.getLogger(VueEntete.class.getName()).log(Level.SEVERE, null, ex);
            }

           
        });
       String c1="Meubles"; 
       String c2="Véhicules";
       String c3="Vêtements";
       String c4="Services";
               

       this.cbcategorie=new ComboBox();
       
       ObservableList<String>listCategorie= FXCollections.observableArrayList(c1,c2,c3,c4);
       
       this.cbcategorie.setItems(listCategorie);

       cbcategorie.setOnAction((t) -> {
        
           this.cbcategorie.getValue();
          // GestionBDinterface.trouveObjetCat(this.main.getCon(), this.tfsearch.getText());
       });

        Region region = new Region();
        Region region1 = new Region();

        HBox hbox1 = new HBox(this.tfsearch, this.search, region1, this.find, region, this.afficheAllAnnonce);
        hbox1.setSpacing(10);
        //VBox vbox = new VBox(this.disconnect, this.InfosPerso);

        /*depoAnnonce.setOnAction((t) -> {
           
           this.main.setCenter(new VueMiseEnVentes(this.main));
               
        });*/
        this.categorieGenerale = new MenuItem("Créer Catégorie Générale");
        this.categorie = new MenuItem("Créer Catégorie");
        this.objet = new MenuItem("Déposer Objet");

        
         objet.setOnAction((t) -> {
             
              this.main.setCenter(new VueDéposerObjet(this.main));
           
        });
        
        this.a = new SplitMenuButton(categorie, categorieGenerale, objet);
        a.setText("Mise en Vente");

        HBox hbox = new HBox(this.a, region, hbox1);
        region.setPrefSize(100, 100);

        this.getChildren().addAll(hbox);
        
        VBox vbox = new VBox(hbox,cbcategorie);
        this.getChildren().addAll(vbox);

        a.setOnAction((t) -> {
            this.main.setCenter(new VueMiseEnVentes(this.main));

        });

        
    }
}
