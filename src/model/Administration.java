package model;

import java.sql.Time;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Administration {
    /*private int Numero;
    private String type;*/ 
    College college;
    //List<Seance> lesSeancesActuel;
    List<Seance> historiqueDesSeances;
    Map<Integer,Classe> lesClasses;
    //methode pour penaliser eleves (envoyer des couriers). do you see me?
    public Administration(College college) {
        this.college = college;
        //lesSeancesActuel = new ArrayList<Seance>();
        historiqueDesSeances = new ArrayList<Seance>();
        lesClasses  = new HashMap<Integer,Classe>();
    }

    public void ajouterClasse(Classe classe){
        lesClasses.put(classe.getNumeroClasse(),classe);
    }

    public void retirerClasse(int numeroClasse){
        if (lesClasses.remove(numeroClasse) == null) {
            System.out.println("classe non trouvee");
        }
    }

    public void ajouterSeance(Seance seance){
        historiqueDesSeances.add(seance);
    }

    public void afficherLesSeancesEnCours(){
        for (int i = 0; i < historiqueDesSeances.size(); i++) {
            if (Time.valueOf(LocalTime.now()).after(historiqueDesSeances.get(i).getDebut()) && Time.valueOf(LocalTime.now()).before(historiqueDesSeances.get(i).getFin())) {
                System.out.println(historiqueDesSeances.get(i).toString());
            }
        }
    }

    public void afficherHistoriqueDesSeances(){
        for (int i = 0; i < historiqueDesSeances.size(); i++) {
            System.out.println(historiqueDesSeances.get(i).toString());
        }
    }

}
