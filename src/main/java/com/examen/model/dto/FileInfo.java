package com.examen.model.dto;


public class FileInfo {
	private String basename;             
	private String extention;
	private String subDirectory;
	private String baseDirectory;
	
	
	public FileInfo(String basename, String extention, String subDirectory, String baseDirectory) {
		
		this.basename = basename;
		this.extention = extention;
		this.subDirectory = subDirectory;
		this.baseDirectory = baseDirectory;
	}


	
	public String getBasename() {
		return basename;
	}


	
	public String getExtention() {
		return extention;
	}


	
	public String getSubDirectory() {
		return subDirectory;
	}


	
	public String getBaseDirectory() {
		return baseDirectory;
	}



	public void setBasename(String basename) {
		this.basename = basename;
	}


	
	public void setExtention(String extention) {
		this.extention = extention;
	}


	
	public void setSubDirectory(String subDirectory) {
		this.subDirectory = subDirectory;
	}


	
	public void setBaseDirectory(String baseDirectory) {
		this.baseDirectory = baseDirectory;
	}



	@Override
	public String toString() {
		return "FileInfo [basename=" + basename + ", extention=" + extention + ", subDirectory=" + subDirectory
				+ ", baseDirectory=" + baseDirectory + "]";
	}
	
	
	
}
