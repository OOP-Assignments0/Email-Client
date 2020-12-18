package oop.emailApp.EmailClient.services.factroy;


public class Factory {

	private Factory factory =  null;
	private Factory() {}
	
	public Factory getInstance() {
		if (factory==null) {
			factory = new Factory();
		}
		return factory;
	}
	

	
	
	
	
}
