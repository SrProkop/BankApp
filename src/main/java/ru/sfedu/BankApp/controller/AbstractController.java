package ru.sfedu.BankApp.controller;

import com.mysql.cj.jdbc.MysqlConnectionPoolDataSource;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public abstract class AbstractController<E, K> {
    public static final String USER_NAME = "root";
    public static final String PASSWORD = "root";
    public static final String URL = "jdbc:mysql://localhost:3306/mydbtest";

    private Connection connection;

    public AbstractController() throws SQLException, ClassNotFoundException {
        connection = getConnection();
    }

    public abstract List<E> getAll();
    public abstract E update(E entity) throws SQLException;
    public abstract E getById(K id) throws SQLException;
    public abstract boolean delete(K id) throws SQLException;
    public abstract boolean create(E entity) throws SQLException;

    private Connection getConnection() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(URL, USER_NAME, PASSWORD);
    }

    public void initDB() {
        try {
            String s;
            FileReader fr = new FileReader(new File("src/main/resources/initDB.sql"));
            BufferedReader br = new BufferedReader(fr);

            while((s = br.readLine()) != null){
                PreparedStatement ps = getPrepareStatement(s);
                ps.executeUpdate(s);
            }
            br.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public PreparedStatement getPrepareStatement(String sql) {
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return ps;
    }

    public void closePrepareStatement(PreparedStatement ps) {
        if (ps != null) {
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
