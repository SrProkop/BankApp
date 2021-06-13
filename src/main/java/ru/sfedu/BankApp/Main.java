package ru.sfedu.BankApp;

import ru.sfedu.BankApp.controller.AccountService;
import ru.sfedu.BankApp.controller.UserService;
import ru.sfedu.BankApp.models.Account;
import ru.sfedu.BankApp.models.User;
import ru.sfedu.BankApp.utils.Util;

import java.sql.*;
import java.util.Optional;

public class Main {

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Connection connection = new Util().getConnection();
        UserService userService = new UserService(connection);
        AccountService accountService = new AccountService(connection);
        //userService.initDB();
        //userService.create(new User("Иван", "Иванов", "Иванович", "м"));
        //accountService.create(new Account(7382938, 1000, "60ed851d-7f18-411f-b6aa-0495e9cbfc95"));
        System.out.println(userService.getAll().toString());
        System.out.println(accountService.getAll().toString());

    }

}
