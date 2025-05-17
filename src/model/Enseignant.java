package model;

import java.util.ArrayList;
import java.util.List;

public class Enseignant {
    private String nom ;
    private String prenom ;
    private int identifiant ;
    private String email ;
    private String numeroTelephone ;
    private List<Cours> cours ;



    public String getNom() {
        return nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }
    public String getPrenom() {
        return prenom;
    }
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
    public int getIdentifiant() {
        return identifiant;
    }
    public void setIdentifiant(int identifiant) {
        this.identifiant = identifiant;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getNumeroTelephone() {
        return numeroTelephone;
    }
    public void setNumeroTelephone(String numeroTelephone) {
        this.numeroTelephone = numeroTelephone;
    }

    public Enseignant(int identifiant , String nom, String prenom, String email,
                      String numeroTelephone) {
        this.nom = nom;
        this.prenom = prenom;
        this.identifiant = identifiant;
        this.email = email;
        this.numeroTelephone = numeroTelephone;
        cours = new ArrayList<>();
    }

    public List<Cours> obtenirListeCours() {
        return cours;
    }

}