/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */
package fr.insa.naho.modelinterface;



import fr.insa.naho.model.GestionBD;
import static fr.insa.naho.modelinterface.GestionBDinterface.afficheAllObjets;
import static fr.insa.naho.modelinterface.GestionBDinterface.afficheEnchere;
import static fr.insa.naho.modelinterface.GestionBDinterface.afficheEncheresDeMesObjets;
import static fr.insa.naho.modelinterface.GestionBDinterface.afficheUsers;
import static fr.insa.naho.modelinterface.GestionBDinterface.creeSchema;
import static fr.insa.naho.modelinterface.GestionBDinterface.defautConnect;
import static fr.insa.naho.modelinterface.GestionBDinterface.deleteSchema;
import static fr.insa.naho.modelinterface.GestionBDinterface.demandeCategorie;
import static fr.insa.naho.modelinterface.GestionBDinterface.demandeUpdateFin;
import static fr.insa.naho.modelinterface.GestionBDinterface.login;
import static fr.insa.naho.modelinterface.GestionBDinterface.recreatebdd;
import static fr.insa.naho.modelinterface.GestionBDinterface.recreationbdd;
import static fr.insa.naho.modelinterface.GestionBDinterface.trouveObjetCat;
import static fr.insa.naho.modelinterface.GestionBDinterface.trouveObjetCatGen;
import static fr.insa.naho.modelinterface.GestionBDinterface.trouveObjetCodePostal;
import static fr.insa.naho.modelinterface.GestionBDinterface.trouveObjetMot;
import static fr.insa.naho.modelinterface.GestionBDinterface.trouveUtilisateurMail;
import static fr.insa.naho.modelinterface.GestionBDinterface.trouveidCategorie;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;


/**
 *
 * @author ynaho01
 */
public class MainAppinterface {

    public static void main(String[] args) {

        try ( Connection con = defautConnect())  { //defautconnect renvoie une connection. Une fois que cette méthode est appelé ton sgbd est actif et tu peux accéder a la database depuis java
            System.out.println("connection réussie"); //si la connection est établie ca te l'affiche, si tu n'as rien c'est que non
         MenuTexte(con); //Toutes mes méthodes prennent au minimun une connection en entrée. Cette connection c'est celle qui t'aura été fournie après le défaut connect. Ca veut dire que toutes les méthodes dont tu te serviras auront forcément un impact sur la bdd 
        } catch (Exception ex) {
            throw new Error(ex);
        }
    }

    public static void MenuTexte(Connection con) throws SQLException, GestionBD.UtilisateurNexistePasException, GestionBD.CategorieNexistePasException, GestionBD.CategorieExisteDejaException, GestionBD.ObjetNexistePasException, GestionBD.MontantTropPetitException, GestionBD.DelaiDEnchereDepasseException, Exception {

        int m = -1;

        char u = 0;
//        
//        System.out.println("Bienvenue sur le site de vente aux encheres!"); 
//            System.out.println("S'il vous plait veuillez vous connecter"); 
//             System.out.println("Entrez votre adresse mail:");
//                            String mail = Lire.S();
//                            System.out.println("Entrez votre  mot de passe:");
//                            String mdp = Lire.S();
//                            login(con, mail, mdp);b

        while (u != 'o') {

            System.out.println("Bienvenue dans le menu BdD!");
            System.out.println("Entrez (a) si votre requete concerne un ensemble de tables");
            System.out.println("Entrez (b) si votre requete concerne la table utilisateur");
            System.out.println("Entrez (c) si votre requete concerne la table objet");
            System.out.println("Entrez (d) si votre requete concerne la table categorie");
            System.out.println("Entrez (e) si votre requete concerne la table enchere");
            System.out.println("Entrez (o) si vous avez rien à foutre ici");
            u = Lire.c();
            System.out.println("vous avez demandé \"" + u + "\"");

            try {

                if (u == 'a') {
                    m = -1;
                    while (m != 0) {
                        System.out.println("Bienvenue dans le menu ensemble de tables!");
                        System.out.println("Voici vos options");
                        System.out.println("(1) Effacer puis recréer votre bdd ");
                        System.out.println("(0) Quitter le menu");
                        System.out.println("Entrez votre choix");
                        m = Lire.i();

                        if (m == 1) {
                            recreationbdd(con);

                        }

                        if (m == 0) {
                            System.out.println("eh bien aurevoir");

                        }

                    }

                }

                if (u == 'b') {
                    m = -1;

                    while (m != 0) {
                        System.out.println("Bienvenue dans le menu utilisateur !");
                        System.out.println("Voici vos options");
                        System.out.println("(1) Se connecter");
                        System.out.println("(2) Ajouter un utilisateur");
                        System.out.println("(3) Retrouver un utilisateur en entrant son mail");
                        System.out.println("(4) Afficher la liste de tous les utilisateurs");
                        System.out.println("(5) Afficher l'ensemble des objets que vous avez mis en enchere");
                        System.out.println("(6) Afficher l'ensemble des objets sur lesquels vous avez mis une enchère");
                        System.out.println("(7) Voir toutes les enchères mises sur un de vos objets");
                        System.out.println("(8) Modifier votre adresse mail");
                        System.out.println("(9) Modifier votre mot de passe");
                        System.out.println("(0) Quitter le menu");
                        System.out.println("Entrez votre choix");
                        m = Lire.i();

                        if (m == 1) {

                            System.out.println("Entrez votre adresse mail:");
                            String email = Lire.S();
                            System.out.println("Entrez votre mot de passe:");
                            String mdep = Lire.S();
                            login(con, email, mdep);
                        }

                        if (m == 2) {
                            System.out.println("--- creation nouvel utilisateur");
                            System.out.println("Entrez votre nom, votre prénom et votre mail");
                            String nom = Lire.S();
                            String prenom = Lire.S();
                            String admail = Lire.S();
                            System.out.println("Entrez un mot de passe:");
                            String pass = Lire.S();
                            System.out.println("Entrez le code postal correspondant à votre adresse d'habitation:");
                            String codepostal = Lire.S();

                            GestionBDinterface.demandeUtilisateur(con, nom, prenom, admail, pass, codepostal);

                            System.out.println("Utilisateur ajouté avec succès.");
                        }

                        if (m == 3) {
                            System.out.println("entrez le mail de l'utilisateur recherché");
                            String tonmail = Lire.S();

                            trouveUtilisateurMail(con, tonmail);

                        }

                        if (m == 4) {

                            afficheUsers(con);
                        }

                        if (m == 5) {
                            System.out.println("entrez votre adresse mail");
                            String emailuser = Lire.S();
                            GestionBDinterface.mesObjets(con, emailuser);
                        }

                       
                        if (m == 6) {
                            System.out.println("entrez votre adresse mail");
                            String emailuser = Lire.S();
                            GestionBDinterface.mesObjetsVoulus(con, emailuser);
                        }
                        if (m==7){
                            
                            System.out.println("Entrez votre adresse mail ");
                            String emailuser = Lire.S();
                            afficheEncheresDeMesObjets(con,emailuser);
                        }
                        if (m == 8) {
                            System.out.println("entrez votre ancienne adresse mail");
                            String oldemailuser = Lire.S();
                            System.out.println("entrez votre nouvelle adresse mail");
                            String newemailuser = Lire.S();
                            
                            GestionBDinterface.demandeUpdateEmail(con, oldemailuser, newemailuser);
                             System.out.println("Email modifié");
                        }
                        if (m == 9) {
                            System.out.println("entrez votre ancien mot de passe");
                            String oldmdp = Lire.S();
                            System.out.println("entrez votre nouveau mot de passe");
                            String newmdp = Lire.S();
                            
                            GestionBDinterface.demandeUpdateMdp(con, oldmdp, newmdp);
                            
                            System.out.println("Mot de passe modifié");
                        }

                        if (m == 0) {
                            System.out.println("ok bye");

                        }

                    }
                }

                if (u == 'c') {
                    m = -1;

                    while (m != 0) {
                        System.out.println("Bienvenue dans le menu objet!");
                        System.out.println("Voici vos options");
                        System.out.println("(1) Ajouter un nouvel objet");
                        System.out.println("(2) Chercher un objet en entrant une catégorie générale");
                        System.out.println("(3) Chercher un objet en entrant une sous-catégorie");
                        System.out.println("(4) Chercher un objet en entrant une brève description (un mot)");
                        System.out.println("(5) Chercher un objet en entrant un code postal");
                        System.out.println("(6) Modifier la date de fin d'enchere d'un objet");
                        System.out.println("(7) Afficher l'ensemble des objets déposés sur le site");
                        System.out.println("(0) Quitter le menu");
                        System.out.println("Entrez votre choix");
                        m = Lire.i();

                        if (m == 1) {
                            System.out.println("--- ajout nouvel objet");

                            System.out.println("Entrez le titre de l'objet");//, une petite description, le prix de base et la date souhaitée de fin d'enchere");
                            String titre = Lire.S();

                            System.out.println("Entrez une description de l'objet");
                            String description = Lire.S();

                            System.out.println("Entrez le prix de base de l'objet (en euros)");
                            int prixbase = Lire.i();

                            System.out.println("Maintenant vous allez entrer la date de fin d'enchere");

                            System.out.println("Entrez l'annee");
                            int annee = Lire.i();

                            System.out.println("Entrez le mois");
                            int mois = Lire.i();

                            System.out.println("entrez la date");
                            int date = Lire.i();

                            int heure = 00;
                            int minute = 00;
                            int seconde = 00;

                            String Fin = annee + "-" + mois + "-" + date + " " + heure + ":" + minute + ":" + seconde; //je crée un string de format exact à ce à quoi est sensé ressembler un timestamp

                            System.out.println("A present vous allez attribuer une catégorie à votre objet, entrez le nom de cette categorie ");
                            String nomcatgen = Lire.S();

                            System.out.println("Votre objet appartient à une sous-catégorie spécifique, veuillez l'entrez.");
                            String nomcat = Lire.S();

                            System.out.println("A present il faut vous associer à cet objet, entrez donc votre adresse mail.");
                            String emailuser = Lire.S();

                            GestionBDinterface.demandeObjet(con, titre, description, prixbase, annee, mois, date, Fin, nomcatgen, nomcat, emailuser);

                            System.out.println("Objet ajouté avec succès.");

                        }

                        if (m == 2) {
                            System.out.println("Entrez la catégorie d'objet que vous recherchez");
                            String nom = Lire.S();
                            trouveObjetCatGen(con, nom);
                        }

                        if (m == 3) {
                            System.out.println("Entrez la sous-catégorie d'objet que vous recherchez");
                            String nom = Lire.S();
                            trouveObjetCat(con, nom);
                        }

                        if (m == 4) {
                            System.out.println("Entrez un mot clé pour chercher des objets correspondants");
                            String mot = Lire.S();
                            trouveObjetMot(con, mot);
                        }

                        if (m == 5) {
                            
                            System.out.println("Entrez un code postal pour trouver des objets");
                            String codepostal = Lire.S();
                            trouveObjetCodePostal(con,codepostal);
//                            BufferedImage image;
//                            image = ImageIO.read(new File("toto.jpg"));
//                            
//                            ByteArrayOutputStream baos = new ByteArrayOutputStream();
//                            ImageIO.write(image, "png", baos);
//                            byte[] byteArr = baos.toByteArray();
                        }

                        if (m == 6) {

                            System.out.println("--- modification date de fin de mise en enchere");

                            System.out.println("entrez le nom de l'objet concerné");

                            String Titre = Lire.S();

                            System.out.println("Entrez l'annee");
                            int annee = Lire.i();

                            System.out.println("Entrez le mois");
                            int mois = Lire.i();

                            System.out.println("entrez la date");
                            int jour = Lire.i();

                            int heure = 00;
                            int minute = 00;
                            int seconde = 00;

                            String Fin = annee + "-" + mois + "-" + jour + " " + heure + ":" + minute + ":" + seconde; //je crée un string de format exact à ce à quoi est sensé ressembler un timestamp
                            demandeUpdateFin(con, Titre, annee, mois, jour, Fin);
                            System.out.println("La date a été modifiée avec succès.");
                        }
                        if (m==7){
                            afficheAllObjets(con);
                        }
                        if (m == 0) {
                            System.out.println("ok bye");

                        }

                    }
                }

                if (u == 'd') {
                    m = -1;

                    while (m != 0) {
                        System.out.println("Bienvenue dans le menu categorie!");
                        System.out.println("Voici vos options");
                        System.out.println("(1) Ajouter une nouvelle categorie générale");
                        System.out.println("(2) Ajouter une nouvelle sous catégorie");
                        System.out.println("(3) Afficher toutes les catégories générales et leurs sous-catégories");
                        System.out.println("(4) Retrouver l'identifiant d'une catégorie");
                        System.out.println("(0) Quitter le menu");
                        System.out.println("Entrez votre choix");
                        m = Lire.i();

                        if (m == 1) {
                            System.out.println("--- creation nouvelle categorie générale");
                            System.out.println("Entrez le nom de la categorie générale");
                            String nomcat = Lire.S();
                            GestionBDinterface.demandeCategorieGenerale(con, nomcat);
                            System.out.println("Catégorie ajoutée avec succès");
                        }

                        if (m == 2) {
                            System.out.println("--- creation nouvelle sous-catégorie");
                            System.out.println("Entrez le nom de la sous-catégorie");
                            String nomcat = Lire.S();
                            System.out.println("Entrez le nom de sa categorie générale");
                            String nomcatgen = Lire.S();
                            demandeCategorie(con, nomcat, nomcatgen);
                            System.out.println("Sous-catégorie ajoutée avec succès.");

                        }

                        if (m == 3) {
                            System.out.println("Entrez le nom de la catégorie  générale cherchée");
                            String nomcatgen = Lire.S();
                            GestionBDinterface.afficheallcats1(con, nomcatgen);

                        }

                        if (m == 4) {
                            System.out.println("entrez le nom de la categorie recherchée");
                            String nomcat = Lire.S();
                            trouveidCategorie(con, nomcat);

                        }

                        if (m == 0) {
                            System.out.println("ok bye");

                        }

                    }
                }

                if (u == 'e') {
                    m = -1;

                    while (m != 0) {
                        System.out.println("Bienvenue dans le menu enchere!");
                        System.out.println("Voici vos options");
                        System.out.println("(1) Ajouter une nouvelle enchere sur un objet donné");
                        System.out.println("(2) Afficher l'ensemble des encheres faites sur un objet donné");
                        System.out.println("(0) Quitter le menu");
                        System.out.println("Entrez votre choix");
                        m = Lire.i();

                        if (m == 1) {
                            System.out.println("--- ajout nouvelle enchere");

                            System.out.println("Entrez le titre de l'objet sur lequel vous souhaitez faire une enchere: ");
                            String titreobj = Lire.S();

                            System.out.println("Entrez le montant de votre offre:");
                            int offre = Lire.i();

                            System.out.println("A present vous allez vous identifier à votre enchere, entrez votre adresse mail:");
                            String emailuser = Lire.S();
                            try {
                                GestionBDinterface.demandeEnchere(con, titreobj, offre, emailuser);
                            } catch (Exception ex) {
                                System.out.println("Problème détécté quelque part,il faut recommencer.");
                            }
                        }

                        if (m == 2) {

                            System.out.println("Entrez le nom de l'objet concerné");
                            String nomobjet = Lire.S();
                            afficheEnchere(con, nomobjet);

                        }

                        if (m == 0) {
                            System.out.println("ok bye");

                        }

                    }
                }

                if (u == 'o') {

                    System.out.println("eh bien dégagez alors!");
                }

            } catch (SQLException ex) {
                throw new Error(ex);
            }

        }

    }
}
