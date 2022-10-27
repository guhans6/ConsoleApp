package customer;
import product.Product;

public class Orders {

    private int orderId;
    private String ProductName;
    private Product product;
    private String orderDate;
    private String deliveryDate;
    
    public int getOrderId() {
        return orderId;
    }
    
    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }
    
    public String getProductName() {
        return ProductName;
    }
    
    public void setProductName(String ProductName) {
        this.ProductName = ProductName;
    }
    
    public Product getProduct() {
        return product;
    }
    
    public void setProduct(Product product) {
        this.product = product;
    }
    
    public String getOrderDate() {
        return orderDate;
    }
    
    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }
    
    public String getDeliveryDate() {
        return deliveryDate;
    }
    
    public void setDeliveryDate(String deliveryDate) {
        this.deliveryDate = deliveryDate;
    }
    
    @Override
    public String toString() {
        return "Orders{" + "orderId=" + orderId + ", ProductName=" + ProductName + ", product=" + product + ", orderDate=" + orderDate + ", deliveryDate=" + deliveryDate + '}';
    }
}
