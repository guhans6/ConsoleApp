package Storage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import Customer.Customer;
import Seller.Product;
import Seller.Seller;
import Users.User;

public class Storage {

    private static int id = 0;
    private static Scanner scanner = new Scanner(System.in);
    private static File customers = new File("customers.txt");
    private static File sellers = new File("sellers.txt");
    private static File admins = new File("admins.txt");
    private static File products = new File("products.txt");
    private static FileReader fileReader;
    private static BufferedReader reader;
    private static BufferedWriter writer;
    
    //save customer to customers file
    public static boolean saveCustomer(Customer customer) {
        
        if(userExists(customer, customers)){
            try {

                FileWriter fileWriter = new FileWriter(customers, true);
                BufferedWriter writer = new BufferedWriter(fileWriter);
                writer.write(customer.getName() + "|" + customer.getUserName() +"|" + customer.getPassword() + "|" + customer.getEmail() + "|" + customer.getAddress() + "|");
                writer.newLine();
                writer.close();
                return true;
            } catch(IOException e) {

                System.out.println("Error can't save user!");
            }
        }
        return false;
    }
    
    //save seller to seller file
    public static boolean saveSeller(Seller seller) {

        if(userExists(seller, sellers)) {
            try {

                FileWriter fileWriter = new FileWriter(sellers, true);
                BufferedWriter writer = new BufferedWriter(fileWriter);
                writer.write(seller.getName() + "|" + seller.getUserName() +"|" + seller.getPassword() +  "|" + seller.getEmail() + "|" + seller.getAddress() + "|" + "null" + "|");
                writer.newLine();
                writer.close();
                return true;
            } catch(IOException e) {

                System.out.println("Error can't save user!");
            }
        }
        return false;
    }

    //save admin
    public static boolean saveAdmin(User admin) {

        if(userExists(admin, admins)) {
            try {

                FileWriter fileWriter = new FileWriter(admins, true);
                BufferedWriter writer = new BufferedWriter(fileWriter);
                writer.write(admin.getName() + "|" + admin.getUserName() +"|" + admin.getPassword() +  "|" + admin.getEmail() + "|" + admin.getAddress() );
                writer.newLine();
                writer.close();
                return true;
            } catch(IOException e) {

                System.out.println("Error can't save user!");
            }
        }
        return false;
    }

    //check if user exists by username,email,number
    public static boolean userExists(User user,File file) {

        try {
            System.out.println(user.getUserName());
            fileReader = new FileReader(file);
            reader = new BufferedReader(fileReader);
            String line = reader.readLine();
            while(line != null) {

                String[] split = line.split("\\|");
                if(split[1].equals(user.getUserName())) {
                    System.out.println("Username already exists.");
                    return false;
                }
                else if(split[3].equals(user.getEmail())) {
                    System.out.println("Email already assoicated with another account.");
                    return false;
                }
                line = reader.readLine();
            }
            reader.close();
        } catch(IOException e) {

            System.out.println("Error can't find user!");
        }
        return true; 
    }

    public static short checkUser(String username, String password, short userType) {
        try {
            
            if(userType == 1) {
                // System.out.println("Customer Portal.");
                fileReader = new FileReader(customers);
            }
            else if(userType == 2) {
                // System.out.println("Seller Portal.");
                fileReader = new FileReader(sellers);
            }
            else if(userType == 3) {
                // System.out.println("Admin Portal");
            }
            else {
                return 0;
            }
    
            reader = new BufferedReader(fileReader);
            String line = reader.readLine();
            while(line != null) {

                String[] split = line.split("\\|");
                if(split[1].equals(username) && split[2].equals(password)) {
                    return userType;
                }
                line = reader.readLine();
            }
            reader.close();
        } catch(IOException e) {

            System.out.println("Error can't find user!");
        }
        return -1;
    }
    
    //add products to products file with usename as identification
    public static boolean addProduct(String username, Product product) {
        try {
            int id = getAvailavleID();
            product.setProductID(++id);
            FileWriter fileWriter = new FileWriter(products, true);
            BufferedWriter writer = new BufferedWriter(fileWriter);
            writer.write(username + "|" + product.toString());
            writer.newLine();
            writer.close();
            return true;
        } catch(IOException e) {

            System.out.println("Error can't save product!");
        }
        return false;
    }

    private static int getAvailavleID() {
        //read products file and get the last id and add 1 to it
        try {
            reader = new BufferedReader(new FileReader(products));
            String line = reader.readLine();
            while(line != null) {

                String[] split = line.split("\\|");
                id = Integer.parseInt(split[1]);
                line = reader.readLine();
            }
            reader.close();
        } catch(IOException e) {

            System.out.println("Error can't find user!");
        }
        System.out.println(id);
        return id;
    }

    public static void getProducts(String username) {
        try {
            reader = new BufferedReader(new FileReader("products.txt"));
            String line = reader.readLine();
            while(line != null) {

                String[] split = line.split("\\|");
                if(split[0].equals(username)) {
                    displayProduct(split);
                }
                line = reader.readLine();
            }
            reader.close();
        } catch(IOException e) {

            System.out.println("Error can't read!");
        }
    }

    private static void displayProduct(String[] split) {
        //this method displays the array with beautiful alignment like a table
        System.out.println("====================================================================================================");
        System.out.println("Product Id : " + split[1] + "\tProduct Name : " + split[2] + "\tProduct Price : " + split[3]);
        System.out.println("Description : " + split[5]);
        System.out.println("Discount : " + split[6] + "\tRating : " + split[7] + "\tAvailable : " + split[4]);
        System.out.println("====================================================================================================");
    }

    public static void deatileProductView(short type) {
        try {
            reader = new BufferedReader(new FileReader("products.txt"));
            String line = reader.readLine();
            String productType;
            if(type == 1) { productType = "Laptop"; }
            else if(type == 2) { productType = "Mobile"; }
            else {
                System.out.println("Enter Correct Type!");
                return;
            }
            while(line != null) {

                String[] split = line.split("\\|");
                if(split[0].equals(productType)) {
                    displayProduct(split);
                }
                line = reader.readLine();
            }
            reader.close();
        } catch(IOException e) {

            System.out.println("Error can't find products!");
        }
    }

    public static void deleteProduct(String username, int id) {
        //this method deletes the product from the file using temp file
        try {
            reader = new BufferedReader(new FileReader(products));
            File tempFile = new File("temp.txt");
            writer = new BufferedWriter(new FileWriter(tempFile));
            tempFile.createNewFile();
            String line = reader.readLine();
            while(line != null) {

                String[] split = line.split("\\|");
                if(split[0].equals(username) && Integer.parseInt(split[1]) == id) {
                    line = reader.readLine();
                    continue;
                }
                writer.write(line);
                writer.newLine();
                line = reader.readLine();
            }
            reader.close();
            writer.close();
            products.delete();
            tempFile.renameTo(products);
            System.out.println("Product deleted successfully.");
        } catch(IOException e) {

            System.out.println("Error can't find products!");
        }
    }

}