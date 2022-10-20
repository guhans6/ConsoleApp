
import java.io.IOException;
import java.util.Scanner;

import Customer.Customer;
import Seller.Laptop;
import Seller.Product;
import Seller.Seller;
import Storage.Storage;
import Users.userMenu;

public class Main {
    public static void main(String[] args) {
        
        Scanner sn = new Scanner(System.in);
        
        userMenu.displayMainMenu(); 
        
        // Product p = new Laptop();
        // p.setProductName("lap1");
        // p.setPrice(1000);
        // Storage.addProduct("sabarishG", p);

        
        // Storage.updateEmail(cus, "aa");;
        sn.close();
    }
}
