package com.epitech.bankserver.model.transactions;

import com.epitech.bankserver.model.creditcard.CreditCard;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Document("transactions")
@AllArgsConstructor
@NoArgsConstructor
public class Transactions {

    @Id
    private String id;
    private String sourceAccountNumber;
    @DBRef
    private CreditCard creditCard;
    private float amount;
    private LocalDateTime timestamp;
}
