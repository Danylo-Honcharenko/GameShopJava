package org.coursesjava.service;

import org.coursesjava.config.ConnectionConfig;
import org.coursesjava.GameStoreMenu;
import org.coursesjava.enums.*;
import org.coursesjava.enums.Error;
import org.coursesjava.model.Account;
import org.coursesjava.model.User;
import org.coursesjava.repository.AccountRepositoryImpl;
import org.coursesjava.repository.UserRepositoryImpl;

import java.sql.SQLException;
import java.util.Optional;
import java.util.Scanner;

public class MainMenuService {
    private final Scanner scanner;
    private UserService userService;
    private AccountService accountService;
    public MainMenuService(Scanner scanner) {
        this.scanner = scanner;

        try {
            this.userService = new UserService(new UserRepositoryImpl(ConnectionConfig.getConnection()));
            this.accountService = new AccountService(new AccountRepositoryImpl(ConnectionConfig.getConnection()));
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public void createUser() {
        System.out.println(MenuTitle.CREATE_USER);

        System.out.print(Menu.ADD_USER_NAME);
        String name = scanner.next();

        System.out.print(Menu.ADD_USER_NICKNAME);
        String nickname = scanner.next();

        System.out.println(Tips.DATE_FORMAT);
        System.out.print(Menu.ADD_BIRTHDAY);
        String birthday = scanner.next();

        System.out.print(Menu.ADD_PASSWORD);
        String password = scanner.next();

        User candidate = new User();
        candidate.setName(name);
        candidate.setNickname(nickname);
        candidate.setBirthday(birthday);
        candidate.setPassword(password);

        Optional<User> createUser = userService.create(candidate);

        if (createUser.isPresent()) {
            System.out.println(Message.USER_CREATE_SUCCESSFULLY);
            if (userService.findByNameAndPassword(name, password).isEmpty()) {
                System.out.println(Error.USER_NOT_FOUND);
                return;
            }
            createAccount(createUser.get().getId());
        } else {
            System.out.println(Error.NOT_CREATED_USER);
        }
    }

    public void login() {
        System.out.println(MenuTitle.LOGIN);

        System.out.print(Menu.USER_NAME);
        String userName = scanner.next();

        System.out.print(Menu.PASSWORD);
        String password = scanner.next();

        Optional<User> result = userService.findByNameAndPassword(userName, password);

        if (result.isPresent()) {
            LocalStorageService.set(result.get());
            new GameStoreMenu(scanner).show();
        } else {
            System.out.println(Error.USER_NOT_FOUND);
        }
    }

    private void createAccount(int userId) {
        System.out.println(Tips.ACCOUNT_REGISTRATION);
        System.out.print(Menu.INDICATE_PAYMENT_SYSTEM);
        String paymentSystem = scanner.next();

        Account account = new Account();
        account.setUserId(userId);
        account.setCardType(paymentSystem);

        if (accountService.create(account).isPresent()) {
            System.out.println(Message.ACCOUNT_CREATED_SUCCESSFULLY);
        } else {
            System.out.println(Error.NOT_CREATED_ACCOUNT);
        }
    }
}
