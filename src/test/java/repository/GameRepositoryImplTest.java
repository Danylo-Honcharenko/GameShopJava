package repository;

import config.MysqlConnector;
import org.coursesjava.model.Game;
import org.coursesjava.model.User;
import org.coursesjava.repository.GameRepositoryImpl;
import org.coursesjava.repository.dao.GameRepository;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class GameRepositoryImplTest {
    private GameRepository game;
    private Connection connection;

    @Before
    public void init() throws SQLException {
        connection = MysqlConnector.getConnection();
        game = new GameRepositoryImpl(connection);
    }

    @After
    public void close() throws SQLException {
        connection.close();
    }

    @Test
    public void getByName() {
        Assert.assertTrue(game.getByName("Red Dead Redemption 2").isPresent());
        Assert.assertTrue(game.getByName("Mario").isEmpty());
    }

    @Test
    public void getAll() {
        List<Game> expected = new ArrayList<>();

        Game firstGame = new Game();
        firstGame.setId(1);
        firstGame.setName("The Witcher 3: Wild Hunt");
        firstGame.setReleaseDate(LocalDate.of(2015, 5, 19));
        firstGame.setRating((int) 9.7);
        firstGame.setCost(300);
        firstGame.setDescription("Action role-playing game developed and published by CD Projekt.");

        Game secondGame = new Game();
        secondGame.setId(2);
        secondGame.setName("");
        secondGame.setReleaseDate(LocalDate.of(2018, 10, 26));
        secondGame.setRating((int) 9.8);
        secondGame.setCost(500);
        secondGame.setDescription("Action-adventure game developed and published by Rockstar Games.");

        expected.add(firstGame);
        expected.add(secondGame);

        Assert.assertEquals(expected, game.getAll());
    }

    @Test
    public void get() {
        Assert.assertTrue(game.getById(1).isPresent());
        Assert.assertTrue(game.getById(100).isEmpty());
    }

    @Test
    public void buy() {
        Assert.assertTrue(game.addGameToUser(1, 4).isPresent());
    }
}
