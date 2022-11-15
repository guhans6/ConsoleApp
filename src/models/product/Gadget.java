package models.product;

public class Gadget extends Product{

    private String color;
    private String ram;
    private String rom;
    private String battery;
    private String camera;
    private String display;
    private String processor;
    private String os;
    private String warranty;

    public String getColor() {
        return color;
    }
    public void setColor(String color) {
        this.color = color;
    }
    public String getRam() {
        return ram;
    }
    public void setRam(String ram) {
        this.ram = ram;
    }
    public String getRom() {
        return rom;
    }
    public void setRom(String rom) {
        this.rom = rom;
    }
    public String getBattery() {
        return battery;
    }
    public void setBattery(String battery) {
        this.battery = battery;
    }
    public String getCamera() {
        return camera;
    }
    public void setCamera(String camera) {
        this.camera = camera;
    }
    public String getDisplay() {
        return display;
    }
    public void setDisplay(String display) {
        this.display = display;
    }
    public String getProcessor() {
        return processor;
    }
    public void setProcessor(String processor) {
        this.processor = processor;
    }
    public String getOs() {
        return os;
    }
    public void setOs(String os) {
        this.os = os;
    }
    public String getWarranty() {
        return warranty;
    }
    public void setWarranty(String warranty) {
        this.warranty = warranty;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        return sb.append(getProductID()).append("|").append(getProductBrand()).append("|").append(getProductName())
                .append("|").append(getPrice()).append("|").append(getQuantity()).append("|").append(getProductDescription())
                .append("|").append(getProductDiscount()).append("|").append(getProductDiscountedPrice()).append("|").append(getProductCategory())
                .append("|").append(getProductBrand()).append("|").append(getColor()).append("|").append(getRam())
                .append("|").append(getRom()).append("|").append(getBattery()).append("|").append(getCamera())
                .append("|").append(getDisplay()).append("|").append(getProcessor()).append("|").append(getOs())
                .append("|").append(getWarranty()).append("|").append(getFeatures()).toString();
        
    }    

}
