package user;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

import storage.FileStorage;

public class Seller implements User{

    //create variables and methods that implements user
    private String sellerName;
    private String sellerUserName;
    private String sellerEmail;
    private String sellerPassword;
    private String sellerOfficeAddress;

    @Override
    public void setName(String sellerName) {
        this.sellerName = sellerName;
    }

    @Override
    public String getName() {
        return sellerName;
    }

    @Override
    public void setUserName(String sellerUserName) {
        this.sellerUserName = sellerUserName;
    }

    @Override
    public String getUserName() {
        return sellerUserName;
    }

    @Override
    public void setEmail(String sellerEmail) {
        this.sellerEmail = sellerEmail;
    }

    @Override
    public String getEmail() {
        return sellerEmail;
    }

    @Override
    public void setPassword(String sellerPassword) {
        this.sellerPassword = sellerPassword;
    }

    @Override
    public String getPassword() {
        return sellerPassword;
    }

    @Override
    public void setAddress(String sellerOfficeAddress) {
        this.sellerOfficeAddress = sellerOfficeAddress;
    }

    @Override
    public String getAddress() {
        return sellerOfficeAddress;
    }
    
    //seller registration get input from user
    public void register(short userType) throws InputMismatchException {
        System.out.println("Welcome! Enter the following details ");
        Scanner input = new Scanner(System.in);
        try {
            System.out.println("Enter your name:");
            setName(input.nextLine());
            System.out.println("Enter your username:");
            setUserName(input.nextLine());
            System.out.println("Enter your email:");
            setEmail(input.nextLine());
            System.out.println("Enter your password:");
            setPassword(input.nextLine());
            System.out.println("Enter your office address:");
            setAddress(input.nextLine());
            if(getName().equals("") || getUserName().equals("") || getEmail().equals("") 
                    || getPassword().equals("") || getAddress().equals("")) {
                System.out.println("Please fill all the fields!");
            } else {
                FileStorage.getInstance().addUser(this, userType);
                System.out.println("Registration successful!");
            }
        } catch (IOException e) {
            System.out.println("Error occured! Please try again!"); 
        }
    }

    @Override
    public String toString() {
        return this.getName() + "|" + this.getUserName() +"|" + this.getPassword() +
               "|" + this.getEmail() + "|" + this.getAddress() + "|" + "null" + "|";
    }
    
}