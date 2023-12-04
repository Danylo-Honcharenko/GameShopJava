package repository;

import config.MysqlConnector;
import org.coursesjava.model.Account;
import org.coursesjava.repository.AccountRepositoryImpl;
import org.coursesjava.repository.dao.AccountRepository;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;

public class AccountRepositoryImplTest {
    private AccountRepository account;
    private Connection connection;

    @Before
    public void init() throws SQLException {
        connection = MysqlConnector.getConnection();
        account = new AccountRepositoryImpl(connection);
    }

    @After
    public void close() throws SQLException {
        connection.close();
    }

    @Test
    public void create() {
        Account account = new Account();
        account.setCardType("Visa");
        account.setUserId(3);
        Assert.assertTrue(this.account.create(account).isPresent());
    }

    @Test
    public void update() {
        Account account = new Account();
        account.setId(1);
        Assert.assertTrue(this.account.update(account, 500).isPresent());
    }
}
