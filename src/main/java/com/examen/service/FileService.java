package com.examen.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class FileService {
	
	@Value("${photo.file.extensions}")									//file extentions in properties file
	private String imigeExtentions;
	
	private String getFileExtention(String filename) {					//getting pictures file extention 
		
		int dotPos = filename.lastIndexOf(".");							//getting dot position in filename
		
		if(dotPos < 0 ) {												//returning null if there is not dot in filename
			return null;
		}
		
		return filename.substring( dotPos + 1 ).toLowerCase();			//returning substring from dot position in lowercase
	}
	
	private boolean isImageExtention(String extention) {
		String testExtention= extention.toLowerCase();
		
		for(String validExtentions : imigeExtentions.split(","))		//getting array of extensions to loop thru
			if(testExtention.equals(validExtentions)) {					//returning true when getting match
				return true;
			}
		return false;
		
	}

}
