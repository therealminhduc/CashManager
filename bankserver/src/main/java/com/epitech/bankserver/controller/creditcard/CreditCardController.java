package com.epitech.bankserver.controller.creditcard;

import com.epitech.bankserver.model.creditcard.CreditCard;
import com.epitech.bankserver.service.creditcard.CreditCardService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/creditcards")
public class CreditCardController {

  @Autowired
  private CreditCardService creditCardService;

  @GetMapping
  public ResponseEntity<List<CreditCard>> getAllCreditCards() {
    List<CreditCard> creditCards = creditCardService.getAllCreditCards();
    return new ResponseEntity<>(creditCards, HttpStatus.OK);
  }

  @GetMapping("/{cardnumber}")
  public ResponseEntity<CreditCard> getCreditCardByCardNumber(
    @PathVariable String cardnumber
  ) {
    CreditCard creditCard = creditCardService.findByCardNumber(cardnumber);

    if (creditCard != null) {
      return new ResponseEntity<>(creditCard, HttpStatus.OK);
    } else {
      return new ResponseEntity<>(creditCard, HttpStatus.NOT_FOUND);
    }
  }

  @PostMapping
  public ResponseEntity<?> createCreditCardWithAccountNumber(
    @RequestBody Map<String, Object> requestBody
  ) {
    String accountOwner = (String) requestBody.get("accountNumber");

    if (accountOwner == null) {
      return new ResponseEntity<>(
        "'accountNumber' must be provided",
        HttpStatus.BAD_REQUEST
      );
    }

    CreditCard createdCreditCard = creditCardService.createCreditCardWithAccountNumber(
      accountOwner
    );

    return new ResponseEntity<>(createdCreditCard, HttpStatus.CREATED);
  }

  @PutMapping("/{id}")
  public ResponseEntity<CreditCard> updateCreditCard(
    @PathVariable String id,
    @RequestBody CreditCard creditCard
  ) {
    if (!id.equals(creditCard.getId())) {
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    CreditCard updatedCreditCard = creditCardService.updateCreditCard(
      creditCard
    );
    return new ResponseEntity<>(updatedCreditCard, HttpStatus.OK);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<?> deleteCreditCard(@PathVariable String id) {
    creditCardService.deleteCreditCard(id);
    // print the id of the deleted credit card
    return new ResponseEntity<>(
      "The credit card with id " + id + " has been deleted",
      HttpStatus.OK
    );
  }
}
