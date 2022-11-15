package storage;

import java.util.ArrayList;

public interface AdminStorage {
    
    public boolean deleteUser(String username, int userType) throws Exception;
    public ArrayList<String[]> getAllUsers(int userType) throws Exception;

}
