package org.coursesjava.repository.dao;

import org.coursesjava.model.User;

public interface UserRepository {
    int create(User user);
    User get(User user);
    int remove(final int ID);
}
