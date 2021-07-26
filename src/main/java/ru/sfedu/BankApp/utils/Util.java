package ru.sfedu.BankApp.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {

    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String USER_NAME = "root";
    private static final String PASSWORD = "root";
    private static final String URL = "jdbc:mysql://localhost:3306/mydbtest";
    private static Connection connection;

    public Util() throws SQLException, ClassNotFoundException {
        connection = createConnection();
    }

    public Connection getConnection() {
        return connection;
    }

    public static Connection createConnection() throws SQLException, ClassNotFoundException {
        Connection connection = null;
        try {
            Class.forName(DRIVER);
            connection = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
            System.out.println("Connection OK");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("Connection error");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Connection error");
        }
        return connection;
    }

}
