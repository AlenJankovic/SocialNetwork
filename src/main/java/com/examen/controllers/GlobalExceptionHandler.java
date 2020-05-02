package com.examen.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class GlobalExeptionHandler {
	
	@ExceptionHandler(value = Exception.class)            //handling all type of exceptions
	public ModelAndView defaultErrorHandler(HttpServletRequest req, Exception e) {
		ModelAndView modelAndView = new ModelAndView();
		
		modelAndView.getModel().put("message", "An error accure");
		modelAndView.setViewName("app.message");
		
		return modelAndView ;
		
	}
}
