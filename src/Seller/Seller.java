package Seller;
import java.util.InputMismatchException;
import java.util.Scanner;

import Storage.Storage;
import Users.User;

public class Seller implements User{

    //create variables and methods that implements user
    private String sellerName;
    private String sellerUserName;
    private String sellerEmail;
    private String sellerPassword;
    private String sellerOfficeAddress;
    // private ArrayList<Product> products;

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
    public void sellerRegistration() {
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
        } catch (InputMismatchException e) {
            System.out.println("Please enter valid input!");
        }
        //check every input is not empty
        if(getName().equals("") || getUserName().equals("") || getEmail().equals("") || getPassword().equals("") || getAddress().equals("")) {
            System.out.println("Please fill all the fields!");
        } else {
        //save seller to seller file
            if(Storage.saveSeller(this)) {
                System.out.println("You have registered successfully!");
            }
        }
    }
}
