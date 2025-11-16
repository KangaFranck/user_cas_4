package com.s4m.poo.basic;


public class TestStock {

    public static void main(String[] args) {

        // Créer des ordinateurs

        Ordinateur ord1 = new Ordinateur("Dell", "XPS", "Intel i7", 16, 512);
        Ordinateur ord2 = new Ordinateur("HP", "Pavilion", "Intel i5", 8, 256);
        Ordinateur ord3 = new Ordinateur("Lenovo", "ThinkPad", "Intel i7", 16, 1024);

        // Créer un stock

        Stock monStock = new Stock(5);  // Stock qui peut contenir 5 ordinateurs

        // Ajouter les ordinateurs dans le stock


        monStock.ajouterOrdinateur(ord1);
        monStock.ajouterOrdinateur(ord2);
        monStock.ajouterOrdinateur(ord3);

        // Afficher le stock

        monStock.afficherStock();

        // Rechercher un ordinateur


        Ordinateur recherche = monStock.rechercherOrdinateur("HP", "Pavilion");

        if (recherche != null) {
            System.out.println("Ordinateur trouvé !");
            recherche.afficherInfos();
        } else {
            System.out.println("Ordinateur non trouvé");
        }

        //  Supprimer un ordinateur

        monStock.supprimerOrdinateur("Dell", "XPS");

        //  Afficher le stock après suppression

        monStock.afficherStock();

    }
}
