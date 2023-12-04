package com.epitech.bankserver.repository.creditcard;

import com.epitech.bankserver.model.creditcard.CreditCard;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CreditCardRepository
  extends MongoRepository<CreditCard, String> {
  CreditCard findCreditCardByCardNumber(String cardNumber);

  boolean existsCreditCardByCardNumber(String cardNumber);

  boolean existsCreditCardBySecurityNumber(String securityNumber);

  CreditCard deleteCreditCardByCardNumber(String cardNumber);
}
