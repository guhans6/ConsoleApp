package storage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class AdminStorage extends FileStorage {

    private BufferedReader reader;
    private BufferedWriter writer;

     //delete user from file
     public void deleteUser(String username, short userType) throws IOException {
        File file = getFileByType(userType);
        reader = new BufferedReader(new FileReader(file));
        File tempFile = new File("temp.txt");
        writer = new BufferedWriter(new FileWriter(tempFile));

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
        writer.flush();
        file.delete();
        tempFile.renameTo(file);
        System.out.println("User deleted successfully.");
    }

    public void displayAllCustomers() throws IOException {
        reader = new BufferedReader(new FileReader("Files/customers.txt"));
        String line = reader.readLine();
        while(line != null) {
            String[] split = line.split("\\|");
            System.out.println("-" + "Username: "+split[1]+ " Email: "+split[3]);
            line = reader.readLine();
        }
    }

    public void displayAllSellers() throws IOException {
        reader = new BufferedReader(new FileReader("Files/sellers.txt"));
        String line = reader.readLine();
        while(line != null) {
            String[] split = line.split("\\|");
            System.out.println("-" + "Username: "+split[1]+ " Email: "+split[3]);
            line = reader.readLine();
        }
    }

    //close all objects
    public void close() throws IOException {
        reader.close();
        writer.close();
    }
    
}
