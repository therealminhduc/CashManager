package com.epitech.bankserver.service.account;

import com.epitech.bankserver.model.account.Account;
import com.epitech.bankserver.model.account.Admin;
import com.epitech.bankserver.repository.account.AccountRepository;
import com.epitech.bankserver.repository.account.AdminRepository;
import com.epitech.bankserver.role.AccountRole;
import java.util.List;
import java.util.Random;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class AccountService implements UserDetailsService {

  @Autowired
  private AccountRepository accountRepository;

  @Autowired
  private AdminRepository adminRepository;

  public List<Account> getAllAccounts() {
    return accountRepository.findAll();
  }

  public Account findByAccountNumber(String accountNumber) {
    return accountRepository.findAccountByAccountNumber(accountNumber);
  }

  public Account createAccount(Account account) {
    return accountRepository.save(account);
  }

  public Account createAccountWithAccountOwnerAndBalance(
    String accountOwner,
    int balance
  ) {
    Account account = new Account();
    account.setAccountOwner(accountOwner);
    account.setBalance(balance);
    account.setRole(AccountRole.CLIENT);

    String accountNumber = generateUniqueAccountNumber();
    account.setAccountNumber(accountNumber);

    return accountRepository.save(account);
  }

  public Account updateAccount(Account account) {
    return accountRepository.save(account);
  }

  public void deleteAccount(String id) {
    accountRepository.deleteById(id);
  }

  public void deleteAccountByAccountNumber(String accountNumber) {
    accountRepository.deleteAccountByAccountNumber(accountNumber);
  }

  /********************************************************************************/

  public String generateUniqueAccountNumber() {
    Random random = new Random();
    String random6DigitNumber = generateRandom6DigitNumber(random);

    while (accountRepository.existsAccountByAccountNumber(random6DigitNumber)) {
      random6DigitNumber = generateRandom6DigitNumber(random);
    }

    return random6DigitNumber;
  }

  private String generateRandom6DigitNumber(Random random) {
    StringBuilder accountNumberBuilder = new StringBuilder();
    for (int i = 0; i < 6; i++) {
      accountNumberBuilder.append(random.nextInt(10));
    }
    return accountNumberBuilder.toString();
  }

  /********************************************************************************/

  @Override
  public UserDetails loadUserByUsername(String username)
    throws UsernameNotFoundException {
    return null;
    //        Admin admin = adminRepository.findAdminByUsername(username);
    //
    //        if (admin == null) {
    //            throw new UsernameNotFoundException("Admin not found");
    //        }
    //
    //        return new User(
    //
    //        )
  }
}
