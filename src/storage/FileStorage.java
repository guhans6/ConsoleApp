package storage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import user.User;

public class FileStorage {

    private static FileStorage fileStorage = null;
    private File customers = new File("Files/customers.txt");
    private File sellers = new File("Files/sellers.txt");
    private File admins = new File("Files/admins.txt");
    private File products = new File("Files/products.txt");

    //singleton constructor
    private FileStorage() {
    }

    //singleton method
    public static FileStorage getInstance() {
        if(fileStorage == null) {
            fileStorage = new FileStorage();
        }
        return fileStorage;
    }
    
    //method to add user to the file according to the type
    public void addUser(User user, short userType) throws IOException {
        File file = getFileByType(userType);
        BufferedWriter writer;

        if(!checkUserExists(user, file)){
            writer = new BufferedWriter(new FileWriter(file, true));
            writer.write(user.toString());
            writer.newLine();
            writer.close();
            System.out.println("Registered Successfully!");
            return;
        }
        return;
    }

    //check if user exists by username,email,number
    public boolean checkUserExists(User user,File file) throws IOException {
        String split[];
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line = reader.readLine();

        while(line != null) {

            split = line.split("\\|");
            if(split[1].equals(user.getUserName())) {
                System.out.println("Username already exists.");
                reader.close();
                return true;
            }
            else if(split[3].equals(user.getEmail())) {
                System.out.println("Email already assoicated with another account.");
                reader.close();
                return true;
            }
            line = reader.readLine();
        }
        reader.close();
        return false; 
    }

    public short checkUser(String username, String password, short userType) throws IOException {
        File file = getFileByType(userType);
        String line;
        String[] split;
        BufferedReader reader = new BufferedReader(new FileReader(file));
        line = reader.readLine();
        while(line != null) {
            split = line.split("\\|");
            if(split[1].equals(username) && split[2].equals(password)) {
                System.out.println("Login successful!");
                reader.close();
                return userType;
            }
            line = reader.readLine();
        }
        reader.close();
        System.out.println("Invalid username or password.");
        return -1;
    }

    public void displayProduct(String[] split) {
        //this method displays the array with beautiful alignment like a table
        System.out.println("====================================================================================================");
        System.out.println("Product Id : " + split[1] + "\tBrand : " + split[9] +  "\t\tProduct Name : " + split[2] + "\tProduct Price : " + split[3]);
        System.out.println("Description : " + split[5]);
        System.out.println("Discount : " + split[7] +"%" +  "\t\tDiscounted Price : " + split[8] + "\t\tQuantity : " + split[4]);
        System.out.println("====================================================================================================");
    }

    public void deatileProductView(String productType) throws IOException {
        boolean flag = false;
        BufferedReader reader = new BufferedReader(new FileReader(products));
        String line = reader.readLine();

        while(line != null) {

            String[] split = line.split("\\|");
            if(split[6].equals(productType)) {
                displayProduct(split);
                flag = true;
            }
            line = reader.readLine();
        }
        reader.close();
        if(!flag) {
            System.out.println("Sorry no products available!");
        }
    }

    public void getProducts(String sellerName) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(products));
        String line = reader.readLine();
        while(line != null) {

            String[] split = line.split("\\|");
            if(split[0].equals(sellerName )) {
                displayProduct(split);
            }
            line = reader.readLine();
        }
        reader.close();
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
        BufferedReader reader = new BufferedReader(new FileReader(products));
        String line = reader.readLine();

        while(line != null) {

            String[] split = line.split("\\|");
            if(split[1].equals(productId)) {
                reader.close();
                return split;
            }
        line = reader.readLine();
        }
    reader.close();
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
        BufferedReader reader = new BufferedReader(new FileReader(products));
        String line = reader.readLine();
        String id = String.valueOf(productId);
        boolean flag = false;

        while(line != null) {

            String[] split = line.split("\\|");
            if(split[0].equals(username) && split[1].equals(id) || username.equals("Admin")) {
                flag = true;
                break;
            }
            line = reader.readLine();
        }
        reader.close();
        return flag;
    }
}