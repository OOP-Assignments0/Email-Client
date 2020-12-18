package oop.emailApp.EmailClient.model;

import org.json.JSONObject;

import oop.emailApp.EmailClient.services.FileMethods;

public class Contact {
	private String email,name,password;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public static void addContact(String jsonString) {
		JSONObject obj = new JSONObject(jsonString);
		FileMethods.appendJsonObjectToFile("Users\\Contacts.json", obj);
	}
	
	

}
