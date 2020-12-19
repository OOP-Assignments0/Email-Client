package oop.emailApp.EmailClient.services.filters;

import java.util.ArrayList;

import oop.emailApp.EmailClient.model.Mail;

public class SenderFilter extends Filter {
	private static SenderFilter filter = null;

	private SenderFilter() {
	}

	public static SenderFilter getInstance() {
		if (filter == null) {
			filter = new SenderFilter();
		}
		return filter;
	}

	@Override
	public ArrayList<Mail> meetFilter(ArrayList<Mail> list, String SenderEmail) {
		ArrayList<Mail> Senderlist = new ArrayList<Mail>();
		for (Mail m : list) {
			if (m.getFrom().equalsIgnoreCase(SenderEmail)) {
				Senderlist.add(m);
			}
		}
		return Senderlist;
	}

}
