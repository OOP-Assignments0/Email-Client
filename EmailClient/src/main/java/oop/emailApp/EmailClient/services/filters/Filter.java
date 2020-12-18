package oop.emailApp.EmailClient.services.filters;

public class Filter {
	private Filter filter =  null;
	private Filter() {}
	
	public Filter getInstance() {
		if (filter==null) {
			filter = new Filter();
		}
		return filter;
	}
	
	
	
	
	
	
	
}
