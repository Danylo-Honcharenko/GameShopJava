package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MysqlConnector {
    public static Connection getConnection() throws SQLException {
        // connection
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/Test", "root", "");
    }
}
