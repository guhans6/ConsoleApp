package models.user;


public class Admin {

    private String adminName;
    private String adminUserName;
    private String adminEmail;
    private String adminPassword;

    public void setName(String adminName) {
        this.adminName = adminName;
    }
    
    public String getName() {
        return adminName;
    }
 
    public void setUserName(String adminUserName) {
        this.adminUserName = adminUserName;
    }
  
    public String getUserName() {
        return adminUserName;
    }
   
    public void setEmail(String adminEmail) {
        this.adminEmail = adminEmail;
    }
   
    public String getEmail() {
        return adminEmail;
    }
    
    public void setPassword(String adminPassword) {
        this.adminPassword = adminPassword;
    }
    
    public String getPassword() {
        return adminPassword;
    }

}
