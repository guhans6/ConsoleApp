package storage.fileStorage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import storage.CustomerStorage;
import storage.UserStorage;
import storage.database.UserDbStorage;

public class CustomerFileStorage implements CustomerStorage {
    
    private File orders = new File("Files/orders.txt");
    private File cart = new File("Files/cart.txt");
    private File products = new File("Files/products.txt");
    private File tempFile = new File("Files/temp.txt");
    UserStorage fileStorage = new UserDbStorage();
    private BufferedReader reader;
    private BufferedWriter writer;

    //this method gets all the products in the cart in the cart file of the username and displays total price
    public ArrayList<String[]> getCart(String username) throws IOException {
        reader = new BufferedReader(new FileReader(cart));
        ArrayList<String[]> producList =  new ArrayList<>();
        String line = reader.readLine();

        while(line != null) {
            String[] split = line.split("\\|");
            if(split[0].equals(username)) {
                producList.add(split);
            }
            line = reader.readLine();
        }
        return producList;
    }
    
    public ArrayList<String[]> getOrders(String username) throws IOException {
        ArrayList<String[]> orderList = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(orders));
        String line = reader.readLine();

        while(line != null) {

            String[] split = line.split("\\|");
            if(split[0].equals(username)) {
                orderList.add(split);
            }
            line = reader.readLine();
        }
        reader.close();
        return orderList;
    }

    public void addToCart(String username, int id,int quantity) throws Exception {
        BufferedReader reader = new BufferedReader(new FileReader(cart));
        String line = reader.readLine();
        int newQuantity = quantity;
        String[] product;
        while(line != null) {
            String[] split = line.split("\\|");
            if(id == Integer.parseInt(split[1]) && username.equals(split[0])){
                newQuantity = Integer.parseInt(split[2]) + quantity;
                removeProductFromCart(username, id);
                break;
            }
            System.out.println("aa");
            line = reader.readLine();
        }
        BufferedWriter writer = new BufferedWriter(new FileWriter(cart, true)); //add product and usernmae in cart file
        product = fileStorage.findProdcutByID(id + "");
        writer.write(username + "|" + id + "|" + newQuantity + "|" + product[7]);
        writer.newLine();
        writer.close();;
        reader.close();
    }


    //calculate total price of the products in the cart by username and display it if user wants to buy add it to orders file
    public void buyProducts(String username) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(cart));
        String line = reader.readLine();
        
        while(line != null) {

            String[] split = line.split("\\|");
            if(split[0].equals(username)) {
                addOrder(username, split[1], split[2], split[3]);                //add product to orders file
                removeStock(split[1], split[2]);                                 //remove stock from products file          
            }   
            clearCart(username);                                                 //clear cart file
            line = reader.readLine();
        }
        reader.close();
    }

    //this method add the order to orders file
    private void addOrder(String username, String productId, String productPrice, String quantity) throws IOException {
        writer = new BufferedWriter(new FileWriter(orders, true));
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        writer.write(username + "|" + productId + "|" + dtf.format(LocalDateTime.now()) + "|" + quantity + "|" + productPrice);
        writer.newLine();
        writer.close();;
    }

     //this method removes the product from stock
     private void removeStock(String id,String quantity) throws IOException {
        reader = new BufferedReader(new FileReader(products));
        writer = new BufferedWriter(new FileWriter(tempFile));
        String stockString;

        tempFile.createNewFile();
        String line = reader.readLine();
        while(line != null) {

            String[] split = line.split("\\|");
            if(split[0].equals(id)) {
                int stock = Integer.parseInt(split[4]);
                stockString = UserFileStorage.getStockString(split);
                stock -= Integer.parseInt(quantity);
                line = split[0] + "|" + split[1] + "|" + split[2] + "|" + split[3] + "|" + stock + "|" + stockString;
            }
            writer.write(line);
            writer.newLine();
            line = reader.readLine();
        }
        writer.close();
        products.delete();
        tempFile.renameTo(products);
    }

    //remove product from cart
    private void clearCart(String username) throws IOException {
        reader = new BufferedReader(new FileReader(cart));
        BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));
        tempFile.createNewFile();
        String line = reader.readLine();
        while(line != null) {

            String[] split = line.split("\\|");
            if(split[0].equals(username)) {
                line = reader.readLine();
                continue;
            }
            writer.write(line);
            writer.newLine();
            line = reader.readLine();
        }
        writer.close();;
        cart.delete();
        tempFile.renameTo(cart);
    }

    //this method checks if stock is available for the product
    public short checkStock(int id,int quantity) throws IOException {
        reader = new BufferedReader(new FileReader(products));
        String line = reader.readLine();
        
        while(line != null) {

            String[] split = line.split("\\|");
            if(Integer.parseInt(split[0]) == id) {
                if(Integer.parseInt(split[4]) >= quantity) {
                    return 1;
                } else {
                    return -1;
                }
            }
            line = reader.readLine();
        }
        return 0;
    }

    //remove product from cart
    public boolean removeProductFromCart(String username, int id) throws IOException {
        reader = new BufferedReader(new FileReader(cart));
        writer = new BufferedWriter(new FileWriter(tempFile));
        boolean isProductRemoved = false;
        tempFile.createNewFile();
        String line = reader.readLine();
        while(line != null) {

            String[] split = line.split("\\|");
            if(split[0].equals(username) && split[1].equals(id + "")) {
                line = reader.readLine();
                isProductRemoved = true;
                continue;
            }
            writer.write(line);
            writer.newLine();
            line = reader.readLine();
        }
        writer.close();;
        cart.delete();
        tempFile.renameTo(cart);
        return isProductRemoved;
    }

}