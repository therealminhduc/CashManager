package com.epitech.bankserver.model.account;

import com.epitech.bankserver.model.account.Account;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Admin extends Account {

    private String username;
    private String password;

    public Admin(String accountOwner, String username, String password) {
        super(accountOwner);
        this.username = username;
        this.password = password;
    }


}
