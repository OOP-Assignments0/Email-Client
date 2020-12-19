package oop.emailApp.EmailClient.services;

import oop.emailApp.EmailClient.model.Contact;
import oop.emailApp.EmailClient.model.Mail;
import oop.emailApp.EmailClient.model.RunningData;

public class Method {

	private RunningData data;

	public Method(RunningData data) {
		this.data = data;
	}

	public void SignIn(String jsonString) {
		/*this.data.setCurrentContact(Handle.handleJsonContact(jsonString));
		String fileContent = FileMethods
				.ReadFromFile("Users\\" + this.data.getCurrentContact().getEmail() + "\\Inbox.json");
		this.data.setInbox(Handle.loadMailsToList(fileContent));
		fileContent = FileMethods.ReadFromFile("Users\\" + this.data.getCurrentContact().getEmail() + "\\Trash.json");
		this.data.setTrash(Handle.loadMailsToList(fileContent));
		fileContent = FileMethods.ReadFromFile("Users\\" + this.data.getCurrentContact().getEmail() + "\\Draft.json");
		this.data.setDraft(Handle.loadMailsToList(fileContent));
		fileContent = FileMethods.ReadFromFile("Users\\" + this.data.getCurrentContact().getEmail() + "\\Send.json");
		this.data.setSend(Handle.loadMailsToList(fileContent));*/
	}

	public void SignUp(String jsonString) {
		Contact.addContact(jsonString);
		//this.data.setCurrentContact(Handle.handleJsonContact(jsonString));
	}

	public void Send(Mail mail) {
		data.getSend().add(mail);
		String path = "Users" + "\\" + mail.getTo() + "\\" + "Inbox.json";
		FileMethods.appendJsonObjectToFile(path, mail.dataToString());
		FileMethods.updateSend(data);
	}

	public void Delete(Mail mail) {
		for (int i = 0; i < data.getInbox().size(); i++) {
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
		for (int i = 0; i < data.getTrash().size(); i++) {
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
		String path = "Users" + "\\" + data.getCurrentContact().getEmail() + "\\" + "Draft.json";
		FileMethods.appendJsonObjectToFile(path, mail.dataToString());
		FileMethods.updateDraft(data);
	}

}
