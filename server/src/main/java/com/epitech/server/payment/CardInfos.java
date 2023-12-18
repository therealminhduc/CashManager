package com.epitech.server.payment;

public class CardInfos {
  private String cardNumber;
  private String name;
  private int ccv;

  public CardInfos() {}

  public CardInfos(String cardNumber, String name, int ccv) {
    this.cardNumber = cardNumber;
    this.name = name;
    this.ccv = ccv;
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
