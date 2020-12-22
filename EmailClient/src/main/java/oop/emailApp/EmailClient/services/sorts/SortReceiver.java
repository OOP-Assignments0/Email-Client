package oop.emailApp.EmailClient.services.sorts;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import oop.emailApp.EmailClient.model.Mail;

public class SortReceiver implements ISort{

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<Mail> Sort(ArrayList<Mail> list) {
		ArrayList<Mail> Sorted = (ArrayList<Mail>) list.clone() ;
		Collections.sort(Sorted,new Comparator<Mail>() {
			public int compare(Mail m1,Mail m2) {
				String MailReceiver1 = m1.getTo();
				String MailReceiver2 = m2.getTo();
				return MailReceiver1.compareTo(MailReceiver2);
			}
		});
		Collections.reverse(Sorted);
		return Sorted;
	}

}
