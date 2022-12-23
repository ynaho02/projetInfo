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

    public int getId() {
        return id;
    }

    public String getTitre() {
        return titre;
    }

    public String getDescription() {
        return description;
    }

    public Timestamp getDebut() {
        return debut;
    }

    public Timestamp getFin() {
        return fin;
    }

    public int getPrixbase() {
        return prixbase;
    }

    
    public int getProposepar() {
        return proposepar;
    }

    
    public int getCategoriegenerale() {
        return categoriegenerale;
    }

    
    public int getCategorie() {
        return categorie;
    }
    
    @Override
    public String toString(){
       return "Objet numéro: "+" "+this.id +" "+ "titre:"+ " " +this.titre+" "+
               "description:"+ " " +this.description+ " "+ "debut d'enchere:"+" "+this.debut+" "+
               "fin d'enchere"+ " "+this.fin +" "+ "prix de base:" + " "+ this.prixbase+" "+
               "proposé par l'utilisateur d'identifiant:"+ " " +this.proposepar+ " "+
               "appartenant à la catégorie générale numéro:"+ " "+this.categoriegenerale+ " "+
               "et à la catégorie:"+ " "+this.categorie;
    
}
}
