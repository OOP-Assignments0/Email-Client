package oop.emailApp.EmailClient.services;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import oop.emailApp.EmailClient.model.Contact;
import oop.emailApp.EmailClient.model.Mail;
import oop.emailApp.EmailClient.model.RunningData;
import oop.emailApp.EmailClient.services.filters.*;

public class Method {

	// private static RunningData data = new RunningData();
	private static Map<String, RunningData> dictionary = new HashMap<String, RunningData>();

	private Method() {
	}
	/*
	 * public Method(RunningData data) { this.data = data; }
	 */

	public static void SignIn(String email, String password) {
		RunningData data = new RunningData();
		loadContacts(email, data);
		if (SetCurrentUser(email, password, data)) {
			String FileContent = FileMethods.ReadFromFile("Users\\" + email + "\\Inbox\\Inbox.json");
			if ((!FileContent.equalsIgnoreCase("")) && FileContent != null) {
				data.setInbox(Handle.loadMailsToList(FileContent));
			} else {
				data.getInbox().clear();
			}
			FileContent = FileMethods.ReadFromFile("Users\\" + email + "\\Draft\\Draft.json");
			if ((!FileContent.equalsIgnoreCase("")) && FileContent != null) {
				data.setDraft(Handle.loadMailsToList(FileContent));
			} else {
				data.getDraft().clear();
			}
			FileContent = FileMethods.ReadFromFile("Users\\" + email + "\\Send\\Send.json");
			if ((!FileContent.equalsIgnoreCase("")) && FileContent != null) {
				data.setSend(Handle.loadMailsToList(FileContent));
			} else {
				data.getSend().clear();
			}
			FileContent = FileMethods.ReadFromFile("Users\\" + email + "\\Trash\\Trash.json");
			if ((!FileContent.equalsIgnoreCase("")) && FileContent != null) {
				data.setTrash(Handle.loadMailsToList(FileContent));
			} else {
				data.getTrash().clear();
			}
			dictionary.put(email, data);
		} else {
			System.out.println("INCORRECT USEREMAIL OR PASSWORD");
		}
	}

	public static void SignUp(String email, String name, String password) {
		RunningData data = new RunningData();
		loadContacts(email, data);
		if (!UserFound(email, data)) {
			Contact c = new Contact(email, name, password);
			data.getContacts().add(c);
			data.setCurrentContact(c);
			FileMethods.updateContacts(data);
			dictionary.put(email, data);
		} else {
			System.out.println("USER IS ALREADY FOUND");
			SignIn(email, password);
		}
	}

	private static void loadContacts(String email, RunningData data) {
		String FileContent = FileMethods.ReadFromFile("Users\\Contacts.json");
		data.setContacts(Handle.loadContactsToList(FileContent));
	}

	public static boolean UserFound(String email, RunningData data) {
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

	public static ArrayList<Mail> Filter(String filterType, String Useremail, String targetFolder, String Word) {
		Filter filter = FilterFactory.filterMethod(filterType);
		ArrayList<Mail> list = new ArrayList<Mail>();
		switch (targetFolder) {
		case "Inbox":
			list = dictionary.get(Useremail).getInbox();
			break;
		case "Draft":
			list = dictionary.get(Useremail).getDraft();
			break;
		case "Trash":
			list = dictionary.get(Useremail).getTrash();
			break;
		case "Send":
			list = dictionary.get(Useremail).getSend();
			break;
		default :
			return list;
		}
		return filter.meetFilter(list, Word);
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

	public static boolean SetCurrentUser(String email, String password, RunningData data) {
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
		RunningData data = dictionary.get(mail.getFrom());

		String InboxPath =  "Users" + "\\" + mail.getTo() + "\\" + "Inbox\\"+mail.getName();
		mail.setName(FileMethods.CreateFolder(InboxPath));
		
		String path = "Users" + "\\" + mail.getTo() + "\\" + "Inbox\\Inbox.json";

		FileMethods.appendJsonObjectToFile(path, mail.dataToString());
		
		String SendPath =  "Users" + "\\" + mail.getFrom() + "\\" + "Inbox\\"+mail.getName();
		mail.setName(FileMethods.CreateFolder(SendPath));
		data.getSend().add(mail);
		FileMethods.updateSend(data);
	}

	public static void Delete(Mail mail) {
		RunningData data = dictionary.get(mail.getTo());
		
		String SourcePath = "Users" + "\\" + mail.getTo() + "\\" + "Inbox\\"+mail.getName();
		String TargetPath = "Users" + "\\" + mail.getTo() + "\\" + "Trash\\"+mail.getName();
		String name = FileMethods.checkFile(TargetPath);
		TargetPath = "Users" + "\\" + mail.getTo() + "\\" + "Trash\\" +name;
		for (int i = 0; i < data.getInbox().size(); i++) {
			if (data.getInbox().get(i).getName().equalsIgnoreCase(mail.getName())) {
				Mail m = data.getInbox().get(i).copy();
				data.getInbox().remove(i);
				m.setName(name);
				data.getTrash().add(m);
				break;
			}
		}
		File source = new File(SourcePath);
		File target = new File(TargetPath);
		try {
			FileMethods.copyDirectoryCompatibityMode(source, target);
		} catch (IOException e) {
			e.printStackTrace();
		}
		FileMethods.delete(source);
		FileMethods.updateTrash(data);
		FileMethods.updateInbox(data);
	}

	public static void Restore(Mail mail) {
		RunningData data = dictionary.get(mail.getTo());
		

		String SourcePath = "Users" + "\\" + mail.getTo() + "\\" + "Trash\\"+mail.getName();
		String TargetPath = "Users" + "\\" + mail.getTo() + "\\" + "Inbox\\"+mail.getName();
		String name = FileMethods.checkFile(TargetPath);
		TargetPath = "Users" + "\\" + mail.getTo() + "\\" + "Inbox\\" +name;
		for (int i = 0; i < data.getTrash().size(); i++) {
			if (data.getTrash().get(i).getName().equalsIgnoreCase(mail.getName())) {
				Mail m = data.getTrash().get(i).copy();
				data.getTrash().remove(i);
				m.setName(name);
				data.getInbox().add(m);
				break;
			}
		}
		File source = new File(SourcePath);
		File target = new File(TargetPath);
		try {
			FileMethods.copyDirectoryCompatibityMode(source, target);
		} catch (IOException e) {
			e.printStackTrace();
		}
		FileMethods.updateTrash(data);
		FileMethods.updateInbox(data);
	}

	public static void Draft(Mail mail) {
		RunningData data = dictionary.get(mail.getFrom());
		String DraftPath =  "Users" + "\\" + mail.getFrom() + "\\" + "Draft\\"+mail.getName();
		mail.setName(FileMethods.CreateFolder(DraftPath));
		data.getDraft().add(mail);
		FileMethods.updateDraft(data);
	}

}
