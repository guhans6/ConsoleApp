package models.product;

public class Product {
    
    private int productID;
    private String productBrand;
    private String productName;
    private double price;
    private int quantity;
    private String productDescription;
    private double productDiscount;
    private double productDiscountedPrice;
    private String productCategory;
    private String features;

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public String getProductBrand() {
        return productBrand;
    }

    public void setProductBrand(String productBrand) {
        this.productBrand = productBrand;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public double getProductDiscount() {
        return productDiscount;
    }

    public void setProductDiscount(double productDiscount) {
        this.productDiscount = productDiscount;
    }

    public double getProductDiscountedPrice() {
        return productDiscountedPrice;
    }

    public void setProductDiscountedPrice(double productDiscountedPrice) {
        this.productDiscountedPrice = productDiscountedPrice;
    }

    public String getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(String productCategory) {
        this.productCategory = productCategory;
    }

    public String getFeatures() {
        return features;
    }

    public void setFeatures(String features) {
        this.features = features;
    }

    

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(productID).append("|").append(productBrand).append("|").append(productName).append("|")
                .append(price).append("|").append(quantity).append("|").append(productDescription).append("|")
                .append(productDiscount).append("|").append(productDiscountedPrice).append("|").append(productCategory)
                .append("|").append(features);
        return builder.toString();
    }

    
    
}
