package ru.sfedu.BankApp;

import ru.sfedu.BankApp.models.Account;
import ru.sfedu.BankApp.models.User;
import ru.sfedu.BankApp.repository.AccountRepository;
import ru.sfedu.BankApp.repository.UserRepository;
import ru.sfedu.BankApp.utils.Util;

import java.math.BigDecimal;
import java.sql.*;

public class Main {

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Connection connection = new Util().getConnection();
        UserRepository userService = new UserRepository(connection);
        AccountRepository accountService = new AccountRepository(connection);
        //userService.initDB();
        //userService.create(new User("Иван", "Иванов", "Иванович", "м"));
        //accountService.create(new Account(2394392, new BigDecimal("1000"), "70fa3a20-fd29-4bf9-8e50-7844a5b4f659"));
        Account account = accountService.getById("242535da-2923-4151-aeae-c89ebcffc869").get();
        account.setBalance(new BigDecimal("40000"));
        accountService.update(account);
        //User user = userService.getById("70fa3a20-fd29-4bf9-8e50-7844a5b4f659").get();
        //user.setFirstName("Иван");
        //userService.update(user);
        System.out.println(userService.getAll().toString());
        System.out.println(accountService.getAll().toString());

    }

}
