package controller;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

import menu.DisplayMenu;
import product.Laptop;
import product.Mobile;
import storage.FileStorage;
import storage.SellerStorage;

public class SellerController {

    private static SellerController sellerController = null;
    DisplayMenu displayMenu = DisplayMenu.getInstance();
    Scanner scanner = new Scanner(System.in);
    FileStorage fileStorage = FileStorage.getInstance();
    SellerStorage sellerStorage = SellerStorage.getInstance();
    

    private SellerController() {
    }

    public static SellerController getInstance() {
        if(sellerController == null) {
            sellerController = new SellerController();
        }
        return sellerController;
    }

    void sellerMenu(String username) throws InputMismatchException, IOException {
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

    void addProduct(String username) throws InputMismatchException, IOException {
        String type = displayMenu.getProductType();

        if(type.equals("Laptop")) {
            addLaptop(username);
            return;
        }
        else if(type.equals("Mobile")) {
            addMobile(username);
            return;
        }
        System.out.println("Enter from given options!");
    }

    void updateStock(String username) throws InputMismatchException, IOException {
        short productId;
        short quantity;

        System.out.println("Enter product id to update stock: ");
        productId = scanner.nextShort();
        System.out.println("Enter quantity to add: ");
        quantity = scanner.nextShort();
        if(fileStorage.checkUserProduct(username, productId)) {
            sellerStorage.addStock(productId, quantity);
        }
    }

    void deleteProduct(String username) throws IOException {
        System.out.println("Enter product ID to delete: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        if(fileStorage.checkUserProduct(username, id)) {
            sellerStorage.deleteProduct(id);
        }
        else {
            System.out.println("You don't have this product!");
        }
    }

    //add laptop
    void addLaptop(String username) throws IOException,InputMismatchException {
        Laptop laptop = new Laptop();

        System.out.println("Enter Laptop Brand: ");
        scanner.nextLine();
        laptop.setLaptopBrand(scanner.nextLine());
        System.out.println("Enter Laptop Name: ");
        laptop.setProductName(scanner.nextLine());
        System.out.println("Enter Laptop Price: ");
        laptop.setPrice(scanner.nextFloat());
        scanner.nextLine();
        System.out.println("Enter Laptop Quantity: ");
        laptop.setQuantity(scanner.nextInt());
        scanner.nextLine();
        System.out.println("Enter discount percentage");
        laptop.setProductDiscount(scanner.nextFloat());
        scanner.nextLine();
        System.out.println("Enter Laptop RAM: ");
        laptop.setLaptopRam(scanner.nextLine());
        System.out.println("Enter Laptop Processor: ");
        laptop.setLaptopProcessor(scanner.nextLine());
        System.out.println("Enter Laptop Hard Disk: ");
        laptop.setLaptopRom(scanner.nextLine());
        System.out.println("Enter Laptop Screen Size: ");
        laptop.setLaptopDisplay(scanner.nextLine());
        System.out.println("Enter Laptop Operating System: ");
        laptop.setLaptopOs(scanner.nextLine());
        System.out.println("Enter Laptop Battery: ");
        laptop.setLaptopBattery(scanner.nextLine());
        System.out.println("Enter Laptop Color: ");
        laptop.setLaptopColor(scanner.nextLine());
        System.out.println("Enter Laptop Warranty: ");
        laptop.setLaptopWarranty(scanner.nextLine());
        System.out.println("Enter Laptop Discription: ");
        laptop.setProductDescription(scanner.nextLine());

        sellerStorage.addProduct(username, laptop);
    }

    //add mobile
    void addMobile(String username) throws IOException, InputMismatchException {
        Mobile mobile = new Mobile();

        System.out.println("Enter Mobile Brand: ");
        mobile.setProductBrand(scanner.nextLine());
        System.out.println("Enter Mobile Name: ");
        mobile.setProductName(scanner.nextLine());
        System.out.println("Enter Mobile Price: ");
        mobile.setPrice(scanner.nextFloat());
        System.out.println("Enter Mobile Quantity: ");
        mobile.setQuantity(scanner.nextInt());
        System.out.println("Enter discount percentage");
        mobile.setProductDiscount(scanner.nextFloat());
        scanner.nextLine();
        System.out.println("Enter Mobile RAM: ");
        mobile.setMobileRam(scanner.nextLine());
        System.out.println("Enter Mobile Processor: ");
        mobile.setMobileProcessor(scanner.nextLine());
        System.out.println("Enter Mobile Hard Disk: ");
        mobile.setMobileRom(scanner.nextLine());
        System.out.println("Enter Mobile Screen Size: ");
        mobile.setMobileDisplay(scanner.nextLine());
        System.out.println("Enter Mobile Operating System: ");
        mobile.setMobileOs(scanner.nextLine());
        System.out.println("Enter Mobile Battery: ");
        mobile.setMobileBattery(scanner.nextLine());
        System.out.println("Enter Mobile Color: ");
        mobile.setMobileColor(scanner.nextLine());
        System.out.println("Enter Mobile Warranty: ");
        mobile.setMobileWarranty(scanner.nextLine());
        System.out.println("Enter Mobile Description");
        mobile.setProductDescription(scanner.nextLine());

        sellerStorage.addProduct(username, mobile);
    }
    
}
