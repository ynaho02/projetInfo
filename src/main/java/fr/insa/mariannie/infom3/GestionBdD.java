/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.mariannie.infom3;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author alexa
 */
public class GestionBdD {
    
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
        return connectGeneralPostGres("localhost", 5439, "postgres", "postgres", "admin");

}
 public static void creeSchema(Connection con)
            throws SQLException {
        // je veux que le schema soit entierement créé ou pas du tout
        // je vais donc gérer explicitement une transaction
        con.setAutoCommit(false); // pk false??
        try ( Statement st = con.createStatement()) {
            // creation des tables
            st.executeUpdate(
                    """
                    create table javautilisateurs (
                    id integer not null primary key
                    generated always as identity,  -- c'est le sgbd qui affecte automatiquement les id
                    nom varchar(100) not null ,
                    prenom varchar (100) not null,
                    email varchar(300) not null unique,
                    pass varchar(100) not null,
                    codepostal varchar(200) not null
                    )
                    """
                   );
          
            st.executeUpdate(
                    """
                   create table javaencheres (
                    id integer not null primary key generated always as identity,
                    de integer not null,
                    sur integer not null,
                    montant varchar not null,
                    quand timestamp not null
                     )
                    """);
            
            st.executeUpdate(
                    """
                    create table javaobjets(
                             id integer not null primary key generated always as identity,
                             titre varchar(100) not null,
                             description text,
                             debut timestamp not null,
                             fin timestamp not null,
                             prixbase integer not null,
                             categorie integer not null,
                             proposepar integer not null
                        )
                             """); 
            
            st.executeUpdate(
                    """
                    create table javacategories(
                    id integer not null primary key generated always as identity,
                    nom varchar not null
                    )
                    """
            
            );
            // je defini les liens entre les clés externes et les clés primaires
            // correspondantes
             st.executeUpdate(
                    """
                    alter table javaobjets
                        add constraint fk_javaobjets_proposepar
                        foreign key (proposepar) references javautilisateurs(id)
                    
                    """);
              st.executeUpdate(
                    """
                    alter table javaobjets
                        add constraint fk_javaobjets_categorie
                        foreign key (categorie) references javacategories(id)
                    """);
            st.executeUpdate(
                    """
                    alter table javaencheres 
                        add constraint fk_javaencheres_de
                        foreign key (de) references javautilisateurs(id)
                   """);
            st.executeUpdate(
                    """
                    alter table javaencheres 
                        add constraint fk_javaencheres_sur
                        foreign key (sur) references javaobjets(id)
                   """);
//             si j'arrive jusqu'ici, c'est que tout s'est bien passé
            // je confirme (commit) la transaction
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
                    alter table javaobjets
                        drop constraint fk_javaobjets_proposepar
                             """);
                System.out.println("constraint fk_fdbaime_u1 dropped");
            } catch (SQLException ex) {
                // nothing to do : maybe the constraint was not created
            }
            try {
                st.executeUpdate(
                        """
                    alter table javaobjets
                        drop constraint fk_javaobjets_categorie
                    """);
                System.out.println("constraint fk_fdbaime_u2 dropped");
            } catch (SQLException ex) {
                // nothing to do : maybe the constraint was not created
            }
            try {
                st.executeUpdate(
                        """
                    alter table javaencheres
                        drop constraint fk_javaencheres_de
                    """);
                System.out.println("constraint fk_fdbutilisateur_role dropped");
            } catch (SQLException ex) {
                // nothing to do : maybe the constraint was not created
            }
            try {
                st.executeUpdate(
                        """
                    alter table javaencheres
                        drop constraint fk_javaencheres_sur
                             """);
                System.out.println("constraint fk_fdbaime_u1 dropped");
            } catch (SQLException ex) {
                // nothing to do : maybe the constraint was not created
            }
            // je peux maintenant supprimer les tables
            try {
                st.executeUpdate(
                        """
                    drop table javautilisateurs
                    """);
                System.out.println("table javautilsateurs dropped");
            } catch (SQLException ex) {
                // nothing to do : maybe the table was not created
            }
            try {
                st.executeUpdate(
                        """
                    drop table javaobjets
                    """);
                System.out.println("table javaobjets dropped");
            } catch (SQLException ex) {
                // nothing to do : maybe the table was not created
            }
            try {
                st.executeUpdate(
                        """
                    drop table javaencheres
                    """);
                System.out.println("table javaencheres dropped");
            } catch (SQLException ex) {
                // nothing to do : maybe the table was not created
            }
            try {
                st.executeUpdate(
                        """
                    drop table javacategories
                    """);
                System.out.println("table javacategories dropped");
            } catch (SQLException ex) {
                // nothing to do : maybe the table was not created
            }
            
        }
    }
 
    public static void main(String[] args) {
        try(Connection con = defautConnect()) {
            System.out.println("connection OK");
            creeSchema(con);
        } catch (ClassNotFoundException ex) {
            System.out.println("pas de driver");
        } catch (SQLException ex) {
            ex.printStackTrace();
        } 
    }
        public static class NomExisteDejaException extends Exception {
    }
    
  public static int createUtilisateur(Connection con, String nom, String prenom, String pass)
            throws SQLException, NomExisteDejaException {
        // je me place dans une transaction pour m'assurer que la séquence
        // test du nom - création est bien atomique et isolée
        con.setAutoCommit(false);
        try ( PreparedStatement chercheNom = con.prepareStatement(
                "select id from javautilsateurs where nom = ?")) {
            chercheNom.setString(1, nom);
            ResultSet testNom = chercheNom.executeQuery();
            if (testNom.next()) {
                throw new NomExisteDejaException();
            }
            // lors de la creation du PreparedStatement, il faut que je précise
            // que je veux qu'il conserve les clés générées
            try ( PreparedStatement pst = con.prepareStatement(
                    """
                insert into javautilsateurs (nom,prenom,pass) values (?,?,?)
                """, PreparedStatement.RETURN_GENERATED_KEYS)) {
               
                pst.setString(1, nom);
                pst.setString(2, prenom);
                pst.setString(3, pass);
                
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

  public static int createUtilisateurSimple(Connection con, String nom, String prenom, String pass)
            throws SQLException, NomExisteDejaException {
            try ( PreparedStatement pst = con.prepareStatement(
                    """
                insert into javautilsateurs (nom,prenom,pass) values (?,?,?)
                """, PreparedStatement.RETURN_GENERATED_KEYS)) {
               
                pst.setString(1, nom);
                pst.setString(2, prenom);
                pst.setString(3, pass);
                
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
    }

  public static void afficheTousLesUtilisateur(Connection con) throws SQLException {
        try ( Statement st = con.createStatement()) {
            // pour effectuer une recherche, il faut utiliser un "executeQuery"
            // et non un "executeUpdate".
            // un executeQuery retourne un ResultSet qui contient le résultat
            // de la recherche (donc une table avec quelques information supplémentaire)
            try ( ResultSet tlu = st.executeQuery("select id,nom,pass,role from javautilisateurs")) {
                // un ResultSet se manipule un peu comme un fichier :
                // - il faut le fermer quand on ne l'utilise plus
                //   d'où l'utilisation du try(...) ci-dessus
                // - il faut utiliser la méthode next du ResultSet pour passer
                //   d'une ligne à la suivante.
                //   . s'il y avait effectivement une ligne suivante, next renvoie true
                //   . si l'on était sur la dernière ligne, next renvoie false
                //   . au début, on est "avant la première ligne", il faut donc
                //     faire un premier next pour accéder à la première ligne
                //     Note : ce premier next peut renvoyer false si le résultat
                //            du select était vide
                // on va donc très souvent avoir un next
                //   . dans un if si l'on veut tester qu'il y a bien un résultat
                //   . dans un while si l'on veut traiter l'ensemble des lignes
                //     de la table résultat

                System.out.println("liste des utilisateurs :");
                System.out.println("------------------------");
                // ici, on veut lister toutes les lignes, d'où le while
                while (tlu.next()) {
                    // Ensuite, pour accéder à chaque colonne de la ligne courante,
                    // on a les méthode getInt, getString... en fonction du type
                    // de la colonne.

                    // on peut accéder à une colonne par son nom :
                    int id = tlu.getInt("id");
                    // ou par son numéro (la première colonne a le numéro 1)
                    String nom = tlu.getString(2);
                    String prenom = tlu.getString(3);
                    String pass = tlu.getString("pass");
                    String email = tlu.getString(4);
                    String codepostal = tlu.getString(6);
                    
                }
            }
        }

    }
   
       public static void createNvObjet(Connection con) throws SQLException {
        try ( PreparedStatement cr = con.prepareStatement(
                "insert into javautilisateurs (id,titre) values (?,?)")) {
            // role user
            cr.setInt(1, 2);
            cr.executeUpdate();
            System.out.println("nv objet créée");
        }
    }
/*      public static List<Utilisateur> tousLesUtilisateurs(Connection con) throws SQLException {
        List<Utilisateur> res = new ArrayList<>();
        try ( PreparedStatement pst = con.prepareStatement(
                "select fdbutilisateur.id as uid,nom,pass,nrole"
                + " from fdbutilisateur "
                + "   join fdbrole on fdbutilisateur.role = fdbrole.id"
                + " order by nom asc")) {

            try ( ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    res.add(new Utilisateur(rs.getInt("uid"),
                            rs.getString("nom"), rs.getString("pass"),
                            rs.getString("nrole")));
                }
                return res;
            }
        }
  }*/

       
}
    
 