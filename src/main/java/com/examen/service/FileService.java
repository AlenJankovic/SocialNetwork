package com.examen.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.examen.exceptions.InvalidFileException;
import com.examen.model.FileInfo;

@Service
public class FileService {
	
	@Value("${photo.file.extensions}")									//list of file extentions in properties file
	private String imigeExtentions;
	
	private Random random = new Random();
	
	
	private String getFileExtention(String filename) {					//getting pictures file extention 
		
		int dotPos = filename.lastIndexOf(".");							//getting dot position in filename
		
		if(dotPos < 0 ) {												//returning null if there is not dot in filename
			return null;
		}
		
		return filename.substring( dotPos + 1 ).toLowerCase();			//returning substring from dot position in lowercase
	}
	
	private boolean isImageExtention(String extention) {				//Checking if file extention is image extention
		String testExtention= extention.toLowerCase();
		
		for(String validExtentions : imigeExtentions.split(","))		//getting array of extensions to loop thru
			if(testExtention.equals(validExtentions)) {					//returning true when getting match
				return true;
			}
		return false;
		
	}
	
	//photo001
	
	private File makeSubDirectory(String basePath ,String prefix) {			//adding sub directories
		
		int nDirectory = random.nextInt(1000);			
		
		String sDirectory = String.format("%s%03d", prefix, nDirectory);   //making directory name
		
		File directory = new File(basePath,sDirectory);
		
		if(!directory.exists()) {											//checking if directory exist
			directory.mkdir();												//if not make dir.
		}
		 
		return directory;
	}
	
	public FileInfo saveImageFile(MultipartFile file,String baseDirectory,String subDirPrefix, String filePrefix) throws InvalidFileException, IOException {
		
		int nFileName = random.nextInt(1000);
		
		String filename = String.format("%s%03d", filePrefix, nFileName);			//make file name of filePrefix and random generated number 
	
		String fileExtention = getFileExtention(file.getOriginalFilename());		//Getting extension from original file name
			
			if(fileExtention == null) {												//if file doesn't have extension throw exception
				throw new InvalidFileException("No file extention");
			}
			
			if(isImageExtention(fileExtention) == false) {							//checking if file is a image file, if not throwing exception
				throw new InvalidFileException("Not an image file");
			}
			
				File subDirectory = makeSubDirectory(baseDirectory, subDirPrefix);			//Creating subdirectory
		
				Path filepath = Paths.get(subDirectory.getCanonicalPath() , filename +"." + fileExtention);		//making filepath
				
				Files.deleteIfExists(filepath);												//deleting if file already exist
				
				Files.copy(file.getInputStream(), filepath);								//copying and saving file
				
				
				return new FileInfo(filename,fileExtention, subDirectory.getName(), baseDirectory);    //returning file info
	}

}
