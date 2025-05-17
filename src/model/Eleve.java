package model;

public class Eleve {
    private String cne;
    private String nom;
    private String prenom;
    private String adresse;
    private String emailParent;
    private String email;
    private Classe classe;
    public Eleve(){}
    public Eleve(String cne, String nom, String prenom, String adresse, String emailParent, String email,Classe classe) {
        this.cne = cne;
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.emailParent = emailParent;
        this.email = email;
        this.classe=classe;
    }

    public String getCne() {
        return cne;
    }

    public void setCne(String cne) {
        this.cne = cne;
    }

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

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getEmailParent() {
        return emailParent;
    }

    public void setEmailParent(String emailParent) {
        this.emailParent = emailParent;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Classe getClasse() {
        return classe;
    }

    public void setClasse(Classe classe) {
        this.classe = classe;
    }

    @Override
    public String toString() {
        return "Eleve{" +
                "cne='" + cne + '\'' +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", adresse='" + adresse + '\'' +
                ", emailParent='" + emailParent + '\'' +
                ", email='" + email + '\'' +
                ", classe=" + classe +
                '}';
    }

}