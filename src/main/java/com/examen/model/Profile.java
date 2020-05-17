package com.examen.model;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderColumn;
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
	@JoinColumn(name = "user_id", nullable = false)		//every user must have profile(can't be empty)
	private SiteUser user;
	
	@Column(name = "about", length = 4000)
	@Size(max = 4000, message ="{editprofile.about.size}")
	private String about;
	
	@Column(name= "photo_directory" ,length = 10)
	private String photoDirectory;
	
	@Column(name= "photo_name", length = 10)
	private String photoName;
	
	@Column(name= "photo_extention", length = 5)
	private String photoExtention;
	
	@ManyToMany(fetch=FetchType.EAGER)							//FetchType.EAGER loading profile and interests at the same time
	@JoinTable(name="profile_interests",
	joinColumns= {@JoinColumn(name="profile_id")},	
	inverseJoinColumns ={@JoinColumn(name="interest_id")})
	@OrderColumn(name="display_order")							//Specifies a column that is used to maintain the persistent order of a list		
	private Set<Interest> interests;
	
	
	public Profile() {
		
	}
	
	public Profile(SiteUser user) {								//use for testing
		this.user = user;
	}
	
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
			if(other.about != null) {					//is acceptable to display in JSP
				this.about = other.about;
			}
			
			if(other.interests != null) {				//copying only interest field or fields which are not confident(copyof profile object)
				this.interests = other.interests;		//is acceptable to display in JSP
			}
	}


	public void safeMergeFrom(Profile webProfile, PolicyFactory htmlPolicy) {		//sanitize copy suitable to save
		if(webProfile.about != null) {
			this.about = htmlPolicy.sanitize(webProfile.about);
		}
		
	}


	
	public String getPhotoDirectory() {
		return photoDirectory;
	}


	
	public String getPhotoName() {
		return photoName;
	}


	
	public String getPhotoExtention() {
		return photoExtention;
	}


	
	public void setPhotoDirectory(String photoDirectory) {
		this.photoDirectory = photoDirectory;
	}


	
	public void setPhotoName(String photoName) {
		this.photoName = photoName;
	}


	
	public void setPhotoExtention(String photoExtention) {
		this.photoExtention = photoExtention;
	}
	
	public void setPhotoDetail(FileInfo info) {
		photoDirectory = info.getSubDirectory();
		photoExtention = info.getExtention();
		photoName = info.getBasename();
		
	}
	
	public Path getPhoto(String baseDirectory) {
		
		if(photoName == null) {																		//if there is no photo ,returning null, can use it to set default photo
			return null;
		}
		
		return Paths.get(baseDirectory, photoDirectory, photoName + "." + photoExtention);			//returning full path to photo
		
	}



	public Set<Interest> getInterests() {
		return interests;
	}


	
	public void setInterests(Set<Interest> interests) {
		this.interests = interests;
	}

	public void addInterest(Interest interest) {
		interests.add(interest);								//adding interests to interest Set
		
	}

	public void removeInterest(String interestName) {			//removing interest from Set (interests with same name)
		interests.remove(new Interest(interestName));
		
	}

	@Override
	public String toString() {
		return "Profile [id=" + id + ", user=" + user + ", about=" + about + ", photoDirectory=" + photoDirectory
				+ ", photoName=" + photoName + ", photoExtention=" + photoExtention + ", interests=" + interests + "]";
	}
	
	
}
