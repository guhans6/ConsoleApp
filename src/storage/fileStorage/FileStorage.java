package storage.fileStorage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import models.user.User;

public class FileStorage {

    private File customers = new File("Files/customers.txt");
    private File sellers = new File("Files/sellers.txt");
    private File products = new File("Files/products.txt");
    private BufferedReader reader;

    
    //method to add user to the file according to the type
    public boolean addUser(User user, short userType) {
        BufferedWriter writer = null;
        try {
            File file = getFileByType(userType);

            if(!checkUserExists(user, file)){
                writer = new BufferedWriter(new FileWriter(file, true));
                writer.write(user.toString());
                writer.newLine();
                writer.close();;
                return true;
            }
        } catch (IOException e) {
            System.out.println("Error while adding user. Please try again.");
        }
        return false;
    }

    //check if user exists by username,email,number
    private boolean checkUserExists(User user,File file) throws IOException {
        String split[];
        reader = new BufferedReader(new FileReader(file));
        String line = reader.readLine();

        while(line != null) {

            split = line.split("\\|");
            if(split[1].equals(user.getUserName()) || split[3].equals(user.getEmail())) {
                return true;
            }

            line = reader.readLine();
        }
        return false; 
    }

    public short authenticateUser(String username, String password, short userType) throws IOException {
        File file = getFileByType(userType);
        String line;
        String[] split;
        reader = new BufferedReader(new FileReader(file));

        line = reader.readLine();
        while(line != null) {
            split = line.split("\\|");
            if(split[1].equals(username) && split[2].equals(password)) {
                return userType;
            }
            line = reader.readLine();
        }
        return -1;
    }

    public ArrayList<String> getProducList(String productType) throws IOException {
        ArrayList<String> productList = new ArrayList<>();
        boolean flag = false;
        reader = new BufferedReader(new FileReader(products));
        String line = reader.readLine();

        while(line != null) {

            String[] split = line.split("\\|");
            if(split[6].equals(productType)) {
                productList.add(line);
                flag = true;
            }
            line = reader.readLine();
        }
        if(!flag) {
            return null;
        }
        return productList;
    }

    

    protected File getFileByType(short userType) throws FileNotFoundException {
        if(userType == 1) {
            return customers;
        }
        else if(userType == 2) {
            return sellers;
        }
        else {
            System.out.println("Wrong user type.");
        }
        return null;
    }

    public String[] findProdcutByID(String productId) throws IOException {
        reader = new BufferedReader(new FileReader(products));
        String line = reader.readLine();

        while(line != null) {

            String[] split = line.split("\\|");
            if(split[1].equals(productId)) {
                return split;
            }
        line = reader.readLine();
        }
    // System.out.println("Enter Correct ID!");
    return null;
    }

    //add stoock array and return it
    protected String getStockString(String[] split) {
        String stock = "";
        stock +=split[5] + "|" + split[6] + "|" + split[7] + "|" + split[8] + "|" + split[9] + "|" + split[10] + "|" 
                 + split[11] + "|" + split[12] + "|" + split[13] + "|" + split[14] + "|" + split[15] + "|" + split[16];
        return stock;
    }
}