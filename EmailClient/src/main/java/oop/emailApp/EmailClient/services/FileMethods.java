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
				f1.getParentFile().mkdirs();
				f1.createNewFile();
				jsonArray = new JSONArray();
			} else {
				jsonArray = new JSONArray(ReadFromFile(filePath));
			}
			jsonArray.put(obj);
			FileWriter fileWriter = new FileWriter(filePath, true);
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
			while ((line = reader.readLine()) != null) {
				str.append(line);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		//str.deleteCharAt(str.length() - 1);
		return str.toString();
	}
	
	public static void jsonFile(String filePath,JSONArray jsonArray) {
		try {
			
			File f1 = new File(filePath);
			if (!f1.exists()) {
				f1.createNewFile();	
			} else {
				f1.delete();
				f1.createNewFile();
			};
			FileWriter fileWriter = new FileWriter(filePath, true);
			BufferedWriter bw = new BufferedWriter(fileWriter);
			bw.write(jsonArray.toString());
			bw.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void updateInbox(RunningData data) {
		JSONArray jsonArray=new JSONArray();
		for(int i = 0 ; i < data.getTrash().size() ; i++) {
			jsonArray.put(data.getTrash().get(i).dataToString());
		}
		String path = "Users"+"\\"+data.getCurrentContact().getEmail()+"\\"+"Inbox.json";
		jsonFile(path, jsonArray);
	}
	public static void updateTrash(RunningData data) {
		JSONArray jsonArray=new JSONArray();
		for(int i = 0 ; i < data.getTrash().size() ; i++) {
			jsonArray.put(data.getTrash().get(i).dataToString());
		}
		String path = "Users"+"\\"+data.getCurrentContact().getEmail()+"\\"+"Trash.json";
		jsonFile(path, jsonArray);
	}
	public static void updateDraft(RunningData data) {
		JSONArray jsonArray=new JSONArray();
		for(int i = 0 ; i < data.getDraft().size() ; i++) {
			jsonArray.put(data.getDraft().get(i).dataToString());
		}
		String path = "Users"+"\\"+data.getCurrentContact().getEmail()+"\\"+"Draft.json";
		jsonFile(path, jsonArray);
	}
	public static void updateSend(RunningData data) {
		JSONArray jsonArray=new JSONArray();
		for(int i = 0 ; i < data.getSend().size() ; i++) {
			jsonArray.put(data.getSend().get(i).dataToString());
		}
		String path = "Users"+"\\"+data.getCurrentContact().getEmail()+"\\"+"Send.json";
		jsonFile(path, jsonArray);
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
