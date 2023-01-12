/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.mariannie.infom3;

import fr.insa.naho.modelinterface.Objet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

/**
 *
 * @author alexa
 */
public class Annonce extends GridPane {

    private MainPane main;

    public Annonce(Objet obj, MainPane main) throws SQLException {
        this.main = main;

        int idCategorie = (obj.getCategorie());
        int idUtilisateur= (obj.getProposepar());
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

}
