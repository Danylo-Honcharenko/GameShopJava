package org.coursesjava.service;

import org.coursesjava.config.ConnectionConfig;
import org.coursesjava.enums.Error;
import org.coursesjava.enums.Menu;
import org.coursesjava.repository.AccountRepositoryImpl;

import java.sql.SQLException;
import java.util.Scanner;

public class AccountMenuService {
    private AccountService account;
    private final Scanner scanner;

    public AccountMenuService(Scanner scanner) {
        this.scanner = scanner;
        try {
            this.account = new AccountService(new AccountRepositoryImpl(ConnectionConfig.getConnection()));
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public void topUpAccount() {
        System.out.print(Menu.TOP_UP_ACCOUNT_Q);
        int amount = scanner.nextInt();
        /*
        * from the local storage we get a copy of the current session user data,
        * get the amount and perform the sum operation on the new desired amount and current
        */
        int creditedAmount = amount + LocalStorageService.get()
                .getAccount()
                .getAmount();

        if (account.update(LocalStorageService.get().getAccount(), creditedAmount).isPresent()) {
            System.out.println("Your account has been successfully replenished with the amount " + creditedAmount + "$");
            // write the account change to local storage
            LocalStorageService.get()
                    .getAccount()
                    .setAmount(creditedAmount);
        } else {
            System.out.println(Error.NOT_CREDITED);
        }
    }

    public void stateOfAnAccount() {
        System.out.println("You have " + LocalStorageService.get().getAccount().getAmount() + "$");
    }
}
