package com.monprojet;

import java.sql.*;
import java.util.Scanner;

public class GestionUtilisateurs {
    private Connection connection;

    public GestionUtilisateurs(Connection connection) {
        this.connection = connection;
    }

    public void afficherMenu() {
        Scanner scanner = new Scanner(System.in);
        int choix;
        do {
            System.out.println("\nMenu:");
            System.out.println("1. Ajouter un utilisateur");
            System.out.println("2. Lister les utilisateurs");
            System.out.println("3. Supprimer un utilisateur");
            System.out.println("4. Modifier un utilisateur");
            System.out.println("5. Rechercher un utilisateur");
            System.out.println("6. Quitter");
            System.out.print("Votre choix : ");
            choix = scanner.nextInt();
            scanner.nextLine();

            switch (choix) {
                case 1:
                    ajouterUtilisateur(scanner);
                    break;
                case 2:
                    listerUtilisateurs();
                    break;
                case 3:
                    supprimerUtilisateur(scanner);
                    break;
                case 4:
                    modifierUtilisateur(scanner);
                    break;
                case 5:
                    rechercherUtilisateur(scanner);
                    break;
                case 6:
                    System.out.println("Au revoir !");
                    break;
                default:
                    System.out.println("Choix invalide !");
            }
        } while (choix != 6);
        scanner.close();
    }

    public void ajouterUtilisateur(Scanner scanner) {
        System.out.print("Nom : ");
        String nom = scanner.nextLine();
        System.out.print("Email : ");
        String email = scanner.nextLine();

        String sql = "INSERT INTO utilisateurs (nom, email) VALUES (?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, nom);
            stmt.setString(2, email);
            stmt.executeUpdate();
            System.out.println("Utilisateur ajouté avec succès.");
        } catch (SQLException e) {
            System.out.println("Erreur lors de l'ajout : " + e.getMessage());
        }
    }

    public void listerUtilisateurs() {
        String sql = "SELECT * FROM utilisateurs";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id") + ", Nom: " + rs.getString("nom") + ", Email: " + rs.getString("email"));
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la récupération : " + e.getMessage());
        }
    }

    public void supprimerUtilisateur(Scanner scanner) {
        System.out.print("ID de l'utilisateur à supprimer : ");
        int id = scanner.nextInt();

        String sql = "DELETE FROM utilisateurs WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            int rows = stmt.executeUpdate();
            if (rows > 0) {
                System.out.println("Utilisateur supprimé.");
            } else {
                System.out.println("Aucun utilisateur avec cet ID.");
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la suppression : " + e.getMessage());
        }
    }

    public void modifierUtilisateur(Scanner scanner) {
        System.out.print("ID de l'utilisateur à modifier : ");
        int id = scanner.nextInt();
        scanner.nextLine();  // consume the newline character
        System.out.print("Nouveau nom : ");
        String nom = scanner.nextLine();
        System.out.print("Nouvel email : ");
        String email = scanner.nextLine();

        String sql = "UPDATE utilisateurs SET nom = ?, email = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, nom);
            stmt.setString(2, email);
            stmt.setInt(3, id);
            int rows = stmt.executeUpdate();
            if (rows > 0) {
                System.out.println("Utilisateur modifié.");
            } else {
                System.out.println("Aucun utilisateur avec cet ID.");
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la modification : " + e.getMessage());
        }
    }

    public void rechercherUtilisateur(Scanner scanner) {
        System.out.println("Rechercher par : ");
        System.out.println("1. Nom");
        System.out.println("2. Email");
        int choix = scanner.nextInt();
        scanner.nextLine();  // consommer le caractère de nouvelle ligne
    
        String sql = "";
        if (choix == 1) {
            System.out.print("Nom : ");
            String nom = scanner.nextLine();
            sql = "SELECT * FROM utilisateurs WHERE nom LIKE ?";
        } else if (choix == 2) {
            System.out.print("Email : ");
            String email = scanner.nextLine();
            sql = "SELECT * FROM utilisateurs WHERE email LIKE ?";
        } else {
            System.out.println("Choix invalide !");
            return;  // sort de la méthode si le choix est invalide
        }
    
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            if (choix == 1) {
                stmt.setString(1, "%" + scanner.nextLine() + "%"); // Recherche partielle sur le nom
            } else if (choix == 2) {
                stmt.setString(1, "%" + scanner.nextLine() + "%"); // Recherche partielle sur l'email
            }
    
            try (ResultSet rs = stmt.executeQuery()) {
                boolean utilisateurTrouve = false;
                while (rs.next()) {
                    System.out.println("ID: " + rs.getInt("id") + ", Nom: " + rs.getString("nom") + ", Email: " + rs.getString("email"));
                    utilisateurTrouve = true;
                }
    
                if (!utilisateurTrouve) {
                    System.out.println("Aucun utilisateur trouvé avec ces critères.");
                }
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la recherche : " + e.getMessage());
        }
    }
    
}
