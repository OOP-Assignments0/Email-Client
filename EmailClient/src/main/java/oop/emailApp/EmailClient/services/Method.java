package oop.emailApp.EmailClient.services;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.management.RuntimeErrorException;

import org.springframework.web.multipart.MultipartFile;

import oop.emailApp.EmailClient.model.Contact;
import oop.emailApp.EmailClient.model.Mail;
import oop.emailApp.EmailClient.model.RunningData;
import oop.emailApp.EmailClient.services.filters.*;
import oop.emailApp.EmailClient.services.sorts.ISort;
import oop.emailApp.EmailClient.services.sorts.SortFactory;
import oop.emailApp.EmailClient.services.search.search;

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
			FileContent = FileMethods.ReadFromFile("Users\\" + email + "\\Friends.json");
			if ((!FileContent.equalsIgnoreCase("")) && FileContent != null) {
				data.setFriends(Handle.loadContactsToList(FileContent));
			} else {
				data.getFriends().clear();
			}
			
			dictionary.put(email, data);
		} else {
			//System.out.println("INCORRECT USEREMAIL OR PASSWORD");
			throw new RuntimeErrorException(null, "INCORRECT USER EMAIL OR PASSWORD");
		}
	}

	public static void SignUp(String email, String name, String password) {
		System.out.println("i'm in sign up");
		RunningData data = new RunningData();
		if (!UserFound(email)) {
			Contact c = new Contact(email, name, password);
			Contact.getContacts().add(c);
			data.setCurrentContact(c);
			FileMethods.updateFileContentWithContactsList("Users\\Contacts.json",Contact.getContacts());
			dictionary.put(email, data);
		} else {
			//System.out.println("USER IS ALREADY FOUND");
			throw new RuntimeErrorException(null, "USER IS ALREADY FOUND");
			//SignIn(email, password);
		}
	}
	
	public static ArrayList<Mail> getMails(String Email,String targetfolder) {
		ArrayList<Mail> newMails = new ArrayList<Mail>();
		switch(targetfolder) {
		case "Inbox":
			newMails = dictionary.get(Email).getInbox();
			break;
		case "Send":
			newMails = dictionary.get(Email).getSend();
			break;
		case "Draft":
			newMails = dictionary.get(Email).getDraft();
			break;
		case "Trash":
			newMails = dictionary.get(Email).getTrash();
			break;
		}
		return newMails;
	}

	public static void loadContacts() {
		String FileContent = FileMethods.ReadFromFile("Users\\Contacts.json");
		Contact.setContacts(Handle.loadContactsToList(FileContent));
	}

	public static boolean UserFound(String email) {
		boolean User = false;
		ArrayList<Contact> contacts = Contact.getContacts();
		for (int i = 0; i < contacts.size(); i++) {
			if (contacts.get(i).getEmail().equalsIgnoreCase(email)) {
				User = true;
				break;
			}
		}
		return User;
	}
	
	///delete current contact 
	public static boolean SetCurrentUser(String email, String password, RunningData data) {
		boolean User = false;
		ArrayList<Contact> contacts = Contact.getContacts();
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
	
	public static void AddFriend(String Useremail,String FriendEmail) {
		if (!UserFound(FriendEmail)) {
			throw new RuntimeErrorException(null, "INVALID FRIEND EMAIL");
		}
		ArrayList<Contact> contacts = Contact.getContacts();
		for (int i = 0; i < contacts.size(); i++) {
			if (contacts.get(i).getEmail().equalsIgnoreCase(FriendEmail)) {
				dictionary.get(Useremail).getFriends().add(contacts.get(i));
				FileMethods.updateFileContentWithContactsList("Users\\" + Useremail + "\\Friends.json", dictionary.get(Useremail).getFriends());
			}
		}
	}
	
	public static void DeleteFriend(String Useremail,String FriendEmail) {
		ArrayList<Contact> UserFriends = dictionary.get(Useremail).getFriends();
		for (int i = 0; i < UserFriends.size(); i++) {
			if (UserFriends.get(i).getEmail().equalsIgnoreCase(FriendEmail)) {
				UserFriends.remove(i);
				FileMethods.updateFileContentWithContactsList("Users\\" + Useremail + "\\Friends.json", UserFriends);
			}
		}
	}
	
	
	public static ArrayList<Contact> getFriends(String Useremail) {
		return dictionary.get(Useremail).getFriends();
	}
	
	public static Contact getContact(String Useremail) {
		Contact c = new Contact();
		for (int i = 0 ; i <Contact.getContacts().size();i++) {
			if (Contact.getContacts().get(i).getEmail().equalsIgnoreCase(Useremail)) {
				c = Contact.getContacts().get(i);
			}
		}
		return c;
	}
	
	public static void ModifyContact(String Email,String UserName,String Password) {
		ArrayList<Contact> contacts = Contact.getContacts();
		for (int i = 0; i < contacts.size(); i++) {
			if (contacts.get(i).getEmail().equalsIgnoreCase(Email)) {
				contacts.get(i).setName(UserName);
				contacts.get(i).setPassword(Password);
				FileMethods.updateFileContentWithContactsList("Users\\Contacts.json",Contact.getContacts());
			}
		}
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
		default:
			return list;
		}
		return filter.meetFilter(list, Word);
	}
	
	public static ArrayList<Mail> Sorting(String SortType, String Useremail, String targetFolder) {
		ISort sort = SortFactory.sortMethod( SortType);
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
		default:
			return list;
		}
		return sort.Sort(list);
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

	public static void addMail(Mail m) throws IOException {
		// create folder and get its pass
		String path = "C:\\test";
		// put attachments in that pass
		saveAttachments(m.getFile(), path);
		// clear file from Mail
		m.setFile(null);
		// use function send
		Send(m);
	}
	
	private static Path root = null;
	public static void saveAttachments(MultipartFile[] file, String path) throws IOException {
		if(file.length == 0)
			return;
		root = Paths.get(path);
		for(int i=0; i<file.length; i++)
			Files.copy(file[i].getInputStream(), Method.root.resolve(file[i].getOriginalFilename()));
	}
	
	//public static void Send(Mail mail) throws IOException {


	
	public static void Send(Mail mail) {
		RunningData data = dictionary.get(mail.getFrom());
		/*boolean enter = false;
		for(int i = 0 ; i < Contact.getContacts().size() ; i++) {
			if(Contact.getContacts().get(i).getEmail().equalsIgnoreCase(mail.getTo())) {
				enter = true;
			}
		}*/
		if(UserFound(mail.getTo())) {
			if (mail.getTo().equalsIgnoreCase(mail.getFrom())) {
				throw new RuntimeException("Send to yourself");
			}
		
		mail.setFolder("Inbox");

		SimpleDateFormat format = new SimpleDateFormat("dd:MM:yyyy HH:mm:ss"); 
		Date date = new  Date();
		mail.setDate(format.format(date));
		String InboxPath = "Users" + "\\" + mail.getTo() + "\\" + "Inbox\\" + mail.getName();
		mail.setName(FileMethods.CreateFolder(InboxPath)); // mail name may be updated here
		InboxPath = "Users" + "\\" + mail.getTo() + "\\" + "Inbox\\" + mail.getName();
		//saveAttachments(mail.getFile(), InboxPath);
		
		String path = "Users" + "\\" + mail.getTo() + "\\" + "Inbox\\Inbox.json";
		FileMethods.appendJsonObjectToFile(path, mail.dataToString());
		
		// create folder for attachments
		String SendPath = "Users" + "\\" + mail.getFrom() + "\\" + "Send\\" + mail.getName();
		mail.setName(FileMethods.CreateFolder(SendPath));
		SendPath = "Users" + "\\" + mail.getFrom() + "\\" + "Send\\" + mail.getName();
		//saveAttachments(mail.getFile(), SendPath);
		//clear attachments from mail object
		mail.setFile(null);
		
		Mail m = mail.copy();
		m.setFolder("Send");
 		data.getSend().add(m);
		if (dictionary.containsKey(mail.getTo())) {
			dictionary.get(mail.getTo()).getInbox().add(mail);
		}
		FileMethods.updateSend(data);}
		else {
			throw new RuntimeException("Invalid Receiver");
		}
	}
	
	private static ArrayList<Mail> targetDelete(String targetFolder,RunningData data) {
		if(targetFolder.equalsIgnoreCase("Inbox")) {
			return data.getInbox();
		}else if (targetFolder.equalsIgnoreCase("Send")) {
			return data.getSend();
		}else if (targetFolder.equalsIgnoreCase("Draft")) {
			return data.getDraft();
		}
		return null;
	}
	public static void Delete(Mail mail, String Useremail, String targetFolder) {
		if(targetFolder.equalsIgnoreCase("Trash")) {
			removeFromTrash(mail, Useremail);
		}
		else {
		RunningData data = dictionary.get(Useremail);
		String SourcePath = "Users" + "\\" + Useremail + "\\" + targetFolder+"\\" + mail.getName();
		String TargetPath = "Users" + "\\" + Useremail + "\\" + "Trash\\" + mail.getName();
		String name = FileMethods.checkFile(TargetPath);
		TargetPath = "Users" + "\\" + Useremail + "\\" + "Trash\\" + name;
		ArrayList<Mail> targetDelete = targetDelete(targetFolder, data);
		for (int i = 0; i < targetDelete.size(); i++) {
			if (targetDelete.get(i).getName().equalsIgnoreCase(mail.getName())) {
				Mail m = targetDelete.get(i).copy();
				targetDelete.remove(i);
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
		FileMethods.update(targetDelete, targetFolder, Useremail);
		FileMethods.updateTrash(data);}
	}
	
	public static void removeFromTrash(Mail mail, String Useremail) {
		RunningData data = dictionary.get(Useremail);
		
		String SourcePath = "Users" + "\\" + Useremail + "\\" + "Trash"+"\\" + mail.getName();
		for (int i = 0; i < data.getTrash().size(); i++) {
			if (data.getTrash().get(i).getName().equalsIgnoreCase(mail.getName())) {
				data.getTrash().remove(i);
				break;
			}
		}
		File source = new File(SourcePath);
		FileMethods.delete(source);
		FileMethods.updateTrash(data);
	}
	/*
	public static void Delete(Mail mail) {
		RunningData data = dictionary.get(mail.getTo());

		String SourcePath = "Users" + "\\" + mail.getTo() + "\\" + "Inbox\\" + mail.getName();
		String TargetPath = "Users" + "\\" + mail.getTo() + "\\" + "Trash\\" + mail.getName();
		String name = FileMethods.checkFile(TargetPath);
		TargetPath = "Users" + "\\" + mail.getTo() + "\\" + "Trash\\" + name;
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
	}*/
	
	public static void Restore(Mail mail, String Useremail) {
		RunningData data = dictionary.get(Useremail);
		 
		String targetFolder = mail.getFolder();
		String SourcePath = "Users" + "\\" + Useremail + "\\" + "Trash\\" + mail.getName();
		String TargetPath = "Users" + "\\" + Useremail + "\\" + targetFolder+"\\" + mail.getName();
		String name = FileMethods.checkFile(TargetPath);
		TargetPath = "Users" + "\\" + Useremail + "\\" + targetFolder+"\\"  + name;
		ArrayList<Mail> targetDelete = targetDelete(targetFolder, data);
		for (int i = 0; i < data.getTrash().size(); i++) {
			if (data.getTrash().get(i).getName().equalsIgnoreCase(mail.getName())) {
				Mail m = data.getTrash().get(i).copy();
				data.getTrash().remove(i);
				m.setName(name);
				targetDelete.add(m);
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
		FileMethods.update(targetDelete, targetFolder, Useremail);
		FileMethods.updateTrash(data);
	}
	/*
	public static void Restore(Mail mail) {
		RunningData data = dictionary.get(mail.getTo());

		String SourcePath = "Users" + "\\" + mail.getTo() + "\\" + "Trash\\" + mail.getName();
		String TargetPath = "Users" + "\\" + mail.getTo() + "\\" + "Inbox\\" + mail.getName();
		String name = FileMethods.checkFile(TargetPath);
		TargetPath = "Users" + "\\" + mail.getTo() + "\\" + "Inbox\\" + name;
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
	}*/

	public static void Draft(Mail mail) throws IOException {
		RunningData data = dictionary.get(mail.getFrom());
		// create folder for attachments
		String DraftPath = "Users" + "\\" + mail.getFrom() + "\\" + "Draft\\" + mail.getName();
		mail.setName(FileMethods.CreateFolder(DraftPath));
		DraftPath = "Users" + "\\" + mail.getFrom() + "\\" + "Draft\\" + mail.getName();
		saveAttachments(mail.getFile(), DraftPath);
		
		mail.setFolder("Draft");
		SimpleDateFormat format = new SimpleDateFormat("dd:MM:yyyy HH:mm:ss"); 
		Date date = new  Date();
		mail.setDate(format.format(date));
		data.getDraft().add(mail);
		FileMethods.updateDraft(data);
	}
	
	public static ArrayList<Mail> search(String str, String region, String emailPart, String Useremail) {
		RunningData data = dictionary.get(Useremail);
		search s = new search();
		return s.searchForString(str, region, emailPart, data);
	}

}
