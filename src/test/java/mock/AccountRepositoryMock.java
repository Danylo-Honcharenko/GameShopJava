package mock;

import org.coursesjava.model.Account;
import org.coursesjava.model.User;
import org.coursesjava.repository.dao.AccountRepository;

import java.util.ArrayList;
import java.util.List;

public class AccountRepositoryMock implements AccountRepository {
    private final List<Account> accounts = new ArrayList<>();

    @Override
    public int create(User user, String paymentSystem) {
        Account check = new Account();
        check.setId(10);
        check.setAmount(0);
        check.setType(paymentSystem);
        check.setUser_id(user.getId());
        accounts.add(check);
        return 1;
    }

    @Override
    public int update(Account account, int amount) {
        account.setAmount(amount);
        accounts.set(0, account);
        return 1;
    }

    @Override
    public Account get(int ID) {
        return accounts.stream()
                .filter(a -> a.getId() == ID)
                .findFirst()
                .orElse(null);
    }

    @Override
    public int remove(int ID) {
        return 1;
    }
}
