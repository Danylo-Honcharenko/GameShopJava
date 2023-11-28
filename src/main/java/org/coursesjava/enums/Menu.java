package org.coursesjava.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Menu {
    CREATE_NEW_USER("1. Create new user"),
    LOGIN("2. Login"),
    EXIT("3. Exit"),
    ACTION("Action: "),

    // User menu (create)
    ADD_USER_NAME("Name: "),
    ADD_USER_NICKNAME("Nickname: "),
    ADD_BIRTHDAY("Birthday: "),
    ADD_PASSWORD("Password: "),


    // User menu (login)
    USER_NAME("Name: "),
    PASSWORD("Password: "),

    // Store
    GAME_LIST("1. Show all games"),
    BUY_GAME("2. Buy the game"),
    TOP_UP_YOUR_ACCOUNT("3. Top up your account"),
    STATE_OF_AN_ACCOUNT("4. State of an account"),
    USER_LIB("5. My game library"),
    LOGOUT("6. Logout"),
    GAME_SELECTION("Game ID: "),

    // Create account
    INDICATE_PAYMENT_SYSTEM("Payment system (Visa/Mastercard): "),

    TOP_UP_ACCOUNT_Q("How much do you want to top up your account?: "),

    BUY_THE_GAME("Enter the name of the game you want to buy: ");
    private final String item;
}
