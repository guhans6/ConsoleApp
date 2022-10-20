package Users;
import java.util.InputMismatchException;
import java.util.Scanner;

import Customer.Customer;
import Seller.Laptop;
import Seller.Seller;
import Storage.Storage;

public class userMenu {
    
    private static Scanner scanner = new Scanner(System.in);
    private static short choice;
    public static void displayMainMenu(){
        Scanner scanner = new Scanner(System.in);
        short input=0;
        boolean b=true;
        while(b)
        {
            System.out.println("\n1. Login");
            System.out.println("2. Register");
            System.out.println("3. Exit Application");
            try {
                input = scanner.nextShort(); }
            catch(InputMismatchException e) {
                System.out.println("Enter from given options!");
            }

            switch(input) {
                case 1:
                    short userType = userType();
                    System.out.println("Enter username: ");
                    scanner.nextLine();
                    String username = scanner.nextLine();
                    System.out.println("Enter password: ");
                    String password = scanner.nextLine();
                    int checker = Storage.checkUser(username, password, userType);
                    if(checker == 1) {
                        System.out.println("Login successful!");
                        customerMenu();
                    }
                    else if(checker == 2) {
                        System.out.println("Seller Login Successfull");
                        sellerMenu(username);
                    }
                    else if(checker == -1){
                        System.out.println("Login failed!");
                    }
                    break;
                case 2:
                    registerUser();
                    break;
                case 3:
                    b = false;
                    System.out.println("Bye!");
                    break;
                default:
                    System.out.println("Enter from given options!");
            }
        }
        scanner.close();
    }

    public static void customerMenu(){
        System.out.println("\n1. View Products");
        System.out.println("2. View Cart");
        System.out.println("3. View Orders");
        System.out.println("4. Logout");
        System.out.println("5. Exit");
    }
    
    public static void sellerMenu(String username){
        System.out.println("Welcome "+username);
        // while(true) 
        System.out.println("\n1. Add Product");
        System.out.println("2. View Products");
        System.out.println("3. View Orders");
        System.out.println("4. Logout");
        System.out.println("5. Exit");
        choice = scanner.nextShort();
        switch(choice) {
            case 1:
                System.out.println("Enter product type:\n1. Laptop\n2. Mobile");
                short productChoice = scanner.nextShort();
                if(productChoice == 1) {
                    addLaptop(username);
                }
                else if(productChoice == 2) {

                }
                else {
                    System.out.println("Enter from given options!");
                }
                break;
            case 2:
                // Storage.viewProducts(Seller.getSellerUserName());
                break;
            case 3:
                // Storage.viewOrders(Seller.getSellerUserName());
                break;
            case 4:
                System.out.println("Logged out!");
                break;
            case 5:
                System.out.println("Bye!");
                break;
            default:
                System.out.println("Enter from given options!");
        }
    }
    


    public static void productMenu(){
        System.out.println("\n1. Add Product");
        System.out.println("2. View Products");
        System.out.println("3. Update Product");
        System.out.println("4. Remove Product");
        System.out.println("5. Exit");
    }
    
    public static void orderMenu(){
        System.out.println("\n1. View Orders");
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

    public static short userType() { 

        System.out.println("\nSelect user type.");
        System.out.println("1. Customer");
        System.out.println("2. Seller");
        System.out.println("3. Exit this menu");
        try {
            return scanner.nextShort();
        }
        catch(InputMismatchException e) {
            System.out.println("Enter corrct option!");
        }
        return 3;
    }

    public static boolean registerUser() {
            
        int userType = userMenu.userType();
        if(userType == 1) {
            Customer customer =  new Customer();
            customer.customerRegistration();
        }
        else if(userType == 2) {
            Seller seller = new Seller();
            seller.sellerRegistration();
        }
        else if(userType == 3) {
                        
        }
        return false;
    }

    private static void addLaptop(String username) {
        Laptop laptop = new Laptop();
        System.out.println("Enter Laptop Brand: ");
        laptop.setProductBrand(scanner.next());
        System.out.println("Enter Laptop Name: ");
        laptop.setProductName(scanner.next());
        System.out.println("Enter Laptop Price: ");
        laptop.setPrice(scanner.nextFloat());
        System.out.println("Enter Laptop Quantity: ");
        laptop.setQuantity(scanner.nextInt());
        System.out.println("Enter Laptop RAM: ");
        laptop.setLaptopRam(scanner.next());
        System.out.println("Enter Laptop Processor: ");
        laptop.setLaptopProcessor(scanner.next());
        System.out.println("Enter Laptop Hard Disk: ");
        laptop.setLaptopRom(scanner.next());
        System.out.println("Enter Laptop Screen Size: ");
        laptop.setLaptopDisplay(scanner.next());
        System.out.println("Enter Laptop Operating System: ");
        laptop.setLaptopOs(scanner.next());
        System.out.println("Enter Laptop Graphics Card(If not enter null): ");
        laptop.setLaptopGraphicsCard(scanner.next());
        System.out.println("Enter Laptop Battery: ");
        laptop.setLaptopBattery(scanner.next());
        System.out.println("Enter Laptop Color: ");
        laptop.setLaptopColor(scanner.next());
        System.out.println("Enter Laptop Warranty: ");
        laptop.setLaptopWarranty(scanner.next());
        if(Storage.addProduct(username, laptop)) {
            System.out.println("Laptop added successfully!");
        }
        else{
            System.out.println("Laptop not added!");
        }   


    }
}