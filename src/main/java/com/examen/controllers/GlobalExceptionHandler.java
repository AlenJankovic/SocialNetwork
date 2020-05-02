package com.examen.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	@Value("${message.error.exception}")
	private String exceptionMessage;
	
	@Value("${message.error.duplicate.user}")
	private String duplicatUserMessage;
	
	
	
	@ExceptionHandler(value = Exception.class)            //handling all type of exceptions
	public ModelAndView defaultErrorHandler(HttpServletRequest req, Exception e) {
		ModelAndView modelAndView = new ModelAndView();
		
		modelAndView.getModel().put("message", exceptionMessage);
		modelAndView.getModel().put("url", req.getRequestURL());	//getting url on which exception occurred 
		modelAndView.getModel().put("exception", e);				//Outputting exception from actual page
		
		modelAndView.setViewName("app.exception");
		
		return modelAndView ;
		
	}

	//Duplicate User Exception
	
	@ExceptionHandler(value = DataIntegrityViolationException.class)            //handling duplicate user exceptions
	public ModelAndView duplicatUserHandler(HttpServletRequest req, Exception e) {
		ModelAndView modelAndView = new ModelAndView();
		
		modelAndView.getModel().put("message", duplicatUserMessage);
		modelAndView.getModel().put("url", req.getRequestURL());	//getting url on which exception occurred 
		modelAndView.getModel().put("exception", e);				//Outputting exception from actual page
		
		modelAndView.setViewName("app.exception");
		
		return modelAndView ;
		
	}
}
