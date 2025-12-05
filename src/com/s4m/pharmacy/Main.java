package com.s4m.pharmacy;

import com.s4m.pharmacy.db.DatabaseConnection;

/**
 * Point d'entrée de l'application
 * Initialise la base de données au démarrage
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("=== Initialisation de la base de données ===");
        DatabaseConnection.initialiser();
        System.out.println("\n=== Application prête ===");
    }
}
