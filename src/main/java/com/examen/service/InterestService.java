package com.examen.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.examen.model.entity.Interest;
import com.examen.model.repository.InterestDao;

@Service
public class InterestService {
	
	@Autowired
	private InterestDao interestDao;
	
	public long count() {											//count howmany interests is i DB
		
		return interestDao.count();
	}
	public Interest get(String interestName) {
		
		return interestDao.findOneByName(interestName);				//finding interest
	}
	public void save(Interest interest) {							//saving interest
		
		interestDao.save(interest);
	}
	
	public Interest createIfNotExist(String interestText) {				//create interest if not exist
		Interest interest = interestDao.findOneByName(interestText);
		
		if(interest == null) {											//if interest not exist create than
			interest = new Interest(interestText);
			interestDao.save(interest);
		}
		
		return interest;
	}

}
