
        /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */
package com.mycompany.projetinfo;

import static com.mycompany.projetinfo.GestionBD.BilanUser;
import static com.mycompany.projetinfo.GestionBD.TrouveObjetCat;
import static com.mycompany.projetinfo.GestionBD.TrouveObjetMot;
import static com.mycompany.projetinfo.GestionBD.TrouveUtilisateurNom;
import static com.mycompany.projetinfo.GestionBD.TrouveidCategorie;
import static com.mycompany.projetinfo.GestionBD.afficheUsers;
import static com.mycompany.projetinfo.GestionBD.creeSchema;
import static com.mycompany.projetinfo.GestionBD.defautConnect;
import static com.mycompany.projetinfo.GestionBD.deleteSchema;
import static com.mycompany.projetinfo.GestionBD.demandeUpdateFin;


import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author ynaho01
 */
public class MainApp {

    public static void main(String[] args) {

        try ( Connection con = defautConnect()) {
            System.out.println("connection réussie");
            MenuTexte(con);
        } catch (Exception ex) {
            throw new Error(ex);
        }
    }

    public static void MenuTexte(Connection con) throws SQLException, GestionBD.UtilisateurNexistePasException, GestionBD.CategorieNexistePasException, GestionBD.CategorieExisteDejaException, GestionBD.ObjetNexistePasException, GestionBD.MontantTropPetitException, GestionBD.DelaiDEnchereDepasseException {

        int m = -1;

        char u = 0;

        while (u != 'o') {

            System.out.println("Bienvenue dans le menu de votre base de données!"); //différents menus en fonction  de la table à laquellle tu veux accéder
            System.out.println("Voici les instructions:");
            System.out.println("Entrez (a) si votre requete concerne un ensemble de tables");
            System.out.println("Entrez (b) si votre requete concerne la table utilisateur");
            System.out.println("Entrez (c) si votre requete concerne la table objet");
            System.out.println("Entrez (d) si votre requete concerne la table categorie");
            System.out.println("Entrez (e) si votre requete concerne la table enchere");
            System.out.println("Entrez (o) si vous avez rien à foutre ici");
            u = Lire.c();
            System.out.println("vous avez demandé \""+u+"\"");

            try {

                if (u == 'a') {
                    m=-1;
                    while (m != 0) {
                        System.out.println("Bienvenue dans le menu ensemble de tables!");
                        System.out.println("Voici vos options");
                        System.out.println("(1) Creer un schema");
                        System.out.println("(2) Supprimer un schema existant");

                        System.out.println("(0) Quitter le menu");
                        System.out.println("Entrez votre choix");
                        m = Lire.i();

                        if (m == 1) {
                            creeSchema(con);

                        }

                        if (m == 2) {
                            deleteSchema(con);

                        }
                        if (m == 0) {
                            System.out.println("eh bien aurevoir");
                       
                        }

                    }

                }

                if (u == 'b') {
                    m=-1;

                    while (m != 0) {
                        System.out.println("Bienvenue dans le menu utilisateur !");
                        System.out.println("Voici vos options");
                        //System.out.println("(1) Se connecter");
                        System.out.println("(1) Ajouter un utilisateur");
                        System.out.println("(2) Retrouver un utilisateur en entrant son nom");
                        System.out.println("(3) Afficher la liste de tous les utilisateurs");
                        System.out.println("(4) Afficher un bilan pour un utilisateur donné"); //pas fait correctement
                        System.out.println("(0) Quitter le menu");
                        System.out.println("Entrez votre choix");
                        m = Lire.i();

                        if (m == 1) {

                            GestionBD.demandeUtilisateur(con);
                        }

                        if (m == 2) {
                          System.out.println("entrez le nom de l'utilisateur recherché");
                          String nomuser = Lire.S();
                          TrouveUtilisateurNom(con, nomuser);
                        }

                        if (m == 3) {

                            afficheUsers(con);
                        }
                        
                        if (m == 4) {

                            BilanUser(con);
                        }

                        if (m == 0) {
                            System.out.println("ok bye");

                        }

                    }
                }

                if (u == 'c') {
                    m=-1;

                    while (m != 0) {
                        System.out.println("Bienvenue dans le menu objet!");
                        System.out.println("Voici vos options");
                        System.out.println("(1) Ajouter un nouvel objet");
                        System.out.println("(2) Chercher un objet en entrant une catégorie");
                        System.out.println("(3) Chercher un objet en entrant une brève description (un mot)"); //pas fait
                        System.out.println("(4) Modifier la date de fin d'enchere d'un objet");
                        System.out.println("(0) Quitter le menu");
                        System.out.println("Entrez votre choix");
                        m = Lire.i();

                        if (m == 1) {

                            GestionBD.demandeObjet(con);
                        }

                        if (m == 2) {
                          System.out.println("Entrez la catégorie d'objet que vous recherchez");
                          String nom = Lire.S();
                            TrouveObjetCat(con, nom);
                        }
                        
                         if (m == 3) {
                            System.out.println("Entrez un mot clé pour chercher des objets correspondants");
                            String mot = Lire.S();
                            TrouveObjetMot(con, mot);
                        }
                         
                         if (m == 4) {

                            demandeUpdateFin(con);
                        }

                        if (m == 0) {
                            System.out.println("ok bye");

                        }

                    }
                }

                if (u == 'd') {
                    m=-1;

                    while (m != 0) {
                        System.out.println("Bienvenue dans le menu categorie!");
                        System.out.println("Voici vos options");
                        System.out.println("(1) Ajouter une nouvelle categorie");
                        System.out.println("(2) Retrouver l'identifiant d'une catégorie");
                        System.out.println("(0) Quitter le menu");
                        System.out.println("Entrez votre choix");
                        m = Lire.i();

                        if (m == 1) {

                            GestionBD.demandeCategorie(con);
                        }

                        if (m == 2) {

                            TrouveidCategorie(con);
                        }

                        if (m == 0) {
                            System.out.println("ok bye");

                        }

                    }
                }

                if (u == 'e') {
                    m=-1;

                    while (m != 0) {
                        System.out.println("Bienvenue dans le menu enchere!");
                        System.out.println("Voici vos options");
                        System.out.println("(1) Ajouter une nouvelle enchere sur un objet donné"); //pas réussi
                        System.out.println("(2) Afficher l'ensemble des encheres faites sur un objet donné"); //pas encore fait
                        System.out.println("(3) Afficher l'ensemble des encheres"); //pas encore fait
                        System.out.println("(0) Quitter le menu");
                        System.out.println("Entrez votre choix");
                        m = Lire.i();

                        if (m == 1) {

                            GestionBD.demandeEnchere(con); //demandeEnchere
                        }

                        if (m == 2) {

                            //TrouveUtilisateurNom(con);//trouvecategorie
                        }

                        if (m == 0) {
                            System.out.println("ok bye");

                        }

                    }
                }

                if (u == 'o') {

                    System.out.println("eh bien dégagez alors!");
                }

//                if (m == 0) {
//
//                    System.out.println("Bienvenue dans le menu de votre base de données!");
//                    System.out.println("Voici les instructions:");
//                    System.out.println("Entrez (a) si votre requete concerne un ensemble de tables");
//                    System.out.println("Entrez (b) si votre requete concerne la table utilisateur");
//                    System.out.println("Entrez (c) si votre requete concerne la table objet");
//                    System.out.println("Entrez (d) si votre requete concerne la table categorie");
//                    System.out.println("Entrez (e) si votre requete concerne la table enchere");
//                    System.out.println("Entrez (o) si vous avez rien à foutre ici");
//                }
            } catch (SQLException ex) {
                throw new Error(ex);
            }

        }

    }
}
