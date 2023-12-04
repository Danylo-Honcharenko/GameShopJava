package org.coursesjava.repository.dao;

import org.coursesjava.model.Game;
import org.coursesjava.model.User;

import java.util.*;

public interface GameRepository {
    Optional<Game> getByName(String name);
    List<Game> getAll();
    Optional<Game> getById(int id);
    Optional<Game> addGameToUser(int userId, int gameId);
    List<Game> getGamesByUser(int userId);
}
