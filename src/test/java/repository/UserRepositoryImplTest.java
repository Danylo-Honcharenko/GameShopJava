package repository;

import org.coursesjava.ConnectionSingleton;
import org.coursesjava.model.User;
import org.coursesjava.repository.AccountRepositoryImpl;
import org.coursesjava.repository.UserRepositoryImpl;
import org.coursesjava.repository.dao.AccountRepository;
import org.coursesjava.repository.dao.UserRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;

public class UserRepositoryImplTest {

    private UserRepository user;
    private AccountRepository account;

    @Before
    public void init() throws SQLException {
        user = new UserRepositoryImpl(ConnectionSingleton.getConnection());
        account = new AccountRepositoryImpl(ConnectionSingleton.getConnection());
    }

    @Test
    public void create() {
        User user = new User();
        user.setName("Dima");
        user.setNickname("Ethra");
        user.setBirthday("2002-10-21");
        user.setPassword("123456789");

        // created user
        Assert.assertEquals(1, this.user.create(user));
        // clear database
        Assert.assertEquals(1, this.user.remove(this.user.get(user).getId()));
    }

    @Test
    public void get() {
        User expected = new User();
        expected.setName("Dima");
        expected.setNickname("Ethra");
        expected.setBirthday("2002-10-21");
        expected.setPassword("123456789");

        // created user
        Assert.assertEquals(1, user.create(expected));
        Assert.assertEquals(1, account.create(expected, "Visa"));

        int ID = user.get(expected).getId();
        // set the ID to the expected object received from the database and the account
        expected.setId(ID);
        expected.setAccount(user.get(expected).getAccount());
        // get the user object and compare it with the expected object
        Assert.assertEquals(expected, user.get(expected));
        // clear database
        Assert.assertEquals(1, account.remove(user.get(expected).getId()));
        Assert.assertEquals(1, user.remove(ID));
    }

    @Test
    public void remove() {
        User user = new User();
        user.setName("Dima");
        user.setNickname("Ethra");
        user.setBirthday("2002-10-21");
        user.setPassword("123456789");

        // created user
        Assert.assertEquals(1, this.user.create(user));
        // get user id
        // remove user
        Assert.assertEquals(1, this.user.remove(this.user.get(user).getId()));
    }
}
