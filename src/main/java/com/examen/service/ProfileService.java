package com.examen.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import com.examen.model.entity.Profile;
import com.examen.model.entity.SiteUser;
import com.examen.model.repository.ProfileDao;



@Service
public class ProfileService {
	
	
	@Autowired
	ProfileDao profileDao;
	
	//@PreAuthorize("isAuthenticated()")											//saving users profile only to authenticated users
	public void save(Profile profile) {												//method is unreachable to unauthenticated users
		profileDao.save(profile);
	}
	
	//@PreAuthorize("isAuthenticated()")												//returning users profile
	public Profile getUserProfile(SiteUser user) {									//method is unreachable to unauthenticated users
		return profileDao.findByUser(user);
		
	}

}
