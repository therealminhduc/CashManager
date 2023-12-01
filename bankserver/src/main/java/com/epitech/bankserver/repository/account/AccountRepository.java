package com.epitech.bankserver.repository.account;

import com.epitech.bankserver.model.account.Account;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AccountRepository extends MongoRepository<Account, String> {

    Account findAccountByAccountNumber(String accountNumber);

    boolean existsAccountByAccountNumber(String accountNumber);

    Account deleteAccountByAccountNumber(String accountNumber);
}
