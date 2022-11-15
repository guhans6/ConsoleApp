package storage;

import java.util.ArrayList;

public interface CustomerStorage {
    
    ArrayList<String[]> getCart(String username) throws Exception; 
    ArrayList<String[]> getOrders(String username) throws Exception;
    void addToCart(String username, int id,int quantity) throws Exception;
    void buyProducts(String username) throws Exception;
    boolean removeProductFromCart(String username, int id) throws Exception; 
    public short checkStock(int id, int quantity) throws Exception;
}
