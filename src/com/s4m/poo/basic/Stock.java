package com.s4m.poo.basic;


public class Stock {

    private Ordinateur[] ordinateurs;
    private int nbOrdinateurs;

    // Constructeur
    public Stock(int taille) {
        ordinateurs = new Ordinateur[taille];
        nbOrdinateurs = 0;
    }

    // Méthode pour ajouter un ordinateur au stock si de la
    //place est disponible
    public void ajouterOrdinateur(Ordinateur o) {
        if (nbOrdinateurs < ordinateurs.length) {
            ordinateurs[nbOrdinateurs] = o;
            nbOrdinateurs++;
            System.out.println("Ordinateur ajouté avec succès");
        } else {
            System.out.println("Le stock est plein");
        }
    }

    // Méthode pour afficher tous les ordinateurs disponible dans le stock
    public void afficherStock() {
       // System.out.println("\n===== STOCK D'ORDINATEURS =====");
        System.out.println("Nombre d'ordinateurs : " + nbOrdinateurs);
        System.out.println("----------------------------\n");

        // On parcourt le tableau jusqu'à nbOrdinateurs
        for (int i = 0; i < nbOrdinateurs; i++) {
            System.out.println("Ordinateur numéro " + (i + 1) + " :");
            ordinateurs[i].afficherInfos();
        }
    }

    // Méthode pour rechercher un ordinateur par marque et modèle
    public Ordinateur rechercherOrdinateur(String marque, String modele) {
        // On parcourt tous les ordinateurs du stock
        for (int i = 0; i < nbOrdinateurs; i++) {
            // On compare la marque et le modèle
            if (ordinateurs[i].getMarque().equals(marque) &&
                    ordinateurs[i].getModele().equals(modele)) {
                // Si on trouve, on retourne l'ordinateur
                return ordinateurs[i];
            }
        }
        // Si on ne trouve pas, on retourne null
        return null;
    }

    // Méthode pour supprimer un ordinateur du stock
    public void supprimerOrdinateur(String marque, String modele) {
        //chercher l'ordinateur dans le stock
        for (int i = 0; i < nbOrdinateurs; i++) {
            // Si on trouve l'ordinateur
            if (ordinateurs[i].getMarque().equals(marque) &&
                    ordinateurs[i].getModele().equals(modele)) {
                // On décale tous les ordinateurs après celui-ci
                for (int j = i; j < nbOrdinateurs - 1; j++) {
                    ordinateurs[j] = ordinateurs[j + 1];
                }

                // On diminue le compteur
                nbOrdinateurs--;

                System.out.println("Ordinateur supprimé");
                return;
            }
        }
        // l'ordinateur non trouvé
        System.out.println("Ordinateur non trouvé");
    }
}
