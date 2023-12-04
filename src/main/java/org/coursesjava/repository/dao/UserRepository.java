package org.coursesjava.repository.dao;

import org.coursesjava.model.User;

import java.util.Optional;

public interface UserRepository {
    Optional<User> create(User candidate);
    Optional<User> getByNameAndPassword(String name, String password);
}
