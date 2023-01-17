/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.mariannie.infom3;


import fr.insa.naho.modelinterface.GestionBDinterface;
import fr.insa.naho.modelinterface.Objet;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.DepthTest;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.TreeView;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
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
    
    private Button b;

    private MenuButton find;
    private MenuItem fcategorie;
    private MenuItem fcategorieGenerale;
    private MenuItem fkeyword;
    private MenuItem fcodePostal;
    
    private String test;
    
    private ComboBox cbcategorie;
    
    private Button encherir;

    public VueEntete(MainPane main) throws SQLException, GestionBDinterface.CategorieGenExisteDejaException, GestionBDinterface.CategorieExisteDejaException {
        
        this.setStyle("-fx-color:white;-fx-font-size:14px;-fx-font-weight:bold");
        
        Image fondecran =getImage("ressources/fond4.jpg", 25, 25);
        this.setBackground(new Background(new BackgroundImage(fondecran, 
                BackgroundRepeat.SPACE, 
                BackgroundRepeat.SPACE,
                BackgroundPosition.CENTER,
                BackgroundSize.DEFAULT)));
        
        //this.setStyle("-fx-background-color:#E9E3D9");

        this.main = main;
        this.depoAnnonce = new ToggleButton("Mise en vente");
        
        this.test=new String();

        //hb1 = new HBox (this.next, label, region, this.Ajouter  );
        //hb1.setAlignment(Pos.CENTER_LEFT);
        this.tfsearch = new TextField();

        this.search = new ToggleButton("recherche");
        
         this.afficheAllAnnonce = new ToggleButton("Toutes les Annonces");
       
        
         //this.fcategorie=new MenuItem("Catégorie");
        //.fcategorieGenerale=new MenuItem("Catégorie Générale");
        this.fkeyword=new MenuItem( "Description");
        this.fcodePostal=new MenuItem("Code Postal");
        
         this.find = new SplitMenuButton(fcodePostal,fkeyword);
        find.setText("Rechercher par");
        
       
        
        
         

        search.setOnAction((t) -> {
            try {
                ArrayList<Objet> objets;
                objets = GestionBDinterface.trouveObjetTitre(this.main.getCon(), this.tfsearch.getText());
                
                this.main.setCenter(new VueAnnonces(this.main, objets));

            } catch (Exception ex) {
                Utils.showErrorInAlert("PB", ex.getLocalizedMessage());
            }
        });
        
    
        
        fkeyword.setOnAction((t) -> {
            
            this.main.setCenter(new VueDescription (this.main));
        });
        
        
         fcodePostal.setOnAction((t) -> {
             
             this.main.setCenter(new VueCodePostal(this.main));   
               /* ArrayList<Objet> objets;
                objets = GestionBDinterface.trouveObjetCodePostal(this.main.getCon(), this.tfsearch.getText());
                
                this.main.setCenter(new VueAnnonces(this.main, objets));*/

           
        });
        
        
        
        afficheAllAnnonce.setOnAction((t) -> {
            ArrayList<Objet> objets =null ;
            try {
                objets = GestionBDinterface.afficheAllObjets(this.main.getCon());
            } catch (Exception ex) {
                Logger.getLogger(VueEntete.class.getName()).log(Level.SEVERE, null, ex);
            }
                
            try {
                this.main.setCenter(new VueAnnonces(this.main, objets));
            } catch (SQLException ex) {
                Logger.getLogger(VueEntete.class.getName()).log(Level.SEVERE, null, ex);
            }

           
        });
       String c0="Catégories";
       
       String c16="Arts";
       String c4="Bijoux";
       String c1="Immobilier";
       String c14="Electroménager";
       String c2="Véhicules";
       String c3="Vêtements";
       
       String c5="Musique";
       String c6="Livres";
       String c7="Films";
       String c8="Jeux & Jouets";
       String c9="Vins & Gastronomie";
       String c10="Jeux Videos";
       String c11="Téléphonies";
       String c12="Image & Son";
       String c13="Informatique";
      
       String c15="Décoration";
       
       String c17="Matériel Agricole";
       String c18="BTP";
//      int cg1= GestionBDinterface.createCategorieGenerale(this.main.getCon(), "Puit");
////       
//       GestionBDinterface.createCategorie(this.main.getCon(), c18,cg1);
//       GestionBDinterface.createCategorie(this.main.getCon(), c17, cg1);
//       GestionBDinterface.createCategorie(this.main.getCon(), c16, cg1);        
//       GestionBDinterface.createCategorie(this.main.getCon(), c15, cg1);        
//GestionBDinterface.createCategorie(this.main.getCon(), c14, cg1);
//       GestionBDinterface.createCategorie(this.main.getCon(), c13, cg1);
//       GestionBDinterface.createCategorie(this.main.getCon(), c12, cg1);
//       GestionBDinterface.createCategorie(this.main.getCon(), c11, cg1);
//       GestionBDinterface.createCategorie(this.main.getCon(), c10, cg1);
//       GestionBDinterface.createCategorie(this.main.getCon(), c9, cg1);
//       GestionBDinterface.createCategorie(this.main.getCon(), c8, cg1);
//       GestionBDinterface.createCategorie(this.main.getCon(), c7, cg1);
//       GestionBDinterface.createCategorie(this.main.getCon(), c6, cg1);
//       GestionBDinterface.createCategorie(this.main.getCon(), c5, cg1);
//       GestionBDinterface.createCategorie(this.main.getCon(), c4, cg1);
//       GestionBDinterface.createCategorie(this.main.getCon(), c3, cg1);
//       GestionBDinterface.createCategorie(this.main.getCon(), c1, cg1);
//       GestionBDinterface.createCategorie(this.main.getCon(), c2, cg1);
//       
       
       this.cbcategorie=new ComboBox();
       
       ObservableList<String>listCategorie= FXCollections.observableArrayList(c16,c4,c14,c7,c8,c9,c10,c11,c12,c13,c15,c17,c18,c1,c2,c3,c5,c6);
       
       this.cbcategorie.setItems(listCategorie);
       this.cbcategorie.setValue(c0);

       cbcategorie.setOnAction((t) -> {
           System.out.println(this.cbcategorie.getValue().toString());
            try {
                
                System.out.println(this.cbcategorie.getValue().toString());
                 ArrayList<Objet> objets;
                
                 int idCat = recupereIdCategorie(this.cbcategorie.getValue().toString());
                  String nomcat = GestionBDinterface.retrouveNomCatFromObj(this.main.getCon(), idCat);
                objets = GestionBDinterface.trouveObjetCat(this.main.getCon(),nomcat); 
                System.out.println("objet cat taille:"+objets.size());
                this.main.setCenter(new VueAnnonces(main, objets));
            } catch (Exception ex) {
                Logger.getLogger(VueEntete.class.getName()).log(Level.SEVERE, null, ex);
            }
       });
       
     
       

        Region region = new Region();
        Region region1 = new Region();
         Region region2 = new Region();

        HBox hbox1 = new HBox(this.tfsearch, this.search, region1,this.cbcategorie,region2, this.find, region, this.afficheAllAnnonce);
        hbox1.setSpacing(10);
        //VBox vbox = new VBox(this.disconnect, this.InfosPerso);

        /*depoAnnonce.setOnAction((t) -> {
           
           this.main.setCenter(new VueMiseEnVentes(this.main));
               
        });*/
        
        this.b=new Button("Mise en Vente");
        
        //this.categorieGenerale = new MenuItem("Créer Catégorie Générale");
       // this.categorie = new MenuItem("Créer Catégorie");
       // this.objet = new MenuItem("Déposer Objet");

        
         b.setOnAction((t) -> {
             
              this.main.setCenter(new VueDéposerObjet(this.main));
           
        });
        
        //this.a = new SplitMenuButton(categorie, categorieGenerale, objet);
        //a.setText("Mise en Vente");
        
        

        HBox hbox = new HBox(this.b, region, hbox1);
        region.setPrefSize(100, 100);

        this.getChildren().addAll(hbox);
        
       
        
    }
    public int recupereIdCategorie(String nom) throws SQLException {
        Connection con = this.main.getCon();
        try ( PreparedStatement st = con.prepareStatement("select * from categorie where nom = ?")) {
            st.setString(1, nom);
            ResultSet res = st.executeQuery();
            while (res.next()) {
                return res.getInt("id"); //id de la colone qui contient le nom de la categorie 
            }

        }
        return 0;
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
