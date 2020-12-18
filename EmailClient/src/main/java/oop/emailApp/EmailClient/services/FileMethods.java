package oop.emailApp.EmailClient.services;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

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

}
