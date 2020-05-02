package com.examen.controllers;



import java.io.FileNotFoundException;
import java.util.Date;

import javax.validation.Valid;

import org.junit.jupiter.params.shadow.com.univocity.parsers.annotations.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.examen.model.SiteUser;
import com.examen.model.VerificationToken;
import com.examen.service.EmailService;
import com.examen.service.UserService;

@Controller
public class AuthController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private EmailService emailService;
	
	@Value("${message.registration.confirmed}")
	private String registrationConfirmedMessage;
	
	@Value("${message.invalid.user}")
	private String invalidUserMessage;
	
	@Value("${message.expired.token}")
	private String expiredToken;
	
	@RequestMapping("/login")
	String admin() {
		
		return "app.login";
	}
	
	@RequestMapping("/verifyemail")
	String verifyemail() {
		
		return "app.verifyemail";
	}
	
	@RequestMapping("/confirmregister")                       //RequestMapping for message when user confirm email
	ModelAndView registrationConfirmed(ModelAndView modelAndView ,@RequestParam("t") String tokenString) {		
		
		VerificationToken token = userService.getVerificationToken(tokenString);
		
		if(token == null) {										//if token value is  invalid
			modelAndView.setViewName("redirect:/invaliduser");
			userService.deleteToken(token);						//deleting token
			return modelAndView;
		}
		
		Date expiryDate = token.getExpiry();       				//getting expiry date
		
		if(expiryDate.before(new Date())) {						// if token is before current date then token expired and redirecting to expired page
			modelAndView.setViewName("redirect:/expiredtoken");
			userService.deleteToken(token);						//deleting token if expired
			return modelAndView;
		}
		
		SiteUser user = token.getUser();
		
		if(user == null) {										//if user is not in DB
			modelAndView.setViewName("redirect:/invaliduser");
			userService.deleteToken(token);						//deleting token if user is not in DB
			return modelAndView;
		}
		
		userService.deleteToken(token);				
		user.setEnabled(true);                     //if we pass all conditions set user to enabled
		userService.save(user); 					//saving user
		
		
		modelAndView.getModel().put("message" , registrationConfirmedMessage);
		modelAndView.setViewName("app.message");
		
		return modelAndView;
	}
	
	@RequestMapping("/invaliduser")
	ModelAndView invalidUser(ModelAndView modelAndView) {			//RequestMapping for message if user is not in register
		
		modelAndView.getModel().put("message" , invalidUserMessage);
		modelAndView.setViewName("app.message");
		
		return modelAndView;
	}
	@RequestMapping("/expiredtoken")
	ModelAndView expiredToken(ModelAndView modelAndView) {			//RequestMapping for message when token is expired
		
		modelAndView.getModel().put("message" , expiredToken);
		modelAndView.setViewName("app.message");
		
		return modelAndView;
	}
	
	
	@RequestMapping(value = "/register",method = RequestMethod.GET)
		
		ModelAndView register(ModelAndView modelAndView ) throws FileNotFoundException {
			
			if(true)
			throw new FileNotFoundException();
			
			SiteUser user = new SiteUser();
			
			modelAndView.getModel().put("user", user);
			
			modelAndView.setViewName("app.register");
			
			return modelAndView;
	}
	@RequestMapping(value = "/register",method = RequestMethod.POST)
	
	ModelAndView register(ModelAndView modelAndView ,@ModelAttribute(value="user") @Valid SiteUser user ,BindingResult result) {
		
		modelAndView.setViewName("app.register");
		
		if(!result.hasErrors()) {
			userService.register(user);
			
			String token = userService.createEmailVerificationToken(user);     //creating verification token for user
			
			emailService.sendVerificationEmail(user.getEmail(),token);
			
			modelAndView.setViewName("redirect:/verifyemail");
			
			
		}
		
		return modelAndView;
}
}
