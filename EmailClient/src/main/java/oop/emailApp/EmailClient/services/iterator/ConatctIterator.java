package oop.emailApp.EmailClient.services.iterator;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import oop.emailApp.EmailClient.model.*;

public class ConatctIterator {

	public static Contact handleJsonContact(JSONObject obj) {
		Contact c = new Contact();
		c.setEmail((String) obj.getString("email"));
		c.setName((String) obj.getString("name"));
		c.setPassword(obj.getString("password"));
		return c;
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
	
	
	

}
