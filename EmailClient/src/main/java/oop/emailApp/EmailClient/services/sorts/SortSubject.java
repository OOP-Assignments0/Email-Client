package oop.emailApp.EmailClient.services.sorts;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import oop.emailApp.EmailClient.model.Mail;

public class SortSubject implements ISort{

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<Mail> Sort(ArrayList<Mail> list) {
		ArrayList<Mail> Sorted = (ArrayList<Mail>) list.clone() ;
		Collections.sort(Sorted,new Comparator<Mail>() {
			public int compare(Mail m1,Mail m2) {
				String MailSubject1 = m1.getSubject();
				String MailSubject2 = m2.getSubject();
				return MailSubject1.compareTo(MailSubject2);
			}
		});
		return Sorted;
	}

}
