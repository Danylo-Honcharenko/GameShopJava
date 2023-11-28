package org.coursesjava.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MenuTitle {
    MAIN("Welcome to Game Store\uD83D\uDE80"),
    CREATE_USER("Create user"),
    LOGIN("Login");

    private final String title;
}
