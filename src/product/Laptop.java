package product;


public class Laptop implements Product {

    //varibles
    private double price;
    private int quantity;
    private int productID;
    private String productName;
    private String productDescription;
    final private String productCategory = "Laptop";
    private double productDiscount;
    private double productDiscountedPrice;
    private String laptopBrand;
    private String laptopColor;
    private String laptopRam;
    private String laptopRom;
    private String laptopBattery;
    private String laptopDisplay;
    private String laptopProcessor;
    private String laptopOs;
    private String laptopWarranty;

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
    public void setProductBrand(String productBrand) {
        this.laptopBrand = productBrand;
    }

    @Override
    public String getProductBrand() {
        return laptopBrand;
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

    @Override
    public String toString() {
        return productID + "|" + productName + "|" + price + "|" + quantity + "|" + productDescription + "|" + productCategory
                + "|" + productDiscount + "|" + productDiscountedPrice + "|" + laptopBrand + "|"
                + laptopColor + "|" + laptopRam + "|" + laptopRom + "|" + laptopBattery + "|" + laptopDisplay + "|"
                + laptopProcessor + "|" + laptopOs + "|" + laptopWarranty + "|" ;
    }
}
