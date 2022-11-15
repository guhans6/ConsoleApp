package storage.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import storage.CustomerStorage;

public class CustomerDbStorage implements CustomerStorage {

    Connection databaseConnection = IntiateConnection.getConnection();
    PreparedStatement statement;
    ResultSet resultSet;
    String query;

    @Override
    public void addToCart(String username, int id,int quantity) throws SQLException {
        PreparedStatement checkCart = databaseConnection.prepareStatement("SELECT * FROM \"Cart\" WHERE user_id = ? AND product_id = ?");
        checkCart.setString(1, username);
        checkCart.setInt(2, id);
        ResultSet carResultSet = checkCart.executeQuery();
        if(carResultSet.next()) {
            PreparedStatement updateCart = databaseConnection.prepareStatement("UPDATE \"Cart\" SET quantity = quantity + ? WHERE user_id = ? AND product_id = ?");
            updateCart.setInt(1, quantity);
            updateCart.setString(2, username);
            updateCart.setInt(3, id);
            updateCart.executeUpdate();
        } else {
            statement = databaseConnection.prepareStatement("SELECT discount_price FROM \"Products\" WHERE product_id = ?");
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            resultSet.next();
            double price = resultSet.getDouble("discount_price");
            String query = "INSERT INTO \"Cart\" (user_id, product_id, quantity, price) VALUES (?, ?, ?, ?)";
            statement = databaseConnection.prepareStatement(query);
            statement.setString(1, username);
            statement.setInt(2, id);
            statement.setInt(3, quantity);
            statement.setDouble(4, price);
            statement.executeUpdate();
        }
    }

    @Override
    public boolean removeProductFromCart(String username, int id) throws SQLException {
        String query = "DELETE FROM \"Cart\" WHERE user_id = ? AND product_id = ?";
        PreparedStatement statement = databaseConnection.prepareStatement(query);
        statement.setString(1, username);
        statement.setInt(2, id);
        return statement.executeUpdate() > 0;
    }

    @Override
    public ArrayList<String[]> getCart(String username) throws SQLException {
        ArrayList<String[]> cartItems = new ArrayList<>();
        String cartItem[] = new String[4];
        String getCartQuery = "SELECT * FROM  \"Cart\" WHERE user_id = ?";
        PreparedStatement statement = databaseConnection.prepareStatement(getCartQuery);
        statement.setString(1, username);
        ResultSet cart = statement.executeQuery();
        while(cart.next()) {
            cartItem[0] = cart.getString("user_id");
            cartItem[1] = cart.getLong("product_id") + "";
            cartItem[2] = cart.getInt("quantity") + "";
            cartItem[3] = cart.getDouble("price") + "";
            cartItems.add(cartItem);
            cartItem = new String[4];
        }
        return cartItems;
    }

    @Override
    public void buyProducts(String username) throws Exception {
        ArrayList<String[]> cartItems = getCart(username);
        for(String[] item : cartItems) {
                addOrder(username, Integer.parseInt(item[1]), Integer.parseInt(item[2]), Double.parseDouble(item[3]));
                removeStock(Integer.parseInt(item[1]), Integer.parseInt(item[2]));
        }
        clearCart(username);
        
    }

    private void clearCart(String username) throws SQLException {
        String query = "DELETE FROM \"Cart\" WHERE user_id = ?";
        PreparedStatement statement = databaseConnection.prepareStatement(query);
        statement.setString(1, username);
        statement.executeUpdate();
    }

    private void removeStock(int id, int quantity) throws SQLException {
        query = "UPDATE \"Products\" SET quantity = quantity - ? WHERE product_id = ?";
        statement = databaseConnection.prepareStatement(query);
        statement.setInt(1, quantity);
        statement.setInt(2, id);
        statement.executeUpdate();
    }

    private void addOrder(String username, int id, int quantity, double price) throws SQLException {
        String query = "INSERT INTO \"Orders\" (customer_username, product_id, quantity, price, date) VALUES (?, ?, ?, ?, ?)";
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        statement = databaseConnection.prepareStatement(query);
        statement.setString(1, username);
        statement.setInt(2, id);
        statement.setInt(3, quantity);
        statement.setDouble(4, price);
        statement.setString(5, dtf.format(LocalDateTime.now()));
        statement.executeUpdate();
    }

    @Override
    public ArrayList<String[]> getOrders(String username) throws SQLException {
        ArrayList<String[]> orders = new ArrayList<>();
        String order[] = new String[5];
        query = "SELECT * FROM \"Orders\" WHERE customer_username = ?";
        statement = databaseConnection.prepareStatement(query);
        statement.setString(1, username);
        ResultSet orderSet = statement.executeQuery();
        while(orderSet.next()) {
            order[0] = orderSet.getString("customer_username");
            order[1] = orderSet.getString("product_id");
            order[2] = orderSet.getString("date");
            order[3] = orderSet.getString("price");
            order[4] = orderSet.getString("quantity");
            orders.add(order);
            order = new String[5];
        }
        return orders;
    }

    public short checkStock(int id, int quantity) throws SQLException {
        query = "SELECT quantity FROM \"Products\" WHERE product_id = ?";
        statement = databaseConnection.prepareStatement(query);
        statement.setInt(1, id);
        ResultSet stock = statement.executeQuery();
        if(stock.next()) {
            if(stock.getInt("quantity") >= quantity) {
                return 1;
            }
            else {
                return -1;
            }
        }
        return 0;
    }

}