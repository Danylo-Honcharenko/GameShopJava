package org.coursesjava.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Tips {
    DATE_FORMAT("Specify the date, for example 2000-02-02"),
    ACCOUNT_REGISTRATION("To continue, create an account.\nDon't forget to top up your account before purchasing the game!");
    private final String prompt;
}
