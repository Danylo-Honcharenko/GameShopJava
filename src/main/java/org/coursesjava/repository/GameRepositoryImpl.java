package org.coursesjava.repository;

import org.coursesjava.model.Game;
import org.coursesjava.model.User;
import org.coursesjava.repository.dao.GameRepository;
import org.coursesjava.service.TemporaryGameStorage;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class GameRepositoryImpl implements GameRepository {
    private final Connection connection;

    public GameRepositoryImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Optional<Game> getByName(String name) {
        Game game = null;
        try (PreparedStatement query = connection.prepareStatement("SELECT * FROM Game WHERE name = ?;")) {
            query.setString(1, name);
            try (ResultSet data = query.executeQuery()) {
                if (data.next()) {
                    Game gameData = new Game();
                    game = new TemporaryGameStorage().data(gameData, data);
                }
            }
        } catch (SQLException ex) {
            System.err.println("DB game error: " + ex.getMessage());
            try {
                connection.close();
            } catch (SQLException exc) {
                System.err.println("Close error: " + ex.getMessage());
            }
        }
        return Optional.ofNullable(game);
    }

    @Override
    public List<Game> getAll() {
        List<Game> games = new ArrayList<>();
        try (Statement query = connection.createStatement()) {
            try (ResultSet data = query.executeQuery("SELECT * FROM Game;")) {
                while (data.next()) {
                    Game gameData = new Game();
                    games.add(new TemporaryGameStorage().data(gameData, data));
                }
            }
        } catch (SQLException ex) {
            System.err.println("DB game error: " + ex.getMessage());
            try {
                connection.close();
            } catch (SQLException exc) {
                System.err.println("Close error: " + ex.getMessage());
            }
        }
        return games;
    }

    @Override
    public Optional<Game> getById(int id) {
        Game game = null;
        try (PreparedStatement query = connection.prepareStatement("SELECT * FROM Game WHERE ID = ?;")) {
            query.setInt(1, id);
            try (ResultSet data = query.executeQuery()) {
                if (data.next()) {
                    Game gameData = new Game();
                    game = new TemporaryGameStorage().data(gameData, data);
                }
            }
        } catch (SQLException ex) {
            System.err.println("DB game error: " + ex.getMessage());
            try {
                connection.close();
            } catch (SQLException exc) {
                System.err.println("Close error: " + ex.getMessage());
            }
        }
        return Optional.ofNullable(game);
    }

    @Override
    public Optional<Game> addGameToUser(int userId, int gameId) {
        Game gameResult = null;
        try (PreparedStatement query = connection.prepareStatement("INSERT INTO User_game (user_id, game_id) VALUES (?, ?);")) {
            if (getById(gameId).isPresent()) {
                query.setInt(1, userId);
                query.setInt(2, gameId);
                query.executeUpdate();

                gameResult = getById(gameId).get();
            }
        } catch (SQLException ex) {
            System.err.println("DB game error: " + ex.getMessage());
            try {
                connection.close();
            } catch (SQLException exc) {
                System.err.println("Close error: " + ex.getMessage());
            }
        }
        return Optional.ofNullable(gameResult);
    }

    @Override
    public List<Game> getGamesByUser(int userId) {
        List<Game> userGames = new ArrayList<>();
        try (PreparedStatement query = connection.prepareStatement("SELECT U.name, password, nickname, birthday, G.name, release_date, rating, cost, description FROM Users AS U LEFT JOIN User_game GR ON U.ID = GR.user_id JOIN Game G ON G.ID = GR.game_id WHERE U.ID = ?;")) {
            query.setInt(1, userId);
            try (ResultSet data = query.executeQuery()) {
                while (data.next()) {
                    Game game = new Game();
                    game.setName(data.getString("G.name"));
                    game.setReleaseDate(data.getDate("release_date").toLocalDate());
                    game.setRating(data.getInt("rating"));
                    game.setDescription(data.getString("description"));
                    userGames.add(game);
                }
            }
        } catch (SQLException ex) {
            System.err.println("DB game error: " + ex.getMessage());
            try {
                connection.close();
            } catch (SQLException exc) {
                System.err.println("Close error: " + ex.getMessage());
            }
        }
        return userGames;
    }
}
