package oop.emailApp.EmailClient.services.iterator;

import java.util.ArrayList;

import org.json.JSONArray;

import oop.emailApp.EmailClient.model.Contact;
import oop.emailApp.EmailClient.model.Mail;

public class JsonArrayIterator {
	
	public static JSONArray mailListToJsonArray (ArrayList<Mail> list) {
		JSONArray jsonArray=new JSONArray();
		if(list == null)
			return jsonArray.put("");
		for(int i = 0 ; i < list.size() ; i++) {
			jsonArray.put(list.get(i).dataToString());
		}
		return jsonArray;
	}
	
	public static JSONArray ContactListToJsonArray (ArrayList<Contact> list) {
		JSONArray jsonArray=new JSONArray();
		if(list == null)
			return jsonArray.put("");
		for(int i = 0 ; i < list.size() ; i++) {
			jsonArray.put(list.get(i).ContactTOJsonObject());
		}
		return jsonArray;
	}

}
