package menu;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

import storage.FileStorage;
import user.Customer;
import user.Seller;
import user.User;

public class UserMenu {
    
    private static UserMenu userMenu = null;
    private Scanner scanner = new Scanner(System.in);
    private FileStorage fileStorage = FileStorage.getInstance();
    private DisplayMenu displayMenu = DisplayMenu.getInstance();

    private UserMenu() {
    }

    public static UserMenu getInstance() {
        if(userMenu == null) {
            userMenu = new UserMenu();
        }
        return userMenu;
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
                System.out.println(e);
                // System.out.println("Enter Input Correctly!");
                scanner.nextLine();
            } catch(Exception e) {
                System.out.println(e);
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
                customerMenu(userName);
                break;
            case 2:
                sellerMenu(userName);
                break;
            case 3:
                adminMenu(userName);
                break;
            case -1:
                System.out.println("Login Failed!");
                break;
        }
        
    }

    public void customerMenu(String username) throws IOException, InputMismatchException {
        short productId;
        short choice;

        System.out.println("Welcome " + username);
        while(true) {
            displayMenu.displayCustomerMenu();
            choice = scanner.nextShort();
            switch(choice) {
                case 1:         
                    String type = displayMenu.getProductType();
                    if(!type.equals("Laptop") && !type.equals("Mobile")) {
                        System.out.println("Enter from given options!");
                        break;
                    } else {
                        fileStorage.deatileProductView(type);
                    }
                    break;
                case 2:
                    System.out.println("Enter product id to add in cart: ");
                    productId = scanner.nextShort();
                    fileStorage.addToCart(username, productId);
                    break;
                case 3:
                    fileStorage.getCart(username);
                    break;
                case 4:
                    fileStorage.viewOrders(username);
                    break;
                case 5:
                    System.out.println("Logged out successfully.");
                    return;
                case 6:
                    System.out.println("Bye!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Enter from given options!");
            }
        }
    }

    public void sellerMenu(String username) throws InputMismatchException, IOException {
        short choice;
        FileStorage fileStorage = FileStorage.getInstance();

        System.out.println("Welcome "+username);
        while(true){
            displayMenu.displaySellerMenu();
            choice = scanner.nextShort();
            switch(choice){
                case 1:
                    addProduct(username);
                    break;
                case 2:
                    fileStorage.getProducts(username);
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
        }
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
                    deleteProduct("Admin");
                    break;
                case 3:
                    deleteUser();
                    break;
                case 4:
                    System.out.println("Logged out successfully!");
                    return;
                case 5:
                    System.out.println("Bye!");
                    System.exit(0);
                default:
                    System.out.println("Enter from given options!");
            }
        }
    }

    public void addProduct(String username) throws InputMismatchException, IOException {
        String type = displayMenu.getProductType();

        if(type.equals("Laptop")) {
            displayMenu.addLaptop(username);
            return;
        }
        else if(type.equals("Mobile")) {
            displayMenu.addMobile(username);
            return;
        }
        System.out.println("Enter from given options!");
    }

    public void updateStock(String username) throws InputMismatchException, IOException {
        short productId;
        short quantity;

        System.out.println("Enter product id to update stock: ");
        productId = scanner.nextShort();
        System.out.println("Enter quantity to add: ");
        quantity = scanner.nextShort();
        if(fileStorage.checkUserProduct(username, productId)) {
            fileStorage.addStock(productId, quantity);
        }
    }

    private void deleteProduct(String username) throws IOException {
        System.out.println("Enter product ID to delete: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        if(fileStorage.checkUserProduct(username, id)) {
            fileStorage.deleteProduct(id);
        }
        else {
            System.out.println("You don't have this product!");
        }
    }

    private void deleteUser() throws IOException {
        System.out.println("Enter username to delete: ");
        String usernameToDelete = scanner.nextLine();
        System.out.println("Enter user type: ");
        short userType = scanner.nextShort();
        scanner.nextLine();

        fileStorage.deleteUser(usernameToDelete, userType);
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

}