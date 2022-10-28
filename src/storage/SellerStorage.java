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
    private FileStorage fileStorage = FileStorage.getInstance();
    private File products = new File("Files/products.txt");
    private File tempFile = new File("Files/temp.txt");

    private SellerStorage() {
    }

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

    public void deleteProduct(int id) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(products));
        BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

        tempFile.createNewFile();
        String line = reader.readLine();
        while(line != null) {

            String[] split = line.split("\\|");
            if(Integer.parseInt(split[1]) == id) {
                line = reader.readLine();
                continue;
            }
            writer.write(line);
            writer.newLine();
            line = reader.readLine();
        }
        reader.close();
        writer.close();
        products.delete();
        tempFile.renameTo(products);
        System.out.println("Product deleted successfully.");
    }

    //add stock to the product
    public void addStock(int id, int stock) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(products));
        BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));
        String stockString;

        tempFile.createNewFile();
        String line = reader.readLine();
        while(line != null) {

            String[] split = line.split("\\|");
            if(Integer.parseInt(split[1]) == id) {
                int currentStock = Integer.parseInt(split[4]);
                stockString = fileStorage.getStockString(split);

                currentStock = stock;
                line = split[0] + "|" + split[1] + "|" + split[2] + "|" + split[3] + "|" + currentStock + "|" + stockString;
            }
            writer.write(line);
            writer.newLine();
            line = reader.readLine();
        }
        reader.close();
        writer.close();
        products.delete();
        tempFile.renameTo(products);
        System.out.println("Stock updated Successfully!");
    }
    
}
