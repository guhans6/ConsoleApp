package storage.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import models.user.User;

public class CustomerDbStorage {

    Connection databaseConnection = IntiateConnection.getConnection();
    

    public boolean addUser(User user, short type) {
        String userType = findUserByType(type);
        String query = "INSERT INTO " + userType + " VALUES (?, ?, ?, ?)";
        try {
            PreparedStatement statement = databaseConnection.prepareStatement(query);
            statement.setString(1, user.getUserName());
            statement.setString(2, user.getName());
            statement.setString(3, user.getAddress());
            statement.setString(4, user.getEmail());
            statement.setString(5, user.getPassword());
            int result = statement.executeUpdate();
            if(result == 1) {
                return true;
            }
        } catch (SQLException e) {
            System.out.println("Error while adding user. Please try again.");
        }
        return false;
    
}

    private String findUserByType(short type) {
        String userType = "";
        switch(type) {
            case 1:
                userType = "customers";
                break;
            case 2:
                userType = "sellers";
                break;
        }
        return userType;
    }
}
