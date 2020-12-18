package oop.emailApp.EmailClient.services;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.json.simple.JSONArray;

import oop.emailApp.EmailClient.model.RunningData;

public class FileMethods {
	
	public void createDirectory (String filePath) {
		try {
		    Path path = Paths.get(filePath);
		    Files.createDirectories(path);

		  } catch (Exception e) {
		    
		  }
		
	}
	public void appendMFile (String filePath, String Content) {
		
	}
	@SuppressWarnings("unchecked")
	public void updateDelete(RunningData data) {
		JSONArray jsonArray=new JSONArray();
		for(int i = 0 ; i < data.getTrash().size() ; i++) {
			jsonArray.add(data.getTrash().get(i).dataToString());
		}
		
		JSONArray jsonArray2=new JSONArray();
		for(int i = 0 ; i < data.getTrash().size() ; i++) {
			jsonArray2.add(data.getTrash().get(i).dataToString());
		}
	}
}
