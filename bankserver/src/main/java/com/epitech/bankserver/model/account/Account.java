package com.epitech.bankserver.model.account;

import com.epitech.bankserver.model.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Account {

    private String accountNumber;
    private int balance;
    private User user;
    private String[] creditCard;

}
