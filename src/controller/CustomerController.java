package controller;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

import menu.DisplayMenu;
import storage.CustomerStorage;
import storage.FileStorage;

public class CustomerController {

    private static CustomerController customerController = null;
    DisplayMenu displayMenu = DisplayMenu.getInstance();
    FileStorage fileStorage = FileStorage.getInstance();
    CustomerStorage customerStorage = CustomerStorage.getInstance();
    Scanner scanner = new Scanner(System.in);

    private CustomerController() {
    }

    public static CustomerController getInstance() {
        if(customerController == null) {
            customerController = new CustomerController();
        }
        return customerController;
    }

    public void customerMenu(String username) throws IOException, InputMismatchException {
        short productId;
        short choice;

        System.out.println("Welcome " + username);
        while(true) {
            displayMenu.displayCustomerMenu();
            choice = scanner.nextShort();
            switch(choice) {
                case 1:         
                    String type = displayMenu.getProductType();
                    if(!type.equals("Laptop") && !type.equals("Mobile")) {
                        System.out.println("Enter from given options!");
                        break;
                    } else {
                        fileStorage.deatileProductView(type);
                    }
                    break;
                case 2:
                    System.out.println("Enter product id to add in cart: ");
                    productId = scanner.nextShort();
                    customerStorage.addToCart(username, productId);
                    break;
                case 3:
                    customerStorage.getCart(username);
                    break;
                case 4:
                    customerStorage.viewOrders(username);
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
        }
    }

    
}
