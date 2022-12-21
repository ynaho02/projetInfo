/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelinterface;

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
    
}
