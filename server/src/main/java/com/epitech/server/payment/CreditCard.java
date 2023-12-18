package com.epitech.server.payment;

public class CreditCard {
  private String cardNumber;
  private String securityNumber;
  private String expirationDate;

  public String getExpirationDate() {
    return expirationDate;
  }

  public void setExpirationDate(String expirationDate) {
    this.expirationDate = expirationDate;
  }

  public CreditCard() {}

  public CreditCard(String cardNumber, String securityNumber, String expirationDate) {
    this.cardNumber = cardNumber;
    this.securityNumber = securityNumber;
    this.expirationDate = expirationDate;
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
