package com.examen.controllers;

import java.util.Date;

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

import com.examen.model.StatusUpdate;
import com.examen.service.StatusUpdateService;

@Controller
public class PageController {
	
	@Autowired
	private StatusUpdateService statusUpdateService;
	
	@Value("${message.error.forbidden}")
	private String accessDeniedMessage;
	
	@RequestMapping("/")
	ModelAndView home(ModelAndView modelAndView) {
		
		modelAndView.setViewName("app.homepage");
		
		StatusUpdate statusUpdate = statusUpdateService.getLatest();
		
		modelAndView.getModel().put("statusUpdate", statusUpdate);
		
		
		return modelAndView;
		
	}
	
	@RequestMapping("/about")
	String about() {
		return "app.about";
	}
	
	@RequestMapping("/403")
		ModelAndView accessDenied(ModelAndView modelAndView) {			//RequestMapping for message when token is expired
		
		modelAndView.getModel().put("message" , accessDeniedMessage);
		modelAndView.setViewName("app.message");
		
		return modelAndView;
	}
	
		

}
