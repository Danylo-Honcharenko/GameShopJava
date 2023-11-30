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

public class AccountRepositoryImplTest {
    private AccountRepository account;
    private UserRepository user;

    @Before
    public void init() throws SQLException {
        account = new AccountRepositoryImpl(ConnectionSingleton.getConnection());
        user = new UserRepositoryImpl(ConnectionSingleton.getConnection());
    }

    @Test
    public void create() {
        User user = new User();
        user.setName("Misha");
        user.setPassword("123456789");
        user.setNickname("Nota");
        user.setBirthday("2009-08-21");

        // created user
        Assert.assertEquals(1, this.user.create(user));
        Assert.assertTrue(this.user.get(user).isPresent());
        // created account
        Assert.assertEquals(1, account.create(this.user.get(user).get(), "Mastercard"));
        // clear database
        Assert.assertEquals(1, account.remove(this.user.get(user).get().getAccount().getId()));
        Assert.assertEquals(1, this.user.remove(this.user.get(user).get().getId()));
    }

    @Test
    public void update() {
        User user = new User();
        user.setName("Misha");
        user.setPassword("123456789");
        user.setNickname("Nota");
        user.setBirthday("2009-08-21");

        // created user
        Assert.assertEquals(1, this.user.create(user));
        Assert.assertTrue(this.user.get(user).isPresent());
        // created account
        Assert.assertEquals(1, account.create(this.user.get(user).get(), "Visa"));
        // the default amount of money in the user's account is 0, let's check it out
        Assert.assertEquals(0, this.user.get(user).get().getAccount().getAmount());
        // topped up your account with $200
        Assert.assertEquals(1, account.update(this.user.get(user).get().getAccount(), 200));
        // let's check it out
        Assert.assertEquals(200, this.user.get(user).get().getAccount().getAmount());
        // clear database
        Assert.assertEquals(1, account.remove(this.user.get(user).get().getAccount().getId()));
        Assert.assertEquals(1, this.user.remove(this.user.get(user).get().getId()));
    }

    @Test
    public void remove() {
        User user = new User();
        user.setName("Misha");
        user.setPassword("123456789");
        user.setNickname("Nota");
        user.setBirthday("2009-08-21");

        // created user
        Assert.assertEquals(1, this.user.create(user));
        Assert.assertTrue(this.user.get(user).isPresent());
        // created account
        Assert.assertEquals(1, account.create(this.user.get(user).get(), "Visa"));
        // clear database
        Assert.assertEquals(1, account.remove(this.user.get(user).get().getAccount().getId()));
        Assert.assertEquals(1, this.user.remove(this.user.get(user).get().getId()));
    }
}
