/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.naho.modelinterface;

import fr.insa.naho.model.*;

/**
 *
 * @author nahoy
 */
public class Utilisateur {
    
    private String nom;
    private String prenom;
    private String email;
    private String motdepasse;
    private String codepostal;
    
    public Utilisateur(String nom, String prenom, String email, String motdepasse, String codepostal ) {
        
        this.nom=nom;
        this.prenom=prenom;
        this.email= email;
        this.motdepasse=motdepasse;
        this.codepostal=codepostal;
    }
    
}
