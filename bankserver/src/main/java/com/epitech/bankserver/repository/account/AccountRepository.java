package com.epitech.bankserver.repository.account;

import com.epitech.bankserver.model.account.Account;
import com.epitech.bankserver.model.account.Admin;
import com.epitech.bankserver.role.AccountRole;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AccountRepository extends MongoRepository<Account, String> {

    Account findAccountByAccountNumber(String accountNumber);

    Account findAccountByAccountOwner(String accountOwner);

    boolean existsAccountByAccountNumber(String accountNumber);

    Account deleteAccountByAccountNumber(String accountNumber);

    boolean existsAccountByRole(AccountRole accountRole);
}
