package com.examen.model;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfileDao extends CrudRepository<Profile, Long>{
	
		Profile findByUser(SiteUser user);

		List<Profile> findByInterestsName(String text);
}
