package dao;

import model.Cours;
import model.Enseignant;
import util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DAOCours {

    Connection conn = DBUtil.getConnection();

    //ajouter un cours au table de cours
    public boolean ajouterCours(Cours cours){
        try{

            PreparedStatement sql1 =conn.prepareStatement("INSERT INTO cours (nomCours , typeCours , idEnseignant) VALUES(?,?,?)");
            sql1.setString(1, cours.getNom());
            sql1.setString(2, cours.getType());
            sql1.setInt(3, cours.getEnseignant().getIdentifiant());
            int addedLignes = sql1.executeUpdate();
            sql1.close();
            return addedLignes > 0 ;

        } catch (Exception e) {
            System.out.println("EXCEPTION"+e);
            return false;    }
    }


    // afficher tous les cours ;
    public List<Cours> tousCours() {
        List<Cours> coursList = new ArrayList<>();

        try {
            PreparedStatement sqlCours = conn.prepareStatement("SELECT * FROM cours");
            ResultSet resultCours = sqlCours.executeQuery();

            while (resultCours.next()) {
                int idCours = resultCours.getInt("idCours");
                String nomCours = resultCours.getString("nomCours");
                String typeCours = resultCours.getString("typeCours");
                int idEnseignant = resultCours.getInt("idEnseignant");

                // On récupère l'enseignant correspondant
                PreparedStatement sqlEns = conn.prepareStatement("SELECT * FROM enseignant WHERE idEnseignant = ?");
                sqlEns.setInt(1, idEnseignant);
                ResultSet resultEns = sqlEns.executeQuery();

                Enseignant enseignant = null;
                if (resultEns.next()) {
                    enseignant = new Enseignant(
                            resultEns.getInt("idEnseignant"),
                            resultEns.getString("nom"),
                            resultEns.getString("prenom"),
                            resultEns.getString("email"),
                            resultEns.getString("specialite")
                    );
                }

                // Fermeture de la 2ème requête
                resultEns.close();
                sqlEns.close();

                // Ajout du cours à la liste
                Cours cours = new Cours(idCours, nomCours, typeCours, enseignant);
                coursList.add(cours);
            }

            resultCours.close();
            sqlCours.close();

        } catch (Exception e) {
            System.out.println("EXCEPTION : " + e.getMessage());
        }

        return coursList;
    }

    public boolean supprimerCours(Cours cours){
        try{

            PreparedStatement ps = conn.prepareStatement("DELETE FROM cours WHERE idCours=?");
            ps.setString(1, cours.getNom());
            int deletedLines = ps.executeUpdate();
            ps.close();
            return deletedLines >0 ;
        } catch (SQLException e) {
            System.out.println("EXCEPTION"+e);
            return  false ;
        }

    }

    //modifier un cours

    public boolean modifierCours(Cours cours) {
        try {
            PreparedStatement ps = conn.prepareStatement(
                    "UPDATE cours SET nomCours = ?, typeCours = ?, idEnseignant = ? WHERE idCours=?"
            );

            ps.setString(1, cours.getNom());
            ps.setString(2, cours.getType());
            ps.setInt(3, cours.getEnseignant().getIdentifiant()); // on récupère l'id depuis l'objet Enseignant
            ps.setInt(4, cours.getIdCours());

            int updatedLines = ps.executeUpdate();
            ps.close();
            return updatedLines > 0;

        } catch (SQLException e) {
            System.out.println("EXCEPTION : " + e.getMessage());
            return false;
        }
    }

    public boolean coursExiste(String nomCours) {
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT 1 FROM cours WHERE nomCours = ?");
            ps.setString(1, nomCours);

            ResultSet rs = ps.executeQuery();
            boolean existe = rs.next(); // il existe si on a au moins une ligne

            rs.close();
            ps.close();

            return existe;

        } catch (SQLException e) {
            System.out.println("EXCEPTION : " + e.getMessage());
            return false;
        }
    }

    public List<Cours> avoirCoursParIdEnseignant(int idEnseignant) {
        List<Cours> listeCours = new ArrayList<>();

        try {
            PreparedStatement ps = conn.prepareStatement(
                    "SELECT idCours, nomCours, typeCours FROM cours WHERE idEnseignant = ?");
            ps.setInt(1, idEnseignant);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                listeCours.add(new Cours(rs.getInt("idCour"),rs.getString("nomCours"),rs.getString("typeCours")));
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            System.out.println("EXCEPTION : " + e.getMessage());
        }
        return listeCours;
    }
}
