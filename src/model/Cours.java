package model;

public class Cours {
    private int idCours ;
    private String nom ;
    private String type ;
    private Enseignant enseignant ;

    public Cours(int idCours , String nom, String type , Enseignant enseignant) {
        this.idCours= idCours ;
        this.nom = nom;
        this.type = type;
        this.enseignant = enseignant ;
    }

    public Cours(int idCours , String nom, String type ) {
        this.idCours= idCours ;
        this.nom = nom;
        this.type = type;
    }

    public Cours() {

    }


    public int getIdCours(){return idCours ;}
    public void setIdCours(int idCours){ this.idCours= idCours ;}
    public String getNom() {
        return nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public Enseignant getEnseignant() {
        return enseignant;
    }
    public void setEnseignant(Enseignant enseignant) {
        this.enseignant = enseignant;
    }

    public String getDescription() {
        if (enseignant != null) {
            return "Cours identifié par " +idCours +" de " + nom + " (" + type + ") assuré par "
                    + enseignant.getPrenom() + " " + enseignant.getNom() + ".";
        } else {
            return "Cours de " + nom + " (" + type + ") sans enseignant affecté.";
        }
    }

    public boolean estAssurePar(String nomEnseignant) {
        if (enseignant == null) {
            return false;
        }
        return enseignant.getNom().equalsIgnoreCase(nomEnseignant);
    }

}