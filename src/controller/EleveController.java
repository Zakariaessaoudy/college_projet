package controller;

import dao.DAOEleve;
import model.Eleve;

import java.util.List;

public class EleveController {
    private DAOEleve daoElve;
    public EleveController(){
        daoElve=new DAOEleve();
    }
    public boolean ajouterEleve(Eleve eleve){
        return daoElve.ajouterEleve(eleve);
    }
    public boolean supprimerEleve(String CNE){
        return daoElve.supprimerEleve(CNE);
    }
    public List<Eleve> tousEleves(){
        return daoElve.tousEleves();
    }
    public Eleve trouveEleveParCNE(String CNE){
        return daoElve.trouveEleveParCNE(CNE);
    }

}
