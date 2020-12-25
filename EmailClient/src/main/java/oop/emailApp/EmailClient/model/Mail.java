package oop.emailApp.EmailClient.model;

import org.json.JSONObject;
import org.springframework.web.multipart.MultipartFile;


public class Mail  {
	private String body;
	private String from;
	private String to;
	private String subject;
	private String date;
	private String attachmentsPath;
	private MultipartFile[] file;
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
	public void setAttachments(String path) {
		this.attachmentsPath = System.getProperty("user.dir") + "\\" + path;
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
	public MultipartFile[] getFile() {
		return file;
	}
	public void setFile(MultipartFile[] file) {
		this.file = file;
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
	public String getAttachmentsPath() {
		return attachmentsPath;
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
		m.attachmentsPath = this.attachmentsPath;
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
		
		jsonObject.put("from",this.from);
        jsonObject.put("to",this.to);
        jsonObject.put("priority",this.priority);
        jsonObject.put("subject",this.subject);
        jsonObject.put("name",this.name);
        jsonObject.put("date",this.date);
        jsonObject.put("body",this.body);
        jsonObject.put("folder", this.folder);
        jsonObject.put("attachments", this.attachmentsPath);
        
		return jsonObject;
        
	}
	
	// delete this later
	public static void main(String[] args) {
		Mail m = new Mail();
		m.setBody("body of the email");
		m.setDate("5/10/2030");
		m.setFrom("ahmed@fray.com");
		m.setName("hello");
		m.setPriority(5);
		m.setSubject("hello");
		m.setTo("ali@fray.com");
		System.out.println(m.dataToString().toString());
		System.out.println(System.getProperty("user.dir"));
		
	}

	}
