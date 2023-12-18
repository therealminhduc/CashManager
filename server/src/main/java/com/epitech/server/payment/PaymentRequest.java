package com.epitech.server.payment;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class PaymentRequest {
  private CardInfos cardInfos;
  private float amount;

  public PaymentRequest(CardInfos cardInfos, float amount) {
    this.cardInfos = cardInfos;
    this.amount = amount;
  }

  public String toJson() throws JsonProcessingException {
    ObjectMapper objectMapper = new ObjectMapper();
    return objectMapper.writeValueAsString(this);
  }

  public CardInfos getCardInfos() {
    return cardInfos;
  }

  public void setCardInfos(CardInfos cardInfos) {
    this.cardInfos = cardInfos;
  }

  public float getAmount() {
    return amount;
  }

  public void setAmount(float amount) {
    this.amount = amount;
  }
}
