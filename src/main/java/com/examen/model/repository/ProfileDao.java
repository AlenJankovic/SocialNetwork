package com.examen.model.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.examen.model.entity.Profile;
import com.examen.model.entity.SiteUser;

@Repository
public interface ProfileDao extends CrudRepository<Profile, Long>{
	
		Profile findByUser(SiteUser user);

		List<Profile> findByInterestsName(String text);
}
