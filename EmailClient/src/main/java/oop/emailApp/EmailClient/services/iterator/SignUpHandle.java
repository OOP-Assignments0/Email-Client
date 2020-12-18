package oop.emailApp.EmailClient.services.iterator;

import oop.emailApp.EmailClient.model.*; 

public class SignUpHandle  {
	private static SignUpHandle instance = null;

	private SignUpHandle() {
	}

	public static SignUpHandle getInstance() {
		if (instance == null) {
			instance = new SignUpHandle();
		}
		return instance;
	}


	public Contact handle(String jsonString) {
		
		return null;
	}

}
