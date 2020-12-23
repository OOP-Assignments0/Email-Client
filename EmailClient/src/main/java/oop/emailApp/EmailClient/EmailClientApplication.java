package oop.emailApp.EmailClient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import oop.emailApp.EmailClient.services.Method;

@SpringBootApplication
public class EmailClientApplication {

	public static void main(String[] args) {
		Method.loadContacts();
		
		/*for (int i = 0 ; i < Contact.getContacts().size();i++) {
			System.out.println(Contact.getContacts().get(i).ContactTOJsonObject().toString());
		}*/
		SpringApplication.run(EmailClientApplication.class, args);
	}

}
