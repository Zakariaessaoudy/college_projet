package dao;

import model.Eleve;
import model.Seance;
import util.DBUtil;
import java.sql.*;
import java.util.Vector;

public class DAOSeance{
    Connection cnn= DBUtil.getConnection();
    public boolean ajouterSeance(Seance seance){
        try{
            PreparedStatement ps =cnn.prepareStatement("INSERT INTO seance values(?,?,?,?,?,?,?,?,?)");
            ps.setInt(1,seance.getNumeroSalle());
            ps.setInt(2,seance.getIdCours());
            ps.setString(3,seance.getNom_jour());
            ps.setInt(4,seance.getNumero_Semaine());
            ps.setTime(5,seance.getDebut());
            ps.setTime(6,seance.getFin());
            ps.setDate(7,java.sql.Date.valueOf(seance.getDate()));
            int ligneExcute=ps.executeUpdate();
            ps.close();
           return ligneExcute>0;

        }
        catch(Exception e){
            System.out.println("EXCEPTION"+e);
            return false;
        }
    }
    public boolean supprimerSeance(String CNE){
        try{
            PreparedStatement ps=cnn.prepareStatement("DELETE FROM eleve where CNE like ?");
            ps.setString(1,CNE);
            int lignesExcute=ps.executeUpdate();
            ps.close();
           return lignesExcute>0;
        }catch(SQLException e){
            System.out.println("EXCEPTION"+e);
        return false;
        }
    }
   /* public  Vector<Seance>  tousSeances(){
        Vector<Seance> TousSeances=new Vector<Seance>();
        try{
            Statement s= cnn.createStatement();
            ResultSet rs=s.executeQuery("SELECT * FROM seance");
            while(rs.next()){
            Seance seance = new Seance();
            seance.getNumeroSalle(rs.getInt("numeroSalle"));
            seance.getIdCours(rs.getInt("idCours"));



            }
        }catch(SQLException e){

        }
    }*/

}