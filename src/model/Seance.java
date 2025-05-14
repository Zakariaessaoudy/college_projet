package model;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Seance {
    private String nom_jour;
    private int numero_Semaine;
    private Time debut;
    private Time fin ;
    private LocalDate date;
    private Classe classe;
    private int numeroSalle;
    private String nomCours;
    private Administration administration;


    public int[] getOrdreSeances() {
        LocalTime debut;
        LocalTime fin;

        try {
            if (getDebut() == null || getFin() == null) {
                System.err.println("Erreur : heure de début ou de fin null");
                return new int[0];
            }

            debut = getDebut().toLocalTime();
            fin = getFin().toLocalTime();

            if (debut == null || fin == null) {
                System.err.println("Erreur : conversion vers LocalTime échouée");
                return new int[0];
            }

            if (!debut.isBefore(fin)) {
                System.err.println("Erreur : heure de début est après ou égale à l'heure de fin");
                return new int[0];
            }

        } catch (Exception e) {
            System.err.println("Exception lors de la récupération des heures : " + e.getMessage());
            return new int[0];
        }


        LocalTime[] heures = {
                LocalTime.of(8, 0), LocalTime.of(9, 0), LocalTime.of(10, 0), LocalTime.of(11, 0),
                LocalTime.of(14, 0), LocalTime.of(15, 0), LocalTime.of(16, 0), LocalTime.of(17, 0)
        };

        List<Integer> ordre = new ArrayList<>();

        for (int i = 0; i < heures.length; i++) {
            LocalTime debutSeance = heures[i];
            LocalTime finSeance = debutSeance.plusHours(1);

            // Vérifie si la séance est complètement dans l'intervalle demandé
            if (!debutSeance.isBefore(debut) && finSeance.isAfter(debut) && !debutSeance.isAfter(fin.minusSeconds(1))) {
                ordre.add(i + 1);
            }
        }

        // Convertir liste vers tableau
        int[] result = new int[ordre.size()];
        for (int i = 0; i < ordre.size(); i++) {
            result[i] = ordre.get(i);
        }

        return result;
    }


    public List<Eleve> getElevesByStatus(Absence listAbsence, StatusPresence status) {
        List<Eleve> elevesAbsents = new ArrayList<>();
        int[] ordre = getOrdreSeances();

        if (ordre == null || ordre.length == 0) {
            return elevesAbsents;
        }

        int startIndex = ordre[0];
        int endIndex = ordre[ordre.length - 1] + 1;

        for (Map.Entry<Eleve, List<StatusPresence>> entry : listAbsence.getListeAbscence().entrySet()) {
            Eleve eleve = entry.getKey();
            List<StatusPresence> jourAbsence = entry.getValue();

            if (jourAbsence == null || jourAbsence.size() < endIndex) {
                break;
            }

            try {
                List<StatusPresence> sublist = jourAbsence.subList(startIndex, endIndex);
                if (sublist.contains(status)) {
                    elevesAbsents.add(eleve);
                }
            } catch (IndexOutOfBoundsException | IllegalArgumentException e) {
                System.err.println("erreur dans les indices du tableau des Status eleve");
                break;
            }
        }

        return elevesAbsents;
    }

    public Seance(String nom_jour, int numero_Semaine, Time debut, Time fin, Classe classe, int numeroSalle, String nomCours, Administration administration, LocalDate date) {
        this.nom_jour = nom_jour;
        this.numero_Semaine = numero_Semaine;
        this.debut = debut;
        this.fin = fin;
        this.classe = classe;
        this.numeroSalle = numeroSalle;
        this.nomCours = nomCours;
        this.administration = administration;
        this.date=date;

    }

    public Seance(Time debut, Time fin, Classe classe, int numeroSalle, String nomCours) {
        this.debut = debut;
        this.fin = fin;
        this.classe = classe;
        this.numeroSalle = numeroSalle;
        this.nomCours = nomCours;
        this.date= LocalDate.now();
    }

    //getters and setters
    public String getNom_jour() {
        return nom_jour;
    }
    public LocalDate getDate() {
        return date;
    }
    public void setDate(LocalDate date) {
        this.date = date;
    }
    public void setNom_jour(String nom_jour) {
        this.nom_jour = nom_jour;
    }
    public int getNumero_Semaine() {
        return numero_Semaine;
    }
    public void setNumero_Semaine(int numero_Semaine) {
        this.numero_Semaine = numero_Semaine;
    }
    public Time getDebut() {
        return debut;
    }
    public void setDebut(Time debut) {
        this.debut = debut;
    }
    public Time getFin() {
        return fin;
    }
    public void setFin(Time fin) {
        this.fin = fin;
    }

}
