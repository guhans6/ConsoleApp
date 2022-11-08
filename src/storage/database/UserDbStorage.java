package storage.database;

import java.sql.*;

import models.user.User;

public class UserDbStorage {

    Connection databaseConnection = IntiateConnection.getConnection();
    String query;
    // final String selectCustomer = "SELECT * FROM customers WHERE ";

    public boolean addUser(User user,short role) throws SQLException {
        int result;
        String query;
        String userRole;
        PreparedStatement statement;

        userRole = findUserByType(role);
        if(checkUserNameExists(user.getUserName(), user.getEmail() ,userRole)) {
            System.out.println("Username or Email already exists!");
            return false;
        } 
        query = "INSERT INTO " + userRole + " VALUES (?, ?, ?, ?, ?)";
        statement = databaseConnection.prepareStatement(query);

        statement.setString(1, user.getUserName());
        statement.setString(2, user.getName());
        statement.setString(3, user.getAddress());
        statement.setString(4, user.getEmail());
        statement.setString(5, user.getPassword());
        result = statement.executeUpdate();
        return result == 1;
    }
    
    String findUserByType(short role) {
        String query = "";
        
        switch(role) {
            case 1:
                query = "customers";
                break;
            case 2:
                query = "sellers";
                break;
        }
        return query;
    }

    boolean checkUserNameExists(String username, String email, String userRole) throws SQLException {
        String userNameQuery = "SELECT * FROM " + userRole + " WHERE username = ?";
        String emailQuery = "SELECT * FROM " + userRole + " WHERE email = ?";
        PreparedStatement checkUserStatement = databaseConnection.prepareStatement(userNameQuery);
        PreparedStatement checkEmailStatement = databaseConnection.prepareStatement(emailQuery);
        checkUserStatement.setString(1, username);
        checkEmailStatement.setString(1, email);
        ResultSet userResult = checkUserStatement.executeQuery();
        ResultSet emailResult = checkEmailStatement.executeQuery();

        if(userResult.next() || emailResult.next()) {
            return true;
        }
        return false;
    }

    public short authenticateUser(String username, String password, short userType) throws SQLException {
        query = "SELECT * FROM " + findUserByType(userType) + " WHERE username = ? AND password = ?";
        PreparedStatement statement = databaseConnection.prepareStatement(query);
        statement.setString(1, username);
        statement.setString(2, password);
        ResultSet result = statement.executeQuery();

        if(result.next()) {
            return userType;
        }
        return -1;
    }

    public void closeConnection() throws SQLException {
        databaseConnection.close();
    }

}
