package oop.emailApp.EmailClient.services.sorts;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import oop.emailApp.EmailClient.model.Mail;

public class ImportanceSort implements ISort{

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<Mail> Sort(ArrayList<Mail> list) {
		ArrayList<Mail> Sorted = (ArrayList<Mail>) list.clone() ;
		Collections.sort(Sorted,new Comparator<Mail>() {
			public int compare(Mail m1,Mail m2) {
				if(m1.getPriority() > m2.getPriority()) return -1;
				if(m1.getPriority() < m2.getPriority()) return 1;
				return 0;
			}
		});
		return Sorted;
	}

}
