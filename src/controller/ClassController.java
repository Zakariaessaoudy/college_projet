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
    public void ajouterClasse(Classe classe){
        daoClasse.ajouterClasse(classe);}
    public void supprimerClasse(Classe classe){
        daoClasse.supprimerClass(classe);}
    public List<Eleve> listeDesElevesDansClasse(){
        return daoClasse.listeDesElevesDansClasse();
    }
    public Classe TrouverClasseParNumero(int numeroClasse){
        Classe classe = daoClasse.TrouverClasseParNumero(numeroClasse);
        return classe;

    }

}