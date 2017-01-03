package com.chicago.labs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
public class CoffeeTalksApplication {

	public static void main(String[] args) {
		SpringApplication.run(CoffeeTalksApplication.class, args);
	}
}
