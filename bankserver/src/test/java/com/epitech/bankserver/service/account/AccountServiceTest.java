package com.epitech.bankserver.service.account;

import com.epitech.bankserver.model.account.Account;
import com.epitech.bankserver.model.creditcard.CreditCard;
import com.epitech.bankserver.repository.account.AccountRepository;
import com.epitech.bankserver.repository.creditcard.CreditCardRepository;
import com.epitech.bankserver.service.creditcard.CreditCardService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class AccountServiceTest {

    @Test
    void createAccount() {
        AccountRepository mockAccountRepository = Mockito.mock(AccountRepository.class);
        CreditCardRepository mockCreditCardRepository = Mockito.mock(CreditCardRepository.class);
        CreditCardService mockCreditCardService = Mockito.mock(CreditCardService.class);

        AccountService accountService = new AccountService(
                mockAccountRepository,
                mockCreditCardRepository,
                mockCreditCardService
        );

        Account account = new Account(
                "John Doe",
                "12345678",
                1000
        );

        when(mockAccountRepository.save(account)).thenReturn(account);

        when(mockCreditCardService.createCreditCardWithAccountNumber(account.getAccountNumber(), account.getAccountOwner()))
                .thenReturn(new CreditCard());

        Account createdAccount = accountService.createAccount(account);

        assertNotNull(createdAccount);
        assertEquals(account.getAccountNumber(), createdAccount.getAccountNumber());
        assertEquals(account.getAccountOwner(), createdAccount.getAccountOwner());
        assertEquals(account.getBalance(), createdAccount.getBalance());
        assertEquals(account.getRole(), createdAccount.getRole());
    }
}