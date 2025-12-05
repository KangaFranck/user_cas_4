package com.s4m.pharmacy.service;

import com.s4m.pharmacy.model.User;
import com.s4m.pharmacy.util.PasswordHasher;

/**
 * Service d'authentification pour gérer la connexion et déconnexion
 */
public class AuthService {
    
    private UserService userService;
    private User utilisateurConnecte;
    
    public AuthService() {
        this.userService = new UserService();
        this.utilisateurConnecte = null;
    }
    
    /**
     * Authentifie un utilisateur avec son email et mot de passe
     */
    public boolean seConnecter(String email, String motDePasse) {
        if (email == null || email.isEmpty() || motDePasse == null || motDePasse.isEmpty()) {
            return false;
        }
        
        User user = userService.getUtilisateurParEmail(email);
        if (user == null) return false;
        
        if (PasswordHasher.hashPassword(motDePasse).equals(user.getMotDePasse())) {
            this.utilisateurConnecte = user;
            return true;
        }
        return false;
    }
    
    /**
     * Déconnecte l'utilisateur actuel
     */
    public void seDeconnecter() {
        this.utilisateurConnecte = null;
    }
    
    /**
     * Vérifie si un utilisateur est connecté
     */
    public boolean estConnecte() {
        return utilisateurConnecte != null;
    }
    
    /**
     * Retourne l'utilisateur actuellement connecté
     */
    public User getUtilisateurConnecte() {
        return utilisateurConnecte;
    }
    
    /**
     * Vérifie si l'utilisateur actuel est administrateur
     */
    public boolean estAdmin() {
        return utilisateurConnecte != null && utilisateurConnecte.isAdmin();
    }
}
