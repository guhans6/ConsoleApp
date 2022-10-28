package user;


import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

import storage.FileStorage;

public class Customer implements User {

    //create variables and methods that implements user
    private String customerName;
    private String customerUserName;
    private String customerEmail;
    private String customerPassword;
    private String address;

    @Override
    public void setName(String customerName) {
        this.customerName = customerName;
    }

    @Override
    public String getName() {
        return customerName;
    }

    @Override
    public void setUserName(String customerUserName) {
        this.customerUserName = customerUserName;
    }

    @Override
    public String getUserName() {
        return customerUserName;
    }

    @Override
    public void setEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    @Override
    public String getEmail() {
        return customerEmail;
    }

    @Override
    public void setPassword(String customerPassword) {
        this.customerPassword = customerPassword;
    }

    @Override
    public String getPassword() {
        return customerPassword;
    }

    @Override
    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String getAddress() {
        return address;
    }

    public void register(short userType) throws InputMismatchException {
        System.out.println("Welcome! Enter the following details ");
        Customer customer = new Customer();
        Scanner scanner = new Scanner(System.in);
        //get customer details
        try {
            System.out.print("Enter your name: ");
            customer.setName(scanner.nextLine());
            System.out.print("Enter your user name: ");
            customer.setUserName(scanner.nextLine());
            System.out.print("Enter your Password ");
            customer.setPassword(scanner.nextLine());
            System.out.print("Enter your email: ");
            customer.setEmail(scanner.nextLine());
            System.out.print("Enter your address: ");
            customer.setAddress(scanner.nextLine());

            FileStorage.getInstance().addUser(customer, userType);
        } catch(IOException e) {
            System.out.println("Error occured! Please try again!");
        }
    }

    @Override
    public String toString() {
        return this.getName() + "|" + this.getUserName() +"|" + this.getPassword() 
               + "|" + this.getEmail() + "|" + this.getAddress();
    }
}
