package org.coursesjava.service;
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
    public Optional<User> findByNameAndPassword(String name, String password) {
        return repository.getByNameAndPassword(name, password);
    }
}
