package org.coursesjava.services;

import org.coursesjava.ConnectionSingleton;
import org.coursesjava.enums.Error;
import org.coursesjava.enums.Menu;
import org.coursesjava.enums.Message;
import org.coursesjava.model.Game;
import org.coursesjava.repository.AccountRepositoryImpl;
import org.coursesjava.repository.GameRepositoryImpl;

import java.sql.SQLException;
import java.util.Optional;
import java.util.Scanner;

public class GameStoreMenuService {
    private final Scanner scanner;
    private GameService game;
    private AccountService account;

    public GameStoreMenuService(Scanner scanner) {
        this.scanner = scanner;
        try {
            this.game = new GameService(new GameRepositoryImpl(ConnectionSingleton.getConnection()));
            this.account = new AccountService(new AccountRepositoryImpl(ConnectionSingleton.getConnection()));
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public void list() {
        System.out.println(Message.ALL_GAME);
        game.findAll().forEach(g -> System.out.println("Name: " + g.getName() + " " + "Release date: " + g.getRelease_date() + " " + "Rating: " + g.getRating() + " " + "Cost: " + g.getCost() + " " + "Description: " + g.getDescription()));
    }

    public void buy() {
        scanner.nextLine();
        System.out.print(Menu.BUY_THE_GAME);
        String name = scanner.nextLine();

        // Looking for a game by name
        Optional<Game> game = this.game.findByName(name);
        // If it is not there, we inform the user about it
        if (game.isEmpty()) {
            System.out.println(Error.GAME_NOT_FOUND);
            return;
        }
        // We get the cost of the game
        int cost = game.get().getCost();
        int userBalance = LocalStorageService.get()
                .getAccount()
                .getAmount();
        // Check if the user has enough money to buy the game
        if(cost > userBalance) {
            System.out.println(Error.NO_MONEY);
            return;
        }
        // We make a request to purchase the game
        if (this.game.buy(LocalStorageService.get().getId(), game.get().getId())) {
            // Update the user's balance in local storage
            LocalStorageService.get()
                    .getAccount()
                    .setAmount(userBalance - cost);
            // Now we update the balance in the database. We take the balance from local storage
            account.update(LocalStorageService.get().getAccount(), LocalStorageService.get().getAccount().getAmount());
            System.out.println(Message.GAME_BUY_SUCCESSFULLY);
        } else {
            System.out.println(Error.NOT_BUY_GAME);
        }
    }

    public void lib() {
        System.out.println("+==============+");
        // We check whether the user has purchased games
        if (!game.userLib(LocalStorageService.get().getId()).isEmpty()) {
            game.userLib(LocalStorageService.get().getId()).forEach(g -> System.out.println(g.getName()));
        } else {
            System.out.println(Message.LIBRARY_IS_EMPTY);
        }
        System.out.println("+==============+");
    }
}
