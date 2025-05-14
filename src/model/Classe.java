package model;

import java.util.ArrayList;
import java.util.List;
public class Classe {

    private int numeroClasse;
    private int NiveauScolaire;
    private boolean free = false;
    List<Eleve>eleves;
    List<Seance>seances;
    List<Absence> HistoriqueListesAbsence;
  private int[][] emploiDuTemps= new int [6][8];


    public Classe(int numeroClasse, int niveauScolaire) {
        this.numeroClasse = numeroClasse;
        NiveauScolaire = niveauScolaire;
        eleves = new ArrayList<>();
        seances= new ArrayList<>();
        HistoriqueListesAbsence=new ArrayList<>();
    }


    public void setNumeroClasse(int numeroClasse){
        this.numeroClasse=numeroClasse;
    }
    public int getNumeroClasse(){
        return numeroClasse;
    }

    public int getNiveauScolaire() {
        return NiveauScolaire;
    }

    public void setNiveauScolaire(int niveauScolaire) {
        NiveauScolaire = niveauScolaire;
    }
    public void ajouterSeance(Seance seance){
        if(!seances.contains(seance)){
            free=true;
            seances.add(seance);
        }
        else
            System.out.println("la seance est existe deja ");

    }
    public void supprimerSeance(Seance seance){
        if(seances.contains(seance)){
            seances.remove(seance);
        }
        else
            System.out.println("la seance n'existe pas");
    }
     public void afficheListeSeances(){
        int i=0;
        for( Seance s:seances){

            System.out.println(i++ +") la seance de jour : " + s.getNom_jour()+" et de la semaine : "+s.getNumero_Semaine());
        }
     }
     public void ajouterEleve(Eleve eleve){
        if(!eleves.contains(eleve)){
            eleves.add(eleve);
            System.out.println("l'eleve " +eleve.getNom()+" "+eleve.getPrenom()+" a bien ajouté");
        }
        else System.out.println("l'eleve " +eleve.getNom()+" "+eleve.getPrenom()+" est deja existe ");

     }
     public void supprimerEleve(Eleve eleve ){
        if(eleves.contains(eleve)){
            eleves.remove(eleve);
            System.out.println("l'eleve "+eleve.getNom()+"a bien supprimé");
        }
        else
            System.out.println("eleve n'exite pas");
     }
     public void afficherListeEleves(){
        int i=0;
        for(Eleve e:eleves){
            System.out.println(i++ +") "+e.toString());
        }
     }
    public void afficheListeAbsence(){
        for(Absence absence:HistoriqueListesAbsence){
            System.out.println(absence.toString());
        }

    }

}
