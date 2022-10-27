package storage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import product.Product;

public class SellerStorage {

    private static SellerStorage sellerStorage = null;
    private File products = new File("Files/products.txt");

    //singleton  constructor
    private SellerStorage() {
    }

    //singleton method
    public static SellerStorage getInstance() {
        if(sellerStorage == null) {
            sellerStorage = new SellerStorage();
        }
        return sellerStorage;
    }

    public void addProduct(String username, Product product) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(products, true));
        double discount = product.getPrice() * (product.getProductDiscount() / 100);
        double discountedPrice = product.getPrice() - discount;

        product.setProductID(getAvailavleID());
        product.setProductDiscountedPrice(discountedPrice);
        writer.write(username + "|" + product.toString());
        writer.newLine();
        writer.close();
        System.out.println("Product added successfully.");
    }
    
    //read products file and get the last id and add 1 to it
    private int getAvailavleID() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(products));
        int id = 0;

        String line = reader.readLine();
        while(line != null) {

            String[] split = line.split("\\|");
            id = Integer.parseInt(split[1]);
            line = reader.readLine();
        }
        reader.close();
        return ++id;
    }

    public void getProducts(String sellerName) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("products.txt"));
        String line = reader.readLine();
        while(line != null) {

            String[] split = line.split("\\|");
            if(split[0].equals(sellerName )) {
                FileStorage.displayProduct(split);
            }
            line = reader.readLine();
        }
        reader.close();
    }
}
