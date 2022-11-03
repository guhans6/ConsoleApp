package storage.database;

import java.sql.Connection;
import java.sql.DriverManager;

public class IntiateConnection {
    
    public static Connection getConnection() {
        Connection connection = null;
        String url = "jdbc:postgresql://localhost:5432/e_commerce_app";
        String user = "postgres";
        String password = "asdfgh";
        try {
            //postgresql
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(url, user, password);
        } catch (Exception e) {
            System.out.println("Can't Establish Connection!");
            e.printStackTrace();
        }
        return connection;
    }
}
