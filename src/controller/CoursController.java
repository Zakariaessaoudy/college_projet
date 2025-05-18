package controller;

import dao.DAOCours;
import model.Cours;

import java.util.List;

public class CoursController {

    private DAOCours daoCours;

    public CoursController() {
        daoCours = new DAOCours();
    }

    // Ajouter un cours
    public boolean ajouterCours(Cours cours) {
        return daoCours.ajouterCours(cours);
    }
    //  Modifier un cours
    public boolean modifierCours(Cours cours) {
        return daoCours.modifierCours(cours);
    }

    //  Supprimer un cours
    public boolean supprimerCours(int idCours) {
        Cours cours = new Cours();
        cours.setIdCours(idCours);
        return daoCours.supprimerCours(cours);
    }

    //  Vérifier si un cours existe par nom
    public boolean coursExiste(String nomCours) {
        return daoCours.coursExiste(nomCours);
    }
    //  Récupérer tous les cours
    public List<Cours> tousLesCours() {
        return daoCours.tousCours();
    }

    //  Récupérer les cours d’un enseignant par son id
    public List<Cours> coursParEnseignant(int idEnseignant) {
        return daoCours.avoirCoursParIdEnseignant(idEnseignant);
    }

}