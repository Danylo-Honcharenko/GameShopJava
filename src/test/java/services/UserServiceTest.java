package services;

import mock.UserRepositoryMock;
import org.coursesjava.model.Account;
import org.coursesjava.model.User;
import org.coursesjava.services.UserService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class UserServiceTest {
    private UserService userService;

    @Before
    public void init() {
        userService = new UserService(new UserRepositoryMock());
    }

    @Test
    public void create() {
        Account account = new Account();
        account.setUser_id(1);
        account.setAmount(0);
        account.setType("Visa");

        User actual = new User();
        actual.setName("Dima");
        actual.setNickname("Ethra");
        actual.setBirthday("2002-10-21");
        actual.setPassword("123456789");
        actual.setAccount(account);

        Assert.assertTrue(userService.create(actual));
    }

    @Test
    public void get() {
        Account account = new Account();
        account.setId(1);
        account.setUser_id(1);
        account.setAmount(0);
        account.setType("Visa");

        User expected = new User();
        expected.setId(1);
        expected.setName("Dima");
        expected.setNickname("Ethra");
        expected.setBirthday("2002-10-21");
        expected.setPassword("123456789");
        expected.setAccount(account);

        Assert.assertTrue(userService.create(expected));

        User actual = new User();
        actual.setName("Dima");
        actual.setPassword("123456789");

        Assert.assertEquals(expected, userService.find(actual));
    }
}
