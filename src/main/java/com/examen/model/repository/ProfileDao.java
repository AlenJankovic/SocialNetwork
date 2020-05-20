package com.examen.model.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.examen.model.entity.Profile;
import com.examen.model.entity.SiteUser;

@Repository
public interface ProfileDao extends CrudRepository<Profile, Long>{
	
		Profile findByUser(SiteUser user);

		List<Profile>findByInterestsNameContainingIgnoreCase(String text);
		Page<Profile>findByInterestsNameContainingIgnoreCase(String text, Pageable request);  //text and Page request object
}
