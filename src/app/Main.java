package app;

import menu.UserMenu;

public class Main {
    public static void main(String[] args) {
        
        UserMenu userMenu = UserMenu.getInstance();
        userMenu.userMenu();
        
    }
}
