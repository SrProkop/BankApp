package ru.sfedu.BankApp;

import ru.sfedu.BankApp.controller.UserController;
import ru.sfedu.BankApp.models.User;

import java.sql.*;

public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        UserController userController = new UserController();
        //userController.initDB();
        //userController.create(new User("Иван", "Иванов", "Иванович", "м"));
        System.out.println(userController.getAll().toString());
        User user = userController.getById("474d63fa-c38b-4a42-a382-be853953118f");
        user.setName("Петр");
        System.out.println(userController.update(user));
        System.out.println(userController.getAll().toString());
    }

}
