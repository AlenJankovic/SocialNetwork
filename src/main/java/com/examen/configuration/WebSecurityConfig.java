package com.examen.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.examen.service.UserService;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	UserService userService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {

		//@formatter:off				//Swithc off formating 
		http
			.authorizeRequests()
				.antMatchers("/",
							 "/about",
							 "/register",
							 "/registrationconfirmed",
							 "/invaliduser",
							 "/expiredtoken",
							 "/verifyemail",
							 "/confirmregister")
				.permitAll() 			// permiting any user to have access to homepage
				.antMatchers(
						"/js/*",
						"/css/*",
						"/img/*")
				.permitAll()			// permiting any user to have access to js,css,img files		
			.antMatchers("/addstatus",
						 "/viewstatus",
						 "/editstatus",
						 "/deletstatus")
				.hasRole("ADMIN")
				.anyRequest()
				.denyAll()
				.and()
			.formLogin()
				.loginPage("/login")
				.defaultSuccessUrl("/")
				.permitAll()
				.and()
			.logout()
				.permitAll();
		
		
		//@formatter:on					//Switch on formating
	}
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) 
	  throws Exception {
		//@formatter:off		
	    auth.inMemoryAuthentication()	
	      .withUser("sara")
	      .password("{noop}zdravo")
	      .roles("USER");
	      
	  //@formatter:on	
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userService).passwordEncoder(passwordEncoder);
	}

	
}
