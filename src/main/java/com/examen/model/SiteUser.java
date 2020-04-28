package com.examen.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name ="users")
public class SiteUser {
	
	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.AUTO )
	private Long id;
	
	@Column(name="email" , unique = true)
	private String email;
	
	@Column(name="password",length=60)
	private String password;

	@Column(name="role", length=20)
	private String role;
	
	public Long getId() {
		return id;
	}

	
	public String getEmail() {
		return email;
	}

	
	public String getPassword() {
		return password;
	}

	
	public void setId(Long id) {
		this.id = id;
	}

	
	public void setEmail(String email) {
		this.email = email;
	}

	
	
	public void setPassword(String password) {
		this.password = password;
	}


	
	public String getRole() {
		return role;
	}


	
	public void setRole(String role) {
		this.role = role;
	}
	

	
}
