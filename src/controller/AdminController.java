package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import storage.fileStorage.AdminStorage;
import ui.DisplayMenu;
import ui.ProductView;

public class AdminController {

    private AdminStorage adminStorage = new AdminStorage();
    private DisplayMenu displayMenu = DisplayMenu.getInstance();
    private SellerController sellerController = new SellerController();
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
                        displayAllUsers(1);
                        break;
                    case 3:
                        displayAllUsers(2);
                        break;
                    case 4:
                        sellerController.deleteProduct("Admin"); //verification password
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
            } catch (IOException e) {
                // e.printStackTrace();
                System.out.println("Error occured!");
            }
        }
    }

    private void displayAllUsers(int userType) throws IOException {
        ArrayList<String[]> users = adminStorage.getAllUsers(userType);
        for(String[] user: users) {
            System.out.println("Username: " + user[1] + " Email: " + user[3]);
        }
    }

    private void displayProducts() throws InputMismatchException, IOException {
        ArrayList<String> productList = adminStorage.getProducList(displayMenu.getProductType());
        if(productList.size() == 0) {
            System.out.println("No products available!");
            return;
        }
        ProductView.getInstance().displayProductDetails(productList,2);

    }

    private void deleteUser() throws IOException {
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
