package mypackage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Connexion {
    
    // Méthode de connexion
    public static Connection getConnection() {
        Connection con = null;
        try {
            con = DriverManager.getConnection("jdbc:mysql:///bdd_criee","root","");
            System.out.println("La connexion à la base de données a réussi.");
        } catch (SQLException e) {
            System.out.println("Problème de connexion à la base de donnée.");
        }
        return con;
    }
}
