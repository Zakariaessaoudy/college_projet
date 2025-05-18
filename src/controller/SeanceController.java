package controller;

import dao.DAOSeance;
import model.Seance;

import java.util.Vector;

public class SeanceController {
    private DAOSeance daoSeance ;
    public SeanceController(){
        daoSeance=new DAOSeance();
    }
    public boolean ajouterSeance(Seance seance){
       return daoSeance.ajouterSeance(seance);
}
    public boolean supprimerSeance(int numeroSalle,int idCours){
        return daoSeance.supprimerSeance( numeroSalle,idCours);
    }
    public Vector<Seance> TousSeances(){
        return daoSeance.tousSeances();
    }
}
