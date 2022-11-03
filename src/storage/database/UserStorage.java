package storage.database;

import java.sql.*;

import models.user.User;

public class UserStorage {

    Connection databaseConnection = IntiateConnection.getConnection();
    final String selectCustomer = "SELECT * FROM customers WHERE ";

    public boolean addUser(User user,short role) throws SQLException {
        int result;
        String query;
        String userRole;
        PreparedStatement statement;

        if(checkUserNameExists(user.getUserName()) || checkEmailExists(user.getEmail())) {
            System.out.println("Username or Email already exists!");
            return false;
        } 
        userRole = findUserByType(role);
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
            case 3:
                query = "admins";
                break;
        }
        return query;
    }

    boolean checkUserNameExists(String username) throws SQLException {
        String userNameQuery = selectCustomer + " username = ?";
        PreparedStatement checkUserStatement = databaseConnection.prepareStatement(userNameQuery);
        checkUserStatement.setString(1, username);
        ResultSet userResult = checkUserStatement.executeQuery();

        if(userResult.next()) {
            return true;
        }
        return false;
    }

    boolean checkEmailExists(String email) throws SQLException {
        String emailQuery = selectCustomer + " email = ?";
        PreparedStatement checkEmailStatement = databaseConnection.prepareStatement(emailQuery);
        checkEmailStatement.setString(1, email);
        ResultSet emailResult = checkEmailStatement.executeQuery();

        if(emailResult.next()) {
            return true;
        }
        return false;
    }

    public short authenticateUser(String username, String password) throws SQLException {
       return -1;
    }

    public void closeConnection() throws SQLException {
        databaseConnection.close();
    }

}
