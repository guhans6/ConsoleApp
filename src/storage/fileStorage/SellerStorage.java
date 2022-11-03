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

public class SellerStorage extends FileStorage {


    private File products = new File("Files/products.txt");
    private File tempFile = new File("Files/temp.txt");
    private BufferedReader reader;

    public void addProduct(String username, Product product) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(products, true));
        double discount = product.getPrice() * (product.getProductDiscount() / 100);
        double discountedPrice = product.getPrice() - discount;

        product.setProductID(getAvailavleID());
        product.setProductDiscountedPrice(discountedPrice);
        writer.write(username + "|" + product);
        writer.newLine();
        writer.close();;
    }
    
    //read products file and get the last id and add 1 to it
    private int getAvailavleID() throws IOException {
        reader = new BufferedReader(new FileReader(products));
        int id = 0;

        String line = reader.readLine();
        while(line != null) {

            String[] split = line.split("\\|");
            id = Integer.parseInt(split[1]);
            line = reader.readLine();
        }
        return ++id;
    }

    public ArrayList<String> getProducts(String sellerName) throws IOException {
        ArrayList<String> productsList = new ArrayList<>();
        String[] split;
        reader = new BufferedReader(new FileReader(products));
        String line = reader.readLine();
        boolean flag = false;

        while(line != null) {

            split = line.split("\\|");
            if(split[0].equals(sellerName )) {
                productsList.add(line);
                flag = true;
            }
            line = reader.readLine();
        }
        if(!flag) {
            return null;
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
            if(Integer.parseInt(split[1]) == id) {
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

    public boolean checkUserProductAssociated(String username, int productId) throws FileNotFoundException, IOException {
        reader = new BufferedReader(new FileReader(products));
        String line = reader.readLine();
        String id = String.valueOf(productId);

        while(line != null) {

            String[] split = line.split("\\|");
            if(split[0].equals(username) && split[1].equals(id) || username.equals("Admin")) {
                return true;
            }
            line = reader.readLine();
        }
        return false;
    }

    //add stock to the product
    public void addStock(int id, int stock) throws IOException {
        reader = new BufferedReader(new FileReader(products));
        BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));
        String stockString;

        tempFile.createNewFile();
        String line = reader.readLine();
        while(line != null) {

            String[] split = line.split("\\|");
            if(Integer.parseInt(split[1]) == id) {
                int currentStock = Integer.parseInt(split[4]);
                stockString = getStockString(split);

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
