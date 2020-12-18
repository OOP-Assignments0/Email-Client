package oop.emailApp.EmailClient.controller;

import org.json.JSONObject;
import org.springframework.web.bind.annotation.*;

import oop.emailApp.EmailClient.model.RunningData;
import oop.emailApp.EmailClient.services.Method;
import oop.emailApp.EmailClient.services.iterator.Handle;

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
	
	@PostMapping("/Send")
	public void Send(@RequestBody String jsonString) {
		m.Send(Handle.handleJsonMail(new JSONObject(jsonString)));
	}
	
}
