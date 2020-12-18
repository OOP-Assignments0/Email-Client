package oop.emailApp.EmailClient.services.filters;

public class Filter {
	
	
	private static Filter instance =  null;
	private Filter() {}
	
	public static Filter getInstance() {
		if (instance==null) {
			instance = new Filter();
		}
		return instance;
	}
	
	
	
	
	
	
	
}
