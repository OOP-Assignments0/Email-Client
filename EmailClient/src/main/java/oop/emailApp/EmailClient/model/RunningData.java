package oop.emailApp.EmailClient.model;

import java.util.ArrayList;

public class RunningData {
	
	private ArrayList<Mail> Inbox = new ArrayList<Mail>();
	private ArrayList<Mail> Trash = new ArrayList<Mail>();
	private ArrayList<Mail> Draft = new ArrayList<Mail>();
	private ArrayList<Mail> Send  = new ArrayList<Mail>();
	private ArrayList<Contact> Contacts  = new ArrayList<Contact>();
	private Contact currentContact = new Contact();
	public ArrayList<Mail> getInbox() {
		return Inbox;
	}
	public void setInbox(ArrayList<Mail> inbox) {
		Inbox = inbox;
	}
	public ArrayList<Mail> getTrash() {
		return Trash;
	}
	public void setTrash(ArrayList<Mail> trash) {
		Trash = trash;
	}
	public ArrayList<Mail> getDraft() {
		return Draft;
	}
	public void setDraft(ArrayList<Mail> draft) {
		Draft = draft;
	}
	public ArrayList<Mail> getSend() {
		return Send;
	}
	public void setSend(ArrayList<Mail> send) {
		Send = send;
	}
	public Contact getCurrentContact() {
		return currentContact;
	}
	public void setCurrentContact(Contact currentContact) {
		this.currentContact = currentContact;
	}
	public ArrayList<Contact> getContacts() {
		return Contacts;
	}
	public void setContacts(ArrayList<Contact> contacts) {
		Contacts = contacts;
	}
}
