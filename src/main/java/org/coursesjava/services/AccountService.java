package org.coursesjava.services;

import org.coursesjava.model.Account;
import org.coursesjava.repository.dao.AccountRepository;

import java.util.Optional;

public class AccountService {
    private final AccountRepository repository;
    public AccountService(AccountRepository account) {
        repository = account;
    }
    public Optional<Account> create(Account account) {
        return repository.create(account);
    }
    public Optional<Account> update(Account account, int amount) {
        return repository.update(account, amount);
    }
}
