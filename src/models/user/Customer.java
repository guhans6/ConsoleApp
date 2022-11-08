package models.user;

public class Customer implements User {

    private String customerName;
    private String customerUserName;
    private String customerEmail;
    private String customerPassword;
    private String address;

    @Override
    public void setName(String customerName) {
        this.customerName = customerName;
    }

    @Override
    public String getName() {
        return customerName;
    }

    @Override
    public void setUserName(String customerUserName) {
        this.customerUserName = customerUserName;
    }

    @Override
    public String getUserName() {
        return customerUserName;
    }

    @Override
    public void setEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    @Override
    public String getEmail() {
        return customerEmail;
    }

    @Override
    public void setPassword(String customerPassword) {
        this.customerPassword = customerPassword;
    }

    @Override
    public String getPassword() {
        return customerPassword;
    }

    @Override
    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String getAddress() {
        return address;
    }

    @Override
    public String toString() {
        return this.getName() + "|" + this.getUserName() +"|" + this.getPassword() 
               + "|" + this.getEmail() + "|" + this.getAddress();
    }
}
