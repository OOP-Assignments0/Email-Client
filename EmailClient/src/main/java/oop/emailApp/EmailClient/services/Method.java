package oop.emailApp.EmailClient.services;

import java.util.ArrayList;

import oop.emailApp.EmailClient.model.Contact;
import oop.emailApp.EmailClient.model.Mail;
import oop.emailApp.EmailClient.model.RunningData;

public class Method {

	private static RunningData data = new RunningData();
	
	private Method() {}
	/*public Method(RunningData data) {
		this.data = data;
	}*/

	public static void SignIn(String email, String password) {
		loadContacts();
		if (SetCurrentUser(email, password)) {
			String FileContent = FileMethods.ReadFromFile("Users\\" + email + "\\Inbox.json");
			if ((!FileContent.equalsIgnoreCase("")) && FileContent != null) {
				data.setInbox(Handle.loadMailsToList(FileContent));
			}else {
				data.getInbox().clear();
			}
			FileContent = FileMethods.ReadFromFile("Users\\" + email + "\\Draft.json");
			if ((!FileContent.equalsIgnoreCase("")) && FileContent != null) {
				data.setDraft(Handle.loadMailsToList(FileContent));
			}else {
				data.getDraft().clear();
			}
			FileContent = FileMethods.ReadFromFile("Users\\" + email + "\\Send.json");
			if ((!FileContent.equalsIgnoreCase("")) && FileContent != null) {
				data.setSend(Handle.loadMailsToList(FileContent));
			}else {
				data.getSend().clear();
			}
			FileContent = FileMethods.ReadFromFile("Users\\" + email + "\\Trash.json");
			if ((!FileContent.equalsIgnoreCase("")) && FileContent != null) {
				data.setTrash(Handle.loadMailsToList(FileContent));
			}else {
				data.getTrash().clear();
			}
		} else {
			System.out.println("INCORRECT USEREMAIL OR PASSWORD");
		}
	}

	public static void SignUp(String email, String name, String password) {
		loadContacts();
		if (!UserFound(email)) {
			Contact c = new Contact(email, name, password);
			data.getContacts().add(c);
			data.setCurrentContact(c);
			FileMethods.updateContacts(data);
		} else {
			System.out.println("USER IS ALREADY FOUND");
			SignIn(email, password);
		}
	}

	private static void loadContacts() {
		String FileContent = FileMethods.ReadFromFile("Users\\Contacts.json");
		data.setContacts(Handle.loadContactsToList(FileContent));
	}

	public static boolean UserFound(String email) {
		boolean User = false;
		ArrayList<Contact> contacts = data.getContacts();
		for (int i = 0; i < contacts.size(); i++) {
			if (contacts.get(i).getEmail().equalsIgnoreCase(email)) {
				User = true;
				break;

			}
		}
		return User;
	}
	/*
	 * public void loadInbox(String email) { String FileContent =
	 * FileMethods.ReadFromFile("Users\\" + email + "\\Inbox.json"); if
	 * ((!FileContent.equalsIgnoreCase("")) && FileContent != null) {
	 * this.data.setInbox(Handle.loadMailsToList(FileContent)); } }
	 * 
	 * public void loadSend(String email) { String FileContent =
	 * FileMethods.ReadFromFile("Users\\" + email + "\\Send.json"); if
	 * ((!FileContent.equalsIgnoreCase("")) && FileContent != null) {
	 * this.data.setSend(Handle.loadMailsToList(FileContent)); } }
	 * 
	 * public void loadDraft(String email) { String FileContent =
	 * FileMethods.ReadFromFile("Users\\" + email + "\\Draft.json"); if
	 * ((!FileContent.equalsIgnoreCase("")) && FileContent != null) {
	 * this.data.setDraft(Handle.loadMailsToList(FileContent)); } }
	 * 
	 * public void loadTrash(String email) { String FileContent =
	 * FileMethods.ReadFromFile("Users\\" + email + "\\Trash.json"); if
	 * ((!FileContent.equalsIgnoreCase("")) && FileContent != null) {
	 * this.data.setTrash(Handle.loadMailsToList(FileContent)); } }
	 */

	public static boolean SetCurrentUser(String email, String password) {
		boolean User = false;
		ArrayList<Contact> contacts = data.getContacts();
		for (int i = 0; i < contacts.size(); i++) {
			if (contacts.get(i).getEmail().equalsIgnoreCase(email)) {
				if (contacts.get(i).getPassword().equalsIgnoreCase(password)) {
					data.setCurrentContact(contacts.get(i));
					User = true;
					break;
				}
			}
		}
		return User;
	}

	public static void Send(Mail mail) {
		data.getSend().add(mail);
	    String path = "Users" + "\\" + mail.getTo() + "\\" + "Inbox.json";
		FileMethods.appendJsonObjectToFile(path, mail.dataToString());
		FileMethods.updateSend(data);
	}

	public static void Delete(Mail mail) {
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

	public static void Restore(Mail mail) {
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

	public static void Draft(Mail mail) {
		data.getDraft().add(mail);
	    //String path = "Users" + "\\" + data.getCurrentContact().getEmail() + "\\" +
		//"Draft.json";
		//FileMethods.appendJsonObjectToFile(path, mail.dataToString());
		FileMethods.updateDraft(data);
	}

}
