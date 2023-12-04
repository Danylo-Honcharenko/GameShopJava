package org.coursesjava.service;

import org.coursesjava.config.ConnectionConfig;
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
    private GameService gameService;
    private AccountService accountService;

    public GameStoreMenuService(Scanner scanner) {
        this.scanner = scanner;
        try {
            this.gameService = new GameService(new GameRepositoryImpl(ConnectionConfig.getConnection()));
            this.accountService = new AccountService(new AccountRepositoryImpl(ConnectionConfig.getConnection()));
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public void list() {
        System.out.println(Message.ALL_GAME);
        gameService.findAll().forEach(g -> System.out.println("Name: " + g.getName() + " " + "Release date: " + g.getReleaseDate() + " " + "Rating: " + g.getRating() + " " + "Cost: " + g.getCost() + " " + "Description: " + g.getDescription()));
    }

    public void buy() {
        scanner.nextLine();
        System.out.print(Menu.BUY_THE_GAME);
        String name = scanner.nextLine();

        // Looking for a game by name
        Optional<Game> game = gameService.findByName(name);
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
        if (this.gameService.buy(LocalStorageService.get().getId(), game.get().getId()).isPresent()) {
            LocalStorageService.get()
                    .getAccount()
                    .setAmount(userBalance - cost);

            accountService.update(LocalStorageService.get().getAccount(), LocalStorageService.get().getAccount().getAmount());
            System.out.println(Message.GAME_BUY_SUCCESSFULLY);
        } else {
            System.out.println(Error.NOT_BUY_GAME);
        }
    }

    public void lib() {
        System.out.println("+==============+");
        // We check whether the user has purchased games
        if (!gameService.userLib(LocalStorageService.get().getId()).isEmpty()) {
            gameService.userLib(LocalStorageService.get().getId()).forEach(g -> System.out.println(g.getName()));
        } else {
            System.out.println(Message.LIBRARY_IS_EMPTY);
        }
        System.out.println("+==============+");
    }
}
