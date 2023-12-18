package com.epitech.bankserver.service.transactions;

import com.epitech.bankserver.model.account.Account;
import com.epitech.bankserver.model.creditcard.CreditCard;
import com.epitech.bankserver.model.transactions.Transactions;
import com.epitech.bankserver.repository.transactions.TransactionsRepository;
import com.epitech.bankserver.service.account.AccountService;
import com.epitech.bankserver.service.creditcard.CreditCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class TransactionsService {

    @Autowired
    private TransactionsRepository transactionsRepository;

    @Autowired
    private AccountService accountService;

    @Autowired
    private CreditCardService creditCardService;

    public Transactions performTransaction(String cardNumber, float amount) {
        CreditCard creditCard = creditCardService.findByCardNumber(cardNumber);
        Account sourceAccount = accountService.findAccountByCardNumber(cardNumber);
        String sourceAccountNumber = sourceAccount.getAccountNumber();
        float sourceAccountBalance = sourceAccount.getBalance();

        deduct(sourceAccount, amount, sourceAccountBalance);

        Transactions transactions = new Transactions();
        transactions.setSourceAccountNumber(sourceAccountNumber);
        transactions.setCreditCard(creditCard);
        transactions.setAmount(amount);
        transactions.setTimestamp(LocalDateTime.now());

        return transactionsRepository.save(transactions);
    }

    public void deduct(Account account, float amount, float balance) {
        if (balance < 0) {
            throw new RuntimeException("Insufficient funds");
        }

        account.setBalance(balance - amount);
        accountService.updateAccount(account);
    }
}
