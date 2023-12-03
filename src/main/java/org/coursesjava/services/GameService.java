package org.coursesjava.services;

import org.coursesjava.model.Game;
import org.coursesjava.model.User;
import org.coursesjava.repository.dao.GameRepository;

import java.util.List;
import java.util.Optional;

public class GameService {
    private final GameRepository repository;
    public GameService(GameRepository game) {
        repository = game;
    }
    public Optional<Game> findByName(String name) {
        return repository.getByName(name);
    }
    public List<Game> findAll() {
        return repository.getAll();
    }
    public Optional<Game> findById(int id) {
        return repository.getById(id);
    }
    public Optional<Game> buy(User user, Game game) {
        return repository.addGameToUser(user, game);
    }
    public List<Game> userLib(int userId) {
        return repository.getUserGame(userId);
    }
}
