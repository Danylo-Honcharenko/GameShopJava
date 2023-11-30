package org.coursesjava.repository.dao;

import org.coursesjava.model.User;

import java.util.Optional;

public interface UserRepository {
    int create(User user);
    Optional<User> get(User user);
    int remove(final int ID);
}
