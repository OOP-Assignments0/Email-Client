package oop.emailApp.EmailClient.services.builder;


public class MailBuilder implements Builder {
	
	private static MailBuilder instance =  null;
	private MailBuilder() {}
	
	public static MailBuilder getInstance() {
		if (instance==null) {
			instance = new MailBuilder();
		}
		return instance;
	}

	public void addAttachment() {
		// TODO Auto-generated method stub
		
	}

	public void addReciever() {
		// TODO Auto-generated method stub
		
	}

}
