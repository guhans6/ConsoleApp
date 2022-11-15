package storage;

import java.util.ArrayList;

import models.product.Product;


public interface SellerStorage {
    
    boolean saveProduct(String username, Product product) throws Exception;
    ArrayList<String> getSellerProducts(String sellerName) throws Exception;
    void deleteProduct(int id) throws Exception;
    boolean checkUserProductAssociated(String sellerUsername, int productId) throws Exception;
    void saveStock(int id, int stock) throws Exception;

}
