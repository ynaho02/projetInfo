/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.naho.modelinterface;

/**
 *
 * @author nahoy
 */
public class Categorie {
    
    private int id;
    private int generale;
    private String nom;
    
    public Categorie(int id, int generale,String nom){
        
        this.id=id;
        this.generale=generale;
        this.nom=nom;
    }
     @Override
    public String toString(){
       return "Catégorie numéro: "+" "+this.id +" "+ "nom:"+ " " +this.nom+" "+
               "identifiant de la caégorie générale:"+ " " +this.generale;
    
}
    
    
}
