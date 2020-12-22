package oop.emailApp.EmailClient.controller;

import java.util.ArrayList;

import org.json.JSONObject;
import org.springframework.web.bind.annotation.*;

import oop.emailApp.EmailClient.services.Method;
import oop.emailApp.EmailClient.model.Mail;
import oop.emailApp.EmailClient.services.Handle;

@CrossOrigin
@RestController
public class Controller {

	@PostMapping("/SignUp")
	public String SignUp(@RequestBody String jsonString) {
		try {
			JSONObject obj = new JSONObject(jsonString);
			Method.SignUp(obj.getString("email"), obj.getString("name"), obj.getString("password"));
			System.out.println("true");
			return "true";
		}catch(Exception e){
			System.out.println(e.getMessage());
			return e.getMessage();
		}
	}

	@PostMapping("/SignIn")
	public String SignIn(@RequestBody String jsonString) {
		try {
			JSONObject obj = new JSONObject(jsonString);
			Method.SignIn(obj.getString("email"), obj.getString("password"));
			
			return "true";
		}catch(Exception e){
			return e.getMessage();
		}	
	}

	@PostMapping("/Send")
	public String Send(@RequestBody String jsonString) {
		System.out.println("send");
		Method.Send(Handle.handleJsonMail(new JSONObject(jsonString)));
		return "true";
		/*try {
			
		}catch(Exception e) {
			return e.getMessage();
		}*/
	}
	@PostMapping("/Delete")
	public void Delete(@RequestBody String jsonString) {
		JSONObject obj = new JSONObject(jsonString);
		Method.Delete(Handle.handleJsonMail(obj),obj.getString("email"),obj.getString("targetFolder"));
	}

	@GetMapping("/Filter")
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
	
	@GetMapping("/GetEmails")
	public String getEmails(@RequestBody String jsonString) {
		JSONObject obj = new JSONObject(jsonString);
		return Handle.mailListToJsonArray(Method.getMails(obj.getString("email"),obj.getString("targetFolder"))).toString();
	}
	@GetMapping("/Search")
	public String Search(@RequestBody String jsonString) {

		return "true";
	}
}