package storage.fileStorage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import models.user.User;
import storage.UserStorage;

public class UserFileStorage implements UserStorage {

    private File products = new File("Files/products.txt");
    private BufferedReader reader;

    
    //method to add user to the file according to the type
    public boolean addUser(User user, int userType) {
        BufferedWriter writer = null;
        try {
            File file = new File(getUserByType(userType));

            if(!checkUserExists(user, userType)) {
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
    private boolean checkUserExists(User user, int userType) throws IOException {
        File file = new File(getUserByType(userType));
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
        File file = new File(getUserByType(userType));
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
        reader = new BufferedReader(new FileReader(products));
        String line = reader.readLine();

        while(line != null) {

            String[] split = line.split("\\|");
            if(split[8].equals(productType)) {
                productList.add(line);
            }
            line = reader.readLine();
        }
        return productList;
    }

    

    static String getUserByType(int userType) {
        if(userType == 1) {
            return "Files/customers.txt";
        } else if(userType == 2) {
            return "Files/sellers.txt";
        } else if(userType == 3) {
            return "Files/admins.txt";
        } else {
            return null;
        }
    }

    public String[] findProdcutByID(String productId) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("Files/products.txt"));
        String line = reader.readLine();

        while(line != null) {

            String[] split = line.split("\\|");
            if(split[0].equals(productId)) {
                reader.close();
                return split;
            }
        line = reader.readLine();
        }
        reader.close();
        return null;
    }


    //add stoock array and return it
    static String getStockString(String[] split) {
        StringBuilder stock = new StringBuilder();
        for(int i = 5; i < split.length; i++) {
            stock.append(split[i]);
            stock.append("|");
        }
        return stock.toString();
    }
}