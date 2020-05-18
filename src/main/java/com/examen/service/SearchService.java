package com.examen.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.examen.model.dto.SearchResult;
import com.examen.model.entity.Profile;
import com.examen.model.repository.ProfileDao;

@Service
public class SearchService {
	
	@Autowired
	private ProfileDao profileDao;

	public List<SearchResult> search(String text){
		
		return profileDao.findByInterestsName(text).stream().map(SearchResult::new).collect(Collectors.toList());       //turning objects(Profile) in list to another type object(SearchResult)
																												 			//collecting in List
		
		
	}
}
