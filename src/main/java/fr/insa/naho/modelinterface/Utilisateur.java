/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.naho.modelinterface;



/**
 *
 * @author nahoy
 */
public class Utilisateur {
    private int id;
    private String nom;
    private String prenom;
    private String email;
    private String motdepasse;
    private String codepostal;
    
    public Utilisateur(int id, String nom, String prenom, String email, String motdepasse, String codepostal ) {
        this.id=id;
        this.nom=nom;
        this.prenom=prenom;
        this.email= email;
        this.motdepasse=motdepasse;
        this.codepostal=codepostal;
    }
    
     @Override
    public String toString(){
       return "Utilisateur d'identifiant: "+" "+this.id +" "+ "nom:"+ " " +this.nom+" "+
               "prénom:"+ " " +this.prenom+ " "+ "email:"+" "+this.email+" "+
               "mot de passe"+ " "+this.motdepasse+" "+"code postal:"+ " "+ this.codepostal; 
    
}
}
