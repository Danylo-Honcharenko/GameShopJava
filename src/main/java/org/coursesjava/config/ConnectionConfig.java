package org.coursesjava.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionConfig {
    private static Connection connection;
    private static final String USER = "root";
    private static final String PASSWORD = "123456789";

    private ConnectionConfig() {}

    public static Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/gameStore", USER, PASSWORD);
        }
        return connection;
    }
}
