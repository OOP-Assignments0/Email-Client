package oop.emailApp.EmailClient.services;


import oop.emailApp.EmailClient.model.Mail;
import oop.emailApp.EmailClient.model.RunningData;

public class Method {
	
	private RunningData data ;
	public Method (RunningData data) {
		this.data = data;
	}
	public void Send(Mail mail) {
		data.getSend().add(mail);
		String path = "Users"+"\\"+mail.getTo()+"\\"+"Inbox.json";
		FileMethods.appendJsonObjectToFile(path, mail.dataToString());
		FileMethods.updateSend(data);
	}
	public void Delete(Mail mail) {
		for(int i = 0 ; i < data.getInbox().size() ; i++) {
			if (data.getInbox().get(i).getName() == mail.getName()) {
				Mail m = data.getInbox().get(i).copy();
				data.getInbox().remove(i);
				data.getTrash().add(m);
				break;
			}
		}
		FileMethods.updateTrash(data);
		FileMethods.updateInbox(data);
	}
	public void Restore(Mail mail) {
		for(int i = 0 ; i < data.getTrash().size() ; i++) {
			if (data.getTrash().get(i).getName() == mail.getName()) {
				Mail m = data.getTrash().get(i).copy();
				data.getTrash().remove(i);
				data.getInbox().add(m);
				break;
			}
		}
		FileMethods.updateTrash(data);
		FileMethods.updateInbox(data);
	}
	public void Draft(Mail mail) {
		data.getDraft().add(mail);
		String path = "Users"+"\\"+data.getCurrentContact().getEmail()+"\\"+"Draft.json";
		FileMethods.appendJsonObjectToFile(path, mail.dataToString());
		FileMethods.updateDraft(data);
	}
	
}
