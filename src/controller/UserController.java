package controller;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

import menu.DisplayMenu;
import storage.FileStorage;
import user.Customer;
import user.Seller;
import user.User;

public class UserController {

    private static UserController userController = null;
    Scanner scanner = new Scanner(System.in);
    FileStorage fileStorage = FileStorage.getInstance();
    DisplayMenu displayMenu = DisplayMenu.getInstance();
    CustomerController customerController = CustomerController.getInstance();
    SellerController sellerController = SellerController.getInstance();
    AdminController adminController = AdminController.getInstance();

    private UserController() {
    }

    public static UserController getInstance() {
        if(userController == null) {
            userController = new UserController();
        }
        return userController;
    }

    public void userMenu(){
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
                        break;
                    default:
                        System.out.println("Enter from given options!");
                }
            } catch(InputMismatchException e) {
                e.printStackTrace();
                // System.out.println("Enter Input Correctly!");
                scanner.nextLine();
            } catch(Exception e) {
                e.printStackTrace();
                // System.out.println("Error Occured! Try Again!");
                scanner.nextLine();
            }
        }
        scanner.close();
    }

    private void userLogin() throws InputMismatchException,IOException {
        short userType = userType();
        String userName;
        String password;
        if(userType == 4) {
            return;
        }

        System.out.print("Enter username: ");
        userName = scanner.next();
        System.out.print("Enter password: ");
        password = scanner.next();
        int checker = fileStorage.checkUser(userName, password, userType);
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
        
    }

    private void registerUser() {
        short userType = userType();
        User user = null;

        if(userType == 1) {
            user =  new Customer();
        }
        else if(userType == 2) {
            user = new Seller();
        }
        else if(userType == 3) {
            System.out.println("Admin can't be registered!");
            return;
        }
        user.register(userType);
    }


    private short userType(){ 

        displayMenu.displayUsertypeMenu();
        try {
            return scanner.nextShort();
        }
        catch(InputMismatchException e) {
            System.out.println("Enter corrct option!");
            scanner.nextLine();
        }
        return 4;
    }



}