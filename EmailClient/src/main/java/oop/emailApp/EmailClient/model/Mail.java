package oop.emailApp.EmailClient.model;

import org.json.JSONArray;
import org.json.JSONObject;


public class Mail  {
	private String body;
	private String from;
	private String to;
	private String subject;
	private String date;
	private String[] attachments;
	private int priority;
    private String name;
    private String folder;
	
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
	public void setAttachments(String[] attachment) {
		this.attachments = attachment.clone();
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
	public void setFolder(String folder) {
		this.folder = folder;
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
	public String[] getAttachments() {
		return attachments;
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
	public String getFolder() {
		return folder;
	}
	public Mail copy() {
		Mail m = new Mail();
		m.attachments = this.attachments;
		m.body = this.body;
		m.from = this.from;
		m.to = this.to;
		m.date= this.date;
		m.priority = this.priority;
		m.subject  = this.subject;
		m.folder = this.folder;
		m.name = this.name;
		return m ;
	}

	public JSONObject dataToString() {
		JSONObject jsonObject=new JSONObject();
		JSONArray attach = new JSONArray();
		
		jsonObject.put("from",this.from);
        jsonObject.put("to",this.to);
        jsonObject.put("priority",this.priority);
        jsonObject.put("subject",this.subject);
        jsonObject.put("name",this.name);
        jsonObject.put("date",this.date);
        jsonObject.put("body",this.body);
        jsonObject.put("folder", this.folder);
        
        /*for(int i=0; i<attachments.length; i++)
        	attach.put(attachments[i]);
        
        jsonObject.put("attachments", attach);*/
        
		return jsonObject;
        
	}
	
	// delete this later
	public static void main(String[] args) {
		Mail m = new Mail();
		m.setAttachments(new String[]{"photo","video"});
		m.setBody("body of the email");
		m.setDate("5/10/2030");
		m.setFrom("ahmed@fray.com");
		m.setName("hello");
		m.setPriority(5);
		m.setSubject("hello");
		m.setTo("ali@fray.com");
		System.out.print(m.dataToString().toString());
		
	}

	}
