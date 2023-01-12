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
    public int getId() {
        return id;
    }
    public String getNom() {
        return nom;
    }
    public String getPrenom() {
        return prenom;
    }
    
    public String getEmail() {
        return email;
    }
    
    public String getMdp() {
        return motdepasse;
    }
    
     public String getCodeP() {
        return codepostal;
    }
     
     
     @Override
    public String toString(){
       return "Utilisateur d'identifiant: "+" "+this.id +"\n"+ "nom:"+ " " +this.nom+"\n"+
               "prénom:"+ " " +this.prenom+ "\n"+ "email:"+" "+this.email+"\n"+
               "mot de passe"+ " "+this.motdepasse+"\n"+"code postal:"+ " "+ this.codepostal; 
    
}
}
