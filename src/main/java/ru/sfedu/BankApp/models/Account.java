package ru.sfedu.BankApp.models;

import java.util.UUID;

public class Account {

    public Account() {

    }

    public Account(long number, long balance, String idUser) {
        this.id = UUID.randomUUID().toString();
        this.number = number;
        this.balance = balance;
        this.idUser = idUser;
    }

    private String id;
    private long number;
    private long balance;
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

    public long getBalance() {
        return balance;
    }

    public void setBalance(long balance) {
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
