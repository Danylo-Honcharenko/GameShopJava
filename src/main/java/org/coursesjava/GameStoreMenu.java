package org.coursesjava;

import org.coursesjava.enums.Error;
import org.coursesjava.enums.Menu;
import org.coursesjava.service.AccountMenuService;
import org.coursesjava.service.GameStoreMenuService;
import org.coursesjava.service.LocalStorageService;

import java.util.Scanner;

public class GameStoreMenu {
    private final Scanner scanner;

    public GameStoreMenu(Scanner scanner) {
        this.scanner = scanner;
    }

    public void show() {
        boolean logout = true;

        GameStoreMenuService game = new GameStoreMenuService(scanner);
        AccountMenuService account = new AccountMenuService(scanner);

        while (logout) {
            System.out.println("Welcome " + LocalStorageService.get().getName() + "!");
            System.out.println("====");
            System.out.println(Menu.GAME_LIST);
            System.out.println(Menu.BUY_GAME);
            System.out.println(Menu.TOP_UP_YOUR_ACCOUNT);
            System.out.println(Menu.STATE_OF_AN_ACCOUNT);
            System.out.println(Menu.USER_LIB);
            System.out.println(Menu.LOGOUT);
            System.out.print(Menu.ACTION);

            switch (scanner.next()) {
                case "1" -> game.list();
                case "2" -> game.buy();
                case "3" -> account.topUpAccount();
                case "4" -> account.stateOfAnAccount();
                case "5" -> game.lib();
                case "6" -> logout = false;
                default -> System.out.println(Error.NO_EXIST);
            }
        }
    }
}
