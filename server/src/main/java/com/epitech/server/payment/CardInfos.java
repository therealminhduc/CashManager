package com.epitech.server.payment;

import java.util.Date;

public class CardInfos {
  private String cardNumber;
  private String name;
  private int ccv;

  public Date getExpirationDate() {
    return expirationDate;
  }

  public void setExpirationDate(Date expirationDate) {
    this.expirationDate = expirationDate;
  }

  private Date expirationDate;

  public CardInfos() {}

  public CardInfos(String cardNumber, String name, int ccv, Date expirationDate) {
    this.cardNumber = cardNumber;
    this.name = name;
    this.ccv = ccv;
    this.expirationDate = expirationDate;
  }

  public String getCardNumber() {
    return cardNumber;
  }

  public void setCardNumber(String cardNumber) {
    this.cardNumber = cardNumber;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getCcv() {
    return ccv;
  }

  public void setCcv(int ccv) {
    this.ccv = ccv;
  }
}
