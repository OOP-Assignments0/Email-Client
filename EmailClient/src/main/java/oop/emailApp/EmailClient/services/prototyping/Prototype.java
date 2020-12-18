package oop.emailApp.EmailClient.services.prototyping;


public class Prototype {
	
	private static Prototype instance =  null;
	private Prototype() {}
	
	public static Prototype getInstance() {
		if (instance==null) {
			instance = new Prototype();
		}
		return instance;
	}
	

	
	
	
	
}
