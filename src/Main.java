import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {
            public static void main(String[] args) {
                String url = "jdbc:mysql://localhost:3306/college";
                String user = "root";
                String password = "";
                try {
                    Connection conn = DriverManager.getConnection(url, user, password);
                    System.out.println("Connexion réussie !");
                    conn.close();
                } catch (SQLException e) {
                    System.out.println("Échec de la connexion !");
                    e.printStackTrace();
                }
            }
        }

