
import java.io.IOException;
import java.util.Scanner;

import Customer.Customer;
import Seller.Seller;
import Storage.Storage;
import Users.userMenu;

public class Main {
    public static void main(String[] args) {
        
        Scanner sn = new Scanner(System.in);
        
        userMenu.displayMainMenu(); 
    
        // Customer cus = new Customer();
        // cus.setUserName("guhan");
        // cus.setEmail("aa");


        
        // Storage.updateEmail(cus, "aa");;
        sn.close();
    }
}
