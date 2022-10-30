package controller;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

import models.user.Seller;
import storage.SellerStorage;
import ui.DisplayMenu;

public class SellerController {

    DisplayMenu displayMenu = DisplayMenu.getInstance();
    Scanner scanner = new Scanner(System.in);
    ProductController productController = new ProductController();
    SellerStorage sellerStorage = new SellerStorage();

    void sellerMenu(String username){
        short choice;

        System.out.println("Welcome "+username);
        while(true){
            displayMenu.displaySellerMenu();
            try {
                choice = scanner.nextShort();
                switch(choice){
                    case 1:
                        addProduct(username);
                        break;
                    case 2:
                        sellerStorage.getProducts(username);
                        break;
                    case 3:
                        updateStock(username);
                        break;
                    case 4:
                        deleteProduct(username);
                        break;
                    case 5:
                        System.out.println("Logged out successfully!");
                        return;
                    case 6:
                        System.out.println("Bye!");
                        System.exit(0);
                    default:
                        System.out.println("Enter from given options!");
                }
            } catch(InputMismatchException e) {
                System.out.println("Enter from given options!");
                // e.printStackTrace();
                scanner.nextLine();
            } catch (IOException e) {
                // e.printStackTrace();
                System.out.println("Error occured!");
            }
        }
    }

    private void addProduct(String username) throws InputMismatchException, IOException {
        String type = displayMenu.getProductType();

        if(type.equals("Laptop")) {
            productController.addLaptop(username);
            return;
        }
        else if(type.equals("Mobile")) {
            productController.addMobile(username);
            return;
        }
        System.out.println("Enter from given options!");
    }

    private void updateStock(String username) throws InputMismatchException, IOException {
        short productId;
        short quantity;

        System.out.println("Enter product id to update stock: ");
        productId = scanner.nextShort();
        System.out.println("Enter quantity to add: ");
        quantity = scanner.nextShort();
        if(checkUserProductAssociated(username, productId)) {
            sellerStorage.addStock(productId, quantity);
        }
    }

    void deleteProduct(String username) throws IOException {
        System.out.println("Enter product ID to delete: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        if(checkUserProductAssociated(username, id)) {
            sellerStorage.deleteProduct(id);
        }
    }

    private boolean checkUserProductAssociated(String username, int id) throws IOException {

        if(sellerStorage.checkUserProduct(username, id)) {
            return true;
        } else {
            System.out.println("You are not associated with this product.");
            return false;
        }
    }

     //seller registration get input from user
     public void registerSeller(short userType) throws InputMismatchException {
        System.out.println("Welcome! Enter the following details ");
        Seller seller = new Seller();

        System.out.println("Enter your name:");
        seller.setName(scanner.nextLine());
        System.out.println("Enter your username:");
        seller.setUserName(scanner.nextLine());
        System.out.println("Enter your email:");
        seller.setEmail(scanner.nextLine());
        System.out.println("Enter your password:");
        seller.setPassword(scanner.nextLine());
        System.out.println("Enter your office address:");
        seller.setAddress(scanner.nextLine());
        if(seller.getName().equals("") || seller.getUserName().equals("") || seller.getEmail().equals("") 
                || seller.getPassword().equals("") || seller.getAddress().equals("")) {
            System.out.println("Please fill all the fields!");
            return;
        } 
        sellerStorage.addUser(seller, userType);
    }
    
    public void close() throws IOException {
        sellerStorage.close();
        productController.close();
        scanner.close();
    }
}
