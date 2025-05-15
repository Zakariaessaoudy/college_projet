package util;

import java.sql.*;


public class DBUtil {
    private static Connection conn;
    static{
        try{
            Class.forName("com.mysql.jdbc.Driver");
            String url="jdbc:mysql:://localhoast:3306/college";
            conn= DriverManager.getConnection(url,"root","");
        }catch(Exception e){
            System.out.println(" erreur lors de telechargement de driver"+e);
        }
    }
    public static Connection getConnection(){
        return conn;
    }
}
