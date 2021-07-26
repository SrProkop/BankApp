package ru.sfedu.BankApp.utils;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import ru.sfedu.BankApp.models.Account;
import ru.sfedu.BankApp.models.User;
import ru.sfedu.BankApp.repository.AccountRepository;
import ru.sfedu.BankApp.repository.UserRepository;

import java.sql.SQLException;

@Configuration
@ComponentScan("ru.sfedu.BankApp")
public class SpringConfig {

    @Bean
    public Util util() throws SQLException, ClassNotFoundException {
        return new Util();
    }

    @Bean
    public UserRepository userRepository() throws SQLException, ClassNotFoundException {
        return new UserRepository(util().getConnection());
    }

    @Bean
    public AccountRepository accountRepository() throws SQLException, ClassNotFoundException {
        return new AccountRepository(util().getConnection(), userRepository());
    }

}
