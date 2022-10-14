package Users;
import java.util.InputMismatchException;
import java.util.Scanner;

import Customer.Customer;
import Seller.Seller;
import Storage.Storage;

public class userMenu {
    
    private static Scanner scanner = new Scanner(System.in);
    public static void displayMainMenu(){
        Scanner scanner = new Scanner(System.in);
        char input=0;
        boolean b=true;
        while(b)
        {
            System.out.println("\n1. Login");
            System.out.println("2. Register");
            System.out.println("3. Exit Application");
            try {
                input = scanner.next().charAt(0); }
            catch(InputMismatchException e) {
                System.out.println("Enter from given options!");
            }

            switch(input) {
                case '1':
                    int checker = Storage.checkUser();
                    if(checker == 1) {
                        System.out.println("Login successful!");
                        customerMenu();
                    }
                    else if(checker == -1){
                        System.out.println("Login failed!");
                    }
                    break;
                case '2':
                    registerUser();
                    break;
                case '3':
                    b=false;
                    System.out.println("Bye!");
                    break;
                default:
                    System.out.println("Enter from given options!");
            }
        }
        scanner.close();
    }

    public static void customerMenu(){
        System.out.println("\n1. View Products");
        System.out.println("2. View Cart");
        System.out.println("3. View Orders");
        System.out.println("4. Logout");
        System.out.println("5. Exit");
    }
    
    public static void SellerMenu(){
        System.out.println("\n1. Add Product");
        System.out.println("2. View Products");
        System.out.println("3. View Orders");
        System.out.println("4. Logout");
        System.out.println("5. Exit");
    }
    
    public static void productMenu(){
        System.out.println("\n1. Add Product");
        System.out.println("2. View Products");
        System.out.println("3. Update Product");
        System.out.println("4. Remove Product");
        System.out.println("5. Exit");
    }
    
    public static void orderMenu(){
        System.out.println("\n1. View Orders");
        System.out.println("2. Update Order Status");
        System.out.println("3. Exit");
    }
    
    public static void cartMenu(){
        System.out.println("1. Add Product");
        System.out.println("2. Remove Product");
        System.out.println("3. View Cart");
        System.out.println("4. Checkout");
        System.out.println("5. Exit");
    }

    public static int userType() { 

        System.out.println("\nSelect user type.");
        System.out.println("1. Customer");
        System.out.println("2. Seller");
        System.out.println("3. Exit this menu");
        try {
            return scanner.nextInt();
        }
        catch(InputMismatchException e) {
            System.out.println("Enter corrct option!");
        }
        return 3;
    }

    public static boolean registerUser() {
            
        int userType = userMenu.userType();
        if(userType == 1) {
            Customer customer =  new Customer();
            customer.customerRegistration();
        }
        else if(userType == 2) {
            Seller seller = new Seller();
            seller.sellerRegistration();
        }
        else if(userType == 3) {
   
        }
        return false;
}


    
    // try { 
    //     if(input == '1')
    //     {
    //         if(Storage.checkUser())
    //         {
    //             System.out.println("Logged in successfully");
    //         }
    //         else 
    //         {
    //             System.out.println("Enter correct username or password");
    //         }
    //     }
    //     else if(input == '2')
    //     {
    //         userType();
    //         input2 = scanner.next().charAt(0);
    //         if(input2 == 1){
    //             new Customer().customerRegistration();
    //         }
    //         else {
    //             new Seller().sellerRegistration();
    //         }
    //     }
    //     else if(input == '3')
    //     { 
    //         b=false;
    //         scanner.close();
    //         //break;
    //     }
    //     else
    //     {
    //         System.out.println("Enter from given options");
    //     }
    // } 
    // catch(Exception e) {
    //         System.out.println("Enter from given options");
    //     }
// }

}