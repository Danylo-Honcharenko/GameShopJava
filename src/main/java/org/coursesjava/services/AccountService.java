package org.coursesjava.services;

import org.coursesjava.model.Account;
import org.coursesjava.model.User;
import org.coursesjava.repository.dao.AccountRepository;

public class AccountService {
    private final AccountRepository account;

    public AccountService(AccountRepository account) {
        this.account = account;
    }

    public boolean create(User user, String paymentSystem) {
        return this.account.create(user, paymentSystem) == 1;
    }

    public boolean update(Account account, int amount) {
        return this.account.update(account, amount) > 0;
    }

    public Account getAmount(final int ID) {
        return account.get(ID);
    }

    public boolean remove(final int ID) {
        return account.remove(ID) > 0;
    }
}
