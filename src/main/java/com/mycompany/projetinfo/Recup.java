package com.mycompany.projetinfo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author ynaho01
 */
public class Recup {
    
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
 
 public static void access(Connection con) throws SQLException{
   
     try{
         
         Statement st = con.createStatement();
         ResultSet result = st.executeQuery("Select * from utilisateur");

         while(result.next()){
             
             System.out.println("id: " +result.getInt("id"));
             System.out.println("Nom: " +result.getString("nom"));
             System.out.println("Prenom: " +result.getString("prenom"));
             
         }
     }catch (SQLException ex)  {
            throw new Error(ex);
        }
     
     
     
 }
    
}
