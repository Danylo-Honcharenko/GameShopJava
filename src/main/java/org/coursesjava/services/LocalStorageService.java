package org.coursesjava.services;

import org.coursesjava.model.User;


public class LocalStorageService {
    private static final User userStorage = new User();
    public static void set(User user) {
        userStorage.setId(user.getId());
        userStorage.setName(user.getName());
        userStorage.setNickname(user.getNickname());
        userStorage.setBirthday(user.getBirthday());
        userStorage.setPassword(user.getPassword());
        userStorage.setAccount(user.getAccount());
    }

    public static User get() {
        return userStorage;
    }
}
