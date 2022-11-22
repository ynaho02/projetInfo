/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projetinfo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;

/**
 *
 * @author ynaho01
 */
public class GestionBD {

    public static Connection connectGeneralPostGres(String host,
            int port, String database,
            String user, String pass)
            throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        Connection con = DriverManager.getConnection(
                "jdbc:postgresql://" + host + ":" + port
                + "/" + database,
                user, pass);
        con.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
        return con;
    }

    public static Connection defautConnect()
            throws ClassNotFoundException, SQLException {
        return connectGeneralPostGres("localhost", 5439, "postgres", "postgres", "pass");
    }

    public static void creeSchema(Connection con)
            throws SQLException {

        con.setAutoCommit(false);
        try ( Statement st = con.createStatement()) {

            // creation des tables
            String utilisateur2 = "create table utilisateur2"
                    + ""
                    + "("
                    + "id integer not null primary key generated always as identity,"
                    + " nom varchar (30),"
                    + "prenom varchar(40),"
                    + "email varchar(100) unique"
                    + ")";

            st.executeUpdate(utilisateur2);
            System.out.println("table utilisateur2 created");

            String categorie2 = "create table categorie2"
                    + ""
                    + "("
                    + "id integer not null primary key generated always as identity ,"
                    + "nom varchar (30)"
                    + ")";

            st.executeUpdate(categorie2);
            System.out.println("table categorie2 created");

            String objet2 = "create table objet2"
                    + ""
                    + "("
                    + "id integer not null primary key generated always as identity ,"
                    + " titre varchar (300),"
                    + "description varchar(300),"
                    + "debut timestamp,"
                    + "fin timestamp,"
                    + "prixbase integer,"
                    + "proposepar integer,"
                    + "categorie integer"
                    + ")";

            st.executeUpdate(objet2);

            System.out.println("table objet2 created");

            st.executeUpdate(
                    """
                    alter table objet2
                        add constraint fk_objet2_proposepar
                        foreign key (proposepar) references utilisateur2(id)
                    """);
            System.out.println("clé externe propose par ajoutée");

            st.executeUpdate(
                    """
                    alter table objet2
                        add constraint fk_objet2_categorie
                        foreign key (categorie) references categorie2(id)
                    """);
            System.out.println("clé externe categorie ajoutée");

            String enchere2 = "create table enchere2"
                    + ""
                    + "("
                    + "id integer not null primary key generated always as identity ,"
                    + "quand timestamp,"
                    + "montant integer,"
                    + "sur integer,"
                    + "de integer"
                    + ")";

            st.executeUpdate(enchere2);
            System.out.println("table enchere2 created");

            st.executeUpdate(
                    """
                    alter table enchere2
                        add constraint fk_enchere2_de
                        foreign key (de) references utilisateur2(id)
                    """);
            System.out.println("clé externe de ajoutée");

            st.executeUpdate(
                    """
                    alter table enchere2
                        add constraint fk_enchere2_sur
                        foreign key (sur) references objet2(id)
                    """);
            System.out.println("clé externe sur ajoutée");

            con.commit();
            // je retourne dans le mode par défaut de gestion des transaction :
            // chaque ordre au SGBD sera considéré comme une transaction indépendante
            con.setAutoCommit(true);
        } catch (SQLException ex) {
            // quelque chose s'est mal passé
            // j'annule la transaction
            con.rollback();
            // puis je renvoie l'exeption pour qu'elle puisse éventuellement
            // être gérée (message à l'utilisateur...)
            throw ex;
        } finally {
            // je reviens à la gestion par défaut : une transaction pour
            // chaque ordre SQL
            con.setAutoCommit(true);
        }

    }

    public static void deleteSchema(Connection con) throws SQLException {
        try ( Statement st = con.createStatement()) {
            // pour être sûr de pouvoir supprimer, il faut d'abord supprimer les liens
            // puis les tables
            // suppression des liens
            try {
                st.executeUpdate(
                        """
                    alter table objet2
                        drop constraint fk_objet2_proposepar
                             """);
                System.out.println("constraint fk_proposepar dropped");
            } catch (SQLException ex) {
                // nothing to do : maybe the constraint was not created
            }
            try {
                st.executeUpdate(
                        """
                    alter table objet2
                        drop constraint fk_objet2_categorie
                    """);
                System.out.println("constraint fk_categorie dropped");
            } catch (SQLException ex) {
                // nothing to do : maybe the constraint was not created
            }

            try {
                st.executeUpdate(
                        """
                    alter table enchere2
                        drop constraint fk_enchere2_de
                    """);
                System.out.println("constraint fk_de dropped");
            } catch (SQLException ex) {
                // nothing to do : maybe the constraint was not created
            }
            try {
                st.executeUpdate(
                        """
                    alter table enchere2
                        drop constraint fk_enchere2_sur
                    """);
                System.out.println("constraint fk_sur dropped");
            } catch (SQLException ex) {
                // nothing to do : maybe the constraint was not created
            }

            // je peux maintenant supprimer les tables
            try {
                st.executeUpdate(
                        """
                    drop table utilisateur2
                    """);
                System.out.println("dable utilisateur2 dropped");
            } catch (SQLException ex) {
                // nothing to do : maybe the table was not created
            }
            try {
                st.executeUpdate(
                        """
                    drop table objet2
                    """);
                System.out.println("table objet2 dropped");
            } catch (SQLException ex) {
                // nothing to do : maybe the table was not created
            }
            try {
                st.executeUpdate(
                        """
                    drop table enchere2
                    """);
                System.out.println("table enchere2 dropped");
            } catch (SQLException ex) {
                // nothing to do : maybe the table was not created
            }

            try {
                st.executeUpdate(
                        """
                    drop table categorie2
                    """);
                System.out.println("table categorie2 dropped");
            } catch (SQLException ex) {
                // nothing to do : maybe the table was not created
            }
        }
    }

    public static int createUtilisateur(Connection con, String nom, String prenom, String email)
            throws SQLException, EmailExisteDejaException {
        // je me place dans une transaction pour m'assurer que la séquence
        // test du nom - création est bien atomique et isolée
        con.setAutoCommit(false);
        try ( PreparedStatement chercheEmail = con.prepareStatement(
                "select id from utilisateur2 where email = ?")) { //seul le mail doit etre unique pour notre bdd
            chercheEmail.setString(1, email); //on indique ici que le premier point ? référence le mail
            ResultSet testEmail = chercheEmail.executeQuery();
            if (testEmail.next()) {
                throw new EmailExisteDejaException();
            }
            // lors de la creation du PreparedStatement, il faut que je précise
            // que je veux qu'il conserve les clés générées
            try ( PreparedStatement pst = con.prepareStatement(
                    """
                insert into utilisateur2 (nom,prenom,email) values (?,?,?)
                """, PreparedStatement.RETURN_GENERATED_KEYS)) {
                pst.setString(1, nom);
                pst.setString(2, prenom);
                pst.setString(3, email);
                pst.executeUpdate();
                con.commit();

                // je peux alors récupérer les clés créées comme un result set :
                try ( ResultSet rid = pst.getGeneratedKeys()) {
                    // et comme ici je suis sur qu'il y a une et une seule clé, je
                    // fait un simple next 
                    rid.next();
                    // puis je récupère la valeur de la clé créé qui est dans la
                    // première colonne du ResultSet
                    int id = rid.getInt(1);
                    return id;
                }
            }
        } catch (Exception ex) {
            con.rollback();
            throw ex;
        } finally {
            con.setAutoCommit(true);
        }

    }

    public static class EmailExisteDejaException extends Exception {
    }

    public static class CategorieExisteDejaException extends Exception {
    }

    public static void demandeUtilisateur(Connection con) throws SQLException {
        boolean existe = true;
        while (existe) {
            System.out.println("--- creation nouvel utilisateur");
            System.out.println("Entrez votre nom, votre prénom et votre mail");
            String nom = Lire.S();
            String prenom = Lire.S();
            String email = Lire.S();
            try {
                createUtilisateur(con, nom, prenom, email);
                existe = false;
            } catch (EmailExisteDejaException ex) {
                System.out.println("cet email est déjà prit, choisissez en un autre");
            }
        }
    }

    public static void TrouveUtilisateurNom(Connection con) throws SQLException { //choisir un utilisateur à partir de son nom et renvoyer ses infos

        con.setAutoCommit(false);
        try ( PreparedStatement searchuser = con.prepareStatement("select id, prenom, email from utilisateur2 where nom = ?")) {
            System.out.println("entrez le nom de l'utilisateur recherché");
            String nom = Lire.S();
            searchuser.setString(1, nom); //on indique ici que le premier point ? référence le nom
            ResultSet testNom = searchuser.executeQuery();

            boolean exist = false;
            while (testNom.next()) { //je vérifie que le nom est bien dans la base de données
                //le resultset est un peu comme un tableau quicontient l'ensemble des résultats de ta requete
                //quand tu fais next le pointeur va sur une autre case mais au début il est avant la première ligne
                exist = true;

                if (exist == true) {

                    System.out.println("voici les infos de l'utilisateur cherché");
                    System.out.println("id: " + testNom.getInt("id"));
                    System.out.println("Prenom: " + testNom.getString("prenom"));
                    System.out.println("Mail: " + testNom.getString("email"));

                }
            }
        } catch (SQLException ex) {
            throw new Error(ex);
        }

    }

    public static void afficheUsers(Connection con) throws SQLException {

        try {

            Statement st = con.createStatement();
            ResultSet result = st.executeQuery("Select * from utilisateur2");

            System.out.println("ok voici la liste des utilisateurs :");
            System.out.println("------------------------");

            while (result.next()) {

                System.out.println("id: " + result.getInt("id"));
                System.out.println("Nom: " + result.getString("nom"));
                System.out.println("Prenom: " + result.getString("prenom"));

            }
        } catch (SQLException ex) {
            throw new Error(ex);
        }

    }

    public static int createCategorie(Connection con, String nom)
            throws SQLException, CategorieExisteDejaException {
        // je me place dans une transaction pour m'assurer que la séquence
        // test du nom - création est bien atomique et isolée
        con.setAutoCommit(false);
        try ( PreparedStatement chercheCat = con.prepareStatement(
                "select id from categorie2 where nom = ?")) {
            chercheCat.setString(1, nom); //on indique ici que le premier point ? référence le mail
            ResultSet testCat = chercheCat.executeQuery();
            if (testCat.next()) {
                throw new CategorieExisteDejaException();
            }

            try ( PreparedStatement pst = con.prepareStatement(
                    """
                insert into categorie2 (nom) values (?)
                """, PreparedStatement.RETURN_GENERATED_KEYS)) {
                pst.setString(1, nom);
                pst.executeUpdate();
                con.commit();

                // je peux alors récupérer les clés créées comme un result set :
                try ( ResultSet rid = pst.getGeneratedKeys()) {
                    // et comme ici je suis sur qu'il y a une et une seule clé, je
                    // fait un simple next 
                    rid.next();
                    // puis je récupère la valeur de la clé créé qui est dans la
                    // première colonne du ResultSet
                    int id = rid.getInt(1);
                    return id;
                }
            }
        } catch (Exception ex) {
            con.rollback();
            throw ex;
        } finally {
            con.setAutoCommit(true);
        }

    }

    public static class CategorieNexistePasException extends Exception {
    }

    public static class UtilisateurNexistePasException extends Exception {
    }

    public static int createObjet(Connection con, String titre, String description, Timestamp debut, Timestamp fin, int categorie, int prixbase, int proposepar)
            throws SQLException, CategorieNexistePasException, UtilisateurNexistePasException {

        con.setAutoCommit(false);

        try ( PreparedStatement pst = con.prepareStatement(
                """
                insert into objet2 (titre,description,debut,fin,proposepar,prixbase,categorie) values (?,?,?,?,?,?,?)
                """, PreparedStatement.RETURN_GENERATED_KEYS)) {
            pst.setString(1, titre);
            pst.setString(2, description);
            pst.setTimestamp(3, debut);
            pst.setTimestamp(4, fin);
            pst.setInt(5, prixbase);
            pst.setInt(6, categorie);
            pst.setInt(7, proposepar);

            pst.executeUpdate();

            con.commit();

            try ( ResultSet rid = pst.getGeneratedKeys()) {

                rid.next();

                int id = rid.getInt(1);
                return id;
            }

        } catch (Exception ex) {
            con.rollback();
            throw ex;
        } finally {
            con.setAutoCommit(true);
        }
    }

    public static class ObjetNexistePasException extends Exception {
    }

    public static int createEnchere(Connection con, Timestamp quand, int sur, int de, int montant) throws SQLException, ObjetNexistePasException, UtilisateurNexistePasException {

        con.setAutoCommit(false);

        try ( PreparedStatement pst = con.prepareStatement(
                """
                insert into enchere2 (quand,montant,sur,de) values (?,?,?,?)
                """, PreparedStatement.RETURN_GENERATED_KEYS)) {

            pst.setTimestamp(1, quand);
            pst.setInt(2, montant);
            pst.setInt(3, sur);
            pst.setInt(4, de);

            pst.executeUpdate();
            con.commit();

            try ( ResultSet rid = pst.getGeneratedKeys()) {

                rid.next();

                int id = rid.getInt(1);
                return id;
            }

        } catch (Exception ex) {
            con.rollback();
            throw ex;
        } finally {
            con.setAutoCommit(true);

        }

    }

    public static class MontantTropPetitException extends Exception {
    }

    public static class DelaiDEnchereDepasseException extends Exception {
    }

    public static void demandeEnchere(Connection con) throws SQLException, UtilisateurNexistePasException, ObjetNexistePasException, MontantTropPetitException, DelaiDEnchereDepasseException {
        boolean existe = true;
        while (existe) {

            System.out.println("--- ajout nouvelle enchere");

            int sur = -1;
            int de = -1;
            int max = -1;

            Timestamp quand = new Timestamp(System.currentTimeMillis());

            System.out.println("Entrez le titre de l'objet sur lequel vous souhaitez faire une enchere ");
            String titreobj = Lire.S();

            System.out.println("entrez le montant de votre choix");
            int offre = Lire.i();

            try ( PreparedStatement chercheObj = con.prepareStatement( //on vérifie que l'objet sur lequel on veut faire l'enchere est bien dans la bdd
                    "select id from objet2 where titre = ?")) {

                chercheObj.setString(1, titreobj);
                ResultSet testObj = chercheObj.executeQuery();

                if (testObj.next()) {
                    sur = testObj.getInt("id");

                } else {
                    throw new ObjetNexistePasException();

                }
            } catch (ObjetNexistePasException ex) {
                System.out.println(" l'objet voulu n'existe pas, retournez dans le menu le créer");
            }

            try ( PreparedStatement chercheUser = con.prepareStatement( //on vérifie que l'utilisateur est bien dans la bdd
                    "select id from utilisateur2 where nom = ?")) {
                System.out.println("A present vous allez vous identifier à votre enchere, entrez votre nom");
                String nomuser = Lire.S();
                chercheUser.setString(1, nomuser);
                ResultSet testUser = chercheUser.executeQuery();

                if (testUser.next()) {
                    de = testUser.getInt("id");

                } else {
                    throw new UtilisateurNexistePasException();

                }

            } catch (UtilisateurNexistePasException ex) {
                System.out.println(" l'utilisateur voulu n'existe pas, retournez dans le menu le créer");
            }
//
            try (
                     PreparedStatement chercheMontant = con.prepareStatement( //on s'occupe de la contrainte de l'enchere faite doit etre supérieure au max ou au prix base
                            """
                select prixbase, montant from enchere2, objet2 where montant = 
                            (select max(montant) from enchere2 where sur = (select id from objet2 where titre = ? )) 
               
                """
                    )) {

                        chercheMontant.setString(1, titreobj);
                        ResultSet testMontant = chercheMontant.executeQuery();

                        if (testMontant.next()) {

                            max = testMontant.getInt("montant");

                            if (max >= offre) {

                                throw new MontantTropPetitException();
                            } else {

                                System.out.println("offre acceptée");
                            }

                        } else {
                            PreparedStatement charchePrixBase = con.prepareStatement( //on s'occupe de la contrainte de l'enchere faite doit etre supérieure au max ou au prix base
                                    """
                select prixbase from objet2 where titre = ? 
               
                """
                            );
                            charchePrixBase.setString(1, titreobj);
                            ResultSet testPrixBase = charchePrixBase.executeQuery();
                            testPrixBase.next();

                            max = testPrixBase.getInt("prixbase");

                            if (offre <= max) {

                                throw new MontantTropPetitException();
                            } else {

                                System.out.println("offre acceptée");
                            }

                        }
                    } catch (MontantTropPetitException ex) {
                        System.out.println(" votre offre est trop petite désolé");
                    }

                    try ( PreparedStatement chercheQuand = con.prepareStatement( //on s'assure que le délai de l'enchere n'est pas dépassé
                            """
                select fin from objet2 
                where titre=?
               
                """
                    )) {

                        Timestamp fin = new Timestamp(0, 0, 0, 0, 0, 0, 0);

                        chercheQuand.setString(1, titreobj);
                        ResultSet testQuand = chercheQuand.executeQuery();

                        if (testQuand.next()) {

                            fin = testQuand.getTimestamp("fin");

                            if (quand.after(fin)) {

                                throw new DelaiDEnchereDepasseException();
                            } else {

                                System.out.println("enchere acceptée");
                            }

                        }
                    } catch (DelaiDEnchereDepasseException ex) {
                        System.out.println(" les enchères pour cet objet sont closes désolé");
                    }

                    try {
                        createEnchere(con, quand, sur, de, offre); //maintenant on ajoute l'enchere si toute les conditions sont respectées

                        existe = false;
                    } catch (SQLException ex) {

                    }

        }

    }

    public static void demandeObjet(Connection con) throws SQLException, UtilisateurNexistePasException, CategorieNexistePasException {

        boolean existe = true;
        while (existe) {

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

            Timestamp debut = new Timestamp(System.currentTimeMillis());
            Timestamp fin = Timestamp.valueOf(Fin); //valueof convertit un string en si on veut valeur d'une horloge

            int categorie = -1;
            int proposepar = -1;

            try ( PreparedStatement chercheCat = con.prepareStatement(
                    "select id from categorie2 where nom = ?")) {
                System.out.println("A present vous allez attribuer une catégorie à votre objet, entrez le nom de cette categorie ");
                String nom = Lire.S();
                chercheCat.setString(1, nom);
                ResultSet testCat = chercheCat.executeQuery();

                if (testCat.next()) {
                    categorie = testCat.getInt("id");

                } else {
                    throw new CategorieNexistePasException();

                }
            } catch (CategorieNexistePasException ex) {
                System.out.println(" la catégorie voulue n'existe pas, retournez dans le menu la créer");
            }

            try ( PreparedStatement chercheUs = con.prepareStatement(
                    "select id from utilisateur2 where nom = ?")) {
                System.out.println("A present il faut vous associer à cet objet, entrez donc votre nom ");
                String Nom = Lire.S();
                chercheUs.setString(1, Nom);
                ResultSet testUs = chercheUs.executeQuery();
                if (testUs.next()) {

                    proposepar = testUs.getInt("id");
                } else {
                    throw new UtilisateurNexistePasException();
                }
            } catch (UtilisateurNexistePasException ex) {
                System.out.println(" l'utilisateur n'est pas reconnu, retournez dans le menu l'ajouter");

            }

            try {
                createObjet(con, titre, description, debut, fin, prixbase, proposepar, categorie);

                existe = false;
            } catch (SQLException ex) {

            }

        }

    }

    public static void demandeCategorie(Connection con) throws SQLException, CategorieExisteDejaException { //entrer manuellement de nouvelles categories
        boolean existe = true;
        while (existe) {
            System.out.println("--- creation nouvel categorie");
            System.out.println("Entrez le nom de la categorie");
            String nom = Lire.S();
            try {
                createCategorie(con, nom);
                existe = false;
            } catch (CategorieExisteDejaException ex) {
                System.out.println("Categorie deja existante, cherchez bien!");
            }
        }
    }

    public static void TrouveObjetCat(Connection con) throws SQLException {
        con.setAutoCommit(false);
        try ( PreparedStatement searchobjet = con.prepareStatement(
                """
         select objet2.id, titre, description, debut, fin, prixbase
                         from objet2 join categorie2 on objet2.categorie = categorie2.id
                         where categorie2.nom = ?
                         order by titre asc
        
        """
        )) {

            System.out.println("Entrez la catégorie d'objet que vous recherchez");
            String nom = Lire.S();
            searchobjet.setString(1, nom); //on indique ici que le premier point ? référence le nom
            ResultSet rs = searchobjet.executeQuery();

            while (rs.next()) {

                System.out.println("voici les infos pour l'objet d'identifiant :" + rs.getInt("id"));
                System.out.println("titre: " + rs.getString("titre"));
                System.out.println("description: " + rs.getString("description"));
                System.out.println("date de mise en enchere: " + rs.getTimestamp("debut"));
                System.out.println("date de fin de la mise en enchere: " + rs.getTimestamp("fin"));
                System.out.println("le prix de base est de: " + rs.getInt("prixbase") + "euros");

            }

        } catch (SQLException ex) {
            throw new Error(ex);
        }
    }

    public static void TrouveObjetMot(Connection con) throws SQLException {
        con.setAutoCommit(false);
        try ( PreparedStatement searchobjett = con.prepareStatement(
                """
         select * from objet2 where description like ?
                         order by titre asc
        
        """
        )) {

            System.out.println("Entrez un mot clé pour chercher des objets correspondants");
            String mot = Lire.S();
            searchobjett.setString(1, "%"+mot+"%");
            ResultSet rs = searchobjett.executeQuery();

            while (rs.next()) {

                System.out.println("voici les infos pour l'objet d'identifiant :" + rs.getInt("id"));
                System.out.println("titre: " + rs.getString("titre"));
                System.out.println("description: " + rs.getString("description"));
                System.out.println("date de mise en enchere: " + rs.getTimestamp("debut"));
                System.out.println("date de fin de la mise en enchere: " + rs.getTimestamp("fin"));
                System.out.println("le prix de base est de: " + rs.getInt("prixbase") + "euros");

            }

        } catch (SQLException ex) {
            throw new Error(ex);
        }
    }

    public static void BilanUser(Connection con) throws SQLException { //il manque une vérification de si l'utilisateur existe vraiment ou pas

        con.setAutoCommit(false);
        try ( PreparedStatement searchuser = con.prepareStatement(
                """
               
                select utilisateur2.id, nom, prenom, email, titre, fin  from utilisateur2,objet2 where utilisateur2.id=objet2.proposepar
                and nom=?
                                                                  
                """
        )) {
            System.out.println("entrez le nom de l'utilisateur pour qui vous voulez le bilan");
            String nom = Lire.S();
            searchuser.setString(1, nom); //on indique ici que le premier point ? référence le nom
            ResultSet testNom = searchuser.executeQuery();

            boolean exist = false;
            while (testNom.next()) { //je vérifie que le nom est bien dans la base de données
                //le resultset est un peu comme un tableau quicontient l'ensemble des résultats de ta requete
                //quand tu fais next le pointeur va sur une autre case mais au début il est avant la première ligne
                exist = true;

                if (exist == true) {

                    System.out.println("voici les infos de l'utilisateur nommé:" + testNom.getString("nom"));
                    System.out.println("id: " + testNom.getInt("id"));
                    System.out.println("prenom: " + testNom.getString("prenom"));
                    System.out.println("son mail: " + testNom.getString("email"));
                    System.out.println("Les titres des objets qu'il a proposé en enchere: " + testNom.getString("titre"));
                    System.out.println("Leur date de fin d'enchere: " + testNom.getTimestamp("fin"));

                }
            }
        } catch (SQLException ex) {
            throw new Error(ex);
        }

    }

    public static void UpdateObjetFin(Connection con, String titre, Timestamp fin)
            throws SQLException, ObjetNexistePasException {
        // je me place dans une transaction pour m'assurer que la séquence
        // test du nom - création est bien atomique et isolée
        con.setAutoCommit(false);
        try ( PreparedStatement cherchetitre = con.prepareStatement(
                "select id from objet2 where titre = ?")) { //seul le mail doit etre unique pour notre bdd
            cherchetitre.setString(1, titre); //on indique ici que le premier point ? référence le mail
            ResultSet testTitre = cherchetitre.executeQuery();
            if (testTitre.next()) {
                System.out.println("ok objet retrouvé");
            } else {
                throw new ObjetNexistePasException();

            }

            try ( PreparedStatement pst = con.prepareStatement(
                    """
               UPDATE objet2
               SET fin = ?
               WHERE titre = ?
                """)) {
                pst.setTimestamp(1, fin);
                pst.setString(2, titre);

                pst.executeUpdate();
                con.commit();

            }
        } catch (Exception ex) {
            con.rollback();
            throw ex;
        } finally {
            con.setAutoCommit(true);
        }

    }

    public static void demandeUpdateFin(Connection con) throws SQLException, ObjetNexistePasException {

        boolean existe = true;
        while (existe) {
            System.out.println("--- modification date de fin de mise en enchere");

            System.out.println("entrez le nom de l'objet concerné");

            String Titre = Lire.S();

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

            Timestamp fin = Timestamp.valueOf(Fin); //valueof convertit un string en si on veut valeur d'une horloge;
            try {
                UpdateObjetFin(con, Titre, fin);
                existe = false;
            } catch (ObjetNexistePasException ex) {
                System.out.println("cet objet n'existe pas désolé");
            }
        }

    }

    public static void TrouveidCategorie(Connection con) throws SQLException, CategorieNexistePasException {

        con.setAutoCommit(false);
        try ( PreparedStatement searchcat = con.prepareStatement("select idfrom categorie2 where nom = ?")) {
            System.out.println("entrez le nom de la categorie recherchée");
            String nom = Lire.S();
            searchcat.setString(1, nom); //on indique ici que le premier point ? référence le nom
            ResultSet testCat = searchcat.executeQuery();

            boolean exist = false;
            while (testCat.next()) { //je vérifie que le nom est bien dans la base de données
                //le resultset est un peu comme un tableau quicontient l'ensemble des résultats de ta requete
                //quand tu fais next le pointeur va sur une autre case mais au début il est avant la première ligne
                exist = true;

                if (exist == true) {

                    System.out.println("l'id de la catégorie cherchée est" + testCat.getInt("id"));

                }
            }
        } catch (SQLException ex) {
            throw new Error(ex);
        }

    }

    public static void main(String[] args) {
        try ( Connection con = defautConnect()) {
            System.out.println("connection réussie");
//            demandeEnchere(con);
TrouveObjetMot(con);
        } catch (Exception ex) {
            throw new Error(ex);
        }
    }

}
