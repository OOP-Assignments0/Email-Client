package oop.emailApp.EmailClient.services.iterator;

import org.json.JSONObject;

import oop.emailApp.EmailClient.model.*; 

public class Handle  {
	private static Handle instance = null;

	private Handle() {
	}

	public static Handle getInstance() {
		if (instance == null) {
			instance = new Handle();
		}
		return instance;
	}


	public static Contact handleJsonContact (String jsonString) {
		Contact c = new Contact();
		JSONObject obj = new JSONObject(jsonString);
		c.setEmail((String) obj.get("email"));
		c.setName((String) obj.get("name"));
		c.setPassword((String) obj.get("password"));
		return c;
	}

}
