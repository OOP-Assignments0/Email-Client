package oop.emailApp.EmailClient.services;

import oop.emailApp.EmailClient.model.Contact;
import oop.emailApp.EmailClient.services.iterator.Handle;

public class SignUpMethods {

	public static void signUp(String jsonString) {
		Contact c = Handle.handleJsonContact(jsonString);
		FileMethods.ReadFromFile("Users\\");
	}
}
