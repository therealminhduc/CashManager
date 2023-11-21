package com.epitech.bankserver.model.account;

import com.epitech.bankserver.model.user.User;

public class Account {

    private String accountNumber;
    private int balance;
    private User user;
    private String[] creditCard;

    public Account(String accountNumber, int balance, User user, String[] creditCard) {
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.user = user;
        this.creditCard = creditCard;
    }

    /* getters */
    public String getAccountNumber() {
        return accountNumber;
    }

    public int getBalance() {
        return balance;
    }

    public User getUser() {
        return user;
    }

    public String[] getCreditCard() {
        return creditCard;
    }

    /* setters */
    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public void setCreditCard(String[] creditCard) {
        this.creditCard = creditCard;
    }
}
