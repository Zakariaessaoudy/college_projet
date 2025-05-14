package model;

import java.time.DayOfWeek;
import java.util.*;
import java.io.*;
public class Absence {

    private DayOfWeek jour;
    private int numeroSemaine;
    private Classe classe;
    private Map<Eleve, List <StatusPresence>> listeAbscence ;


    //methode pour remplir la liste d'absence avec les eleves du classe
    public void initialiserListe(){
        this.listeAbscence = new HashMap<>(this.classe.eleves.size());
        for(Eleve eleve: this.classe.eleves){
            this.listeAbscence.put(eleve,new ArrayList<StatusPresence>(8));
        }
    }

    //constructeur
    public Absence(DayOfWeek jour, int numeroSemaine, Classe classe) {
        this.jour = jour;
        this.numeroSemaine = numeroSemaine;
        this.classe = classe;
        initialiserListe();
    }

    //methode pour faire l'appel aux eleves et marquer leurs abscense dans la vie reel
    //cette methode doit avoir (pas necessairement) une interface graphique pour marquer l'absence des eleves
    //l'ordre designe a quel heure l'enseignant effectue cette simulation

    public void marquerAbsenceReel(){
        StatusPresence status = null;
        Scanner S = new Scanner(System.in);
        System.out.println("veuillez entrez l'ordre du session courante:");
        int ordre = S.nextInt();
        while(ordre>8||ordre<1){
            System.out.println("valeur d'ordre invalid, veuillez ressayer:");
            ordre=S.nextInt();
        }
        //initialiserListe();
        for (Map.Entry<Eleve,List<StatusPresence>> entry :
             this.listeAbscence.entrySet()) {
            Eleve eleve = entry.getKey();
            List<StatusPresence> jourAbsence = entry.getValue();
            while(status == null){
                System.out.println("status de l'eleve : "+eleve.getNom()+" (tapez P ou A Majuscules)");
                String St = S.nextLine().trim().toUpperCase();

                try{
                    status = StatusPresence.valueOf(St);
                    System.out.println("Statut enregistré : " + status);
                }catch(IllegalArgumentException e){
                    System.out.println("Valeur invalide! veuillez ressayer");
                }
            }
            if(jourAbsence.get(ordre)==StatusPresence.E){
                System.out.println("l'eleve : "+eleve.getNom()+" a un excuse");
            }
            else jourAbsence.set(ordre,status);
        }
    }

    //methode qui simule l'operation de marquage de presence
    public void simulerMarquerAbsence(int ordre){
        if(ordre<=8&&ordre>=1){
            initialiserListe();
            for (Map.Entry<Eleve,List<StatusPresence>> entry :
                    this.listeAbscence.entrySet()) {
                Eleve eleve = entry.getKey();
                List<StatusPresence> jourAbsence = entry.getValue();
                if(jourAbsence.get(ordre)==StatusPresence.E){
                    System.out.println("l'eleve : "+eleve.getNom()+" a un excuse");
                }
                else {
                    StatusPresence[] status ={StatusPresence.A,StatusPresence.P} ;
                    Random random = new Random();
                    jourAbsence.set(ordre, status[random.nextInt(status.length)]);
                }
            }
        }else{
            System.out.println("valeur d'ordre invalid, veuillez ressayer:");
        }
    }


    //methode qui affecte une excuse a une liste des eleves pour une heure specifique au cours du jour
    //cette methode fail l'objectif d'affecter les excuses aux eleves manuellement via une liste donnee au parametres
    public void marquerExcuses(List<Eleve> elevesExcusees, int ordreHeure){
        if(ordreHeure>8||ordreHeure<1){
            for(Eleve eleve: elevesExcusees){
                List<StatusPresence> jourAbsence = this.listeAbscence.get(eleve);
                if(jourAbsence == null){
                    System.out.println("l'eleve : "+ eleve+" ne figure pas dans la liste de cette classe ");
                }else {
                    jourAbsence.set(ordreHeure,StatusPresence.E);
                }
            }
        }
        else{
            System.out.println("valeur d'ordre invalid, veuillez ressayer:");
        }
    }


    //cette methode fait l'objectif d'affecter les excuses a des eleves aleatoires au cours de la jour .
    public void simulerMarquerExcuses(){
        List<Eleve> elevesExcusees = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < random.nextInt(8)+1; i++) {
            //extraire une liste des eleves excuses aleatoirement du classe
            elevesExcusees.add(this.classe.eleves.get(random.nextInt(this.classe.eleves.size())+1));
            marquerExcuses(elevesExcusees,i);
        }
    }




    //methode pour afficher la liste d'absence
    public void afficher(){
        System.out.println("semaine : "+getNumeroSemaine());
        System.out.println("jour : "+ getJour());
        System.out.println("Nom de l'élève         | H1 | H2 | H3 | H4 | H5 | H6 | H7 | H8 |");
        System.out.println("-----------------------+----------------------------------------");
        for (Map.Entry<Eleve, List<StatusPresence>> entry : this.listeAbscence.entrySet()) {
            Eleve eleve = entry.getKey();
            List<StatusPresence> statusList = entry.getValue();
            System.out.printf("%-24s|", eleve.getNom());
            for (StatusPresence s : statusList) {
                System.out.print(" " + s+"  |");
            }
            System.out.println("-----------------------+----------------------------------------");
        }

    }


    //getters and setters
    public DayOfWeek getJour() {
        return jour;
    }

    public void setJour(DayOfWeek jour) {
        this.jour = jour;
    }

    public int getNumeroSemaine() {
        return numeroSemaine;
    }

    public void setNumeroSemaine(int numeroSemaine) {
        this.numeroSemaine = numeroSemaine;
    }

    public Classe getClasse() {
        return classe;
    }

    public void setClasse(Classe classe) {
        this.classe = classe;
    }

    public Map<Eleve, List<StatusPresence>> getListeAbscence() {
        return listeAbscence;
    }

    public void setListeAbscence(Map<Eleve, List<StatusPresence>> listeAbscence) {
        this.listeAbscence = listeAbscence;
    }


}
