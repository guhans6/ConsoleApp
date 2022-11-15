package controller;

import java.util.InputMismatchException;
import java.util.Scanner;

import models.product.Gadget;
import models.product.Product;
import storage.database.SellerDbStorage;
import storage.fileStorage.SellerFileStorage;

public class ProductController {

    private SellerFileStorage sellerStorage = new SellerFileStorage();
    private SellerDbStorage sellerDbStorage = new SellerDbStorage();
    private Scanner scanner = new Scanner(System.in);

    Product addProduct(String username, Product product) throws Exception {
        try {
            System.out.println("Enter Product Brand");;
            product.setProductBrand(scanner.nextLine());
            System.out.println("Enter Product Name");
            product.setProductName(scanner.nextLine());
            System.out.println("Enter Product Price");
            product.setPrice(scanner.nextDouble());
            System.out.println("Enter Product Quantity");
            product.setQuantity(scanner.nextInt());
            scanner.nextLine();
            System.out.println("Enter Product Description");
            product.setProductDescription(scanner.nextLine());
            System.out.println("Enter Product Discount Percentage");
            product.setProductDiscount(scanner.nextDouble());
            scanner.nextLine();
            product.setProductDiscountedPrice(product.getPrice() - (product.getPrice() * product.getProductDiscount() / 100));
            if(product instanceof Gadget) {
                addGadget(username, (Gadget)product);
            }

            addFeatures(product);

            sellerStorage.saveProduct(username, product);
            sellerDbStorage.saveProduct(username, product);
            System.out.println("Product added successfully.");

            return product;
        } catch(InputMismatchException e) {
            scanner.nextLine();
            throw e;
        }
    }

    void addGadget(String username, Gadget gadget) throws Exception {

        System.out.println("Enter RAM: ");
        gadget.setRam(scanner.nextLine());
        System.out.println("Enter Processor: ");
        gadget.setProcessor(scanner.nextLine());
        System.out.println("Enter Hard Disk Capacity: ");
        gadget.setRom(scanner.nextLine());
        System.out.println("Enter Screen Size: ");
        gadget.setDisplay(scanner.nextLine());
        System.out.println("Enter Operating System: ");
        gadget.setOs(scanner.nextLine());
        System.out.println("Enter Battery: ");
        gadget.setBattery(scanner.nextLine());
        System.out.println("Enter Color: ");
        gadget.setColor(scanner.nextLine());
        System.out.println("Enter Warranty: ");
        gadget.setWarranty(scanner.nextLine());
    }

    private void addFeatures(Product product) {
        StringBuilder features = new StringBuilder();
        System.out.println();
        while(true) {
            System.out.println("Do you want to add more features? (y/n)");
            String choice = scanner.nextLine();
            if(choice.equals("y")) {

            } else if(choice.equals("n")) {
                break;
            } else {
                System.out.println("Enter from given options!");
                continue;
            }
            System.out.println("Enter a feature: ");
            String feature = scanner.nextLine();
            features.append(feature).append(",");
        }
        product.setFeatures(features.toString());
    }
}