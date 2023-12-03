package org.coursesjava.repository;

import org.coursesjava.model.Account;
import org.coursesjava.model.User;
import org.coursesjava.repository.dao.UserRepository;

import java.sql.*;
import java.util.Optional;

public class UserRepositoryImpl implements UserRepository {

    private final Connection connection;

    private String add =
            """
            INSERT INTO Users (name, password, nickname, birthday) VALUES (?, ?, ?, STR_TO_DATE(?, '%Y-%m-%d'));
            """;

    private String find =
            """
            SELECT * FROM Users AS U LEFT JOIN Accounts A ON U.ID = A.user_id WHERE U.name = ? AND U.password = ?;
            """;

    public UserRepositoryImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Optional<User> create(User candidate) {
        User user = null;
        try (PreparedStatement query = connection.prepareStatement(add, Statement.RETURN_GENERATED_KEYS)) {
            query.setString(1, candidate.getName());
            query.setString(2, candidate.getPassword());
            query.setString(3, candidate.getNickname());
            query.setDate(4, Date.valueOf(candidate.getBirthday()));

            query.executeUpdate();

            ResultSet generationKey = query.getGeneratedKeys();
            generationKey.next();
            candidate.setId(generationKey.getInt(1));
            user = candidate;
        } catch (SQLException ex) {
            System.err.println("DB save error: " + ex.getMessage());
            try {
                connection.close();
            } catch (SQLException exc) {
                System.err.println("Close error: " + ex.getMessage());
            }
        }
        return Optional.ofNullable(user);
    }

    @Override
    public Optional<User> get(User user) {
        User result = null;
        try (PreparedStatement query = connection.prepareStatement(find)) {
            query.setString(1, user.getName());
            query.setString(2, user.getPassword());
            try (ResultSet data = query.executeQuery()) {
                while (data.next()) {
                    Account account = new Account();
                    account.setId(data.getInt("A.ID"));
                    account.setAmount(data.getInt("amount"));
                    account.setCardType(data.getString("type"));
                    account.setUserId(data.getInt("user_id"));

                    User userData = new User();
                    userData.setId(data.getInt("U.ID"));
                    userData.setName(data.getString("name"));
                    userData.setPassword(data.getString("password"));
                    userData.setNickname(data.getString("nickname"));
                    userData.setBirthday(data.getString("birthday"));
                    userData.setAccount(account);
                    result = userData;
                }
            }
        } catch (SQLException ex) {
            System.err.println("DB find user error: " + ex.getMessage());
            try {
                connection.close();
            } catch (SQLException exc) {
                System.err.println("Close error: " + ex.getMessage());
            }
        }
        return Optional.ofNullable(result);
    }
}
