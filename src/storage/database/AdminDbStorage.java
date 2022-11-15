package storage.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import storage.AdminStorage;

public class AdminDbStorage implements AdminStorage {

    Connection connection = IntiateConnection.getConnection();
    PreparedStatement preparedStatement;
    ResultSet resultSet;
    String query;


    @Override
    public boolean deleteUser(String username, int userType) throws Exception {
        String user = UserDbStorage.getUserByType(userType);
        query = "DELETE FROM "+ user + " WHERE user_id = ?";
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, username);
        int result = preparedStatement.executeUpdate();
        if(result == 1) {
            return true;
        }
        return false;
    }

    @Override
    public ArrayList<String[]> getAllUsers(int userType) throws Exception {
        ArrayList<String[]> users = new ArrayList<>();
        String user = UserDbStorage.getUserByType(userType);
        query = "SELECT * FROM "+ user;
        preparedStatement = connection.prepareStatement(query);
        resultSet = preparedStatement.executeQuery();
        resultSet.toString();
        while(resultSet.next()) {
            String[] userArray = new String[5];
            userArray[0] = resultSet.getString("name");
            userArray[1] = resultSet.getString("user_id");
            userArray[2] = resultSet.getString("password");
            userArray[3] = resultSet.getString("email");
            userArray[4] = resultSet.getString("address");
            users.add(userArray);
        }

        return users;
    }

}