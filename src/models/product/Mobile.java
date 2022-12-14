package models.product;

public class Mobile implements Product{

    //varibles
    private double price;
    private int quantity;
    private int productID;
    private String productName;
    private String productDescription;
    final private String productCategory = "Mobile";
    private double productDiscount;
    private double productDiscountedPrice;
    private String mobileBrand;
    private String mobileColor;
    private String mobileRam;
    private String mobileRom;
    private String mobileBattery;
    private String mobileCamera;
    private String mobileDisplay;
    private String mobileProcessor;
    private String mobileOs;
    private String mobileWarranty;

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
    public String getProductCategory() {
        return productCategory;
    }

    @Override
    public void setProductBrand(String productBrand) {
        this.mobileBrand = productBrand;
        
    }

    @Override
    public String getProductBrand() {
        return mobileBrand;
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

    public String getMobileBrand() {
        return mobileBrand;
    }

    public void setMobileBrand(String mobileBrand) {
        this.mobileBrand = mobileBrand;
    }

    public String getMobileColor() {
        return mobileColor;
    }

    public void setMobileColor(String mobileColor) {
        this.mobileColor = mobileColor;
    }

    public String getMobileRam() {
        return mobileRam;
    }

    public void setMobileRam(String mobileRam) {
        this.mobileRam = mobileRam;
    }

    public String getMobileRom() {
        return mobileRom;
    }

    public void setMobileRom(String mobileRom) {
        this.mobileRom = mobileRom;
    }

    public String getMobileBattery() {
        return mobileBattery;
    }

    public void setMobileBattery(String mobileBattery) {
        this.mobileBattery = mobileBattery;
    }

    public String getMobileCamera() {
        return mobileCamera;
    }

    public void setMobileCamera(String mobileCamera) {
        this.mobileCamera = mobileCamera;
    }

    public String getMobileDisplay() {
        return mobileDisplay;
    }

    public void setMobileDisplay(String mobileDisplay) {
        this.mobileDisplay = mobileDisplay;
    }

    public String getMobileProcessor() {
        return mobileProcessor;
    }

    public void setMobileProcessor(String mobileProcessor) {
        this.mobileProcessor = mobileProcessor;
    }

    public String getMobileOs() {
        return mobileOs;
    }

    public void setMobileOs(String mobileOs) {
        this.mobileOs = mobileOs;
    }

    public String getMobileWarranty() {
        return mobileWarranty;
    }

    public void setMobileWarranty(String mobileWarranty) {
        this.mobileWarranty = mobileWarranty;
    }

    @Override
    public String toString() {
        return productID + "|" + productName + "|" + price + "|" + quantity + "|" + productDescription + "|" + productCategory 
               + "|" + productDiscount + "|" + productDiscountedPrice + "|" + mobileBrand + "|" + mobileColor 
               + "|" + mobileRam + "|" + mobileRom + "|" + mobileBattery + "|" + mobileDisplay + "|" 
               + mobileProcessor + "|" + mobileOs + "|" + mobileWarranty;
        
    }    

}
