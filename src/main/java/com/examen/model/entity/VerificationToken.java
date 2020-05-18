package com.examen.model.entity;

import java.util.Calendar;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="verification")
public class VerificationToken {			

	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name="token")
	private String token;
	
	@OneToOne(targetEntity = SiteUser.class)
	@JoinColumn(name="user_id" , nullable = false)
	private SiteUser user;
	
	@Column(name="expiry_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date expiry;
	
	@Column(name= "token_type")
	@Enumerated(EnumType.STRING)		// Enum type is String
	private TokenType type;
	
	@PrePersist							//Date of expiry of token	
	private void setDate() {
		Calendar c = Calendar.getInstance();
		c.add(Calendar.HOUR, 24);		//adding 24 hours to current date 
		expiry = c.getTime();			//make token valid 24 hours
	}

	public VerificationToken() {		//hibernate needs an empty constructor
		
	}
	
	public VerificationToken(String token, SiteUser user, TokenType type) {
		
		this.token = token;
		this.user = user;
		this.type = type;
	}

	
	public Long getId() {
		return id;
	}


	public String getToken() {
		return token;
	}

	
	public SiteUser getUser() {
		return user;
	}

	
	public Date getExpiry() {
		return expiry;
	}

	
	public TokenType getType() {
		return type;
	}

	
	public void setId(Long id) {
		this.id = id;
	}

	
	public void setToken(String token) {
		this.token = token;
	}

	
	public void setUser(SiteUser user) {
		this.user = user;
	}

	
	public void setExpiry(Date expiry) {
		this.expiry = expiry;
	}

	
	 
	public void setType(TokenType type) {
		this.type = type;
	}
	
	
}
