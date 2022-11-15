package storage;

import java.util.ArrayList;

import models.user.User;

public interface UserStorage {
    
    public boolean addUser(User user, int userType) throws Exception;
    public ArrayList<String> getProducList(String productType) throws Exception;
    public String[] findProdcutByID(String string) throws Exception;
    public short authenticateUser(String username, String password, short userType) throws Exception;
    
}
