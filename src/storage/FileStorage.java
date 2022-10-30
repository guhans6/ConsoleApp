package storage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import models.user.User;
import ui.ProductView;

public class FileStorage {

    private File customers = new File("Files/customers.txt");
    private File sellers = new File("Files/sellers.txt");
    private File admins = new File("Files/admins.txt");
    private File products = new File("Files/products.txt");
    BufferedReader reader;
    BufferedWriter writer;

    
    //method to add user to the file according to the type
    public void addUser(User user, short userType) {
        try {
            File file = getFileByType(userType);

            if(!checkUserExists(user, file)){
                writer = new BufferedWriter(new FileWriter(file, true));
                writer.write(user.toString());
                writer.newLine();
                writer.flush();
                System.out.println("Registered Successfully!");
            }
        } catch (IOException e) {
            System.out.println("Error while adding user. Please try again.");
        }
    }

    //check if user exists by username,email,number
    public boolean checkUserExists(User user,File file) throws IOException {
        String split[];
        reader = new BufferedReader(new FileReader(file));
        String line = reader.readLine();

        while(line != null) {

            split = line.split("\\|");
            if(split[1].equals(user.getUserName())) {
                System.out.println("Username already exists.");
                return true;
            }
            else if(split[3].equals(user.getEmail())) {
                System.out.println("Email already assoicated with another account.");
                return true;
            }
            line = reader.readLine();
        }
        return false; 
    }

    public short checkUser(String username, String password, short userType) throws IOException {
        File file = getFileByType(userType);
        String line;
        String[] split;
        reader = new BufferedReader(new FileReader(file));

        line = reader.readLine();
        while(line != null) {
            split = line.split("\\|");
            if(split[1].equals(username) && split[2].equals(password)) {
                System.out.println("Login successful!");
                return userType;
            }
            line = reader.readLine();
        }
        System.out.println("Invalid username or password.");
        return -1;
    }

    public void deatiledProductView(String productType) throws IOException {
        boolean flag = false;
        reader = new BufferedReader(new FileReader(products));
        String line = reader.readLine();

        while(line != null) {

            String[] split = line.split("\\|");
            if(split[6].equals(productType)) {
                ProductView.displayProductDetails(split);
                flag = true;
            }
            line = reader.readLine();
        }
        if(!flag) {
            System.out.println("No products found.");
        }
    }

    public void getProducts(String sellerName) throws IOException {
        reader = new BufferedReader(new FileReader(products));
        String line = reader.readLine();
        boolean flag = false;

        while(line != null) {

            String[] split = line.split("\\|");
            if(split[0].equals(sellerName )) {
                ProductView.displayProductDetails(split);
                flag = true;
            }
            line = reader.readLine();
        }
        if(!flag) {
            System.out.println("No products found.");
        }
    }

    File getFileByType(short userType) throws FileNotFoundException {
        if(userType == 1) {
            return customers;
        }
        else if(userType == 2) {
            return sellers;
        }
        else if(userType == 3) {
            return admins;
        }
        else {
            System.out.println("Wrong user type.");
        }
        return null;
    }

    public String[] findProdcutByID(String productId) throws IOException {
        reader = new BufferedReader(new FileReader(products));
        String line = reader.readLine();

        while(line != null) {

            String[] split = line.split("\\|");
            if(split[1].equals(productId)) {
                return split;
            }
        line = reader.readLine();
        }
    System.out.println("Enter Correct ID!");
    return null;
    }

    //add stoock array and return it
    public  String getStockString(String[] split) {
        String stock = "";
        stock +=split[5] + "|" + split[6] + "|" + split[7] + "|" + split[8] + "|" + split[9] + "|" + split[10] + "|" 
                 + split[11] + "|" + split[12] + "|" + split[13] + "|" + split[14] + "|" + split[15] + "|" + split[16];
        return stock;
    }

    public boolean checkUserProduct(String username, int productId) throws FileNotFoundException, IOException {
        //check seller is associated with the product or not
        reader = new BufferedReader(new FileReader(products));
        String line = reader.readLine();
        String id = String.valueOf(productId);

        while(line != null) {

            String[] split = line.split("\\|");
            if(split[0].equals(username) && split[1].equals(id) || username.equals("Admin")) {
                return true;
            }
            line = reader.readLine();
        }
        return false;
    }

    public void close() throws IOException {
        reader.close();
        writer.close();
    }
}