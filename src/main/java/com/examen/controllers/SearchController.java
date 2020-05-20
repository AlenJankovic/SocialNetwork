package com.examen.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.examen.model.dto.SearchResult;
import com.examen.service.SearchService;

@Controller
public class SearchController {
	
	@Autowired
	SearchService searchService;
	
	@RequestMapping(value ="/search", method = {RequestMethod.POST, RequestMethod.GET})			//handling both POST and GET
	public ModelAndView search(ModelAndView modelAndView , @RequestParam("s") String text ,@RequestParam(name="p", defaultValue="1") int pageNumber){
		
		Page<SearchResult>results = searchService.search(text,pageNumber);
		
		modelAndView.getModel().put("s",text);
		modelAndView.getModel().put("page", results);
		modelAndView.setViewName("app.search");
		
		return modelAndView;
			}

}
