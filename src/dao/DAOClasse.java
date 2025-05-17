package dao;

import model.Classe;
import model.Eleve;
import util.DBUtil;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class DAOClasse {
    Connection cnn = DBUtil.getConnection();

    public void AllClass() {
        try {
            Statement s = cnn.createStatement();
            ResultSet rs = s.executeQuery("SELECT * FROM CLASSE;");
            while (rs.next()) {
                System.out.println("Classe son nom"+rs.getString(3)+"son numero"+rs.getString(1) + " son niveau scolaire" + rs.getString(2));
            }
            rs.close();
            s.close();
        } catch (Exception e) {
            System.out.println("EXCEPTION"+e);
        }

    }

    public boolean ajouterClasse(Classe classe) {
        try {
            PreparedStatement ps = cnn.prepareStatement("INSERT INTO CLASSE VALUES(?,?,?) ");
            ps.setInt(1, classe.getNumeroClasse());
            ps.setInt(2, classe.getNiveauScolaire());
            ps.setString(3,classe.getNomClasse());
            int lignesExc=ps.executeUpdate();
            ps.close();
          return lignesExc>0;
        } catch (Exception e) {
            System.out.println("EXCEPTION"+e);
                    return false;
        }
    }

    public boolean supprimerClass(Classe classe) {
        try {
            PreparedStatement ps = cnn.prepareStatement("DELETE FROM CLASSE WHEN NUMEROCLASSE =?");
            ps.setInt(1, classe.getNumeroClasse());
           int lignesExcute =ps.executeUpdate();
            ps.close();
          return lignesExcute>0;
        } catch (Exception e) {
            System.out.println("Exception lors de supprission de class "+e);
            return false;
        }
    }

    public List<Eleve> listeDesElevesDansClasse(){
        List <Eleve> ListeEleves = new ArrayList<>();
        try{
            Classe classe =null;
            Eleve eleve=null;
            PreparedStatement ps=cnn.prepareStatement("SELECT * FROM classe");
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                classe = new Classe(rs.getInt(1),rs.getInt(2),rs.getString(3));
                int numeroClasse=rs.getInt(1);

                PreparedStatement ps2=cnn.prepareStatement("SELECT * FROM eleve where numeroClasse=?");
                ps2.setInt(1,numeroClasse);
                ResultSet rs2=ps2.executeQuery();
                while(rs2.next()){
                    eleve=new Eleve(rs2.getString(1),rs2.getString(2),rs2.getString(3),
                            rs2.getString(4),rs2.getString(5),rs2.getString(6),classe);
                    ListeEleves.add(eleve);
                }
                rs2.close();
                ps2.close();
            }


            ps.close();
            rs.close();
        }catch(Exception e){
            System.out.println("EXCEPTION"+e);
        }
        return(ListeEleves);
    }
    public Classe TrouverClasseParNumero(int numeroClasse) {
        Classe classe = null;
        try {
            PreparedStatement ps = cnn.prepareStatement("SELECT * FROM classe where numeroClasse = ?");
            ps.setInt(1, numeroClasse);
            ResultSet rs =ps.executeQuery();

            if (rs.next()){
                classe=new Classe(rs.getInt(1),rs.getInt(2),rs.getString(3));
            }
            rs.close();
            ps.close();

        } catch (Exception e) {
            System.out.println("Exception "+e);
        }
        return classe;
    }
}