package com.epitech.bankserver.controller.account;

import com.epitech.bankserver.model.account.Account;
import com.epitech.bankserver.service.account.AccountService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

  @Autowired
  private AccountService accountService;

  @GetMapping
  public ResponseEntity<List<Account>> getAllAccounts() {
    List<Account> accounts = accountService.getAllAccounts();
    return new ResponseEntity<>(accounts, HttpStatus.OK);
  }

  @GetMapping("/{accountnumber}")
  public ResponseEntity<Account> getAccountByAccountNumber(
    @PathVariable String accountnumber
  ) {
    Account account = accountService.findByAccountNumber(accountnumber);

    if (account != null) {
      return new ResponseEntity<>(account, HttpStatus.OK);
    } else {
      return new ResponseEntity<>(account, HttpStatus.NOT_FOUND);
    }
  }

  //  we can set this admin-only request
  //    @PostMapping
  //    public ResponseEntity<Account> createAccount(@RequestBody Account account) {
  //        Account createdAccount = accountService.createAccount(account);
  //        return new ResponseEntity<>(createdAccount, HttpStatus.CREATED);
  //    }

  @PostMapping
  public ResponseEntity<?> createAccountWithAccountOwnerAndBalance(
    @RequestBody Map<String, Object> requestBody
  ) {
    String accountOwner = (String) requestBody.get("accountOwner");
    Integer balance = (Integer) requestBody.get("balance");

    if (accountOwner == null || balance == null) {
      return new ResponseEntity<>(
        "Both 'accountOwner' and 'balance' must be provided",
        HttpStatus.BAD_REQUEST
      );
    }

    Account createdAccount = accountService.createAccountWithAccountOwnerAndBalance(
      accountOwner,
      balance
    );
    return new ResponseEntity<>(createdAccount, HttpStatus.CREATED);
  }

  @PutMapping("/{id}")
  public ResponseEntity<Account> updateAccount(
    @PathVariable String id,
    @RequestBody Account account
  ) {
    if (!id.equals(account.getId())) {
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    Account updateAccount = accountService.updateAccount(account);
    return new ResponseEntity<>(updateAccount, HttpStatus.OK);
  }

  @DeleteMapping("/{accountNumber}")
  public ResponseEntity<Void> deleteAccountByAccountNumber(
    @PathVariable String accountNumber
  ) {
    accountService.deleteAccountByAccountNumber(accountNumber);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }
}
