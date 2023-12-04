package com.epitech.bankserver.model.creditcard;

import java.util.Date;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document("credit_card")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CreditCard {

  @Id
  private String id;

  @Indexed(unique = true)
  private String cardNumber;

  @Indexed(unique = true)
  private String securityNumber;

  private Date expirationDate;

  private String accountOwner;
}
