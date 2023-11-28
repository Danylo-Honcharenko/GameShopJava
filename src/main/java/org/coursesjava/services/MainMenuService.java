package org.coursesjava.services;

import org.coursesjava.ConnectionSingleton;
import org.coursesjava.GameStoreMenu;
import org.coursesjava.enums.*;
import org.coursesjava.enums.Error;
import org.coursesjava.model.User;
import org.coursesjava.repository.AccountRepositoryImpl;
import org.coursesjava.repository.UserRepositoryImpl;

import java.sql.SQLException;
import java.util.Scanner;

public class MainMenuService {
    private final Scanner scanner;
    private UserService user;
    private AccountService account;
    public MainMenuService(Scanner scanner) {
        this.scanner = scanner;

        try {
            this.user = new UserService(new UserRepositoryImpl(ConnectionSingleton.getConnection()));
            this.account = new AccountService(new AccountRepositoryImpl(ConnectionSingleton.getConnection()));
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

        /**
         * User schema (Java)
         * =====
         * id: id
         * name: name
         * nickname: nickname
         * birthday: birthday
         * password: password
         * account: [id: id, amount: amount, type: type, user_id: user_id]
         * **/

        if (user.create(candidate)) {
            System.out.println(Message.USER_CREATE_SUCCESSFULLY);

            System.out.println(Tips.ACCOUNT_REGISTRATION);
            System.out.print(Menu.INDICATE_PAYMENT_SYSTEM);
            String paymentSystem = scanner.next();

            if (account.create(user.find(candidate), paymentSystem)) {
                System.out.println(Message.ACCOUNT_CREATED_SUCCESSFULLY);
            } else {
                System.out.println(Error.NOT_CREATED_ACCOUNT);
            }
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

        User user = new User();
        user.setName(userName);
        user.setPassword(password);

        User result = this.user.find(user);

        if (result != null) {
            LocalStorageService.set(result);
            new GameStoreMenu(scanner).show();
        } else {
            System.out.println(Error.USER_NOT_FOUND);
        }
    }
}
