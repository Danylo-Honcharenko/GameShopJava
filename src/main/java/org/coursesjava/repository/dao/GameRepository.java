package org.coursesjava.repository.dao;

import org.coursesjava.model.Game;
import org.coursesjava.model.User;

import java.util.*;

public interface GameRepository {
    Optional<Game> getByName(String desiredGame);
    List<Game> getAll();
    Optional<Game> getById(int id);
    Optional<Game> addGameToUser(User user, Game game);
    List<Game> getUserGame(int userId);
}
