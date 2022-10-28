package controller;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

import menu.DisplayMenu;
import storage.AdminStorage;
import storage.FileStorage;

public class AdminController {

    private static AdminController adminController = null;
    AdminStorage adminStorage = AdminStorage.getInstance();
    FileStorage fileStorage = FileStorage.getInstance();
    DisplayMenu displayMenu = DisplayMenu.getInstance();
    CustomerController customerController = CustomerController.getInstance();
    SellerController sellerController = SellerController.getInstance();
    Scanner scanner = new Scanner(System.in);

    private AdminController() {
    }

    public static AdminController getInstance() {
        if(adminController == null) {
            adminController = new AdminController();
        }
        return adminController;
    }

    public void adminMenu(String username) throws IOException, InputMismatchException {
        short choice;
        
        System.out.println("Welcome "+username);
        while(true){
            displayMenu.displayAdminMenu();
            choice = scanner.nextShort();
            switch(choice){
                case 1:
                    FileStorage.getInstance().deatileProductView("Laptop");
                    FileStorage.getInstance().deatileProductView("Mobile");
                    break;
                case 2:
                    adminStorage.displayAllSellers();
                    break;
                case 3:
                    adminStorage.displayAllCustomers();
                    break;
                case 4:
                    sellerController.deleteProduct("Admin");
                    break;
                case 5:
                    deleteUser();
                    break;
                case 6:
                    return;
                case 7:
                    System.out.println("Bye!");
                    System.exit(0);
                default:
                    System.out.println("Enter from given options!");
            }
        }
    }

    private void deleteUser() throws IOException {
        System.out.println("Enter username to delete: ");
        scanner.nextLine();
        String usernameToDelete = scanner.nextLine();
        displayMenu.displayUsertypeMenu();
        short userType = scanner.nextShort();
        scanner.nextLine();

        adminStorage.deleteUser(usernameToDelete, userType);
    }
}
