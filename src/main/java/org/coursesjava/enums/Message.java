package org.coursesjava.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Message {
    DATE_FORMAT("Specify the date, for example 2000-02-02"),
    USER_CREATE_SUCCESSFULLY("User create successfully!"),
    ACCOUNT_CREATED_SUCCESSFULLY("Account created successfully!"),
    ALL_GAME("All games: "),
    GAME_BUY_SUCCESSFULLY("Game buy successfully!"),
    LIBRARY_IS_EMPTY("Your library is empty!");
    private final String message;
}
