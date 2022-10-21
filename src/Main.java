import java.util.Scanner;

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
