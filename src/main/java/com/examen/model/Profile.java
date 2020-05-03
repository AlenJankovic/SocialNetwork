package com.examen.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.owasp.html.PolicyFactory;

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
	@Size(max = 4000, message ="{editprofile.about.size}")
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
	
	public void safeCopyFrom(Profile other) {			//copying only about field or fields which are not confident(copyof profile object)
			if(other.about != null) {
				this.about = other.about;
			}
	}


	public void safeMergeFrom(Profile webProfile, PolicyFactory htmlPolicy) {
		if(webProfile.about != null) {
			this.about = htmlPolicy.sanitize(webProfile.about);
		}
		
	}

	
}
