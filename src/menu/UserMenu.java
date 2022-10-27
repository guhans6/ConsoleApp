package menu;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

import customer.Customer;
import seller.Seller;
import storage.FileStorage;

public class UserMenu {
    
    private static Scanner scanner = new Scanner(System.in);

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
                        userLogin();
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

    private static void userLogin() {
        try {
            short userType = userType();
            if(userType == 4) {
                return;
            }
            String userName;
            String password;

            System.out.print("Enter username: ");
            userName = scanner.next();
            System.out.print("Enter password: ");
            password = scanner.next();
            int checker = FileStorage.checkUser(userName, password, userType);
            switch(checker) {
                case 1:
                    CustomerMenu.customerMenu(userName);
                    break;
                case 2:
                    SellerMenu.sellerMenu(userName);
                    break;
                case 3:
                    adminMenu(userName);
                    break;
                case -1:
                    System.out.println("Login Failed!");
                    break;
            }
        } catch(IOException e) {
            System.out.println("Error occured");
        }
    }

    private static void displayAdminMenu() {
        System.out.println("\n1. View Products");
        System.out.println("2. Delete Product");
        System.out.println("3. Delete User");
        System.out.println("4. Logout");
        System.out.println("5. Exit");
    }

    public static void adminMenu(String username) {
        short choice;
        
        System.out.println("Welcome "+username);
        while(true){
            displayAdminMenu();
            try {
                choice = scanner.nextShort();
                switch(choice){
                    case 1:
                        FileStorage.deatileProductView(1);
                        FileStorage.deatileProductView(2);
                        break;
                    case 2:
                        System.out.println("Enter product ID to delete: ");
                        int id = scanner.nextInt();
                        scanner.nextLine();
                        FileStorage.deleteProduct(id);
                        break;
                    case 3:
                        System.out.println("Enter username to delete: ");
                        String usernameToDelete = scanner.nextLine();
                        System.out.println("Enter user type: ");
                        short userType = scanner.nextShort();
                        FileStorage.deleteUser(usernameToDelete, userType);
                        break;
                    case 4:
                        System.out.println("Logged out successfully!");
                        return;
                    case 5:
                        System.out.println("Bye!");
                        System.exit(0);
                    default:
                        System.out.println("Enter from given options!");
                }
            } catch(InputMismatchException e) {
                System.out.println("Enter from given options!");
                scanner.nextLine();
            } catch(IOException e) {
                System.out.println("Enter from given options!");
                scanner.nextLine();
            }
        }
    }

    private static short userType() { 

        System.out.println("\nSelect user type.");
        System.out.println("1. Customer");
        System.out.println("2. Seller");
        System.out.println("3. Admin");
        System.out.println("4. Exit this menu");
        try {
            return scanner.nextShort();
        }
        catch(InputMismatchException e) {
            System.out.println("Enter corrct option!");
            scanner.nextLine();
        }
        return 4;
    }

    private static boolean registerUser() {
            
        int userType = UserMenu.userType();
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

}