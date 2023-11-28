package repository;

import org.coursesjava.ConnectionSingleton;
import org.coursesjava.model.Game;
import org.coursesjava.repository.GameRepositoryImpl;
import org.coursesjava.repository.dao.GameRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class GameRepositoryImplTest {
    private GameRepository game;

    @Before
    public void init() throws SQLException {
        game = new GameRepositoryImpl(ConnectionSingleton.getConnection());
    }

    @Test
    public void getByName() {
        Game expected = new Game();
        expected.setId(2);
        expected.setName("Red Dead Redemption 2");
        expected.setRelease_date(LocalDate.of(2018, 10, 26));
        expected.setRating((int) 9.8);
        expected.setCost(80000000);
        expected.setDescription("Action-adventure game developed and published by Rockstar Games.");

        Assert.assertEquals(expected, this.game.getByName("Red Dead Redemption 2"));
    }

    @Test
    public void getAll() {
        List<Game> expected = new ArrayList<>();

        Game firstGame = new Game();
        firstGame.setId(1);
        firstGame.setName("The Witcher 3: Wild Hunt");
        firstGame.setRelease_date(LocalDate.of(2015, 5, 19));
        firstGame.setRating((int) 9.7);
        firstGame.setCost(60000000);
        firstGame.setDescription("Action role-playing game developed and published by CD Projekt.");

        Game secondGame = new Game();
        secondGame.setId(2);
        secondGame.setName("Red Dead Redemption 2");
        secondGame.setRelease_date(LocalDate.of(2018, 10, 26));
        secondGame.setRating((int) 9.8);
        secondGame.setCost(80000000);
        secondGame.setDescription("Action-adventure game developed and published by Rockstar Games.");

        expected.add(firstGame);
        expected.add(secondGame);

        Assert.assertEquals(expected, this.game.getAll());
    }

    @Test
    public void get() {
        Game expected = new Game();
        expected.setId(1);
        expected.setName("The Witcher 3: Wild Hunt");
        expected.setRelease_date(LocalDate.of(2015, 5, 19));
        expected.setRating((int) 9.7);
        expected.setCost(60000000);
        expected.setDescription("Action role-playing game developed and published by CD Projekt.");

        Assert.assertEquals(expected, this.game.getById(1));
    }

    @Test
    public void buy() {
        Assert.assertEquals(1, this.game.buy(2, 1));
    }
}
