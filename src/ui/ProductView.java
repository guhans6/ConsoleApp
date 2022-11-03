package ui;

import java.util.ArrayList;

public class ProductView {

    private static ProductView productView = null;

    private ProductView() {
    }

    public static ProductView getInstance() {
        if (productView == null) {
            productView = new ProductView();
        }
        return productView;
    }

    public void displayProductView() {
        System.out.println("1. Laptop");
        System.out.println("2. Mobile");
        System.out.println("3. Exit");
    }

     //this method displays product deatils
    public void displayProduct(String[] split) {
        System.out.println("====================================================================================================");
        System.out.println("Product Id : " + split[1] + "\tBrand : " + split[9] +  "\t\tProduct Name : " + split[2] + "\tProduct Price : " + split[3]);
        System.out.println("Description : " + split[5]);
        System.out.println("Discount : " + split[7] +"%" +  "\t\tDiscounted Price : " + split[8] + "\t\tAvailable : " + split[4]);
        System.out.println("=========================================xxxxxxx===================================================");
    }
    public void displayProductDetails(ArrayList<String> prodcutList, int viewType) {
        for (String product : prodcutList) {
            String[] split = product.split("\\|");
            if(viewType == 1) {
                displayProduct(split);
            } else if(viewType == 2) {
                displayProductWithDetails(split);
            }
        }
    }

    //display the product in a deatiled way
    public void displayProductWithDetails(String[] split) {
        System.out.println("====================================================================================================");
        System.out.println("Product Id : " + split[1] + "\tBrand : " + split[9] +  "\t\tProduct Name : " + split[2] + "\tProduct Price : " + split[3]);
        System.out.println();
        System.out.println("Discount : " + split[7] +"%" +  "\t\tDiscounted Price : " + split[8] + "\t\tAvailable : " + split[4]);
        System.out.println("====================================================================================================");
        System.out.println("Description : " + split[5]);
        System.out.println("====================================================================================================");
        System.out.println("Processor : " + split[15]);
        System.out.println("RAM : " + split[11]);
        System.out.println("Storage : " + split[12]);
        System.out.println("Display : " + split[14]);
        System.out.println("Battery : " + split[13]);
        System.out.println("=========================================xxxxxxx====================================================");
    }

    public void cartView(String[] split, String quantity) {
        System.out.println("====================================================================================================");
        System.out.println("Product Brand : " + split[9] + "\tProduct Name : " + split[2] + "\tProduct Price : " + split[8]);
        System.out.println("Ordered Quantity : " +  quantity);
        System.out.println("====================================================================================================");
    }

    public void orderView(String[] productDetails, String orderDate, String total, String quantity) {
        System.out.println("====================================================================================================");
        System.out.println("Order Date : " + orderDate);
        System.out.println("\nProduct Brand : " + productDetails[9] + "\tProduct Name : " + productDetails[2] + "\tProduct Price : " + productDetails[8]);
        System.out.println("\nOrdered Quantity : " +  quantity);
        System.out.println("\nTotal : " + total);
        System.out.println("===========================================xxxxxxx==================================================");
    }
}
