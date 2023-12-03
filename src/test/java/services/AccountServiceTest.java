package services;

import org.coursesjava.model.Account;
import org.coursesjava.services.AccountService;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.*;

public class AccountServiceTest {
    private final AccountService accountService = Mockito.mock(AccountService.class);;

    @Test
    public void create() {

        Account actual = new Account();
        actual.setCardType("Mastercard");
        actual.setUserId(1);

        Account expected = new Account();
        expected.setCardType("Mastercard");
        expected.setUserId(1);

        Mockito.when(accountService.create(any(Account.class)))
                        .thenReturn(Optional.of(actual));
        Assert.assertTrue(accountService.create(actual).isPresent());
        Assert.assertEquals(expected, accountService.create(actual).get());
    }

    @Test
    public void update() {
        Account actual = new Account();
        actual.setId(1);
        actual.setAmount(100);
        actual.setUserId(1);

        Account expected = new Account();
        expected.setId(1);
        expected.setAmount(100);
        expected.setUserId(1);

        Mockito.when(accountService.update(any(Account.class), anyInt()))
                .thenReturn(Optional.of(actual));
        Assert.assertTrue(accountService.update(actual, 100).isPresent());
        Assert.assertEquals(expected, accountService.update(actual, 100).get());
    }
}
