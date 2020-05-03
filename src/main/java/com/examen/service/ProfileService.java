package com.examen.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.examen.model.Profile;
import com.examen.model.ProfileDao;
import com.examen.model.SiteUser;



@Service
public class ProfileService {
	
	
	@Autowired
	ProfileDao profileDao;
													//saving users profile
	public void save(Profile profile) {
		profileDao.save(profile);
	}
													//returning users profile
	public Profile getUserProfile(SiteUser user) {
		return profileDao.findByUser(user);
		
	}

}
