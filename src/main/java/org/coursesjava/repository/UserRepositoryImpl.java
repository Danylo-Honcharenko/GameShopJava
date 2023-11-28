package org.coursesjava.repository;

import org.coursesjava.model.Account;
import org.coursesjava.model.User;
import org.coursesjava.repository.dao.UserRepository;

import java.sql.*;

public class UserRepositoryImpl implements UserRepository {

    private final Connection connection;

    private final String add =
            """
            INSERT INTO Users (name, password, nickname, birthday) VALUE (?, ?, ?,STR_TO_DATE(?, '%Y-%m-%d'));
            """;

    private final String find =
            """
            SELECT * FROM Users AS U LEFT JOIN Accounts A ON U.ID = A.user_id WHERE U.name = ? AND U.password = ?;
            """;

    private final String delete =
            """
            DELETE FROM Users WHERE ID = ?;
            """;

    public UserRepositoryImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public int create(User user) {
        int rowsChanged = 0;
        try (PreparedStatement query = connection.prepareStatement(add)) {
            query.setString(1, user.getName());
            query.setString(2, user.getPassword());
            query.setString(3, user.getNickname());
            query.setDate(4, Date.valueOf(user.getBirthday()));
            rowsChanged = query.executeUpdate();

        } catch (SQLException ex) {
            System.err.println("DB save error: " + ex.getMessage());
            try {
                connection.close();
            } catch (SQLException exc) {
                System.err.println("Close error: " + ex.getMessage());
            }
        }
        return rowsChanged;
    }

    @Override
    public User get(User user) {
        User result = null;
        try (PreparedStatement query = connection.prepareStatement(find);
             ResultSet data = query.executeQuery()) {
            query.setString(1, user.getName());
            query.setString(2, user.getPassword());
            while (data.next()) {
                Account account = new Account();
                account.setId(data.getInt("A.ID"));
                account.setAmount(data.getInt("amount"));
                account.setType(data.getString("type"));
                account.setUser_id(data.getInt("user_id"));

                User userData = new User();
                userData.setId(data.getInt("U.ID"));
                userData.setName(data.getString("name"));
                userData.setPassword(data.getString("password"));
                userData.setNickname(data.getString("nickname"));
                userData.setBirthday(data.getString("birthday"));
                userData.setAccount(account);
                result = userData;
            }
        } catch (SQLException ex) {
            System.err.println("DB find user error: " + ex.getMessage());
            try {
                connection.close();
            } catch (SQLException exc) {
                System.err.println("Close error: " + ex.getMessage());
            }
        }
        return result;
    }

    @Override
    public int remove(final int ID) {
        int delete = 0;
        try (PreparedStatement query = connection.prepareStatement(this.delete)) {
            query.setInt(1, ID);
            delete = query.executeUpdate();
        } catch (SQLException ex) {
            System.err.println("DB remove error: " + ex.getMessage());
        }
        return delete;
    }
}
