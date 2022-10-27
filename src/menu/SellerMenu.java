package menu;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

import product.Laptop;
import product.Mobile;
import storage.FileStorage;
import storage.SellerStorage;

public class SellerMenu {
    
    private static Scanner scanner = new Scanner(System.in);

    private static void displaySellerMenu() {
        System.out.println("\n1. Add Product");
        System.out.println("2. View Products");
        System.out.println("3. View Orders");
        System.out.println("4. Logout");
        System.out.println("5. Exit");
    }
    
    public static void sellerMenu(String username){
        short choice;

        System.out.println("Welcome "+username);
        while(true){
            displaySellerMenu();
            try {
                choice = scanner.nextShort();
                switch(choice){
                    case 1:
                        addProduct(username);
                        break;
                    case 2:
                        FileStorage.getProducts(username);
                        break;
                    case 3:
                        FileStorage.viewOrders(username);
                        break;
                    case 4:
                        System.out.println("Enter product ID to delete: ");
                        int id = scanner.nextInt();
                        FileStorage.deleteProduct(id);
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
                scanner.nextLine();
            } catch(IOException e) {
                System.out.println("Error occured!");
            }
        }
    }

    public static void addProduct(String username) {
        System.out.println("Enter type of product: \n1. Laptop \n2. Mobile");
        try {
            short type = scanner.nextShort();
            scanner.nextLine();

            if(type == 1) {
                addLaptop(username);
                return;
            }
            else if(type == 2) {
                addMobile(username);
                return;
            }
            System.out.println("Enter from given options!");

        } catch(InputMismatchException e) {
            System.out.println("Enter from given options!");
            scanner.nextLine();
        }
    }

    private static void addLaptop(String username) {
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
            System.out.print("Enter Laptop Graphics Card(If not enter None): ");
            laptop.setLaptopGraphicsCard(scanner.nextLine());
            System.out.print("Enter Laptop Battery: ");
            laptop.setLaptopBattery(scanner.nextLine());
            System.out.print("Enter Laptop Color: ");
            laptop.setLaptopColor(scanner.nextLine());
            System.out.print("Enter Laptop Warranty: ");
            laptop.setLaptopWarranty(scanner.nextLine());
            System.out.print("Enter Laptop Discription: ");
            laptop.setProductDescription(scanner.nextLine());

            SellerStorage.getInstance().addProduct(username, laptop);
        } catch(InputMismatchException e) {
            System.out.println("Enter correct details!");
            scanner.nextLine();
        } catch(IOException e) {
            System.out.println("Error occured!");
        }

    }

    //add mobile
    private static void addMobile(String username) {
        Mobile mobile = new Mobile();

        try {
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

            SellerStorage.getInstance().addProduct(username, mobile);
        } catch(InputMismatchException e) {
            System.out.println("Enter correct details!");
            scanner.nextLine();
        } catch(IOException e) {
            System.out.println("Error occured!");
        }
    }
}
