package com.epitech.bankserver.controller.transactions;

import com.epitech.bankserver.model.creditcard.CreditCard;
import com.epitech.bankserver.model.transactions.Transactions;
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
        CreditCard creditCard = requestBody.getCreditCard();
        String cardNumber = creditCard.getCardNumber();
        float amount = requestBody.getAmount();

        if (cardNumber == null) {
            return new ResponseEntity<>(
                    "'cardNumber' does not existed",
                    HttpStatus.BAD_REQUEST
            );
        }

        Transactions transactions = transactionsService.performTransaction(cardNumber, amount);

        return new ResponseEntity<>(transactions, HttpStatus.OK);
    }
}
