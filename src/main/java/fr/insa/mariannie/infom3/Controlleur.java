/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.mariannie.infom3;

/**
 *
 * @author alexa
 */
public class Controlleur {
    
    private int state;
    private MainPane vue;

    Controlleur(MainPane aThis) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
     

       
        
    
    public void changeState(int state){ //fonction pour changer l'etat en fonction des events
        System.out.println("changedsta"+state);
        this.state=state;
        switch(state){
            case 1:
        }
    }
    
    
    
}

