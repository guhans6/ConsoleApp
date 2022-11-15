package storage.fileStorage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import models.product.Product;
import storage.SellerStorage;

public class SellerFileStorage implements SellerStorage {


    private File products = new File("Files/products.txt");
    private File tempFile = new File("Files/temp.txt");
    private BufferedReader reader;

    public boolean saveProduct(String username, Product product) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(products, true));
        double discount = product.getPrice() * (product.getProductDiscount() / 100);
        double discountedPrice = product.getPrice() - discount;

        product.setProductID(getAvailavleID());
        product.setProductDiscountedPrice(discountedPrice);
        writer.write(product + "|" + username);
        writer.newLine();
        writer.close();
        return true;
    }
    
    //read products file and get the last id and add 1 to it
    private int getAvailavleID() throws IOException {
        reader = new BufferedReader(new FileReader(products));
        int id = 0;

        String line = reader.readLine();
        while(line != null) {
            String[] split = line.split("\\|");
            if(split.length > 1) {
                id = Integer.parseInt(split[0]);
            }
            line = reader.readLine();
        }
        return ++id;
    }

    public ArrayList<String> getSellerProducts(String sellerName) throws IOException {
        ArrayList<String> productsList = new ArrayList<>();
        String[] split;
        reader = new BufferedReader(new FileReader(products));
        String line = reader.readLine();

        while(line != null) {

            split = line.split("\\|");
            if(split[20].equals(sellerName)) {
                productsList.add(line);
            }
            line = reader.readLine();
        }
        return productsList;
    }

    public void deleteProduct(int id) throws IOException {
        reader = new BufferedReader(new FileReader(products));
        BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

        tempFile.createNewFile();
        String line = reader.readLine();
        while(line != null) {

            String[] split = line.split("\\|");
            if(Integer.parseInt(split[0]) == id) {
                line = reader.readLine();
                continue;
            }
            writer.write(line);
            writer.newLine();
            line = reader.readLine();
        }
        products.delete();
        tempFile.renameTo(products);
        writer.close();;
        System.out.println("Product deleted successfully.");
    }

    public boolean checkUserProductAssociated(String sellerUsername, int productId) throws FileNotFoundException, IOException {
        reader = new BufferedReader(new FileReader(products));
        String line = reader.readLine();
        String id = String.valueOf(productId);

        while(line != null) {

            String[] split = line.split("\\|");
            if(split[19].equals(sellerUsername) && split[0].equals(id) || sellerUsername.equals("Admin")) {
                return true;
            }
            line = reader.readLine();
        }
        return false;
    }

    //add stock to the product
    public void saveStock(int id, int stock) throws IOException {
        reader = new BufferedReader(new FileReader(products));
        BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));
        String stockString;

        tempFile.createNewFile();
        String line = reader.readLine();
        while(line != null) {

            String[] split = line.split("\\|");
            if(Integer.parseInt(split[0]) == id) {
                int currentStock = Integer.parseInt(split[4]);
                stockString = UserFileStorage.getStockString(split);

                currentStock = stock;
                line = split[0] + "|" + split[1] + "|" + split[2] + "|" + split[3] + "|" + currentStock + "|" + stockString;
            }
            writer.write(line);
            writer.newLine();
            line = reader.readLine();
        }
        products.delete();
        tempFile.renameTo(products);
        writer.close();
    }
    
}
