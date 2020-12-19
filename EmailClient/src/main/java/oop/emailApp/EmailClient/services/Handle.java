package oop.emailApp.EmailClient.services;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import oop.emailApp.EmailClient.model.*;

public class Handle {

	public static Contact handleJsonContact(JSONObject obj) {
		Contact c = new Contact();
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
		if ((!FileContent.equalsIgnoreCase(""))&&FileContent!=null) {
			try {
				JSONArray arr = new JSONArray(FileContent);
				JSONObject obj;
				for (int i = 0; i < arr.length(); i++) {
					obj = (JSONObject) arr.get(i);
					arrayList.add(handleJsonMail(obj));
				}

			} catch (Exception e) {
				System.out.println("Cannot load mails");
			}
		}
		return arrayList;
	}
	
	public static ArrayList<Contact> loadContactsToList(String FileContent) {
		ArrayList<Contact> arrayList = new ArrayList<Contact>();
		if ((!FileContent.equalsIgnoreCase(""))&&FileContent!=null){
			try {
				JSONArray arr = new JSONArray(FileContent);
				JSONObject obj;
				for (int i = 0; i < arr.length(); i++) {
					obj = (JSONObject) arr.get(i);
					arrayList.add(handleJsonContact(obj));
				}

			} catch (Exception e) {
				System.out.println("Cannot load contacts");
			}
		}
		return arrayList;
	}
	
	public static JSONArray mailListToJsonArray (ArrayList<Mail> list) {
		JSONArray jsonArray=new JSONArray();
		for(int i = 0 ; i < list.size() ; i++) {
			jsonArray.put(list.get(i).dataToString());
		}
		return jsonArray;
	}

}
