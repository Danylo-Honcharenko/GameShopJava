package org.coursesjava.repository;

import org.coursesjava.model.Account;
import org.coursesjava.repository.dao.AccountRepository;

import java.sql.*;
import java.util.Optional;

public class AccountRepositoryImpl implements AccountRepository {
    private Connection connection;

    public AccountRepositoryImpl(Connection connection) {
        this.connection = connection;
    }

    private String create =
            """
            INSERT INTO Accounts (type, user_id) VALUES (?, ?);
            """;

    private String update =
            """
            UPDATE Accounts SET amount = ? WHERE ID = ?;
            """;

    @Override
    public Optional<Account> create(Account account) {
        Account accountResult = null;
        try (PreparedStatement query = connection.prepareStatement(create, Statement.RETURN_GENERATED_KEYS)) {
            query.setString(1, account.getCardType());
            query.setInt(2, account.getUserId());
            query.executeUpdate();
            ResultSet generationKey = query.getGeneratedKeys();
            generationKey.next();
            account.setId(generationKey.getInt(1));

            accountResult = account;
        } catch (SQLException ex) {
            System.out.println("DB create error: " + ex.getMessage());
            try {
                connection.close();
            } catch (SQLException exc) {
                System.err.println("Close error: " + ex.getMessage());
            }
        }
        return Optional.ofNullable(accountResult);
    }

    @Override
    public Optional<Account> update(Account account, int amount) {
        Account accountUpdate = null;
        try (PreparedStatement query = connection.prepareStatement(update)) {
            query.setInt(1, amount);
            query.setInt(2, account.getId());
            if (query.executeUpdate() > 0) {
                account.setAmount(amount);
                accountUpdate = account;
            }
        } catch (SQLException ex) {
            System.out.println("DB create error: " + ex.getMessage());
            try {
                connection.close();
            } catch (SQLException exc) {
                System.err.println("Close error: " + ex.getMessage());
            }
        }
        return Optional.ofNullable(accountUpdate);
    }
}
