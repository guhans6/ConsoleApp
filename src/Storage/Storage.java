package Storage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import Customer.Customer;
import Seller.Product;
import Seller.Seller;
import Users.User;

public class Storage {

    private static int id = 0;
    private static Scanner scanner = new Scanner(System.in);
    private static File customers = new File("customers.txt");
    private static File sellers = new File("sellers.txt");
    private static File admins = new File("admins.txt");
    private static File products = new File("products.txt");
    private static File orders = new File("orders.txt");
    private static File cart = new File("cart.txt");

    
    //save customer to customers file
    public static boolean saveCustomer(Customer customer) {
        
        if(userExists(customer, customers)){
            try {
                BufferedWriter writer = new BufferedWriter(new FileWriter(customers, true));
                writer.write(customer.getName() + "|" + customer.getUserName() +"|" + customer.getPassword() + "|" + customer.getEmail() + "|" + customer.getAddress());
                writer.newLine();
                writer.close();
                //To intialize empty cart for customer
                writer.newLine();
                writer.close();
                return true;
            } catch(IOException e) {

                System.out.println("Error can't save user!");
            }
        }
        return false;
    
    }
    
    //save seller to seller file
    public static boolean saveSeller(Seller seller) {

        if(userExists(seller, sellers)) {
            try {
                BufferedWriter writer = new BufferedWriter(new FileWriter(sellers, true));
                writer.write(seller.getName() + "|" + seller.getUserName() +"|" + seller.getPassword() +  "|" + seller.getEmail() + "|" + seller.getAddress() + "|" + "null" + "|");
                writer.newLine();
                writer.close();
                return true;
            } catch(IOException e) {

                System.out.println("Error can't save user!");
            }
        }
        return false;
    }
    //check if user exists by username,email,number
    public static boolean userExists(User user,File file) {

        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line = reader.readLine();
            while(line != null) {

                String[] split = line.split("\\|");
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
        } catch(IOException e) {

            System.out.println("Error can't find user!");
        }
        return true; 
    }

    public static short checkUser(String username, String password, short userType) {
        FileReader fileReader = null;
        try {
            if(userType == 1) {
                fileReader = new FileReader(customers);
            }
            else if(userType == 2) {
                // System.out.println("Seller Portal.");
                fileReader = new FileReader(sellers);
            }
            else if(userType == 3) {
                // System.out.println("Admin Portal.");
                fileReader = new FileReader(admins);
            }
            else {
                return 0;
            }
            BufferedReader reader = new BufferedReader(fileReader);
            String line = reader.readLine();
            while(line != null) {

                String[] split = line.split("\\|");
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
        } catch(IOException e) {

            System.out.println("Error can't find user!");
        }
        return -1;
    }
    
    //add products to products file with usename as identification
    public static boolean addProduct(String username, Product product) {
        try {
            product.setProductID(getAvailavleID());
            BufferedWriter writer = new BufferedWriter(new FileWriter(products, true));
            //calculate discount
            double discount = product.getPrice() * (product.getProductDiscount() / 100);
            double discountedPrice = product.getPrice() - discount;
            product.setProductDiscountedPrice(discountedPrice);
            writer.write(username + "|" + product.toString());
            writer.newLine();
            writer.close();
            return true;
        } catch(IOException e) {

            System.out.println("Error can't save product!");
        }
        return false;
    }

    private static int getAvailavleID() {
        //read products file and get the last id and add 1 to it
        try {
            BufferedReader reader = new BufferedReader(new FileReader(products));
            String line = reader.readLine();
            while(line != null) {

                String[] split = line.split("\\|");
                id = Integer.parseInt(split[1]);
                line = reader.readLine();
            }
            reader.close();
        } catch(IOException e) {

            System.out.println("Error can't find user!");
        }
        return ++id;
    }

    public static void getProducts(String sellerName) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("products.txt"));
            String line = reader.readLine();
            while(line != null) {

                String[] split = line.split("\\|");
                if(split[0].equals(sellerName )) {
                    displayProduct(split);
                }
                line = reader.readLine();
            }
            reader.close();
        } catch(IOException e) {

            System.out.println("Error can't read!");
        }
    }

    private static void displayProduct(String[] split) {
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
            if(type == 1) { productType = "Laptop"; }
            else if(type == 2) { productType = "Mobile"; }
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

    public static void deleteProduct(String username, int id) {
        //this method deletes the product from the file using temp file
        try {
            BufferedReader reader = new BufferedReader(new FileReader(products));
            File tempFile = new File("temp.txt");
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));
            tempFile.createNewFile();
            String line = reader.readLine();
            while(line != null) {

                String[] split = line.split("\\|");
                if(split[0].equals(username) && Integer.parseInt(split[1]) == id) {
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
        } catch(IOException e) {

            System.out.println("Error can't find products!");
        }
    }

    //delete user from file
    public static void deleteUser(String username, short userType) {
        try {
            BufferedReader reader = null;
            File tempFile = new File("temp.txt");
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));
            tempFile.createNewFile();
            if(userType == 1) {
                reader = new BufferedReader(new FileReader(customers));
            }
            else if(userType == 2) {
                reader = new BufferedReader(new FileReader(sellers));
            }
            else if(userType == 3) { 
                System.out.println("Admin can't be deleted!");
            }
            else {
                System.out.println("Enter Correct Type!");
                writer.close();
                return;
            }
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
        } catch(IOException e) {

            System.out.println("Error can't find user!");
        }
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
                System.out.println("Total Price : " + total);
                System.out.println("Do you want to buy the products in the cart? (y/n)");
                String choice = scanner.nextLine();
                if(choice.equalsIgnoreCase("y")) {
                    buyProducts(username);
                //orders will be deliverd within 3 random days
                    System.out.println("Your order will be delivered within " + (int)(Math.random() * 3 + 1) + " days.");
                    System.out.println("Thank you for shopping with us.");
                }
                else {
                    System.out.println("Come back later.");
                }
            }
        } catch(IOException e) {
            System.out.println("Error can't find products!");
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

    public static void addToCart(String username, int id) {
        if(checkStock(id)) {
            //add product and usernmae in cart file
            try {
                BufferedWriter writer = new BufferedWriter(new FileWriter("cart.txt", true));
                writer.write(username + "|" + id);
                writer.newLine();
                writer.close();
                System.out.println("Product added to cart successfully.");
            } catch(IOException e) {

                System.out.println("Error can't save product!");
            }
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
                    removeStock(split[1]); // -Not this line
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
            File tempFile = new File("temp.txt");
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
                            + "|" + split[12] + "|" + split[13] + "|" + split[14] + "|" + split[15] + "|" + split[16] + "|" + split[17];
                }
                tempWriter.write(line);
                tempWriter.newLine();
                line = reader.readLine();
            }
            reader.close();
            tempWriter.close();
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
            reader.close();
            writer.close();
            cart.delete();
            tempFile.renameTo(cart);
        } catch(IOException e) {

            System.out.println("Error can't find products!");
        }
    }

    //this method add the order to orders file
    private static void addOrder(String username, String productId) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(products));
            String line = reader.readLine();
            String[] product = findProdcutByID(productId);
            while(line != null) {

                String[] split = line.split("\\|");
                if(split[1].equals(productId)) {
                    //add product to orders file
                    BufferedWriter writer = new BufferedWriter(new FileWriter(orders, true));
                    //current date and time
                    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                    writer.write(username + "|" + productId + "|" + dtf.format(LocalDateTime.now()));
                    writer.newLine();
                    writer.close();
                    // System.out.println("Product to orders successfully.");
                }
                line = reader.readLine();
            }
            reader.close();
        } catch(IOException e) {
            System.out.println("Error can't find products!");
        }
    }

    // private static double getProductPrice(int id) {
    //     try {
    //         BufferedReader reader = new BufferedReader(new FileReader(products));
    //         String line = reader.readLine();
    //         while(line != null) {

    //             String[] split = line.split("\\|");
    //             if(Integer.parseInt(split[1]) == id) {
    //                 System.out.println("Product Id : " + split[1] + " Product Name : " + split[2] + " Product Price : " + split[3]);
    //                 System.out.println("price : " + split[3]);
    //                 return Double.parseDouble(split[3]);
    //             }
    //             line = reader.readLine();
    //         }
    //         reader.close();
    //     } catch(IOException e) {

    //         System.out.println("Error can't find products!");
    //     }
    //     return 0;
    // }

    private static boolean checkStock(int id) { 
        try {
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
        } catch(IOException e) {

            System.out.println("Error can't find products!");
        }
        return false;
    }
}