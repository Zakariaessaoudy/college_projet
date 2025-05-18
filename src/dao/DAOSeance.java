package dao;

import com.sun.xml.internal.ws.org.objectweb.asm.ClassAdapter;
import model.Administration;
import model.Classe;
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
            ps.setInt(8,seance.getAdministration().getIdAdministration());
            ps.setInt(9,seance.getClasse().getNumeroClasse());
            int ligneExcute=ps.executeUpdate();
            ps.close();
           return ligneExcute>0;

        }
        catch(Exception e){
            System.out.println("EXCEPTION"+e);
            return false;
        }
    }
    public boolean supprimerSeance(int numeroSalle,int idCours){
        try{
            PreparedStatement ps=cnn.prepareStatement("DELETE FROM seance where numeroSalle=? and idCours =?");
            ps.setInt(1,numeroSalle);
            ps.setInt(2,idCours);
            int lignesExcute=ps.executeUpdate();
            ps.close();
           return lignesExcute>0;
        }catch(SQLException e){
            System.out.println("EXCEPTION"+e);
        return false;
        }

    public  Vector<Seance>  tousSeances(){
        Vector<Seance> TousSeances=new Vector<Seance>();
        try{
            Statement s= cnn.createStatement();
            ResultSet rs=s.executeQuery("SELECT * FROM seance");
            while(rs.next()){
                Administration administration=new Administration();
                administration.setIdAdministration(rs.getInt(8));
                Classe classe =new Classe();
                classe.setNumeroClasse(rs.getInt(9));
            Seance seance = new Seance(rs.getInt(1),rs.getInt(2),
                    rs.getString(3),rs.getInt(4),rs.getTime(5),
                    rs.getTime(6),rs.getDate(7),administration,classe);
        TousSeances.add(seance);
            }
            rs.close();
            s.close();
        }catch(Exception e){

        }
        return(TousSeances);
    }

}