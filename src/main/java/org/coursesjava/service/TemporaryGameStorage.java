package org.coursesjava.service;

import org.coursesjava.model.Game;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TemporaryGameStorage {
    public Game data(Game game, ResultSet data) throws SQLException {
        game.setId(data.getInt("ID"));
        game.setName(data.getString("name"));
        game.setReleaseDate(data.getDate("release_date").toLocalDate());
        game.setRating(data.getInt("rating"));
        game.setCost(data.getInt("cost"));
        game.setDescription(data.getString("description"));
        return game;
    }
}
