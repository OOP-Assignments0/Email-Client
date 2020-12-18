package oop.emailApp.EmailClient.model;

import java.util.ArrayList;

public class Mail {
	private String[] body;
	private String from;
	private String to;
	private String subject;
	private ArrayList<Object> attachments = new ArrayList<Object>();
	private String attachlinks[];
	private int priority;
    private String name;
	
	public void setFrom(String sender) {
		this.from = sender;
	}
	public void setTo(String reciever) {
		this.to = reciever;
	}
	public void setSubject(String sub) {
		this.subject = sub;
	}
	public void setBody(String[] email) {
		this.body = email;
	}
	public void setAttachments(ArrayList<Object> attachment) {
		this.attachments = attachment;
	}
	public void setAttaclinks(String attach[]) {
		this.attachlinks=attach;
	}
	public void setPriority(int priority) {
		this.priority = priority;
	}
	public void setName(String name) {
		this.name = name;
	}

	
	public String getFrom() {
		return from;
	}
	public String getTo() {
		return to;
	}
	public String getSubject() {
		return subject;
	}
	public String[] getBody() {
		return body;
	}
	public ArrayList<Object> getAttachments() {
		return attachments;
	}
	public String[] getAttaclinks() {
		return attachlinks;
	}
	public int getPriority() {
		return priority;
	}
	public String getName() {
		return name;
	}
}
