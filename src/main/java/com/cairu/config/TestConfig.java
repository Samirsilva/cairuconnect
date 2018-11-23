package com.cairu.config;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.cairu.service.DbService;
import com.cairu.service.EmailService;
import com.cairu.service.MockEmailService;

@Configuration
@Profile("test")
public class TestConfig {

	@Autowired
	private DbService dbService;
	
	@Bean
	public boolean instanciateDatabase() throws ParseException {
		
		dbService.instaciateDatabase();
		return true;
	}
	
	@Bean
	public EmailService emailService() {
		return new MockEmailService();
	}
}
