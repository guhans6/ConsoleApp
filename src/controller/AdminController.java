package controller;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

import storage.AdminStorage;
import ui.DisplayMenu;

public class AdminController {

    AdminStorage adminStorage = new AdminStorage();
    DisplayMenu displayMenu = DisplayMenu.getInstance();
    CustomerController customerController = new CustomerController();
    SellerController sellerController = new SellerController();
    Scanner scanner = new Scanner(System.in);

     void adminMenu(String username) {
        short choice;
        System.out.println("Welcome "+username);
        while(true){
            displayMenu.displayAdminMenu();
            choice = scanner.nextShort();
            try {
                switch(choice){
                    case 1:
                        adminStorage.deatiledProductView(displayMenu.getProductType());
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
            }catch(InputMismatchException e){
                System.out.println("Enter from given options!");
                e.printStackTrace();
                scanner.nextLine();
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Error occured!");
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

    public void close() throws IOException {
        adminStorage.close();
        scanner.close();
    }
}
