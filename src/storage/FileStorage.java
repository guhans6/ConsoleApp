package storage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import user.User;
import product.Product;

public class FileStorage {

    private static FileStorage fileStorage = null;
    private Scanner scanner = new Scanner(System.in);
    private File customers = new File("Files/customers.txt");
    private File sellers = new File("Files/sellers.txt");
    private File admins = new File("Files/admins.txt");
    private File products = new File("Files/products.txt");
    private File orders = new File("Files/orders.txt");
    private File cart = new File("Files/cart.txt");
    private File tempFile = new File("Files/temp.txt");

    //singleton constructor
    private FileStorage() {
    }

    //singleton method
    public static FileStorage getInstance() {
        if(fileStorage == null) {
            fileStorage = new FileStorage();
        }
        return fileStorage;
    }
    
    //method to add user to the file according to the type
    public void addUser(User user, short userType) throws IOException {
        File file = getFileByType(userType);
        BufferedWriter writer;

        if(!checkUserExists(user, file)){
            writer = new BufferedWriter(new FileWriter(file, true));
            writer.write(user.toString());
            writer.newLine();
            writer.close();
            System.out.println("Registered Successfully!");
            return;
        }
        return;
    }

    //check if user exists by username,email,number
    public boolean checkUserExists(User user,File file) throws IOException {
        String split[];
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line = reader.readLine();

        while(line != null) {

            split = line.split("\\|");
            if(split[1].equals(user.getUserName())) {
                System.out.println("Username already exists.");
                reader.close();
                return true;
            }
            else if(split[3].equals(user.getEmail())) {
                System.out.println("Email already assoicated with another account.");
                reader.close();
                return true;
            }
            line = reader.readLine();
        }
        reader.close();
        return false; 
    }

    public short checkUser(String username, String password, short userType) throws IOException {
        File file = getFileByType(userType);
        String line;
        String[] split;
        BufferedReader reader = new BufferedReader(new FileReader(file));
        line = reader.readLine();
        while(line != null) {
            split = line.split("\\|");
            if(split[1].equals(username) && split[2].equals(password)) {
                System.out.println("Login successful!");
                reader.close();
                return userType;
            }
            line = reader.readLine();
        }
        reader.close();
        System.out.println("Invalid username or password.");
        return -1;
    }

    public void displayProduct(String[] split) {
        //this method displays the array with beautiful alignment like a table
        System.out.println("====================================================================================================");
        System.out.println("Product Id : " + split[1] + "\tBrand : " + split[10] +  "\t\tProduct Name : " + split[2] + "\tProduct Price : " + split[3]);
        System.out.println("Description : " + split[5]);
        System.out.println("Discount : " + split[7] +"%" +  "\t\tDiscounted Price : " + split[8] + "\t\tQuantity : " + split[4]);
        System.out.println("====================================================================================================");
    }

    public void deatileProductView(String productType) throws IOException {
        boolean flag = false;
        BufferedReader reader = new BufferedReader(new FileReader(products));
        String line = reader.readLine();

        while(line != null) {

            String[] split = line.split("\\|");
            if(split[6].equals(productType)) {
                displayProduct(split);
                flag = true;
            }
            line = reader.readLine();
        }
        reader.close();
        if(!flag) {
            System.out.println("Sorry no products available!");
        }
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
        BufferedReader reader = new BufferedReader(new FileReader(products));
        String line = reader.readLine();
        while(line != null) {

            String[] split = line.split("\\|");
            if(split[0].equals(sellerName )) {
                displayProduct(split);
            }
            line = reader.readLine();
        }
        reader.close();
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
            closeReaderWriter(reader, writer);
            products.delete();
            tempFile.renameTo(products);
            System.out.println("Product deleted successfully.");
    }

    //delete user from file
    public void deleteUser(String username, short userType) throws IOException {
        File file = getFileByType(userType);
        BufferedReader reader = new BufferedReader(new FileReader(file));
        File tempFile = new File("temp.txt");
        BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

        tempFile.createNewFile();
        String line = reader.readLine();
        while(line != null) {

            String[] split = line.split("\\|");
            if(split[1].equals(username)) {
                line = reader.readLine();
                continue;
            }
            writer.write(line);
            writer.newLine();
            line = reader.readLine();
        }
        reader.close();
        writer.close();
        file.delete();
        tempFile.renameTo(file);
        System.out.println("User deleted successfully.");
    }

    private File getFileByType(short userType) throws FileNotFoundException {
        if(userType == 1) {
            return customers;
        }
        else if(userType == 2) {
            return sellers;
        }
        else if(userType == 3) {
            return admins;
        }
        else {
            System.out.println("Wrong user type.");
        }
        return null;
    }


    //this method gets all the products in the cart in the cart file of the username and displays total price
    public void getCart(String username) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(cart));
        String line = reader.readLine();
        double total = 0;

        while(line != null) {
            String[] id = line.split("\\|");
            if(id[0].equals(username)) {
                String[] product = findProdcutByID(id[1]);
                displayProduct(product);
                total += Double.parseDouble(product[8]);
            }
            line = reader.readLine();
        }
        reader.close();
        if(total == 0) {
            System.out.println("Cart is empty.");
        } else {
            checkout(total, username);
        }
    }

    private void checkout(double total, String username) throws IOException {
        int deliveryDay;

        System.out.println("Total Price : " + total);
        System.out.println("Do you want to buy the products in the cart? (y/n)");
        String choice = scanner.nextLine();

        if(choice.equalsIgnoreCase("y")) {
            buyProducts(username);
            deliveryDay = (int)(Math.random() * 3) + 1;
            System.out.println("Your order will be delivered within " + deliveryDay + " days.");
            System.out.println("Thank you for shopping with us.");
        } else if(choice.equalsIgnoreCase("n")) {
            System.out.println("Come back later.");
        } else {
            System.out.println("Enter correct choice!");
        }
    }

    private String[] findProdcutByID(String productId) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(products));
        String line = reader.readLine();

        while(line != null) {

            String[] split = line.split("\\|");
            if(split[1].equals(productId)) {
                reader.close();
                return split;
            }
        line = reader.readLine();
        }
    reader.close();
    System.out.println("Enter Correct ID!");
    return null;
    }

    public void viewOrders(String username) throws IOException {
        boolean found = false;
        BufferedReader reader = new BufferedReader(new FileReader(orders));
        String line = reader.readLine();

        while(line != null) {

            String[] split = line.split("\\|");
            if(split[0].equals(username)) {
                //displays the order time and date
                System.out.println("Order Date : " + split[2]);
                String product[] = findProdcutByID(split[1]);
                displayProduct(product);
                found = true;
            }
            line = reader.readLine();
        }
        reader.close();
        if(!found) {
            System.out.println("No orders history found.");
        }
    }

    public void addToCart(String username, int id) throws IOException {
        if(checkStock(id)) {
            //add product and usernmae in cart file
            BufferedWriter writer = new BufferedWriter(new FileWriter(cart, true));
            writer.write(username + "|" + id);
            writer.newLine();
            writer.close();
            System.out.println("Product added to cart successfully.");

        }
    }

    //calculate total price of the products in the cart by username and display it if user wants to buy add it to orders file
    public void buyProducts(String username) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(cart));
        String line = reader.readLine();
        
        while(line != null) {

            String[] split = line.split("\\|");
            if(split[0].equals(username)) {
                //add product to orders file
                addOrder(username,split[1]);
                //remove product from stock
                removeStock(split[1]);
                //remove product from cart
                clearCart(username); 
            }
            line = reader.readLine();
        }
        reader.close();
    }

    private void clearCart(String username) throws IOException {
        //remove product from cart
        BufferedReader reader = new BufferedReader(new FileReader(cart));
        File tempFile = new File("temp.txt");
        BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));
        tempFile.createNewFile();
        String line = reader.readLine();
        while(line != null) {

            String[] split = line.split("\\|");
            if(split[0].equals(username)) {
                line = reader.readLine();
                continue;
            }
            writer.write(line);
            writer.newLine();
            line = reader.readLine();
        }
        closeReaderWriter(reader, writer);
        cart.delete();
        tempFile.renameTo(cart);
    }

    //this method add the order to orders file
    private void addOrder(String username, String productId) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(orders, true));
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");

        writer.write(username + "|" + productId + "|" + dtf.format(LocalDateTime.now()));
        writer.newLine();
        writer.close();
    }

    private boolean checkStock(int id) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(products));
        String line = reader.readLine();
        while(line != null) {

            String[] split = line.split("\\|");
            if(Integer.parseInt(split[1]) == id) {
                if(Integer.parseInt(split[4]) > 0) {
                    reader.close();
                    return true;
                } else {
                    System.out.println("Product is out of stock.");
                    reader.close();
                    return false;
                }
            }
            line = reader.readLine();
        }
        System.out.println("Product not found.");
        reader.close();
        return false;
    }

    //add stock to the product
    public void addStock(int id, int stock) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(products));
        BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));
        tempFile.createNewFile();
        String line = reader.readLine();
        while(line != null) {

            String[] split = line.split("\\|");
            if(Integer.parseInt(split[1]) == id) {
                int currentStock = Integer.parseInt(split[4]);
                currentStock = stock;
                line = split[0] + "|" + split[1] + "|" + split[2] + "|" + split[3] + "|" + currentStock + "|" + getStockString(split);
            }
            writer.write(line);
            writer.newLine();
            line = reader.readLine();
        }
        closeReaderWriter(reader, writer);
        products.delete();
        tempFile.renameTo(products);
    }

    public void removeStock(String id) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(products));
        BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

        tempFile.createNewFile();
        String line = reader.readLine();
        while(line != null) {

            String[] split = line.split("\\|");
            if(split[1].equals(id)) {
                int stock = Integer.parseInt(split[4]);
                stock--;
                line = split[0] + "|" + split[1] + "|" + split[2] + "|" + split[3] + "|" + stock + "|" + getStockString(split);
                System.out.println("Stock updated successfully.");
            }
            writer.write(line);
            writer.newLine();
            line = reader.readLine();
        }
        closeReaderWriter(reader, writer);
        products.delete();
        tempFile.renameTo(products);
        
    }

    //add stoock array and return it
    private String getStockString(String[] split) {
        String stock = "";
        stock +=split[5] + "|" + split[6] + "|" + split[7] + "|" + split[8] + "|" + split[9] + "|" + split[10] + "|" 
                 + split[11] + "|" + split[12] + "|" + split[13] + "|" + split[14] + "|" + split[15] + "|" + split[16] + "|"
                 + split[17];
        return stock;
    }

    private static void closeReaderWriter(BufferedReader br,BufferedWriter bw) throws IOException {
            br.close();
            bw.close();
    }

    public boolean checkUserProduct(String username, int productId) throws FileNotFoundException, IOException {
        //check seller is associated with the product or not
        BufferedReader reader = new BufferedReader(new FileReader(products));
        String line = reader.readLine();
        String id = String.valueOf(productId);
        boolean flag = false;

        while(line != null) {

            String[] split = line.split("\\|");
            if(split[0].equals(username) && split[1].equals(id) || username.equals("Admin")) {
                flag = true;
                break;
            }
            line = reader.readLine();
        }
        reader.close();
        return flag;
    }
}