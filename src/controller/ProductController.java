package controller;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

import models.product.Laptop;
import models.product.Mobile;
import storage.fileStorage.SellerStorage;

public class ProductController {

    private SellerStorage sellerStorage = new SellerStorage();
    private Scanner scanner = new Scanner(System.in);

     //add laptop
    void addLaptop(String username) throws IOException,InputMismatchException {
        Laptop laptop = new Laptop();

        System.out.println("Enter Laptop Brand: ");
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
        System.out.println("Product added successfully.");
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
        System.out.println("Product added successfully.");
    }

    //close scanner
    void close() {
        scanner.close();
    }
    
}