package com.epitech.bankserver.model.creditcard;

import com.epitech.bankserver.model.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CreditCard {

    private String cardNumber;
    private String securityNumber;
    private Date expirationDate;
    private User userInfo;

}
