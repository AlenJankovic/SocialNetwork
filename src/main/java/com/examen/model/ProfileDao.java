package com.examen.model;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfileDao extends CrudRepository<Profile, Long>{
	
		Profile findByUser(SiteUser user);
}
