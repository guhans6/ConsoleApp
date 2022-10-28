package app;

import controller.UserController;

public class Main {
    public static void main(String[] args) {
        
        UserController userController = UserController.getInstance();
        userController.userMenu();
    }
}
