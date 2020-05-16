package com.examen.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.examen.validation.PasswordMatch;

@Entity
@Table(name ="users")
@PasswordMatch(message = "{register.repeatpassword.mismatch}")
public class SiteUser {
	
	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.AUTO )
	private Long id;
	
	@Column(name="email" , unique = true)
	@Email(message="{register.email.invalid}")
	@NotBlank(message="{register.email.invalid}")
	private String email;
	
	@Transient
	@Size(min=5 ,max=15,message ="{register.password.size}")
	private String plainPassword;				//Uses onlly to validate plain password user enter not to save to DB
	
	@Column(name="password",length=60)
	private String password;
	
	@Transient
	private String repeatPassword;
	
	@Column(name="enabled")
	private Boolean enabled = false;
	

	@Column(name="role", length=20)
	private String role;
	
	
	public SiteUser() {
		
	}
	
	public SiteUser(String email, String password) {				//constructor för testing 
		this.email = email;
		this.plainPassword = password;
		this.repeatPassword= password;
		this.enabled= true;
	}
	
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


	
	public String getPlainPassword() {
		return plainPassword;
	}


	
	
	public String getRepeatPassword() {
		return repeatPassword;
	}


	public void setRepeatPassword(String repeatPassword) {
		this.repeatPassword = repeatPassword;
	}


	public void setPlainPassword(String plainPassword) {				//setter encrypted password from plainpassword
		this.password = new BCryptPasswordEncoder().encode(plainPassword);
		this.plainPassword = plainPassword;
	}


	
	public Boolean getEnabled() {
		return enabled;
	}


	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}
	
	
	

	
}
