package oop.emailApp.EmailClient.services.search;

import java.util.ArrayList;

import oop.emailApp.EmailClient.model.Mail;
import oop.emailApp.EmailClient.model.RunningData;

public class searchRegion {
	
	ArrayList<Mail> result;
	boolean found = false;
	
	public ArrayList<Mail> search(
		String str, RunningData data, boolean inbox, boolean trash, boolean draft, boolean send,
		boolean from, boolean to, boolean subject, boolean body, boolean attachments
			) {
		result = new ArrayList<>();
		found = false;
		
		if(inbox)
			searchInList(str, data.getInbox(), from,to,subject,body,attachments);
		if(trash & !found)
			searchInList(str, data.getTrash(), from,to,subject,body,attachments);
		if(draft & !found)
			searchInList(str, data.getDraft(), from,to,subject,body,attachments);
		if(send & !found)
			searchInList(str, data.getSend(), from,to,subject,body,attachments);
		if(!found) {
			return null;
		}
		return result;
	}
	
	private void searchInList(String str, ArrayList<Mail> list,
			boolean from, boolean to, boolean subject, boolean body, boolean attachments
			) {
		searchEmail se = new searchEmail();
		for(int i=0; i<list.size(); i++) {
			if(se.search(str, list.get(i), from, to, subject, body, attachments)) {
				found = true;
				result.add(list.get(i).copy());
			}
		}
	}
	
	
}
