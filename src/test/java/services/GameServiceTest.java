package services;

import mock.GameRepositoryMock;
import org.coursesjava.model.Game;
import org.coursesjava.services.GameService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class GameServiceTest {
    private final GameService game = new GameService(new GameRepositoryMock());
    @Before
    public void init() {
        Game gameFirst = new Game();
        gameFirst.setId(1);
        gameFirst.setName("Doom");
        gameFirst.setCost(300);
        gameFirst.setRating(4);
        gameFirst.setDescription("Test, test");
        gameFirst.setRelease_date(LocalDate.of(2000, 8, 10));

        Game gameSecond = new Game();
        gameSecond.setId(2);
        gameSecond.setName("Gta 5");
        gameSecond.setCost(500);
        gameSecond.setRating(5);
        gameSecond.setDescription("Test, test");
        gameSecond.setRelease_date(LocalDate.of(2013, 6, 12));

        this.game.create(gameFirst);
        this.game.create(gameSecond);
    }

    @Test
    public void getByName() {
        Game expected = new Game();
        expected.setId(2);
        expected.setName("Gta 5");
        expected.setCost(500);
        expected.setRating(5);
        expected.setDescription("Test, test");
        expected.setRelease_date(LocalDate.of(2013, 6, 12));

        Assert.assertTrue(this.game.findByName("Gta 5").isPresent());
        Assert.assertEquals(expected, this.game.findByName("Gta 5").get());
    }

    @Test
    public void findAll() {
        List<Game> games = new ArrayList<>();

        Game gameFirst = new Game();
        gameFirst.setId(1);
        gameFirst.setName("Doom");
        gameFirst.setCost(300);
        gameFirst.setRating(4);
        gameFirst.setDescription("Test, test");
        gameFirst.setRelease_date(LocalDate.of(2000, 8, 10));

        Game gameSecond = new Game();
        gameSecond.setId(2);
        gameSecond.setName("Gta 5");
        gameSecond.setCost(500);
        gameSecond.setRating(5);
        gameSecond.setDescription("Test, test");
        gameSecond.setRelease_date(LocalDate.of(2013, 6, 12));

        games.add(gameFirst);
        games.add(gameSecond);

        Assert.assertEquals(games, this.game.findAll());
    }

    @Test
    public void findById() {
        Game expected = new Game();
        expected.setId(1);
        expected.setName("Doom");
        expected.setCost(300);
        expected.setRating(4);
        expected.setDescription("Test, test");
        expected.setRelease_date(LocalDate.of(2000, 8, 10));

        Assert.assertTrue(this.game.findById(1).isPresent());
        Assert.assertEquals(expected, this.game.findById(1).get());
    }
}
