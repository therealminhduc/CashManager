package com.epitech.bankserver.model.creditcard;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document("credit_card")
public class CreditCard {

  @Id
  private String id;

  @Indexed(unique = true)
  private String cardNumber;

  @Indexed(unique = true)
  private String securityNumber;

  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM/yyyy", timezone = "UTC")
  private Date expirationDate;

  private String accountNumber;

  // public ArrayList<String> getCardNumber() {
  //   return cardNumber;
  // }
}
