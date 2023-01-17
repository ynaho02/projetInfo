package fr.insa.mariannie.infom3;

import static fr.insa.mariannie.infom3.GestionBdD.defautConnect;
import java.sql.Connection;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 * 
 * @author alexa
 */
public class Main {

    public static void main(String[] args) {
        try ( Connection con = defautConnect()) {
            System.out.println("connection reussie!");
             GestionBdD.deleteSchema(con);
            System.out.println("schema supprimé");
           GestionBdD.creeSchema(con);
            System.out.println("schéma créé");
           GestionBdD.createNvObjet(con);
            GestionBdD.afficheTousLesUtilisateur(con);
        } catch (Exception ex) {
            throw new Error(ex);
        }

    }

}
