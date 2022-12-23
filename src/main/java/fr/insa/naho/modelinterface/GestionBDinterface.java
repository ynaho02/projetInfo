/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.naho.modelinterface;

import fr.insa.naho.modelinterface.Enchere;
import fr.insa.naho.modelinterface.Categoriegenerale;
import fr.insa.naho.modelinterface.Categorie;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;

/**
 *
 * @author ynaho01
 */
public class GestionBDinterface {

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
        return connectGeneralPostGres("localhost", 5432, "postgres", "postgres", "pass"); //Le port c'est du 5432 sur ordi perso et du 5439 sur ordi de l'école
        // donc si le port n'est pas changé modifie le!
    }

    public static void creeSchema(Connection con)
            throws SQLException {
//on crée le schéma c'est à dire l'ensemble des tables
        con.setAutoCommit(false);
        try ( Statement st = con.createStatement()) {

//            // creation des tables
            String utilisateur = "create table utilisateur"
                    + ""
                    + "("
                    + "id integer not null primary key generated always as identity,"
                    + " nom varchar (30),"
                    + "prenom varchar(40),"
                    + "email varchar(100) unique,"
                    + "motdepasse varchar (30),"
                    + "codepostal varchar (100)"
                    + ")";

            st.executeUpdate(utilisateur);
            System.out.println("table utilisateur created");

            String categoriegenerale = "create table categoriegenerale"
                    + ""
                    + "("
                    + "id integer not null primary key generated always as identity ,"
                    + "nom varchar (30)"
                    + ")";

            st.executeUpdate(categoriegenerale);
            System.out.println("table categoriegenerale created");

            String categorie = "create table categorie"
                    + ""
                    + "("
                    + "id integer not null primary key generated always as identity ,"
                    + "nom varchar (30),"
                    + "generale integer"
                    + ")";

            st.executeUpdate(categorie);
            System.out.println("table categorie created");

            st.executeUpdate(
                    """
                    alter table categorie
                        add constraint fk_categorie_generale
                        foreign key (generale) references categoriegenerale(id)
                    """);
            System.out.println("clé externe generale ajoutée");

            String objet = "create table objet"
                    + ""
                    + "("
                    + "id integer not null primary key generated always as identity ,"
                    + " titre varchar (300),"
                    + "description varchar(300),"
                    + "debut timestamp,"
                    + "fin timestamp,"
                    + "prixbase integer,"
                    + "proposepar integer,"
                    + "categoriegenerale integer,"
                    + "categorie integer"
                    + ")";

            st.executeUpdate(objet);

            System.out.println("table objet created");

            st.executeUpdate(
                    """
                    alter table objet
                        add constraint fk_objet_proposepar
                        foreign key (proposepar) references utilisateur(id)
                    """);
            System.out.println("clé externe proposepar ajoutée");

            st.executeUpdate(
                    """
                    alter table objet
                        add constraint fk_objet_categorie
                        foreign key (categorie) references categorie(id)
                    """);
            System.out.println("clé externe categorie ajoutée");

            st.executeUpdate(
                    """
                    alter table objet
                        add constraint fk_objet_categoriegenerale
                        foreign key (categoriegenerale) references categoriegenerale(id)
                    """);
            System.out.println("clé externe categoriegenerale ajoutée");

            String enchere = "create table enchere"
                    + ""
                    + "("
                    + "id integer not null primary key generated always as identity ,"
                    + "quand timestamp,"
                    + "montant integer,"
                    + "sur integer,"
                    + "de integer"
                    + ")";

            st.executeUpdate(enchere);
            System.out.println("table enchere created");

            st.executeUpdate(
                    """
                    alter table enchere
                        add constraint fk_enchere_de
                        foreign key (de) references utilisateur(id)
                    """);
            System.out.println("clé externe de ajoutée");

            st.executeUpdate(
                    """
                    alter table enchere
                        add constraint fk_enchere_sur
                        foreign key (sur) references objet(id)
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
                    alter table objet
                        drop constraint fk_objet_proposepar
                             """);
                System.out.println("constraint fk_proposepar dropped");
            } catch (SQLException ex) {
                // nothing to do : maybe the constraint was not created
            }
            try {
                st.executeUpdate(
                        """
                    alter table objet
                        drop constraint fk_objet_categorie
                    """);
                System.out.println("constraint fk_categorie dropped");
            } catch (SQLException ex) {
                // nothing to do : maybe the constraint was not created
            }
            try {
                st.executeUpdate(
                        """
                    alter table objet
                        drop constraint fk_objet_categoriegenerale
                    """);
                System.out.println("constraint fk_categoriegenerale dropped");
            } catch (SQLException ex) {
                // nothing to do : maybe the constraint was not created
            }
            try {
                st.executeUpdate(
                        """
                    alter table categorie
                        drop constraint fk_categorie_generale
                    """);
                System.out.println("constraint fk_generale dropped");
            } catch (SQLException ex) {
                // nothing to do : maybe the constraint was not created
            }

            try {
                st.executeUpdate(
                        """
                    alter table enchere
                        drop constraint fk_enchere_de
                    """);
                System.out.println("constraint fk_de dropped");
            } catch (SQLException ex) {
                // nothing to do : maybe the constraint was not created
            }

            try {
                st.executeUpdate(
                        """
                    alter table enchere
                        drop constraint fk_enchere_sur
                    """);
                System.out.println("constraint fk_sur dropped");
            } catch (SQLException ex) {
                // nothing to do : maybe the constraint was not created
            }

//
            // je peux maintenant supprimer les tables
            try {
                st.executeUpdate(
                        """
                    drop table utilisateur
                    """);
                System.out.println("table utilisateur dropped");
            } catch (SQLException ex) {
                // nothing to do : maybe the table was not created
            }

            try {
                st.executeUpdate(
                        """
                    drop table objet
                    """);
                System.out.println("table objet dropped");
            } catch (SQLException ex) {
                // nothing to do : maybe the table was not created
            }
            try {
                st.executeUpdate(
                        """
                    drop table enchere
                    """);
                System.out.println("table enchere dropped");
            } catch (SQLException ex) {
                // nothing to do : maybe the table was not created
            }

//
            try {
                st.executeUpdate(
                        """
                    drop table categorie
                    """);
                System.out.println("table categorie dropped");
            } catch (SQLException ex) {
                // nothing to do : maybe the table was not created
            }

            try {
                st.executeUpdate(
                        """
                    drop table categoriegenerale
                    """);
                System.out.println("table categoriegenerale dropped");
            } catch (SQLException ex) {
                // nothing to do : maybe the table was not created
            }

        } catch (SQLException ex) {
            // nothing to do : maybe the table was not created
        }
    }

    public static int createUtilisateur(Connection con, String nom, String prenom, String email, String mdp, String codepostal)
            throws SQLException, EmailExisteDejaException {
        // je me place dans une transaction pour m'assurer que la séquence
        // test du nom - création est bien atomique et isolée
        con.setAutoCommit(false);
        try ( PreparedStatement chercheEmail = con.prepareStatement(
                "select id from utilisateur where email = ?")) { //seul le mail doit etre unique pour notre bdd
            chercheEmail.setString(1, email); //on indique ici que le premier point ? référence le mail
            ResultSet testEmail = chercheEmail.executeQuery();
            if (testEmail.next()) {
                throw new EmailExisteDejaException();
            }
            // lors de la creation du PreparedStatement, il faut que je précise
            // que je veux qu'il conserve les clés générées
            try ( PreparedStatement pst = con.prepareStatement(
                    """
                insert into utilisateur (nom,prenom,email,motdepasse,codepostal) values (?,?,?,?,?)
                """, PreparedStatement.RETURN_GENERATED_KEYS)) {
                pst.setString(1, nom);
                pst.setString(2, prenom);
                pst.setString(3, email);
                pst.setString(4, mdp);
                pst.setString(5, codepostal);
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

    public static boolean demandeUtilisateur(Connection con, String nom, String prenom, String email, String motdepasse, String codepostal) throws SQLException {
        boolean existe = true;
        while (existe) {

            try {
                createUtilisateur(con, nom, prenom, email, motdepasse, codepostal);
                existe = false;

            } catch (EmailExisteDejaException ex) {
                System.out.println("Cet email est déjà prit, choisissez en un autre.");
                return false;
            }
        }
        return true;
    }

    public static void trouveUtilisateurMail(Connection con, String mail) throws SQLException, UtilisateurNexistePasException { //choisir un utilisateur à partir de son nom et renvoyer ses infos

        con.setAutoCommit(false);
        try ( PreparedStatement searchuser = con.prepareStatement("select * from utilisateur where email = ?")) {

            searchuser.setString(1, mail); //on indique ici que le premier point ? référence le nom
            ResultSet testNom = searchuser.executeQuery();

            boolean exist = false;
            while (testNom.next()) { //je vérifie que le nom est bien dans la base de données
                //le resultset est un peu comme un tableau quicontient l'ensemble des résultats de ta requete
                //quand tu fais next le pointeur va sur une autre case mais au début il est avant la première ligne
                exist = true;

                if (exist == true) {

                    System.out.println("voici les infos de l'utilisateur cherché");
                    System.out.println("id: " + " " + testNom.getInt("id"));
                    System.out.println("Nom: " + " " + testNom.getString("nom"));
                    System.out.println("Prenom: " + " " + testNom.getString("prenom"));
                    System.out.println("Email: " + " " + testNom.getString("email"));
                    System.out.println("Code Postal: " + " " + testNom.getString("codepostal"));
                    System.out.println("Mot de passe: " + " " + testNom.getString("motdepasse"));

                } else {
                    throw new UtilisateurNexistePasException();

                }
            }
        }
    }

    public static ArrayList<Utilisateur> afficheUsers(Connection con) throws SQLException {
//afficher tous les utilisateurs inscrits dans la bdd
        ArrayList<Utilisateur> utilisateurs = new ArrayList<>();
        try {

            Statement st = con.createStatement();
            ResultSet result = st.executeQuery("Select * from utilisateur");

            System.out.println("ok voici la liste des utilisateurs :");
            System.out.println("------------------------");

            while (result.next()) {
                Utilisateur user = new Utilisateur(result.getInt("id"), result.getString("nom"), result.getString("prenom"),
                        result.getString("email"), result.getString("codepostal"), result.getString("motdepasse"));
                utilisateurs.add(user);
                for (int i = 0; i < utilisateurs.size(); i++) {
                    System.out.println(utilisateurs.get(i).toString());
                }

            }
        } catch (SQLException ex) {
            throw new Error(ex);
        }
        return utilisateurs;
    }
    
    public static ArrayList<Objet> afficheAllObjets(Connection con) throws SQLException {
//afficher tous les objets déposés dans la bdd
        ArrayList<Objet> objets = new ArrayList<>();
        try {

            Statement st = con.createStatement();
            ResultSet result = st.executeQuery("Select * from objet");

            System.out.println("ok voici la liste des objets :");
            System.out.println("------------------------");

            while (result.next()) {
                Objet obj = new Objet(result.getInt("id"), result.getString("titre"), result.getString("description"),
                       result.getTimestamp("debut"),result.getTimestamp("fin"), result.getInt("prixbase"), 
                        result.getInt("proposepar"), result.getInt("categoriegenerale"),result.getInt("categorie"));
                objets.add(obj);
                for (int i = 0; i < objets.size(); i++) {
                    System.out.println(objets.get(i).toString());
                }

            }
        } catch (SQLException ex) {
            throw new Error(ex);
        }
        return objets;
    }

    public static int createCategorieGenerale(Connection con, String nom)
            throws SQLException, CategorieExisteDejaException {
        // je me place dans une transaction pour m'assurer que la séquence
        // test du nom - création est bien atomique et isolée
        con.setAutoCommit(false);
        try ( PreparedStatement chercheCat = con.prepareStatement(
                "select id from categoriegenerale where nom = ?")) {
            chercheCat.setString(1, nom); //on indique ici que le premier point ? référence le mail
            ResultSet testCat = chercheCat.executeQuery();
            if (testCat.next()) {
                throw new CategorieExisteDejaException();
            }

            try ( PreparedStatement pst = con.prepareStatement(
                    """
                insert into categoriegenerale (nom) values (?)
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

    public static int createCategorie(Connection con, String nom, int generale)
            throws SQLException, CategorieExisteDejaException {
        // Ce n'est pas la méthode pour ajouter une categorie c'est plutot celle da,s laquelle on utilise la requete SQL
        //on a décomposé le truc et cette méthode est appelé dans demandeCategorie où on permet à l'utilisateur de rentrer les infos
        con.setAutoCommit(false);
        try ( PreparedStatement chercheCat = con.prepareStatement(
                "select id from categorie where nom = ?")) {
            chercheCat.setString(1, nom); //on indique ici que le premier point ? référence le mail
            ResultSet testCat = chercheCat.executeQuery();
            if (testCat.next()) {
                throw new CategorieExisteDejaException();
            }

            try ( PreparedStatement pst = con.prepareStatement(
                    """
                insert into categorie(nom,generale) values (?,?)
                """, PreparedStatement.RETURN_GENERATED_KEYS)) {
                pst.setString(1, nom);
                pst.setInt(2, generale);
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
            System.out.println("Problème" + ex.getMessage());
            con.rollback();
            throw ex;

        }
    }

    public static class CategorieNexistePasException extends Exception {
    }

    public static class UtilisateurNexistePasException extends Exception {
    }

    public static int createObjet(Connection con, String titre, String description, Timestamp debut, Timestamp fin, int prixbase, int proposepar, int categoriegenerale, int categorie)
            throws SQLException, CategorieNexistePasException, UtilisateurNexistePasException {
//C'est pareil, c'est la première partie du processsus permettant d'ajouter un objet
        ArrayList<Objet> Objets = new ArrayList<>();
        con.setAutoCommit(false);

        try ( PreparedStatement pst = con.prepareStatement(
                """
                insert into objet (titre,description,debut,fin,prixbase,proposepar,categoriegenerale,categorie) values (?,?,?,?,?,?,?,?)
                """, PreparedStatement.RETURN_GENERATED_KEYS)) {
            pst.setString(1, titre);
            pst.setString(2, description);
            pst.setTimestamp(3, debut);
            pst.setTimestamp(4, fin);
            pst.setInt(5, prixbase);
            pst.setInt(6, proposepar);
            pst.setInt(7, categoriegenerale);
            pst.setInt(8, categorie);

            pst.executeUpdate();

            con.commit();

            try ( ResultSet rid = pst.getGeneratedKeys()) {

                rid.next();

                int id = rid.getInt(1);
                Objet obj = new Objet(id, titre, description, debut, fin, prixbase, proposepar, categoriegenerale, categorie);
                Objets.add(obj);

                return id;

            }
        } catch (Exception ex) {
            System.out.println("Problème" + ex.getMessage());
            con.rollback();
            throw ex;

        } finally {
            con.setAutoCommit(true);

        }
    }

    public static class ObjetNexistePasException extends Exception {
    }

    public static int createEnchere(Connection con, Timestamp quand, int montant, int sur, int de) throws SQLException, ObjetNexistePasException, UtilisateurNexistePasException {
//C'est pareil, c'est la première partie du processsus permettant d'ajouter une enchere sur un objet
//la partie concernant les contraintes est gérée dans demande
        con.setAutoCommit(false);

        try ( PreparedStatement pst = con.prepareStatement(
                """
                insert into enchere (quand,montant,sur,de) values (?,?,?,?)
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
            System.out.println("Problème" + ex.getMessage());
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

    public static void demandeEnchere(Connection con, String titreobj, int offre, String emailuser)
            throws Exception {
//méthiode qui permet d'ajouter une enchère sur un objet
        int sur = -1;
        int de = -1;
        int max = -1;

        Timestamp quand = new Timestamp(System.currentTimeMillis());

        try ( PreparedStatement chercheObj = con.prepareStatement( //on vérifie que l'objet sur lequel on veut faire l'enchere est bien dans la bdd
                "select id from objet where titre = ?")) {

            chercheObj.setString(1, titreobj);
            ResultSet testObj = chercheObj.executeQuery();

            if (testObj.next()) {
                sur = testObj.getInt("id");

            } else {
                System.out.println(" L'objet voulu n'est pas répertorié.");
                throw new ObjetNexistePasException();

            }
        }

        try ( PreparedStatement chercheUser = con.prepareStatement( //on vérifie que l'utilisateur est bien dans la bdd
                "select id from utilisateur where email = ?")) {

            chercheUser.setString(1, emailuser);
            ResultSet testUser = chercheUser.executeQuery();

            if (testUser.next()) {
                de = testUser.getInt("id");

            } else {
                System.out.println(" L'utilisateur susmentionné n'existe pas.");
                throw new UtilisateurNexistePasException();

            }

        }

        try ( PreparedStatement chercheQuand = con.prepareStatement( //on s'assure que le délai de l'enchere n'est pas dépassé
                """
                select fin from objet 
                where titre=?
               
                """
        )) {

            Timestamp fin = new Timestamp(0, 0, 0, 0, 0, 0, 0);

            chercheQuand.setString(1, titreobj);
            ResultSet testQuand = chercheQuand.executeQuery();

            if (testQuand.next()) {

                fin = testQuand.getTimestamp("fin");

                if (quand.after(fin)) {
                    System.out.println("Les enchères pour cet objet sont malheureusement closes.");

                    throw new DelaiDEnchereDepasseException();
                } else {

                    System.out.println("Le délai d'enchère est respecté.");
                }

            }
        }

        try (
                 PreparedStatement chercheMontant = con.prepareStatement( //on s'occupe de la contrainte de l'enchere faite doit etre supérieure au max ou au prix base
                        """
                select montant from enchere, objet where montant = 
                            (select max(montant) from enchere where sur = (select id from objet where titre = ? )) 
               
                """
                )) {

                    chercheMontant.setString(1, titreobj);
                    ResultSet testMontant = chercheMontant.executeQuery();

                    if (testMontant.next()) {

                        max = testMontant.getInt("montant");

                        if (offre <= max) {

                            throw new MontantTropPetitException();
                        } else {

                            System.out.println("Votre offre est acceptée.");
                        }

                    } else {
                        PreparedStatement cherchePrixBase = con.prepareStatement(
                                """
                select prixbase from objet where titre = ? 
               
                """
                        );

                        cherchePrixBase.setString(1, titreobj);
                        ResultSet testPrixBase = cherchePrixBase.executeQuery();
                        testPrixBase.next();

                        max = testPrixBase.getInt("prixbase");

                        if (offre <= max) {
                            System.out.println("Cependant, votre offre est trop petite désolé.");

                            throw new MontantTropPetitException();
                        } else {

                            System.out.println("Votre offre est acceptée.");
                        }

                    }
                }
                try {
                    createEnchere(con, quand, offre, sur, de); //maintenant on ajoute l'enchere si toute les conditions sont respectées
                } catch (Exception ex) {
                    System.out.println("Problème" + ex.getMessage());
                }
    }

    public static void demandeObjet(Connection con, String titre, String description, int prixbase, int annee, int mois, int date, String Fin, String nomcatgen, String nomcat, String emailuser) throws Exception {
        ArrayList<Objet> objets = new ArrayList<>();
        boolean existe = true;
        while (existe) {

            Timestamp debut = new Timestamp(System.currentTimeMillis());
            Timestamp fin = Timestamp.valueOf(Fin); //valueof convertit un string en si on veut valeur d'une horloge

            int categorie = -1;
            int categoriegenerale = -1;
            int proposepar = -1;

            try ( PreparedStatement chercheCatGen = con.prepareStatement( //ici on récupère l'id de la cat correspondante
                    "select id from categoriegenerale where nom = ?")) {

                chercheCatGen.setString(1, nomcatgen);

                ResultSet testCatGen = chercheCatGen.executeQuery();

                if (testCatGen.next()) {
                    categoriegenerale = testCatGen.getInt("id");

                } else {
                    throw new CategorieNexistePasException();

                }
            } catch (CategorieNexistePasException ex) {
                System.out.println(" la catégorie voulue n'existe pas, retournez dans le menu la créer");
            }

            try ( PreparedStatement chercheCat = con.prepareStatement( //ici on récupère l'id de la cat correspondante
                    "select id from categorie where nom = ?")) {

                chercheCat.setString(1, nomcat);
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
                    "select id from utilisateur where email = ?")) {

                chercheUs.setString(1, emailuser);
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
                createObjet(con, titre, description, debut, fin, prixbase, proposepar, categoriegenerale, categorie);

            } catch (Exception ex) {
                System.out.println("Problème" + ex.getMessage());

            }

        }

    }

    public static void demandeCategorieGenerale(Connection con, String nomcat) throws SQLException, CategorieExisteDejaException { //entrer manuellement de nouvelles categories
        boolean existe = true;
        while (existe) {

            try {
                createCategorieGenerale(con, nomcat);
                existe = false;
            } catch (CategorieExisteDejaException ex) {
                System.out.println("Categorie deja existante, cherchez bien!");
            }
        }
    }

    public static void demandeCategorie(Connection con, String nomcat, String nomcatgen) throws SQLException, CategorieExisteDejaException { //entrer manuellement de nouvelles categories
        boolean existe = true;
        while (existe) {

            int generale = -1;

            try ( PreparedStatement chercheCat = con.prepareStatement( //ici on récupère l'id de la cat correspondante
                    "select id from categoriegenerale where nom = ?")) {

                chercheCat.setString(1, nomcatgen);
                ResultSet testCat = chercheCat.executeQuery();

                if (testCat.next()) {
                    generale = testCat.getInt("id");

                } else {
                    throw new CategorieNexistePasException();

                }
            } catch (CategorieNexistePasException ex) {
                System.out.println(" la catégorie generale voulue n'existe pas, retournez dans le menu la créer");
            }

            try {
                createCategorie(con, nomcat, generale);
                existe = false;
            } catch (CategorieExisteDejaException ex) {
                System.out.println("Categorie deja existante, cherchez bien!");
            }
        }
    }

    public static ArrayList<Objet> trouveObjetCatGen(Connection con, String nom) throws SQLException {
        //méthode permettant de trouver un objet en entrant une catégorie générale
        ArrayList<Objet> objets = new ArrayList<>();
        con.setAutoCommit(false);
        try ( PreparedStatement searchobjet = con.prepareStatement(
                """
         select objet.id, titre, description, debut, fin, prixbase, categorie, categoriegenerale, proposepar
                         from objet join categoriegenerale on objet.categoriegenerale = categoriegenerale.id
                         where categoriegenerale.nom = ?
                         order by titre asc
        
        """
        );  PreparedStatement searchobjet1 = con.prepareStatement(
                """
         select montant from enchere, objet where montant = 
                                     (select max(montant) from enchere where sur = (select id from objet where categoriegenerale = (select id from categoriegenerale where nom=? ))) 
        
        """)) {

            searchobjet.setString(1, nom); //on indique ici que le premier point ? référence le nom
            ResultSet rs = searchobjet.executeQuery();

            while (rs.next()) {

                System.out.println("voici les infos pour l'objet d'identifiant :" + " " + rs.getInt("id"));
                System.out.println("titre: " + " " + rs.getString("titre"));
                System.out.println("description: " + " " + rs.getString("description"));
                System.out.println("date de mise en enchere: " + " " + rs.getTimestamp("debut"));
                System.out.println("date de fin de la mise en enchere: " + " " + rs.getTimestamp("fin"));
                System.out.println("le prix de base est de: " + " " + rs.getInt("prixbase") + "euros");
                Objet objet = new Objet(rs.getInt("id"), rs.getString("titre"), rs.getString("description"), rs.getTimestamp("debut"),
                        rs.getTimestamp("fin"), rs.getInt("prixbase"), rs.getInt("proposepar"), rs.getInt("categoriegenerale"),
                        rs.getInt("categorie"));

                objets.add(objet);
                int maxi = getMax(con, objet);
                System.out.println("Le montant de l'enchère maximale est de :"+" "+maxi+ " "+ "euros");

            }


        } catch (SQLException ex) {
            System.out.println("problème: " + ex.getMessage());
            throw new Error(ex);
        }

        return objets;
    }

    public static int getMax(Connection con, Objet obj) throws SQLException {
        //cette méthode prend en paramètre d'entrée un objet et te retrouve et te renvoie le montant de l'enchere maximale faite sur lui
        try ( PreparedStatement searchobjet1 = con.prepareStatement(
                """
         select montant from enchere, objet where montant = 
                                     (select max(montant) from enchere where sur = (select id from objet where titre = ?)) 
        
        """)) {

            searchobjet1.setString(1, obj.getTitre());
            ResultSet rs1 = searchobjet1.executeQuery();

            if (rs1.next()) {
                System.out.println(" " + rs1.getInt("montant"));
                return rs1.getInt("montant");

            } else {

                return -1;
            }

        } catch (SQLException ex) {
            System.out.println("problème: " + ex.getMessage());
            throw new Error(ex);
        }

    }

    public static ArrayList<Objet> trouveObjetCat(Connection con, String nom) throws SQLException {
        ArrayList<Objet> objets = new ArrayList<>();
        con.setAutoCommit(false);
        try ( PreparedStatement searchobjet = con.prepareStatement(
                """
         select objet.id, titre, description, debut, fin, prixbase, proposepar, categoriegenerale, categorie
                         from objet join categorie on objet.categorie = categorie.id
                         where categorie.nom = ?
                         order by titre asc
        
        """
        )) {

            searchobjet.setString(1, nom); //on indique ici que le premier point ? référence le nom
            ResultSet rs = searchobjet.executeQuery();

            while (rs.next()) {

                System.out.println("voici les infos pour l'objet d'identifiant :" + " " + rs.getInt("id"));
                System.out.println("titre: " + " " + rs.getString("titre"));
                System.out.println("description: " + " " + rs.getString("description"));
                System.out.println("date de mise en enchere: " + " " + rs.getTimestamp("debut"));
                System.out.println("date de fin de la mise en enchere: " + " " + rs.getTimestamp("fin"));
                System.out.println("le prix de base était de: " + " " + rs.getInt("prixbase") + "euros");

                Objet objet = new Objet(rs.getInt("id"), rs.getString("titre"), rs.getString("description"), rs.getTimestamp("debut"),
                        rs.getTimestamp("fin"), rs.getInt("prixbase"), rs.getInt("proposepar"), rs.getInt("categoriegenerale"),
                        rs.getInt("categorie"));

                objets.add(objet);
                int maxi = getMax(con, objet);
                System.out.println("Le montant de l'enchère maximale est de :"+" "+maxi+ " "+ "euros");

            }

        } catch (SQLException ex) {
            System.out.println("problème: " + ex.getMessage());
            throw new Error(ex);
        }
        return objets;
    }

    public static ArrayList<Objet> trouveObjetMot(Connection con, String mot) throws SQLException {
        ArrayList<Objet> objets = new ArrayList<>();
        con.setAutoCommit(false);
        try ( PreparedStatement searchobjett = con.prepareStatement(
                """
         select * from objet where description like ?
                         order by titre asc
        
        """
        )) {

            searchobjett.setString(1, "%" + mot + "%");
            ResultSet rs = searchobjett.executeQuery();

            while (rs.next()) {

                System.out.println("voici les infos pour l'objet d'identifiant :" + " " + rs.getInt("id"));
                System.out.println("titre: " + " " + rs.getString("titre"));
                System.out.println("description: " + " " + rs.getString("description"));
                System.out.println("date de mise en enchere: " + " " + rs.getTimestamp("debut"));
                System.out.println("date de fin de la mise en enchere: " + " " + rs.getTimestamp("fin"));
                System.out.println("le prix de base est de: " + " " + rs.getInt("prixbase") + " " + "euros");
                Objet objet = new Objet(rs.getInt("id"), rs.getString("titre"), rs.getString("description"), rs.getTimestamp("debut"),
                        rs.getTimestamp("fin"), rs.getInt("prixbase"), rs.getInt("proposepar"), rs.getInt("categoriegenerale"),
                        rs.getInt("categorie"));

                objets.add(objet);
                int maxi = getMax(con, objet);
                System.out.println("Le montant de l'enchère maximale est de :"+" "+maxi+ " "+ "euros");
            }

        } catch (SQLException ex) {
            System.out.println("problème: " + ex.getMessage());
        }
        return objets;
    }

    public static ArrayList<Objet> trouveObjetCodePostal(Connection con, String codepostal) throws SQLException {
        ArrayList<Objet> objets = new ArrayList<>();
        con.setAutoCommit(false);
        try ( PreparedStatement searchobjett = con.prepareStatement(
                """
         select objet.id, titre, description, debut, fin, prixbase, categorie, categoriegenerale, proposepar 
                from objet join utilisateur on objet.proposepar = utilisateur.id
                where codepostal like ?
                         order by titre asc
        
        """
        )) {

            searchobjett.setString(1, "%" + codepostal + "%");
            ResultSet rs = searchobjett.executeQuery();

            while (rs.next()) {

                System.out.println("voici les infos pour l'objet d'identifiant :" + " " + rs.getInt("id"));
                System.out.println("titre: " + " " + rs.getString("titre"));
                System.out.println("description: " + " " + rs.getString("description"));
                System.out.println("date de mise en enchere: " + " " + rs.getTimestamp("debut"));
                System.out.println("date de fin de la mise en enchere: " + " " + rs.getTimestamp("fin"));
                System.out.println("le prix de base est de: " + " " + rs.getInt("prixbase") + " " + "euros");

                Objet objet = new Objet(rs.getInt("id"), rs.getString("titre"), rs.getString("description"), rs.getTimestamp("debut"),
                        rs.getTimestamp("fin"), rs.getInt("prixbase"), rs.getInt("proposepar"), rs.getInt("categoriegenerale"),
                        rs.getInt("categorie"));

                objets.add(objet);
                int maxi = getMax(con, objet);
                System.out.println("Le montant de l'enchère maximale est de :"+" "+maxi+ " "+ "euros");
            }

        } catch (SQLException ex) {
            System.out.println("problème: " + ex.getMessage());
        }

        return objets;
    }

    public static ArrayList<Objet> mesObjets(Connection con, String emailuser) throws Exception { //il manque une vérification de si l'utilisateur existe vraiment ou pas
        //un utiisateur entre son mail et on lui renvoie les infos de tous les objets qu'il a mis en enchere
        //en gros une fois qu'il s'est connecté faudrait stocker son mail dans une variable et c'est celle là que tu prends en paramètres ici
        ArrayList<Objet> objets = new ArrayList<>();
        con.setAutoCommit(false);
        try ( PreparedStatement searchuser = con.prepareStatement(
                """
               
                select objet.id, prenom, titre, description, debut, fin, prixbase,proposepar,
                categoriegenerale,categorie
                from utilisateur,objet 
                where utilisateur.id=objet.proposepar
                and email=?
                order by debut asc
                                                                  
                """
        )) {

            searchuser.setString(1, emailuser);
            ResultSet testNom = searchuser.executeQuery();

            boolean exist = false;

            while (testNom.next()) { 
                exist = true;

                if (exist == true) {


                    Objet objet = new Objet(testNom.getInt("id"), testNom.getString("titre"), testNom.getString("description"), testNom.getTimestamp("debut"),
                            testNom.getTimestamp("fin"), testNom.getInt("prixbase"), testNom.getInt("proposepar"), testNom.getInt("categoriegenerale"),
                            testNom.getInt("categorie"));

                    objets.add(objet);
                     for (int i = 0; i < objets.size(); i++) {
                    System.out.println(objets.get(i).toString());
            }
                } else {

                    System.out.println("Pas encore d'objet mis en enchère");
                }
            }
        } catch (Exception ex) {
            System.out.println(" " + ex.getMessage());

        }
        return objets;
    }

    public static ArrayList<Objet> mesObjetsVoulus(Connection con, String emailuser) throws SQLException {
       //ANCIENNEMENT MESENCHERES!!
       //JE L'AI RENOMMEE
       //un utilisateur entre son mail et on lui renvoie les infos des objet sur lesquels il a encheri

        ArrayList<Objet> objets = new ArrayList<>();
        con.setAutoCommit(false);
        try ( PreparedStatement searchuser = con.prepareStatement(
                """
               
                select objet.id, titre,description, debut, fin, categorie, categoriegenerale, proposepar,
                prixbase
                from utilisateur, objet, enchere 
                where enchere.de = utilisateur.id and enchere.sur = objet.id
                and email=?
                order by quand asc
                                                                  
                """
        )) {

            searchuser.setString(1, emailuser); 
            ResultSet testNom = searchuser.executeQuery();

            boolean exist = false;
            while (testNom.next()) { 
                exist = true;

            if (exist == true) {

//                   

                    Objet objet = new Objet(testNom.getInt("id"), testNom.getString("titre"), testNom.getString("description"), testNom.getTimestamp("debut"),
                            testNom.getTimestamp("fin"), testNom.getInt("prixbase"), testNom.getInt("proposepar"), testNom.getInt("categoriegenerale"),
                            testNom.getInt("categorie"));

                    objets.add(objet);
                     for (int i = 0; i < objets.size(); i++) {
                    System.out.println(objets.get(i).toString());
                     
                    int maxi = getMax(con, objet);
                    System.out.println("L'enchère maximale faite sur cet objet est de"+ " "+maxi+ " "+"euros");
                     }
                } else {
                    
                    System.out.println("Vous n'avez encore encheri sur aucun objet");
                }
            }
        } catch (Exception ex) {
            throw new Error(ex);
        }
        return objets;
    }

    public static void updateUtilisateurMdp(Connection con, String oldmdp, String newmdp)
            throws SQLException, UtilisateurNexistePasException {

        con.setAutoCommit(false);
        try ( PreparedStatement cherchemdp = con.prepareStatement(
                "select id from utilisateur where motdepasse = ?")) { 
            cherchemdp.setString(1, oldmdp); 
            ResultSet testmdp = cherchemdp.executeQuery();
            if (testmdp.next()) {
                System.out.println("Nous avons retrouvé l'utilisateur concerné.");
            } else {
                throw new UtilisateurNexistePasException();

            }

            try ( PreparedStatement pst = con.prepareStatement(
                    """
               UPDATE utilisateur
               SET motdepasse = ?
               WHERE motdepasse = ?
                """)) {
                pst.setString(1, newmdp);
                pst.setString(2, oldmdp);

                pst.executeUpdate();
                con.commit();

            }

        } finally {
            con.setAutoCommit(true);
        }

    }

    public static void updateUtilisateurEmail(Connection con, String oldemail, String newemail)
            throws SQLException, UtilisateurNexistePasException {

        con.setAutoCommit(false);
        try ( PreparedStatement cherchemail = con.prepareStatement(
                "select id from utilisateur where email = ?")) { 
            cherchemail.setString(1, oldemail); 
            ResultSet testmail = cherchemail.executeQuery();
            if (testmail.next()) {
                System.out.println("Nous avons retrouvé l'utilisateur concerné.");
            } else {
                throw new UtilisateurNexistePasException();

            }

            try ( PreparedStatement pst = con.prepareStatement(
                    """
               UPDATE utilisateur
               SET email = ?
               WHERE email = ?
                """)) {
                pst.setString(1, newemail);
                pst.setString(2, oldemail);

                pst.executeUpdate();
                con.commit();

            }

        } finally {
            con.setAutoCommit(true);
        }

    }

    public static void updateObjetFin(Connection con, String titre, Timestamp fin)
            throws SQLException, ObjetNexistePasException {
       
        con.setAutoCommit(false);
        try ( PreparedStatement cherchetitre = con.prepareStatement(
                "select id from objet where titre = ?")) { 
            cherchetitre.setString(1, titre); 
            ResultSet testTitre = cherchetitre.executeQuery();
            if (testTitre.next()) {
                System.out.println("Nous avons retrouvé l'objet concerné.");
            } else {
                throw new ObjetNexistePasException();

            }

            try ( PreparedStatement pst = con.prepareStatement(
                    """
               UPDATE objet
               SET fin = ?
               WHERE titre = ?
                """)) {
                pst.setTimestamp(1, fin);
                pst.setString(2, titre);

                pst.executeUpdate();
                con.commit();

            }

        } finally {
            con.setAutoCommit(true);
        }

    }

    public static void demandeUpdateFin(Connection con, String Titre, int annee, int mois, int jour, String Fin) throws SQLException, ObjetNexistePasException {

        boolean existe = true;
        while (existe) {

            Timestamp fin = Timestamp.valueOf(Fin); //valueof convertit un string en si on veut valeur d'une horloge;
            try {
                updateObjetFin(con, Titre, fin);
                existe = false;
            } catch (ObjetNexistePasException ex) {
                System.out.println("" + ex.getMessage());
            }
        }

    }

    public static void demandeUpdateEmail(Connection con, String oldemail, String newemail) throws SQLException, UtilisateurNexistePasException {

        boolean existe = true;
        while (existe) {

            try {
                updateUtilisateurEmail(con, oldemail, newemail);
                existe = false;
            } catch (UtilisateurNexistePasException ex) {
                System.out.println("" + ex.getMessage());
            }
        }

    }

    public static void demandeUpdateMdp(Connection con, String oldmdp, String newmdp) throws SQLException, UtilisateurNexistePasException {

        boolean existe = true;
        while (existe) {

            try {
                updateUtilisateurEmail(con, oldmdp, newmdp);
                existe = false;
            } catch (UtilisateurNexistePasException ex) {
                System.out.println("" + ex.getMessage());
            }
        }

    }

    public static void trouveidCategorie(Connection con, String nomcat) throws SQLException, CategorieNexistePasException {

        con.setAutoCommit(false);
        try ( PreparedStatement searchcat = con.prepareStatement("select id from categorie where nom = ?")) {

            searchcat.setString(1, nomcat); 
            ResultSet testCat = searchcat.executeQuery();

            boolean exist = false;
            while (testCat.next()) {
                exist = true;

                if (exist == true) {

                    System.out.println("l'id de la catégorie cherchée est" + testCat.getInt("id"));

                }
            }
        } catch (SQLException ex) {
            throw new Error(ex);
        }

    }

    public static ArrayList<Enchere> afficheEnchere(Connection con, String nomobjet) throws SQLException {
//méthode qui affiche les infos de toutes les enchères faites sur un objet donné
        ArrayList<Enchere> encheres = new ArrayList<>();
        con.setAutoCommit(false);
        try ( PreparedStatement searchobjet = con.prepareStatement(
                """
         select enchere.id, quand, montant,de, sur
                         from enchere join objet on enchere.sur = objet.id
                         where objet.titre = ?
                         order by quand asc
        
        """
        )) {

            searchobjet.setString(1, nomobjet); 
            ResultSet rs = searchobjet.executeQuery();
            System.out.println("voici l'ensemble des encheres faites sur cet objet:");

            while (rs.next()) {

                Enchere enchere = new Enchere(rs.getInt("id"), rs.getTimestamp("quand"),
                        rs.getInt("montant"), rs.getInt("sur"), rs.getInt("de"));
                encheres.add(enchere);
                 for (int i = 0; i < encheres.size(); i++) {
                    System.out.println(encheres.get(i).toString());
            }

            }

        } catch (SQLException ex) {
            System.out.println("Problème" + ex.getMessage());
        }

        return encheres;
    }
    
     public static ArrayList<Enchere> afficheEncheresDeMesObjets(Connection con, String emailuser) throws SQLException {
//méthode qui affiche les infos de toutes les enchères faites sur tous les objets déposés par un user
        ArrayList<Enchere> encheres = new ArrayList<>();
        con.setAutoCommit(false);
        try ( PreparedStatement searchobjet = con.prepareStatement(
                """
         select enchere.id, quand,montant,sur,de from enchere join objet 
         on enchere.sur = objet.id where objet.proposepar = (select id from utilisateur where email = ?)
                         order by quand asc
        
        """
        )) {

            searchobjet.setString(1, emailuser);
            ResultSet rs = searchobjet.executeQuery();
            System.out.println("voici l'ensemble des encheres faites sur cet objet:");

            while (rs.next()) {

                Enchere enchere = new Enchere(rs.getInt("id"), rs.getTimestamp("quand"),
                        rs.getInt("montant"), rs.getInt("sur"), rs.getInt("de"));
                encheres.add(enchere);
                 for (int i = 0; i < encheres.size(); i++) {
                    System.out.println(encheres.get(i).toString());
            }

            }

        } catch (SQLException ex) {
            System.out.println("Problème" + ex.getMessage());
        }

        return encheres;
    }


    public static boolean login(Connection con, String mail, String mdp) throws Exception { //choisir un utilisateur à partir de son nom et renvoyer ses infos

        con.setAutoCommit(false);
        try ( PreparedStatement searchuser = con.prepareStatement("select * from utilisateur where email=? and motdepasse = ?")) {

            searchuser.setString(1, mail);
            searchuser.setString(2, mdp);
            ResultSet testNom = searchuser.executeQuery();

            boolean exist = false;
            if (testNom.next()) {
                exist = true;

                if (exist == true) {

                    System.out.println("Login successful");

                } else {

                    throw new UtilisateurNexistePasException();
                }
            }

        } catch (Exception ex) {
            con.rollback();
            System.out.println("Problème" + ex.getMessage());
            return false;
        }
        return true;
    }

    public static ArrayList<Categorie> afficheallcats1(Connection con, String nomcatgen) throws SQLException {
//afficher les sous catégories en entrant une catégorie générale
        ArrayList<Categorie> categories = new ArrayList<>();
        con.setAutoCommit(false);
        try ( PreparedStatement searchcat = con.prepareStatement(
                """
         select categorie.nom, categorie.id, generale
                         from categoriegenerale join categorie on categoriegenerale.id = categorie.generale
                         where categoriegenerale.nom = ?
                         order by categoriegenerale.nom asc
        
        """
        )) {

            searchcat.setString(1, nomcatgen); 
            ResultSet rs = searchcat.executeQuery();

            System.out.println("Voici l'ensemble des sous-catégories recherchées:");

            while (rs.next()) {

                System.out.println("" + rs.getString("nom"));
                Categorie categorie = new Categorie(rs.getInt("id"), rs.getInt("generale"), rs.getString("nom"));
                categories.add(categorie);
                for (int i = 0; i < categories.size(); i++) {
                    System.out.println(categories.get(i).toString());
            }

        }
        }catch (SQLException ex) {
            System.out.println("Problème" + ex.getMessage());
        }

        return categories;
    }

    public static ArrayList<Categoriegenerale> afficheallcats2(Connection con) throws SQLException {
        //affiche toutes les catégories générales
        ArrayList<Categoriegenerale> categoriesgenerales = new ArrayList<>();
        con.setAutoCommit(false);
        try ( PreparedStatement searchcat = con.prepareStatement(
                """
         select * from categoriegenerale 
                        
                         order by categoriegenerale.nom asc
        
        """
        )) {

            ResultSet rs = searchcat.executeQuery();
            System.out.println("Voici l'ensemble des sous-catégories recherchées:");
            while (rs.next()) {

                System.out.println("Catégories générales" + rs.getString("nom"));
                Categoriegenerale categoriegenerale = new Categoriegenerale(rs.getInt("id"), rs.getString("nom"));
                categoriesgenerales.add(categoriegenerale);
                
                 for (int i = 0; i < categoriesgenerales.size(); i++) {
                    System.out.println(categoriesgenerales.get(i).toString());
            }
            }

        } catch (SQLException ex) {

            System.out.println("Problème" + ex.getMessage());
        }
        return categoriesgenerales;
    }

    public static void recreatebdd(Connection con) throws Exception {

        try {
            int u1 = createUtilisateur(con, "Espinola", "Sophia",
                    "sophia.espinola@insa-strasbourg.fr", "andorre", "67000");
            int u2 = createUtilisateur(con, "Amon", "Priscilla",
                    "prichou02@gmail.com", "lunaticboii", "37000");
            int cg1 = createCategorieGenerale(con, "Culture");
            int c1 = createCategorie(con, "Livres", cg1);
            int o1 = createObjet(con, "La fille de papier", "L'un des meilleurs romans de Guillaume Musso",
                    new Timestamp(System.currentTimeMillis()),
                    new Timestamp(System.currentTimeMillis() + 60 * 60 * 1000),
                    9, u1, cg1, c1);
            int e1 = createEnchere(con, new Timestamp(System.currentTimeMillis()), 10, o1, u2);

        } catch (Exception ex) {

            System.out.println("Problème" + ex.getMessage());
        }

    }
}



