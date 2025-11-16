package com.s4m.poo.basic;


public class Ordinateur {

    private String marque;
    private String modele;
    private String processeur;
    private int ram;
    private int stockage;

    //CONSTRUCTEURS
    public Ordinateur(String marque, String modele, String processeur, int ram, int stockage) {
        this.marque = marque;
        this.modele = modele;
        this.processeur = processeur;
        this.ram = ram;
        this.stockage = stockage;
    }

    // GETTERS
    public String getMarque() {
        return marque;
    }

    public String getModele() {
        return modele;
    }

    public String getProcesseur () {
        return processeur;
    }

    public int getRam() {
        return ram;
    }

    public int getStockage() {
        return stockage;
    }

    // SETTERS
    public void setMarque(String marque) {
        this.marque = marque;
    }

    public void setModele(String modele) {
        this.modele = modele;
    }

    public void setProcesseur(String processeur) {
        this.processeur = processeur;
    }

    public void setRam(int ram) {
        this.ram = ram;
    }

    public void setStockage(int stockage) {
        this.stockage = stockage;
    }

    // Méthode pour afficher  les détails d'un ordinateur
    public void afficherInfos() {
        System.out.println("Marque : " + marque);
        System.out.println("Modèle : " + modele);
        System.out.println("Processeur : " + processeur);
        System.out.println("RAM : " + ram + " Go");
        System.out.println("Stockage : " + stockage + " Go");
        System.out.println("----------------------------");
    }
}