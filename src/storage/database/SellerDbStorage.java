package storage.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import models.product.Gadget;
import models.product.Product;
import storage.SellerStorage;


public class SellerDbStorage implements SellerStorage {

    Connection databaseConnection = IntiateConnection.getConnection();
    PreparedStatement preparedStatement;
    ResultSet resultSet;
    String query = null;
    int rowsAffected ;

    public boolean saveProduct(String username, Product product) throws Exception {
        if(product instanceof Gadget) {
            saveGadget((Gadget) product, username);
        } else {
            
        }
        return rowsAffected == 1;
    }

    private boolean saveGadget(Gadget gadget, String username) throws SQLException {

        query = "INSERT INTO \"Electronics\" (seller_id, brand, product_name, quantity, description, price, category,"
                + " discount_percentage, discount_price, features, colour, ram, rom, battery, display, processor, os, warranty)"
                + " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        preparedStatement = databaseConnection.prepareStatement(query);
        preparedStatement.setString(1, username); 
        preparedStatement.setString(2, gadget.getProductBrand());
        preparedStatement.setString(3, gadget.getProductName());
        preparedStatement.setLong(4, gadget.getQuantity());
        preparedStatement.setString(5, gadget.getProductDescription());
        preparedStatement.setDouble(6, gadget.getPrice());
        preparedStatement.setString(7, gadget.getProductCategory());
        preparedStatement.setDouble(8, gadget.getProductDiscount());
        preparedStatement.setDouble(9, gadget.getProductDiscountedPrice());
        preparedStatement.setString(10, gadget.getFeatures());
        preparedStatement.setString(11, gadget.getColor());
        preparedStatement.setString(12, gadget.getRam());
        preparedStatement.setString(13, gadget.getRom());
        preparedStatement.setString(14, gadget.getBattery());
        preparedStatement.setString(15, gadget.getDisplay());
        preparedStatement.setString(16, gadget.getProcessor());
        preparedStatement.setString(17, gadget.getOs());
        preparedStatement.setString(18, gadget.getWarranty());

        rowsAffected = preparedStatement.executeUpdate();
        return rowsAffected == 1;
    }

    public boolean checkUserProductAssociated(String sellerUsername, int productId) throws SQLException {
        query = "SELECT * FROM \"Products\" WHERE seller_id = ? AND product_id = ?";
        preparedStatement = databaseConnection.prepareStatement(query);
        preparedStatement.setString(1, sellerUsername);
        preparedStatement.setInt(2, productId);
        return preparedStatement.executeQuery().next();
    }

    public ArrayList<String> getSellerProducts(String sellerName)  throws SQLException {
        ArrayList<String> products = new ArrayList<>();
        StringBuilder productString = new StringBuilder();
        query = "SELECT * FROM \"Products\" WHERE seller_id = ?";
        preparedStatement = databaseConnection.prepareStatement(query);
        preparedStatement.setString(1, sellerName);
        resultSet = preparedStatement.executeQuery();
        
        while(resultSet.next()) {
            productString.append(resultSet.getInt("product_id"))
            .append("|").append(resultSet.getString("brand"))
                .append("|").append(resultSet.getString("product_name"))
                .append("|").append(resultSet.getDouble("price"))
                .append("|").append(resultSet.getLong("quantity"))
                .append("|").append(resultSet.getString("description"))
                .append("|").append(resultSet.getDouble("discount_percentage"))
                .append("|").append(resultSet.getDouble("discount_price"))
                .append("|").append(resultSet.getString("category"));
            products.add(productString.toString());
        }
        return products;
    }

    public void saveStock(int id, int stock) throws SQLException{

        query = "UPDATE \"Products\" SET quantity = ? WHERE product_id = ?";
        preparedStatement = databaseConnection.prepareStatement(query);
        preparedStatement.setInt(1, stock);
        preparedStatement.setInt(2, id);
        preparedStatement.executeUpdate();
    }

    public void deleteProduct(int id) throws SQLException {
        query = "DELETE FROM \"Products\" WHERE product_id = ?";
        preparedStatement = databaseConnection.prepareStatement(query);
        preparedStatement.setInt(1, id);
        preparedStatement.executeUpdate();
    }

}
