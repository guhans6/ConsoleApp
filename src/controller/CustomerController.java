package controller;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import models.user.Customer;
import storage.CustomerStorage;
import storage.UserStorage;
import storage.database.CustomerDbStorage;
import storage.database.UserDbStorage;
import storage.fileStorage.CustomerFileStorage;
import storage.fileStorage.UserFileStorage;
import ui.*;

public class CustomerController {

    private CustomerStorage customerStorage = new CustomerDbStorage();
    // private UserStorage userStorage = new UserFileStorage();
    private ProductView productView = ProductView.getInstance();
    private DisplayMenu displayMenu = DisplayMenu.getInstance();
    private Scanner scanner = new Scanner(System.in);
    private UserStorage userStorage = new UserDbStorage();


    void customerMenu(String username) {                //Customer menu 
        short choice;
        System.out.println("Login successful!");
        System.out.println("Welcome " + username);

        while(true) {
            displayMenu.displayCustomerMenu();
            try {
                choice = scanner.nextShort();
                scanner.nextLine();
                switch(choice) {
                    case 1:         
                        displayProducts();
                        break;
                    case 2:
                        addProductToCart(username);
                        break;
                    case 3:
                        removeCartProducts(username);
                        break;
                    case 4:
                        buyProductInCart(username);
                        break;
                    case 5:
                        orderHistory(username);
                        break;
                    case 6: 
                        System.out.println("Logged out successfully.");
                        return;
                    case 7:
                        System.out.println("Bye!");
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Enter from given options!");
                }
            } catch(InputMismatchException e) {
                System.out.println("Enter Correct Input!");
                scanner.nextLine();
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Error occured!");
            }
        }
    }

    //This method is used to register a new customer
    void registerCustomer(short userType) throws InputMismatchException,Exception {
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
                || customer.getEmail().isEmpty() || customer.getAddress().isEmpty() 
                || customer.getUserName().equals("Admin")) {        //check all fields are filled
            System.out.println("All fields are mandatory!");
            return;
        }
        if(userStorage.addUser(customer, userType)) {
            System.out.println("Registered successfully!");
        } else {
            System.out.println("Username or email already exists!");
        }
    }

    private void displayProducts() throws InputMismatchException, Exception {     
        ArrayList<String> products =  userStorage.getProducList(displayMenu.getProductType());

        if(products.isEmpty()) {
            System.out.println("No products available!");
            return;
        }
        productView.displayProductDetails(products,2);
    }

    private void addProductToCart(String username) throws InputMismatchException, Exception {
        int productId;
        int quantity;
        System.out.println("Enter product id to add in cart: ");
        productId = scanner.nextShort();
        System.out.println("Enter quantity: ");
        quantity = scanner.nextInt();
        switch(customerStorage.checkStock(productId, quantity)) {
            case 1:
                customerStorage.addToCart(username, productId, quantity);
                System.out.println("Product added to cart successfully.");
                break;
            case -1:
                System.out.println("Check available quantity and try again!");
                break;
            default:
                System.out.println("Product not found. Enter Correct Id!");
        }
    }

    private void removeCartProducts(String username) throws InputMismatchException, Exception {
        ArrayList<String[]> cartProducts =  customerStorage.getCart(username);
        int productId;
        if(cartProducts.isEmpty()) {
            System.out.println("Cart is Empty");
            return;
        }
        System.out.println("Enter product id to remove from cart: ");
        productId = scanner.nextShort();
        scanner.nextLine();
        if(customerStorage.removeProductFromCart(username, productId)) {
            System.out.println("Product removed from cart successfully.");
        } else {
            System.out.println("Product not found in cart.");
        }
    }

    private void buyProductInCart(String username) throws Exception {               //buy product from cart
        ArrayList<String[]> cartProducts =  customerStorage.getCart(username);
        String[] product;
        char choice;
        double totalAmount = 0;
        
        if(cartProducts.isEmpty()) {                      //check cart is empty
            System.out.println("Cart is empty!");
            return;
        }
        for(String[] cartDetails : cartProducts) {
            if(customerStorage.checkStock(Integer.parseInt(cartDetails[1]), Integer.parseInt(cartDetails[2])) == 1) {
                product = userStorage.findProdcutByID(cartDetails[1]);
                ProductView.getInstance().cartView(product, cartDetails[2]);
                totalAmount += (Double.parseDouble(cartDetails[3]) * Integer.parseInt(cartDetails[2]));
            } else {
                System.out.println("Ordered Quantity is not Available for this Product Id '" + cartDetails[1] + "'");
                return;
            }

        }
        if(totalAmount > 0.0) {
            System.out.println("Total amount: " + totalAmount);
            System.out.println("Do you want to buy the products? (y/n)");
            choice = scanner.next().charAt(0);
            if(choice == 'y' || choice == 'Y') {
                customerStorage.buyProducts(username);
                int deliveryDay = (int)(Math.random() * 3) + 1;
                System.out.println("Your order will be delivered within " + deliveryDay + " days.");
                System.out.println("Thank you for shopping with us.");
            } else if(choice == 'n' || choice == 'N') {
                System.out.println("Come back later.");
            } else {
                System.out.println("Enter correct choice! (y/n)");
            }
        }
    }

    private void orderHistory(String username) throws Exception {
        ArrayList<String[]> customerOrders =  customerStorage.getOrders(username);
        String[] productDetails;

        if(customerOrders.isEmpty()) {
            System.out.println("No orders placed yet!");
            return;
        }
        for(String[] order : customerOrders) {
            productDetails = userStorage.findProdcutByID(order[1]);
            if(productDetails != null) {
                ProductView.getInstance().orderView(productDetails, order[2], order[4]);
            }
        }


    }
    
}
