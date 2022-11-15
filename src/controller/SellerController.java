package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import models.product.Gadget;
import models.user.Seller;
import storage.SellerStorage;
import storage.UserStorage;
import storage.database.SellerDbStorage;
import storage.database.UserDbStorage;
import ui.DisplayMenu;
import ui.ProductView;

public class SellerController {

    private DisplayMenu displayMenu = DisplayMenu.getInstance();
    private Scanner scanner = new Scanner(System.in);
    private ProductController productController = new ProductController();
    private UserStorage fileStorage = new UserDbStorage();
    private SellerStorage sellerStorage = new SellerDbStorage();

    void sellerMenu(String username){
        int choice;

        System.out.println("Welcome "+username);
        while(true){
            displayMenu.displaySellerMenu();
            try {
                choice = scanner.nextInt();
                switch(choice){
                    case 1:
                        addProduct(username);
                        break;
                    case 2:
                        displaySellerProducts(username);
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
                System.out.println("Enter valid input!!");
                scanner.nextLine();
            } catch (Exception e) {
                // e.printStackTrace();
                System.out.println("Error occured!");
            }
        }
    }

     //seller registration get input from user
     public void registerSeller(short userType) throws InputMismatchException, Exception {
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
                || seller.getPassword().equals("") || seller.getAddress().equals("") 
                || seller.getUserName().equals("Admin")) {
            System.out.println("Please fill all the fields!");
            return;
        } 
        if(fileStorage.addUser(seller, userType)) {
            System.out.println("Registration successful!");
        } else {
            System.out.println("Username or email already exists!");
        }
    }

    private void displaySellerProducts(String username) throws Exception{
        ArrayList<String> sellerProducts =  sellerStorage.getSellerProducts(username);
        if(sellerProducts.size() == 0) {
            System.out.println("No products added yet!");
            return;
        }
        ProductView.getInstance().displayProductDetails(sellerProducts,1);
    }

    private void addProduct(String username) throws InputMismatchException, Exception {
        String type = displayMenu.getProductType();

        if(type.equals("Laptop") || type.equals("Mobile")) {
            Gadget gadget = new Gadget();
            gadget.setProductCategory(type);
            productController.addProduct(username, gadget);

            return;
        }
        System.out.println("Enter from given options!");
    }

    private void updateStock(String username) throws InputMismatchException, Exception {
        short productId;
        short quantity;

        System.out.println("Enter product id to update stock: ");
        productId = scanner.nextShort();
        System.out.println("Enter quantity to update: ");
        quantity = scanner.nextShort();
        if(sellerStorage.checkUserProductAssociated(username, productId)) {
            sellerStorage.saveStock(productId, quantity);
            System.out.println("Stock updated Successfully!");
        } else {
            System.out.println("Product not found. Please enter valid product id!");
        }
    }

    void deleteProduct(String username) throws IOException, Exception {
        System.out.println("Enter product ID to delete: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        if(sellerStorage.checkUserProductAssociated(username, id)) {
            sellerStorage.deleteProduct(id);
            System.out.println("Product deleted successfully!");
        } else {
            System.out.println("Product not found, Check product ID!");
        }
    }

}
