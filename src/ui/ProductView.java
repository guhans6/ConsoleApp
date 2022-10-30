package ui;

public class ProductView {

    public void displayProductView() {
        System.out.println("1. Laptop");
        System.out.println("2. Mobile");
        System.out.println("3. Exit");
    }

     //this method displays product deatils
    public static void displayProduct(String[] split) {
        System.out.println("====================================================================================================");
        System.out.println("Product Id : " + split[1] + "\tBrand : " + split[9] +  "\t\tProduct Name : " + split[2] + "\tProduct Price : " + split[3]);
        System.out.println("Description : " + split[5]);
        System.out.println("Discount : " + split[7] +"%" +  "\t\tDiscounted Price : " + split[8] + "\t\tQuantity : " + split[4]);
        System.out.println("====================================================================================================");
    }
    
    //display the product in a deatiled way
    public static void displayProductDetails(String[] split) {
        System.out.println("====================================================================================================");
        System.out.println("Product Id : " + split[1] + "\tBrand : " + split[9] +  "\t\tProduct Name : " + split[2] + "\tProduct Price : " + split[3]);
        System.out.println();
        System.out.println("Discount : " + split[7] +"%" +  "\t\tDiscounted Price : " + split[8] + "\t\tQuantity : " + split[4]);
        System.out.println("====================================================================================================");
        System.out.println("Description : " + split[5]);
        System.out.println("====================================================================================================");
        System.out.println("Processor : " + split[15]);
        System.out.println("RAM : " + split[11]);
        System.out.println("Storage : " + split[12]);
        System.out.println("Display : " + split[14]);
        System.out.println("Battery : " + split[13]);
        System.out.println("====================================================================================================");
    }
}
