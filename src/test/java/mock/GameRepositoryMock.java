package mock;

import org.coursesjava.model.Game;
import org.coursesjava.repository.dao.GameRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class GameRepositoryMock implements GameRepository {
    private final List<Game> games = new ArrayList<>();
    public int create(Game game) {
        games.add(game);
        return 1;
    }
    @Override
    public Optional<Game> getByName(String findGame) {
        Game game = games.stream()
                .filter(g -> g.getName().equals(findGame))
                .findFirst()
                .orElse(null);
        return Optional.ofNullable(game);
    }

    @Override
    public List<Game> getAll() {
        return games;
    }

    @Override
    public Optional<Game> getById(int ID) {
        Game game = games.stream()
                .filter(g -> g.getId() == ID)
                .findFirst()
                .orElse(null);
        return Optional.ofNullable(game);
    }

    @Override
    public int buy(int userID, int gameID) {
        return 1;
    }

    @Override
    public List<Game> getUserGame(int userID) {
        return games;
    }
}
