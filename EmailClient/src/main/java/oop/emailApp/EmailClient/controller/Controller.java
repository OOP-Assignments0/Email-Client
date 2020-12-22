package oop.emailApp.EmailClient.controller;

import java.io.IOException;
import java.util.ArrayList;

import org.json.JSONArray;
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
		try {
			System.out.println("send");
			Method.Send(Handle.handleJsonMail(new JSONObject(jsonString)));
			return "true";
		}catch(Exception e) {
			return e.getMessage();
		}
	}
	
	@PostMapping("/SendAll")
	public void uploadFiles(@ModelAttribute Mail mail) throws IOException {
		System.out.println(mail.getFrom());
		System.out.println(mail.getTo());
		//Method.addMail(mail);
		Method.saveAttachments(mail.getFile(), "C:\\test");
	}
	
	@PostMapping("/Draft")
	public String Draft(@RequestBody String jsonString) {
		try {
			System.out.println("send");
			Method.Draft(Handle.handleJsonMail(new JSONObject(jsonString)));
			return "true";
		}catch(Exception e) {
			return e.getMessage();
		}
	}
	@PostMapping("/Delete")
	public String Delete(@RequestBody String jsonString) {
		try {
			JSONArray arr = new JSONArray(jsonString);
			/*System.out.println("\n\n\n"+arr.getJSONObject(0).getJSONObject("mail"));

			System.out.println(arr.getJSONObject(1).getString("email"));
			System.out.println(arr.getJSONObject(2).getString("targetFolder"));*/
			Method.Delete(Handle.handleJsonMail(arr.getJSONObject(0).getJSONObject("mail")),arr.getJSONObject(1).getString("email"),arr.getJSONObject(2).getString("targetFolder"));
			/*JSONObject obj = new JSONObject(jsonString);
			System.out.println("\n\n\n"+obj.getJSONObject("mail").toString());
			System.out.println(obj.getString("email"));
			System.out.println(obj.getString("targetFolder"));
			Method.Delete(Handle.handleJsonMail(obj.getJSONObject("mail")),obj.getString("email"),obj.getString("targetFolder"));*/
			return "true";
		}catch(Exception e) {
			return e.getMessage();
		}
	}
	@PostMapping("/Restore")
	public String Restore(@RequestBody String jsonString) {
		try {
			JSONArray arr = new JSONArray(jsonString);
			Method.Restore(Handle.handleJsonMail(arr.getJSONObject(0).getJSONObject("mail")),arr.getJSONObject(1).getString("email"));
			return "true";
		}catch(Exception e) {
			return e.getMessage();
		}
	}

	@PostMapping("/Filter")
	public String filter(@RequestBody String jsonString) {
		JSONObject obj = new JSONObject(jsonString);
		ArrayList<Mail> list = Method.Filter(obj.getString("filterType"), obj.getString("email"),
				obj.getString("targetFolder"), obj.getString("Word"));
		return Handle.mailListToJsonArray(list).toString();
	}
	
	@PostMapping("/Sort")
	public String sort(@RequestBody String jsonString) {
		JSONObject obj = new JSONObject(jsonString);
		ArrayList<Mail> list = Method.Sorting(obj.getString("SortType"), obj.getString("email"),
				obj.getString("targetFolder"));
		return Handle.mailListToJsonArray(list).toString();
	}
	
	@PostMapping("/GetEmails")
	public String getEmails(@RequestBody String jsonString) {
		System.out.println(jsonString);
		JSONObject obj = new JSONObject(jsonString);
		System.out.println(Handle.mailListToJsonArray(Method.getMails(obj.getString("email"),obj.getString("targetFolder"))).toString());
		return Handle.mailListToJsonArray(Method.getMails(obj.getString("email"),obj.getString("targetFolder"))).toString();
	}
	
	
	
	@GetMapping("/Search")
	public String Search(@RequestBody String jsonString) {

		return "true";
	}
}
