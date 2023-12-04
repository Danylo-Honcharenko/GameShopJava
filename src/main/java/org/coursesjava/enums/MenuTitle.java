package org.coursesjava.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum MenuTitle {
    MAIN("Welcome to Game Store\uD83D\uDE80"),
    CREATE_USER("Create user"),
    LOGIN("Login");

    private final String title;

    @Override
    public String toString() {
        return title;
    }
}
