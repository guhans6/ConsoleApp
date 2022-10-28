package menu;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

import product.Laptop;
import product.Mobile;
import storage.FileStorage;

public class DisplayMenu {
    
    private static DisplayMenu displayMenu = null;
    Scanner scanner = new Scanner(System.in);
    FileStorage fileStorage = FileStorage.getInstance();

    private DisplayMenu() {
    }

    public static DisplayMenu getInstance() {
        if(displayMenu == null) {
            displayMenu = new DisplayMenu();
        }
        return displayMenu;
    }

    public void displayMainMenu() {
        System.out.println("\n1. Login");
        System.out.println("2. Register");
        System.out.println("3. Exit Application");
    }

    public void displayUsertypeMenu() {
        System.out.println("\nSelect user type.");
        System.out.println("1. Customer");
        System.out.println("2. Seller");
        System.out.println("3. Admin");
        System.out.println("4. Exit this menu");
    }


    public void displayCustomerMenu() {
        System.out.println("\n1. View Products");
        System.out.println("2. Add Cart");
        System.out.println("3. Buy Products in the cart");
        System.out.println("4. View Orders");
        System.out.println("5. Logout");
        System.out.println("6. Exit");
    }


    public void displayAdminMenu() {
        System.out.println("\n1. View Products");
        System.out.println("2. Delete Product");
        System.out.println("3. Delete User");
        System.out.println("4. Logout");
        System.out.println("5. Exit");
    }

    public void displaySellerMenu() {
        System.out.println("\n1. Add Product");
        System.out.println("2. View Products");
        System.out.println("3. Update Stock");
        System.out.println("4. Logout");
        System.out.println("5. Exit");
    }

    public String getProductType() {
        System.out.println("Enter type of product: \n1. Laptop \n2. Mobile");
        try {
            short type = scanner.nextShort();
            scanner.nextLine();
            return type == 1 ? "Laptop" : "Mobile";
        } catch(InputMismatchException e) {
            System.out.println("Enter from given options!");
            scanner.nextLine();
        }
        return null;
    }

    void addLaptop(String username) throws IOException,InputMismatchException {
        Laptop laptop = new Laptop();

        try {
            System.out.print("Enter Laptop Brand: ");
            laptop.setProductBrand(scanner.nextLine());
            System.out.print("Enter Laptop Name: ");
            laptop.setProductName(scanner.nextLine());
            System.out.print("Enter Laptop Price: ");
            laptop.setPrice(scanner.nextFloat());
            System.out.print("Enter Laptop Quantity: ");
            laptop.setQuantity(scanner.nextInt());
            System.out.print("Enter discount percentage");
            laptop.setProductDiscount(scanner.nextFloat());
            scanner.nextLine();
            System.out.print("Enter Laptop RAM: ");
            laptop.setLaptopRam(scanner.nextLine());
            System.out.print("Enter Laptop Processor: ");
            laptop.setLaptopProcessor(scanner.nextLine());
            System.out.print("Enter Laptop Hard Disk: ");
            laptop.setLaptopRom(scanner.nextLine());
            System.out.print("Enter Laptop Screen Size: ");
            laptop.setLaptopDisplay(scanner.nextLine());
            System.out.print("Enter Laptop Operating System: ");
            laptop.setLaptopOs(scanner.nextLine());
            System.out.print("Enter Laptop Battery: ");
            laptop.setLaptopBattery(scanner.nextLine());
            System.out.print("Enter Laptop Color: ");
            laptop.setLaptopColor(scanner.nextLine());
            System.out.print("Enter Laptop Warranty: ");
            laptop.setLaptopWarranty(scanner.nextLine());
            System.out.print("Enter Laptop Discription: ");
            laptop.setProductDescription(scanner.nextLine());

            FileStorage.getInstance().addProduct(username, laptop);
        } catch(InputMismatchException e) {
            System.out.println("Enter correct details!");
            scanner.nextLine();
        } catch(IOException e) {
            System.out.println("Error occured!");
        }

    }

    //add mobile
    void addMobile(String username) throws IOException, InputMismatchException {
        Mobile mobile = new Mobile();

            System.out.print("Enter Mobile Brand: ");
            mobile.setProductBrand(scanner.nextLine());
            System.out.print("Enter Mobile Name: ");
            mobile.setProductName(scanner.nextLine());
            System.out.print("Enter Mobile Price: ");
            mobile.setPrice(scanner.nextFloat());
            System.out.print("Enter Mobile Quantity: ");
            mobile.setQuantity(scanner.nextInt());
            System.out.print("Enter discount percentage");
            mobile.setProductDiscount(scanner.nextFloat());
            scanner.nextLine();
            System.out.print("Enter Mobile RAM: ");
            mobile.setMobileRam(scanner.nextLine());
            System.out.print("Enter Mobile Processor: ");
            mobile.setMobileProcessor(scanner.nextLine());
            System.out.print("Enter Mobile Hard Disk: ");
            mobile.setMobileRom(scanner.nextLine());
            System.out.print("Enter Mobile Screen Size: ");
            mobile.setMobileDisplay(scanner.nextLine());
            System.out.print("Enter Mobile Operating System: ");
            mobile.setMobileOs(scanner.nextLine());
            System.out.print("Enter Mobile Battery: ");
            mobile.setMobileBattery(scanner.nextLine());
            System.out.print("Enter Mobile Color: ");
            mobile.setMobileColor(scanner.nextLine());
            System.out.print("Enter Mobile Warranty: ");
            mobile.setMobileWarranty(scanner.nextLine());

            FileStorage.getInstance().addProduct(username, mobile);
    }
}
