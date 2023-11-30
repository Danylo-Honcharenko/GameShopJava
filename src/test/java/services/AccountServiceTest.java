package services;

import org.coursesjava.model.Account;
import org.coursesjava.model.User;
import org.coursesjava.services.AccountService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.mockito.ArgumentMatchers.*;

public class AccountServiceTest {
    private AccountService accountService;

    @Before
    public void init() {
        accountService = Mockito.mock(AccountService.class);
    }

    @Test
    public void create() {
        User user = new User();
        user.setId(5);
        user.setName("Dima");
        user.setNickname("Ethra");
        user.setBirthday("2002-10-21");
        user.setPassword("123456789");
        user.setAccount(null);

        Account account = new Account();
        account.setId(1);
        account.setAmount(0);
        account.setUser_id(user.getId());

        Mockito.when(accountService.create(any(User.class), eq("Visa")))
                        .thenReturn(true);
        Assert.assertTrue(accountService.create(user, "Visa"));
        Mockito.when(accountService.create(any(User.class), eq("Mastercard")))
                .thenReturn(true);
        Assert.assertTrue(accountService.create(user, "Mastercard"));
    }

    @Test
    public void update() {
        Account account = new Account();
        account.setId(1);
        account.setAmount(0);
        account.setUser_id(0);

        Mockito.when(accountService.update(any(Account.class), anyInt()))
                .thenReturn(true);
        Assert.assertTrue(accountService.update(account, 100));
    }

    @Test
    public void get() {
        Account expected = new Account();
        expected.setId(10);
        expected.setAmount(0);
        expected.setType("Visa");
        expected.setUser_id(0);

        Mockito.when(accountService.getAmount(anyInt()))
                .thenReturn(expected);
        Assert.assertEquals(expected, accountService.getAmount(10));
    }
}
