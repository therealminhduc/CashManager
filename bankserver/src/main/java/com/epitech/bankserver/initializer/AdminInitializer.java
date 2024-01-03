package com.epitech.bankserver.initializer;

import com.epitech.bankserver.model.account.Account;
import com.epitech.bankserver.model.account.Admin;
import com.epitech.bankserver.repository.account.AccountRepository;
import com.epitech.bankserver.role.AccountRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class AdminInitializer implements CommandLineRunner {

  @Autowired
  private AccountRepository accountRepository;

  @Override
  public void run(String... args) throws Exception {
    if (!accountRepository.existsAccountByRole(AccountRole.ADMIN)) {
      Admin admin = new Admin();
      admin.setUsername("admin");
      admin.setAccountOwner("admin");
      admin.setRole(AccountRole.ADMIN);
      admin.setAccountNumber("000000");

      accountRepository.save(admin);
    }
  }
}
