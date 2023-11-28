package services;

import mock.AccountRepositoryMock;
import org.coursesjava.model.Account;
import org.coursesjava.model.User;
import org.coursesjava.services.AccountService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class AccountServiceTest {
    private AccountService accountService;

    @Before
    public void init() {
        accountService = new AccountService(new AccountRepositoryMock());
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

        Assert.assertTrue(accountService.create(user, "Visa"));
    }

    @Test
    public void update() {
        Account account = new Account();
        User user = new User();

        user.setId(5);
        user.setName("Dima");
        user.setNickname("Ethra");
        user.setBirthday("2002-10-21");
        user.setPassword("123456789");
        user.setAccount(account);

        account.setId(1);
        account.setAmount(0);
        account.setUser_id(user.getId());

        Assert.assertTrue(accountService.create(user, "Visa"));
        Assert.assertTrue(accountService.update(account, 100));
    }

    @Test
    public void get() {
        Account account = new Account();
        User user = new User();

        user.setId(5);
        user.setName("Dima");
        user.setNickname("Ethra");
        user.setBirthday("2002-10-21");
        user.setPassword("123456789");
        user.setAccount(account);

        account.setId(10);
        account.setAmount(0);
        account.setType("Visa");
        account.setUser_id(user.getId());

        Assert.assertTrue(accountService.create(user, "Visa"));
        Assert.assertEquals(account, accountService.getAmount(10));
    }
}
