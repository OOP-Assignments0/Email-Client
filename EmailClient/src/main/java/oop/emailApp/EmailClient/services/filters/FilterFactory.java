package oop.emailApp.EmailClient.services.filters;

public class FilterFactory {

	public static Filter filterMethod(String filter) {
		switch (filter) {
		case "Sender":
			return SenderFilter.getInstance();
		case "Subject":
			return  SubjectFilter.getInstance();
		}
		return null;
	}
}
