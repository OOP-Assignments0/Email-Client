package oop.emailApp.EmailClient.services.iterator;

import oop.emailApp.EmailClient.model.*; 

public class Handle  {
	private static Handle instance = null;

	private Handle() {
	}

	public static Handle getInstance() {
		if (instance == null) {
			instance = new Handle();
		}
		return instance;
	}


	public Contact handle(String jsonString) {
		
		return null;
	}

}
