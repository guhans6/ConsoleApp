package models.user;

public class Seller implements User{

    //create variables and methods that implements user
    private String sellerName;
    private String sellerUserName;
    private String sellerEmail;
    private String sellerPassword;
    private String sellerOfficeAddress;

    @Override
    public void setName(String sellerName) {
        this.sellerName = sellerName;
    }

    @Override
    public String getName() {
        return sellerName;
    }

    @Override
    public void setUserName(String sellerUserName) {
        this.sellerUserName = sellerUserName;
    }

    @Override
    public String getUserName() {
        return sellerUserName;
    }

    @Override
    public void setEmail(String sellerEmail) {
        this.sellerEmail = sellerEmail;
    }

    @Override
    public String getEmail() {
        return sellerEmail;
    }

    @Override
    public void setPassword(String sellerPassword) {
        this.sellerPassword = sellerPassword;
    }

    @Override
    public String getPassword() {
        return sellerPassword;
    }

    @Override
    public void setAddress(String sellerOfficeAddress) {
        this.sellerOfficeAddress = sellerOfficeAddress;
    }

    @Override
    public String getAddress() {
        return sellerOfficeAddress;
    }

    @Override
    public String toString() {
        return this.getName() + "|" + this.getUserName() +"|" + this.getPassword() +
               "|" + this.getEmail() + "|" + this.getAddress() + "|" + "null" + "|";
    }
    
}
