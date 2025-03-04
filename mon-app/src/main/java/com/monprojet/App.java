package com.monprojet;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class App {
    public static void main(String[] args) {
        // Informations de connexion
        String url = "jdbc:mysql://localhost:3306/mabase"; // Remplacer "mabase" par le nom de votre base
        String utilisateur = "root";
        String motDePasse = "";

        System.out.println("Hello World!");

        // Création d'une instance de Connexion
        Connexion connexion = new Connexion(url, utilisateur, motDePasse);
        Connection conn = connexion.getConnection();
     
        
        // Utilisation de GestionUtilisateurs
        if (conn != null) {
            GestionUtilisateurs gestionUtilisateurs = new GestionUtilisateurs(conn);
            gestionUtilisateurs.afficherMenu();
        } else {
            System.out.println("Impossible d'établir la connexion à la base de données.");
        }
    }
}