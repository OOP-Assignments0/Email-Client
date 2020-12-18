package oop.emailApp.EmailClient.services.factroy;


public class Factory {

	private static Factory instance =  null;
	
	private Factory() {}
	
	public static Factory getInstance() {
		
		if (instance==null) {
			instance = new Factory();
		}
		return instance;
	}
	
	
	
	
	
	
}
