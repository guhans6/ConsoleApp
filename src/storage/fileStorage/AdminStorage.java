package storage.fileStorage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class AdminStorage extends FileStorage {

    private BufferedReader reader;

     //delete user from file
     public boolean deleteUser(String username, short userType) throws IOException {
        boolean flag = false;
        File file = getFileByType(userType);
        reader = new BufferedReader(new FileReader(file));
        File tempFile = new File("temp.txt");
        BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

        tempFile.createNewFile();
        String line = reader.readLine();
        while(line != null) {

            String[] split = line.split("\\|");
            if(split[1].equals(username)) {
                line = reader.readLine();
                flag = true;
                continue;
            }
            writer.write(line);
            writer.newLine();
            line = reader.readLine();
        }
        writer.close();;
        file.delete();
        tempFile.renameTo(file);
        return flag;
    }

    public ArrayList<String[]> getAllUsers(int userType) throws IOException {
        ArrayList<String[]> users = new ArrayList<String[]>();
        String line;
        if(userType == 1) {
            reader = new BufferedReader(new FileReader("Files/customers.txt"));
        } else if(userType == 2) {
            reader = new BufferedReader(new FileReader("Files/sellers.txt"));
        }
        line = reader.readLine();
        while(line != null) {
            users.add(line.split("\\|"));
            line = reader.readLine();
        }
        return users;
    }

    //convert user string to user object and return it
    public String[] getUser(String username, short userType) throws IOException {
        String line;
        if(userType == 1) {
            reader = new BufferedReader(new FileReader("Files/customers.txt"));
        } else if(userType == 2) {
            reader = new BufferedReader(new FileReader("Files/sellers.txt"));
        }
        line = reader.readLine();
        while(line != null) {
            String[] split = line.split("\\|");
            if(split[1].equals(username)) {
                return split;
            }
            line = reader.readLine();
        }
        return null;
    }

    //close all objects
    public void close() throws IOException {
        reader.close();
    }
}
