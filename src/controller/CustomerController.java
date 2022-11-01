package controller;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

import models.user.Customer;
import storage.CustomerStorage;
import ui.DisplayMenu;

public class CustomerController {

    DisplayMenu displayMenu = DisplayMenu.getInstance();
    CustomerStorage customerStorage = new CustomerStorage();
    Scanner scanner = new Scanner(System.in);

    void customerMenu(String username) {                //Customer 
        short productId;
        short choice;

        System.out.println("Welcome " + username);
        while(true) {
            displayMenu.displayCustomerMenu();
            try {
                choice = scanner.nextShort();
                scanner.nextLine();
                switch(choice) {
                    case 1:         
                        customerStorage.deatiledProductView(displayMenu.getProductType());
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
            } catch(InputMismatchException e) {
                System.out.println("Enter Correct Input!");
                scanner.nextLine();
            } catch (IOException e) {
                System.out.println("Error occured!");
            } 
        }
    }

    //This method is used to register a new customer
    public void registerCustomer(short userType) throws InputMismatchException {
        System.out.println("Welcome! Enter the following details ");
        Customer customer = new Customer();

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
        
        if(customer.getName().isEmpty() || customer.getUserName().isEmpty() || customer.getPassword().isEmpty() 
                || customer.getEmail().isEmpty() || customer.getAddress().isEmpty()) {        //check all fields are filled
            System.out.println("All fields are mandatory!");
            return;
        }
        customerStorage.addUser(customer, userType);
    }

    //close the scanner
    public void close() throws IOException {
        customerStorage.close();
        scanner.close();
    }
    
}
