package oop.emailApp.EmailClient.controller;



import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import oop.emailApp.EmailClient.services.Method;
import oop.emailApp.EmailClient.model.Mail;
import oop.emailApp.EmailClient.services.JsonArrayIterator;
import oop.emailApp.EmailClient.services.MailIterator;

import org.springframework.web.bind.annotation.RestController;


@CrossOrigin
@RestController
public class Controller {

	@PostMapping(value = "/download/{file_name}")
	public @ResponseBody ResponseEntity<Resource> getFile(@RequestBody String path, @PathVariable String file_name) throws IOException {
		JSONObject obj = new JSONObject(path);
		File file = new File(obj.getString("path") +"\\"+ file_name);
		InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
		HttpHeaders headers = new HttpHeaders();
        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
        headers.add("Pragma", "no-cache");
        headers.add("Expires", "0");
		return ResponseEntity.ok()
	            .headers(headers)
	            .contentLength(file.length())
	            .contentType(MediaType.APPLICATION_OCTET_STREAM)
	            .body(resource);
	}
	
	@PostMapping("/SignUp")
	public String SignUp(@RequestBody String jsonString) {
		try {
			JSONObject obj = new JSONObject(jsonString);
			Method.SignUp(obj.getString("email"), obj.getString("name"), obj.getString("password"));
			System.out.println("true");
			return "true";
		}catch(Exception e){
			//System.out.println(e.getMessage());
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

	// delete this function later
	@PostMapping("/SendOld")
	public String SendOld(@RequestBody String jsonString) {
		try {
			System.out.println("send");
			Method.Send(MailIterator.handleJsonMail(new JSONObject(jsonString)));
			return "true";
		}catch(Exception e) {
			return e.getMessage();
		}
	}
	
	@PostMapping("/Send")
	public String Send(@ModelAttribute Mail mail) {
		try {
			Method.Send(mail);
			return "true";
		}catch(Exception e) {
			return e.getMessage();
		}
	}
	
	// delete this function later
	@PostMapping("/DraftOld")
	public String DraftOld(@RequestBody String jsonString) {
		try {
			System.out.println("send");
			Method.Draft(MailIterator.handleJsonMail(new JSONObject(jsonString)));
			return "true";
		}catch(Exception e) {
			return e.getMessage();
		}
	}
	
	@PostMapping("/Draft")
	public String Draft(@ModelAttribute Mail mail) {
		try {
			Method.Draft(mail);
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
			Method.Delete(MailIterator.handleJsonMail(arr.getJSONObject(0).getJSONObject("mail")),arr.getJSONObject(1).getString("email"),arr.getJSONObject(2).getString("targetFolder"));
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
			Method.Restore(MailIterator.handleJsonMail(arr.getJSONObject(0).getJSONObject("mail")),arr.getJSONObject(1).getString("email"));
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
		return JsonArrayIterator.mailListToJsonArray(list).toString();
	}
	
	@PostMapping("/Sort")
	public String sort(@RequestBody String jsonString) {
		JSONObject obj = new JSONObject(jsonString);
		ArrayList<Mail> list = Method.Sorting(obj.getString("SortType"), obj.getString("email"),
				obj.getString("targetFolder"));
		return JsonArrayIterator.mailListToJsonArray(list).toString();
	}
	
	@PostMapping("/GetEmails")
	public String getEmails(@RequestBody String jsonString) {
		//System.out.println(jsonString);
		JSONObject obj = new JSONObject(jsonString);
		//System.out.println(Handle.mailListToJsonArray(Method.getMails(obj.getString("email"),obj.getString("targetFolder"))).toString());
		return JsonArrayIterator.mailListToJsonArray(Method.getMails(obj.getString("email"),obj.getString("targetFolder"))).toString();
	}
	
	
	@PostMapping("/Search")
	public String Search(@RequestBody String jsonString) {
		JSONObject obj = new JSONObject(jsonString);
		 ArrayList<Mail> result = Method.search(obj.getString("str"), obj.getString("region"), obj.getString("emailPart"), obj.getString("Useremail"));
		return JsonArrayIterator.mailListToJsonArray(result).toString();
	}
	
	@PostMapping("/AddFriend")
	public String AddFriend(@RequestBody String jsonString) {
		try {
			JSONObject obj = new JSONObject(jsonString);
			Method.AddFriend(obj.getString("Useremail"), obj.getString("FriendEmail"));
			return "true";
		}catch(Exception e) {
			return e.getMessage();
		}
	}
	
	@PostMapping("/DeleteFriend")
	public String DeleteFriend(@RequestBody String jsonString) {
		try {
			JSONObject obj = new JSONObject(jsonString);
			Method.DeleteFriend(obj.getString("Useremail"), obj.getString("FriendEmail"));
			return "true";
		}catch(Exception e) {
			return e.getMessage();
		}
	}
	
	@PostMapping("/GetFriends")
	public String GetFriends(@RequestBody String jsonString) {
		//System.out.println(jsonString);
		JSONObject obj = new JSONObject(jsonString);
		return JsonArrayIterator.ContactListToJsonArray(Method.getFriends(obj.getString("email"))).toString();
	}
	
	@PostMapping("/GetContact")
	public String GetContact(@RequestBody String jsonString) {
		JSONObject obj = new JSONObject(jsonString);
		return Method.getContact(obj.getString("email")).ContactTOJsonObject().toString();
	}
	
	@PostMapping("/ModifyContact")
	public String ModifyContact(@RequestBody String jsonString) {
		try {
			JSONObject obj = new JSONObject(jsonString);
			Method.ModifyContact(obj.getString("email"), obj.getString("name"), obj.getString("password"));
			return "true";
		}catch(Exception e){
			//System.out.println(e.getMessage());
			return e.getMessage();
		}
	}
	
	@PostMapping("/RemoveDraft")
	public String RemoveDraft(@RequestBody String jsonString) {
		try {
			JSONArray arr = new JSONArray(jsonString);
			System.out.println(jsonString);
			Method.removeFromDraft(MailIterator.handleJsonMail(arr.getJSONObject(0).getJSONObject("mail")),arr.getJSONObject(1).getString("email"));
			return "true";
		}catch(Exception e) {
			return e.getMessage();
		}
	}


	
	
}
