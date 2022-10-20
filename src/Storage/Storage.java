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
            fileReader = new FileReader(products);
            reader = new BufferedReader(fileReader);
            String line = reader.readLine();
            while(line != null) {

                String[] split = line.split("\\|");
                if(split[0].equals(username)) {
                    id = Integer.parseInt(split[1]);
                }
                line = reader.readLine();
            }
            reader.close();
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

    //add products to seller file to specific seller by username
    // public static boolean addProduct(String username, Product product) {
    //     product.setProductID(++id);
    //     String productString = product.toString();
    //     try {
    //         // creating temp file and copies all data from seller file to temp file except the product
    //         fileReader = new FileReader(sellers);
    //         reader = new BufferedReader(fileReader);
    //         File temp = new File("temp.txt");
    //         temp.createNewFile();
    //         FileWriter fileWriter = new FileWriter(temp);
    //         BufferedWriter writer = new BufferedWriter(fileWriter);
    //         String line = reader.readLine();
    //         while(line != null) {
    //             String[] split = line.split("\\|");
    //             if(split[1].equals(username)) {
    //                 if(split[5].equals("null")){
    //                     split[5] = productString;
    //                 }
    //                 else {
    //                     split[5] += "," + productString;
    //                 }
    //                 line = split[0] + "|" + split[1] + "|" + split[2] + "|" + split[3] + "|" + split[4] + "|" + split[5] + "|";
    //             }
    //             writer.write(line);
    //             writer.newLine();
    //             line = reader.readLine();
    //         }
    //         writer.close();
    //         reader.close();
    //         fileReader.close();
    //         fileWriter.close();
    //         sellers.delete();
    //         temp.renameTo(sellers);
    //     } catch(IOException e) {

    //         System.out.println("Error can't find user!");
    //     }
    //     return false;
    // }
}