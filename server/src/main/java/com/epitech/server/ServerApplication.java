package com.epitech.server;

import com.epitech.server.model.Basket;
import com.epitech.server.model.User;
import com.epitech.server.service.UserService;
import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ServerApplication {
	public static void main(String[] args) {
		Dotenv dotenv = Dotenv.configure().load();

		String mongoDbUri = dotenv.get("MONGODB_URI");
		String mongoDbName = dotenv.get("MONGODB_NAME");

		System.setProperty("spring.data.mongodb.uri", mongoDbUri);
		System.setProperty("spring.data.mongodb.database", mongoDbName);

		SpringApplication.run(ServerApplication.class, args);
	}
}
