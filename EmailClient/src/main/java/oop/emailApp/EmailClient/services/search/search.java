package oop.emailApp.EmailClient.services.search;

import java.util.ArrayList;

import oop.emailApp.EmailClient.model.Mail;
import oop.emailApp.EmailClient.model.RunningData;

public class search {
	public ArrayList<Mail> searchForString(String str, String region, String emailPart, RunningData data) {
		boolean inbox = false, trash = false, draft=false, send=false;
		switch(region) {
			case "Inbox": inbox = true; break;
			case "Trash": trash = true; break;
			case "Draft": draft = true; break;
			case "Send": send = true; break;
			case "all":
				inbox=true; trash=true; draft=true; send=true;
		}
		
		boolean from=false, to=false, subject=false, body=false, attachments=false;
		switch(emailPart) {
			case "from": from = true; break;
			case "to": to = true; break;
			case "subject": subject = true; break;
			case "body": body = true; break;
			case "attachments": attachments = true; break;
			case "all":
				from=true; to=true; subject=true; body=true; attachments=true; break;
		}
		searchRegion ser = new searchRegion();
		return ser.search(str, data, inbox, trash, draft, send, from, to, subject, body, attachments);
	}
}
