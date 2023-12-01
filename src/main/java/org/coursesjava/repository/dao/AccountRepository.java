package org.coursesjava.repository.dao;

import org.coursesjava.model.Account;
import org.coursesjava.model.User;

public interface AccountRepository {
    int create(User user, String paymentSystem);
    int update(Account account, int amount);
    int remove(final int ID);
}
