package com.examen.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.BindingResultUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.examen.model.entity.StatusUpdate;
import com.examen.service.StatusUpdateService;

@Controller
public class PageController {
	
	@Autowired
	private StatusUpdateService statusUpdateService;
	

	@RequestMapping("/")
	ModelAndView home(ModelAndView modelAndView) {
		
		StatusUpdate statusUpdate = statusUpdateService.getLatest();
		
		modelAndView.getModel().put("statusUpdate", statusUpdate);
		
		modelAndView.setViewName("app.homepage");
		
		
		return modelAndView;	
	}
	

	@RequestMapping("/about")
	String about() {
		return "app.about";
	}
	
	}


	
	
	
	
	
	
	
	/*	@RequestMapping("/")
	String home() {
		return "app.homepage";
		
	}
	
	@RequestMapping("/about")
	String about() {
		return "app.about";
	}
*/	
	


