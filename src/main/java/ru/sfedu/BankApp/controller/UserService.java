package ru.sfedu.BankApp.controller;

import ru.sfedu.BankApp.models.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class UserService extends AbstractService<User, String> {

    public static final String SELECT_ALL_USERS = "SELECT * FROM USER";
    public static final String INSERT_USER = "INSERT INTO USER VALUES ('%s', '%s', '%s', '%s', '%s');";
    public static final String SELECT_USER = "SELECT * FROM USER WHERE idUser='%s';";
    public static final String DELETE_USER = "DELETE FROM USER WHERE idUser='%s';";

    public UserService() throws SQLException, ClassNotFoundException {

    }

    public UserService(Connection connection) throws SQLException, ClassNotFoundException {
        super(connection);
    }

    @Override
    public Optional<List<User>> getAll() {
        List<User> list = new LinkedList<User>();
        PreparedStatement ps = getPrepareStatement(SELECT_ALL_USERS);
        try {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getString(1));
                user.setName(rs.getString(2));
                user.setSurname(rs.getString(3));
                user.setPatronymic(rs.getString(4));
                user.setSex(rs.getString(5));
                list.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closePrepareStatement(ps);
        }
        return Optional.of(list);
    }

    @Override
    public Optional<User> update(User entity) throws SQLException {
        Optional<User> user = getById(entity.getId());
        if (user.isPresent()) {
            delete(entity.getId());
            create(entity);
            return Optional.of(entity);
        } else {
            return Optional.empty();
        }
    }

    @Override
    public Optional<User> getById(String id) throws SQLException {
        PreparedStatement ps = getPrepareStatement(String.format(SELECT_USER, id));
        User user = new User();
        ResultSet rs = ps.executeQuery();
        if (rs != null && rs.next()) {
            user.setId(rs.getString(1));
            user.setName(rs.getString(2));
            user.setSurname(rs.getString(3));
            user.setPatronymic(rs.getString(4));
            user.setSex(rs.getString(5));
            return Optional.of(user);
        } else {
            return Optional.empty();
        }
    }

    @Override
    public boolean delete(String id) throws SQLException {
        Optional<User> user = getById(id);
        if (user.isPresent()) {
            PreparedStatement ps = getPrepareStatement(String.format(DELETE_USER, id));
            ps.executeUpdate();
            closePrepareStatement(ps);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean create(User entity) throws SQLException {
        Optional<User> user = getById(entity.getId());
        if (user.isEmpty()) {
            PreparedStatement statement = getPrepareStatement(
                    String.format(
                            INSERT_USER,
                            entity.getId(),
                            entity.getName(),
                            entity.getSurname(),
                            entity.getPatronymic(),
                            entity.getSex()));
            statement.executeUpdate();
            closePrepareStatement(statement);
            return true;
        } else {
            return false;
        }
    }
}
