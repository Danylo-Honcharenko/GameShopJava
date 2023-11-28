package org.coursesjava.repository;

import org.coursesjava.model.Account;
import org.coursesjava.model.User;
import org.coursesjava.repository.dao.AccountRepository;

import java.sql.*;

public class AccountRepositoryImpl implements AccountRepository {
    private final Connection connection;

    public AccountRepositoryImpl(Connection connection) {
        this.connection = connection;
    }

    private final String create =
            """
            INSERT INTO Accounts (type, user_id) VALUE (?, ?);
            """;

    private final String update =
            """
            UPDATE Accounts SET amount = ? WHERE ID = ?;
            """;

    private final String delete =
            """
            DELETE FROM Accounts WHERE ID = ?;
            """;

    @Override
    public int create(User user, String paymentSystem) {
        int rowsChanged = 0;
        try (PreparedStatement query = connection.prepareStatement(create, Statement.RETURN_GENERATED_KEYS)){
            query.setString(1, paymentSystem);
            query.setInt(2, user.getId());

            rowsChanged = query.executeUpdate();

        } catch (SQLException ex) {
            System.out.println("DB create error: " + ex.getMessage());
            try {
                connection.close();
            } catch (SQLException exc) {
                System.err.println("Close error: " + ex.getMessage());
            }
        }
        return rowsChanged;
    }

    @Override
    public int update(Account account, int amount) {
        int rowsUpdate = 0;
        try (PreparedStatement query = connection.prepareStatement(update)) {
            query.setInt(1, amount);
            query.setInt(2, account.getId());
            rowsUpdate = query.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("DB create error: " + ex.getMessage());
            try {
                connection.close();
            } catch (SQLException exc) {
                System.err.println("Close error: " + ex.getMessage());
            }
        }
        return rowsUpdate;
    }

    @Override
    public Account get(int ID) {
        return null;
    }

    @Override
    public int remove(int ID) {
        int rowsChanged = 0;
        try (PreparedStatement query = connection.prepareStatement(delete)) {
            query.setInt(1, ID);
            rowsChanged = query.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("DB remove entry error: " + ex.getMessage());
        }
        return rowsChanged;
    }
}
