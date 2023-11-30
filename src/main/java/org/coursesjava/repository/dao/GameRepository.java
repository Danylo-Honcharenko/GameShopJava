package org.coursesjava.repository.dao;

import org.coursesjava.model.Game;

import java.util.*;

public interface GameRepository {
    // just for test
    default int create(Game game) {
        return 0;
    }
    Optional<Game> getByName(String Game);
    List<Game> getAll();
    Optional<Game> getById(int ID);
    int buy(int userID, int gameID);
    List<Game> getUserGame(int userID);
}
