package oop.emailApp.EmailClient.services;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;


import org.json.*;

import oop.emailApp.EmailClient.model.RunningData;

public class FileMethods {

	
	
	public static void appendJsonObjectToFile(String filePath, JSONObject obj) {
		try {
			JSONArray jsonArray;
			File f1 = new File(filePath);
			if (!f1.exists()) {
				f1.createNewFile();
				jsonArray = new JSONArray();
			} else {
				jsonArray = new JSONArray(ReadFromFile(filePath));
			}
			jsonArray.put(obj);
			FileWriter fileWriter = new FileWriter(f1.getName(), true);
			BufferedWriter bw = new BufferedWriter(fileWriter);
			bw.write(jsonArray.toString());
			bw.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static String ReadFromFile(String FilePath) {
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
	
	public void appendToFile() {
		
	}

	public void appendMFile (String filePath, String Content) {
		
	}


	public void updateDelete(RunningData data) {
		JSONArray jsonArray=new JSONArray();
		for(int i = 0 ; i < data.getTrash().size() ; i++) {
			jsonArray.put(data.getTrash().get(i).dataToString());
		}
		
		JSONArray jsonArray2=new JSONArray();
		for(int i = 0 ; i < data.getTrash().size() ; i++) {
			jsonArray2.put(data.getTrash().get(i).dataToString());
		}
	}


	/*public void appendContactToFile(String filePath, Contact contact) {
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
			e.printStackTrace();
		}

	}*/


}
