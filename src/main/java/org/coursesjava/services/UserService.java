package org.coursesjava.services;
import org.coursesjava.model.User;
import org.coursesjava.repository.dao.UserRepository;


public class UserService {

    private final UserRepository user;

    public UserService(UserRepository user) {
        this.user = user;
    }


    public boolean create(User user) {
        // if true user created if false no
        return this.user.create(user) == 1;
    }

    public User find(User user) {
        return this.user.get(user);
    }
}
