package com.examen.controllers;


import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ProfileController {
	
	@RequestMapping(value="/profile")
	public ModelAndView showeProfile(ModelAndView modelAndView) {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication(); //getting currently authenticated user
		
		String username = auth.getName();		//getting current user name(username)
						
		modelAndView.getModel().put("message", username);
		
		modelAndView.setViewName("app.message");
		
		return modelAndView;
		
	}

}
