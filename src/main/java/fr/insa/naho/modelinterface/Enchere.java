package fr.insa.naho.modelinterface;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


import java.sql.Timestamp;

/**
 *
 * @author nahoy
 */
public class Enchere {
    
    private int id;
    private Timestamp quand;
    private int montant;
    private int sur;
    private int de;
    
    public Enchere(int id, Timestamp quand, int montant,int sur,int de){
        
        this.id=id;
        this.quand = quand;
        this.montant=montant;
        this.sur=sur;
        this.de= de;
    }
    
    @Override
    public String toString(){
       return "Enchère numéro: "+" "+this.id +" "+ "faite le:"+ " " +this.quand+" "+
               "d'un montant de:"+ " " +this.montant+ " "+ "sur l'objet d'identifiant:"+" "+this.sur+" "+
               "par l'utilisaeur d'identifiant"+ " "+this.de; 
    
}
    
}
