/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.mariannie.infom3;

import fr.insa.naho.modelinterface.GestionBDinterface;
import static fr.insa.naho.modelinterface.GestionBDinterface.getMax;
import static fr.insa.naho.modelinterface.GestionBDinterface.retrouveNomCatFromObj;
import static fr.insa.naho.modelinterface.GestionBDinterface.retrouveNomCatGen;
import static fr.insa.naho.modelinterface.GestionBDinterface.retrouveNomUser;
import fr.insa.naho.modelinterface.Objet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author alexa
 */
public class VueSauve {
    public static ArrayList<Objet> trouveObjetCategorie(Connection con, int id) throws Exception {
       /* ArrayList<Objet> objets = new ArrayList<>();
        con.setAutoCommit(false);
        try ( PreparedStatement searchobjet = con.prepareStatement(
                """
         select * from objet   where categorie = ?        
        """
        )) {

            searchobjet.setInt(1, id); //on indique ici que le premier point ? référence le nom
            ResultSet rs = searchobjet.executeQuery();

            if (rs.next()) {
                while (rs.next()) {

                    System.out.println("voici les infos pour l'objet d'identifiant :" + " " + rs.getInt("id"));
                    System.out.println("titre: " + " " + rs.getString("titre"));
                    System.out.println("description: " + " " + rs.getString("description"));
                    System.out.println("date de mise en enchere: " + " " + rs.getTimestamp("debut"));
                    System.out.println("date de fin de la mise en enchere: " + " " + rs.getTimestamp("fin"));
                    System.out.println("le prix de base était de: " + " " + rs.getInt("prixbase") + "euros");

                    Objet objet = new Objet(rs.getInt("id"), rs.getString("titre"), rs.getString("description"), rs.getTimestamp("debut"),
                            rs.getTimestamp("fin"), rs.getInt("prixbase"), rs.getInt("proposepar"), rs.getInt("categoriegenerale"),
                            rs.getInt("categorie"));

                    objets.add(objet);

                    String user = retrouveNomUser(con, rs.getInt("proposepar"));
                    System.out.println("Objet proposé par:" + " " + user);

                    String catgen = retrouveNomCatGen(con, rs.getInt("categoriegenerale"));
                    System.out.println("Catégorie générale:" + " " + catgen);

                    String cat = retrouveNomCatFromObj(con, rs.getInt("categorie"));
                    System.out.println("Sous-catégorie:" + " " + cat);

                    int maxi = getMax(con, objet);
                    System.out.println("Le montant de l'enchère maximale est de :" + " " + maxi + " " + "euros");
                }

            }

        } catch (Exception ex) {
            System.out.println("problème: " + ex.getMessage());

        }
        if (objets.size() == 0) {
            System.out.println("Pas d'objets correspondants.");
            return null;
        } else {
            return objets;
        }

    }

     /* dans vueEntete cbcategorie.setOnAction((t) -> {
           System.out.println(this.cbcategorie.getValue().toString());
            try {
                
                System.out.println(this.cbcategorie.getValue().toString());
                 ArrayList<Objet> objets;
                 int idCat = recupereIdCategorie(this.cbcategorie.getValue().toString());
                objets = GestionBDinterface.trouveObjetCategorie(this.main.getCon(),idCat);
                System.out.println("objet cat taille:"+objets.size());
                this.main.setCenter(new VueAnnonces(main, objets));
            } catch (Exception ex) {
                Logger.getLogger(VueEntete.class.getName()).log(Level.SEVERE, null, ex);
            }
       });*/return null;
       /* ArrayList<Objet> objets = new ArrayList<>();
        con.setAutoCommit(false);
        try ( PreparedStatement searchobjet = con.prepareStatement(
                """
         select * from objet   where categorie = ?        
        """
        )) {

            searchobjet.setInt(1, id); //on indique ici que le premier point ? référence le nom
            ResultSet rs = searchobjet.executeQuery();

            if (rs.next()) {
                while (rs.next()) {

                    System.out.println("voici les infos pour l'objet d'identifiant :" + " " + rs.getInt("id"));
                    System.out.println("titre: " + " " + rs.getString("titre"));
                    System.out.println("description: " + " " + rs.getString("description"));
                    System.out.println("date de mise en enchere: " + " " + rs.getTimestamp("debut"));
                    System.out.println("date de fin de la mise en enchere: " + " " + rs.getTimestamp("fin"));
                    System.out.println("le prix de base était de: " + " " + rs.getInt("prixbase") + "euros");

                    Objet objet = new Objet(rs.getInt("id"), rs.getString("titre"), rs.getString("description"), rs.getTimestamp("debut"),
                            rs.getTimestamp("fin"), rs.getInt("prixbase"), rs.getInt("proposepar"), rs.getInt("categoriegenerale"),
                            rs.getInt("categorie"));

                    objets.add(objet);

                    String user = retrouveNomUser(con, rs.getInt("proposepar"));
                    System.out.println("Objet proposé par:" + " " + user);

                    String catgen = retrouveNomCatGen(con, rs.getInt("categoriegenerale"));
                    System.out.println("Catégorie générale:" + " " + catgen);

                    String cat = retrouveNomCatFromObj(con, rs.getInt("categorie"));
                    System.out.println("Sous-catégorie:" + " " + cat);

                    int maxi = getMax(con, objet);
                    System.out.println("Le montant de l'enchère maximale est de :" + " " + maxi + " " + "euros");
                }

            }

        } catch (Exception ex) {
            System.out.println("problème: " + ex.getMessage());

        }
        if (objets.size() == 0) {
            System.out.println("Pas d'objets correspondants.");
            return null;
        } else {
            return objets;
        }

    }

     /* dans vueEntete cbcategorie.setOnAction((t) -> {
           System.out.println(this.cbcategorie.getValue().toString());
            try {
                
                System.out.println(this.cbcategorie.getValue().toString());
                 ArrayList<Objet> objets;
                 int idCat = recupereIdCategorie(this.cbcategorie.getValue().toString());
                objets = GestionBDinterface.trouveObjetCategorie(this.main.getCon(),idCat);
                System.out.println("objet cat taille:"+objets.size());
                this.main.setCenter(new VueAnnonces(main, objets));
            } catch (Exception ex) {
                Logger.getLogger(VueEntete.class.getName()).log(Level.SEVERE, null, ex);
            }
       });*/
    
}
}
