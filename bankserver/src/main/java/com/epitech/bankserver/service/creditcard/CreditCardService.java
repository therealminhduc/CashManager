package com.epitech.bankserver.service.creditcard;

import com.epitech.bankserver.model.creditcard.CreditCard;
import com.epitech.bankserver.repository.creditcard.CreditCardRepository;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@AllArgsConstructor
@Service
public class CreditCardService {

  @Autowired
  private CreditCardRepository creditCardRepository;

  public List<CreditCard> getAllCreditCards() {
    return creditCardRepository.findAll();
  }

  public CreditCard findByCardNumber(String cardNumber) {
    return creditCardRepository.findCreditCardByCardNumber(cardNumber);
  }

  public CreditCard createCreditCard(CreditCard creditCard) {
    return creditCardRepository.save(creditCard);
  }

  public CreditCard createCreditCardWithAccountNumber(
    String accountNumber,
    String cardOwner
  ) {
    CreditCard creditCard = new CreditCard();

    String cardNumber = generateUniqueCardNumber();
    creditCard.setCardNumber(cardNumber);

    String securityNumber = generateUniqueSecurityNumber();
    creditCard.setSecurityNumber(securityNumber);

    creditCard.setAccountNumber(accountNumber);
    creditCard.setCardOwner(cardOwner);

    // Credit card expiration date is set to 3 years from now
    Date expirationDate = new Date(
      System.currentTimeMillis() + (3L * 365L * 24L * 60L * 60L * 1000L)
    );
    SimpleDateFormat formatter = new SimpleDateFormat("MM/yyyy");
    String formattedExpirationDate = formatter.format(expirationDate);

    System.out.println(formattedExpirationDate);

    try {
      Date parsedExpirationDate = formatter.parse(formattedExpirationDate);
      creditCard.setExpirationDate(parsedExpirationDate);
    } catch (ParseException e) {
      log.error("Parse error", e);
    }

    return creditCardRepository.save(creditCard);
  }

  public CreditCard updateCreditCard(CreditCard creditCard) {
    return creditCardRepository.save(creditCard);
  }

  public void deleteCreditCard(String id) {
    creditCardRepository.deleteById(id);
  }

  public void deleteCreditCardByCardNumber(String cardNumber) {
    creditCardRepository.deleteCreditCardByCardNumber(cardNumber);
  }

  /********************************************************************************/

  public String generateUniqueCardNumber() {
    Random random = new Random();
    String random16DigitNumber = generateRandom16DigitNumber(random);

    while (
      creditCardRepository.existsCreditCardByCardNumber(random16DigitNumber)
    ) {
      random16DigitNumber = generateRandom16DigitNumber(random);
    }

    return random16DigitNumber;
  }

  private String generateRandom16DigitNumber(Random random) {
    StringBuilder stringBuilder = new StringBuilder();

    for (int i = 0; i < 16; i++) {
      stringBuilder.append(random.nextInt(10));
    }

    return stringBuilder.toString();
  }

  public String generateUniqueSecurityNumber() {
    Random random = new Random();
    String random3DigitNumber = generateRandom3DigitNumber(random);

    while (
      creditCardRepository.existsCreditCardBySecurityNumber(random3DigitNumber)
    ) {
      random3DigitNumber = generateRandom3DigitNumber(random);
    }

    return random3DigitNumber;
  }

  private String generateRandom3DigitNumber(Random random) {
    StringBuilder stringBuilder = new StringBuilder();

    for (int i = 0; i < 3; i++) {
      stringBuilder.append(random.nextInt(10));
    }

    return stringBuilder.toString();
  }
}
