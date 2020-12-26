package oop.emailApp.EmailClient.services;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

import org.json.*;

import oop.emailApp.EmailClient.model.Contact;
import oop.emailApp.EmailClient.model.Mail;
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
			FileWriter fileWriter = new FileWriter(filePath, false);
			BufferedWriter bw = new BufferedWriter(fileWriter);
			bw.write(jsonArray.toString());
			bw.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public static String CreateFolder(String FilePath) {
		String old = FilePath;
		File file = new File(FilePath);
		if(!file.exists()) {
			file.mkdirs();
			String parts[] = FilePath.split("\\\\");
			return parts[parts.length-1];
		}
		int i = 1;
		FilePath = FilePath + i ;
		file = new File(FilePath);
		while (file.exists()) {
		i++;
		FilePath = old + i;
		file = new File(FilePath);
		}
		file.mkdirs();
		String parts[] = FilePath.split("\\\\");
		return parts[parts.length-1];
	}
	
	public static String checkFile(String FilePath) {
		String old = FilePath;
		File file = new File(FilePath);
		if(!file.exists()) {
			String parts[] = FilePath.split("\\\\");
			return parts[parts.length-1];
		}
		int i = 1;
		FilePath = FilePath + i ;
		file = new File(FilePath);
		while (file.exists()) {
		i++;
		FilePath = old + i;
		file = new File(FilePath);
		}
		file.mkdirs();
		String parts[] = FilePath.split("\\\\");
		return parts[parts.length-1];
	}
	
	private static void copyDirectory(File sourceDirectory, File destinationDirectory) throws IOException {
	    if (!destinationDirectory.exists()) {
	        destinationDirectory.mkdirs();
	    }
	    for (String f : sourceDirectory.list()) {
	        copyDirectoryCompatibityMode(new File(sourceDirectory, f), new File(destinationDirectory, f));
	    }
	}
	
	public static void copyDirectoryCompatibityMode(File source, File destination) throws IOException {
	    if (source.isDirectory()) {
	        copyDirectory(source, destination);
	    } else {
	        copyFile(source, destination);
	    }
	}
	
	private static void copyFile(File sourceFile, File destinationFile) 
			  throws IOException {
			    try (InputStream in = new FileInputStream(sourceFile); 
			      OutputStream out = new FileOutputStream(destinationFile)) {
			        byte[] buf = new byte[1024];
			        int length;
			        while ((length = in.read(buf)) > 0) {
			            out.write(buf, 0, length);
			        }
			    }
			}
	
	public static void delete (File sourceFile) {
		String[]entries = sourceFile.list();
		for(String s: entries){
		    File currentFile = new File(sourceFile.getPath(),s);
		    currentFile.delete();
		}
		if(sourceFile.exists()) {
			sourceFile.delete();
		}
	}
	public static String ReadFromFile(String FilePath) {
		StringBuilder str = new StringBuilder();
		try {
			File f1 = new File(FilePath);
			if (!f1.exists()) {
				return "";
			}
			@SuppressWarnings("resource")
			BufferedReader reader = new BufferedReader(new FileReader(FilePath));
			String line = null;
			while ((line = reader.readLine()) != null) {
				str.append(line);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return str.toString();
	}
	
	public static void jsonFile(String filePath,JSONArray jsonArray) {
		try {
			
			File f1 = new File(filePath);
			if (!f1.exists()) {
				f1.getParentFile().mkdirs();
				f1.createNewFile();	
			} else {
				f1.delete();
				f1.createNewFile();
			};
			FileWriter fileWriter = new FileWriter(filePath, false);
			BufferedWriter bw = new BufferedWriter(fileWriter);
			bw.write(jsonArray.toString());
			bw.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void updateInbox(RunningData data) {
		/*JSONArray jsonArray=new JSONArray();
		for(int i = 0 ; i < data.getTrash().size() ; i++) {
<<<<<<< HEAD
			jsonArray.put(data.getTrash().get(i).dataToString());
		}
		String path = "Users"+"\\"+data.getCurrentContact().getEmail()+"\\"+"Inbox\\Inbox.json";
=======
			jsonArray.put(data.getInbox().get(i).dataToString());
		}*/
		JSONArray jsonArray = JsonArrayIterator.mailListToJsonArray(data.getInbox());
		String path = "Users"+"\\"+data.getCurrentContact().getEmail()+"\\"+"Inbox\\Inbox.json";
		jsonFile(path, jsonArray);
	}
	
	
	public static void updateTrash(RunningData data) {
		/*JSONArray jsonArray=new JSONArray();
		for(int i = 0 ; i < data.getTrash().size() ; i++) {
			jsonArray.put(data.getTrash().get(i).dataToString());
<<<<<<< HEAD
		}
		String path = "Users"+"\\"+data.getCurrentContact().getEmail()+"\\"+"Trash\\Trash.json";
=======
		}*/
		JSONArray jsonArray = JsonArrayIterator.mailListToJsonArray(data.getTrash());
		String path = "Users"+"\\"+data.getCurrentContact().getEmail()+"\\"+"Trash\\Trash.json";
		jsonFile(path, jsonArray);
	}
	
	
	public static void updateDraft(RunningData data) {
		/*JSONArray jsonArray=new JSONArray();
		for(int i = 0 ; i < data.getDraft().size() ; i++) {
			jsonArray.put(data.getDraft().get(i).dataToString());
<<<<<<< HEAD
		}
		String path = "Users"+"\\"+data.getCurrentContact().getEmail()+"\\"+"Draft\\Draft.json";
=======
		}*/
		JSONArray jsonArray = JsonArrayIterator.mailListToJsonArray(data.getDraft());
		String path = "Users"+"\\"+data.getCurrentContact().getEmail()+"\\"+"Draft\\Draft.json";
		jsonFile(path, jsonArray);
	}
	public static void updateSend(RunningData data) {
		/*JSONArray jsonArray=new JSONArray();
		for(int i = 0 ; i < data.getSend().size() ; i++) {
			jsonArray.put(data.getSend().get(i).dataToString());
<<<<<<< HEAD
		}
		String path = "Users"+"\\"+data.getCurrentContact().getEmail()+"\\"+"Send\\Send.json";
=======
		}*/
		JSONArray jsonArray = JsonArrayIterator.mailListToJsonArray(data.getSend());
		String path = "Users"+"\\"+data.getCurrentContact().getEmail()+"\\"+"Send\\Send.json";
		jsonFile(path, jsonArray);
	}
	
	public static void update(ArrayList<Mail> list,String targetfolder,String Useremail) {
		JSONArray jsonArray = JsonArrayIterator.mailListToJsonArray(list);
		String path = "Users"+"\\"+Useremail+"\\"+targetfolder+"\\"+targetfolder+".json";
		jsonFile(path, jsonArray);
	}
	
	
	/*public static void updateContacts() {
		JSONArray jsonArray= new JSONArray();
		for(int i = 0 ; i < Contact.getContacts().size() ; i++) {
			jsonArray.put(Contact.getContacts().get(i).ContactTOJsonObject());
		}
		jsonFile("Users\\Contacts.json", jsonArray);
	}*/
	
	public static void updateFileContentWithContactsList(String path,ArrayList<Contact> contacts) {
		JSONArray jsonArray= new JSONArray();
		for(int i = 0 ; i < contacts.size() ; i++) {
			jsonArray.put(contacts.get(i).ContactTOJsonObject());
		}
		jsonFile(path, jsonArray);
	}

}
