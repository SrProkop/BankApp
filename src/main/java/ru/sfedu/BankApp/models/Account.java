package ru.sfedu.BankApp.models;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.UUID;

@Component
@Scope("prototype")
public class Account {

    public Account() {

    }

    public void initInformation(long number, BigDecimal balance, String idUser) {
        this.id = UUID.randomUUID().toString();
        this.number = number;
        this.balance = balance;
        this.idUser = idUser;
    }

    private String id;
    private long number;
    private BigDecimal balance;
    private String idUser;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public long getNumber() {
        return number;
    }

    public void setNumber(long number) {
        this.number = number;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id='" + id + '\'' +
                ", number=" + number +
                ", balance=" + balance +
                ", idUser='" + idUser + '\'' +
                '}';
    }
}
