package oop.emailApp.EmailClient.services.iterator;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import oop.emailApp.EmailClient.model.*;

public class Handle {

	public static Contact handleJsonContact(String jsonString) {
		Contact c = new Contact();
		JSONObject obj = new JSONObject(jsonString);
		c.setEmail((String) obj.get("email"));
		c.setName((String) obj.get("name"));
		c.setPassword((String) obj.get("password"));
		return c;
	}

	public static Mail handleJsonMail(JSONObject obj) {
		Mail m = new Mail();
		m.setFrom(obj.getString("from"));
		m.setTo(obj.getString("to"));
		m.setPriority(obj.getInt("priority"));
		m.setBody(obj.getString("body"));
		m.setSubject(obj.getString("subject"));
		m.setName(obj.getString("name"));
		m.setDate(obj.getString("date"));
		return m;
	}

	public static ArrayList<Mail> loadMailsToList(String FileContent) {
		ArrayList<Mail> arrayList = new ArrayList<Mail>();
		JSONArray arr = new JSONArray(FileContent);
		JSONObject obj;
		for (int i = 0; i < arr.length(); i++) {
			obj = (JSONObject) arr.get(i);
			arrayList.add(handleJsonMail(obj));
		}
		return arrayList;
	}

}
