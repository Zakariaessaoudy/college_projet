package util;

import java.sql.*;


public class DBUtil {
    private static Connection conn;
    static{
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url="jdbc:mysql://localhost:3306/college";
            conn= DriverManager.getConnection(url,"root","");
        }catch(Exception e){
            System.out.println(" erreur lors de telechargement de driver"+e);
        }
    }
    public static Connection getConnection(){
        return conn;
    }
}
