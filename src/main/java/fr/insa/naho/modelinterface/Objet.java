/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.naho.modelinterface;

import java.sql.Timestamp;

/**
 *
 * @author nahoy
 */
public class Objet {
    private int id;
    private String titre;
    private String description;
    private Timestamp debut;
    private Timestamp fin;
    private int prixbase;
    private int proposepar;
    private int categoriegenerale;
    private int categorie;
    
    public Objet(int id,String titre, String description, Timestamp debut, Timestamp fin, int prixbase, int proposepar, int categoriegenerale, int categorie){
        
       this.id=id;
       this.titre=titre;
       this.description= description;
       this.debut=debut;
       this.fin=fin;
       this.prixbase=prixbase;
       this.proposepar=proposepar;
       this.categoriegenerale=categoriegenerale;
       this.categorie=categorie;
       
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @return the titre
     */
    public String getTitre() {
        return titre;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @return the debut
     */
    public Timestamp getDebut() {
        return debut;
    }

    /**
     * @return the fin
     */
    public Timestamp getFin() {
        return fin;
    }

    /**
     * @return the prixbase
     */
    public int getPrixbase() {
        return prixbase;
    }

    /**
     * @return the proposepar
     */
    public int getProposepar() {
        return proposepar;
    }

    /**
     * @return the categoriegenerale
     */
    public int getCategoriegenerale() {
        return categoriegenerale;
    }

    /**
     * @return the categorie
     */
    public int getCategorie() {
        return categorie;
    }
    
}
