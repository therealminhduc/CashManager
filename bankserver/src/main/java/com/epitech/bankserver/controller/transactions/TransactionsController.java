package com.epitech.bankserver.controller.transactions;

import com.epitech.bankserver.model.transactions.Transactions;
import com.epitech.bankserver.service.transactions.TransactionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/transaction")
public class TransactionsController {

    @Autowired
    private TransactionsService transactionsService;


    @PostMapping
    public ResponseEntity<?> performTransaction(@RequestBody Map<String, Object> requestBody) {
        String cardNumber = (String) requestBody.get("cardNumber");
        int amount = (int) requestBody.get("amount");

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
