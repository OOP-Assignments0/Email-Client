package oop.emailApp.EmailClient.model;

import java.util.ArrayList;


public class Mail  {
	private String body;
	private String from;
	private String to;
	private String subject;
	private String date;
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
	public void setBody(String email) {
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
	public void setDate(String date) {
		this.date = date;
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
	public String getBody() {
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
	public String getDate() {
		return date;
	}
	public Mail copy() {
		Mail m = new Mail();
		m.attachments =(this.attachments);
		m.attachlinks = this.attachlinks;
		m.body = this.body;
		m.from = this.from;
		m.to = this.to;
		m.date= this.date;
		m.priority = this.priority;
		m.subject  = this.subject;
		m.name = this.name;
		return m ;
	}
	@SuppressWarnings("unchecked")
	public org.json.simple.JSONObject dataToString() {
		org.json.simple.JSONObject jsonObject=new org.json.simple.JSONObject();
		jsonObject.put("from",this.from);
        jsonObject.put("to",this.to);
        jsonObject.put("priority",this.priority);
        jsonObject.put("subject",this.subject);
        jsonObject.put("name",this.name);
        jsonObject.put("date",this.date);
        jsonObject.put("body",this.body);
        
		return jsonObject;
        
	}

	}
