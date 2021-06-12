package ru.sfedu.BankApp;

import ru.sfedu.BankApp.controller.AccountService;
import ru.sfedu.BankApp.controller.UserService;
import ru.sfedu.BankApp.models.Account;
import ru.sfedu.BankApp.models.User;

import java.sql.*;
import java.util.Optional;

public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        UserService userService = new UserService();
        AccountService accountService = new AccountService();
        //userService.initDB();
        //userService.create(new User("Иван", "Иванов", "Иванович", "м"));
        //accountService.create(new Account(7382938, 1000, "91ca7f24-e4ce-426b-ad91-3621e5272364"));
        System.out.println(accountService.getById("34082c67-17c1-4fda-8b67-2b1c799ab58d"));
        Account account = accountService.getById("34082c67-17c1-4fda-8b67-2b1c799ab58d").get();
        account.setBalance(2000);
        System.out.println(accountService.update(account));
        //System.out.println(userService.getAll().toString());
        System.out.println(accountService.getAll().toString());

    }

}
