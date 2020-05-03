package com.examen.controllers;


import javax.validation.Valid;

import org.owasp.html.PolicyFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.examen.model.Profile;
import com.examen.model.SiteUser;
import com.examen.service.ProfileService;
import com.examen.service.UserService;

@Controller
public class ProfileController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	ProfileService profileService;
	
	@Autowired
	PolicyFactory htmlPolicy;
	
	
	private SiteUser getUser() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication(); // getting currently authenticated
																						// user
		String email = auth.getName(); // getting current user name(username is email))
		
		return userService.get(email);

	}
	
	@RequestMapping(value="/profile")							//showing own profile
	public ModelAndView showeProfile(ModelAndView modelAndView) {
		
		SiteUser user = getUser();								//getting  current auth. user
		
		Profile profile = profileService.getUserProfile(user);   //getting  current users profile
		
		if(profile == null) {									//if the user does not have a profile, create new profile
				profile = new Profile();
				profile.setUser(user);                  //binding user with created profile
				profileService.save(profile);			//saving new profile
		}
		
		Profile webProfile = new Profile();
		webProfile.safeCopyFrom(profile);				//exposing only information from about field 
		
		modelAndView.getModel().put("profile", webProfile);		//exposing only information from about field to the web
		
		
		modelAndView.setViewName("app.profile");
		
		return modelAndView;
		
	}
	
	@RequestMapping(value="/edit-profile-about", method = RequestMethod.GET)
	public ModelAndView editAbout(ModelAndView modelAndView) {
		
		SiteUser user = getUser();								//getting  current auth. user
		
		Profile profile = profileService.getUserProfile(user);	//getting  current users profile
		
		Profile webProfile = new Profile();
		webProfile.safeCopyFrom(profile);				//exposing only information from 'about' field 
		
		
		modelAndView.getModel().put("profile", webProfile);
		
		modelAndView.setViewName("app.editprofileabout");
		
		return modelAndView;
		
	}
	@RequestMapping(value="/edit-profile-about", method = RequestMethod.POST)
	public ModelAndView editAbout(ModelAndView modelAndView, @Valid Profile webProfile ,BindingResult result) {
		
		modelAndView.setViewName("app.editprofileabout");
		
		SiteUser user = getUser();								//getting  current auth. user
		
		Profile profile = profileService.getUserProfile(user);	//getting  current users profile
		
		profile.safeMergeFrom(webProfile, htmlPolicy);						//adding ny information  from 'about' field
																			//and sanitizing html
		if(!result.hasErrors()) {
			profileService.save(profile);						//saving profile with new information
			modelAndView.setViewName("redirect:/profile");		//redirecting to profile page
		}
		
		
		
		return modelAndView;
		
	}
	

}
