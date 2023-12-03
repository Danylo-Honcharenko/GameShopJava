package org.coursesjava.repository;

import org.coursesjava.model.Game;
import org.coursesjava.model.User;
import org.coursesjava.repository.dao.GameRepository;
import org.coursesjava.services.TemporaryGameStorage;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class GameRepositoryImpl implements GameRepository {
    private final Connection connection;

    public GameRepositoryImpl(Connection connection) {
        this.connection = connection;
    }

    private String getByName =
            """
            SELECT * FROM Game WHERE name = ?;
            """;

    private String getById =
            """
            SELECT * FROM Game WHERE ID = ?;
            """;

    private String getAll =
            """
            SELECT * FROM Game;
            """;

    private String gameBelongs =
            """
            INSERT INTO User_game (user_id, game_id) VALUES (?, ?);
            """;

    private String getUserGame =
            """
            SELECT U.name, password, nickname, birthday, G.name, release_date, rating, cost, description 
            FROM Users AS U LEFT JOIN User_game GR ON U.ID = GR.user_id JOIN Game G ON G.ID = GR.game_id WHERE U.ID = ?;
            """;

    @Override
    public Optional<Game> getByName(String desiredGame) {
        Game game = null;
        try (PreparedStatement query = connection.prepareStatement(getByName)) {
            query.setString(1, desiredGame);
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
            try (ResultSet data = query.executeQuery(getAll)) {
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
        try (PreparedStatement query = connection.prepareStatement(getById)) {
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
    public Optional<Game> addGameToUser(User user, Game game) {
        Game gameResult = null;
        try (PreparedStatement query = connection.prepareStatement(gameBelongs)) {
            if (getById(game.getId()).isPresent()) {
                query.setInt(1, user.getId());
                query.setInt(2, game.getId());
                query.executeUpdate();

                gameResult = getById(game.getId()).get();
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
    public List<Game> getUserGame(int userId) {
        List<Game> userGames = new ArrayList<>();
        try (PreparedStatement query = connection.prepareStatement(getUserGame)) {
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
