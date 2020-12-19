package oop.emailApp.EmailClient.services.filters;

import java.util.ArrayList;

import oop.emailApp.EmailClient.model.Mail;

public abstract class Filter {
	
	public abstract ArrayList<Mail> meetFilter(ArrayList<Mail> list,String Word); 
	
}
