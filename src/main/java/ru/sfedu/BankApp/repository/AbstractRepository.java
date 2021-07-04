package ru.sfedu.BankApp.repository;

import ru.sfedu.BankApp.utils.Util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public abstract class AbstractRepository<E, K> {

    private Connection connection;

    public AbstractRepository() throws SQLException, ClassNotFoundException {
        Util util = new Util();
        connection = util.getConnection();
    }

    public AbstractRepository(Connection connection) throws SQLException, ClassNotFoundException {
        this.connection = connection;
    }

    public abstract Optional<List<E>> getAll();
    public abstract Optional<E> update(E entity) throws SQLException, ClassNotFoundException;
    public abstract Optional<E> getById(K id) throws SQLException;
    public abstract boolean delete(K id) throws SQLException;
    public abstract boolean create(E entity) throws SQLException, ClassNotFoundException;

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
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
