package mock;

import org.coursesjava.model.User;
import org.coursesjava.repository.dao.UserRepository;

import java.util.ArrayList;
import java.util.List;

public class UserRepositoryMock implements UserRepository {
    private final List<User> users = new ArrayList<>();

    @Override
    public int create(User user) {
        users.add(user);
        return 1;
    }

    @Override
    public User get(User user) {
        return users.stream()
                .filter(u -> u.getName().equals(user.getName()) && u.getPassword().equals(user.getPassword()))
                .findFirst()
                .orElse(null);
    }

    @Override
    public int remove(int ID) {
        return 1;
    }
}
