package repository;

import config.MysqlConnector;
import org.coursesjava.model.User;
import org.coursesjava.repository.UserRepositoryImpl;
import org.coursesjava.repository.dao.UserRepository;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;

public class UserRepositoryImplTest {
    private UserRepository user;
    private Connection connection;

    @Before
    public void init() throws SQLException {
        connection = MysqlConnector.getConnection();
        user = new UserRepositoryImpl(connection);
    }

    @After
    public void close() throws SQLException {
        connection.close();
    }

    @Test
    public void create() {
        User actual = new User();
        actual.setName("Dima");
        actual.setNickname("Ethra");
        actual.setBirthday("2002-10-21");
        actual.setPassword("123456789");

        Assert.assertTrue(user.create(actual).isPresent());
    }

    @Test
    public void get() {
        User actual = new User();
        actual.setName("Dima");
        actual.setPassword("123456789");

        Assert.assertTrue(user.get(actual).isPresent());
    }
}
