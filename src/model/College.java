package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class College {
    private String Nom;
    private String Adresse;
    private Administration admin;
    Map<Integer,Salle> lesSalles;
    public College(String nom, String adresse) {
        Nom = nom;
        Adresse = adresse;
        admin = new Administration(this);
        lesSalles = new HashMap<Integer,Salle>();
    }

    public void ajouterSalle(Salle salle){
        lesSalles.put(salle.getNumero(), salle);
    }

    public void retirerSalle(int numero){
        if (lesSalles.remove(numero) == null) {
            System.out.println("salle non trouvee");
        }
    }

    
}
