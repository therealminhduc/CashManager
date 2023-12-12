package com.epitech.bankserver;

import com.epitech.bankserver.repository.account.AccountRepository;
import io.github.cdimascio.dotenv.Dotenv;
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
        Dotenv dotenv = Dotenv.configure().load();

        String mongoDbUri = dotenv.get("MONGODB_URI");
        String mongoDbName = dotenv.get("MONGODB_NAME");

        System.setProperty("spring.data.mongodb.uri", mongoDbUri);
        System.setProperty("spring.data.mongodb.database", mongoDbName);

        SpringApplication.run(BankserverApplication.class, args);
    }

}
