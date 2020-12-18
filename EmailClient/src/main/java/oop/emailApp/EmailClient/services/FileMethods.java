package oop.emailApp.EmailClient.services;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;


import org.json.*;

import oop.emailApp.EmailClient.model.*;

public class FileMethods {

	public void appendMailToFile(String filePath, Mail mail) {

	}

	public void appendContactToFile(String filePath, Contact contact) {
		try {
			JSONArray jsonArray;
			File f1 = new File(filePath);
			if (!f1.exists()) {
				f1.createNewFile();
				jsonArray = new JSONArray();
			} else {
				jsonArray = new JSONArray(ReadFromFile(filePath));
			}
			JSONObject obj = new JSONObject("{\"email\":\"" + contact.getEmail() + "\",\"name\":\"" + contact.getName()
					+ "\",\"password\":\"" + contact.getPassword() + "\"}");
			jsonArray.put(obj);
			FileWriter fileWriter = new FileWriter(f1.getName(), true);
			BufferedWriter bw = new BufferedWriter(fileWriter);
			bw.write(jsonArray.toString());
			bw.close();

		} catch (Exception e) {

		}

	}

	public String ReadFromFile(String FilePath) {
		StringBuilder str = new StringBuilder();
		try {
			@SuppressWarnings("resource")
			BufferedReader reader = new BufferedReader(new FileReader(FilePath));
			String line = null;
			String ls = System.getProperty("line.separator");

			while ((line = reader.readLine()) != null) {
				str.append(line);
				str.append(ls);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		str.deleteCharAt(str.length() - 1);
		return str.toString();
	}

}
