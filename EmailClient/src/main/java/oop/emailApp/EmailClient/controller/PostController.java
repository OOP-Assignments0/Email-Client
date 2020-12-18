package oop.emailApp.EmailClient.controller;

import org.springframework.web.bind.annotation.*;

import oop.emailApp.EmailClient.model.Contact;

@CrossOrigin
@RestController
public class PostController {

	@PostMapping("/signUP")
	public void SignUp(@RequestBody String jsonString) {
		Contact.addContact(jsonString);
	}

	@PostMapping("/signIn")
	public void SignIn(@RequestBody String jsonString) {
		
	}
}
