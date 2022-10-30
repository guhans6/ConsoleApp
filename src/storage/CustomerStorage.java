package storage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import ui.ProductView;

public class CustomerStorage extends FileStorage {

    Scanner scanner = new Scanner(System.in);
    private File orders = new File("Files/orders.txt");
    private File cart = new File("Files/cart.txt");
    private File products = new File("Files/products.txt");
    private File tempFile = new File("Files/temp.txt");
    private BufferedReader reader;
    private BufferedWriter writer;

    //this method gets all the products in the cart in the cart file of the username and displays total price
    public void getCart(String username) throws IOException {
        reader = new BufferedReader(new FileReader(cart));
        String line = reader.readLine();
        double total = 0;

        while(line != null) {
            String[] id = line.split("\\|");
            if(id[0].equals(username)) {
                String[] product = findProdcutByID(id[1]);
                ProductView.displayProduct(product);
                total += Double.parseDouble(product[8]);
            }
            line = reader.readLine();
        }
        if(total == 0) {
            System.out.println("Cart is empty.");
        } else {
            checkout(total, username);
        }
    }

    private void checkout(double total, String username) throws IOException {
        int deliveryDay;

        System.out.println("Total Price : " + total);
        System.out.println("Do you want to buy the products in the cart? (y/n)");
        String choice = scanner.nextLine();

        if(choice.equalsIgnoreCase("y")) {
            buyProducts(username);
            deliveryDay = (int)(Math.random() * 3) + 1;
            System.out.println("Your order will be delivered within " + deliveryDay + " days.");
            System.out.println("Thank you for shopping with us.");
        } else if(choice.equalsIgnoreCase("n")) {
            System.out.println("Come back later.");
        } else {
            System.out.println("Enter correct choice! (y/n)");
        }
    }
    
    public void viewOrders(String username) throws IOException {
        boolean found = false;
        reader = new BufferedReader(new FileReader(orders));
        String line = reader.readLine();

        while(line != null) {

            String[] split = line.split("\\|");
            if(split[0].equals(username)) {
                //displays the order time and date
                System.out.println("Order Date : " + split[2]);
                String product[] = findProdcutByID(split[1]);
                ProductView.displayProduct(product);
                found = true;
            }
            line = reader.readLine();
        }
        if(!found) {
            System.out.println("No orders history found.");
        }
    }

    public void addToCart(String username, int id) throws IOException {
        if(checkStock(id)) {
            //add product and usernmae in cart file
            writer = new BufferedWriter(new FileWriter(cart, true));
            writer.write(username + "|" + id);
            writer.newLine();
            writer.flush();
            System.out.println("Product added to cart successfully.");

        }
    }

    //calculate total price of the products in the cart by username and display it if user wants to buy add it to orders file
    public void buyProducts(String username) throws IOException {
        reader = new BufferedReader(new FileReader(cart));
        String line = reader.readLine();
        
        while(line != null) {

            String[] split = line.split("\\|");
            if(split[0].equals(username)) {
                addOrder(username,split[1]);    //add product to orders file
                removeStock(split[1]);          //remove stock from products file
                clearCart(username);            //clear cart
            }   
            line = reader.readLine();
        }
    }

    //remove product from cart
    private void clearCart(String username) throws IOException {
        reader = new BufferedReader(new FileReader(cart));
        writer = new BufferedWriter(new FileWriter(tempFile));
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
        writer.flush();
        cart.delete();
        tempFile.renameTo(cart);
    }

    //this method add the order to orders file
    private void addOrder(String username, String productId) throws IOException {
        writer = new BufferedWriter(new FileWriter(orders, true));
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        writer.write(username + "|" + productId + "|" + dtf.format(LocalDateTime.now()));
        writer.newLine();
        writer.flush();
    }

    //this method checks if stock is available for the product
    private boolean checkStock(int id) throws IOException {
        reader = new BufferedReader(new FileReader(products));
        String line = reader.readLine();
        
        while(line != null) {

            String[] split = line.split("\\|");
            if(Integer.parseInt(split[1]) == id) {
                if(Integer.parseInt(split[4]) > 0) {
                    return true;
                } else {
                    System.out.println("Product is out of stock.");
                    return false;
                }
            }
            line = reader.readLine();
        }
        System.out.println("Product not found.");
        return false;
    }

    //this method removes the product from stock
    public void removeStock(String id) throws IOException {
        reader = new BufferedReader(new FileReader(products));
        writer = new BufferedWriter(new FileWriter(tempFile));
        String stockString;

        tempFile.createNewFile();
        String line = reader.readLine();
        while(line != null) {

            String[] split = line.split("\\|");
            if(split[1].equals(id)) {
                int stock = Integer.parseInt(split[4]);
                stockString = getStockString(split);
                stock--;
                line = split[0] + "|" + split[1] + "|" + split[2] + "|" + split[3] + "|" + stock + "|" + stockString;
            }
            writer.write(line);
            writer.newLine();
            line = reader.readLine();
        }
        writer.flush();
        products.delete();
        tempFile.renameTo(products);
    }

    //close all the objects
    public void close() throws IOException {
        reader.close();
        writer.close();
    }
}
