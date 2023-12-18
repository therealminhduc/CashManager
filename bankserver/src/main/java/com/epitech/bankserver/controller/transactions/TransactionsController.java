package com.epitech.bankserver.controller.transactions;

import com.epitech.bankserver.model.account.Account;
import com.epitech.bankserver.model.creditcard.CreditCard;
import com.epitech.bankserver.model.transactions.Transactions;
import com.epitech.bankserver.repository.creditcard.CreditCardRepository;
import com.epitech.bankserver.service.account.AccountService;
import com.epitech.bankserver.service.creditcard.CreditCardService;
import com.epitech.bankserver.service.transactions.TransactionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.Map;

@RestController
@RequestMapping("/api/transaction")
public class TransactionsController {

    @Autowired
    private TransactionsService transactionsService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private CreditCardService creditCardService;

    @Autowired
    private CreditCardRepository creditCardRepository;

    public static class transactionRequest {
        private CreditCard creditCard;
        private float amount;

        public transactionRequest() {}

        public CreditCard getCreditCard() {
            return creditCard;
        }

        public void setCreditCard(CreditCard creditCard) {
            this.creditCard = creditCard;
        }

        public float getAmount() {
            return amount;
        }

        public void setAmount(float amount) {
            this.amount = amount;
        }
    }


    @PostMapping
    public ResponseEntity<?> performTransaction(@RequestBody transactionRequest requestBody) {
        CreditCard creditCardOnRequest = requestBody.getCreditCard();
        String cardNumberOnRequest = creditCardOnRequest.getCardNumber();
        String securityNumberOnRequest = creditCardOnRequest.getSecurityNumber();
        String cardOwnerOnRequest = creditCardOnRequest.getCardOwner();

        float amount = requestBody.getAmount();

        CreditCard creditCard = creditCardService.findByCardNumber(cardNumberOnRequest);
        Account account = accountService.findAccountByCardNumber(cardNumberOnRequest);


        if (!creditCardRepository.existsCreditCardByCardNumber(cardNumberOnRequest) || !creditCardRepository.existsCreditCardBySecurityNumber(securityNumberOnRequest) || !creditCardRepository.existsCreditCardByCardOwner(cardOwnerOnRequest)) {
            return new ResponseEntity<>(
                    "Something wrong with your information",
                    HttpStatus.BAD_REQUEST
            );
        } else if (account.getBalance() - amount < 0 ) {
            return new ResponseEntity<>(
                    "Insufficient funds",
                    HttpStatus.PAYMENT_REQUIRED
            );
        } else {
            Transactions transactions = transactionsService.performTransaction(cardNumberOnRequest, amount);
            return new ResponseEntity<>("Transaction done successfully", HttpStatus.OK);
        }
    }
}
