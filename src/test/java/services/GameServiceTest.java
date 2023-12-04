package services;

import org.coursesjava.model.Game;
import org.coursesjava.service.GameService;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.*;

public class GameServiceTest {
    private final GameService game = Mockito.mock(GameService.class);

    @Test
    public void findByName() {
        Game expected = new Game();
        expected.setId(2);
        expected.setName("Gta 5");
        expected.setCost(500);
        expected.setRating(5);
        expected.setDescription("Test, test");
        expected.setReleaseDate(LocalDate.of(2013, 6, 12));

        String gameName = "Gta 5";

        Mockito.when(game.findByName(anyString()))
                .thenReturn(Optional.of(expected));
        Assert.assertTrue(game.findByName(gameName).isPresent());
        Assert.assertEquals(expected, game.findByName(gameName).get());

        Mockito.when(game.findByName(anyString()))
                .thenReturn(null);
        Assert.assertNull(game.findByName(gameName));
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
        gameFirst.setReleaseDate(LocalDate.of(2000, 8, 10));

        Game gameSecond = new Game();
        gameSecond.setId(2);
        gameSecond.setName("Gta 5");
        gameSecond.setCost(500);
        gameSecond.setRating(5);
        gameSecond.setDescription("Test, test");
        gameSecond.setReleaseDate(LocalDate.of(2013, 6, 12));

        Mockito.when(game.findAll())
                .thenReturn(games);
        Assert.assertEquals(games, game.findAll());
    }

    @Test
    public void findById() {
        Game expected = new Game();
        expected.setId(1);
        expected.setName("Doom");
        expected.setCost(300);
        expected.setRating(4);
        expected.setDescription("Test, test");
        expected.setReleaseDate(LocalDate.of(2000, 8, 10));

        Mockito.when(game.findById(anyInt()))
                .thenReturn(Optional.of(expected));
        Assert.assertTrue(game.findById(1).isPresent());
        Assert.assertEquals(expected, game.findById(1).get());

        Mockito.when(game.findById(anyInt()))
                .thenReturn(null);
        Assert.assertNull(game.findById(1));
    }
}
