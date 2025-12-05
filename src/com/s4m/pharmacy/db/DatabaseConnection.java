package com.s4m.pharmacy.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Gère la connexion à la base de données MySQL et son initialisation
 */
public class DatabaseConnection {
    
    private static final String URL = "jdbc:mysql://localhost:3306/pharmacy_db?useSSL=false&serverTimezone=UTC&characterEncoding=utf8mb4";
    private static final String URL_SANS_DB = "jdbc:mysql://localhost:3306?useSSL=false&serverTimezone=UTC&characterEncoding=utf8mb4";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";
    
    public DatabaseConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Retourne une connexion à la base de données
     */
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }
    
    /**
     * Initialise la base de données : crée la BD, les tables et insère les données par défaut
     */
    public static void initialiser() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            try (Connection conn = DriverManager.getConnection(URL_SANS_DB, USERNAME, PASSWORD);
                 Statement stmt = conn.createStatement()) {
                
                stmt.executeUpdate("CREATE DATABASE IF NOT EXISTS pharmacy_db CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci");
                System.out.println("Base de données 'pharmacy_db' créée ou déjà existante");
            }
            
            try (Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
                 Statement stmt = conn.createStatement()) {
                
                stmt.executeUpdate("CREATE TABLE IF NOT EXISTS Categorie (" +
                        "id INT PRIMARY KEY AUTO_INCREMENT, " +
                        "nom VARCHAR(100) NOT NULL UNIQUE, " +
                        "description TEXT, " +
                        "created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP, " +
                        "updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP" +
                        ") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci");
                
                stmt.executeUpdate("CREATE TABLE IF NOT EXISTS Utilisateur (" +
                        "id INT PRIMARY KEY AUTO_INCREMENT, " +
                        "nom VARCHAR(100) NOT NULL, " +
                        "email VARCHAR(150) NOT NULL UNIQUE, " +
                        "mot_de_passe VARCHAR(255) NOT NULL, " +
                        "role ENUM('ADMIN', 'USER') NOT NULL DEFAULT 'USER', " +
                        "created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP, " +
                        "updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP" +
                        ") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci");
                
                stmt.executeUpdate("CREATE TABLE IF NOT EXISTS Produit (" +
                        "id INT PRIMARY KEY AUTO_INCREMENT, " +
                        "nom VARCHAR(150) NOT NULL, " +
                        "description TEXT, " +
                        "prix DECIMAL(10, 2) NOT NULL CHECK (prix >= 0), " +
                        "quantite INT NOT NULL DEFAULT 0 CHECK (quantite >= 0), " +
                        "date_expiration DATE NOT NULL, " +
                        "id_categorie INT NOT NULL, " +
                        "created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP, " +
                        "updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP, " +
                        "FOREIGN KEY (id_categorie) REFERENCES Categorie(id) ON DELETE RESTRICT ON UPDATE CASCADE, " +
                        "INDEX idx_nom (nom), INDEX idx_date_expiration (date_expiration), INDEX idx_categorie (id_categorie)" +
                        ") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci");
                
                System.out.println("Tables créées ou déjà existantes");
                insererDonneesParDefaut(conn);
            }
            
            System.out.println("Initialisation terminée avec succès !");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Insère les données par défaut si les tables sont vides
     */
    private static void insererDonneesParDefaut(Connection conn) {
        try (Statement stmt = conn.createStatement()) {
            var rs = stmt.executeQuery("SELECT COUNT(*) FROM Categorie");
            if (rs.next() && rs.getInt(1) == 0) {
                stmt.executeUpdate("INSERT INTO Categorie (nom, description) VALUES " +
                        "('Antibiotiques', 'Médicaments pour traiter les infections bactériennes'), " +
                        "('Analgésiques', 'Médicaments pour soulager la douleur'), " +
                        "('Vitamines', 'Compléments vitaminiques et minéraux'), " +
                        "('Antihistaminiques', 'Médicaments pour les allergies'), " +
                        "('Antiseptiques', 'Produits pour désinfecter et nettoyer')");
                System.out.println("Catégories par défaut insérées");
            }
            
            rs = stmt.executeQuery("SELECT COUNT(*) FROM Utilisateur");
            if (rs.next() && rs.getInt(1) == 0) {
                stmt.executeUpdate("INSERT INTO Utilisateur (nom, email, mot_de_passe, role) VALUES " +
                        "('Administrateur', 'admin@pharmacy.com', '240be518fabd2724ddb6f04eeb1da5967448d7e831c08c8fa822809f74c720a9', 'ADMIN'), " +
                        "('Assistant', 'user@pharmacy.com', '240be518fabd2724ddb6f04eeb1da5967448d7e831c08c8fa822809f74c720a9', 'USER')");
                System.out.println("Utilisateurs par défaut insérés (admin@pharmacy.com / admin123)");
            }
            
            rs = stmt.executeQuery("SELECT COUNT(*) FROM Produit");
            if (rs.next() && rs.getInt(1) == 0) {
                stmt.executeUpdate("INSERT INTO Produit (nom, description, prix, quantite, date_expiration, id_categorie) VALUES " +
                        "('Amoxicilline 500mg', 'Antibiotique à large spectre', 15.50, 25, '2025-12-31', 1), " +
                        "('Paracétamol 500mg', 'Antalgique et antipyrétique', 3.20, 150, '2026-06-30', 2), " +
                        "('Vitamine D3', 'Complément en vitamine D', 8.75, 45, '2025-10-15', 3), " +
                        "('Ibuprofène 400mg', 'Anti-inflammatoire non stéroïdien', 4.50, 8, '2025-09-20', 2), " +
                        "('Loratadine 10mg', 'Antihistaminique', 6.30, 30, '2026-03-15', 4)");
                System.out.println("Produits de test insérés");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
