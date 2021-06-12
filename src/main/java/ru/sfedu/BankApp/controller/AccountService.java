package ru.sfedu.BankApp.controller;

import ru.sfedu.BankApp.models.Account;
import ru.sfedu.BankApp.models.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class AccountService extends AbstractService<Account, String > {

    public static final String SELECT_ALL_ACCOUNT = "SELECT * FROM ACCOUNT";
    public static final String INSERT_ACCOUNT = "INSERT INTO ACCOUNT VALUES ('%s', '%s', '%s', '%s');";
    public static final String SELECT_ACCOUNT = "SELECT * FROM ACCOUNT WHERE idAccount='%s';";
    public static final String DELETE_ACCOUNT = "DELETE FROM ACCOUNT WHERE idAccount='%s';";

    public AccountService() throws SQLException, ClassNotFoundException {
    }

    public AccountService(Connection connection) throws SQLException, ClassNotFoundException {
        super(connection);
    }

    @Override
    public Optional<List<Account>> getAll() {
        List<Account> list = new LinkedList<Account>();
        PreparedStatement ps = getPrepareStatement(SELECT_ALL_ACCOUNT);
        try {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Account account = new Account();
                account.setId(rs.getString(1));
                account.setNumber(rs.getLong(2));
                account.setBalance(rs.getLong(3));
                account.setIdUser(rs.getString(4));
                list.add(account);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closePrepareStatement(ps);
        }
        return Optional.of(list);
    }

    @Override
    public Optional<Account> update(Account entity) throws SQLException, ClassNotFoundException {
        Optional<Account> account = getById(entity.getId());
        UserService userService = new UserService(getConnection());
        Optional<User> user = userService.getById(entity.getIdUser());
        if (account.isPresent() && user.isPresent()) {
            delete(entity.getId());
            create(entity);
            return Optional.of(entity);
        } else {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Account> getById(String id) throws SQLException {
        PreparedStatement ps = getPrepareStatement(String.format(SELECT_ACCOUNT, id));
        Account account = new Account();
        ResultSet rs = ps.executeQuery();
        if (rs != null && rs.next()) {
            account.setId(rs.getString(1));
            account.setNumber(rs.getLong(2));
            account.setBalance(rs.getLong(3));
            account.setIdUser(rs.getString(4));
            return Optional.of(account);
        } else {
            return Optional.empty();
        }
    }

    @Override
    public boolean delete(String id) throws SQLException {
        Optional<Account> account = getById(id);
        if (account.isPresent()) {
            PreparedStatement ps = getPrepareStatement(String.format(DELETE_ACCOUNT, id));
            ps.executeUpdate();
            closePrepareStatement(ps);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean create(Account entity) throws SQLException, ClassNotFoundException {
        Optional<Account> account = getById(entity.getId());
        UserService userService = new UserService(getConnection());
        Optional<User> user = userService.getById(entity.getIdUser());
        if (account.isEmpty() && user.isPresent()) {
            PreparedStatement statement = getPrepareStatement(
                    String.format(
                            INSERT_ACCOUNT,
                            entity.getId(),
                            entity.getNumber(),
                            entity.getBalance(),
                            entity.getIdUser()));
            statement.executeUpdate();
            closePrepareStatement(statement);
            return true;
        } else {
            return false;
        }
    }
}
