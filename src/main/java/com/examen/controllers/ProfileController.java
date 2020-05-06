package com.examen.controllers;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.examen.exceptions.InvalidFileException;
import com.examen.model.FileInfo;
import com.examen.model.Profile;
import com.examen.model.SiteUser;
import com.examen.service.FileService;
import com.examen.service.ProfileService;
import com.examen.service.UserService;


@Controller
public class ProfileController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ProfileService profileService;
	
	@Autowired
	private PolicyFactory htmlPolicy;
	
	@Autowired
	private FileService fileService;
	
	@Value("${photo.upload.dir}")			//getting value/path from properties file
	private String photoUploadDirectory;
	
	
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
	
	@RequestMapping(value="/upload-profile-photo", method=RequestMethod.POST)					//uploading photo file to Photo directory 
	public ModelAndView handlePhotoUploads(ModelAndView modelAndView, @RequestParam("file") MultipartFile file) {	//MultipartFile is spring class	
		
		modelAndView.setViewName("redirect:/profile");
		
		SiteUser user = getUser();								//getting  current auth. user
		Profile profile = profileService.getUserProfile(user);	//getting  current users profile
		
		try {
			FileInfo photoInfo = fileService.saveImageFile(file, photoUploadDirectory, "photos", "profile");			//saving image to photo directory
																														//and returning photo info
			profile.setPhotoDetail(photoInfo);					//adding photo details to profile
			
			profileService.save(profile);						//saving profile and adding photo details information
		
		} catch (InvalidFileException | IOException e) {
			
			e.printStackTrace();
		}
		
		return modelAndView;
		
	}
	
	

}
