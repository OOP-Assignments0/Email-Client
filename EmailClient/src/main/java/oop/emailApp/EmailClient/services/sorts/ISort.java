package oop.emailApp.EmailClient.services.sorts;

import java.util.ArrayList;

import oop.emailApp.EmailClient.model.Mail;

public interface ISort {
	public ArrayList<Mail> Sort(ArrayList<Mail> list);
}
