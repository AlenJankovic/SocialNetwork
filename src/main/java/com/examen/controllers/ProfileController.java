	package com.examen.controllers;


import java.io.IOException;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import javax.validation.Valid;

import org.owasp.html.PolicyFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.examen.exceptions.ImageTooSmallException;
import com.examen.exceptions.InvalidFileException;
import com.examen.model.dto.FileInfo;
import com.examen.model.entity.Interest;
import com.examen.model.entity.Profile;
import com.examen.model.entity.SiteUser;
import com.examen.service.FileService;
import com.examen.service.InterestService;
import com.examen.service.ProfileService;
import com.examen.service.UserService;
import com.examen.status.PhotoUploadStatus;

import ch.qos.logback.core.util.FileSize;


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
	
	@Autowired
	private InterestService interestService;
	
	@Value("${photo.upload.dir}")			//getting value/path from properties file
	private String photoUploadDirectory;
	
	@Value("${photo.upload.ok}")			
	private String photoStatusOk;
	
	@Value("${photo.upload.invalid}")			
	private String photoStatusInvalid;
	
	@Value("${photo.upload.ioexception}")			
	private String photoStatusIOExeption;
	
	@Value("${photo.upload.toosmall}")			
	private String photoStatusTooSmall;
	
	
	
	private SiteUser getUser() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication(); // getting currently authenticated
																						// user
		String email = auth.getName(); // getting current user name(username is email))
		
		return userService.get(email);

	}
	
	private ModelAndView showProfile(SiteUser user) {
		
		ModelAndView modelAndView = new ModelAndView();
		
		if(user == null) {											//checking if user not exist redirecting to home
			modelAndView.setViewName("redirect:/");
			return modelAndView;
		}
		
		Profile profile = profileService.getUserProfile(user);   	//getting  current users profile
		
		if(profile == null) {										//if the user does not have a profile, create new profile
				profile = new Profile();
				profile.setUser(user);                  			//binding user with created profile
				profileService.save(profile);						//saving new profile
		}
		
		Profile webProfile = new Profile();
		webProfile.safeCopyFrom(profile);							//exposing only information from about field 
		
		modelAndView.getModel().put("userId", user.getId());		
		modelAndView.getModel().put("profile", webProfile);			//exposing only information from about field to the web
		
		
		modelAndView.setViewName("app.profile");
		
		return modelAndView;
		
	}
		
	@RequestMapping(value="/profile")							     //showing own profile
	public ModelAndView showProfile() {
		
		SiteUser user = getUser();									//getting  current auth. user
		
		ModelAndView modelAndView = showProfile(user);
		
		modelAndView.getModel().put("ownProfile", true);			//own profile
		
		return modelAndView;
		
	}
	
	@RequestMapping(value="/profile/{id}")							//getting user profile with specified id						     
	public ModelAndView showProfile(@PathVariable("id") Long id) {
		
		SiteUser user = userService.get(id);									
		
		ModelAndView modelAndView = showProfile(user);
		
		modelAndView.getModel().put("ownProfile", false);			//other users profile
		
		return modelAndView;
		
	}
	@RequestMapping(value="/edit-profile-about", method = RequestMethod.GET)
	public ModelAndView editAbout(ModelAndView modelAndView) {
		
		SiteUser user = getUser();								//getting  current auth. user
		
		Profile profile = profileService.getUserProfile(user);	//getting  current users profile
		
		Profile webProfile = new Profile();
		webProfile.safeCopyFrom(profile);						//exposing only information from 'about' field 
		
		
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
																			//and sanitizing html accepting only predifined html tags
		if(!result.hasErrors()) {
			profileService.save(profile);						//saving profile with new information
			modelAndView.setViewName("redirect:/profile");		//redirecting to profile page
		}
		
		return modelAndView;
		
	}
	
	@RequestMapping(value="/upload-profile-photo", method=RequestMethod.POST)					//uploading photo file to Photo directory and showing status messages
	@ResponseBody																				//Annotation that indicates a method return value should be bound to the webresponse body.Returning data from method(JSON format)							
	public ResponseEntity<PhotoUploadStatus> handlePhotoUploads(@RequestParam("file") MultipartFile file) {	//MultipartFile is spring class	- ResponseEntity adds a HttpStatus status code
		
		SiteUser user = getUser();								//getting  current auth. user
		Profile profile = profileService.getUserProfile(user);	//getting  current users profile
		
		Path oldPhotoPath = profile.getPhoto(photoUploadDirectory);   //getting path to existing profile pic 
		
		PhotoUploadStatus status = new PhotoUploadStatus(photoStatusOk);	//Creating PhotoUploadStatus instance to manage error messages 
		
		try {
			FileInfo photoInfo = fileService.saveImageFile(file, photoUploadDirectory, "photos", "p" + user.getId(),100,100);		//saving image with unique name (using userID) to photo directory
																																	//and returning photo info
			profile.setPhotoDetail(photoInfo);					//adding photo details to profile
			
			profileService.save(profile);						//saving profile and adding photo details information
			
			if(oldPhotoPath != null) {
				Files.delete(oldPhotoPath);  					//deleting old photo
			}
		
		} catch (InvalidFileException e) {
			status.setMessage(photoStatusInvalid);
				e.printStackTrace();
		
		} catch (ImageTooSmallException e) {
			status.setMessage(photoStatusTooSmall);
				e.printStackTrace();
		
		}catch(IOException e) {
			status.setMessage(photoStatusIOExeption);
				e.printStackTrace();
		}
		
		return new ResponseEntity(status,HttpStatus.OK );
		
	}
	
	@RequestMapping(value="/profilephoto/{id}", method=RequestMethod.GET)						//serving photo
	@ResponseBody																				//Annotating that data method return should display directly(not mapped to tiles) 
	ResponseEntity<InputStreamResource> servPhoto(@PathVariable Long id) throws IOException{ 	// setting content to send to the browser
		
		SiteUser user = userService.get(id);							//getting  current auth. user
		Profile profile = profileService.getUserProfile(user);			//getting  current users profile
		
		Path photoPath = Paths.get(photoUploadDirectory, "default", "avatar.png");		//creating path to default picture (avatar)
		
		if(profile != null && profile.getPhoto(photoUploadDirectory) != null) {			//if profile exist and profile photo is uploaded overriding photoPath and
			photoPath = profile.getPhoto(photoUploadDirectory);							//setting photoPath to uploaded profile picture
		}
		
		return ResponseEntity															//sending information to the browser
				.ok()
				.contentLength(Files.size(photoPath))
				.contentType(MediaType.parseMediaType(URLConnection.guessContentTypeFromName(photoPath.toString())))   //guessing mediatype content with help of file name
				.body(new InputStreamResource(Files.newInputStream(photoPath, StandardOpenOption.READ)));              //reading bits from photo file and returning that
	}
	
	@RequestMapping(value ="/save-interest" ,method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<?> saveInterest(@RequestParam("name") String interestName){
		
		SiteUser user = getUser();
		
		Profile profile = profileService.getUserProfile(user);
		
		String cleanInterestName = htmlPolicy.sanitize(interestName);
		
		Interest interest = interestService.createIfNotExist(cleanInterestName);			//create if not exist otherwise returning existing interest
		
		
		profile.addInterest(interest);														//adding iterest	
		profileService.save(profile);														//saving profile
		
		return new ResponseEntity(null,HttpStatus.OK);
	}
	@RequestMapping(value ="/delete-interest" ,method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<?> deleteInterest(@RequestParam("name") String interestName){
		
		SiteUser user = getUser();
		
		Profile profile = profileService.getUserProfile(user);
		
		profile.removeInterest(interestName);					//calling remove methode in profile and removing interest
			
		profileService.save(profile);							//saving profile
		
		return new ResponseEntity(null,HttpStatus.OK);
	}
	
	

}
