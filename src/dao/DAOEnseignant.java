package dao;

import model.Enseignant;
import util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DAOEnseignant {

    Connection conn = DBUtil.getConnection();

    // Ajouter un nouvel enseignant dans la base de données
    public boolean ajouterEnseignant(Enseignant enseignant) {
        try {
            PreparedStatement ps = conn.prepareStatement("INSET INTO enseignant(nomEnseignant,prenomEnseignant,emailEnseignant,telephoneEnseignant)VALUES(?,?,?,?)");
            ps.setString(1, enseignant.getNom());
            ps.setString(2, enseignant.getPrenom());
            ps.setString(3, enseignant.getEmail());
            ps.setString(4, enseignant.getNumeroTelephone());
            int addedLines = ps.executeUpdate();
            ps.close();
            return addedLines > 0;

        } catch (SQLException e) {
            System.out.println("EXCEPTION" + e);
            return false;
        }
    }

    /**
     * Supprimer un enseignant à partir de son identifiant
     */
    public boolean supprimerEnseignant(Enseignant enseignant) {
        try {
            PreparedStatement ps = conn.prepareStatement("DELETE FROM enseignant(nomEnseignant,prenomEnseignant,emailEnseignant,telephoneEnseignant) WHERE IdEnseignant=? ");
            ps.setString(1, enseignant.getNom());
            ps.setString(2, enseignant.getPrenom());
            ps.setString(3, enseignant.getEmail());
            ps.setString(4, enseignant.getNumeroTelephone());
            ps.setInt(5, enseignant.getIdentifiant());
            int deletedLines = ps.executeUpdate();
            ps.close();
            return deletedLines > 0;

        } catch (SQLException e) {
            System.out.println("EXCEPTION" + e);
            return false;
        }
    }

    /**
     * Modifier les informations d’un enseignant existant
     */
    public boolean modifierEnseignant(Enseignant enseignant) {
        try {
            PreparedStatement ps = conn.prepareStatement("UPDATE enseignant SET nomEnseignant= ? ,prenomEnseignant=? ,emailEnseignant=?,telephoneEnseignant=? WHERE idEnseignant = ? ");
            ps.setString(1, enseignant.getNom());
            ps.setString(2, enseignant.getPrenom());
            ps.setString(3, enseignant.getEmail());
            ps.setString(4, enseignant.getNumeroTelephone());
            int updatedLines = ps.executeUpdate();
            ps.close();
            return updatedLines > 0;

        } catch (SQLException e) {
            System.out.println("EXCEPTION" + e);
            return false;
        }
    }

    /**
     * Récupérer un enseignant selon son identifiant
     */
    public Enseignant getEnseignantParId(int id) {
        Enseignant enseignant = null;
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM enseignant WHERE idEnseignant =?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String nomEnseignant = rs.getString("nomEnseignant");
                String prenomEnseignant = rs.getString("prenomEnseignant");
                String emailEnseignant = rs.getString("emailEnseignant");
                String telephoneEnseignant = rs.getString("telephoneEnseignant");

                enseignant = new Enseignant(id, nomEnseignant, prenomEnseignant, emailEnseignant, telephoneEnseignant);

            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            System.out.println("EXCEPTION" + e);
        }
        return enseignant;
    }

    /**
     * Récupérer la liste complète de tous les enseignants
     */
    public List<Enseignant> tousLesEnseignants() {
        List<Enseignant> enseignants = new ArrayList<>();
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM enseignant");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                enseignants.add(new Enseignant(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5)));
            }
        } catch (Exception e) {
            System.out.println("EXCEPTION" + e);
        }
        return enseignants;
    }

    /**
     * chercher un enseignant par son nom :
     */
    public List<Enseignant> chercherParNom(String nom) {
        List<Enseignant> enseignants = new ArrayList<>();
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM enseignant WHERE nomEnseignant =?");
            ResultSet rs = ps.executeQuery();
            ps.setString(1, nom);
            while (rs.next()) {
                enseignants.add(new Enseignant(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5)));
            }
        } catch (SQLException e) {
            System.out.println("EXCEPTION" + e);
        }
        return enseignants;
    }

    public boolean enseignantExiste(String nomEnseignant) {
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT 1 FROM enseignant WHERE nomEnseignant=?");
            ps.setString(1, nomEnseignant);
            ResultSet rs = ps.executeQuery();
            boolean existe = rs.next();
            rs.close();
            ps.close();

            return existe;
        } catch (SQLException e) {
            System.out.println("EXCEPTION" + e);
            return false;
        }
    }
}
