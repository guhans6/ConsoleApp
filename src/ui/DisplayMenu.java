package ui;

import java.util.InputMismatchException;
import java.util.Scanner;

public class DisplayMenu {
    
    private static DisplayMenu displayMenu = null;
    Scanner scanner = new Scanner(System.in);

    private DisplayMenu() {
    }

    public static DisplayMenu getInstance() {
        if(displayMenu == null) {
            displayMenu = new DisplayMenu();
        }
        return displayMenu;
    }

    public void displayMainMenu() {
        System.out.println("\n1. Login");
        System.out.println("2. Register");
        System.out.println("3. Exit Application");
    }

    public void displayUsertypeMenu() {
        System.out.println("\nSelect user type.");
        System.out.println("1. Customer");
        System.out.println("2. Seller");
        System.out.println("3. Admin");
        System.out.println("4. Exit this menu");
    }


    public void displayCustomerMenu() {
        System.out.println("\n1. View Products");
        System.out.println("2. Add Products To Cart");
        System.out.println("3. Buy Products in the cart");
        System.out.println("4. View Orders");
        System.out.println("5. Logout");
        System.out.println("6. Exit");
    }


    public void displayAdminMenu() {
        System.out.println("\n1. View Products");
        System.out.println("2. See all Sellers");
        System.out.println("3. See all Customers");
        System.out.println("4. Delete Product");
        System.out.println("5. Delete User");
        System.out.println("6. Logout");
        System.out.println("7. Exit");
    }

    public void displaySellerMenu() {
        System.out.println("\n1. Add Product");
        System.out.println("2. View Products");
        System.out.println("3. Update Stock");
        System.out.println("4. Delete Product");
        System.out.println("5. Logout");
        System.out.println("6. Exit");
    }

    public String getProductType() throws InputMismatchException {
        System.out.println("Enter type of product: \n1. Laptop \n2. Mobile");
        
        try {
            short choice = scanner.nextShort();
            switch(choice) {
                case 1:
                    return "Laptop";
                case 2:
                    return "Mobile";
                default:
                    System.out.println("Enter from given options!");
            }
        } catch(InputMismatchException e) {
            System.out.println("Enter from given options!");
            scanner.nextLine();
        }
        return null;
    }
}
