package services;

import org.coursesjava.model.Account;
import org.coursesjava.model.User;
import org.coursesjava.service.UserService;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;

public class UserServiceTest {

    private final UserService userService = Mockito.mock(UserService.class);

    @Test
    public void create() {
        Account account = new Account();
        account.setUserId(1);
        account.setAmount(0);
        account.setCardType("Visa");

        User expected = new User();
        expected.setName("Dima");
        expected.setNickname("Ethra");
        expected.setBirthday("2002-10-21");
        expected.setPassword("123456789");
        expected.setAccount(account);

        User actual = new User();
        actual.setName("Dima");
        actual.setNickname("Ethra");
        actual.setBirthday("2002-10-21");
        actual.setPassword("123456789");
        actual.setAccount(account);

        Mockito.when(userService.create(any(User.class)))
                .thenReturn(Optional.of(actual));
        Assert.assertTrue(userService.create(actual).isPresent());
        Assert.assertEquals(expected, userService.create(actual).get());
        Mockito.when(userService.create(any(User.class)))
                .thenReturn(null);
        Assert.assertNull(userService.create(expected));
    }

    @Test
    public void get() {
        User expected = new User();
        expected.setName("Dima");
        expected.setNickname("Ethra");
        expected.setBirthday("2002-10-21");
        expected.setPassword("123456789");
        expected.setAccount(null);

        Mockito.when(userService.findByNameAndPassword(anyString(), anyString()))
                .thenReturn(Optional.of(expected));
        Assert.assertTrue(userService.findByNameAndPassword("Dima", "123456789").isPresent());
        Assert.assertEquals(expected, userService.findByNameAndPassword("Dima", "123456789").get());

        Mockito.when(userService.findByNameAndPassword("Misha", "123456789"))
                .thenReturn(null);
        Assert.assertNull(userService.findByNameAndPassword("Misha", "123456789"));
    }
}
