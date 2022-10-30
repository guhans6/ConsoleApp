package controller;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

import storage.FileStorage;
import ui.DisplayMenu;

public class UserController {

    Scanner scanner = new Scanner(System.in);
    FileStorage fileStorage = new FileStorage();
    DisplayMenu displayMenu = DisplayMenu.getInstance();
    CustomerController customerController = new CustomerController();
    SellerController sellerController = new SellerController();
    AdminController adminController = new AdminController();

    public void userMenu() {
        short input=0;
        boolean b=true;
        while(b)
        {
            displayMenu.displayMainMenu();
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
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Enter from given options!");
            } 
            } catch(InputMismatchException e) {
                System.out.println("Enter correct input!");
                // e.printStackTrace();
                scanner.nextLine();
            }
        }
    }

    private void userLogin() {
        short userType = userType();
        String userName;
        String password;

        try {
            System.out.print("Enter username: ");
            userName = scanner.next();
            System.out.print("Enter password: ");
            password = scanner.next();
            int checker;
                checker = fileStorage.checkUser(userName, password, userType);
                switch(checker) {
                    case 1:
                        customerController.customerMenu(userName);
                        break;
                    case 2:
                        sellerController.sellerMenu(userName);
                        break;
                    case 3:
                        adminController.adminMenu(userName);
                        break;
                    case -1:
                        System.out.println("Login Failed!");
                        break;
                }
        } catch (IOException e) {
            System.out.println("Error occured! Try again!");
            // e.printStackTrace();
        }
        
    }

    private void registerUser() {
        short userType = userType();

        if(userType == 1) {
            customerController.registerCustomer(userType);
        } else if(userType == 2) {
            sellerController.registerSeller(userType);
        } else if(userType == 3) {
            System.out.println("Admin can't be registered!");
        }
        return;
    }


    private short userType() throws InputMismatchException {
        short userType;

        displayMenu.displayUsertypeMenu();
        userType = scanner.nextShort();
        if(userType == 4) {
            userMenu(); 
        } else if(userType < 1 || userType > 3) {
            System.out.println("Enter from given options!");
            userMenu();
        }
        return userType;
    }

    // private void close() throws IOException {
    //     customerController.close();
    //     sellerController.close();
    //     adminController.close(); 
    // }
}