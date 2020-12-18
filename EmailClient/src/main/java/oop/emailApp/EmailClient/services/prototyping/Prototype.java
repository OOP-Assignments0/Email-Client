package oop.emailApp.EmailClient.services.prototyping;


public class Prototype {
	private Prototype prototype =  null;
	private Prototype() {}
	
	public Prototype getInstance() {
		if (prototype==null) {
			prototype = new Prototype();
		}
		return prototype;
	}
	

	
	
	
	
}
