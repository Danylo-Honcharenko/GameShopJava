package org.coursesjava.services;

import org.coursesjava.model.Game;
import org.coursesjava.repository.dao.GameRepository;

import java.util.List;
import java.util.Optional;

public class GameService {
    private final GameRepository game;
    public GameService(GameRepository game) {
        this.game = game;
    }

    public int create(Game game) {
        this.game.create(game);
        return 1;
    }

    public Optional<Game> findByName(String name) {
        return game.getByName(name);
    }

    public List<Game> findAll() {
        return game.getAll();
    }

    public Optional<Game> findById(int ID) {
        return game.getById(ID);
    }

    public boolean buy(int userID, int gameID) {
        return game.buy(userID, gameID) == 1;
    }
    public List<Game> userLib(int userID) {
        return game.getUserGame(userID);
    }
}
