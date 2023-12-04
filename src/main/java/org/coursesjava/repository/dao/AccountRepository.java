package org.coursesjava.repository.dao;

import org.coursesjava.model.Account;
import org.coursesjava.model.User;

import java.util.Optional;

public interface AccountRepository {
    Optional<Account> create(Account account);
    Optional<Account> update(Account account, int amount);
}
