package org.coursesjava.services;
import org.coursesjava.model.User;
import org.coursesjava.repository.dao.UserRepository;

import java.util.Optional;


public class UserService {
    private final UserRepository repository;
    public UserService(UserRepository user) {
        repository = user;
    }
    public Optional<User> create(User user) {
        return repository.create(user);
    }
    public Optional<User> find(User user) {
        return repository.get(user);
    }
}
