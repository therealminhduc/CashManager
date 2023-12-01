package com.epitech.bankserver.model.account;

import com.epitech.bankserver.model.creditcard.CreditCard;
import com.epitech.bankserver.role.AccountRole;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document("account")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Account {

    @Id
    private String id;

    private String accountOwner;

    @Indexed(unique = true)
    private String accountNumber;

    private int balance;

    private AccountRole role;

    private CreditCard[] creditCard;

    public Account(String accountOwner) {
        this.accountOwner = accountOwner;
    }
}
