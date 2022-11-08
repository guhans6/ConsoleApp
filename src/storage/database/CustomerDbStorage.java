package storage.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CustomerDbStorage {

    Connection databaseConnection = IntiateConnection.getConnection();
    
    public void addToCart(String username, int id,int quantity) throws SQLException {
        String query = "INSERT INTO cart (username, product_id, quantity) VALUES (?, ?, ?)";
        PreparedStatement statement = databaseConnection.prepareStatement(query);
        statement.setString(1, username);
        statement.setInt(2, id);
        statement.setInt(3, quantity);
        statement.executeUpdate();
    }

    public void removeProductFromCart(String username, int id) throws SQLException {
        String query = "DELETE FROM cart WHERE username = ? AND product_id = ?";
        PreparedStatement statement = databaseConnection.prepareStatement(query);
        statement.setString(1, username);
        statement.setInt(2, id);
        statement.executeUpdate();
    }
}