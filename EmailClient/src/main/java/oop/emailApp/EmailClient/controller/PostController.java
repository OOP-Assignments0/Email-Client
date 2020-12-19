package oop.emailApp.EmailClient.controller;

import org.json.JSONObject;
import org.springframework.web.bind.annotation.*;

import oop.emailApp.EmailClient.services.Method;
import oop.emailApp.EmailClient.services.Handle;

@CrossOrigin
@RestController
public class PostController {

	@PostMapping("/SignUp")
	public void SignUp(@RequestBody String jsonString) {
		JSONObject obj = new JSONObject(jsonString);
		Method.SignUp(obj.getString("email"),obj.getString("name"),obj.getString("password"));
	}

	@PostMapping("/SignIn")
	public void SignIn(@RequestBody String jsonString) {
		JSONObject obj = new JSONObject(jsonString);
		Method.SignIn(obj.getString("email"),obj.getString("password"));
	}
	
	@PostMapping("/Send")
	public void Send(@RequestBody String jsonString) {
		Method.Send(Handle.handleJsonMail(new JSONObject(jsonString)));
	}
	
}
