package com.epitech.bankserver;

import com.epitech.bankserver.repository.account.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@Controller
public class BankserverApplication {

    public static void main(String[] args) {
        SpringApplication.run(BankserverApplication.class, args);
    }

}
