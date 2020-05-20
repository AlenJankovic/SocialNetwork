package com.examen.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.examen.model.dto.SearchResult;
import com.examen.model.entity.Profile;
import com.examen.model.repository.ProfileDao;

@Service
public class SearchService {
	
	@Value("${results.pagesize}")
	private int pageSize;	
	
	@Autowired
	private ProfileDao profileDao;

	public Page<SearchResult> search(String text, int pageNumber){
		
		PageRequest request = PageRequest.of(pageNumber -1, pageSize);
		
		Page<Profile> results =  profileDao.findByInterestsNameContainingIgnoreCase(text, request);      
		
		Converter<Profile, SearchResult> converter = new Converter<Profile, SearchResult>(){	//Converting page of Profile to page of SearchResult

			@Override
			public SearchResult convert(Profile profile) {
					return new SearchResult(profile);
			}
			
		};
		return results.map(p -> new SearchResult(p));
		
	}
}
