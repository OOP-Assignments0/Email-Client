package oop.emailApp.EmailClient.services.sorts;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;

import oop.emailApp.EmailClient.model.Mail;

public class DateSort implements ISort{
	/*
	 * var customDate = moment().format('DD:MM:YYYY hh:mm:ss');
     * console.log(customDate);
	 */
	@SuppressWarnings("unchecked")
	public ArrayList<Mail> Sort(ArrayList<Mail> list) {
		ArrayList<Mail> Sorted = (ArrayList<Mail>) list.clone() ;
		Collections.sort(Sorted,new Comparator<Mail>() {
			public int compare(Mail m1,Mail m2) {
				String MailDate1 = m1.getDate();
				String MailDate2 = m2.getDate();
				SimpleDateFormat format = new SimpleDateFormat("dd:MM:yyyy HH:mm:ss");
				Date date1 = null;
				Date date2 = null;
				try {
					date1 = (Date) format.parse(MailDate1);
					date2 = (Date) format.parse(MailDate2);
				} catch (ParseException e) {
					e.printStackTrace();
				}
				return date1.compareTo(date2);
			}
		});
		return Sorted;
	}

}
