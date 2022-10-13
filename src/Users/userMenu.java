package Users;
import java.util.Scanner;

import Customer.Customer;
import Seller.Seller;
import Storage.Storage;

public class userMenu {
    
    public static void displayMainMenu(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("1. Login");
        System.out.println("2. Register");
        System.out.println("3. Exit");
        int input = scanner.nextInt(),input2;

        if(input == 1)
        {
            if(Storage.checkUser())
            {
                System.out.println("Logged in successfully");
            }
            else 
            {
                System.out.println("Enter correct username or password");
            }
        }
        else if(input == 2)
        {
            userType();
            input2 = scanner.nextInt();
            if(input2 == 1){
                new Customer().customerRegistration();
            }
            else {
                new Seller().sellerRegistration();
            }
        }
        else if(input == 3)
        {
            System.exit(0);
        }
        else
        {
            System.out.println("Invalid input");
            displayMainMenu();

        }

        scanner.close();
    }
    
    public static void customerMenu(){
        System.out.println("1. View Products");
        System.out.println("2. View Cart");
        System.out.println("3. View Orders");
        System.out.println("4. Logout");
        System.out.println("5. Exit");
    }
    
    public static void SellerMenu(){
        System.out.println("1. Add Product");
        System.out.println("2. View Products");
        System.out.println("3. View Orders");
        System.out.println("4. Logout");
        System.out.println("5. Exit");
    }
    
    public static void productMenu(){
        System.out.println("1. Add Product");
        System.out.println("2. View Products");
        System.out.println("3. Update Product");
        System.out.println("4. Remove Product");
        System.out.println("5. Exit");
    }
    
    public static void orderMenu(){
        System.out.println("1. View Orders");
        System.out.println("2. Update Order Status");
        System.out.println("3. Exit");
    }
    
    public static void cartMenu(){
        System.out.println("1. Add Product");
        System.out.println("2. Remove Product");
        System.out.println("3. View Cart");
        System.out.println("4. Checkout");
        System.out.println("5. Exit");
    }

    public static void userType() {
        System.out.println("Select user type.");
        System.out.println("1. Customer");
        System.out.println("2. Seller");
    }

    


}