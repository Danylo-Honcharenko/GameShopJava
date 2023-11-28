package org.coursesjava;

import org.coursesjava.enums.Error;
import org.coursesjava.enums.Menu;
import org.coursesjava.enums.MenuTitle;
import org.coursesjava.services.MainMenuService;

import java.sql.SQLException;
import java.util.Scanner;

public class Application {
    public static void run() {
        Scanner scanner = new Scanner(System.in);
        MainMenuService menu = new MainMenuService(scanner);
        boolean exit = true;

        while (exit) {
            System.out.println(MenuTitle.MAIN);
            System.out.println(Menu.CREATE_NEW_USER);
            System.out.println(Menu.LOGIN);
            System.out.println(Menu.EXIT);
            System.out.print(Menu.ACTION);

            switch (scanner.next()) {
                case "1" -> menu.createUser();
                case "2" -> menu.login();
                case "3" -> {
                    exit = false;
                    try {
                        ConnectionSingleton.getConnection().close();
                    } catch (SQLException ex) {
                        System.err.println("Close connection error: " + ex.getMessage());
                    }
                }
                default -> System.out.println(Error.NO_EXIST);
            }
        }

        scanner.close();
    }
}
