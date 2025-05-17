package controller;

import dao.DAOClasse;
import model.Classe;
import model.Eleve;

import java.util.List;

public class ClassController {
    private DAOClasse daoClasse;

    public ClassController(){
        daoClasse=new DAOClasse();
    }
    public boolean ajouterClasse(Classe classe){

        return  daoClasse.ajouterClasse(classe);
    }
    public boolean supprimerClasse(Classe classe){
        return daoClasse.supprimerClass(classe);}
    public List<Eleve> listeDesElevesDansClasse(){
        return daoClasse.listeDesElevesDansClasse();
    }
    public Classe TrouverClasseParNumero(int numeroClasse){
        Classe classe = daoClasse.TrouverClasseParNumero(numeroClasse);
        return classe;

    }
    public void AllClass(){
        daoClasse.AllClass();
}
}