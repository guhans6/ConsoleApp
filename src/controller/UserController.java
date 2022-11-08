package controller;

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

import storage.database.UserDbStorage;
import storage.fileStorage.FileStorage;
import ui.DisplayMenu;

public class UserController {

    private Scanner scanner = new Scanner(System.in);
    private FileStorage fileStorage = new FileStorage();
    private DisplayMenu displayMenu = DisplayMenu.getInstance();
    private CustomerController customerController = new CustomerController();
    private SellerController sellerController = new SellerController();
    private AdminController adminController = new AdminController();
    private UserDbStorage userStorage = new UserDbStorage();

    public void mainMenu() {                    //Main menu of the application
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
            } catch(Exception e) {
                System.out.println("Error Occured!");
                e.printStackTrace();
            }
        }
    }

    private void userLogin() {                  //Login portal for all users
        short userType = userType();
        String userName;
        String password;

        try {
            System.out.print("Enter username: ");
            userName = scanner.next();
            System.out.print("Enter password: ");
            password = scanner.next();
            int checker;
            
            checker = userStorage.authenticateUser(userName, password, userType);
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
                    System.out.println("Invalid username or password.");
                    break;
            }
        } catch (SQLException e) {
            System.out.println("Error occured! Try again!");
            // e.printStackTrace();
        }
        
    }

    private void registerUser() throws InputMismatchException, Exception {
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

    //To get the type of user who is trying to login or register
    private short userType() throws InputMismatchException {
        short userType;

        displayMenu.displayUsertypeMenu();
        userType = scanner.nextShort();
        if(userType == 4) {
            mainMenu();
        } else if(userType < 1 || userType > 3) {
            System.out.println("Enter from given options!");
            mainMenu();
        }
        return userType;
    }
}