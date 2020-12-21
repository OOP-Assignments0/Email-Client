package oop.emailApp.EmailClient.controller;

import java.util.ArrayList;

import org.json.JSONObject;
import org.springframework.web.bind.annotation.*;

import oop.emailApp.EmailClient.services.Method;
import oop.emailApp.EmailClient.model.Mail;
import oop.emailApp.EmailClient.services.Handle;

@CrossOrigin
@RestController
public class PostController {

	@PostMapping("/SignUp")
	public void SignUp(@RequestBody String jsonString) {
		JSONObject obj = new JSONObject(jsonString);
		Method.SignUp(obj.getString("email"), obj.getString("name"), obj.getString("password"));
	}

	@PostMapping("/SignIn")
	public void SignIn(@RequestBody String jsonString) {
		JSONObject obj = new JSONObject(jsonString);
		Method.SignIn(obj.getString("email"), obj.getString("password"));
	}

	@PostMapping("/Send")
	public void Send(@RequestBody String jsonString) {
		Method.Send(Handle.handleJsonMail(new JSONObject(jsonString)));
	}
	@PostMapping("/Delete")
	public void Delete(@RequestBody String jsonString) {
		JSONObject obj = new JSONObject(jsonString);
		Method.Delete(Handle.handleJsonMail(obj),obj.getString("email"),obj.getString("targetFolder"));
	}

	@PostMapping("/Filter")
	public String filter(@RequestBody String jsonString) {
		JSONObject obj = new JSONObject(jsonString);
		ArrayList<Mail> list = Method.Filter(obj.getString("filterType"), obj.getString("email"),
				obj.getString("targetFolder"), obj.getString("Word"));
		return Handle.mailListToJsonArray(list).toString();
	}
	
	@GetMapping("/Sort")
	public String sort(@RequestBody String jsonString) {
		JSONObject obj = new JSONObject(jsonString);
		ArrayList<Mail> list = Method.Sorting(obj.getString("SortType"), obj.getString("email"),
				obj.getString("targetFolder"));
		return Handle.mailListToJsonArray(list).toString();
	}
}
