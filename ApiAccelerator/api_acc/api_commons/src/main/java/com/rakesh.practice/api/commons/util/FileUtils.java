package com.rakesh.practice.api.commons.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class FileUtils {
	
	public static String readFile(String completeFilename) {
		File file = new File(completeFilename);
		 
		  String filecontent=""; 
		  BufferedReader br;
		  try {
			br = new BufferedReader(new FileReader(file)); 
			StringBuffer sb = new StringBuffer();
			String line = "";
			while ((line = br.readLine()) != null) {
			    sb.append(line);
			  }
			filecontent = sb.toString();
			filecontent = filecontent.replaceAll("\n", "\r\n");
			br.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			filecontent = e.getMessage();
		}
			  
		return filecontent;
	}

}
