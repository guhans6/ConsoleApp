package Seller;

public interface Product {
    
    //Interface for product that contains all methods for online products
    public void setPrice(double price);
    public double getPrice();
    public void setQuantity(int quantity);
    public int getQuantity();
    public void setProductID(int productID);
    public int getProductID();
    public void setProductName(String productName);
    public String getProductName();
    public void setProductBrand(String productBrand);
    public String getProductBrand();
    public void setProductDescription(String productDescription);
    public String getProductDescription();
    public String getProductCategory();
    public void setProductDiscount(double productDiscount);
    public double getProductDiscount();
    public void setProductDiscountedPrice(double productDiscountedPrice);
    public double getProductDiscountedPrice();
    public void setProductRating(double productRating);
    public double getProductRating();
    public String toString();
    // public void setProductReviews(String productReviews);
    // public String getProductReviews();
}
