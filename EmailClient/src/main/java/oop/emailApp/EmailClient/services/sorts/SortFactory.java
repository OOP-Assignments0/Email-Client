package oop.emailApp.EmailClient.services.sorts;

public class SortFactory {
	public static ISort sortMethod(String sort) {
		switch (sort) {
		case "Sender":
			return new SortSender();
		case "Receiver":
			return  new SortReceiver();
		case "Body":
			return new BodySort();
		case "Subject":
			return  new SortSubject();
		case "Importance":
			return new ImportanceSort();
		case "Date":
			return  new DateSort();
		}
		return null;
	}
}
