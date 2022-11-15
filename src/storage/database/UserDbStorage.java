package storage.database;

import java.sql.*;
import java.util.ArrayList;

import models.product.Gadget;
import models.product.Product;
import models.user.User;
import storage.UserStorage;

public class UserDbStorage implements UserStorage {

    Connection databaseConnection = IntiateConnection.getConnection();
    PreparedStatement statement;
    StringBuilder sBuilder = new StringBuilder();
    String query;

    public boolean addUser(User user,int userType) throws SQLException {
        int result;
        String query;
        String userRole;
        PreparedStatement statement;

        userRole = getUserByType(userType);
        if(checkUserNameExists(user.getUserName(), user.getEmail() ,userRole)) {
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
    
    static String getUserByType(int role) {
        String query = "";
        
        switch(role) {
            case 1:
                query = "\"Customers\"";
                break;
            case 2:
                query = "\"Sellers\"";
                break;
            case 3:
                query = "\"Admins\"";
                break;
        }
        return query;
    }

    private boolean checkUserNameExists(String username, String email, String userRole) throws SQLException {
        String userNameQuery = "SELECT * FROM " + userRole + " WHERE user_id = ?";
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
        query = "SELECT * FROM " + getUserByType(userType) + " WHERE user_id = ? AND password = ?";
        PreparedStatement statement = databaseConnection.prepareStatement(query);
        statement.setString(1, username);
        statement.setString(2, password);
        ResultSet result = statement.executeQuery();

        if(result.next()) {
            return userType;
        }
        return -1;
    }

    public ArrayList<String> getProducList(String productType) throws SQLException {
        ArrayList<String> productList = new ArrayList<String>();
        query = "SELECT * FROM \"Electronics\"" + " WHERE category = ?";

        PreparedStatement statement = databaseConnection.prepareStatement(query);
        statement.setString(1, productType);
        ResultSet result = statement.executeQuery();

        while(result.next()) {
            Gadget gadget = new Gadget();
            gadget.setProductID(result.getInt("product_id"));
            gadget.setProductName(result.getString("product_name"));
            gadget.setProductBrand(result.getString("brand"));
            gadget.setPrice(result.getDouble("price"));
            gadget.setQuantity(result.getInt("quantity"));
            gadget.setProductDescription(result.getString("description"));
            gadget.setProductDiscount(result.getDouble("discount_percentage"));
            gadget.setProductDiscountedPrice(result.getDouble("discount_price"));
            gadget.setProductCategory(result.getString("category"));
            gadget.setFeatures(result.getString("features"));
            gadget.setRam(result.getString("ram"));
            gadget.setRom(result.getString("rom"));
            gadget.setBattery(result.getString("battery"));
            gadget.setDisplay(result.getString("display"));
            gadget.setProcessor(result.getString("processor"));
            gadget.setOs(result.getString("os"));
            gadget.setWarranty(result.getString("warranty"));
            gadget.setCamera(result.getString("features"));
            productList.add(gadget.toString());
        }
        return productList;
    }

    public String[] findProdcutByID(String string) throws SQLException {
        query = "SELECT * FROM \"Products\"" + " WHERE product_id = ?";
        statement = databaseConnection.prepareStatement(query);
        statement.setInt(1, Integer.parseInt(string));
        ResultSet result = statement.executeQuery();
        if(result.next()) {
            Product product = new Product();
            product.setProductID(result.getInt("product_id"));
            product.setProductBrand(result.getString("brand"));
            product.setProductName(result.getString("product_name"));
            product.setPrice(result.getDouble("price"));
            product.setQuantity(result.getInt("quantity"));
            product.setProductDescription(result.getString("description"));
            product.setProductDiscount(result.getDouble("discount_percentage"));
            product.setProductDiscountedPrice(result.getDouble("discount_price"));
            product.setProductCategory(result.getString("category"));
            return product.toString().split("\\|");
        } else {
            return null;
        }
    }
}
