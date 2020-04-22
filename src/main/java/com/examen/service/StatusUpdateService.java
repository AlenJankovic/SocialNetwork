package com.examen.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.examen.model.StatusUpdate;
import com.examen.model.StatusUpdateDao;

@Service
public class StatusUpdateService {
	
	@Autowired
	private StatusUpdateDao statusUpdateDao;
	
	public void save(StatusUpdate statusUpdate) {			//save status update
		statusUpdateDao.save(statusUpdate);
		
	}
	
	public StatusUpdate getLatest(){
		return statusUpdateDao.findFirstByOrderByAddedDesc();	//getting latest status update
	}
	
}
