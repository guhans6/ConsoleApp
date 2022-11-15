package ui;

import java.util.ArrayList;

public class AdminDisplay {
    
    private static AdminDisplay adminDisplay = null;
    private AdminDisplay() {
        
    }

    public static AdminDisplay getInstance() {
        if(adminDisplay == null) {
            adminDisplay = new AdminDisplay();
        }
        return adminDisplay;
    }

    public void displaySellers(ArrayList<String[]> users) {
        for(String[] user: users) {
            System.out.println("--------------------------------");
            System.out.println("Username: "+user[1]);
            System.out.println("Seller Name: "+user[0]);
            System.out.println("Office Address: "+user[4]);
            System.out.println("Email: "+user[3]);
            System.out.println("------------------------------------------------");
        }
    }

    public void displaycustomers(ArrayList<String[]> users) {
        for(String[] user: users) {
            System.out.println("--------------------------------");
            System.out.println("Username: "+user[1]);
            System.out.println("Customer Name: "+user[0]);
            System.out.println("Email: "+user[3]);
            System.out.println("------------------------------------------------");
        }
    }
}
