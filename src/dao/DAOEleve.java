package dao;

import model.Eleve;
import util.DBUtil;
import java.sql.*;

public class DAOEleve {
    Connection cnn= DBUtil.getConnection();
    public void ajouterEleve(Eleve eleve){
        try{
            PreparedStatement ps=cnn.prepareStatement("INSERT INTO eleve VALUES(?,?,?,?,?,?,?)");
            ps.setString(1,eleve.getCne());
            ps.setString(2,eleve.getNom());
            ps.setString(3,eleve.getPrenom());
            ps.setString(4,eleve.getAdresse());
            ps.setString(5,eleve.getEmail());
            ps.setString(6,eleve.getEmailParent());
            ps.setInt(7,eleve.getClasse().getNumeroClasse());
            ps.executeUpdate();
            ps.close();
            cnn.close();

        }
        catch(SQLException e){
            System.out.println("EXCEPTION"+e);

        }
    }
    public void supprimerEleve(String CNE){
        try{
            PreparedStatement ps=cnn.prepareStatement("DELETE FROM eleve where CNE like ?");
            ps.setString(1,CNE);
            ps.executeUpdate();
            ps.close();
            cnn.close();
        }catch(SQLException e){

        }
    }
    public void tousEleves(){
        try{
            Statement s= cnn.createStatement();
            ResultSet rs=s.executeQuery("SELECT * FROM eleve");
            while(rs.next()){
                System.out.println();
            }
        }catch(SQLException e){

        }
    }

}