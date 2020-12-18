package oop.emailApp.EmailClient.controller;

import org.springframework.web.bind.annotation.*;

import oop.emailApp.EmailClient.model.RunningData;
import oop.emailApp.EmailClient.services.Method;

@CrossOrigin
@RestController
public class PostController {

	RunningData data = new RunningData();
	Method m = new Method(data);

	@PostMapping("/SignUp")
	public void SignUp(@RequestBody String jsonString) {
		m.SignUp(jsonString);
	}

	@PostMapping("/SignIn")
	public void SignIn(@RequestBody String jsonString) {
		m.SignIn(jsonString);
	}
	
}
