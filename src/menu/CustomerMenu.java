package menu;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

import storage.FileStorage;

public class CustomerMenu {
    
    private static Scanner scanner = new Scanner(System.in);

    private static void displayCustomerMenu() {
        System.out.println("\n1. View Products");
        System.out.println("2. Add Cart");
        System.out.println("3. Buy Products in the cart");
        System.out.println("4. View Orders");
        System.out.println("5. Logout");
        System.out.println("6. Exit");
    }

    public static void customerMenu(String username) {
        short productId;
        short choice;

        System.out.println("Welcome " + username);
        while(true) {
            displayCustomerMenu();
            try {
                choice = scanner.nextShort();
                switch(choice) {
                    case 1:
                        System.out.println("What do you want to see?\n1. Laptop\n2. Mobile");            
                        short type = scanner.nextShort();
                        FileStorage.deatileProductView(type);
                        break;
                    case 2:
                        System.out.println("Enter product id to add in cart: ");
                        productId = scanner.nextShort();
                        FileStorage.addToCart(username, productId);
                        break;
                    case 3:
                        FileStorage.getCart(username);
                        break;
                    case 4:
                        FileStorage.viewOrders(username);
                        break;
                    case 5:
                        System.out.println("Logged out successfully.");
                        return;
                    case 6:
                        System.out.println("Bye!");
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Enter from given options!");
                }
            } catch(InputMismatchException e) {
                System.out.println("Enter from given options!");
                scanner.nextLine();
            } catch(IOException e) {
                System.out.println("Error occured! Try again.");
            }
        }
    }
}
