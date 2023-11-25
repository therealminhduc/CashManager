package com.epitech.bankserver.service.account;

import com.epitech.bankserver.db.DatabaseService;
import com.epitech.bankserver.model.account.Account;
import com.epitech.bankserver.repository.account.AccountRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private DatabaseService databaseService;
    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    public Account findByAccountNumber(String accountNumber) {
        return accountRepository.findAccountByAccountNumber(accountNumber);
    }

    public Account createAccount(Account account) {
        return accountRepository.save(account);
    }

    public Account updateAccount(Account account) {
        return accountRepository.save(account);
    }

    public void deleteAccount(String id) {
        accountRepository.deleteById(id);
    }


}
