package controller;

import dao.DAOEnseignant;
import model.Enseignant;
import java.util.List;

public class EnseignantController {
    private DAOEnseignant dao;

    public EnseignantController() {
        dao = new DAOEnseignant();
    }

    // Ajouter un enseignant
    public boolean ajouterEnseignant(Enseignant enseignant){
        return dao.ajouterEnseignant(enseignant);
    }

    // Supprimer un enseignant
    public boolean supprimerEnseignant(Enseignant e) {
        return dao.supprimerEnseignant(e);
    }

    // Modifier un enseignant
    public boolean modifierEnseignant(Enseignant e) {
        return dao.modifierEnseignant(e);
    }

    // Obtenir un enseignant par son ID
    public Enseignant getEnseignantParId(int id) {
        return dao.getEnseignantParId(id);
    }

    // Obtenir tous les enseignants
    public List<Enseignant> tousLesEnseignants() {
        return dao.tousLesEnseignants();
    }

    // Chercher des enseignants par leur nom
    public List<Enseignant> chercherParNom(String nom) {
        return dao.chercherParNom(nom);
    }

    // Vérifier si un enseignant existe par son nom
    public boolean enseignantExiste(String nom) {
        return dao.enseignantExiste(nom);
    }
}
