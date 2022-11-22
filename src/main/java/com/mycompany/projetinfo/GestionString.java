/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projetinfo;


import java.util.Scanner;



/**
 *
 * @author ynaho01
 */
public class GestionString {
    
    public static void gestionstring(int u){
        
     Scanner console = new Scanner(System.in);
     
     System.out.println("entrez votre nom");
     String name = console.nextLine();
     
     System.out.println("entrez votre age");
     int age = console.nextInt();
     
     
    }
    public static void main(String[] args) {
         int m=-1;
        System.out.println("voulez-vous vous identifier?");
        System.out.println("1 pour oui");
        System.out.println("0 pour non");
        m=Lire.i();
        if (m==1)gestionstring(m);
        
        else System.out.println("ok bye");
    }
}
