package Users;
import java.util.InputMismatchException;
import java.util.Scanner;

import Customer.Customer;
import Seller.Laptop;
import Seller.Mobile;
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
                input = scanner.nextShort();
                switch(input) {
                    case 1:
                        short userType = userType();
                        if(userType == 3) { break ; }
                        if(userType > 3) {
                            System.out.println("Invalid input");
                            break;
                        }
                        System.out.println("Enter username: ");
                        scanner.nextLine();
                        String username = scanner.nextLine();
                        System.out.println("Enter password: ");
                        String password = scanner.nextLine();
                        int checker = Storage.checkUser(username, password, userType);
                        if(checker == 1) {
                            System.out.println("Login successful!");
                            customerMenu(username);
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
            } catch(InputMismatchException e) {
                System.out.println("Enter from given options!");
                scanner.nextLine();
            }
        }
        scanner.close();
    }

    private static void customerMenu(String username){
        short productId;
        while(true) {
            System.out.println("\n1. View Products");
            System.out.println("2. Add Cart");
            System.out.println("3. Buy Products in the cart");
            System.out.println("4. View Orders");
            System.out.println("5. Logout");
            System.out.println("6. Exit");
            try {
                choice = scanner.nextShort();
                switch(choice) {
                    case 1:
                        System.out.println("What do you want to see?\n1. Laptop\n2. Mobile");            
                        short type = scanner.nextShort();
                        Storage.deatileProductView(type);
                        break;
                    case 2:
                        System.out.println("Enter product id to add in cart: ");
                        productId = scanner.nextShort();
                        Storage.addToCart(username, productId);
                        break;
                    case 3:
                        Storage.buyProduct(username);
                        break;
                    case 4:
                        Storage.viewOrders(username);
                        break;
                    case 5:
                        break;
                    case 6:
                        System.out.println("Exit");
                        break;
                    default:
                        System.out.println("Enter from given options!");
                }
            } catch(InputMismatchException e) {
                System.out.println("Enter from given options!");
                scanner.nextLine();
            }
        }
    }
    
    private static void sellerMenu(String username){
        System.out.println("Welcome "+username);
        while(true){
            System.out.println("\n1. Add Product");
            System.out.println("2. View Products");
            System.out.println("3. View Orders");
            System.out.println("4. Delete Product");
            System.out.println("5. Logout");
            System.out.println("6. Exit");
            choice = scanner.nextShort();
            switch(choice){
                case 1:
                    addProduct(username);
                    break;
                case 2:
                    Storage.getProducts(username);
                    break;
                case 3:
                    viewOrders(username);
                    break;
                case 4:
                    System.out.println("Enter product ID to delete: ");
                    int id = scanner.nextInt();
                    Storage.deleteProduct(username, id);
                    break;
                case 5:
                    System.out.println("Logged out successfully!");
                    return;
                case 6:
                    System.out.println("Bye!");
                    System.exit(0);
                default:
                    System.out.println("Enter from given options!");
            }
        }
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

    private static short userType() { 

        System.out.println("\nSelect user type.");
        System.out.println("1. Customer");
        System.out.println("2. Seller");
        System.out.println("3. Exit this menu");
        try {
            return scanner.nextShort();
        }
        catch(InputMismatchException e) {
            System.out.println("Enter corrct option!");
            scanner.nextLine();
        }
        return 3;
    }

    private static boolean registerUser() {
            
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

    public static void addProduct(String username) {
        System.out.println("Enter type of product: \n1. Laptop \n2. Mobile");
        short type = scanner.nextShort();
        scanner.nextLine();
        if(type == 1) {
            addLaptop(username);
        }
        else if(type == 2) {
            addMobile(username);
        }
        else {
            System.out.println("Enter from given options!");
        }
    }

    private static void addLaptop(String username) {
        Laptop laptop = new Laptop();
            try {
            System.out.println("Enter Laptop Brand: ");
            laptop.setProductBrand(scanner.nextLine());
            System.out.println("Enter Laptop Name: ");
            laptop.setProductName(scanner.nextLine());
            System.out.println("Enter Laptop Price: ");
            laptop.setPrice(scanner.nextFloat());
            System.out.println("Enter Laptop Quantity: ");
            laptop.setQuantity(scanner.nextInt());
            System.out.println("Enter discount percentage");
            laptop.setProductDiscount(scanner.nextFloat());
            scanner.nextLine();
            System.out.println("Enter Laptop RAM: ");
            laptop.setLaptopRam(scanner.nextLine());
            System.out.println("Enter Laptop Processor: ");
            laptop.setLaptopProcessor(scanner.nextLine());
            System.out.println("Enter Laptop Hard Disk: ");
            laptop.setLaptopRom(scanner.nextLine());
            System.out.println("Enter Laptop Screen Size: ");
            laptop.setLaptopDisplay(scanner.nextLine());
            System.out.println("Enter Laptop Operating System: ");
            laptop.setLaptopOs(scanner.nextLine());
            System.out.println("Enter Laptop Graphics Card(If not enter null): ");
            laptop.setLaptopGraphicsCard(scanner.nextLine());
            System.out.println("Enter Laptop Battery: ");
            laptop.setLaptopBattery(scanner.nextLine());
            System.out.println("Enter Laptop Color: ");
            laptop.setLaptopColor(scanner.nextLine());
            System.out.println("Enter Laptop Warranty: ");
            laptop.setLaptopWarranty(scanner.nextLine());
            System.out.println("Enter Laptop Discription: ");
            laptop.setProductDescription(scanner.nextLine());
            if(Storage.addProduct(username, laptop)) {
                System.out.println("Laptop added successfully!");
            }
            else{
                System.out.println("Laptop not added!");
            }   
        } catch(InputMismatchException e) {
            System.out.println("Enter correct details!");
            scanner.nextLine();
        }

    }

    //add mobile
    private static void addMobile(String username) {
        Mobile mobile = new Mobile();
        System.out.println("Enter Mobile Brand: ");
        mobile.setProductBrand(scanner.nextLine());
        System.out.println("Enter Mobile Name: ");
        mobile.setProductName(scanner.nextLine());
        System.out.println("Enter Mobile Price: ");
        mobile.setPrice(scanner.nextFloat());
        System.out.println("Enter Mobile Quantity: ");
        mobile.setQuantity(scanner.nextInt());
        scanner.nextLine();
        System.out.println("Enter Mobile RAM: ");
        mobile.setMobileRam(scanner.nextLine());
        System.out.println("Enter Mobile Processor: ");
        mobile.setMobileProcessor(scanner.nextLine());
        System.out.println("Enter Mobile Hard Disk: ");
        mobile.setMobileRom(scanner.nextLine());
        System.out.println("Enter Mobile Screen Size: ");
        mobile.setMobileDisplay(scanner.nextLine());
        System.out.println("Enter Mobile Operating System: ");
        mobile.setMobileOs(scanner.nextLine());
        System.out.println("Enter Mobile Battery: ");
        mobile.setMobileBattery(scanner.nextLine());
        System.out.println("Enter Mobile Color: ");
        mobile.setMobileColor(scanner.nextLine());
        System.out.println("Enter Mobile Warranty: ");
        mobile.setMobileWarranty(scanner.nextLine());
        if(Storage.addProduct(username, mobile)) {
            System.out.println("Mobile added successfully!");
        }
        else{
            System.out.println("Mobile not added!");
        }   
    }

    private static void viewOrders(String username) {

    }
}