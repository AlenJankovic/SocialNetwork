package com.examen.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="profile")
public class Profile {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long id;
	
	@OneToOne(targetEntity = SiteUser.class)			//@OneToOne -each user has profile
	@JoinColumn(name = "user_id", nullable = false)		//every user must have profile(can be empty)
	private SiteUser user;
	
	@Column(name = "about", length = 4000)
	private String about;

	
	public SiteUser getUser() {
		return user;
	}

	
	public String getAbout() {
		return about;
	}

	
	public void setUser(SiteUser user) {
		this.user = user;
	}

	
	
	public Long getId() {
		return id;
	}


	
	public void setId(Long id) {
		this.id = id;
	}


	public void setAbout(String about) {
		this.about = about;
	}

	
}
