/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.naho.modelinterface;

/**
 *
 * @author nahoy
 */
public class Categoriegenerale {
    
    private int id;
    private String nom;
    
    public Categoriegenerale(int id, String nom){
        
        this.id = id;
        this.nom=nom;
    }
    
     @Override
    public String toString(){
       return "Catégorie générale numéro: "+" "+this.id +"\n"+ "nom:"+ " " +this.nom+" ";
    
}
}
