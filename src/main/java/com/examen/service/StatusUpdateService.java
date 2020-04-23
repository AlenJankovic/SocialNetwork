package com.examen.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.examen.model.StatusUpdate;
import com.examen.model.StatusUpdateDao;

@Service
public class StatusUpdateService {
	
	private final static int PAGESIZE= 3;	
	
	@Autowired
	private StatusUpdateDao statusUpdateDao;
	//Saving status update
	public void save(StatusUpdate statusUpdate) {			//save status update
		statusUpdateDao.save(statusUpdate);
		
	}
	//getting leatest status update
	public StatusUpdate getLatest(){
		return statusUpdateDao.findFirstByOrderByAddedDesc();	//getting latest status update
	}
	
	//Pagination-getting page
	public Page<StatusUpdate> getPage(int pageNumber){
		PageRequest request = PageRequest.of(pageNumber, PAGESIZE, Sort.Direction.DESC, "added"); //making request of given page,
																								  //PAGESIZE(amount entity's on page),sorting results in desc order on date) 0 based
		 	 	
		return statusUpdateDao.findAll(request);  //Returns a Page of entities meeting the paging restriction provided in the Pageable object
	}
	
}
