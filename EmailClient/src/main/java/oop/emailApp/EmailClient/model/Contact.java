package oop.emailApp.EmailClient.model;

import java.util.ArrayList;

import org.json.JSONObject;


public class Contact {
	private String email,name,password;
	private static ArrayList<Contact> Contacts  = new ArrayList<Contact>();
	
	public Contact() {}
	public Contact(String email,String name,String password) {
		this.email=email;
		this.name=name;
		this.password=password;
	}

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
	
	public  JSONObject ContactTOJsonObject() {
		JSONObject obj = new JSONObject();
		obj.put("email", this.getEmail());
		obj.put("name", this.getName());
		obj.put("password", this.getPassword());
		return obj;
	}
	public static ArrayList<Contact> getContacts() {
		return Contacts;
	}
	public static void setContacts(ArrayList<Contact> contacts) {
		Contacts = contacts;
	}
	
	

}
