package org.coursesjava.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Error {
    NO_EXIST("This menu item does not exist!"),
    NOT_CREDITED("Money was not credited!"),
    NOT_CREATED_ACCOUNT("Error created account!"),
    NOT_CREATED_USER("The user was not created!"),
    USER_NOT_FOUND("User is not found!"),
    GAME_NOT_FOUND("This game is not in our catalog"),
    NO_MONEY("You do not have enough funds to complete this transaction!"),
    NOT_BUY_GAME("Error when purchasing a game!");

    private final String message;
}
