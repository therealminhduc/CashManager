package com.epitech.bankserver.model.transactions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Transactions {

    @Id
    private String id;
    private String sourceAccountNumber;
    private int amount;
    private LocalDateTime timestamp;
}
