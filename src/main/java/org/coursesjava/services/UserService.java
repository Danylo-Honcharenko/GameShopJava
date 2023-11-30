package org.coursesjava.services;
import org.coursesjava.model.User;
import org.coursesjava.repository.dao.UserRepository;

import java.util.Optional;


public class UserService {

    private final UserRepository user;

    public UserService(UserRepository user) {
        this.user = user;
    }

    public boolean create(User user) {
        // if true user created if false no
        return this.user.create(user) == 1;
    }

    public Optional<User> find(User user) {
        return this.user.get(user);
    }
}
