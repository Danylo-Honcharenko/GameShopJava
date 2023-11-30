package mock;

import org.coursesjava.model.User;
import org.coursesjava.repository.dao.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserRepositoryMock implements UserRepository {
    private final List<User> users = new ArrayList<>();

    @Override
    public int create(User user) {
        users.add(user);
        return 1;
    }

    @Override
    public Optional<User> get(User user) {
        User findUser = users.stream()
                .filter(u -> u.getName().equals(user.getName()) && u.getPassword().equals(user.getPassword()))
                .findFirst()
                .orElse(null);
        return Optional.ofNullable(findUser);
    }

    @Override
    public int remove(int ID) {
        return 1;
    }
}
