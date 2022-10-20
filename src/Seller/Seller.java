package Seller;
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
        //save seller to seller file
        if(Storage.saveSeller(this)) {
            System.out.println("You have registered successfully!");
        }
    }


    // //add product to products file
    // public void addProduct(Product product) {
    //     products.add(product);
    // }
    
    // //delete product from products file
    // public void deleteProduct(Product product) {
    //     products.remove(product);
    // }
    
    // //edit product from products file
    // public void editProduct(Product product) {
    //     products.set(products.indexOf(product),product);
    // }
}
