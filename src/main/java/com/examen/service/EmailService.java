package com.examen.service;

import java.util.Date;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

@Service
public class EmailService {
	
	private TemplateEngine templateEngine;
	
	
	@Autowired
	private JavaMailSender mailSender;
	
	@Value("${mail.enable}")			//enabling or disabling mail in properties file
	private Boolean enable;
	
	@Value("${site.url}")				//site url from properties file
	private String url;
	
	public void send(MimeMessagePreparator preparator) {
		if(enable) {
			mailSender.send(preparator);
		}
	}
	
	@Autowired
	public EmailService(TemplateEngine templateEngine) {        //EmailService constructor
		
		ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
		templateResolver.setPrefix("/mailtemplate");
		templateResolver.setSuffix(".html");
		templateResolver.setTemplateMode("HTML5");
		templateResolver.setCacheable(false);       //not caching emails
		templateEngine.setTemplateResolver(templateResolver);
		this.templateEngine = templateEngine; 
	}
	@Async					//if mail server dosn't respond at once program goes to next method
	public void sendVerificationEmail(String emailAddress,String token) {      //passing token to thymeleaf Context
																			   //Sends verifikation mail to users mail address
		Context context = new Context();
		context.setVariable("token", token);
		context.setVariable("url", url);
		
		
		String emailContents = templateEngine.process("/verifyemail", context);
		
		MimeMessagePreparator preparator =new MimeMessagePreparator(){

			@Override
			public void prepare(MimeMessage mimeMessage) throws Exception {
				
				MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
				
				message.setTo(emailAddress);
				message.setFrom(new InternetAddress("no-replay@examen.com"));
				message.setSubject("Please Verify Your Emai Address");
				message.setSentDate(new Date());
				
				message.setText(emailContents,true);
				}
			
			
		};
		
		send(preparator);
		
	}
}
