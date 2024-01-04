package com.epitech.server.payment;

public class CreditCard {
  private String cardNumber;
  private String securityNumber;
  private String expirationDate;
  private String cardOwner;

  public String getCardOwner() {
    return cardOwner;
  }

  public void setCardOwner(String cardOwner) {
    this.cardOwner = cardOwner;
  }

  public String getExpirationDate() {
    return expirationDate;
  }

  public void setExpirationDate(String expirationDate) {
    this.expirationDate = expirationDate;
  }

  public CreditCard() {}

  public CreditCard(String cardNumber, String securityNumber, String expirationDate, String cardOwner) {
    this.cardNumber = cardNumber;
    this.securityNumber = securityNumber;
    this.expirationDate = expirationDate;
    this.cardOwner = cardOwner;
  }

  public String getCardNumber() {
    return cardNumber;
  }

  public void setCardNumber(String cardNumber) {
    this.cardNumber = cardNumber;
  }

  public String getSecurityNumber() {
    return securityNumber;
  }

  public void setSecurityNumber(String securityNumber) {
    this.securityNumber = securityNumber;
  }
}
