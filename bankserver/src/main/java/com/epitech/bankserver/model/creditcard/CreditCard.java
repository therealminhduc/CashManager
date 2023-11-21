package com.epitech.bankserver.model.creditcard;

import com.epitech.bankserver.model.user.User;

import java.util.Date;

public class CreditCard {

    private String cardNumber;
    private String securityNumber;
    private Date expirationDate;
    private User userInfo;

    public CreditCard(String cardNumber, String securityNumber, Date expirationDate, User userInfo) {
        this.cardNumber = cardNumber;
        this.securityNumber = securityNumber;
        this.expirationDate = expirationDate;
        this.userInfo = userInfo;
    }

    /* getters */
    public String getCardNumber() {
        return cardNumber;
    }

    public String getSecurityNumber() {
        return securityNumber;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public User getUserInfo() {
        return userInfo;
    }

    /* setters */
    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public void setSecurityNumber(String securityNumber) {
        this.securityNumber = securityNumber;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public void setUserInfo(User userInfo) {
        this.userInfo = userInfo;
    }
}
