package services;

import org.coursesjava.model.Account;
import org.coursesjava.model.User;
import org.coursesjava.services.UserService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.notNull;

public class UserServiceTest {

    private UserService userService;

    @Before
    public void init() {
        userService = Mockito.mock(UserService.class);
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

        Mockito.when(userService.create(any(User.class)))
                .thenReturn(true);
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

        User actual = new User();
        actual.setName("Dima");
        actual.setPassword("123456789");

        Mockito.when(userService.find(actual))
                .thenReturn(Optional.of(expected));
        Assert.assertTrue(userService.find(actual).isPresent());
        Assert.assertEquals(expected, userService.find(actual).get());

        Mockito.when(userService.find(actual))
                .thenReturn(null);
        Assert.assertNull(userService.find(actual));
    }
}
