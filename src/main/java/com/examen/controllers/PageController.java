package com.examen.controllers;

import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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
	
	@RequestMapping("/")
	String home() {
		return "app.homepage";
		
	}
	
	@RequestMapping("/about")
	String about() {
		return "app.about";
	}
	
	@RequestMapping(value="/viewstatus", method= RequestMethod.GET)
	ModelAndView viewStatus(ModelAndView modelAndView, @RequestParam(name="p", defaultValue="1")int pageNumber) { //@RequestParam is default value of page in case if don't have som page
		
		Page<StatusUpdate> page = statusUpdateService.getPage(pageNumber);
		
		modelAndView.getModel().put("page", page);
		
		modelAndView.setViewName("app.viewStatus");
		
		return modelAndView;
		
	}
	
	@RequestMapping(value="/addstatus", method=RequestMethod.GET)
	ModelAndView addStatus(ModelAndView modelAndView, @ModelAttribute("statusUpdate")StatusUpdate statusUpdate) {
		
		modelAndView.setViewName("app.addStatus");
		
		StatusUpdate latestStatusUpdate = statusUpdateService.getLatest();
		
		modelAndView.getModel().put("latestStatusUpdate",latestStatusUpdate);
		
		return modelAndView;
	}
	
	@RequestMapping(value="/addstatus", method=RequestMethod.POST)
	
	ModelAndView addStatus(ModelAndView modelAndView, @Valid StatusUpdate statusUpdate, BindingResult result) {
		
		modelAndView.setViewName("app.addStatus");
		
		if(!result.hasErrors()) {						//if validation is ok save an and clear text box
		
		statusUpdateService.save(statusUpdate);
		modelAndView.getModel().put("statusUpdate", new StatusUpdate());
		
		}
		
		StatusUpdate latestStatusUpdate = statusUpdateService.getLatest();		//Update statusbox with latest status
		
		modelAndView.getModel().put("latestStatusUpdate",latestStatusUpdate);
		
		
		return modelAndView;
	}	

}
