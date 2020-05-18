package com.examen.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.examen.model.entity.SiteUser;
import com.examen.model.entity.TokenType;
import com.examen.model.entity.VerificationToken;
import com.examen.model.repository.UserDao;
import com.examen.model.repository.VerificationDao;

@Service
public class UserService implements UserDetailsService{
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private VerificationDao verificationDao;
	
	public void register(SiteUser user) {		//method to registrate user
		
		user.setRole("ROLE_USER");   			//default roll
		userDao.save(user);
	}
	
	public void save(SiteUser user) {
		userDao.save(user);						//methode to update user
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		SiteUser user = userDao.findByEmail(email);
		
		if(user == null) {
			return null;
		}
												
		List<GrantedAuthority> auth = AuthorityUtils.commaSeparatedStringToAuthorityList(user.getRole());
		
		Boolean enabled = user.getEnabled();
		String password = user.getPassword();
		return new User(email,password,enabled,true,true,true,auth);   //Spring object User
	}
	
	public String createEmailVerificationToken(SiteUser user) {			//Creating verification token
		VerificationToken token = new VerificationToken(UUID.randomUUID().toString(),user, TokenType.REGISTRATION); //class UUID is generating
																													//random token( 128bit string)
		
		verificationDao.save(token);			//saving token
		
		return token.getToken();				//returning String value of token
	}
	
	public VerificationToken getVerificationToken(String token) {		//getting verification token /parameter token comes from link in the email
		
		return verificationDao.findByToken(token);
		
	}

	public void deleteToken(VerificationToken token) {				//deleting token
		verificationDao.delete(token);
		
	}

	public SiteUser get(String email){
		return userDao.findByEmail(email);
		
	}

	public SiteUser get(Long id) {
		return userDao.findById(id).get();
	}

	

}
