package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;



public class Salle {
    public static int SalleDeCours = 1;
    public static int Laboratoire = 2;
    public static int SalleDeSport = 3;

    private int Numero;
    private int type;
    //private boolean disponible;
    Seance seanceActuel;
    List<Materiel> materielDeSalle;
    Seance[] lesSeancesDeToutLajournee;

    public int getNumero() {
        return Numero;
    }

    public void setNumero(int numero) {
        Numero = numero;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    
    public Salle(int numero, int type) {
        Numero = numero;
        this.type = type;
        seanceActuel = null;
        materielDeSalle = new ArrayList<Materiel>();
        lesSeancesDeToutLajournee = new Seance[8];
    }

    public void affecterSeance(Seance seance){
        seanceActuel = seance;
    }

    public void retirerSeance(){
        if(seanceActuel != null){
            seanceActuel = null;
        }else{
            System.out.println("pas de seance trouver dans cette classe");
        }
    }

    public void affecterMateriel(Materiel materiel){
        materielDeSalle.add(materiel);
    }

    public void retirerMateriel(Materiel materiel){
        
        if(materielDeSalle.contains(materiel)){
            for(int i = 0;i < materielDeSalle.size();i++){
                if (materielDeSalle.get(i).equals(materiel)) {
                    materielDeSalle.remove(i);
                    return;
                }
            }
        }else{
            System.out.println("materiel not found");
        }
    }
}
