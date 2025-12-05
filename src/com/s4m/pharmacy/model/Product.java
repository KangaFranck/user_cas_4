package com.s4m.pharmacy.model;

import java.time.LocalDate;

/**
 * Représente un produit pharmaceutique
 */
public class Product {
    private int id;
    private String nom;
    private String description;
    private double prix;
    private int quantite;
    private LocalDate dateExpiration;
    private int idCategorie;
    
    public Product() {}
    
    public Product(String nom, String description, double prix, int quantite, 
                   LocalDate dateExpiration, int idCategorie) {
        this.nom = nom;
        this.description = description;
        this.prix = prix;
        this.quantite = quantite;
        this.dateExpiration = dateExpiration;
        this.idCategorie = idCategorie;
    }
    
    public Product(int id, String nom, String description, double prix, int quantite,
                   LocalDate dateExpiration, int idCategorie) {
        this.id = id;
        this.nom = nom;
        this.description = description;
        this.prix = prix;
        this.quantite = quantite;
        this.dateExpiration = dateExpiration;
        this.idCategorie = idCategorie;
    }
    
    public int getId() { return id; }
    public String getNom() { return nom; }
    public String getDescription() { return description; }
    public double getPrix() { return prix; }
    public int getQuantite() { return quantite; }
    public LocalDate getDateExpiration() { return dateExpiration; }
    public int getIdCategorie() { return idCategorie; }
    
    public void setId(int id) { this.id = id; }
    public void setNom(String nom) { this.nom = nom; }
    public void setDescription(String description) { this.description = description; }
    public void setPrix(double prix) { this.prix = prix; }
    public void setQuantite(int quantite) { this.quantite = quantite; }
    public void setDateExpiration(LocalDate dateExpiration) { this.dateExpiration = dateExpiration; }
    public void setIdCategorie(int idCategorie) { this.idCategorie = idCategorie; }
    
    /**
     * Vérifie si le stock est bas (quantité < 10)
     */
    public boolean isStockBas() { return quantite < 10; }
    
    @Override
    public String toString() {
        return "Produit [id=" + id + ", nom=" + nom + ", prix=" + prix + 
               ", quantite=" + quantite + ", dateExpiration=" + dateExpiration + "]";
    }
}
