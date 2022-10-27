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
import customer.Customer;
import seller.Seller;

public class FileStorage {

    private static int id = 0;
    private static Scanner scanner = new Scanner(System.in);
    private static File customers = new File("Files/customers.txt");
    private static File sellers = new File("Files/sellers.txt");
    private static File admins = new File("Files/admins.txt");
    private static File products = new File("Files/products.txt");
    private static File orders = new File("Files/orders.txt");
    private static File cart = new File("Files/cart.txt");
    private static File tempFile = new File("Files/temp.txt");
    
    //save customer to customers file
    public static boolean saveCustomer(Customer customer) throws IOException {
        if(userExists(customer, customers)){

            BufferedWriter writer = new BufferedWriter(new FileWriter(customers, true));
            writer.write(customer.toString());
            writer.newLine();
            writer.close();
            return true;
        }
        return false;
    }
    
    //save seller to seller file
    public static boolean saveSeller(Seller seller) throws IOException {

        if(userExists(seller, sellers)) {

            BufferedWriter writer = new BufferedWriter(new FileWriter(sellers, true));
            writer.write(seller.toString());
            writer.newLine();
            writer.close();
            return true;
        }
        return false;
    }

    //check if user exists by username,email,number
    public static boolean userExists(User user,File file) throws IOException {
        String split[];
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line = reader.readLine();

        while(line != null) {

            split = line.split("\\|");
            if(split[1].equals(user.getUserName())) {
                System.out.println("Username already exists.");
                reader.close();
                return false;
            }
            else if(split[3].equals(user.getEmail())) {
                System.out.println("Email already assoicated with another account.");
                reader.close();
                return false;
            }
            line = reader.readLine();
        }
        reader.close();
        return true; 
    }

    public static short checkUser(String username, String password, short userType) throws IOException {
        FileReader fileReader = null;
        String line;
        String[] split;
        
        if(userType == 1) {
            fileReader = new FileReader(customers);
        }
        else if(userType == 2) {
            fileReader = new FileReader(sellers);
        }
        else if(userType == 3) {
            fileReader = new FileReader(admins);
        }
        else {
            return 0;
        }
        
        BufferedReader reader = new BufferedReader(fileReader);
        line = reader.readLine();
        while(line != null) {
            split = line.split("\\|");
            
            if(split[1].equals(username)) {
                if(split[2].equals(password)) {
                    reader.close();
                    return userType;
                }
                else {
                    System.out.println("Wrong password.");
                    reader.close();
                    return -1;
                }
            }
            line = reader.readLine();
        }

        reader.close();
        System.out.println("User not found.");
        return -1;
    }

    public static void displayProduct(String[] split) {
        //this method displays the array with beautiful alignment like a table
        System.out.println("====================================================================================================");
        System.out.println("Product Id : " + split[1] + "\tBrand : " + split[10] +  "\t\tProduct Name : " + split[2] + "\tProduct Price : " + split[3]);
        System.out.println("Description : " + split[5]);
        System.out.println("Discount : " + split[7] +"%" +  "\t\tDiscounted Price : " + split[8]);
        System.out.println("====================================================================================================");
    }

    public static void deatileProductView(int type) {
        boolean flag = false;
        try {
            BufferedReader reader = new BufferedReader(new FileReader("products.txt"));
            String line = reader.readLine();
            String productType;

            if(type == 1) {
                productType = "Laptop"; 
            }
            else if(type == 2) { 
                productType = "Mobile"; 
            }
            else {
                System.out.println("Enter Correct Type!");
                reader.close();
                return;
            }
            while(line != null) {

                String[] split = line.split("\\|");
                if(split[6].equals(productType)) {
                    displayProduct(split);
                    flag = true;
                }
                line = reader.readLine();
            }
            reader.close();
        } catch(IOException e) {
            System.out.println("Error can't find products!");
        }
        if(!flag) {
            System.out.println("Sorry no products available!");
        }
    }

    public static void deleteProduct(int id) throws IOException {
        // try {
            BufferedReader reader = new BufferedReader(new FileReader("product.txt"));
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
    public static void deleteUser(String username, short userType) throws IOException {
        BufferedReader reader = getFileByType(userType);
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
        if(userType == 1) {
            customers.delete();
        }
        else if(userType == 2) {
            sellers.delete();
        }
        else if(userType == 3) {
            admins.delete();
        }
        tempFile.renameTo(customers);
        System.out.println("User deleted successfully.");
    }

    private static BufferedReader getFileByType(short userType) throws FileNotFoundException {
        if(userType == 1) {
            return new BufferedReader(new FileReader(customers));
        }
        else if(userType == 2) {
            return new BufferedReader(new FileReader(sellers));
        }
        else if(userType == 3) {
            System.out.println("Admin can't be deleted.");
        }
        else {
            System.out.println("Wrong user type.");
        }
        return null;
    }

    //this method gets all the products in the cart in the cart file of the username and displays total price
    public static void getCart(String username) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("cart.txt"));
            String line = reader.readLine();
            double total = 0;
            while(line != null) {
                String[] id = line.split("\\|");
                if(id[0].equals(username)) {
                    String[] product = findProdcutByID(id[1]);
                    displayProduct(product);
                    total += Double.parseDouble(product[3]);
                }
                line = reader.readLine();
            }
            reader.close();
            if(total == 0) {
                System.out.println("Cart is empty.");
            }
            else {
                checkout(total, username);
            }
        } catch(IOException e) {
            System.out.println("Error can't find products!");
        }
    }

    private static void checkout(double total, String username) {
        int deliveryDay;

        System.out.println("Total Price : " + total);
        System.out.println("Do you want to buy the products in the cart? (y/n)");
        String choice = scanner.nextLine();

        if(choice.equalsIgnoreCase("y")) {
            buyProducts(username);
            deliveryDay = (int)(Math.random() * 3) + 1;
            System.out.println("Your order will be delivered within " + deliveryDay + " days.");
            System.out.println("Thank you for shopping with us.");
        }
        else if(choice.equalsIgnoreCase("n")) {
            System.out.println("Come back later.");
        }
        else {
            System.out.println("Enter correct choice!");
        }
    }

    private static String[] findProdcutByID(String productId) {
        try {
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
        } catch(IOException e) {
            System.out.println("Error can't find products!");
        }
        System.out.println("Enter Correct ID!");
        return null;
    }

    public static void viewOrders(String username) {
        boolean found = false;
        try {
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
        } catch(IOException e) {
            System.out.println("Error can't find products!");
        }
        if(!found) {
            System.out.println("No orders history found.");
        }
    }

    public static void addToCart(String username, int id) throws IOException {
        if(checkStock(id)) {
            //add product and usernmae in cart file
            BufferedWriter writer = new BufferedWriter(new FileWriter("cart.txt", true));
            writer.write(username + "|" + id);
            writer.newLine();
            writer.close();
            System.out.println("Product added to cart successfully.");

        }
    }

    //calculate total price of the products in the cart by username and display it if user wants to buy add it to orders file
    public static void buyProducts(String username) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("cart.txt"));
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
        } catch(IOException e) {
            System.out.println("Error can't find products!");
        }
    }

    //remove stock by one 
    public static void removeStock(String id) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(products));
            BufferedWriter tempWriter = new BufferedWriter(new FileWriter(tempFile));

            tempFile.createNewFile();
            String line = reader.readLine();
            while(line != null) {
                String[] split = line.split("\\|");
                if(split[1].equals(id)) {
                    int stock = Integer.parseInt(split[4]);
                    stock--;
                    split[4] = stock + "";
                    line = split[0] + "|" + split[1] + "|" + split[2] + "|" + split[3] + "|" + split[4] + "|" + split[5] 
                           + "|" + split[6] + "|" + split[7] + "|" + split[8] + "|" + split[9] + "|" + split[10] + "|" + split[11]
                           + "|" + split[12] + "|" + split[13] + "|" + split[14] + "|" + split[15] + "|" + split[16] 
                           + "|" + split[17];
                }
                tempWriter.write(line);
                tempWriter.newLine();
                line = reader.readLine();
            }
            closeReaderWriter(reader, tempWriter);
            products.delete();
            tempFile.renameTo(products);
        } catch(IOException e) {

            System.out.println("Error can't find products!");
        }
    }

    private static void clearCart(String username) {
        //remove product from cart
        try {
            BufferedReader reader = new BufferedReader(new FileReader("cart.txt"));
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
        } catch(IOException e) {

            System.out.println("Error can't find products!");
        }
    }

    //this method add the order to orders file
    private static void addOrder(String username, String productId) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(products));
        String line = reader.readLine();

        while(line != null) {
            String[] split = line.split("\\|");
            if(split[1].equals(productId)) {
                BufferedWriter writer = new BufferedWriter(new FileWriter(orders, true));
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

                writer.write(username + "|" + productId + "|" + dtf.format(LocalDateTime.now()));
                writer.newLine();
                writer.close();
            }
            line = reader.readLine();
        }
        reader.close();
    }

    private static boolean checkStock(int id) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(products));
        String line = reader.readLine();
        while(line != null) {

            String[] split = line.split("\\|");
            if(Integer.parseInt(split[1]) == id) {
                if(Integer.parseInt(split[4]) > 0) {
                    reader.close();
                    return true;
                }
                else {
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

    private static void closeReaderWriter(BufferedReader br,BufferedWriter bw) throws IOException {
            br.close();
            bw.close();
    }
}