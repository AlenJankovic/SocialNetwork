package com.examen.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.examen.model.Profile;
import com.examen.model.ProfileDao;

@Service
public class SearchService {
	
	@Autowired
	private ProfileDao profileDao;

	public List<Profile> search(String text){
		
		profileDao.findByInterestsName(text).stream().forEach(System.out::println);;
		
		return null;
		
	}
}
