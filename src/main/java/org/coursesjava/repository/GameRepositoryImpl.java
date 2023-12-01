package org.coursesjava.repository;

import org.coursesjava.model.Game;
import org.coursesjava.repository.dao.GameRepository;

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
            INSERT INTO Game_Ref (user_id, game_id) VALUE (?, ?);
            """;

    private String getUserGame =
            """
            SELECT U.name, password, nickname, birthday, G.name, release_date, rating, cost, description 
            FROM Users AS U LEFT JOIN Game_Ref GR ON U.ID = GR.user_id JOIN Game G ON G.ID = GR.game_id WHERE U.ID = ?;
            """;

    @Override
    public Optional<Game> getByName(String GAME) {
        Game game = null;
        try (PreparedStatement query = connection.prepareStatement(getByName)) {
            query.setString(1, GAME);
            try (ResultSet data = query.executeQuery()) {
                if (data.next()) {
                    Game gameData = new Game();
                    gameData.setId(data.getInt("ID"));
                    gameData.setName(data.getString("name"));
                    gameData.setRelease_date(data.getDate("release_date").toLocalDate());
                    gameData.setRating(data.getInt("rating"));
                    gameData.setCost(data.getInt("cost"));
                    gameData.setDescription(data.getString("description"));
                    game = gameData;
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
                    gameData.setId(data.getInt("ID"));
                    gameData.setName(data.getString("name"));
                    gameData.setRelease_date(data.getDate("release_date").toLocalDate());
                    gameData.setRating(data.getInt("rating"));
                    gameData.setCost(data.getInt("cost"));
                    gameData.setDescription(data.getString("description"));

                    games.add(gameData);
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
    public Optional<Game> getById(int ID) {
        Game game = null;
        try (PreparedStatement query = connection.prepareStatement(getById)) {
            query.setInt(1, ID);
            try (ResultSet data = query.executeQuery()) {
                if (data.next()) {
                    Game gameData = new Game();
                    gameData.setId(data.getInt("ID"));
                    gameData.setName(data.getString("name"));
                    gameData.setRelease_date(data.getDate("release_date").toLocalDate());
                    gameData.setRating(data.getInt("rating"));
                    gameData.setCost(data.getInt("cost"));
                    gameData.setDescription(data.getString("description"));
                    game = gameData;
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
    public int buy(int userID, int gameID) {
        int rowsChanged = 0;
        try (PreparedStatement query = connection.prepareStatement(gameBelongs)) {
            query.setInt(1, userID);
            query.setInt(2, gameID);
            rowsChanged = query.executeUpdate();
        } catch (SQLException ex) {
            System.err.println("DB game error: " + ex.getMessage());
            try {
                connection.close();
            } catch (SQLException exc) {
                System.err.println("Close error: " + ex.getMessage());
            }
        }
        return rowsChanged;
    }

    @Override
    public List<Game> getUserGame(int userID) {
        List<Game> userGames = new ArrayList<>();
        try (PreparedStatement query = connection.prepareStatement(getUserGame)) {
            query.setInt(1, userID);
            try (ResultSet data = query.executeQuery()) {
                while (data.next()) {
                    Game game = new Game();
                    game.setName(data.getString("G.name"));
                    game.setRelease_date(data.getDate("release_date").toLocalDate());
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
