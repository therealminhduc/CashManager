package com.epitech.bankserver.model.account;

import com.epitech.bankserver.model.account.Account;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@NoArgsConstructor
@Getter @Setter
public class Admin extends Account {

    @Field("adminUsername")
    private String username;

    @Field("adminPassword")
    private String password;

    public Admin(String accountOwner, String username, String password) {
        super(accountOwner);
        this.username = username;
        this.password = password;
    }


}
