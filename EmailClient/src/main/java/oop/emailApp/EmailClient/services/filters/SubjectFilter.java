package oop.emailApp.EmailClient.services.filters;

import java.util.ArrayList;

import oop.emailApp.EmailClient.model.Mail;

public class SubjectFilter extends Filter {
	private static SubjectFilter filter = null;

	private SubjectFilter() {
	}
	
	public static SubjectFilter getInstance() {
		if (filter == null) {
			filter = new SubjectFilter();
		}
		return filter;
	}
	
	
	@Override
	public ArrayList<Mail> meetFilter(ArrayList<Mail> list, String Word) {
		ArrayList<Mail> Subjectlist = new ArrayList<Mail>();
		for (Mail m : list) {
			if (checkWord(m.getSubject(), Word)) {
				Subjectlist.add(m);
			}
		}
		return Subjectlist;
	}

	protected boolean checkWord(String Subject, String Word) {
		/*String[] words = Subject.split("\\s+");
		for (String w : words) {
			if (w.equalsIgnoreCase(Word)) {
				return true;
			}
		}*/
		if (Subject.contains(Word)) return true;
		return false;
	}
}
