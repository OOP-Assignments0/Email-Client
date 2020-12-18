package oop.emailApp.EmailClient.controller;

import org.springframework.web.bind.annotation.*;

import oop.emailApp.EmailClient.model.Contact;
import oop.emailApp.EmailClient.model.RunningData;

@CrossOrigin
@RestController
public class PostController {
	
	RunningData data = new RunningData();
	@PostMapping("/signUp")
	public void SignUp(@RequestBody String jsonString) {
		Contact.addContact(jsonString);
	}

	@PostMapping("/signIn")
	public void SignIn(@RequestBody String jsonString) {
		
	}
}
