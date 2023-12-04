package org.coursesjava.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum Message {
    USER_CREATE_SUCCESSFULLY("User create successfully!"),
    ACCOUNT_CREATED_SUCCESSFULLY("Account created successfully!"),
    ALL_GAME("All games: "),
    GAME_BUY_SUCCESSFULLY("Game buy successfully!"),
    LIBRARY_IS_EMPTY("Your library is empty!");
    private final String message;

    @Override
    public String toString() {
        return message;
    }
}
