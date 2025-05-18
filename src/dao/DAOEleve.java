package dao;

import model.Classe;
import model.Eleve;
import util.DBUtil;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DAOEleve {
    Connection cnn= DBUtil.getConnection();
    public boolean ajouterEleve(Eleve eleve){
        try{
            PreparedStatement ps=cnn.prepareStatement("INSERT INTO eleve VALUES(?,?,?,?,?,?,?)");
            ps.setString(1,eleve.getCne());
            ps.setString(2,eleve.getNom());
            ps.setString(3,eleve.getPrenom());
            ps.setString(4,eleve.getAdresse());
            ps.setString(5,eleve.getEmail());
            ps.setString(6,eleve.getEmailParent());
            ps.setInt(7,eleve.getClasse().getNumeroClasse());
            int ligneExecute=ps.executeUpdate();
            ps.close();
         return ligneExecute>0;

        }
        catch(SQLException e){
            System.out.println("EXCEPTION"+e);
return false;
        }
    }
    public boolean supprimerEleve(String CNE){
        try{
            PreparedStatement ps=cnn.prepareStatement("DELETE FROM eleve where CNE like ?");
            ps.setString(1,CNE);
           int ligneExceute= ps.executeUpdate();
            ps.close();
         return  ligneExceute >0;
        }catch(Exception e){
            System.out.println("EXCEPTION"+e);
            return false;
        }
    }
    public List<Eleve> tousEleves(){
        List<Eleve> eleves=new ArrayList<>();
        try{
            Statement s= cnn.createStatement();
            ResultSet rs=s.executeQuery("SELECT * FROM eleve");
            while(rs.next()){
                Classe classe=new Classe();
                classe.setNumeroClasse(rs.getInt(7));
            Eleve eleve=new Eleve(rs.getString(1),rs.getString(2),
                    rs.getString(3),rs.getString(4)
                    ,rs.getString(5),rs.getString(6),classe);
            eleves.add(eleve);
            }
        }catch(Exception e){
            System.out.println("EXCEPTION"+e);
        }
        return(eleves);
    }
    public Eleve trouveEleveParCNE(String CNE){
        Eleve eleve=null;
        try{
            PreparedStatement ps=cnn.prepareStatement("SELECT * FROM eleves WHERE CNE = ?");
            ps.setString(1,CNE);
            ResultSet rs=ps.executeQuery();
            if(rs.next()){
                Classe classe=new Classe();
                classe.setNumeroClasse(rs.getInt(7));
                eleve=new Eleve(rs.getString(1),rs.getString(2),
                        rs.getString(3),rs.getString(4),
                        rs.getString(5),rs.getString(6),
                        classe);
            }

        }catch(Exception e){
            System.out.println("EXCEPTION"+e);
        }
        return eleve;
    }
}