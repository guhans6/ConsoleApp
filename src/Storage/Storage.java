package Storage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import Customer.Customer;
import Seller.Seller;
import Users.User;
import Users.userMenu;

public class Storage {

    private static Scanner scanner = new Scanner(System.in);
    private static File customers = new File("customers.txt");
    private static File sellers = new File("sellers.txt");
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
                writer.write(seller.getName() + "|" + seller.getUserName() +"|" + seller.getPassword() +  "|" + seller.getEmail() + "|" + seller.getAddress() + "|");
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

            fileReader = new FileReader(file);
            reader = new BufferedReader(fileReader);
            String line = reader.readLine();
            while(line != null) {

                String[] split = line.split("|");
                System.out.println(split[1]);
                if(split[1].equals(user.getUserName())) {
                    System.out.println("Username already exists.");
                    return false;
                }
                else if(split[2].equals(user.getEmail())) {
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
    
    //check seller and password returns boolean
    public static boolean checkUser() {
        userMenu.userType();
        int input = scanner.nextInt();
        System.out.println("Enter username");
        String userName = scanner.next();
        System.out.println("Enter password");
        String password = scanner.next();

        try {
            if(input == 1) {
                fileReader = new FileReader(customers);
            } else {
                fileReader = new FileReader(sellers);
            }
            reader = new BufferedReader(fileReader);
            String line = reader.readLine();
            while(line != null) {

                String[] split = line.split("\\|");
                if(split[1].equals(userName)) {

                    if(split[2].equals(password)) {
                        reader.close();
                        return true;
                    }
                }
                line = reader.readLine();
            }
            reader.close();
        } catch(IOException e) {

            System.out.println("Error can't find user!");
        }
        return false;
    }
}