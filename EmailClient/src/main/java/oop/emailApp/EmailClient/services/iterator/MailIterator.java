package oop.emailApp.EmailClient.services.iterator;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import oop.emailApp.EmailClient.model.Mail;

public class MailIterator {
	
	
	public static Mail handleJsonMail(JSONObject obj) {
		Mail m = new Mail();
		m.setFrom(obj.getString("from"));
		m.setTo(obj.getString("to"));
		m.setPriority(obj.getInt("priority"));
		m.setBody(obj.getString("body"));
		m.setSubject(obj.getString("subject"));
		m.setName(obj.getString("name"));
		m.setDate(obj.getString("date"));
		m.setFolder(obj.getString("folder"));
		try {
			JSONArray arr = obj.getJSONArray("attachments");
			List<String> list = new ArrayList<String>();
			for(int i = 0; i < arr.length(); i++){
			    list.add(arr.get(i).toString());
			}
			m.setAttachments(list.toArray(String[]::new));
			
		}catch(org.json.JSONException e) {
			System.out.println(e.getMessage()+" error at Mailiterator.handleJsonMail");
		}
		
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

}
