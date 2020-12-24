package oop.emailApp.EmailClient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import oop.emailApp.EmailClient.services.Method;

@SpringBootApplication
public class EmailClientApplication {

	public static void main(String[] args) {
		Method.loadContacts();
		SpringApplication.run(EmailClientApplication.class, args);
	}

}
