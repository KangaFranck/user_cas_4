package com.s4m.pharmacy;

import com.s4m.pharmacy.model.Category;
import com.s4m.pharmacy.model.Product;
import com.s4m.pharmacy.model.User;
import java.time.LocalDate;

public class TestPharmacy {
    public static void main(String[] args) {
        System.out.println("=== TEST DES CLASSES PHARMACY ===\n");
        
        System.out.println("1. TEST CATEGORIE");
        Category cat1 = new Category("Antibiotiques", "Médicaments pour infections");
        cat1.setId(1);
        System.out.println("Catégorie créée : " + cat1);
        System.out.println("ID : " + cat1.getId() + ", Nom : " + cat1.getNom() + "\n");
        
        System.out.println("2. TEST PRODUIT");
        Product prod1 = new Product("Paracétamol 500mg", "Antalgique", 3.50, 100, 
                                    LocalDate.of(2026, 12, 31), 1);
        prod1.setId(1);
        System.out.println("Produit créé : " + prod1);
        System.out.println("Stock bas ? " + prod1.isStockBas());
        
        Product prod2 = new Product();
        prod2.setId(2);
        prod2.setNom("Amoxicilline 500mg");
        prod2.setPrix(15.75);
        prod2.setQuantite(5);
        prod2.setDateExpiration(LocalDate.of(2025, 6, 30));
        System.out.println("Produit 2 : " + prod2);
        System.out.println("Stock bas ? " + prod2.isStockBas() + "\n");
        
        System.out.println("3. TEST UTILISATEUR");
        User user1 = new User("Ahmed", "ahmed@pharmacy.com", "password123", User.Role.ADMIN);
        user1.setId(1);
        System.out.println("Utilisateur créé : " + user1);
        System.out.println("Est admin ? " + user1.isAdmin());
        
        User user2 = new User(2, "Fatima", "fatima@pharmacy.com", "pass456", User.Role.USER);
        System.out.println("Utilisateur 2 : " + user2);
        System.out.println("Est admin ? " + user2.isAdmin());
    }
}
