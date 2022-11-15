package controller;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import storage.AdminStorage;
import storage.UserStorage;
import storage.database.AdminDbStorage;
import storage.database.UserDbStorage;
import ui.AdminDisplay;
import ui.DisplayMenu;
import ui.ProductView;

public class AdminController {

    private DisplayMenu displayMenu = DisplayMenu.getInstance();
    private AdminStorage adminStorage = new AdminDbStorage();
    private UserStorage userStorage = new UserDbStorage();
    private Scanner scanner = new Scanner(System.in);

     void adminMenu(String username) {
        short choice;
        System.out.println("Welcome "+username);
        while(true){
            displayMenu.displayAdminMenu();
            choice = scanner.nextShort();
            try {
                switch(choice){
                    case 1:
                        displayProducts();
                        break;
                    case 2:
                        displayAllUsers(2);
                        break;
                    case 3:
                        displayAllUsers(1);
                        break;
                    case 4:
                        new SellerController().deleteProduct("Admin"); //verification password
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
            }catch(InputMismatchException e){
                System.out.println("Enter from given options!");
                // e.printStackTrace();
                scanner.nextLine();
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Error occured!");
            }
        }
    }

    private void displayAllUsers(int userType) throws Exception {
        ArrayList<String[]> users = adminStorage.getAllUsers(userType);
        AdminDisplay.getInstance().displaySellers(users);
    }

    private void displayProducts() throws InputMismatchException, Exception {
        ArrayList<String> productList = userStorage.getProducList(displayMenu.getProductType());
        if(productList.isEmpty()) {
            System.out.println("No products available!");
            return;
        }
        ProductView.getInstance().displayProductDetails(productList,1);

    }

    private void deleteUser() throws Exception {
        System.out.println("Enter username to delete: ");
        scanner.nextLine();
        String usernameToDelete = scanner.nextLine();
        displayMenu.displayUsertypeMenu();
        short userType = scanner.nextShort();
        scanner.nextLine();

        if(adminStorage.deleteUser(usernameToDelete, userType)) {
            System.out.println("User deleted successfully!");
        } else {
            System.out.println("User not found!");
        }
    }
}
