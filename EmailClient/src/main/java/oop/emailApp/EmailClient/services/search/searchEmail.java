package oop.emailApp.EmailClient.services.search;

import oop.emailApp.EmailClient.model.Mail;

public class searchEmail {
	
	public boolean search(
		String str, Mail email, boolean from, boolean to, boolean subject, boolean body, boolean attachments) {
		
		boolean found = false;
		if(from)
			found = searchString(str, email.getFrom());
		if(to && !found)
			found = searchString(str, email.getTo());
		if(subject && !found)
			found = searchString(str, email.getSubject());
		if(body && !found)
			found = searchString(str, email.getBody());
		if(attachments && !found)
			found = searchAttachments(str, "bla bla");
		return found;
	}
	
	private boolean searchString(String str, String body) {
		if (body.indexOf(str)==-1)
			return false;
		return true;
	}
	
	private boolean searchAttachments(String str, String email) {
		return false;
	}
	
	// delete this later
	public static void main(String[] args) {
		Mail email = new Mail();
		email.setFrom("ahmed@fray.com");
		email.setTo("ali@fray.com");
		email.setBody("hello\nmy name is ahmed");
		email.setSubject("hello message");
		searchEmail s = new searchEmail();
		boolean se = s.search("my name is ahmed", email, true, true, true, true, false);
		System.out.print(se);
	}

}
