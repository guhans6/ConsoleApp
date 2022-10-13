package Seller;
public class Laptop implements Product {

    //varibles
    private double price;
    private int quantity;
    private int productID;
    private String productName;
    private String productDescription;
    private String productCategory;
    private double productDiscount;
    private double productDiscountedPrice;
    private double productRating;
    private String laptopBrand;
    private String laptopColor;
    private String laptopRam;
    private String laptopRom;
    private String laptopBattery;
    private String laptopDisplay;
    private String laptopProcessor;
    private String laptopOs;
    private String laptopWarranty;
    private String laptopFeatures;

    //getters and setters

    @Override
    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public double getPrice() {
        return price;
    }

    @Override
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public int getQuantity() {
        return quantity;
    }

    @Override
    public void setProductID(int productID) {
        this.productID = productID;
    }

    @Override
    public int getProductID() {
        return productID;
    }

    @Override
    public void setProductName(String productName) {
        this.productName = productName;
    }

    @Override
    public String getProductName() {
        return productName;
    }

    @Override
    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    @Override
    public String getProductDescription() {
        return productDescription;
    }

    @Override
    public void setProductCategory(String productCategory) {
        this.productCategory = productCategory;
    }

    @Override
    public String getProductCategory() {
        return productCategory;
    }

    @Override
    public void setProductDiscount(double productDiscount) {
        this.productDiscount = productDiscount;
    }

    @Override
    public double getProductDiscount() {
        return productDiscount;
    }

    @Override
    public void setProductDiscountedPrice(double productDiscountedPrice) {
        this.productDiscountedPrice = productDiscountedPrice;
    }

    @Override
    public double getProductDiscountedPrice() {
        return productDiscountedPrice;
    }

    @Override
    public void setProductRating(double productRating) {
        this.productRating = productRating;
    }

    @Override
    public double getProductRating() {
        return this.productRating;
    }

    public String getLaptopBrand() {
        return laptopBrand;
    }

    public void setLaptopBrand(String laptopBrand) {
        this.laptopBrand = laptopBrand;
    }

    public String getLaptopColor() {
        return laptopColor;
    }

    public void setLaptopColor(String laptopColor) {
        this.laptopColor = laptopColor;
    }

    public String getLaptopRam() {
        return laptopRam;
    }

    public void setLaptopRam(String laptopRam) {
        this.laptopRam = laptopRam;
    }

    public String getLaptopRom() {
        return laptopRom;
    }

    public void setLaptopRom(String laptopRom) {
        this.laptopRom = laptopRom;
    }

    public String getLaptopBattery() {
        return laptopBattery;
    }

    public void setLaptopBattery(String laptopBattery) {
        this.laptopBattery = laptopBattery;
    }

    public String getLaptopDisplay() {
        return laptopDisplay;
    }

    public void setLaptopDisplay(String laptopDisplay) {
        this.laptopDisplay = laptopDisplay;
    }

    public String getLaptopProcessor() {
        return laptopProcessor;
    }

    public void setLaptopProcessor(String laptopProcessor) {
        this.laptopProcessor = laptopProcessor;
    }

    public String getLaptopOs() {
        return laptopOs;
    }

    public void setLaptopOs(String laptopOs) {
        this.laptopOs = laptopOs;
    }

    public String getLaptopWarranty() {
        return laptopWarranty;
    }

    public void setLaptopWarranty(String laptopWarranty) {
        this.laptopWarranty = laptopWarranty;
    }

    public String getLaptopFeatures() {
        return laptopFeatures;
    }

    public void setLaptopFeatures(String laptopFeatures) {
        this.laptopFeatures = laptopFeatures;
    }


}
