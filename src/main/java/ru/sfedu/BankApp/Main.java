package ru.sfedu.BankApp;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.sfedu.BankApp.models.Account;
import ru.sfedu.BankApp.models.User;
import ru.sfedu.BankApp.repository.AccountRepository;
import ru.sfedu.BankApp.repository.UserRepository;
import ru.sfedu.BankApp.utils.SpringConfig;
import ru.sfedu.BankApp.utils.Util;

import java.math.BigDecimal;
import java.sql.*;

public class Main {

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        UserRepository userService = context.getBean("userRepository", UserRepository.class);
        AccountRepository accountService = context.getBean("accountRepository", AccountRepository.class);
        System.out.println(userService.getAll());
        User user = new User();
        User user1 = new User();
        user.initInformation("Константин1", "Константинович1", "Константинов1", "м");
        user1.initInformation("Константин2", "Константинович2", "Константинов2", "м");
        userService.create(user);
        userService.create(user1);
        System.out.println(userService.getAll());
    }

}
